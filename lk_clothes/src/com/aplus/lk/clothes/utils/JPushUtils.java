package com.aplus.lk.clothes.utils;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 工具类 - 极光推送
 * 
 * @Title: JPushUtils.java
 * @Package com.aplus.lk.util
 * @Description: TODO
 * @author w.gang
 * @date 2015-1-14 下午2:44:45
 * @version V1.0
 */
public class JPushUtils {

	// 极光推送APP_KEY
	private static final String APP_KEY_ASSISTANT = "af0fbaffbf2235b3c1155f26";
	private static final String MASTER_SECRET_ASSISTANT = "9b98cbce41392e1ac38e9f38";
	
	private static final String APP_KEY_CLIENT = "28b27a9dbe8dfa77fdf6c12b";
	private static final String MASTER_SECRET_CLIENT = "3e0ee712f09c43d235a01390";

	public static JPushClient pushClient;

	public static void main(String[] args) {
		//JPushUtils.sendPushToAssistant("测试推送", "推送成功了吗？", null , "061fb12f7e0",null);
		
		PushResult result = sendPushToClient("测试推送", "推送成功了吗？", null , "000ddfb8549",null);
		System.out.println(result.getMessage());
	}

	public static PushResult sendPushToClient(String title, String alert, String tag, String registrationId, Map<String, String> extras){
		return sendPush(title, alert, tag, registrationId, extras, 0);
	}
	
	public static PushResult sendPushToAssistant(String title, String alert, String tag, String registrationId, Map<String, String> extras){
		return sendPush(title, alert, tag, registrationId, extras, 1);
	}
	
	/**
	 * 消息推送 - 根据TAG分组推送
	 * 
	 * @param title
	 *            推送标题
	 * @param tag
	 *            分组TAG
	 * @return
	 */
	public static PushResult sendPush(String title, String alert, String tag, String registrationId, Map<String, String> extras, int type) {
		
		if(type == 1){ // 推送助手
			pushClient = new JPushClient(MASTER_SECRET_ASSISTANT, APP_KEY_ASSISTANT);
		}else{ // 推送客户端
			pushClient = new JPushClient(MASTER_SECRET_CLIENT, APP_KEY_CLIENT);
		}
		PushResult result = new PushResult();
		PushPayload payload = null;
		try {
			
			if (StringUtils.isNotEmpty(registrationId)) {// 根据注册ID推送
				payload = buildPayloadForRegistrationId(title, alert, registrationId, extras);
			} else if(StringUtils.isNotEmpty(tag)){ // 根据Tag推送
				payload = buildPayloadForTag(title, alert, tag);
			} else{ // 推送所有
				payload = buildPayloadForAll(title, alert);
			}
			pushClient.sendPush(payload);
			result.setStatus(PushResult.SUCCESS);
			result.setMessage("发送成功！");
		} catch (APIConnectionException e) {
			result.setStatus(PushResult.ERROR);
			result.setMessage(e.getMessage());
		} catch (APIRequestException e) {
			result.setStatus(PushResult.ERROR);
			String message = e.getMessage();
			Map<String, Object> errorMap = (Map<String, Object>) JsonUtils.fromJson(message,HashMap.class);
			Map<String, Object> codeMap = (Map<String, Object>) errorMap.get("error");
			result.setCode(((Double) codeMap.get("code")).intValue());
			result.setMessage(codeMap.get("message").toString());
		}
		return result;
	}

	/**
	 * 获取所有用户的 推送负载
	 * 
	 * @param title
	 *            标题
	 * @param alert
	 *            警告
	 * @return
	 */
	public static PushPayload buildPayloadForAll(String title, String alert) {
		PushPayload payload = PushPayload.newBuilder()
				.setPlatform(Platform.android_ios())
				.setAudience(Audience.all())
				.setNotification(
						Notification.newBuilder()
						.setAlert(alert)
						.addPlatformNotification(
								AndroidNotification.newBuilder()
										.setTitle(title)
										.build())
						.addPlatformNotification(
								IosNotification.newBuilder()
										.incrBadge(1)
										.build())
						.build())
				.build();
		return payload;
	}

	/**
	 * 获取所有用户的 推送负载
	 * 
	 * @param title
	 *            标题
	 * @param alert
	 *            警告
	 * @param tag
	 *            标签
	 * @return
	 */
	public static PushPayload buildPayloadForTag(String title, String alert,
			String tag) {
		PushPayload payload = PushPayload.newBuilder()
				.setPlatform(Platform.android_ios())
				.setAudience(Audience.tag(tag))
				.setNotification(
						Notification.newBuilder()
						.setAlert(alert)
						.addPlatformNotification(
								AndroidNotification.newBuilder()
										.setTitle(title)
										.build())
						.addPlatformNotification(
								IosNotification.newBuilder()
										.incrBadge(1)
										.build())
						.build())
				.build();
		return payload;
	}

	public static PushPayload buildPayloadForAlias(String title, String alert,
			String alias) {
		PushPayload payload = PushPayload.newBuilder()
				.setPlatform(Platform.android_ios())
				.setAudience(Audience.alias(alias))
				.setNotification(
						Notification.newBuilder()
						.setAlert(alert)
						.addPlatformNotification(
								AndroidNotification.newBuilder()
										.setTitle(title)
										.build())
						.addPlatformNotification(
								IosNotification.newBuilder()
										.incrBadge(1)
										.build())
						.build())
				.build();
		return payload;
	}

	/**
	 * 
	 * @Title: buildPayloadForRegistrationId
	 * @Description: TODO 根据注册ID推送
	 * @param title
	 *            推送标题
	 * @param alter
	 *            内容
	 * @param registrationId
	 *            注册ID
	 * @return PushPayload
	 * @throws
	 */
	public static PushPayload buildPayloadForRegistrationId(String title,
			String alert, String registrationId, Map<String, String> extras) {
		PushPayload payload = PushPayload
				.newBuilder()
				.setPlatform(Platform.android_ios())
				.setAudience(Audience.registrationId(registrationId))
				.setNotification(
						Notification.newBuilder()
								.setAlert(alert)
								.addPlatformNotification(
										AndroidNotification.newBuilder()
												.setTitle(title)
												.addExtras(extras)
												.build())
								.addPlatformNotification(
										IosNotification.newBuilder()
												.setBadge(1)
												.addExtras(extras)
												.build())
								.build()).
								setOptions(Options.newBuilder().setApnsProduction(true).build()).build();
		return payload;
	}

}
