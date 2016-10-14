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
    
    <title>Banner列表</title>
    
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
		Banner列表&nbsp;总记录数: ${pager.totalRecords} (共${pager.totalPages}页)&nbsp;&nbsp;
	</div>
	<div class="body">
		<form id="listForm" action="banner/query" method="post">
			<div class="listBar">
				<input type="button" class="formButton" onclick="location.href='banner/add'" value="添加Banner" />
				&nbsp;&nbsp;
				<label>查找: </label>
				Banner名称：
				<input type="text" name="name" value="${name}" />
				状态：
				<select name="status">
					<option value="">请选择..</option>
					<option <c:if test="${status == 0 }">selected</c:if> value="0">未上架</option>
					<option <c:if test="${status == 1 }">selected</c:if> value="1">已上架</option>
				</select>
				类型：
				<select name="type">
					<option value="">请选择..</option>
					<option <c:if test="${type == 0 }">selected</c:if> value="0">打印</option>
					<option <c:if test="${type == 1 }">selected</c:if> value="1">洗衣</option>
				</select>
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
						<span>Banner名称</span>
					</th>
					<th>
						<span>状态</span>
					</th>
					<th>
						<span>图片</span>
					</th>
					<th>
						<span>类型</span>
					</th>
					<th>
						<span>描述</span>
					</th>
					<th>
						<span>创建时间</span>
					</th>
					<th>
						<span>过期时间</span>
					</th>
					<th>
						<span>操作</span>
					</th>
				</tr>
				<c:if test="${fn:length(pager.dataList) > 0 }">
				<c:forEach var="banner" items="${pager.dataList}">
					<tr>
						<td>
							<input type="checkbox" name="ids" value="${banner.id}" />
						</td>
						<td>
							${banner.name}
						</td>
						<td>
							<c:if test="${banner.status == 1 }"><span class="green">已上架</span></c:if>
							<c:if test="${banner.status == 0 }"><span class="red">未上架</span></c:if>
						</td>
						<td>
							<a href="${banner.imageUrl }" target="_blank" title="点击查看" >查看</a>
						</td>
						<td>
							<c:if test="${banner.type == 1 }">洗衣</c:if>
							<c:if test="${banner.type == 0 }">打印</c:if>
						</td>
						<td>
							${banner.description }
						</td>
						<td>
							<fmt:formatDate value="${banner.createTime}" pattern="yyyy-MM-dd HH:mm"/>
						</td>
						<td>
							<fmt:formatDate value="${banner.expirationTime}" pattern="yyyy-MM-dd"/>
						</td>
						<td>
							<a href="banner/edit?id=${banner.id}" title="修改">[修改]</a>
						</td>
					</tr>
				</c:forEach>
				</c:if>
			</table>
			<c:if test="${fn:length(pager.dataList) > 0 }">
				<div class="pagerBar">
					<div class="delete">
						<input type="button" id="deleteButton" class="formButton" url="banner/delete" value="删 除" disabled />
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
