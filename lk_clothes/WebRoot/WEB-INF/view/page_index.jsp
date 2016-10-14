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
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="icon" href="favicon.ico" type="image/x-icon" />
	<link href="<%=basePath%>/resource/admin/css/base.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>/resource/admin/css/admin.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resource/admin/js/base.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resource/admin/js/admin.js"></script>
	<script type="text/javascript">
		$().ready(function(){
			
			var $menu_item;
			//var $menuFrame = $("#menuFrame",window.parent.document);
			$("td a").click(function(){
				var menuTab = $(this).attr("menuTab");
				$menu_item = $(window.parent.frames["headerFrame"].document).find("li #"+menuTab);
				console.log($menu_item);
				$menu_item.trigger("click");
			});
		});
		<%-- function init(){
			var v=$("#cno").val();
			JS.Engine.start('<%=basePath%>conn');
	        JS.Engine.on(v,function(text){
	        	look(text);
	        	 
	        });
	  }
	   function look(i){ 
			layer.closeAll();
			layer.open({
				type: 2,
			    title: '用户信息',
			    shadeClose: true,
			    shade: 0.5,
			    area: ['1000', '90%'],
			    content: 'helpFeedBack/memberInfo?mobiePhone='+i //iframe的url
			});
		} --%>
		            
	</script>
	<script type="text/javascript" src="<%=basePath%>/resource/common/layer/layer.js"></script>
 	 
  </head>
  
  <body class="index">
 
	<div class="bar">
		欢迎使用乐E下管理系统！ 
	</div>
	<div class="body">
		<div class="bodyLeft">
			<table class="listTable">
				<tr>
				</tr>
				<sec:authorize ifAnyGranted="ROLE_CLOTHES_ORDER_APPOINTMENT,ROLE_CLOTHES_ORDER_COLLECT,ROLE_CLOTHES_ORDER_FACTORY,ROLE_CLOTHES_ORDER_WASH,ROLE_CLOTHES_ORDER_SEND">
				<tr>
					<sec:authorize ifAnyGranted="ROLE_CLOTHES_ORDER_APPOINTMENT">
					<td>
						<a menuTab="clothesTab" href="clothesOrder/appointment">[预约订单]</a>
					</td>
					</sec:authorize>
					<sec:authorize ifAnyGranted="ROLE_CLOTHES_ORDER_COLLECT">
					<td>
						<a menuTab="clothesTab" href="clothesOrder/collect">[已收衣订单]</a>
					</td>
					</sec:authorize>
					<sec:authorize ifAnyGranted="ROLE_CLOTHES_ORDER_FACTORY">
					<td>
						<a menuTab="clothesTab" href="clothesOrder/factory">[已分拣订单]</a>
					</td>
					</sec:authorize>
					<sec:authorize ifAnyGranted="ROLE_CLOTHES_ORDER_WASH">
					<td>
						<a menuTab="clothesTab" href="clothesOrder/wash">[已洗涤订单]</a>
					</td>
					</sec:authorize>
					<sec:authorize ifAnyGranted="ROLE_CLOTHES_ORDER_SEND">
					<td>
						<a menuTab="clothesTab" href="clothesOrder/sending">[派送中订单]</a>
					</td>
					</sec:authorize>
					<sec:authorize ifAnyGranted="ROLE_CLOTHES_ORDER_COMPLETED">
					<td>
						<a menuTab="clothesTab" href="clothesOrder/completed">[已完成订单]</a>
					</td>
					</sec:authorize>
				</tr>
				</sec:authorize>
			</table>
		</div>
	</div>
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="copyright"></div>
</body>
</html>
