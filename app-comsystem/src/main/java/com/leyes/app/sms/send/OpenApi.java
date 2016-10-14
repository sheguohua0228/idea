package com.leyes.app.sms.send;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.leyes.app.sms.bean.SendResultBean;
import com.leyes.app.sms.util.PublicUtils;
import com.leyes.app.util.HttpUtils;

public class OpenApi {
	private static String sOpenUrl;
	private static String sAccount;
	private static String sAuthKey;
	private static int nCgid;
	private static int nCsid;

	public static void initialzeAccount(String url, String account,
			String authkey, int cgid, int csid) {
		sOpenUrl = url;
		sAccount = account;
		sAuthKey = authkey;
		nCgid = cgid;
		nCsid = csid;
	}

	// 1000手机号以内
	public static SendResultBean sendOnce(String[] mobileArray, String content,
			String time) {
		try {
			String mobile = StringUtils.join(mobileArray, ",");
			String sRet = querySendOnce(mobile, content, time);
			return PublicUtils.parseResult(sRet);
		} catch (Exception localException) {
		}
		return null;
	}

	// 手机号为2-100才能调用否则报错
	public static SendResultBean sendBatch(String[] mobileArray,
			String[] contentArray, String time) {
		try {
			String mobile = StringUtils.join(mobileArray, ",");
			String content = StringUtils.join(contentArray, "{|}");
			String sRet = querySendBatch(mobile, content, time);
			return PublicUtils.parseResult(sRet);
		} catch (Exception localException) {
		}
		return null;
	}

	protected static String querySendOnce(String mobile, String content,
			String time) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("action=sendOnce&ac=");
		sb.append(sAccount);
		sb.append("&authkey=");
		sb.append(sAuthKey);
		sb.append("&m=");
		sb.append(mobile);
		sb.append("&c=");
		sb.append(PublicUtils.UrlEncode(content, null));
		sb.append("&cgid=");
		sb.append(nCgid);
		sb.append("&csid=");
		sb.append(nCsid);

		if (time != null) {
			sb.append("&t=");
			sb.append(time);
		}
		return HttpUtils.post(sOpenUrl, sb.toString(), "POST", "UTF-8");
	}

	protected static String querySendBatch(String mobile, String content,
			String time) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("action=sendBatch&ac=");
		sb.append(sAccount);
		sb.append("&authkey=");
		sb.append(sAuthKey);
		sb.append("&m=");
		sb.append(mobile);
		sb.append("&c=");
		sb.append(PublicUtils.UrlEncode(content, null));
		sb.append("&cgid=");
		sb.append(nCgid);
		sb.append("&csid=");
		sb.append(nCsid);

		if (time != null) {
			sb.append("&t=");
			sb.append(time);
		}
		return HttpUtils.post(sOpenUrl, sb.toString(), "POST", "UTF-8");
	}

}
