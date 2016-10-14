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
			
				var $allChecked = $("#validateForm .allChecked");
	
				$allChecked.click( function() {
					var $this = $(this);
					var $thisCheckbox = $this.parent().parent().find(":checkbox");
					if ($thisCheckbox.filter(":checked").size() > 0) {
						$thisCheckbox.attr("checked", false);
					} else {
						$thisCheckbox.attr("checked", true);
					}
					return false;
				});
			
			
				// 表单验证
				$validateForm.validate({
					errorContainer: $validateErrorContainer,
					errorLabelContainer: $validateErrorLabelContainer,
					wrapper: "li",
					errorClass: "validateError",
					ignoreTitle: true,
					rules: {
						"groupName":{
							remote:"checkGroupName<c:if test='${!empty groupManager}'>?oldValue=${groupManager.groupName}</c:if>",
							required: true
						}
					},
					messages: {
						"groupName":{
							remote:"分组名称已经存在",
							required: "请输入权限名称"
						}
					},
					submitHandler: function(form) {
						$(form).find(":submit").attr("disabled", true);
						form.submit();
					}
				});
				
				$.validator.addMethod("roleAuthorityListRequired", $.validator.methods.required, "请选择管理权限");
	
				$.validator.addClassRules("roleAuthorityList", {
					roleAuthorityListRequired: true
				});
			})
			
		</script>

	</head>

	<body class="input role">
		<div class="bar">	
			<c:if test="${!empty groupManager}">修改</c:if><c:if test="${empty groupManager}">添加</c:if>分组
		</div>
		<div id="validateErrorContainer" class="validateErrorContainer">
			<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
			<ul></ul>
		</div>
		<div class="body">
			<form id="validateForm" <c:if test="${empty groupManager}">action="save"</c:if><c:if test="${!empty groupManager}">action="update"</c:if> method="post">
				<c:if test="${!empty groupManager}">
					<input name="id" type="hidden" value="${groupManager.id }">
				</c:if>
				<table class="inputTable">
					<tr>
						<th>
							分组名称:
						</th>
						<td>
							<input type="text" name="groupName" value="${groupManager.groupName}" class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr>
					
					<tr>
						<th>
							描述:
						</th>
						<td>
							<textarea name="groupInfo" class="formTextarea">${groupManager.groupInfo}</textarea>
						</td>
					</tr>
					
					<tr class="authorityList">
						<th><a href="#" class="allChecked" title="点击全选此类权限">洗衣管理: </a></th>
						<td>
							<c:forEach items="${actionList }" var="action">
								<c:if test="${action.menuIndex == 7 }">
									<label><input type="checkbox" name="authorityList" class="roleAuthorityList" 
									value="${action.id}" <c:if test="${fn:contains( groupManager.authorityList,action.id)}">checked</c:if> >${action.actionName }</label>
								</c:if>
							</c:forEach>
						</td>
					</tr>
					<tr class="authorityList">
						<th><a href="#" class="allChecked" title="点击全选此类权限">社区管理:</a></th>
						<td>
							<c:forEach items="${actionList }" var="action">
								<c:if test="${action.menuIndex == 8 }">
									<label><input type="checkbox" name="authorityList" class="roleAuthorityList" 
										value="${action.id}" <c:if test="${fn:contains( groupManager.authorityList,action.id)}">checked</c:if>  >${action.actionName }</label>
								</c:if>
							</c:forEach>
						</td>
					</tr>
					
					<tr class="authorityList">
						<th><a href="#" class="allChecked" title="点击全选此类权限">管理员:</a></th>
						<td>
							<c:forEach items="${actionList }" var="action">
								<c:if test="${action.menuIndex == 5 }">
									<label><input type="checkbox" name="authorityList" class="roleAuthorityList" 
										value="${action.id}" <c:if test="${fn:contains( groupManager.authorityList,action.id)}">checked</c:if>  >${action.actionName }</label>
								</c:if>
							</c:forEach>
						</td>
					</tr>
					<tr class="authorityList">
						<th><a href="#" class="allChecked" title="点击全选此类权限">帮助反馈:</a></th>
						<td>
							<c:forEach items="${actionList }" var="action">
								<c:if test="${action.menuIndex == 6 }">
									<label><input type="checkbox" name="authorityList" class="roleAuthorityList" 
										value="${action.id}" <c:if test="${fn:contains( groupManager.authorityList,action.id)}">checked</c:if>  >${action.actionName }</label>
								</c:if>
							</c:forEach>
						</td>
					</tr>
					<tr class="authorityList">
						<th><a href="#" class="allChecked" title="点击全选此类权限">消息中心:</a></th>
						<td>
							<c:forEach items="${actionList }" var="action">
								<c:if test="${action.menuIndex == 9 }">
									<label><input type="checkbox" name="authorityList" class="roleAuthorityList" 
										value="${action.id}" <c:if test="${fn:contains( groupManager.authorityList,action.id)}">checked</c:if>  >${action.actionName }</label>
								</c:if>
							</c:forEach>
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