package com.leyes.app.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.alibaba.fastjson.JSONObject;
import com.leyes.app.dto.comsystem.PushMessageDto;
import com.leyes.app.enums.DeviceType;
import com.leyes.app.mq.IQueueMessageListener;
import com.leyes.app.mq.QueueReceiver;
import com.leyes.app.queue.QueueManager;
import com.leyes.app.service.ComsystemService;

public class InitMQReceiveInformation implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ComsystemService comsystemService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent()!=null)
			return;

		String host = QueueManager.HOST;
		 
		QueueReceiver pushQR = new QueueReceiver(QueueManager.PUSH_MESSAGE_QUEUE, host);
		pushQR.addQueueMessageListener(new IQueueMessageListener() {
			
		@Override
		public void doWork(String message) throws Exception {
			 PushMessageDto pushMessage = JSONObject.parseObject(message,PushMessageDto.class);
			 ExecutorService executor = Executors.newCachedThreadPool();
							
			 List<String>[] taskListPerThread = distributeTasks(pushMessage.getDeviceTokens(), 8);
			 List<WorkThread> tasks=new ArrayList<WorkThread>();
			 for(int i=0;i<taskListPerThread.length;i++){
				 List<String> list = taskListPerThread[i];
				 
				 tasks.add(new WorkThread(pushMessage.getMessageId(), pushMessage.getType(),
						 list, pushMessage.getTitle(), pushMessage.getText(), pushMessage.getExtras()));
			 }
			 executor.invokeAll(tasks);
			 executor.shutdown();
			}
		 
		 	
	});
 
}
	
	 protected static List<String>[] distributeTasks(List<String> taskList, int threadCount) {  
	        // 每个线程至少要执行的任务数,假如不为零则表示每个线程都会分配到任务  
	        int minTaskCount = taskList.size() / threadCount;  
	        // 平均分配后还剩下的任务数，不为零则还有任务依个附加到前面的线程中  
	        int remainTaskCount = taskList.size() % threadCount;  
	        // 实际要启动的线程数,如果工作线程比任务还多  
	        // 自然只需要启动与任务相同个数的工作线程，一对一的执行  
	        // 毕竟不打算实现了线程池，所以用不着预先初始化好休眠的线程  
	        int actualThreadCount = minTaskCount > 0 ? threadCount  
	                : remainTaskCount;  
	        // 要启动的线程数组，以及每个线程要执行的任务列表  
	        List<String>[] taskListPerThread = new List[actualThreadCount];  
	        int taskIndex = 0;  
	        // 平均分配后多余任务，每附加给一个线程后的剩余数，重新声明与 remainTaskCount  
	        // 相同的变量，不然会在执行中改变 remainTaskCount 原有值，产生麻烦  
	        int remainIndces = remainTaskCount;  
	        for (int i = 0; i < taskListPerThread.length; i++) {  
	            taskListPerThread[i] = new ArrayList<String>();  
	            // 如果大于零，线程要分配到基本的任务  
	            if (minTaskCount > 0) {  
	                for (int j = taskIndex; j < minTaskCount + taskIndex; j++) {  
	                    taskListPerThread[i].add(taskList.get(j));  
	                }  
	                taskIndex += minTaskCount;  
	            }  
	            // 假如还有剩下的，则补一个到这个线程中  
	            if (remainIndces > 0) {  
	                taskListPerThread[i].add(taskList.get(taskIndex++));  
	                remainIndces--;  
	            }  
	        }  
	        // 打印任务的分配情况  
	       /* for (int i = 0; i < taskListPerThread.length; i++) {  
	            System.out.println("线程 "  
	                    + i  
	                    + " 的任务数："  
	                    + taskListPerThread[i].size()  
	                    + " 区间["  
	                    + ((Task) taskListPerThread[i].get(0)).getTaskId()  
	                    + ","  
	                    + ((Task) taskListPerThread[i].get(taskListPerThread[i].size() - 1))  
	                            .getTaskId() + "]");  
	        }  */
	        return taskListPerThread;  
	    }  
	 
	 class WorkThread implements Callable<Boolean>{

		private PushMessageDto message;
		 
		public WorkThread(String messageId,DeviceType type,List<String> deviceTokens,
				String title,String text,Map<String,String> extras) {
			this.message = new PushMessageDto(messageId, type, deviceTokens, title, text, extras);
		}

		public PushMessageDto getMessage() {
			return message;
		}

		public void setMessage(PushMessageDto message) {
			this.message = message;
		}

		@Override
		public Boolean call() throws Exception {
			return comsystemService.push(message);
		}
		 
	 }
	 
}