package com.aplus.lk.clothes.bean;

import java.util.List;

public class Pager {
	// 结果集
	private List<?> dataList;

	// 记录数
	private int totalRecords;

	// 每页多少条数据
	private int pageSize = 10;

	// 第几页
	private int pageNo = 1;

	/**
	 * 返回总页数
	 * 
	 * @return
	 */
	public int getTotalPages() {
		return (totalRecords + pageSize - 1) / pageSize;
	}

	/**
	 * 首页
	 * 
	 * @return
	 */
	public int getTopPageNo() {
		return 1;
	}

	/**
	 * 上一页
	 * 
	 * @return
	 */
	public int getPreviousPageNo() {
		if (this.pageNo <= 1) {
			return 1;
		}
		return this.pageNo - 1;
	}

	/**
	 * 下一页
	 * 
	 * @return
	 */
	public int getNextPageNo() {
		if (this.pageNo >= getButtomPageNo()) {
			return getButtomPageNo();
		}
		return this.pageNo + 1;
	}

	/**
	 * 尾页
	 * 
	 * @return
	 */
	public int getButtomPageNo() {
		return getTotalPages();
	}
	
	/**
	 * 
	 * @Title: getCurrentPageRecords
	 * @Description: TODO 获取当前页的记录数
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int getCurrentPageRecords(){
		return dataList == null ? 0 : dataList.size();
	}
	
	public int getFirstResult(){
		return (pageNo - 1) * pageSize;
	}

	public List<?> getDataList() {
		return dataList;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
}
