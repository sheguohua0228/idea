<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>乐E下洗衣管理平台</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style media="print">
		.headerFrame,.menuFrame{display: none;}
	</style>
  </head>
<frameset id="parentFrameset" rows="60,*" cols="*" frameborder="no" border="0" framespacing="0">
	<frame id="headerFrame" name="headerFrame" src="page/header" frameborder="no" scrolling="no" noresize="noresize" />
	<frameset id="mainFrameset" name="mainFrameset" cols="130,6,*" frameborder="no" border="0" framespacing="0">
		<frame id="menuFrame" name="menuFrame" src="menu/clothes" frameborder="no" scrolling="no" noresize="noresize" />
		<frame id="middleFrame" name="middleFrame" src="page/middle" frameborder="no" scrolling="no" noresize="noresize" />
		<frame id="mainFrame" name="mainFrame" src="page/index" frameborder="no" noresize="noresize" />
	</frameset>
</frameset>
<noframes>
	<body>
		noframes
	</body>
</noframes>
</html>
