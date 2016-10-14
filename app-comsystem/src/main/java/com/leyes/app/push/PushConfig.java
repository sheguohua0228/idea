package com.leyes.app.push;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.leyes.app.push.android.AndroidListcast;
import com.leyes.app.push.ios.IOSListcast;

public class PushConfig {
	
	// appkey 57873980e0f55a5ba8001188
	// appMasterSecret  m6afkn0y2adkvpjet8d5s3pjkuqcbbaz 
	private final static String appkey_android = "57873980e0f55a5ba8001188";
	private final static String appMasterSecret_android = "m6afkn0y2adkvpjet8d5s3pjkuqcbbaz";
	
	private final static String appkey_ios = "579ae8bc67e58e64ba001e5c";
	private final static String appMasterSecret_ios = "rotnntswdybrmdqdzkodxma3ngpqidq8";
	
	private final static String appkey_assistant="57bef0d967e58e06240001de";
	private final static String appMasterSecret_assistant="zlpssk3xpl0fcjcxmlrksjtbjrqgyoae";
	
	public static void sendAssistantUnicast(String[] deviceToken,String title,String text,Map<String,String> extras) throws Exception {
		AndroidListcast unicast = new AndroidListcast(appkey_assistant,appMasterSecret_assistant);
		// TODO Set your device token
		unicast.setDeviceToken(deviceToken);
		//通知栏提示文字
	    unicast.setTicker(title);
		//通知标题
		unicast.setTitle(title);
		//通知文字描述 
		unicast.setText(text);
		
		unicast.goAppAfterOpen();
		unicast.goCustomAfterOpen("push--001");
		//
		unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		unicast.setProductionMode();
		
		Set<String> keySet = extras.keySet();
		if(keySet!=null){
			Iterator<String> iterator=keySet.iterator();
			while(iterator.hasNext()){
				String key = iterator.next();
				unicast.setExtraField(key, extras.get(key));
			}
		}
		PushClient.getInstance().send(unicast);
	}
	/**
	 *  
	* @Title: sendAndroidUnicast 
	* @Description: TODO
	* @param deviceToken 设备唯一标识
	* @throws Exception
	* @return void    
	* @throws
	 */
	public static void sendAndroidUnicast(String[] deviceToken,String title,String text,Map<String,String> extras) throws Exception {
		AndroidListcast unicast = new AndroidListcast(appkey_android,appMasterSecret_android);
		// TODO Set your device token
		unicast.setDeviceToken(deviceToken);
		//通知栏提示文字
	    unicast.setTicker(title);
		//通知标题
		unicast.setTitle(title);
		//通知文字描述 
		unicast.setText(text);
		
		unicast.goAppAfterOpen();
		unicast.goCustomAfterOpen("push--001");
		//
		unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		unicast.setProductionMode();
		
		Set<String> keySet = extras.keySet();
		if(keySet!=null){
			Iterator<String> iterator=keySet.iterator();
			while(iterator.hasNext()){
				String key = iterator.next();
				unicast.setExtraField(key, extras.get(key));
			}
		}
		PushClient.getInstance().send(unicast);
	}
	public static void sendIOSUnicast(String[] token,String title,Map<String,String> extras) throws Exception {
		IOSListcast unicast = new IOSListcast(appkey_ios,appMasterSecret_ios);
		// TODO Set your device token
		unicast.setDeviceToken(token);
		unicast.setAlert(title);
		unicast.setBadge(1);
		unicast.setSound("default");
		// TODO set 'production_mode' to 'true' if your app is under production mode
		unicast.setProductionMode();
		//unicast.setDescription("ios测试");
		// Set customized fields
		Set<String> keySet = extras.keySet();
		if(keySet!=null){
			Iterator<String> iterator=keySet.iterator();
			while(iterator.hasNext()){
				String key = iterator.next();
				unicast.setCustomizedField(key, extras.get(key));
			}
		}
		PushClient.getInstance().send(unicast);
	}
	/*public static void main(String[] args) throws Exception {
		Map<String,String> extras=new HashMap<String,String>();
		extras.put("notifyType", "0");
		extras.put("orderId", "DFCE97E2EC684D97BCCB5D36994DB8AB");
		String jsonStr = "你好, 海堂中心有新的洗衣订单，请尽快处理！";
		 
		sendAssistantUnicast(new String []{"AtOsbYj8qgrI50VvAaY9nxZ2npex7TdQ-1GT4ZbJMwWe"}, "洗衣订单",jsonStr, extras);
	}*/
}
