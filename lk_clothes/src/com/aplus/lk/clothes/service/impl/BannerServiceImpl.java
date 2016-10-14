package com.aplus.lk.clothes.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.Banner;
import com.aplus.lk.clothes.entity.Banner.Status;
import com.aplus.lk.clothes.mapper.BannerMapper;
import com.aplus.lk.clothes.service.BannerService;
import com.aplus.lk.clothes.utils.FileUtil;
import com.aplus.lk.clothes.utils.RequestUtils;

@Service
public class BannerServiceImpl implements BannerService{

	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private BannerMapper bannerMapper;
	
	@Override
	public void save(Banner banner, MultipartFile imageUpload , HttpServletRequest request) {
		try {
			banner.setImageUrl(FileUtil.copyImage(imageUpload, request, "upload/banner"));
		} catch (Exception e) {
			logger.error("横幅图片上传错误", e);
			e.printStackTrace();
		}
		bannerMapper.save(banner);
	}

	@Override
	public void update(Banner banner, MultipartFile imageUpload , HttpServletRequest request) {
		try {
			if(banner.getStatus() == null ){
				banner.setStatus(Status.unshelve.ordinal());
			}
			banner.setImageUrl(FileUtil.copyImage(imageUpload, request, "upload/banner"));
			if(!imageUpload.isEmpty()){
				FileUtil.deleteFile(RequestUtils.getRealPath(bannerMapper.queryImageUrlById(banner.getId()),request));
			}
		} catch (Exception e) {
			logger.error("横幅图片上传错误", e);
			e.printStackTrace();
		}
		bannerMapper.update(banner);
	}

	@Override
	public Banner queryById(long id) {
		return bannerMapper.queryById(id);
	}

	@Override
	public Pager queryPager(Pager pager, String name, Integer status, Integer type) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("name", name);
		parameterMap.put("status", status);
		parameterMap.put("type", type);
		parameterMap.put("firstResult", pager.getFirstResult());
		parameterMap.put("maxResult", pager.getPageSize());
		pager.setDataList(bannerMapper.queryByLimit(parameterMap));
		pager.setTotalRecords(bannerMapper.queryCount(parameterMap));
		return pager;
	}

	@Override
	public void delete(long[] ids, HttpServletRequest request) {
		String imageUrl = null;
		for(long id : ids){
			imageUrl = RequestUtils.getRealPath(bannerMapper.queryImageUrlById(id),request);
			bannerMapper.deleteById(id);
			FileUtil.deleteFile(imageUrl);
		}
	}
	
}
