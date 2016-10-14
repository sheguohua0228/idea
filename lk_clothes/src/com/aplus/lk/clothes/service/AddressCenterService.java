package com.aplus.lk.clothes.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.AddressCenterPO;

public interface AddressCenterService {

	public void save(AddressCenterPO addressCenterPO,MultipartFile imageUpload,HttpServletRequest request);

	public void update(AddressCenterPO addressCenterPO,MultipartFile imageUpload,HttpServletRequest request);

	public void delete(long[] ids);

	public AddressCenterPO queryById(long id);

	public AddressCenterPO queryByName(String name);

	public Pager queryPager(Pager pager, String name);
	
	public List<AddressCenterPO> queryAllOfIdAndName();
	
	public String queryNameById(String id);
	
	public List<AddressCenterPO> queryAll();
	
	public List<AddressCenterPO> queryByCenterType(int centerType);
	
}
