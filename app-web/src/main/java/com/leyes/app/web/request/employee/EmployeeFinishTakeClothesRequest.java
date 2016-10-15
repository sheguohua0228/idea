package com.leyes.app.web.request.employee;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class EmployeeFinishTakeClothesRequest {

	@ApiModelProperty(value="订单id")
	private String orderId;
	@ApiModelProperty(value="条码")
	private String barCode;
	@ApiModelProperty(value="衣服数量")
	private int clothesCount;
	@ApiModelProperty(value="物业人员的备注")
	private String remark;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public int getClothesCount() {
		return clothesCount;
	}

	public void setClothesCount(int clothesCount) {
		this.clothesCount = clothesCount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
