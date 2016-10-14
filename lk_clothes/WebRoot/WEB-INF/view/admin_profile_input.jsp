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
						"currentPassword": {
							remote: "checkCurrentPassword"
						},
						"password": {
							requiredTo: "#currentPassword",
							minlength: 4,
							maxlength: 	 20
						},
						"rePassword": {
							equalTo: "#password"
						},
						"email": {
							required: true,
							email: true
						}
					},
					messages: {
						"currentPassword": {
							remote: "当前密码错误"
						},
						"password": {
							requiredTo: "请填写新密码",
							minlength: "密码必须大于等于4",
							maxlength: 	 "密码必须小于等于20"
						},
						"rePassword": {
							equalTo: "两次密码输入不一致"
						},
						"email": {
							required: "请填写E-mail",
							email: "E-mail格式不正确"
						}
					},
					submitHandler: function(form) {
						$(form).find(":submit").attr("disabled", true);
						//form.submit();
						ajaxSubmit();
					}
				});
				
				function ajaxSubmit(){
					var form_data = $validateForm.serialize();
					$.ajax({
						type:"post",
						url:"update",
						async:true,
						data:form_data,
						dataType:"json",
						success:function(result){
							if(result.status == 'success'){
								$.dialog({type: "success", title: "操作提示", content: "您的操作已成功!", ok: "确定", okCallback: null, cancelCallback: null,width: 380, modal: true});
							}else{
								$.dialog({type: "error", title: "操作提示", content: result.message, ok: "确定", okCallback: null, cancelCallback: null,width: 380, modal: true});
							}
						}
					});
				}
				
			})
			
		</script>

	</head>

	<body class="input">
		<div class="bar">
			编辑个人资料
		</div>
		<div id="validateErrorContainer" class="validateErrorContainer">
			<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
			<ul></ul>
		</div>
		<div class="body">
			<form id="validateForm" action="admin_profile!update.action" method="post">
				<table class="inputTable">
					<tr>
						<th>
							用户名: 
						</th>
						<td>
							${admin.username}
						</td>
					</tr>
					<tr>
						<th>
							姓&nbsp;&nbsp;&nbsp;名: 
						</th>
						<td>
							${admin.name}
						</td>
					</tr>
					
					<tr>
						<th>
							当前密码: 
						</th>
						<td>
							<input type="password" id="currentPassword" name="currentPassword" class="formText"  />
						</td>
					</tr>
					<tr>
						<th>
							新密码: 
						</th>
						<td>
							<input type="password" id="password" name="password" class="formText" title="密码长度只允许在4-20之间" />
						</td>
					</tr>
					<tr>
						<th>
							确认新密码: 
						</th>
						<td>
							<input type="password" name="rePassword" class="formText" />
						</td>
					</tr>
					<tr>
						<th>
							E-mail: 
						</th>
						<td>
							<input type="text" name="email" class="formText" value="${admin.email}" />
							<label class="requireField">*</label>
						</td>
					</tr>
					<tr>
						<th>
							&nbsp;
						</th>
						<td>
							<span class="warnInfo"><span class="icon">&nbsp;</span>系统提示: 如需修改密码请先填写当前密码,若留空则密码保持不变</span>
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