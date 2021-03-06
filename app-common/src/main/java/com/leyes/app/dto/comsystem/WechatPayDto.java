package com.leyes.app.dto.comsystem;

import java.io.Serializable;

public class WechatPayDto implements Serializable{
	private String appid;//公众账号ID
	private String partnerid;//商户号
	private String prepayid;//预支付交易会话ID
	private String noncestr;//随机字符串
	private String timestamp;//时间戳
	private String sign;//签名
	private String packageValue;
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getPartnerid() {
		return partnerid;
	}
	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}
	public String getPrepayid() {
		return prepayid;
	}
	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}
	public String getNoncestr() {
		return noncestr;
	}
	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getPackageValue() {
		return packageValue;
	}
	public void setPackageValue(String packageValue) {
		this.packageValue = packageValue;
	}
    public String toString(){
	   return "{\"appid\":\""+appid+"\",\"noncestr\":\""+noncestr+"\",\"packageValue\":\""+packageValue+"\",\"partnerid\":\""+partnerid+"\",\"prepayid\":\""+prepayid+"\",\"sign\":\""+sign+"\",\"timestamp\":\""+timestamp+"\"}";
    }
}
