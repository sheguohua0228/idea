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
	</head>
	<body class="menu">
	<div class="body">
			<sec:authorize ifAnyGranted="ROLE_CLOTHES_ORDER_APPOINTMENT,ROLE_CLOTHES_ORDER_COLLECT,
				ROLE_CLOTHES_ORDER_FACTORY,ROLE_CLOTHES_ORDER_WASH,ROLE_CLOTHES_ORDER_SEND, ROLE_CLOTHES_ORDER_COMPLETED">
			<dl>
					<dt>
						<span>订单管理&nbsp;</span>
					</dt>
					<sec:authorize ifAnyGranted="ROLE_CLOTHES_ORDER_APPOINTMENT"></sec:authorize>
					<dd>
						<a href="managerOrder/appointment" target="mainFrame">预约订单</a>
					</dd>
					<sec:authorize ifAnyGranted="ROLE_CLOTHES_ORDER_COLLECT">
						<dd>
							<a href="managerOrder/processing" target="mainFrame">进行中订单</a>
						</dd>
					</sec:authorize>
					<sec:authorize ifAnyGranted="ROLE_CLOTHES_ORDER_FACTORY">
						<dd>
							<a href="managerOrder/waitPay" target="mainFrame">待支付订单</a>
						</dd>
					</sec:authorize>
					<sec:authorize ifAnyGranted="ROLE_CLOTHES_ORDER_FACTORY">
						<dd>
							<a href="managerOrder/finish" target="mainFrame">已完成订单</a>
						</dd>
					</sec:authorize>
					<sec:authorize ifAnyGranted="ROLE_CLOTHES_ORDER_FACTORY">
						<dd>
							<a href="managerOrder/cancel" target="mainFrame">已取消订单</a>
						</dd>
					</sec:authorize>
					
			</dl>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_CLOTHES_PRICE">
			<dl>
				<dt>
					<span>待开放&nbsp;</span>
				</dt>
				
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
