<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title></title>
		
		<link href="<%=basePath%>/resource/admin/css/base.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/resource/admin/css/admin.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.validate.methods.js"></script>
		
		<script type="text/javascript" src="<%=basePath%>/resource/admin/js/base.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/admin/js/admin.js"></script>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=E40859b35337939f73f5bdd19ea5f741"></script>
		

		<script>
			window.onload = function (){
				marker("${addressCenter.longitude}", "${addressCenter.latitude}");
			};
		</script>

	</head>

	<body class="input">
		<div class="bar">
			查看社区信息
		</div>
		<div class="body">
				<table class="inputTable">
					<tr>
						<th>
							社区名:
						</th>
						<td>
							${addressCenter.name}
						</td>
						<td rowspan = 10><div id="allmap" style="width:400px; height:350px;"></div></td>
					</tr>

					<tr>
						<th>地址:</th>
						<td>
							${addressCenter.address }
						</td>
					</tr>
					
					<tr>
						<th>
							经度:
						</th>
						<td>
							${addressCenter.longitude}
						</td>
					</tr>
					
					
					<tr>
						<th>
							纬度:
						</th>
						<td>
							${addressCenter.latitude}
						</td>
					</tr>

					<tr>
						<th>服务范围(米):</th>
						<td>
							${addressCenter.serviceRange }
						</td>
					</tr>
					
					<tr>
						<th>类型:</th>
						<td>
							<c:if test="${addressCenter.centerType == 0 }">主城区</c:if>
							<c:if test="${addressCenter.centerType == 1 }">机动区</c:if>
						</td>
					</tr>
					
					<tr>
						<th>
							街景图:
						</th>
						<td>
							<c:if test="${!empty addressCenter.icon }">
								<img src="${addressCenter.icon }"  width="300" height="200"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<th>
							是否启用: 
						</th>
						<td>
							<c:if test="${addressCenter.flag }">
								是
							</c:if>
							<c:if test="${!addressCenter.flag }">
								否
							</c:if>
						</td>
					</tr>
					<tr><th>负责小工:</th><td>${employeeName }</td></tr>
					<tr>
						<th>
							&nbsp;
						</th>
						<td>
							&nbsp;
						</td>
					</tr>
				</table>
				<div class="buttonArea">
					<input type="button" class="formButton" onclick="window.history.back();" value="返  回"/>
				</div>
		</div>
	</body>
	<script type="text/javascript" src="<%=basePath%>/resource/admin/js/BMap.js"></script>
</html>