package com.leyes.app.dto.employee;

import java.io.Serializable;

public class ClothesOrderTimeDto implements Serializable{

	private long takeTime;
	
	private long backTime;

	
	public ClothesOrderTimeDto(long takeTime, long backTime) {
		super();
		this.takeTime = takeTime;
		this.backTime = backTime;
	}

	public ClothesOrderTimeDto() {
		super();
	}

	public long getTakeTime() {
		return takeTime;
	}

	public void setTakeTime(long takeTime) {
		this.takeTime = takeTime;
	}

	public long getBackTime() {
		return backTime;
	}

	public void setBackTime(long backTime) {
		this.backTime = backTime;
	}
	
	
}
