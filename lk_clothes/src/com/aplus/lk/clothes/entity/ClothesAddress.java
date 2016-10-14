package com.aplus.lk.clothes.entity;

public class ClothesAddress extends BaseEntity {

	/** 用户ID */
	private String userId;
	/** 收货地址 */
	private String deliveryAddress;
	/** 送货地址 */
	private String sendAddress;
	/** 经度 */
	private String longitude;
	/** 纬度 */
	private String latitude;
	/** 联系电话 */
	private String phoneNumber;
	/** 用户名 */
	private String username;
	/** 是否在服务范围 */
	private Boolean isAccord;
	/** 社区地址ID **/
	private long addressCenterId;
	
	public String getUserId() {
		return userId;
	}

	public Boolean getIsAccord() {
		return isAccord;
	}

	public void setIsAccord(Boolean isAccord) {
		this.isAccord = isAccord;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public String getSendAddress() {
		return sendAddress;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public long getAddressCenterId() {
		return addressCenterId;
	}

	public void setAddressCenterId(long addressCenterId) {
		this.addressCenterId = addressCenterId;
	}

}
