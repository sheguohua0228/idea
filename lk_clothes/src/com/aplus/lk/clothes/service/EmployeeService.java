package com.aplus.lk.clothes.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.Employee;

public interface EmployeeService {
	
	public Employee queryEmployeeByUsernamePassword(String username , String password);
	
	public void save(Employee employee, MultipartFile imageUpload, HttpServletRequest request);
	
	public void update(Employee employee, MultipartFile imageUpload, HttpServletRequest request);
	
	public Employee queryById(String id);
	
	public Employee queryByUsername(String username);
	
	public Pager queryPager(Pager pager, String name);
	
	public void delete(String[] ids);
	
	public List<Employee> queryByAddressCenterId(long addressCenterId);
	
	public String queryRegistIdByOrderId(long orderId);
}
