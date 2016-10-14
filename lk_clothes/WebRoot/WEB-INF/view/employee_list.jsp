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
    
    <title>员工查询</title>
    
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
		员工列表&nbsp;总记录数: ${pager.totalRecords} (共${pager.totalPages}页)
	</div>
	<div class="body">
		<form id="listForm" action="employee/query" method="post">
			<div class="listBar">
				<input type="button" class="formButton" onclick="location.href='employee/add'" value="添加员工" />
				&nbsp;&nbsp;
				<label>查找: </label>
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
						<span>性别</span>
					</th>
					<th>
						<span>推荐码</span>
					</th>
					<th>
						<span>联系电话</span>
					</th>
					<th>
						<span>头像</span>
					</th>
					<th>
						<span>管辖社区</span>
					</th>
					<th>
						<span>部门</span>
					</th>
					<th>
						<span>创建日期</span>
					</th>
					<th>
						<span>操作</span>
					</th>
				</tr>
				<c:if test="${fn:length(pager.dataList) > 0 }">
				<c:forEach items="${pager.dataList}" var="employee">
					<tr>
						<td>
							<input type="checkbox" name="ids" value="${employee.id}" />
						</td>
						<td>${employee.username }</td>
						<td>${employee.realName }</td>
						<td>
							<c:if test="${employee.gender == 0 }">男</c:if>
							<c:if test="${employee.gender == 1 }">女</c:if>
						</td>
						<td>${employee.recommendedCode }</td>
						<td>${employee.phone }</td>
						<td>
							<c:if test="${!empty employee.headImageUrl }"><a target="_blank" href="${employee.headImageUrl }" title="查看">查看</a></c:if>
							<c:if test="${empty employee.headImageUrl }">暂无头像</c:if>
						</td>
						<td>
							<c:forEach var="address" items="${addressCenterList}">
								<c:if test="${address.id == employee.addressCenterId }">${address.name }</c:if>
							</c:forEach>
						</td>
						<td>
							<c:forEach var="department" items="${departmentList}">
								<c:if test="${department.id == employee.departmentId }">${department.name }</c:if>
							</c:forEach>
						</td>
						<td><fmt:formatDate value="${employee.createTime}" pattern="yyyy-MM-dd"/></td>
						<td><a href="employee/edit?id=${employee.id }" title="修改">[修改]</a></td>
					</tr>
				</c:forEach>
				</c:if>
			</table>
			<c:if test="${fn:length(pager.dataList) > 0 }">
				<div class="pagerBar">
					<div class="delete">
						<input type="button" id="deleteButton" class="formButton" url="employee/delete" value="删 除" disabled/>
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
