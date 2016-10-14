<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="icon" href="favicon.ico" type="image/x-icon" />
	<link href="<%=basePath%>/resource/admin/css/base.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>/resource/admin/css/admin.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.pager.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resource/admin/js/base.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resource/admin/js/admin.js"></script>
	
	<script type="text/javascript" src="<%=basePath%>/resource/common/layer/layer.js"></script>
	
	<script>
		
		$().ready(function(){
		})
		
	</script>
  </head>
  
  <body class="list">
	<div class="bar">
		管理员列表&nbsp;总记录数: ${pager.totalRecords} (共${pager.totalPages}页)
	</div>
	<div class="body">
		<form id="listForm" action="admin/query" method="post">
			<div class="listBar">
				<input type="button" class="formButton" onclick="location.href='admin/add'" value="添加管理员" />
				&nbsp;&nbsp;
				<label>查找: </label>
				用户名：
				<input type="text" name="username" value="${username }" />
				姓名：
				<input type="text" name="name" value="${name }" />
				<input type="button" id="searchButton" class="formButton" value="搜 索" />
				&nbsp;&nbsp;
				<label>每页显示: </label>
				<select name="pageSize" id="pageSize">
					<option value="10" <c:if test="${pager.pageSize == 10 }">selected</c:if>> 
						10
					</option>
					<option value="20" <c:if test="${pager.pageSize == 20 }">selected</c:if>>
						20
					</option>
					<option value="50" <c:if test="${pager.pageSize == 50 }">selected</c:if>>
						50
					</option>
					<option value="100" <c:if test="${pager.pageSize == 100 }">selected</c:if>>
						100
					</option>
				</select>
			</div>
			<table id="listTable" class="listTable">
				<tr>
					<th class="check">
						<input type="checkbox" class="allCheck" />
					</th>
					<th>
						<span>用户名</span>
					</th>
					<th>
						<span>姓名</span>
					</th>
					<th>
						<span>Email</span>
					</th>
					<th>
						<span>最后登录时间</span>
					</th>
					<th>
						<span>最后登录IP</span>
					</th>
					<th>
						<span>状态</span>
					</th>
					<th>
						<span>创建日期</span>
					</th>
					<th>
						<span>操作</span>
					</th>
				</tr>
				<c:if test="${fn:length(pager.dataList) > 0 }">
				<c:forEach items="${pager.dataList}" var="admin">
					<tr>
						<td>
							<input type="checkbox" name="ids" value="${admin.id}" />
						</td>
						<td>${admin.username }</td>
						<td>${admin.name }</td>
						<td>${admin.email }</td>
						<td><fmt:formatDate value="${admin.loginDate}" pattern="yyyy-MM-dd"/></td>
						<td>${admin.loginIp }</td>
						<td>
							<c:if test="${admin.isAccountEnabled && !admin.isAccountLocked && !admin.isAccountExpired && !admin.isCredentialsExpired}">
								<span class="green">正常</span>
							</c:if>
							<c:if test="${!admin.isAccountEnabled }">
								<span class="red"> 未启用 </span>
							</c:if>
							<c:if test="${admin.isAccountLocked}"><span class="red"> 已锁定 </span></c:if>
							<c:if test="${admin.isAccountExpired}"><span class="red"> 已过期 </span></c:if>
							<c:if test="${admin.isCredentialsExpired}"><span class="red"> 凭证过期 </span></c:if>
						</td>
						<td><fmt:formatDate value="${admin.createDate}" pattern="yyyy-MM-dd"/></td>
						<td><a href="admin/edit?id=${admin.id }" title="修改">[修改]</a></td>
					</tr>
				</c:forEach>
				</c:if>
			</table>
			<c:if test="${fn:length(pager.dataList) > 0 }">
				<div class="pagerBar">
					<div class="delete">
						<input type="button" id="deleteButton" class="formButton" url="admin/delete" value="删 除" disabled/>
					</div>
					<div class="pager">
						<%@ include file="pager.jsp"%>
					</div>
				</div>
			</c:if>
			<c:if test="${fn:length(pager.dataList) <= 0 }">
				<div class="noRecord">没有找到任何记录!</div>
			</c:if>
		</form>
	</div>
</body>
</html>
