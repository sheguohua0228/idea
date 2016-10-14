package com.aplus.lk.clothes.utils;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shcm.bean.BalanceResultBean;
import com.shcm.bean.SendResultBean;
import com.shcm.send.DataApi;
import com.shcm.send.OpenApi;

/**
 * @author Chinafish
 *
 */
public class SendMessageMOMingUtil
{
	private static Logger logger = LoggerFactory.getLogger(SendMessageMOMingUtil.class);
	private static String sOpenUrl = "http://smsapi.c123.cn/OpenPlatform/OpenApi";
	private static String sDataUrl = "http://smsapi.c123.cn/OpenPlatform/OpenApi";
	
	// 接口帐号
	private static final String account = "1001@501225190001";
	
	// 接口密钥
	private static final String authkey = "FDEDBE75C2E49B2B7ABBA670C7B5317A";
	
	// 通道组编号
	private static final int cgid = 5440;
	
	// 默认使用的签名编号(未指定签名编号时传此值到服务器)
	private static final int csid = 0;
	
	public static List<SendResultBean> sendOnce(String mobile, String content) throws Exception
	{
		logger.info("发送手机号为："+mobile+"内容为："+content);
		// 发送短信
		return OpenApi.sendOnce(mobile, content, 0, 0, null);
	}
	
	public static String sendMessage(String moble, String msg) throws Exception
	{
		// 发送参数
		OpenApi.initialzeAccount(sOpenUrl, account, authkey, cgid, csid);
		
		// 状态及回复参数
		DataApi.initialzeAccount(sDataUrl, account, authkey);
		
		// 取帐户余额
		BalanceResultBean br = OpenApi.getBalance();
		if(br == null)
		{
			System.out.println("获取可用余额时发生异常!");
			return null;
		}
		
		if(br.getResult() < 1)
		{
			System.out.println("获取可用余额失败: " + br.getErrMsg());
			return null;
		}
		System.out.println("可用条数: " + br.getRemain());
		
		/*
		// 更新接口密钥
		UpdateResultBean ur = OpenApi.updateKey();
		if(ur.getResult() < 1)
		{
			System.out.println("更新接口密钥失败: " + br.getErrMsg());
			return;
		}
		System.out.println("已成功更新密钥,新接口密钥为: " + ur.getAuthKey());
		*/
	 
		List<SendResultBean> listItem = sendOnce(moble, msg);
		if(listItem != null)
		{
			for(SendResultBean t:listItem)
			{
				if(t.getResult() < 1)
				{
					System.out.println("发送提交失败: " + t.getErrMsg());
					return null;
				} 
				logger.info("短信发送成功，发送手机号为："+moble);
				return t.getResult() + "";
			}
		}
		return null;
		 
	}
	public static void main(String[] args) throws Exception {
		sendMessage("15908178140", "123456");
		
	}
}
