package com.leyes.app.sms.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.leyes.app.sms.bean.SendResultBean;

public class PublicUtils {
	public static String UrlEncode(String sName, String encodeing) {
		String sValue = "";
		if (sName == null) {
			return sValue;
		}
		try {
			if (encodeing == null) {
				sValue = URLEncoder.encode(sName, "UTF-8");
			} else {
				sValue = URLEncoder.encode(sName, encodeing);
			}
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		return sValue;
	}

	private static String getErrorMsg(int result) {
		String message = "未知错误";
		switch (result) {
		case 1:
			message = "操作成功";
			break;
		case 0:
			message = "帐户格式不正确";
			break;
		case -1:
			message = "服务器拒绝(速度过快、限时或绑定IP不对等)";
			break;
		case -2:
			message = "密钥不正确";
			break;
		case -3:
			message = "密钥已锁定";
			break;
		case -4:
			message = "参数不正确";
			break;
		case -5:
			message = "无此帐户";
			break;
		case -6:
			message = "帐户已锁定或已过期";
			break;
		case -7:
			message = "帐户未开启接口发送";
			break;
		case -8:
			message = "不可使用该通道组";
			break;
		case -9:
			message = "帐户余额不足";
			break;
		case -10:
			message = "内部错误";
			break;
		case -11:
			message = "扣费失败";
			break;
		}
		return message;
	}

	public static SendResultBean parseResult(String xml) {
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml);
		} catch (Exception e) {
		}
		Element root = doc.getRootElement();
		Attribute result = root.attribute("result");
		int resultCode = Integer.parseInt(result.getValue());
		return new SendResultBean(resultCode, getErrorMsg(resultCode));
	}

}
