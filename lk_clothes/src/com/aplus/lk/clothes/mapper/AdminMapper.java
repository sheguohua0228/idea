package com.aplus.lk.clothes.mapper;

import java.util.List;
import java.util.Map;

import com.aplus.lk.clothes.entity.Admin;

public interface AdminMapper {
	
	public void save(Admin admin);
	
	public void update(Admin admin);
	
	public void delete(String[] ids);
	
	public List<Admin> queryByLimit(Map<String, Object> parameterMap);
	
	public Admin queryAdminById(String id);
	
	public Admin queryAdminByUsername(String username);
	
	public int queryCount(Map<String, Object> parameterMap);
	
	public List<String> queryGroupIdListByAdminId(String id);
	
	public void saveAdminGroup(Map<String, String> parameterMap);
	
	public void deleteAdminGroup(Map<String, String> parameterMap);
	
	public void updatePasswordAndEmail(Admin admin);
	
	public void updateLoginIpAndDate(Admin admin);
	
	public void deleteAdminGroupByAdminIds(String[] adminId);
	
}
