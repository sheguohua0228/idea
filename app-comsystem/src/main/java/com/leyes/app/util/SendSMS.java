package com.leyes.app.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.leyes.app.exceptions.ComsystemException;
import com.leyes.app.sms.bean.SendResultBean;
import com.leyes.app.sms.send.OpenApi;

/**
 * @author （只能用于发送验证码）
 * 
 *         以下为：发送推广信息 1001@501225190002 2428130DBBFCBD16706C99B9EEBB7E47
 *         cgid:6566
 * 
 */
public class SendSMS {

	private static Logger logger = LogManager.getLogger(SendSMS.class);

	private static String sOpenUrl = "http://smsapi.c123.cn/OpenPlatform/OpenApi";

	// 接口帐号
	private static final String account = "1001@501225190001";

	// 接口密钥
	private static final String authkey = "FDEDBE75C2E49B2B7ABBA670C7B5317A";

	// 通道组编号
	private static final int cgid = 5440;

	// 默认使用的签名编号(未指定签名编号时传此值到服务器)
	private static final int csid = 0;

	public static boolean sendOnce(String[] mobileArray, String content) throws Exception{
		// 发送参数
		OpenApi.initialzeAccount(sOpenUrl, account, authkey, cgid, csid);

		SendResultBean result = OpenApi.sendOnce(mobileArray, content, null);
		logger.error(result.toString());
		if (result.getResult() != 1)
			throw new ComsystemException(result.getErrMsg());
		return true;
	}

	public static boolean sendBatch(String[] mobileArray, String[] contentArray)throws Exception {
		// 发送参数
		OpenApi.initialzeAccount(sOpenUrl, account, authkey, cgid, csid);
		SendResultBean result =OpenApi.sendBatch(mobileArray, contentArray, null);
		logger.error(result.toString());
		if (result.getResult() != 1)
			throw new ComsystemException(result.getErrMsg());
		return true;
	}
}
