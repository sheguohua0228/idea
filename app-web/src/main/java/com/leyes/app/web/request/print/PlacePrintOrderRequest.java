package com.leyes.app.web.request.print;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
@ApiModel
public class PlacePrintOrderRequest {
	
	@ApiModelProperty(value="上传照片列表信息,isNative 默认为-1，本地户口 1，非本地户口0,photoType//1 普通照片 2护照 3签证照")
	//private List<PlaceOrderDto>  list;

	private String photoInfos;

	public String getPhotoInfos() {
		return photoInfos;
	}

	public void setPhotoInfos(String photoInfos) {
		this.photoInfos = photoInfos;
	}
	
	 
	
	 
}
