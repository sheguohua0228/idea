package com.aplus.lk.clothes.bean;

import java.util.Date;

public class QRCodeBean {

	/** 创建时间 */
	private String createTime;
	/** 修改时间 */
	private String updateTime;
	
	private String id;
	/** 订单 */
	private String printOrderId;
	/** 编码 */
	private String code;
	/** 有效分钟数 */
	private String expiredTime;
	/** 是否使用 */
	private Boolean isUse;
	/** 相框 */
	private String photoFrameId;
	
	private String userTime;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(String expiredTime) {
		this.expiredTime = expiredTime;
	}

	public Boolean getIsUse() {
		return isUse;
	}

	public void setIsUse(Boolean isUse) {
		this.isUse = isUse;
	}

	public String getPhotoFrameId() {
		return photoFrameId;
	}

	public void setPhotoFrameId(String photoFrameId) {
		this.photoFrameId = photoFrameId;
	}

	public String getPrintOrderId() {
		return printOrderId;
	}

	public void setPrintOrderId(String printOrderId) {
		this.printOrderId = printOrderId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserTime() {
		return userTime;
	}

	public void setUserTime(String userTime) {
		this.userTime = userTime;
	}
	
}
