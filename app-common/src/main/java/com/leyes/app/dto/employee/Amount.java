package com.leyes.app.dto.employee;

import java.io.Serializable;

public class Amount implements Serializable{
	private int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Amount(int number) {
		super();
		this.number = number;
	}

	public Amount() {
		super();
	}
	
}
