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
    
    <title>已取消订单</title>
    
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
  </head>
  
  <body class="list">
	<div class="bar">
		已收衣订单列表&nbsp;总记录数: ${pager.totalRecords} (共${pager.totalPages}页)&nbsp;&nbsp;
	</div>
	<div class="body">
		<form id="listForm" action="clothesOrder/cancel" method="post">
			<div class="listBar">
				<label>查找: </label>
				手机号码：
				<input id="barCode" type="text" name="phoneNumber" value="${phoneNumber }" />
				订单编号：
				<input type="text" name="orderNumber" value="${orderNumber }" />
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
						<span>用户名</span>
					</th>
					<th>
						<span>联系电话</span>
					</th>
					<th>
						<span>订单总额</span>
					</th>
					<th>
						<span>状态</span>
					</th>
					
					<th>
						<span>洗衣状态</span>
					</th>
					<th>
						<span>下单时间</span>
					</th>
					<th>
						<span>取衣时间</span>
					</th>
					<th>
						<span>操作</span>
					</th>
				</tr>
				<c:if test="${fn:length(pager.dataList) > 0 }">
				<c:forEach var="order" items="${pager.dataList}">
					<tr>
						<td>
							<input type="checkbox" name="ids" value="${order.id}" />
						</td>
						<td>
							${order.orderNumber}
						</td>
						<td>
							${order.clothesAddress.username }
						</td>
						<td>
							${order.clothesAddress.phoneNumber }
						</td>
						<sec:authorize ifAnyGranted="ROLE_CLOTHES_CANCLE_ORDER">
						<td>
							${order.price }元
						</td>
						</sec:authorize>
						<td>
							<c:if test="${order.status == 0}">未处理</c:if>
							<c:if test="${order.status == 1}">已处理</c:if>
							<c:if test="${order.status == 2}">已完成</c:if>
							<c:if test="${order.status == 3}">已作废</c:if>
						</td>
						<td>
							<c:if test="${order.orderStatus == 0}">预约</c:if>
							<c:if test="${order.orderStatus == 1}">已收衣</c:if>
							<c:if test="${order.orderStatus == 2}">已分拣</c:if>
							<c:if test="${order.orderStatus == 3}">已洗涤</c:if>
							<c:if test="${order.orderStatus == 4}">派送中</c:if>
							<c:if test="${order.orderStatus == 5}">已完成</c:if>
						</td>
						<td>
							<fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<fmt:formatDate value="${order.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<a href="clothesOrder/view?id=${order.id}" title="查看">[查看]</a>
						</td>
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
