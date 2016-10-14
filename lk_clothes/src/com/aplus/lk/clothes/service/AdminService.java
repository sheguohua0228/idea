package com.aplus.lk.clothes.service;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.Admin;

public interface AdminService {
	
	public void save(Admin admin);

	public void update(Admin admin);

	public void delete(String[] ids);

	public Pager queryPager(Pager pager, String username, String name);

	public Admin queryAdminById(String id);

	public Admin queryAdminByUsername(String username);
	
	public Admin queryAdminWithActionIdById(String id);
	
	public Admin getLoginAdmin();
	
	public void updatePasswordAndEmail(Admin admin);
	
	public void updateLoginIpAndDate(Admin admin);
	
}
