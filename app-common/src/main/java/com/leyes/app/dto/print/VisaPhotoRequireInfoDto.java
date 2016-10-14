package com.leyes.app.dto.print;

import java.io.Serializable;
import java.math.BigDecimal;


public class VisaPhotoRequireInfoDto implements Serializable{

	private long infoId;
	 
	private int settingType;
	 
	private String name;//名字
	
	 
	private int printWidth;//冲印尺寸
	private int printHeight;//冲印尺寸
	
	private int pixelWidth;//像素大小
	private int pixelHeight;//像素大小
	
	private String backgroundColor;//背景色
	
	private String other;//其他
 
	private BigDecimal price;
	public VisaPhotoRequireInfoDto(long id, int settingType, String name,
			int printWidth, int printHeight, int pixelWidth, int pixelHeight,
			String backgroundColor, String other, BigDecimal price) {
		super();
		this.infoId = id;
		this.settingType = settingType;
		this.name = name;
		this.printWidth = printWidth;
		this.printHeight = printHeight;
		this.pixelWidth = pixelWidth;
		this.pixelHeight = pixelHeight;
		this.backgroundColor = backgroundColor;
		this.other = other;
		this.price=price;
	}

	 
	public long getInfoId() {
		return infoId;
	}


	public void setInfoId(long infoId) {
		this.infoId = infoId;
	}


	public int getSettingType() {
		return settingType;
	}

	public void setSettingType(int settingType) {
		this.settingType = settingType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrintWidth() {
		return printWidth;
	}

	public void setPrintWidth(int printWidth) {
		this.printWidth = printWidth;
	}

	public int getPrintHeight() {
		return printHeight;
	}

	public void setPrintHeight(int printHeight) {
		this.printHeight = printHeight;
	}

	public int getPixelWidth() {
		return pixelWidth;
	}

	public void setPixelWidth(int pixelWidth) {
		this.pixelWidth = pixelWidth;
	}

	public int getPixelHeight() {
		return pixelHeight;
	}

	public void setPixelHeight(int pixelHeight) {
		this.pixelHeight = pixelHeight;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	

}