package com.leyes.app.dto.print;

import com.leyes.app.dto.comsystem.OrderDto;

public class PrintOrderDto extends OrderDto{

	private String photoType;

	public String getPhotoType() {
		return photoType;
	}

	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}
	
	
}
