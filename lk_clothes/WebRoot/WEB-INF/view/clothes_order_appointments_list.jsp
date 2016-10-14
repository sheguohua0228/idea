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
    
    <title>预约订单</title>
    
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
			
			var barCode = $("#barCode");
			var $openButton = $("#openButton");
			barCode.focus();
			
			var isAutoRefresh = sessionStorage.getItem("isAutoRefresh");
			var $timeInput =  $("#timeInput");
			var timeout = sessionStorage.getItem("timeout");
			if(timeout != null && timeout.length > 0){
				$timeInput.val(timeout);
			}
			var interval;
			initRefresh();
			
			$(".invalidBtn").click(function(){
				$.dialog({type: "warn", content: "您确定要删除吗?", ok: "确 定", cancel: "取 消", modal: true, okCallback: batchDelete});
				$this = $(this);
				function batchDelete(){
					var orderId = $this.attr("orderId");
					$.ajax({
						url:"clothesOrder/invalid",
						type:"post",
						data: {
							id:orderId
						},
						dataType:"json",
						success:function(result){
							if(result.status == 'success'){
								$this.parent().parent().remove();
							}
							$.dialog({type: "error", content: result.message, modal: true, autoCloseTime: 1500});
						},
						error:function(e){
							$.dialog({type: "error", content: "服务器请求失败，请稍候重试", modal: true, autoCloseTime: 1500});
						}
					});
				}
			});
			
			$openButton.click(function(){
				if(isAutoRefresh == "true"){ // 已经开启自动刷新，则关闭
					clearTimeout(interval);
					isAutoRefresh = "false";
					$(this).val("开启刷新");
				}else{ // 未开启自动刷新，开启
					initTimeout();
					isAutoRefresh = "true";
					$(this).val("关闭刷新");
				}
				sessionStorage.setItem("isAutoRefresh",isAutoRefresh);
			});
			
			var regex = /^\+?[1-9][0-9]*$/;
			$timeInput.change(function(){
				if(!regex.test($(this).val())){
					layer.tips("自动刷新时间间隔必须为正整数",$(this));
				}else{
					sessionStorage.setItem("timeout",$(this).val());
				};
			});
			
			function initRefresh(){
				if(isAutoRefresh == 'true'){
					$openButton.val("关闭刷新");
					initTimeout();
				}else if(isAutoRefresh == 'true'){
					$openButton.val("开启刷新");
				}
			}
			
			function initTimeout(){
				interval = setTimeout(function(){
					window.location.reload();
				},parseInt($timeInput.val())*1000);
			}
			
		});
		
	</script>
	
  </head>
  
  <body class="list">
	<div class="bar">
		预约订单列表&nbsp;总记录数: ${pager.totalRecords} (共${pager.totalPages}页)&nbsp;&nbsp;
	</div>
	<div class="body">
		<form id="listForm" action="clothesOrder/appointment" method="post">
			<div class="listBar">
				<input id="timeInput"style="width:40px;" value="10">
				<input type="button" id="openButton" class="formButton" value="开启刷新" />
				<label>查找: </label>
				编号：
				<input type="text" name="orderNumber" value="${orderNumber }" />
				联系电话：
				<input id="phoneNumber" type="text" name="phoneNumber" value="${phoneNumber }" />
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
							<sec:authorize ifAnyGranted="ROLE_CLOTHES_CANCLE_ORDER">
							  <a class="invalidBtn" href="javascript:" orderId="${order.id }" title="取消">[取消]</a>
							  
							<c:if test="${empty order.clothesAddress.deliveryAddress}">
								<a href="clothesAddress/edit?clothesOrderId=${order.id}">[完善地址]</a>
							</c:if>
							<c:if test="${!empty order.employeeId and !empty order.clothesAddress.deliveryAddress}">
								<a href="clothesOrder/changeOrderEmployeeView?clothesOrderId=${order.id}&employeeId=${order.employeeId}&address=${order.clothesAddress.deliveryAddress}&changeType=0&clothesAddressId=${order.clothesAddressId}">[改派]</a>
							</c:if>
							</sec:authorize>
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
