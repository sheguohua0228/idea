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
    
    <title>社区消息查询</title>
    
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
	
	<script>
		$().ready(function(){
			
		});
	</script>
	
  </head>
  
  <body class="list">
	<div class="bar">
		社区消息列表&nbsp;总记录数: ${pager.totalRecords} (共${pager.totalPages}页)&nbsp;&nbsp;
	</div>
	<div class="body">
		 <form id="listForm" action="community/query" method="post">
			<div class="listBar">
				&nbsp;&nbsp;
				<label>查找: </label>
				社区名：
				<select name="communityId">
					<option value="">请选择..</option>
				 	<c:forEach items="${idAndName}" var="community">
						<option value="${community.id}" <c:if test="${communityId==community.id}">selected='selected'</c:if>>${community.name }</option>				 		
				 	</c:forEach>
				</select>
				消息名：
				<input type="text" name="messageName" value="${messageName}">
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
						<span>标题</span>
					</th>
					<th>
						<span>内容</span>
					</th>
					<th>
						<span>所属社区</span>
					</th>
					<th>
						<span>活动预览图</span>
					</th>
					<th>
						<span>创建日期</span>
					</th>
					<th>
						<span>操作</span>
					</th>
				</tr>
				<c:if test="${fn:length(pager.dataList) > 0 }">
				<c:forEach items="${pager.dataList}" var="message">
					<tr><td>
							<input type="checkbox" name="ids" value="${message.id}" />
						</td>
						<td>${fn:substring(message.title,0,10) }</td>
						<td title="${message.content}" >${fn:substring(message.content,0,20)}</td>
						
						<td>
							<c:forEach items="${idAndName}" var="communityName">
								<c:if test="${communityName.id==message.communityId }">${communityName.name }</c:if>
							</c:forEach>
						</td>
						
						<td>
							<c:if test="${!empty message.activityImageUrl}">
								<a href="<%=basePath%>${message.activityImageUrl}" target="_blank">查看</a>
							</c:if>
						</td>
						<td><fmt:formatDate value="${message.createDate}" pattern="yyyy-MM-dd HH:mm"/></td>
						<td><a href="community/edit?messageId=${message.id }" title="修改">[修改]</a></td>
					</tr>
				</c:forEach>
				</c:if>
			</table>
			<c:if test="${fn:length(pager.dataList) > 0 }">
				<div class="pagerBar">
					<div class="delete">
						<input type="button" id="deleteButton" class="formButton" url="community/delete" value="删 除" disabled/>
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
