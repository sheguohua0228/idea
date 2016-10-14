package com.leyes.app.dto.statistics;

import java.io.Serializable;
import java.util.List;

public class NewUserOrderDto implements Serializable {
	public List<Integer> orderNumber;
	public List<String> time;

	public List<Integer> getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(List<Integer> orderNumber) {
		this.orderNumber = orderNumber;
	}

	public List<String> getTime() {
		return time;
	}

	public void setTime(List<String> time) {
		this.time = time;
	}

}
