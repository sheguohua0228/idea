package com.leyes.app.dto.statistics;

import java.io.Serializable;
import java.util.List;

public class TransformationRateDto implements Serializable{
    List<Integer> userNumber;
    List<Integer> newUserNumber;
    List<String> time;
	public List<Integer> getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(List<Integer> userNumber) {
		this.userNumber = userNumber;
	}
	public List<Integer> getNewUserNumber() {
		return newUserNumber;
	}
	public void setNewUserNumber(List<Integer> newUserNumber) {
		this.newUserNumber = newUserNumber;
	}
	public List<String> getTime() {
		return time;
	}
	public void setTime(List<String> time) {
		this.time = time;
	}
}
