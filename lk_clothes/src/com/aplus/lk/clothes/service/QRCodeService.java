package com.aplus.lk.clothes.service;

import java.util.List;
import java.util.Map;

import com.aplus.lk.clothes.entity.QRCode;

public interface QRCodeService {

	List<QRCode> queryAll(Map<String, Object> params);

	int queryCount(Map<String, Object> params);

}
