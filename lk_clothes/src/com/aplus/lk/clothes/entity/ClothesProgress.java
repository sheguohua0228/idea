package com.aplus.lk.clothes.entity;

/**
 * 
 * @ClassName: ClothesProgress
 * @Description: TODO 物流信息（进度）实体类
 * @author w.gang wgang1130@163.com
 * @date 2015-7-17 下午4:46:51
 * 
 */
public class ClothesProgress extends BaseEntity {

	private long clothesOrderId;

	private String content;

	public long getClothesOrderId() {
		return clothesOrderId;
	}

	public void setClothesOrderId(long clothesOrderId) {
		this.clothesOrderId = clothesOrderId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
