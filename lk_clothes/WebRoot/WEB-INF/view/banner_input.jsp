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
						"name":{
							required: true
						},
						"type":"required"
						<c:if test="${empty banner}">
						,
						"imageUpload": {
							required: true
						}
						</c:if>
					},
					messages: {
						"name":{
							required: "请输入Banner名称"
						},
						"type":"请选择Banner类型"
						<c:if test="${empty banner}">
						,
						"imageUpload": {
							required: "请选择Banner图片"
						}
						</c:if>
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
			<c:if test="${!empty banner}">修改</c:if><c:if test="${empty banner}">添加</c:if>Banner
		</div>
		<div id="validateErrorContainer" class="validateErrorContainer">
			<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
			<ul></ul>
		</div>
		<div class="body">
			<form id="validateForm" enctype="multipart/form-data" <c:if test="${empty banner}">action="save"</c:if><c:if test="${!empty banner}">action="update"</c:if> method="post">
				<c:if test="${!empty banner}">
					<input name="id" type="hidden" value="${banner.id }">
				</c:if>
				<table class="inputTable">
					<tr>
						<th>
							Banner名称:
						</th>
						<td>
							<input type="text" name="name" value="${banner.name}" class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr>
					<tr>
						<th>
							过期时间:
						</th>
						<td>
							<input type="date" name="expirationTime" value="${banner.expirationTime}" class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr>
					<tr>
						<th>
							图片:
						</th>
						<td>
							<input type="file" name="imageUpload" class="formText" />
							<c:if test="${empty banner}">
								<label class="requireField">*</label>
							</c:if>
						</td>
					</tr>
					
					<tr>
						<th>
							类型:
						</th>
						<td>
							<select name="type">
								<option value="">请选择..</option>
								<option <c:if test="${banner.type == 0 }">selected</c:if> value="0">打印</option>
								<option <c:if test="${banner.type == 1 }">selected</c:if> value="1">洗衣</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<th>
							描述:
						</th>
						<td>
							<input type="text" name="description" value="${banner.description}" class="formText" />
						</td>
					</tr>
					
					<tr>
						<th>
							设置:
						</th>
						<td>
							<input type="checkbox" <c:if test="${banner.status == 1 }">checked</c:if> name="status" value="1">上架
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