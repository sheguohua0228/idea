package com.leyes.app.web.response.comsystem;


public class QueryTemplateResponse{

	String name	;
	String detail;
	String backgroundImage;	
	int model;	
	String url;
	
	public QueryTemplateResponse(String name, String detail,
			String backgroundImage, int model, String url) {
		super();
		this.name = name;
		this.detail = detail;
		this.backgroundImage = backgroundImage;
		this.model = model;
		this.url = url;
	}
	public QueryTemplateResponse() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getBackgroundImage() {
		return backgroundImage;
	}
	public void setBackgroundImage(String backgroundImage) {
		this.backgroundImage = backgroundImage;
	}
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
