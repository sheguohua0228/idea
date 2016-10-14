<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://www.springframework.org/security/tags"  prefix="sec"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>页面标题栏</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="icon" href="favicon.ico" type="image/x-icon" />
	<link href="<%=basePath%>/resource/admin/css/base.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>/resource/admin/css/admin.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resource/admin/js/base.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resource/admin/js/admin.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resource/admin/js/comet4j.js"></script>
	<script type="text/javascript">
	$().ready(function() {
	
		var $menuItem = $("#menu .menuItem");
		
		$menuItem.click( function() {
			var $this = $(this);
			if ($menuItem != null) {
				$menuItem.removeClass("current");
			}
			$previousMenuItem = $this;
			$this.addClass("current");
		});
	});
	function init(){
		var v=$("#cno").val();
		JS.Engine.start('<%=basePath%>conn');
        JS.Engine.on(v,function(text){
        	self.parent.frames["mainFrame"].location="helpFeedBack/memberInfoView?mobiePhone="+text;
        });
  }
	</script>
	<style media="print">
	.header{display: none;}
	</style>
  </head>
  
  <body class="header"  onload="init()">
    <input type="hidden" value="${sessionScope.cno}" id="cno"/>
	<div class="body">
		<div class="bodyLeft">
			<div class="logo"></div>
		</div>
		<div class="bodyRight">
			<div class="link">
				<span class="welcome">
					<strong><sec:authentication property="name" /></strong>&nbsp;您好!&nbsp;
				</span>
				<a href="page/index" target="mainFrame">后台首页</a>
			</div>
			<div id="menu" class="menu">
				<ul>
					<sec:authorize ifAnyGranted="ROLE_CLOTHES_ORDER_APPOINTMENT,ROLE_CLOTHES_ORDER_COLLECT,ROLE_CLOTHES_ORDER_FACTORY,ROLE_CLOTHES_ORDER_WASH,ROLE_CLOTHES_ORDER_SEND">
						<li class="menuItem current">
							<a id="clothesTab" href="menu/clothes" target="menuFrame" hidefocus>洗衣管理</a>
						</li>
					</sec:authorize>
					<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_ACTION,ROLE_GROUP_MANAGER">
						<li class="menuItem">
							<a id="managerTab" href="menu/manager" target="menuFrame" hidefocus>乐E管家</a>
						</li>
					</sec:authorize>
					<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_ACTION,ROLE_GROUP_MANAGER,ROLE_TICKET">
						<li class="menuItem">
							<a id="communityTab" href="menu/community" target="menuFrame" hidefocus>社区管理</a>
						</li>
					</sec:authorize>
					<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_ACTION,ROLE_GROUP_MANAGER">
						<li class="menuItem">
							<a id="adminTab" href="menu/admin" target="menuFrame" hidefocus>管理员</a>
						</li>
					</sec:authorize>
					<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_ACTION,ROLE_GROUP_MANAGER,ROLE_FEEDBACK,ROLE_QUESTION">
						<li class="menuItem">
							<a id="helpFeedBackTab" href="menu/helpFeedBack" target="menuFrame" hidefocus>帮助\反馈</a>
						</li>
					</sec:authorize>
					<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_ACTION,ROLE_GROUP_MANAGER,ROLE_MESSAGE_QUERY,ROLE_MESSAGE_FACTORYINPUT,ROLE_MESSAGE_ADD">
						<li class="menuItem">
							<a id="messageTab" href="menu/message" target="menuFrame" hidefocus>消息中心</a>
						</li>
					</sec:authorize>
	            </ul>
	            <div class="info">
					<a class="profile" href="adminProfile/edit" target="mainFrame">个人资料</a>
					<a class="logout" href="admin/logout" target="_top">退出</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
