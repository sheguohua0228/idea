<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>门票查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="icon" href="favicon.ico" type="image/x-icon" />
	<link href="<%=basePath%>/resource/admin/css/base.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>/resource/admin/css/admin.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.pager.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resource/admin/js/base.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resource/admin/js/admin.js"></script>
	
	<script type="text/javascript" src="<%=basePath%>/resource/common/layer/layer.js"></script>
	
	<style>
		.deletePrice{
			cursor: pointer;
		}
	</style>
	
  </head>
  
  <body class="list">
	<div class="bar">
		门票列表&nbsp;&nbsp; &nbsp;&nbsp;
	</div>
	<div class="body">
		<form id="listForm" action="checkQrCode/query" method="post">
			<div class="listBar">
				&nbsp;&nbsp;
			</div>
			<table id="listTable" class="listTable">
				<tr>
				 
					<th>
						<span>序号</span>
					</th>
					<th>
						<span>购票日期</span>
					</th>
					<sec:authorize ifAnyGranted="ROLE_ADMIN">
					<th>
						<span>过期时间</span>
					</th>
					 </sec:authorize>
					<th>
						<span>使用日期</span>
					</th>
					 <sec:authorize ifAnyGranted="ROLE_ADMIN">
					<th>
						<span>条形码</span>
					</th>
					<th>
						<span>是否使用</span>
					</th>
					</sec:authorize>
					
				</tr>
				<c:if test="${fn:length(pager.dataList) > 0}">
					<c:forEach var="qrcode" items="${pager.dataList}" varStatus="status">
						<tr>
							 
							<td>
								${status.index + 1}
							</td>
							<td>
								${qrcode.createTime}
							</td>
							<sec:authorize ifAnyGranted="ROLE_ADMIN">
							<td>
								${qrcode.expiredTime}
							</td>
							</sec:authorize>
							<td>
								<c:if test="${qrcode.userTime == '' || qrcode.userTime == null}">未使用</c:if>
								<c:if test="${qrcode.userTime != '' || qrcode.userTime != null}">${qrcode.userTime}</c:if>
							</td>
							<sec:authorize ifAnyGranted="ROLE_ADMIN">
							<td>
								${qrcode.code}
							</td>
							<td>
								<c:if test="${qrcode.isUse == false}">否</c:if>
								<c:if test="${qrcode.isUse == true}">是</c:if>
							</td>
							</sec:authorize>
							 
						</tr>
					</c:forEach>
				</c:if>
				<tr><th></th><th>终端售票总数</th><th>剩余量</th> </tr>
				<tr><td></td><td>${pager.totalRecords}</td><td>${total - pager.totalRecords}</td></tr>
				<tr><th> </th><th>乐一下购票记录</th><th>数量  </th><tr>
				<c:forEach items="${purchaseRecords }" var="record">
					<tr><td></td><td>${record.months }</td><td>${record.sum }</td></tr>
				</c:forEach>
			 	 
			 	<tr><th></th><th>合计</th><th>${total}</th></tr>
			</table>
			
			 <table class="listTable">
			 	
			 	
			 </table>
			 <c:if test="${fn:length(pager.dataList) > 0 }">
				<div class="pagerBar">
					<div class="pager">
						<%@ include file="pager.jsp"%>
					</div>
				</div>
			</c:if>
			<c:if test="${fn:length(pager.dataList) <= 0 }">
				<div class="noRecord">没有找到任何记录!</div>
			</c:if>
		</form>
	</div>
</body>
</html>
