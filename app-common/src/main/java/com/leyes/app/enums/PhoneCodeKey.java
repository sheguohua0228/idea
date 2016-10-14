package com.leyes.app.enums;

public enum PhoneCodeKey {

	COM_REGIST("com.regist_"), COM_LOGIN("com.login_"),
	COM_FORGET_PASSWORD("com.forgetpassword_");

	private String value;

	private PhoneCodeKey(String value) {
		this.value = value;
	}

	public static PhoneCodeKey translate(int code){
		PhoneCodeKey phoneCodeKey=null;
		switch (code) {
			case 0:
				phoneCodeKey=COM_REGIST;
				break;
			case 1:
				phoneCodeKey=COM_LOGIN;
				break;
			case 2:
				phoneCodeKey=COM_FORGET_PASSWORD;
				break;
		}
		return phoneCodeKey;
	}
	public String getKey() {
		return this.value;
	}
}
