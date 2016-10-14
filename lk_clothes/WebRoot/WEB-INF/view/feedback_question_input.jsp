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
		
		<script type="text/javascript" src="<%=basePath%>/resource/common/editor/kindeditor.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/editor/lang/zh_CN.js"></script>
		
		<script type="text/javascript" src="<%=basePath%>/resource/admin/js/base.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/admin/js/admin.js"></script>
 
		<script>

			$().ready(function(){
				var $validateErrorContainer = $("#validateErrorContainer");
				var $validateErrorLabelContainer = $("#validateErrorContainer ul");
				var $validateForm = $("#validateForm");
			
				// 表单验证
				$validateForm.validate({
					errorContainer: $validateErrorContainer,
					errorLabelContainer: $validateErrorLabelContainer,
					wrapper: "li",
					errorClass: "validateError",
					ignoreTitle: true,
					rules: {
						"questionType":"required",
						"title":{
							required:true
						},
						"phone":{
							required:true
						},
						"description":{
							required:true,
						} 
					},
					messages: {
						"questionType":"请选择问题类型",
						"title":{
							required:"请输入标题",
						},
						"title":{
							required:"请输入手机号码",
						},
						"description":{
							required:"请输入描述",
						}
					},
					submitHandler: function(form) {
						$(form).find(":submit").attr("disabled", true);
						form.submit();
					}
				});
				
				
			});
		</script>

	</head>

	<body class="input" >
		<div class="bar">	
			添加反馈信息
		</div>
		<div id="validateErrorContainer" class="validateErrorContainer">
			<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
			<ul></ul>
		</div>
		<div class="body" >
			<form id="validateForm" action="inputFeed"  method="post">
				
				<table class="inputTable">
					<tr>
						<th>
							问题类型:
						</th>
						<td>
						<select name="questionType" >
								<option value="">请选择..</option>
								<option value="0" <c:if test="${!empty question && question.questionType == 0}">selected</c:if>>洗衣订单问题</option>
								<option value="1" <c:if test="${!empty question && question.questionType == 1}">selected</c:if>>打印订单问题</option>
								<option value="2" <c:if test="${!empty question && question.questionType == 2}">selected</c:if>>门票订单问题</option>
								<option value="3" <c:if test="${!empty question && question.questionType == 3}">selected</c:if>>其他问题</option>
								<option value="4" <c:if test="${!empty question && question.questionType == 4}">selected</c:if>>管家订单问题</option>
							</select>
							<label class="requireField">*</label>
						</td>
					</tr>
					<tr>
						<th>
							问题标题:
						</th>
						<td>
							<input type="text" name="title" value="${question.title}" class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr>
					<tr>
						<th>
							手机号码:
						</th>
						<td>
							<input type="text" name="phone" value="${sessionScope.mobiePhone}" class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr> 
					<tr>
						<th>
							订单编号:
						</th>
						<td>
							<input type="text" name="orderNum" value="${question.orderNum}" class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr> 
					<tr>
						<th>
							问题描述:
						</th>
						<td>
							<textarea name="description" class="formTextarea">${question.description}</textarea>
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
					<input type="submit" class="formButton" value="确  定"/>&nbsp;&nbsp;
					<input type="button" class="formButton" onclick="window.history.back();" value="返  回"/>
				</div>
			</form>
		</div>
	</body>

</html>