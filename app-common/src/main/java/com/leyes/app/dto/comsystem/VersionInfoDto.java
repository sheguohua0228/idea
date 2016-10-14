package com.leyes.app.dto.comsystem;

import java.io.Serializable;

public class VersionInfoDto implements Serializable{
     
	String updateUrl;
	 
	public VersionInfoDto() {
		super();
	}
	public VersionInfoDto(String updateUrl) {
		super();
		this.updateUrl = updateUrl;
	}
	public String getUpdateUrl() {
		return updateUrl;
	}
	public void setUpdateUrl(String updateUrl) {
		this.updateUrl = updateUrl;
	}
}
