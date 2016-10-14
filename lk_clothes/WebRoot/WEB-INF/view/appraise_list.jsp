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
    
    <title>评价列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="icon" href="favicon.ico" type="image/x-icon" />
	<link href="<%=basePath%>/resource/admin/css/base.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>/resource/admin/css/admin.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.pager.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resource/admin/js/base.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resource/admin/js/admin.js"></script>
	
	<script type="text/javascript" src="<%=basePath%>/resource/common/layer/layer.js"></script>
	<script>
		$().ready(function(){
			
		})
	</script>
	
  </head>
  
  <body class="list">
	<div class="bar">
		评价列表&nbsp;总记录数: ${pager.totalRecords} (共${pager.totalPages}页)&nbsp;&nbsp;
	</div>
	<div class="body">
		<form id="listForm" action="appraise/query" method="post">
			<div class="listBar">
				<label>查找: </label>
				 订单编号：
				<input type="text" name="orderNumber" value="${orderNumber}" />
				 手机号码：
				<input type="text" name="phoneNumber" value="${phoneNumber}" />
				<input type="button" id="searchButton" class="formButton" value="搜 索" />
				&nbsp;&nbsp; 
				<label>每页显示: </label>
				<select name="pageSize" id="pageSize">
					<option value="10" <c:if test="${pager.pageSize == 10 }">selected</c:if>> 
						10
					</option>
					<option value="20" <c:if test="${pager.pageSize == 20 }">selected</c:if>>
						20
					</option>
					<option value="50" <c:if test="${pager.pageSize == 50 }">selected</c:if>>
						50
					</option>
					<option value="100" <c:if test="${pager.pageSize == 100 }">selected</c:if>>
						100
					</option>
				</select>
			</div>
			<table id="listTable" class="listTable">
				<tr>
					<th class="check">
						<input type="checkbox" class="allCheck" />
					</th>
					<th>
						<span>订单编号</span>
					</th>
					<th>
						<span>订单类型</span>
					</th>
					<th>
						<span>手机号码</span>
					</th>
					<th>
						<span>评价时间</span>
					</th>
					<th>
						<span>评价星级</span>
					</th>
					<th>
						<span>评价内容</span>
					</th>
					
				</tr>
				<c:if test="${fn:length(pager.dataList) > 0 }">
				<c:forEach var="appraise" items="${pager.dataList}">
					<tr>
						<td>
							<input type="checkbox" name="ids" value="${appraise.id}" />
						</td>
						<td>
							${appraise.orderNumber}
						</td>
						<td>
						<c:choose>
							<c:when test="${appraise.orderType ==0}">洗衣订单</c:when>
							<c:when test="${appraise.orderType ==1}">乐E管家订单</c:when>
							<c:when test="${appraise.orderType ==2}">打印订单</c:when>
						</c:choose>
						</td>
						<td>
							${appraise.phoneNumber}
						</td>
						<td><fmt:formatDate value="${appraise.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
						<td><span style="color: #EFD988;">
							<c:choose>
								<c:when test="${appraise.servceQuality==1 }">★</c:when>
								<c:when test="${appraise.servceQuality==2 }">★★</c:when>
								<c:when test="${appraise.servceQuality==3 }">★★★</c:when>
								<c:when test="${appraise.servceQuality==4 }">★★★★</c:when>
								<c:when test="${appraise.servceQuality==5 }">★★★★★</c:when>
							</c:choose>
							</span>
						</td>
						<td>
							${appraise.content}
						</td>
					</tr>
				</c:forEach>
				</c:if>
			</table>
			<c:if test="${fn:length(pager.dataList) > 0 }">
				<div class="pagerBar">
					<!-- <div class="delete">
						<input type="button" id="deleteButton" class="formButton" url="addressCenter/delete" value="删 除" disabled />
					</div> -->
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
