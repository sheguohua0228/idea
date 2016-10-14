package com.leyes.app.mq;
 

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/** 
 * @TypeName: QueueSender 
 * @Description: 队列发送器 只支持广播
 * @author：duanwei 
 * @date 2015年6月3日 上午11:55:15 
 *  
 */
public class QueueSender {
	private String default_exchange_name="leyes.default.exchange"; 
	private String default_queue_name=""; 
	private ConnectionFactory connectionFactory;
	private Connection connection;
	private Channel channel;
	private String host;
	 
	
	/**
	 * 队列构造
	* <p>Title: </p> 
	* <p>Description: </p> 
	* @param exchange_name
	* @param queue_name
	* @param host
	* @throws IOException
	 */
	public QueueSender(String queue_name,String host){
		this.default_queue_name = queue_name;
		this.host=host;
		initAMQ();
	}
 
 
	
	/**
	 * 初始队列相关信息
	* @Title: initAMQ 
	* @Description: TODO
	* @return void    
	* @throws
	 */
	private void initAMQ(){
		try{
		connectionFactory = new ConnectionFactory();
		connectionFactory.setHost(host);
		connection = connectionFactory.newConnection();  
		}catch(Exception e){
		}
	}
	 
	/**
	 * 打开通道
	* @Title: open 
	* @Description: TODO
	* @throws IOException
	* @return void    
	* @throws
	 */
	private void open() throws Exception{
		connection = connectionFactory.newConnection(); 
		channel = connection.createChannel();
		//channel.exchangeDeclare(this.default_exchange_name,ExchangeTypes.FANOUT,true,false,null); 
		//TODO 广播方式改为路由方式
		channel.queueDeclare(this.default_queue_name, true, false, false, null);
	}
	
	/**
	 * 发送消息
	* @Title: Send 
	* @Description: TODO
	* @param object
	* @return void    
	* @throws
	 */
	public void send(Object object){
		String json=JSONObject.toJSONString(object);
		try {
			open();  
			//	channel.basicPublish(this.default_exchange_name, "", null, json.getBytes());
			channel.basicPublish("", this.default_queue_name, MessageProperties.PERSISTENT_TEXT_PLAIN, json.getBytes());
		} catch (Exception e) {
		}finally{
			try {
				channel.close(); 
				connection.close();
			} catch (Exception e) {
			}
		}
	}
	
 
}
