package com.leyes.app.web.response.comsystem;


public class QueryTopicsReponse{

	private String name;
	
	private String url;
	
	private String imageUrl;

	public QueryTopicsReponse() {
		super();
	}

	public QueryTopicsReponse(String name, String url, String imageUrl) {
		super();
		this.name = name;
		this.url = url;
		this.imageUrl = imageUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}
