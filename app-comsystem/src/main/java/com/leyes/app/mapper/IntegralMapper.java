package com.leyes.app.mapper;

import org.apache.ibatis.annotations.Param;

import com.leyes.app.entity.IntegralRule;

public interface IntegralMapper {

	public IntegralRule queryRule(@Param("tradeType") int tradeType,@Param("sourceType") int sourceType);
}
