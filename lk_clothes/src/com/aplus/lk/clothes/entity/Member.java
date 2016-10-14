package com.aplus.lk.clothes.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.aplus.lk.clothes.bean.Gender;

public class Member {
	
	public static final String MEMBER_ID_SESSION_NAME = "memberId";// 保存登录会员ID的Session名称
	public static final String MEMBER_USERNAME_COOKIE_NAME = "memberUsername";// 保存登录会员用户名的Cookie名称
	public static final String PASSWORD_RECOVER_KEY_SEPARATOR = "_";// 密码找回Key分隔符
	public static final int PASSWORD_RECOVER_KEY_PERIOD = 10080;// 密码找回Key有效时间（单位：分钟）
	public static final int MEMBER_ATTRIBUTE_VALUE_PROPERTY_COUNT = 20;// 会员注册项值对象属性个数
	public static final String MEMBER_ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX = "memberAttributeValue";// 会员注册项值对象属性名称前缀
	
	public static final String LOCK = "-1";//锁定
	public static final String UNLOCK = "1";//取消锁定
	
	private String id;
	private Date createDate;
	private Date modifyDate;
	
	private String headicon;// 头像
	private String username;// 用户名
	private String password;// 密码
	private String email;// E-mail
	private String safeQuestion;// 密码保护问题
	private String safeAnswer;// 密码保护问题答案
	private String passwordRecoverKey;// 密码找回Key
	private Integer score;// 积分
	private BigDecimal deposit;// 预存款
	private Boolean isAccountEnabled;// 账号是否启用
	private Boolean isAccountLocked;// 账号是否锁定
	private Integer loginFailureCount;// 连续登录失败的次数
	private Date lockedDate;// 账号锁定日期
	private String registerIp;// 注册IP
	private String loginIp;// 最后登录IP
	private Date loginDate;// 最后登录日期
	private String name;// 姓名
	private Integer gender;// 性别
	private Date birth;// 出生日期
	private String areaStore;// 地区存储
	private String address;// 地址
	private String zipCode;// 邮编
	private String phone;// 电话
	private String mobile;// 手机
	
	/** 推送注册ID **/
	private String registrationID;
	
	private String memberAttributeValue0;// 会员注册项值0
	private String memberAttributeValue1;// 会员注册项值1
	private String memberAttributeValue2;// 会员注册项值2
	private String memberAttributeValue3;// 会员注册项值3
	private String memberAttributeValue4;// 会员注册项值4
	private String memberAttributeValue5;// 会员注册项值5
	private String memberAttributeValue6;// 会员注册项值6
	private String memberAttributeValue7;// 会员注册项值7
	private String memberAttributeValue8;// 会员注册项值8
	private String memberAttributeValue9;// 会员注册项值9
	private String memberAttributeValue10;// 会员注册项值10
	private String memberAttributeValue11;// 会员注册项值11
	private String memberAttributeValue12;// 会员注册项值12
	private String memberAttributeValue13;// 会员注册项值13
	private String memberAttributeValue14;// 会员注册项值14
	private String memberAttributeValue15;// 会员注册项值15
	private String memberAttributeValue16;// 会员注册项值16
	private String memberAttributeValue17;// 会员注册项值17
	private String memberAttributeValue18;// 会员注册项值18
	private String memberAttributeValue19;// 会员注册项值19
	
	public String getHeadicon() {
		return headicon;
	}

	public void setHeadicon(String headicon) {
		this.headicon = headicon;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSafeQuestion() {
		return safeQuestion;
	}

	public void setSafeQuestion(String safeQuestion) {
		this.safeQuestion = safeQuestion;
	}

	public String getSafeAnswer() {
		return safeAnswer;
	}

	public void setSafeAnswer(String safeAnswer) {
		this.safeAnswer = safeAnswer;
	}
	
	public String getPasswordRecoverKey() {
		return passwordRecoverKey;
	}

	public void setPasswordRecoverKey(String passwordRecoverKey) {
		this.passwordRecoverKey = passwordRecoverKey;
	}
	
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}
	
	public Boolean getIsAccountEnabled() {
		return isAccountEnabled;
	}

	public void setIsAccountEnabled(Boolean isAccountEnabled) {
		this.isAccountEnabled = isAccountEnabled;
	}

	public Boolean getIsAccountLocked() {
		return isAccountLocked;
	}

	public void setIsAccountLocked(Boolean isAccountLocked) {
		this.isAccountLocked = isAccountLocked;
	}

	public Integer getLoginFailureCount() {
		return loginFailureCount;
	}

	public void setLoginFailureCount(Integer loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}
	
