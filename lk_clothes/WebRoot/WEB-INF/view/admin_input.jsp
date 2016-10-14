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
						<c:if test="${empty admin}">
						"username": {
							required: true,
							username: true,
							minlength: 2,
							maxlength: 	 20,
							remote: "checkUsername"
						},
						</c:if>
						"password": {
							<c:if test="${empty admin}">
							required: true,
							</c:if>
							minlength: 4,
							maxlength: 	 20
						},
						"rePassword": {
							<c:if test="${empty admin}">
							required: true,
							</c:if>
							equalTo: "#password"
						},
						"email": {
							required: true,
							email: true
						},
						"groupIdList": "required"
					},
					messages: {
						<c:if test="${empty admin}">
						"username": {
							required: "请填写用户名",
							username: "用户名只允许包含中文、英文、数字和下划线",
							minlength: "用户名必须大于等于2",
							maxlength: 	 "用户名必须小于等于20",
							remote: "用户名已存在"
						},
						</c:if>
						"password": {
							<c:if test="${empty admin}">
							required: "请填写密码",
							</c:if>
							minlength: "密码必须大于等于4",
							maxlength: 	 "密码必须小于等于20"
						},
						"rePassword": {
							<c:if test="${empty admin}">
							required: "请填写重复密码",
							</c:if>
							equalTo: "两次密码输入不一致"
						},
						"email": {
							required: "请填写E-mail",
							email: "E-mail格式不正确"
						},
						"groupIdList": "请选择管理角色"
					},
					submitHandler: function(form) {
						$(form).find(":submit").attr("disabled", true);
						form.submit();
					}
				});
			})
			
		</script>

	</head>

	<body class="input admin">
		<div class="bar">	
			<c:if test="${!empty admin}">修改</c:if><c:if test="${empty admin}">添加</c:if>管理员
		</div>
		<div id="validateErrorContainer" class="validateErrorContainer">
			<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
			<ul></ul>
		</div>
		<div class="body">
			<form id="validateForm" <c:if test="${empty admin}">action="save"</c:if><c:if test="${!empty admin}">action="update"</c:if> method="post">
				<c:if test="${!empty admin}">
					<input name="id" type="hidden" value="${admin.id }">
				</c:if>
				<table class="inputTable">
					
					<tr>
						<th>
							用户名:
						</th>
						<td>
							<c:if test="${empty admin}">
								<input type="text" name="username" value="${admin.username}" class="formText" />
								<label class="requireField">*</label>
							</c:if>
							<c:if test="${!empty admin}">
								${admin.username }
							</c:if>
						</td>
					</tr>

					<tr>
						<th>密码:</th>
						<td>
							<input id="password" type="password" name="password" class="formText" />
							<c:if test="${empty admin}">
							<label class="requireField">*</label>
							</c:if>
						</td>
					</tr>
					
					<tr>
						<th>重复密码:</th>
						<td>
							<input type="password" name="rePassword" class="formText" />
							<c:if test="${empty admin}">
								<label class="requireField">*</label>
							</c:if>
						</td>
					</tr>

					<tr>
						<th>
							Email:
						</th>
						<td>
							<input type="text" name="email" value="${admin.email}" class="formText">
							<label class="requireField">*</label>
						</td>
					</tr>
					<tr>
						<th>
							姓名:
						</th>
						<td>
							<input type="text" name="name" value="${admin.name}" class="formText">
						</td>
					</tr>
					<tr class="roleList">
					<th>
						管理分组: 
					</th>
					<td>
						<c:forEach var="group" items="${groupList }">
						<label>
							<input type="checkbox" name="groupIdList" value="${group.id}" <c:if test="${fn:contains(admin.groupIdList,group.id)}">checked</c:if> />${group.groupName}
						</label>
						</c:forEach>
						<label class="requireField" style="color:red;">*</label>
					</td>
					</tr>
					<tr>
						<th>
							设置: 
						</th>
						<td>
							<label>
								<input name="isAccountEnabled" type="checkbox" <c:if test="${!empty admin && admin.isAccountEnabled}">checked</c:if> value="true">启用
							</label>
						</td>
					</tr>
					<c:if test="${!empty admin}">
					<tr>
						<th>&nbsp;</th>
						<td>
							<span class="warnInfo"><span class="icon">&nbsp;</span>如果要修改密码,请填写密码,若留空,密码将保持不变!</span>
						</td>
					</tr>
					</c:if>
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