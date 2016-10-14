<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	    <base href="<%=basePath%>">
    
	    <title>Page-main</title>
	    
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<link href="<%=basePath%>/resource/admin/css/base.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/resource/admin/css/admin.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/admin/js/admin.js"></script>
		<style media="print">
			.menu{display: none;}
		</style> 
	</head>
	<body class="menu">
	<div class="body">
			<sec:authorize ifAnyGranted="ROLE_CLOTHES_ORDER_APPOINTMENT,ROLE_CLOTHES_ORDER_COLLECT,
				ROLE_CLOTHES_ORDER_FACTORY,ROLE_CLOTHES_ORDER_WASH,ROLE_CLOTHES_ORDER_SEND, ROLE_CLOTHES_ORDER_COMPLETED">
			<dl>
					<dt>
						<span>洗衣订单管理&nbsp;</span>
					</dt>
					<sec:authorize ifAnyGranted="ROLE_CLOTHES_ORDER_APPOINTMENT">
					<dd>
						<a href="clothesOrder/appointment" target="mainFrame">预约订单</a>
					</dd>
					</sec:authorize>
					<sec:authorize ifAnyGranted="ROLE_CLOTHES_ORDER_COLLECT">
						<dd>
							<a href="clothesOrder/collect" target="mainFrame">已收衣订单</a>
						</dd>
					</sec:authorize>
					<sec:authorize ifAnyGranted="ROLE_CLOTHES_ORDER_FACTORY">
						<dd>
							<a href="clothesOrder/factory" target="mainFrame">打印质检订单</a>
						</dd>
					</sec:authorize>
					
					<sec:authorize ifAnyGranted="ROLE_CLOTHES_ORDER_WASH">
						<dd>
							<a href="clothesOrder/wash" target="mainFrame">已洗涤订单</a>
						</dd>
					</sec:authorize>
					<sec:authorize ifAnyGranted="ROLE_CLOTHES_WAIT_FACTORY">
						<dd>
							<a href="clothesOrder/waitfactorylist" target="mainFrame">应出厂订单</a>
						</dd>
					</sec:authorize>
					<sec:authorize ifAnyGranted="ROLE_CLOTHES_OUT_FACTORY">
						<dd>
							<a href="clothesOrder/outfactory" target="mainFrame">衣物出厂</a>
						</dd>
					</sec:authorize>
					<sec:authorize ifAnyGranted="ROLE_CLOTHES_ORDER_SEND">
						<dd>
							<a href="clothesOrder/sending" target="mainFrame">派送中订单</a>
						</dd>
					</sec:authorize>
					<sec:authorize ifAnyGranted="ROLE_CLOTHES_ORDER_COMPLETED">
						<dd>
							<a href="clothesOrder/completed" target="mainFrame">已完成订单</a>
						</dd>
					</sec:authorize>
					<sec:authorize ifAnyGranted="ROLE_CLOTHES_ORDER_BACKWASHING">
						<dd>
							<a href="clothesOrder/backWashing" target="mainFrame">返洗订单</a>
						</dd>
					</sec:authorize>
					<sec:authorize ifAnyGranted="ROLE_CLOTHES_CANCLE_ORDER">
						<dd>
							<a href="clothesOrder/cancel" target="mainFrame">已取消订单</a>
						</dd>
					</sec:authorize>
			</dl>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_CLOTHES_PRICE">
			<dl>
				<dt>
					<span>衣服价格管理&nbsp;</span>
				</dt>
				<dd>
					<a href="clothesPrice/query" target="mainFrame">价格列表</a>
				</dd>
			</dl>
			</sec:authorize>
			
			<sec:authorize ifAnyGranted="ROLE_CLOTHES_BRAND">
			<dl>
				<dt>
					<span>工厂打印管理&nbsp;</span>
				</dt>
				<dd>
					<a href="clothesPrint/view?type=1" target="mainFrame">一级品牌库管理</a>
				</dd>
				<dd>
				<a href="clothesPrint/view?type=2" target="mainFrame">二级品牌库管理</a>
				</dd>
			</dl>
			</sec:authorize>
			<!-- 
			<dl>
				<dt>
					<span>订单统计&nbsp;</span>
				</dt>
				<dd>
					<a target="mainFrame">已完成订单</a>
				</dd>
			</dl>
			 -->
	</div>
</body>
</html>
