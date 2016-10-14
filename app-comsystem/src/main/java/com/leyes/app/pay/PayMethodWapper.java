package com.leyes.app.pay;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.leyes.app.dto.clothes.ClothesOrderPriceDto;
import com.leyes.app.dto.comsystem.OrderDto;
import com.leyes.app.dto.comsystem.PayResultDto;
import com.leyes.app.dto.comsystem.RechargeOrderDto;
import com.leyes.app.dto.comsystem.WechatPayDto;
import com.leyes.app.dto.member.CouponDto;
import com.leyes.app.dto.print.PrintOrderDto;
import com.leyes.app.dto.shop.GoodsOrderDto;
import com.leyes.app.enums.CardType;
import com.leyes.app.enums.OrderType;
import com.leyes.app.enums.PayStatus;
import com.leyes.app.enums.PayType;
import com.leyes.app.util.XMLUtil;
import com.leyes.app.util.alipay.AliPayContext;
import com.leyes.app.util.alipay.Rsa;
import com.leyes.app.util.wechat.HttpUtil;
import com.leyes.app.util.wechat.PayCommonUtil;
import com.leyes.app.util.wechat.WechatContext;

public class PayMethodWapper {

	public PayMethodWapper(OrderDto order) {
		this.price=order.getPrice();
		this.orderNumber=order.getOrderNumber();
		this.coupon=order.getCoupon();
		if(order instanceof ClothesOrderPriceDto){
			this.orderType=OrderType.clothesOrder;
		}else if (order instanceof PrintOrderDto){
			this.orderType=OrderType.printOrder;
		}else if(order instanceof GoodsOrderDto){
			this.orderType=OrderType.goodsOrder;
		}else if(order instanceof RechargeOrderDto){
			this.orderType=OrderType.rechargeOrder;
		} 
		
	}
	private BigDecimal price;
	private String orderNumber;
	private CouponDto coupon;
	private OrderType orderType;
	
	public PayResultDto payMethod(BigDecimal balanceNumber, 
			int integralNumber,BigDecimal ratio, int payType) throws Exception {
		PayResultDto payResult=new PayResultDto();
		String result="";
		boolean useCoupon = coupon!=null;
		if (useCoupon) {
			price = couponPay(payResult,price, coupon);
			if (price.compareTo(BigDecimal.ZERO) == 0) {
				// 支付成功，修改订单状态
				payResult.setPayType(PayType.COUPONS);
				payResult.setFinalPrice(price);
				
				payResult.setPayStatus(PayStatus.PAID);
				return payResult;
			}
		}
		boolean useBalance = balanceNumber.compareTo(BigDecimal.ZERO) == 1;
		if (useBalance) {
			price = balancePay(payResult,price, balanceNumber);
			if (price.compareTo(BigDecimal.ZERO) == 0) {
				// 支付成功，修改订单状态
				payResult.setIntegralNum(0);
				payResult.setPayType(PayType.BALANCE);
				payResult.setFinalPrice(price);
				
				payResult.setPayStatus(PayStatus.PAID);
				return payResult;
			}
		}
		boolean useIntegral = integralNumber>0 ;
		if (!useCoupon && useIntegral) {
			price = integralPay(payResult,price, integralNumber,ratio);
			if (price.compareTo(BigDecimal.ZERO) == 0) {
				// 支付成功，修改订单状态
				payResult.setPayType(PayType.INTEGRAL);
				payResult.setFinalPrice(price);
				
				payResult.setPayStatus(PayStatus.PAID);
				return payResult;
			}
		}
		//最终价格大于0
		if (price.compareTo(BigDecimal.ZERO) == 1) {
			payResult.setFinalPrice(price);
			if(payType==PayType.ALIPAY.ordinal()){
				result=alipay(price, orderNumber,orderType);
				payResult.setPayType(PayType.ALIPAY);
				payResult.setResult(result);
			}else if(payType==PayType.WECHAT.ordinal()){
				result=wechatPay(price, orderNumber,orderType);
				payResult.setPayType(PayType.WECHAT);
			}
		}
		payResult.setResult(result);
		return payResult;
	}

	protected BigDecimal balancePay(PayResultDto payResult,BigDecimal price, BigDecimal balance) {
		 if(price.compareTo(balance)!=-1){
			 payResult.setBalance(balance);
			 return price.subtract(balance);
		 }else{
			 payResult.setBalance(price);
			 return BigDecimal.ZERO;
		 }
	}

	protected BigDecimal integralPay(PayResultDto payResult,BigDecimal price, int integeral,BigDecimal ratio) {
		int priceInt = price.divide(ratio).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
		if(priceInt>=integeral){
			payResult.setIntegralNum(integeral);
			return price.subtract(new BigDecimal(integeral).multiply(ratio));
		}else{
			payResult.setIntegralNum(priceInt);
			return BigDecimal.ZERO;
		}
	}
	protected BigDecimal couponPay(PayResultDto payResult,BigDecimal price,CouponDto couponDto){
		int cardTypeInt = couponDto.getCardType();
		CardType cardType = CardType.translate(cardTypeInt);
		 switch (cardType) {
			case discount:
				price=price.multiply(new BigDecimal(couponDto.getDiscountRatioOne()));
				break;
			case rebate:
				price=price.subtract(couponDto.getPrice());
				if(price.compareTo(BigDecimal.ZERO)==-1){
					price=BigDecimal.ZERO;
				}
				break;
			case group:
				if(orderType.equals(orderType.printOrder)){
					price=BigDecimal.ZERO;
				}
				break;
			default:
				//默认拼团卡（因在小哥端使用，不做处理） 
				break;
		}
		 payResult.setCard(couponDto);
		 return price;
	}
	protected String wechatPay(BigDecimal price, String orderNumber,
			OrderType orderType) throws Exception {
		return makeWechatPayParam(orderNumber, price, orderType);
	}

