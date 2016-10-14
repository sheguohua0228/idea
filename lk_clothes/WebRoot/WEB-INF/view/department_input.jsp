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
						"name": {
							required: true,
							username: true,
							minlength: 2,
							maxlength: 	 20,
							remote: "checkName<c:if test='${!empty department}'>?oldValue=${department.name}</c:if>"
						},
						"manager":{
							required: true,
							username: true,
							minlength: 2,
							maxlength: 	 20
						},
						"tel":{
							required: true
						}
					},
					messages: {
						"name": {
							required: "请填写部门名称",
							username: "部门名称只允许包含中文、英文、数字和下划线",
							minlength: "部门名称必须大于等于2",
							maxlength: "部门名称必须小于等于20",
							remote: "部门名称已存在"
						},
						"manager":{
							required: "请填写负责人",
							username: "负责人只允许包含中文、英文、数字和下划线",
							minlength: "负责人必须大于等于2",
							maxlength: "负责人必须小于等于20"
						},
						"tel":{
							required: "请填写联系号码"
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

	<body class="input admin">
		<div class="bar">	
			<c:if test="${!empty admin}">修改</c:if><c:if test="${empty admin}">添加</c:if>部门
		</div>
		<div id="validateErrorContainer" class="validateErrorContainer">
			<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
			<ul></ul>
		</div>
		<div class="body">
			<form id="validateForm" <c:if test="${empty department}">action="save"</c:if><c:if test="${!empty department}">action="update"</c:if> method="post">
				<c:if test="${!empty department}">
					<input name="id" type="hidden" value="${department.id }">
				</c:if>
				<table class="inputTable">
					
					<tr>
						<th>
							部门名称:
						</th>
						<td>
							<input type="text" name="name" value="${department.name}" class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr>

					<tr>
						<th>负责人:</th>
						<td>
							<input type="text" name="manager" value="${department.manager }" class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr>
					
					<tr>
						<th>
							联系电话:
						</th>
						<td>
							<input type="text" name="tel" value="${department.tel}" class="formText">
							<label class="requireField">*</label>
						</td>
					</tr>
					<tr>
						<th>
							备注:
						</th>
						<td>
							<input type="text" name="note" value="${department.note}" class="formText">
						</td>
					</tr>
					<tr>
						<th>
							设置: 
						</th>
						<td>
							<label>
								<input name="isEnabled" type="checkbox" <c:if test="${!empty department && department.isEnabled}">checked</c:if> value="true">启用
							</label>
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