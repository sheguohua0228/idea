package com.leyes.app.dto.comsystem;

import java.io.Serializable;

public class FileDto implements Serializable{

	private String path;
	
	private int type;

	public FileDto() {
		super();
	}

	public FileDto(String path, int type) {
		super();
		this.path = path;
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