	protected String alipay(BigDecimal price, String orderNumber,
			OrderType orderType) throws Exception {
		return makeAlipayParam(orderNumber, price, orderType);
	}

	protected String makeAlipayParam(String orderNumber, BigDecimal amount,
			OrderType orderType) throws UnsupportedEncodingException {
		String subject = "支付乐E下" + orderType.getValue();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("partner=");
		stringBuilder.append("\"");
		stringBuilder.append(AliPayContext.partner);
		stringBuilder.append("\"");
		stringBuilder.append("&seller_id=");
		stringBuilder.append("\"");
		stringBuilder.append(AliPayContext.seller);
		stringBuilder.append("\"");
		stringBuilder.append("&out_trade_no=");
		stringBuilder.append("\"");
		stringBuilder.append(orderNumber);
		stringBuilder.append("\"");
		stringBuilder.append("&subject=");
		stringBuilder.append("\"");
		stringBuilder.append(subject);
		stringBuilder.append("_");
		stringBuilder.append(orderType.ordinal());
		stringBuilder.append("\"");
		stringBuilder.append("&body=");
		stringBuilder.append("\"");
		stringBuilder.append(subject);
		stringBuilder.append("\"");
		stringBuilder.append("&total_fee=");
		stringBuilder.append("\"");
		stringBuilder.append(amount.setScale(2, BigDecimal.ROUND_HALF_UP));
		stringBuilder.append("\"");
		stringBuilder.append("&notify_url=");
		stringBuilder.append("\"");
		stringBuilder.append(AliPayContext.notifyUrl);
		stringBuilder.append("\"");
		stringBuilder.append("&service=");
		stringBuilder.append("\"");
		stringBuilder.append("mobile.securitypay.pay");
		stringBuilder.append("\"");
		stringBuilder.append("&payment_type=");
		stringBuilder.append("\"");
		stringBuilder.append("1");
		stringBuilder.append("\"");
		stringBuilder.append("&_input_charset=");
		stringBuilder.append("\"");
		stringBuilder.append("utf-8");
		stringBuilder.append("\"");
		stringBuilder.append("&it_b_pay=");
		stringBuilder.append("\"");
		stringBuilder.append("30m");
		stringBuilder.append("\"");
		String sign = Rsa.sign(stringBuilder.toString(),
				AliPayContext.rsaPrivate);
		stringBuilder.append("&sign=");
		stringBuilder.append("\"");
		stringBuilder.append(URLEncoder.encode(sign, "utf-8"));
		stringBuilder.append("\"");
		stringBuilder.append("&sign_type=");
		stringBuilder.append("\"");
		stringBuilder.append("RSA");
		stringBuilder.append("\"");
		return stringBuilder.toString();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected String makeWechatPayParam(String orderNumber, BigDecimal amount,
			OrderType orderType) throws Exception {
		String spbill_create_ip = "171.213.58.231";
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", WechatContext.appid);// 公众账号ID
		String body = "支付乐E下" + orderType.getValue();
		parameters.put("body", body);// 商品描述
		parameters.put("mch_id", WechatContext.mch_id);// proper.getValue("MCH_ID"));//商户号
		parameters.put("nonce_str", PayCommonUtil.CreateNoncestr());// 随机字符串
		parameters.put("notify_url", WechatContext.notify_url);// proper.getValue("NOTIFY_URL"));//通知地址
		parameters.put("out_trade_no", orderNumber);// 商户订单号
		parameters.put("spbill_create_ip", spbill_create_ip);
		int totalFee=amount.multiply(new BigDecimal(100)).intValue();
		parameters.put("total_fee", totalFee);// 总金额
		parameters.put("trade_type", WechatContext.trade_type);// proper.getValue("TRADE_TYPE"));//交易类型
		parameters.put("attach", orderType.ordinal());
		String sign = PayCommonUtil.createSign("UTF-8", parameters,
				WechatContext.api_key);
		parameters.put("sign", sign);// 签名
		String requestXML = PayCommonUtil.getRequestXml(parameters);
		String result = HttpUtil.httpsRequest(
				WechatContext.unified_order_url, "POST", requestXML);
		Map results = XMLUtil.doXMLParse(result);
		WechatPayDto dto = new WechatPayDto();
		if (results.get("return_code").toString().equals("SUCCESS")) {
			dto.setPartnerid(results.get("mch_id").toString());
			dto.setAppid(results.get("appid").toString());
			dto.setNoncestr(results.get("nonce_str").toString());
			if (results.get("result_code").toString().equals("SUCCESS")) {
				dto.setPrepayid(results.get("prepay_id").toString());
			}
			String signs = makeParamsWexin(results, dto);
			dto.setSign(signs);
		}
		return dto.toString();
	}

	protected String makeParamsWexin(Map<Object, Object> results,
			WechatPayDto dto) {
		dto.setPackageValue("Sign=WXPay");
		long times = System.currentTimeMillis() / 1000;
		dto.setTimestamp(times + "");
		SortedMap<Object, Object> parameter = new TreeMap<Object, Object>();
		parameter.put("appid", results.get("appid").toString());
		parameter.put("partnerid", results.get("mch_id").toString());
		parameter.put("prepayid", results.get("prepay_id").toString());
		parameter.put("package", "Sign=WXPay");
		parameter.put("noncestr", results.get("nonce_str").toString());
		parameter.put("timestamp", times + "");
		String signs = PayCommonUtil.createSign("UTF-8", parameter,
				WechatContext.api_key);
		return signs;
	}
}
