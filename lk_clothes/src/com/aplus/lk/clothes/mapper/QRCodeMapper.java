package com.aplus.lk.clothes.mapper;

import java.util.List;
import java.util.Map;

import com.aplus.lk.clothes.entity.QRCode;

public interface QRCodeMapper {

	public List<QRCode> queryAll(Map<String, Object> params);

	public int queryCount(Map<String, Object> params);

}
