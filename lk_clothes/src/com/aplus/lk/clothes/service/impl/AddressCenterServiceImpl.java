package com.aplus.lk.clothes.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.AddressCenterPO;
import com.aplus.lk.clothes.mapper.AddressCenterMapper;
import com.aplus.lk.clothes.mapper.CommunityMapper;
import com.aplus.lk.clothes.service.AddressCenterService;
import com.aplus.lk.clothes.utils.FileUtil;

@Service
public class AddressCenterServiceImpl implements AddressCenterService{
	
	@Autowired
	private AddressCenterMapper addressCenterMapper;
	@Autowired
	private CommunityMapper communityMapper;

	@Override
	public void save(AddressCenterPO addressCenterPO,MultipartFile imageUpload,HttpServletRequest request) {
		try {
			addressCenterPO.setIcon(FileUtil.copyImage(imageUpload, request , "upload/address"));
			if(addressCenterPO.getFlag() == null){
				addressCenterPO.setFlag(false);
			}
			addressCenterMapper.save(addressCenterPO);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(AddressCenterPO addressCenterPO,MultipartFile imageUpload,HttpServletRequest request) {
		try {
			addressCenterPO.setIcon(FileUtil.copyImage(imageUpload, request, "upload/address"));
			if(addressCenterPO.getFlag() == null){
				addressCenterPO.setFlag(false);
			}
			addressCenterMapper.update(addressCenterPO);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(long[] ids) {
		communityMapper.deleteByAddressCenterIds(ids);// 删除社区告示
		addressCenterMapper.delete(ids);
	}

	@Override
	public AddressCenterPO queryById(long id) {
		return addressCenterMapper.queryById(id);
	}

	@Override
	public AddressCenterPO queryByName(String name) {
		return addressCenterMapper.queryByName(name);
	}

	@Override
	public Pager queryPager(Pager pager, String name) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("firstResult", (pager.getPageNo() - 1) * pager.getPageSize());
		parameterMap.put("maxResult", pager.getPageSize());
		parameterMap.put("name", name);
		pager.setDataList(addressCenterMapper.queryByLimit(parameterMap));
		pager.setTotalRecords(addressCenterMapper.queryCount(parameterMap));
		return pager;
	}

	@Override
	public List<AddressCenterPO> queryAllOfIdAndName() {
		return addressCenterMapper.queryAllOfIdAndName();
	}

	@Override
	public String queryNameById(String id) {
		return addressCenterMapper.queryNameById(id);
	}

	@Override
	public List<AddressCenterPO> queryAll() {
		return addressCenterMapper.queryAll();
	}

	@Override
	public List<AddressCenterPO> queryByCenterType(int centerType) {
		return addressCenterMapper.queryByCenterType(centerType);
	}

}
