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
	    <base href="<%=basePath%>">
	    <title>洗衣订单查询</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<link rel="icon" href="favicon.ico" type="image/x-icon" />
		<link href="<%=basePath%>/resource/admin/css/base.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/resource/admin/css/admin.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.tools.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.validate.methods.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/admin/js/base.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/admin/js/admin.js"></script>
	</head>
	<script type="text/javascript">
	$().ready( function() {
	
		var $tab = $("#tab");
	
		// Tab效果
		$tab.tabs(".tabContent", {
			tabs: "input"
		});
	
	});
	</script>
	<style>
		.falseIcon {
	    width: 24px;
	    height: 24px;
	    line-height: 24px;
	    display: inline-block;
	    //background: url("../images/base_icon.gif") no-repeat scroll 0px -60px transparent;
	    margin-top:15px;
	}
	</style>
	<body class="input admin">
	<div class="bar">
		洗衣订单
	</div>
	<div class="body">
			<input type="hidden" name="id" value="${id}" />
			<input type="hidden" name="clothesOrder.id" value="${id}" />
			<ul id="tab" class="tab">
				<li>
					<input type="button" value="基本信息" />
				</li>
				
			</ul>
			<div class="tabContent">
				<table class="inputTable">
				<tr>
					<th>
						订单状态: 
					</th>
					<td colspan="3">
						<span class="red">
							
							
							[<c:choose>
								<c:when test="${managerOrder.orderStatus == 0 }">
									预约中
								</c:when>
								<c:when test="${managerOrder.orderStatus == 1 }">
									进行中
								</c:when>
								<c:when test="${managerOrder.orderStatus == 2 }">
									未付款
								</c:when>
								<c:when test="${managerOrder.orderStatus == 3 }">
									已完成
								</c:when>
								<c:when test="${managerOrder.orderStatus == 9 }">
									订单取消
								</c:when>
								
							</c:choose>]
							[<c:choose>
								<c:when test="${managerOrder.payStatus == 0 }">
									未支付
								</c:when>
								<c:when test="${managerOrder.payStatus == 1 }">
									支付成功

								 
								 <c:if test="${managerOrder.payType== 0}">
							] [ 支付宝支付
						        </c:if>
						        <c:if test="${managerOrder.payType== 1}">
							] [ 微信支付
						        </c:if>
								 <c:if test="${managerOrder.payType== 2}">
							] [ 现金支付
						        </c:if>
						        <c:if test="${managerOrder.payType== 3}">
							] [ 积分支付
						        </c:if>
								 <c:if test="${managerOrder.payType== null}">
							] [ 客户端支付
						        </c:if>

								 <c:if test="${managerOrder.payType== 2}">
							] [ 现金支付
						        </c:if>
								 <c:if test="${managerOrder.payType== null}">
							] [ 客户端支付
						        </c:if>

								</c:when>
								
							</c:choose>]
							
						</span>
					</td>
				</tr>
				
				<tr>
					<td colspan="4">
						&nbsp;
					</td>
				</tr>
				
				<tr>
					<th>订单编号:</th>
					<td>${managerOrder.orderNumber}</td>
					<th>下单时间:</th>
					<td>
						<fmt:formatDate value="${managerOrder.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				
				<tr>
					<th>订单总金额:</th>
					<td>${managerOrder.price}￥</td>
					
						<th>最终金额</th>
						<td>
                       ${managerOrder.finalPrice}￥
					</td>
				
				</tr>
				
				
				<tr>
				<th>服务内容:</th>
					<td >
					${managerOrder.content}	
					</td>
				</tr>
				
				<tr>
					<th>
						客户姓名: 
					</th>
					<td>
						${managerOrder.userName}
					</td>
					<th>
						联系电话: 
					</th>
					<td>
						${managerOrder.phoneNumber}
					</td>
					
				</tr>
			    <tr>
				    <th>
						收件地址: 
					</th>
					<td>
						<c:if test="${managerOrder.managerAddress.beiDeliveryAddress!=null}">
							${managerOrder.managerAddress.beiDeliveryAddress}
						</c:if>
						
					</td>
					<th>
						小哥备注收件地址: 
					</th>
					<td>
						
						 <c:if test="${managerOrder.backAddress!=null}">
							${managerOrder.backAddress}
						</c:if>
					</td>
				</tr>
				
				 <tr>
				    <th>
						送件地址: 
					</th>
					<td>
						<c:if test="${managerOrder.managerAddress.beiSendAddress!=null}">
							${managerOrder.managerAddress.beiSendAddress}
						</c:if>
						
					</td>
					<th>
						小哥备注送件地址: <!-- 暂时更换两个位置 -->
					</th>
					<td>
						<c:if test="${managerOrder.takeAddress!=null}">
							${managerOrder.takeAddress}
						</c:if>
						 
					</td>
				</tr>
				<tr>
					<td colspan="4">
						&nbsp;
					</td>
				</tr>
				
				<tr>
					<th>收件/派件人姓名:</th>
					<td>${employee.realName} </td>
					<th>联系电话:</th>
					<td>${employee.phone}</td>
				</tr>
				
				<tr>
					<td colspan="4">
						&nbsp;
					</td>
				</tr>
				</table>
				</div>

			<div class="buttonArea">
				<input type="button" class="formButton" onclick="window.history.back();" value="返  回" />
			</div>
	</div>
</body>
</html>
