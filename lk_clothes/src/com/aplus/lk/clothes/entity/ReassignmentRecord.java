package com.aplus.lk.clothes.entity;

/**
 * 
* @TypeName: ReassignmentRecord 
* @Description: 改派记录
* @author：Jingpeng 
* @date 2015年11月2日 上午9:27:20 
*
 */
public class ReassignmentRecord extends BaseEntity{

	private String orderId;
	
	private String operator;//操作人
	
	private String fromEmployeeId;//初始小工id
	
	private String toEmployeeId;//改派到的小工id

	private Integer changeType;//改派的类型 0取衣 1送衣
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getFromEmployeeId() {
		return fromEmployeeId;
	}

	public void setFromEmployeeId(String fromEmployeeId) {
		this.fromEmployeeId = fromEmployeeId;
	}

	public String getToEmployeeId() {
		return toEmployeeId;
	}

	public void setToEmployeeId(String toEmployeeId) {
		this.toEmployeeId = toEmployeeId;
	}

	public Integer getChangeType() {
		return changeType;
	}

	public void setChangeType(Integer changeType) {
		this.changeType = changeType;
	}
	
	
}
