package com.aplus.lk.clothes.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.Employee;
import com.aplus.lk.clothes.mapper.EmployeeMapper;
import com.aplus.lk.clothes.service.EmployeeService;
import com.aplus.lk.clothes.utils.FileUtil;
import com.aplus.lk.clothes.utils.StringUtils;
import com.aplus.lk.clothes.utils.UUIDUtils;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private EmployeeMapper employeeMapper;

	@Override
	public Employee queryEmployeeByUsernamePassword(String username,
			String password) {
		return employeeMapper.queryEmployeeByUsernamePassword(username, password);
	}

	@Override
	public void save(Employee employee, MultipartFile imageUpload, HttpServletRequest request) {
		employee.setId(UUIDUtils.getUUID());
		employee.setRecommendedCode(RandomStringUtils.randomNumeric(5));//推荐码生成5位数字
		employee.setPassword(DigestUtils.md5Hex(employee.getPassword()));
		try {
			employee.setHeadImageUrl(FileUtil.copyImage(imageUpload, request, "upload/heads"));
		} catch (Exception e) {
			logger.error("头像上传异常", e);
		}
		employeeMapper.save(employee);
	}

	@Override
	public void update(Employee employee, MultipartFile imageUpload, HttpServletRequest request) {
		if(StringUtils.isNotEmpty(employee.getPassword())){
			employee.setPassword(DigestUtils.md5Hex(employee.getPassword()));
		}
		try {
			employee.setHeadImageUrl(FileUtil.copyImage(imageUpload, request, "upload/heads"));
		} catch (Exception e) {
			logger.error("头像上传异常", e);
		}
		employeeMapper.update(employee);
	}

	@Override
	public Employee queryById(String id) {
		return employeeMapper.queryById(id);
	}

	@Override
	public Employee queryByUsername(String username) {
		return employeeMapper.queryByUsername(username);
	}

	@Override
	public Pager queryPager(Pager pager, String name) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("firstResult", (pager.getPageNo() - 1) * pager.getPageSize());
		params.put("maxResult", pager.getPageSize());
		params.put("name", name);
		pager.setDataList(employeeMapper.queryByLimit(params));
		pager.setTotalRecords(employeeMapper.queryCount(params));
		return pager;
	}

	@Override
	public void delete(String[] ids) {
		employeeMapper.delete(ids);
	}

	@Override
	public List<Employee> queryByAddressCenterId(long addressCenterId) {
		return employeeMapper.queryByAddressCenterId(addressCenterId);
	}

	@Override
	public String queryRegistIdByOrderId(long orderId) {
		return employeeMapper.queryRegistIdByOrderId(orderId);
	}

}
