package com.leyes.app.web.response.comsystem;


public class CheckUpdateResponse {

	 
	String updateUrl;
	int newVersion; //0 无 1 有
	
	public CheckUpdateResponse() {}

	public String getUpdateUrl() {
		return updateUrl;
	}

	public void setUpdateUrl(String updateUrl) {
		this.updateUrl = updateUrl;
	}

	public int getNewVersion() {
		return newVersion;
	}

	public void setNewVersion(int newVersion) {
		this.newVersion = newVersion;
	}
	 
}
