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
    
    <title>已收衣订单</title>
    
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
	<script>
		$().ready(function(){
			var barCode =$("#barCode");
			barCode.focus();
			barCode.keydown(function(e){
				if(e.keyCode == 13){ // 监听回车按钮
					if(barCode != null && barCode.length > 0){// 获取订单详情
						
						//layer.closeAll();
						var index =layer.open({
							type: 2,
						    title: '订单信息',
						    area: ['800px', '800px'],
						    maxmin: true,
						    content: 'clothesOrder/details_new?orderStatus=1&barCode='+$.trim(barCode.val()) //iframe的url
						});
						layer.full(index);
						barCode.val("");
						
					}
				}
			});
		});
		
		function barclick(barCode){
			if(barCode != null && barCode.length > 0){// 获取订单详情
				
					//layer.closeAll();
					var index=layer.open({
						type: 2,
					    title: '订单信息',
					    area: ['800px', '800px'],
					    maxmin: true,
					    content: 'clothesOrder/details_new?orderStatus=1&barCode='+$.trim(barCode) //iframe的url
					});
					layer.full(index);

					barCode.val("");
				}
		}
		
	</script>
	
  </head>
  
  <body class="list">
	<div class="bar">
		已收衣订单列表&nbsp;总记录数: ${pager.totalRecords} (共${pager.totalPages}页)&nbsp;&nbsp;
	</div>
	<div class="body">
		<form id="listForm" action="clothesOrder/collect" method="post">
			<div class="listBar">
				<label>查找: </label>
				条形码：
				<input id="barCode" type="text" name="barCode" value="${barCode }" />
				编号：
				<input type="text" name="orderNumber" value="${orderNumber }" />
				支付状态:
				<select name="payStatus">
					<option value="">请选择.</option>
					<option <c:if test="${!empty payStatus && payStatus == 0 }">selected</c:if> value="0">未支付</option>
					<option <c:if test="${!empty payStatus && payStatus == 1 }">selected</c:if> value="1">支付成功</option>
					<option <c:if test="${!empty payStatus && payStatus == 2 }">selected</c:if> value="2">支付失败</option>
				</select>
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
				<label>今日小哥已收衣:${count}件 &nbsp;&nbsp;&nbsp;&nbsp; 皮类:${clothes}件 &nbsp;&nbsp;&nbsp;&nbsp;鞋类:${shoes}件  &nbsp;&nbsp;&nbsp;&nbsp;织补:${darning}件</label>
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
						<span>条形码</span>
					</th>
					<th>
						<span>用户名</span>
					</th>
					<th>
						<span>联系电话</span>
					</th>
					<sec:authorize ifAnyGranted="ROLE_CLOTHES_CANCLE_ORDER">
					<th>
						<span>订单总额</span>
					</th>
					</sec:authorize>
					<th>
						<span>状态</span>
					</th>
					
					<th>
						<span>洗衣状态</span>
					</th>
					<th>
						<span>支付状态</span>
					</th>
					
					<th>
						<span>下单时间</span>
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
						
						
						<td >
						
						<a onclick="javascript:barclick('${order.barCode}')" href="javascript:;">${order.barCode}</a>
						
						
						
						</td>
						
						
						
						<td>
							${order.clothesAddress.username}
						</td>
						<td>
							${order.clothesAddress.phoneNumber}
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
							<c:if test="${order.orderStatus == 1}">收衣</c:if>
							<c:if test="${order.orderStatus == 2}">工厂</c:if>
							<c:if test="${order.orderStatus == 3}">洗涤</c:if>
							<c:if test="${order.orderStatus == 4}">送回</c:if>
						</td>
						<td>
							<c:if test="${order.payStatus == 0 }"><span class="red">未支付</span></c:if>
							<c:if test="${order.payStatus == 1 }"><span class="green">支付成功</span></c:if>
							<c:if test="${order.payStatus == 2 }"><span class="red">支付失败</span></c:if>
						</td>
						<td>
							<fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
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
