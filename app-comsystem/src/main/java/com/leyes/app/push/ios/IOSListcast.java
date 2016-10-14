package com.leyes.app.push.ios;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.leyes.app.push.IOSNotification;

public class IOSListcast extends IOSNotification {
	public IOSListcast(String appkey,String appMasterSecret) throws Exception{
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "listcast");	
	}
	
	public void setDeviceToken(String[] token) throws Exception {
    	setPredefinedKeyValue("device_tokens", StringUtils.join(token,","));
    }
	
}
