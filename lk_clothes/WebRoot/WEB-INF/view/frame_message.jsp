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
	    <title></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/layer/layer.js"></script>
		
	</head>
	<script type="text/javascript">
		
		$().ready(function(){
			var status = '${status}';
			var message = '${message}';
			layer.confirm(message,{
				btn: ['确定'], shade: false //不显示遮罩
			},function(){
				if(status == 'success' || status == 'warn'){
					var index = parent.layer.getFrameIndex(window.name);
					window.parent.location.reload();
					parent.layer.close(index);
				}else{
					history.back();
				}
			});
		});
		
	</script>
<body>
</body>
</html>
