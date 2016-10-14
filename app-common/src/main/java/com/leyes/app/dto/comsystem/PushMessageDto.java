package com.leyes.app.dto.comsystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.leyes.app.enums.DeviceType;

public class PushMessageDto implements Serializable{
    //
	private String messageId;
	
	private DeviceType type;
	//设备编号
	private List<String> deviceTokens;
	//内容标题
	private String title;
	//内容
	private String text;
	//额外参数
	private Map<String,String> extras;

	public PushMessageDto() {
		super();
	}
	public PushMessageDto(String messageId, DeviceType type,
			List<String> deviceTokens, String title, String text,
			Map<String, String> extras) {
		super();
		this.messageId = messageId;
		this.type = type;
		this.deviceTokens = deviceTokens;
		this.title = title;
		this.text = text;
		this.extras = extras;
	}
	public void putExtras(String key,String value){
			if(extras==null){
				extras=new HashMap<String, String>();
			}
			extras.put(key, value);
	}
	public void addDeviceToken(String deviceToken){
		if(deviceTokens==null){
			deviceTokens=new ArrayList<String>(10);
		}
		deviceTokens.add(deviceToken);
	}
	public DeviceType getType() {
		return type;
	}

	public void setType(DeviceType type) {
		this.type = type;
	}

	public List<String> getDeviceTokens() {
		return deviceTokens;
	}
	public void setDeviceTokens(List<String> deviceTokens) {
		this.deviceTokens = deviceTokens;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Map<String, String> getExtras() {
		return extras;
	}

	public void setExtras(Map<String, String> extras) {
		this.extras = extras;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	
	
}
