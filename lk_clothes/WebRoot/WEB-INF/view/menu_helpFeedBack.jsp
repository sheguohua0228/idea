<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://www.springframework.org/security/tags"  prefix="sec"%>
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
		<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_ACTION,ROLE_GROUP_MANAGER,ROLE_FEEDBACK,ROLE_QUESTION">
			<dl>
				<dt>
					<span>帮助\反馈&nbsp;</span>
				</dt>
				<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_QUESTION">
				<dd>
					<a href="helpFeedBack/queryQuestionList" target="mainFrame">常见问题列表</a>
				</dd>
				</sec:authorize>
				<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FEEDBACK">
				<dd>
					<a href="helpFeedBack/queryList" target="mainFrame">APP反馈列表</a>
				</dd>
				</sec:authorize>
				<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FEEDBACK">
				<dd>
					<a href="helpFeedBack/queryTelFeedList" target="mainFrame">来电反馈列表</a>
				</dd>
				</sec:authorize>
				<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FEEDBACK">
				<dd>
					<a href="helpFeedBack/memberInfoView?mobiePhone=" target="mainFrame">用户信息</a>
				</dd>
				</sec:authorize>
				 <sec:authorize ifAnyGranted="ROLE_ACTION">
				<dd>
					<a href="appraise/query" target="mainFrame">评价列表</a>
				</dd>
				</sec:authorize>
				<%--
				<sec:authorize ifAnyGranted="ROLE_GROUP_MANAGER">
				<dd>
					<a href="groupManager/query" target="mainFrame">分组管理</a>
				</dd>
				</sec:authorize> --%>
			</dl>
		</sec:authorize>
	</div>
</body>
</html>
