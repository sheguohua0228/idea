<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	    <base href="<%=basePath%>">
    
	    <title>显示/隐藏菜单栏</title>
	    
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<link rel="icon" href="favicon.ico" type="image/x-icon" />
		<link href="<%=basePath%>/resource/admin/css/base.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/resource/admin/css/admin.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/admin/js/base.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/admin/js/admin.js"></script>
		
		<script type="text/javascript">
		$().ready(function() {
			var $main = $("#main");
			$main.click( function() {
				var mainFrameset = window.parent.window.document.getElementById("mainFrameset");
				if(mainFrameset.cols == "130,6,*") {
					mainFrameset.cols = "0,6,*";
					$main.removeClass("leftArrow");
					$main.addClass("rightArrow");
				} else {
					mainFrameset.cols = "130,6,*";
					$main.removeClass("rightArrow");
					$main.addClass("leftArrow");
				}
			})
		})
	</script>
		<style media="print">
			.middle{display: none;}
		</style>
	</head>
	<body class="middle">
		<div id="main" class="main leftArrow"></div>
	</body>
</html>
