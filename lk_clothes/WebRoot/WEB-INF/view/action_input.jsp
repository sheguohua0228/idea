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
						"actionName":{
							remote:"checkActionName<c:if test='${!empty action}'>?oldValue=${action.actionName}</c:if>",
							required: true
						},
						"resource": {
							remote:"checkResource<c:if test='${!empty action}'>?oldValue=${action.resource}</c:if>",
							required: true
						},
						"roleName": {
							remote:"checkRoleName<c:if test='${!empty action}'>?oldValue=${action.roleName}</c:if>",
							required: true
						},
						"menuIndex": "required"
					},
					messages: {
						"actionName":{
							remote:"权限名称已经存在",
							required: "请输入权限名称"
						},
						"resource": {
							remote:"资源路径已经存在",
							required: "请输入资源路径"
						},
						"roleName": {
							remote:"角色名称已经存在",
							required: "请输入角色名称"
						},
						"menuIndex": "请选择所属菜单"
					},
					submitHandler: function(form) {
						$(form).find(":submit").attr("disabled", true);
						form.submit();
					}
				});
			})
			
		</script>

	</head>

	<body class="input">
		<div class="bar">	
			<c:if test="${!empty action}">修改</c:if><c:if test="${empty action}">添加</c:if>权限
		</div>
		<div id="validateErrorContainer" class="validateErrorContainer">
			<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
			<ul></ul>
		</div>
		<div class="body">
			<form id="validateForm" <c:if test="${empty action}">action="save"</c:if><c:if test="${!empty action}">action="update"</c:if> method="post">
				<c:if test="${!empty action}">
					<input name="id" type="hidden" value="${action.id }">
				</c:if>
				<table class="inputTable">
					<tr>
						<th>
							权限名称:
						</th>
						<td>
							<input type="text" name="actionName" value="${action.actionName}" class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr>
					
					<tr>
						<th>
							资源地址:
						</th>
						<td>
							<input type="text" name="resource" value="${action.resource}" class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr>
					
					<tr>
						<th>
							角色名称:
						</th>
						<td>
							<input type="text" name="roleName" value="${action.roleName}" class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr>

					<tr>
						<th>所属菜单:</th>
						<td>
							<select name="menuIndex" >
								<option value="">请选择...</option>
								<option value="7" <c:if test="${!empty action && action.menuIndex == 7}">selected</c:if>>洗衣管理</option>
								<option value="8" <c:if test="${!empty action && action.menuIndex == 8}">selected</c:if>>社区管理</option>
								<option value="5" <c:if test="${!empty action && action.menuIndex == 5}">selected</c:if>>管理员</option>
								<option value="6" <c:if test="${!empty action && action.menuIndex == 6}">selected</c:if>>帮助反馈管理</option>
								<option value="9" <c:if test="${!empty action && action.menuIndex == 9}">selected</c:if>>消息中心</option>
							</select>
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