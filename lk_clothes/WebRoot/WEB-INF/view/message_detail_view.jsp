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
	</head>

	<body class="input">
		<div class="bar">	
			添加消息
		</div>
		<div id="validateErrorContainer" class="validateErrorContainer">
			<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
			<ul></ul>
		</div>
		<div class="body">
			<form id="validateForm" action='save' method="post">
				<table class="inputTable">
					<tr>
						<th>
							标题:
						</th>
						<td colspan="3">
							<span>${message.title}</span>
						</td>
					</tr>
					<tr>
						<th>
							发布时间:
						</th>
						<td colspan="3">
							<span><fmt:formatDate value="${message.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/> </span>
						</td>
					</tr>
					<tr>
						<th>
							发布者:
						</th>
						<td colspan="3">
							<span>${message.publisher}</span>
						</td>
					</tr>
						<tr>
						<th>
							消息类型:
						</th>
						<td colspan="3">
							<c:choose>
								<c:when test="${message.messageType ==0}">社区消息</c:when>
								<c:when test="${message.messageType ==1}">个人消息</c:when>
								<c:when test="${message.messageType ==2}">活动消息</c:when>
							</c:choose>
						</td>
					</tr>
					<tr>
						<th>接收人数:</th>
						<td>
						 ${message.receiverNum }
						</td>
						
					</tr>
					<!-- <tr>
						<th>接收者手机号码:</th>
						<td><input type="text" name="mobile" class="formText" />
						<label class="requireField">*社区或者接收者二选一</label></td>
					</tr> -->
					<c:if test="${!empty message.previewImageUrl}">
					 <tr >
						<th>
							预览图片:
						</th>
						<td>
							<img class="photoImg"  style="width:200px; height:150px;" src="${message.previewImageUrl}"/>
						</td>
					</tr> 
					</c:if>
					 <tr >
						<th>
							消息内容:
						</th>
						<td><div style="width: 375px;height: 627px;overflow-y: scroll;">
								<c:forEach items="${message.contents }" var="item">
									<c:if test="${item.contentType==0}">
										<p>${item.content}</p>
									</c:if>
									<c:if test="${item.contentType==1}">
										<img src="${item.content}" width="200px" height="150px"/>
									</c:if>
									<c:if test="${item.contentType==2}">
										<video src="${item.content}" width="300px" height="200px"></video>
									</c:if>
								</c:forEach>
								</div>
						</td>
					</tr> 
					
					
					 
					 
				</table>
				<div class="buttonArea">
					<input type="button" class="formButton" onclick="window.history.back();" value="返  回"/>
				</div>
			</form>
		</div>
	</body>

</html>