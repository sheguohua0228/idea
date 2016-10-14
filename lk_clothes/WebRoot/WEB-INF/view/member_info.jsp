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
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery-1.11.1.min.js"></script>
		<link href="<%=basePath%>/resource/admin/css/base.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/resource/admin/css/admin.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>/resource/common/layer/layer.js"></script>
	</head>
	<body class="input admin">
		<div class="bar">	
			 用户信息
		</div>
		<form method="get" action="memberInfo" onsubmit="return false;">
		<table class="inputTable">
		<tr>
						<th>
		用户手机号码</th><td><input type="number" required="required" id="phone" value="${sessionScope.mobiePhone }"/>
		<button type="submit" class="formButton" onclick="getMermerInfo()">查找</button></td>
		</table>
		</form>
		
		<div class="body">
		
					<input id="memberId" type="hidden" value="">
				<table class="inputTable">
					<tr>
						<th>
							用户名:
						</th>
						<td id="username">
							  ${member.username }
						</td>
					</tr>
					<tr>
						<th>姓名:</th>
						<td id="name">
							  ${member.name}
						</td>
					</tr>
					<tr>
						<th>性别:</th>
						<td id="gender">
							   <c:if test="${member.gender==0}">男</c:if> 
							   <c:if test="${member.gender==1}">女</c:if> 
						</td>
					</tr>
					<tr>
						<th>
							生日
						</th>
						<td id="birth">
							 <fmt:formatDate value="${member.birth }" pattern="yyyy-MM-dd"/>
						</td>
					</tr>
					<tr>
						<th>
							地址
						</th>
						<td id="address">
							   ${member.address}
						</td>
					</tr>
					<tr class="roleList">
					<th>
						电话号码
					</th>
					<td id="mobile">
						    ${member.mobile}
					</td>
					</tr>
					<tr>
						<th>
							积分
						</th>
						<td id="score">
							  ${member.score}
						</td>
					</tr>
					 
					<tr>
						<th>邮箱</th>
						<td id="email">
							   ${member.email}
						</td>
					</tr>
					 
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
					<button class="formButton" onclick="location.href='../clothesOrder/queryClothesOrder?phoneNumber='+$('#memberId').val();" >查看洗衣订单</button>
					<button class="formButton" onclick="location.href='printOrder?memberId='+$('#memberId').val();">查看打印订单</button>
					<button class="formButton" onclick="location.href='../clothesOrder/telOrderView'">下单</button>
					<button class="formButton" onclick="location.href='jumpFeedbackView'">反馈信息录入</button>
				</div>
			 
		</div>
		<script type="text/javascript">
		
			function getMermerInfo() {
				$.get("memberInfo", {
					mobiePhone : $("#phone").val()
				}, function(data) {
					if (data == null||data=='') {
						layer.msg("该用户不存在");
					} else {
						var sex = "未填写";
						if (data.gender == 0) {
							sex = "男";
						} else if (data.gender == 1) {
							sex = "女";
						}
						$("#gender").html(sex);
						$("#name").html(data.name);
						$("#username").html(data.username);
						$("#birth").html(timeStampDate(data.birth));
						$("#address").html(data.address);
						$("#mobile").html(data.mobile);
						$("#score").html(data.score);
						$("#email").html(data.email);
						$("#memberId").val(data.id);
					}
				});
			}
			function timeStampDate(time){
			    var datetime = new Date();
			    datetime.setTime(time);
			    var year = datetime.getFullYear();
			    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
			    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
			    return year + "-" + month + "-" + date;
			}
		</script>
	</body>

</html>