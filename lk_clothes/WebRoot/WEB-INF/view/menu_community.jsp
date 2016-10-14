<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
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

<link href="<%=basePath%>/resource/admin/css/base.css" rel="stylesheet"
	type="text/css" />
<link href="<%=basePath%>/resource/admin/css/admin.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="<%=basePath%>/resource/common/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/resource/admin/js/admin.js"></script>
</head>
<body class="menu">
	<div class="body">

		<sec:authorize
			ifAnyGranted="ROLE_COMMUNITY_QUERY,ROLE_COMMUNITY_ADD,ROLE_ADDRESS_CENTER">
			<dl>
				<dt>
					<span>社区信息管理&nbsp;</span>
				</dt>
				<sec:authorize ifAnyGranted="ROLE_ADDRESS_CENTER">
					<dd>
						<a href="addressCenter/query" target="mainFrame">社区管理</a>
					</dd>
				</sec:authorize>
				<%-- <sec:authorize ifAnyGranted="ROLE_COMMUNITY_ADD">
					<dd>
						<a href="community/add" target="mainFrame">发布消息</a>
					</dd>
				</sec:authorize>
				<sec:authorize ifAnyGranted="ROLE_COMMUNITY_QUERY">
					<dd>
						<a href="community/query" target="mainFrame">查看消息</a>
					</dd>
				</sec:authorize> --%>

			</dl>
		</sec:authorize>
		
		<sec:authorize ifAnyGranted="ROLE_DEPARTMENT">
			<dl>
				<dt>
					<span>部门管理&nbsp;</span>
				</dt>
				<sec:authorize ifAnyGranted="ROLE_DEPARTMENT">
					<dd>
						<a href="department/query" target="mainFrame">部门管理</a>
					</dd>
				</sec:authorize>
			</dl>
		</sec:authorize>
		
		<sec:authorize ifAnyGranted="ROLE_EMPLOYEE">
			<dl>
				<dt>
					<span>员工管理&nbsp;</span>
				</dt>
				<sec:authorize ifAnyGranted="ROLE_EMPLOYEE">
					<dd>
						<a href="employee/query" target="mainFrame">员工管理</a>
					</dd>
				</sec:authorize>
			</dl>
		</sec:authorize>
		
		<sec:authorize ifAnyGranted="ROLE_BANNER">
			<dl>
				<dt>
					<span>Banner管理&nbsp;</span>
				</dt>
				<sec:authorize ifAnyGranted="ROLE_BANNER">
					<dd>
						<a href="banner/query" target="mainFrame">Banner管理</a>
					</dd>
				</sec:authorize>
			</dl>
		</sec:authorize>
		
		<sec:authorize ifAnyGranted="ROLE_BANNER">
			<dl>
				<dt>
					<span>排班管理&nbsp;</span>
				</dt>
				<sec:authorize ifAnyGranted="ROLE_BANNER">
					<dd>
						<a href="schedule/query" target="mainFrame">排班管理</a>
					</dd>
				</sec:authorize>
			</dl>
		</sec:authorize>
		
		<sec:authorize ifAnyGranted="ROLE_TICKET">
			<dl>
				<dt>
					<span>门票管理&nbsp;</span>
				</dt>
				<sec:authorize ifAnyGranted="ROLE_TICKET">
					<dd>
						<a href="checkQrCode/query" target="mainFrame">门票管理</a>
					</dd>
				</sec:authorize>
				<sec:authorize ifAnyGranted="ROLE_ADMIN">
					<dd>
						<a href="checkQrCode/input" target="mainFrame">增加门票</a>
					</dd>
				</sec:authorize>
			</dl>
		</sec:authorize>
		
		<sec:authorize ifAnyGranted="ROLE_ADMIN">
			<dl>
				<dt>
					<span>优惠卷/充值卡&nbsp;</span>
				</dt>
				<sec:authorize ifAnyGranted="ROLE_ADMIN">
					<dd>
						<a href="rechargeCard/query" target="mainFrame">优惠卷/充值卡</a>
					</dd>
				</sec:authorize>
				<sec:authorize ifAnyGranted="ROLE_ADMIN">
					<dd>
						<a href="rechargeCard/input" target="mainFrame">创建优惠卡</a>
					</dd>
				</sec:authorize>
				<sec:authorize ifAnyGranted="ROLE_ADMIN">
					<dd>
						<a href="rechargeCard/group/input" target="mainFrame">创建拼团卡</a>
					</dd>
				</sec:authorize>
			</dl>
		</sec:authorize>

		
	</div>
</body>
</html>
