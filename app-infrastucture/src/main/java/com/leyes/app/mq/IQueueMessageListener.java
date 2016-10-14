package com.leyes.app.mq;

/** 
 * 消息监听器接口
* @TypeName: IQueueMessageListener 
* @Description: TODO
* @author：Jingpeng 
* @date 2016年7月28日 下午2:49:42 
*
 */
public interface IQueueMessageListener {
	/**
	 * 回调执行方法
	* @Title: doWork 
	* @Description: TODO
	* @param message
	* @return void    
	* @throws
	 */
	void doWork(String jsonStr)throws Exception;
}
