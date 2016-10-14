package com.aplus.lk.clothes.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.bean.QRCodeBean;
import com.aplus.lk.clothes.entity.QRCode;
import com.aplus.lk.clothes.entity.TicketNumber;
import com.aplus.lk.clothes.service.QRCodeService;
import com.aplus.lk.clothes.service.TicketNumberService;

@Controller
@RequestMapping("checkQrCode")
public class QRcodeController {
	
	@Autowired
	private QRCodeService qRCodeService;
	
	@Autowired
	private TicketNumberService ticketNumberService;

	private SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	
	@RequestMapping("input")
	public String input(){
		return "qrcode_input";
	}
	@RequestMapping("save")
	public String save(TicketNumber ticket){
		ticketNumberService.save(ticket);
		return "qrcode_input";
	}
	
	
	@RequestMapping("query")
	public String query(Pager pager, String name ,HttpServletRequest request){
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("firstResult", (pager.getPageNo() - 1) * pager.getPageSize());
		params.put("maxResult", pager.getPageSize());
		params.put("name", name);
		
		List<QRCode> qrCodes = qRCodeService.queryAll(params);
 		List<QRCodeBean> qrCodeBeans = new ArrayList<QRCodeBean>();
		QRCodeBean qrCodeBean = null;
		
		for(QRCode qrCode : qrCodes){
			qrCodeBean = new QRCodeBean();
			qrCodeBean.setCreateTime(time.format(qrCode.getCreateTime()));
			qrCodeBean.setExpiredTime(time.format(qrCode.getExpiredTime()));
			qrCodeBean.setUpdateTime(time.format(qrCode.getUpdateTime()));
			if(qrCode.getUserTime() != null){
				qrCodeBean.setUserTime(time.format(qrCode.getUserTime()));
			}
			qrCodeBean.setIsUse(qrCode.getIsUse());
			qrCodeBean.setCode(qrCode.getCode());
			qrCodeBeans.add(qrCodeBean);
		}
		pager.setDataList(qrCodeBeans);
		pager.setTotalRecords(qRCodeService.queryCount(params));
		request.setAttribute("pager", pager);
		request.setAttribute("total", ticketNumberService.findTotalByType(1));
		request.setAttribute("purchaseRecords", ticketNumberService.queryRecods());
		return "qrcode_list";
	}
}
