package com.leyes.app.mq;
 

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.util.StringUtils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
 

/** 
 * @TypeName: QueueReceiver 
 * @Description: 队列接收器接口
 * @author：duanwei 
 * @date 2015年6月3日 下午1:00:10 
 *  
 */
public class QueueReceiver{
	 
	private String default_exchange_name="leyes.default.exchange";
	private String default_queue_name=""; 
	private ConnectionFactory connectionFactory;
	private Connection connection;
	private Channel channel; 
	private String host; 
	private List<IQueueMessageListener> queueMessageListeners;
	  
    
	 /**
	  * 构造函数
	 * <p>Title: </p> 
	 * <p>Description: </p> 
	 * @param exchange_name
	 * @param queue_name
	 * @param host
	  */
    public QueueReceiver(String queue_name,String host){
		this.default_queue_name=queue_name;
		this.host=host;
		initAMQ();
	}
    
    /**
     * 初始队列信息
    * @Title: initAMQ 
    * @Description: TODO
    * @return void    
    * @throws
     */
    private void initAMQ(){
    	try{
    		queueMessageListeners=new ArrayList<IQueueMessageListener>();
			connectionFactory = new ConnectionFactory();
			connectionFactory.setHost(host);
			connection = connectionFactory.newConnection();
			channel = connection.createChannel();
			//TODO
			//channel.exchangeDeclare(this.default_exchange_name,ExchangeTypes.FANOUT,true,false,null);
			if(StringUtils.isEmpty(this.default_queue_name)){ 
				 // 创建一个非持久的、唯一的且自动删除的队列  
				String queueName =channel.queueDeclare().getQueue();
				// 为转发器指定队列，设置binding  
				channel.queueBind(queueName, this.default_exchange_name, "");  
			}else{
				// 根据名称创建一个持久的，队列，不会删除
				channel.queueDeclare(this.default_queue_name, true, false, false, null);
				// 为转发器指定队列，设置binding  
		    //	channel.queueBind(this.default_queue_name, this.default_exchange_name, "");    
			}
		    QueueingConsumer consumer = new QueueingConsumer(channel); 
		    channel.basicConsume(this.default_queue_name, false, consumer);
		    ExecutorService executorService = Executors.newCachedThreadPool();
		    executorService.execute(new ThreadListener(consumer));
		    executorService.shutdown();
    	}catch(Exception e){
    		 
    	}
    } 



    /**
     * 添加消息回调监听器
    * @Title: addQueueMessageListener 
    * @Description: TODO
    * @param queueMessageListener
    * @return void    
    * @throws
     */
	public void addQueueMessageListener(IQueueMessageListener queueMessageListener) {
		queueMessageListeners.add(queueMessageListener);
	}
 



	public class ThreadListener implements Runnable{
    	private QueueingConsumer consumer;
    	public ThreadListener(QueueingConsumer consumer){
    		this.consumer=consumer;
    	}
    	@Override
    	public void run() { 
    		QueueingConsumer.Delivery delivery;
    		//System.out.println(" [*] Waiting for messages...");
    		while (true) {
    			try {
    				delivery = this.consumer.nextDelivery();
    				String msg=new String(delivery.getBody(),"UTF-8");
    				if(queueMessageListeners.size()>0){
    					for(IQueueMessageListener listener :queueMessageListeners){ 
    						listener.doWork(msg);
    						channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
    					}
    				}
    			} catch (Exception e) {
    				e.printStackTrace();
    			} 
    		}
    	}

    }


 
    
    
}
