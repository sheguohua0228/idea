package com.leyes.app.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leyes.app.dto.shop.BusinessDto;
import com.leyes.app.dto.shop.CategoryDto;
import com.leyes.app.dto.shop.GoodsDto;
import com.leyes.app.service.ComsystemService;
import com.leyes.app.service.MallService;
import com.leyes.app.util.SpringContextUtils;
import com.leyes.app.web.request.business.QueryBusinessInfoRequest;
import com.leyes.app.web.request.business.QueryBusinessRequest;
import com.leyes.app.web.response.business.QueryBusinessInfoResponse;
import com.leyes.app.web.utils.ReturnResult;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

//@Controller
@RequestMapping("business")
@Api(value = "business-api", description = "周边商家模块接口API", position = 1)
public class BusinessController extends BaseController{

	private ComsystemService comsystemService=SpringContextUtils.getBean("comsystemService");
	
	private MallService mallService=SpringContextUtils.getBean("mallService");
	/**
	 * @throws Exception 
	 * 查询周边商家模块里的分类
	 * 
	 * @Title: queryCategory
	 * @Description: TODO
	 * @param request
	 * @return
	 * @return QueryPrintSizePriceResponse
	 * @throws
	 */
	@ApiOperation(value = "查询周边商家模块的分类", notes = "查询周边商家模块的分类",response=ReturnResult.class, position = 1)
	@RequestMapping(value = "queryCategory", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult queryCategory() throws Exception {
		 List<CategoryDto> result = comsystemService.queryCategoryByType(0);
		return ReturnResult.SUCCESS(result);
	}

	/**
	 * @throws Exception 
	 * 查看商家列表
	 * 
	 * @Title: queryBusiness
	 * @Description: TODO
	 * @param request
	 * @return
	 * @return QueryCategoryResponse
	 * @throws
	 */
	@ApiOperation(value = "查询商家列表", notes = "查询商家列表",response=ReturnResult.class, position = 2)
	@RequestMapping(value = "queryBusiness", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult queryBusiness(@RequestBody QueryBusinessRequest request) throws Exception {
		String communityId=sessionContextUtils.getCommunityId();
		communityId=comsystemService.queryParentCommunityId(communityId);
		List<BusinessDto> result = mallService.queryNearyByBusiness(request.getCategoryId(),communityId, request.getLatitude(), request.getLongitude(), request.getPage());
		return ReturnResult.SUCCESS(result);
	}

	/**
	 * 获取商家信息
	 * 
	 * @Title: queryBusinessInfo
	 * @Description: TODO
	 * @param request
	 * @return
	 * @return QueryBusinessInfoResponse
	 * @throws
	 */
	@ApiOperation(value = "获取商家信息", notes = "获取商家信息",response=ReturnResult.class, position = 3)
	@RequestMapping(value = "queryBusinessInfo", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult queryBusinessInfo(@RequestBody QueryBusinessInfoRequest request) {
		QueryBusinessInfoResponse response = new QueryBusinessInfoResponse();
		String businessId = request.getBusinessId();
		//查询商家基本信息
		BusinessDto business = mallService.queryBusinessInfo(request.getBusinessId());
		response.setBusinessInfo(business);
		//查看商家优惠券
		
		//查看商家的商品
		List<GoodsDto> goodsList = mallService.queryGoodsByBusinessId(businessId, 1);
		response.setGoodsList(goodsList);
		return ReturnResult.SUCCESS(business);
	}
	
}
