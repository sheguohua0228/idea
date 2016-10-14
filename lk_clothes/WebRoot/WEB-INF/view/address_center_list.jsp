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
    
    <title>社区管理</title>
    
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
		社区列表&nbsp;总记录数: ${pager.totalRecords} (共${pager.totalPages}页)&nbsp;&nbsp;
	</div>
	<div class="body">
		<form id="listForm" action="addressCenter/query" method="post">
			<div class="listBar">
				<input type="button" class="formButton" onclick="location.href='addressCenter/add'" value="添加社区" />
				&nbsp;&nbsp;
				<label>查找: </label>
				社区名称：
				<input type="text" name="name" value="${name}" />
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
						<span>社区名称</span>
					</th>
					<th>
						<span>地址</span>
					</th>
					<th>
						<span>服务范围(米)</span>
					</th>
					<th>
						<span>类型</span>
					</th>
					<th>
						<span>街景</span>
					</th>
					<th>
						<span>是否启用</span>
					</th>
					<th>
						<span>操作</span>
					</th>
				</tr>
				<c:if test="${fn:length(pager.dataList) > 0 }">
				<c:forEach var="address" items="${pager.dataList}">
					<tr>
						<td>
							<input type="checkbox" name="ids" value="${address.id}" />
						</td>
						<td>
							${address.name}
						</td>
						<td>
							${address.address}
						</td>
						<td>
							${address.serviceRange }
						</td>
						<td>
							<c:if test="${address.centerType == 0 }">主城区</c:if>
							<c:if test="${address.centerType == 1 }">机动区</c:if>
						</td>
						<td>
							<c:if test="${!empty address.icon }">
								<a href="${address.icon }" target="_blank" title="查看">查看街景</a>
							</c:if>
						</td>
						<td>
							<c:if test="${address.flag }">
								<span class="trueIcon"></span>
							</c:if>
							<c:if test="${!address.flag }">
								<span class="falseIcon"></span>
							</c:if>
						</td>
						<td>
							<a href="addressCenter/view?id=${address.id}" title="查看">[查看]</a>
							<a href="addressCenter/edit?id=${address.id}" title="修改">[修改]</a>
						</td>
					</tr>
				</c:forEach>
				</c:if>
			</table>
			<c:if test="${fn:length(pager.dataList) > 0 }">
				<div class="pagerBar">
					<div class="delete">
						<input type="button" id="deleteButton" class="formButton" url="addressCenter/delete" value="删 除" disabled />
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
