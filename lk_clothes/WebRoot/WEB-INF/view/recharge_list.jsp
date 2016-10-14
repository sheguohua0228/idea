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
    
    <title>优惠卡列表</title>
    
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
		优惠卡列表&nbsp;&nbsp;----->&nbsp;&nbsp;总记录数:${pager.totalRecords} (共${pager.totalPages}页)
		
	</div>
	<div class="body">
		<form id="listForm" action="rechargeCard/query" method="post">
			<div class="listBar">
				&nbsp;&nbsp;
			</div>
			<table id="listTable" class="listTable">
				<tr>
					<th>
						<span>创建日期</span>
					</th>
					<th>
						<span>过期时间</span>
					</th>
					<th>
						<span>面额</span>
					</th>
					<th>
						<span>价格</span>
					</th>
					<th>
						<span>密码</span>
					</th>
					<th>
						<span>描述</span>
					</th>
					<th>
						<span>卡类型</span>
					</th>
					<th>
						<span>是否使用</span>
					</th>
					<th>第几次活动</th>
				</tr>
				<c:if test="${fn:length(pager.dataList) > 0}">
					<c:forEach var="qrcode" items="${pager.dataList}" varStatus="status">
						<tr>
							 
							<td>
							 <fmt:formatDate value="${qrcode.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>	
							</td>
							<td>
								 <fmt:formatDate value="${qrcode.invalidTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</td>
							<td>
								${qrcode.denomination}
							</td>
							<td>
								${qrcode.price}
							</td>
							<td>
								${qrcode.password }
							</td>
							<td>
								${qrcode.remark }
							</td>
							<td>
								<c:if test="${qrcode.rechargeType==0 }">积分</c:if>
								<c:if test="${qrcode.rechargeType ==1}">余额</c:if>
								<c:if test="${qrcode.rechargeType ==2}">优惠卷</c:if>
							</td>
							<td>
								<c:if test="${qrcode.useStatus ==0}">未使用</c:if>
								<c:if test="${qrcode.useStatus ==1}">已使用</c:if>
							</td>
							<td>${qrcode.time }</td>
						</tr>
					</c:forEach>
				</c:if>
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
