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
						<c:if test="${empty employee}">
						"username": {
							required: true,
							username: true,
							minlength: 2,
							maxlength: 	 20,
							remote: "checkUsername"
						},
						</c:if>
						"password": {
							<c:if test="${empty employee}">
							required: true,
							</c:if>
							minlength: 4,
							maxlength: 	 20
						},
						"rePassword": {
							<c:if test="${empty employee}">
							required: true,
							</c:if>
							equalTo: "#rePassword"
						},
						"realName":"required",
						"phone":{
							required:true
						},
						"addressCenterId":"required",
						"gender":"required",
						"departmentId":"required"
					},
					messages: {
						<c:if test="${empty employee}">
						"username": {
							required: "请填写用户名",
							username: "用户名只允许包含中文、英文、数字和下划线",
							minlength:"用户名必须大于等于2",
							maxlength: "用户名必须小于等于20",
							remote: "用户名已存在"
						},
						</c:if>
						"password": {
							<c:if test="${empty employee}">
							required: "请填写密码",
							</c:if>
							minlength: "密码必须大于等于4",
							maxlength: 	 "密码必须小于等于20"
						},
						"rePassword": {
							<c:if test="${empty employee}">
							required: "请填写重复密码",
							</c:if>
							equalTo: "两次密码输入不一致"
						},
						"realName":"请输入真实姓名",
						"phone":{
							required:"请输入联系电话"
						},
						"addressCenterId":"请选择管辖社区",
						"gender":"请选择性别",
						"departmentId":"请选择部门"
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
			<c:if test="${!empty employee}">修改</c:if><c:if test="${empty employee}">添加</c:if>员工
		</div>
		<div id="validateErrorContainer" class="validateErrorContainer">
			<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
			<ul></ul>
		</div>
		<div class="body">
			<form enctype="multipart/form-data" id="validateForm" <c:if test="${empty employee}">action="save"</c:if><c:if test="${!empty employee}">action="update"</c:if> method="post">
				<c:if test="${!empty employee}">
					<input name="id" type="hidden" value="${employee.id }">
				</c:if>
				<table class="inputTable">
					<tr>
						<th>
							用户名:
						</th>
						<td>
							<c:if test="${empty employee }">
								<input type="text" name="username" value="${employee.username}" class="formText" />
								<label class="requireField">*</label>
							</c:if>
							<c:if test="${!empty employee }">
								${employee.username}
							</c:if>
						</td>
					</tr>
					
					<tr>
						<th>
							密码:
						</th>
						<td>
							<input type="password" name="password" class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr>
					
					<tr>
						<th>
							确认密码:
						</th>
						<td>
							<input name="rePassword" id="rePassword" type="password" class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr>
					
					<tr>
						<th>
							真实姓名:
						</th>
						<td>
							<input type="text" name="realName" value="${employee.realName}"  class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr>
					
					<tr>
						<th>
							性别:
						</th>
						<td>
							<input type="radio" name="gender" <c:if test="${employee.gender == 0 }">checked</c:if> value="0">男
							<input type="radio" name="gender" <c:if test="${employee.gender == 1 }">checked</c:if> value="1">女
							<label class="requireField">*</label>
						</td>
					</tr>
					
					<tr>
						<th>
							联系电话:
						</th>
						<td>
							<input type="text" name="phone" value="${employee.phone}" value="${employee.phone }" class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr>
					
					<tr>
						<th>
							头像:
						</th>
						<td>
							<input type="file" name="imageUpload" class="formText" />
						</td>
					</tr>

					<tr>
						<th>管辖社区:</th>
						<td>
							<select name="addressCenterId">
								<option value="">请选择...</option>
								<c:forEach items="${addressCenterList }" var="address">
									<option <c:if test="${!empty employee &&  employee.addressCenterId == address.id }">selected</c:if> value="${address.id}">${address.name }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					
					<tr>
						<th>部门:</th>
						<td>
							<select name="departmentId">
								<option value="">请选择...</option>
								<c:forEach items="${departmentList }" var="department">
									<option <c:if test="${!empty employee &&  employee.departmentId == department.id }">selected</c:if> value="${department.id}">${department.name }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					
					<c:if test="${!empty employee}">
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