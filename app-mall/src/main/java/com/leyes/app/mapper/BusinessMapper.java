package com.leyes.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leyes.app.entity.Business;

public interface BusinessMapper {

	public List<Business> queryNearyByBusiness(
			@Param("categoryId") String categoryId,
			@Param("communityId") String communityId,
			@Param("latitude") String latitude,
			@Param("longitude") String longitude,
			@Param("fristResult") int fristResult);

	public Business queryBusinessInfo(@Param("businessId") String businessId);
}
