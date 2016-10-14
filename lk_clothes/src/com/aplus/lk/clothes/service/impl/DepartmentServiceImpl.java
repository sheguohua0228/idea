package com.aplus.lk.clothes.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplus.lk.clothes.bean.AjaxResult;
import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.bean.AjaxResult.Status;
import com.aplus.lk.clothes.entity.Department;
import com.aplus.lk.clothes.mapper.DepartmentMapper;
import com.aplus.lk.clothes.mapper.EmployeeMapper;
import com.aplus.lk.clothes.service.DepartmentService;
import com.aplus.lk.clothes.utils.UUIDUtils;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Override
	public void save(Department department) {
		department.setId(UUIDUtils.getUUID());
		departmentMapper.save(department);
	}

	@Override
	public void update(Department department) {
		departmentMapper.update(department);
	}

	@Override
	public Department queryById(String id) {
		return departmentMapper.queryById(id);
	}

	@Override
	public AjaxResult delete(String[] ids) {
		AjaxResult result = new AjaxResult(Status.success, "删除成功");
		for(String id: ids){
			if(employeeMapper.queryCountByDepartmentId(id) > 0){
				result.setStatus(Status.error);
				result.setMessage("部门["+departmentMapper.queryById(id).getName()+"]下存在员工，删除失败");
				return result;
			}
		}
		departmentMapper.delete(ids);
		return result;
	}

	@Override
	public Pager queryPager(Pager pager, String name, String manager,
			Boolean isEnabled) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("firstResult", pager.getFirstResult());
		parameterMap.put("maxResult", pager.getPageSize());
		parameterMap.put("name", name);
		parameterMap.put("manager", manager);
		parameterMap.put("isEnabled", isEnabled);
		pager.setDataList(departmentMapper.queryByLimit(parameterMap));
		pager.setTotalRecords(departmentMapper.queryCount(parameterMap));
		return pager;
	}

	@Override
	public Department queryByName(String name) {
		return departmentMapper.queryByName(name);
	}

	@Override
	public List<Department> queryAll() {
		return departmentMapper.queryAll();
	}

}
