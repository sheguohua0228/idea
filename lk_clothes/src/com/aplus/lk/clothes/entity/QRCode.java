package com.aplus.lk.clothes.entity;

import java.util.Date;

/**
 * 
 * ClassName: QRCode 
 * @Description: TODO 实体类 - 条码
 * @author w.gang
 * @date 2015-9-23
 */
public class QRCode{

	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date updateTime;
	
	private String id;
	/** 订单 */
	private String printOrderId;
	/** 编码 */
	private String code;
	/** 有效分钟数 */
	private Date expiredTime;
	/** 是否使用 */
	private Boolean isUse;
	/** 相框 */
	private String photoFrameId;
	
	private Date userTime;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
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

	public Date getCreateTime() {
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUserTime() {
		return userTime;
	}

	public void setUserTime(Date userTime) {
		this.userTime = userTime;
	}
}