	public Date getLockedDate() {
		return lockedDate;
	}

	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}
	
	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}
	
	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	
	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	 

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getAreaStore() {
		return areaStore;
	}

	public void setAreaStore(String areaStore) {
		this.areaStore = areaStore;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMemberAttributeValue0() {
		return memberAttributeValue0;
	}

	public void setMemberAttributeValue0(String memberAttributeValue0) {
		this.memberAttributeValue0 = memberAttributeValue0;
	}

	public String getMemberAttributeValue1() {
		return memberAttributeValue1;
	}

	public void setMemberAttributeValue1(String memberAttributeValue1) {
		this.memberAttributeValue1 = memberAttributeValue1;
	}

	public String getMemberAttributeValue2() {
		return memberAttributeValue2;
	}

	public void setMemberAttributeValue2(String memberAttributeValue2) {
		this.memberAttributeValue2 = memberAttributeValue2;
	}

	public String getMemberAttributeValue3() {
		return memberAttributeValue3;
	}

	public void setMemberAttributeValue3(String memberAttributeValue3) {
		this.memberAttributeValue3 = memberAttributeValue3;
	}

	public String getMemberAttributeValue4() {
		return memberAttributeValue4;
	}

	public void setMemberAttributeValue4(String memberAttributeValue4) {
		this.memberAttributeValue4 = memberAttributeValue4;
	}

	public String getMemberAttributeValue5() {
		return memberAttributeValue5;
	}

	public void setMemberAttributeValue5(String memberAttributeValue5) {
		this.memberAttributeValue5 = memberAttributeValue5;
	}

	public String getMemberAttributeValue6() {
		return memberAttributeValue6;
	}

	public void setMemberAttributeValue6(String memberAttributeValue6) {
		this.memberAttributeValue6 = memberAttributeValue6;
	}

	public String getMemberAttributeValue7() {
		return memberAttributeValue7;
	}

	public void setMemberAttributeValue7(String memberAttributeValue7) {
		this.memberAttributeValue7 = memberAttributeValue7;
	}

	public String getMemberAttributeValue8() {
		return memberAttributeValue8;
	}

	public void setMemberAttributeValue8(String memberAttributeValue8) {
		this.memberAttributeValue8 = memberAttributeValue8;
	}

	public String getMemberAttributeValue9() {
		return memberAttributeValue9;
	}

	public void setMemberAttributeValue9(String memberAttributeValue9) {
		this.memberAttributeValue9 = memberAttributeValue9;
	}

	public String getMemberAttributeValue10() {
		return memberAttributeValue10;
	}

	public void setMemberAttributeValue10(String memberAttributeValue10) {
		this.memberAttributeValue10 = memberAttributeValue10;
	}

	public String getMemberAttributeValue11() {
		return memberAttributeValue11;
	}

	public void setMemberAttributeValue11(String memberAttributeValue11) {
		this.memberAttributeValue11 = memberAttributeValue11;
	}

	public String getMemberAttributeValue12() {
		return memberAttributeValue12;
	}

	public void setMemberAttributeValue12(String memberAttributeValue12) {
		this.memberAttributeValue12 = memberAttributeValue12;
	}

	public String getMemberAttributeValue13() {
		return memberAttributeValue13;
	}

	public void setMemberAttributeValue13(String memberAttributeValue13) {
		this.memberAttributeValue13 = memberAttributeValue13;
	}

	public String getMemberAttributeValue14() {
		return memberAttributeValue14;
	}

	public void setMemberAttributeValue14(String memberAttributeValue14) {
		this.memberAttributeValue14 = memberAttributeValue14;
	}

	public String getMemberAttributeValue15() {
		return memberAttributeValue15;
	}

	public void setMemberAttributeValue15(String memberAttributeValue15) {
		this.memberAttributeValue15 = memberAttributeValue15;
	}

	public String getMemberAttributeValue16() {
		return memberAttributeValue16;
	}

	public void setMemberAttributeValue16(String memberAttributeValue16) {
		this.memberAttributeValue16 = memberAttributeValue16;
	}

	public String getMemberAttributeValue17() {
		return memberAttributeValue17;
	}

	public void setMemberAttributeValue17(String memberAttributeValue17) {
		this.memberAttributeValue17 = memberAttributeValue17;
	}

	public String getMemberAttributeValue18() {
		return memberAttributeValue18;
	}

	public void setMemberAttributeValue18(String memberAttributeValue18) {
		this.memberAttributeValue18 = memberAttributeValue18;
	}

	public String getMemberAttributeValue19() {
		return memberAttributeValue19;
	}

	public void setMemberAttributeValue19(String memberAttributeValue19) {
		this.memberAttributeValue19 = memberAttributeValue19;
	}

	public String getRegistrationID() {
		return registrationID;
	}

	public void setRegistrationID(String registrationID) {
		this.registrationID = registrationID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}