<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>信息提示</title>
<meta name="Author" content="aplus" />
<meta name="Copyright" content="aplus" />
<link href="/lk_clothes/resource/admin/css/error.css" rel="stylesheet" type="text/css"/>
</head>
<body class="errorPage">
	<div class="body">
		<div class="errorBox">
			<div class="errorMessage">
				您访问的页面不存在!
			</div>
			<div class="errorUrl">点击此处<a href="javascript: void(0);" onclick="window.history.back();">返回</a>或回到<a href="/lk_clothes/">首页</a></div>
		</div>
	</div>
</body>
</html>