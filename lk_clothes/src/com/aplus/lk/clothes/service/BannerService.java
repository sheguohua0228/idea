package com.aplus.lk.clothes.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.Banner;

public interface BannerService {

	public void save(Banner banner, MultipartFile imageUpload , HttpServletRequest request);

	public void update(Banner banner, MultipartFile imageUpload , HttpServletRequest request);

	public Banner queryById(long id);

	public Pager queryPager(Pager pager, String name , Integer status, Integer type);

	public void delete(long[] ids, HttpServletRequest request);

}
