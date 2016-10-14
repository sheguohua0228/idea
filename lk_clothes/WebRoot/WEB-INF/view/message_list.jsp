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
    
    <title>问题列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="icon" href="favicon.ico" type="image/x-icon" />
	<link href="<%=basePath%>/resource/admin/css/base.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>/resource/admin/css/admin.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.pager.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resource/admin/js/base.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resource/admin/js/admin.js"></script>
	
		<script type="text/javascript" src="<%=basePath%>/resource/common/layer/layer.js"></script>
			<script>
				$().ready(function() {
					 
				});
				function edit(i){
					{
						layer.closeAll();
						layer.open({
							type : 2,
							title : '修改问题',
							shadeClose : true,
							shade : 0.5,
							area : [ '500', '90%' ],
							content : 'helpFeedBack/questionView?id='+i
						})
					}
					
				}
			</script>
			
</head>
  
  <body class="list">
	<div class="bar">
		消息&nbsp;总记录数: ${pager.totalRecords} (共${pager.totalPages}页)&nbsp;&nbsp;
	</div>
	<div class="body">
		<form id="listForm" action="message/query" method="post">
			<div class="listBar">
			 
				<label>查找: </label>
				消息类型<select name="msgType">
					<option value="">请选择..</option>
					<option value="0" <c:if test="${!empty type && type == 0 }">selected</c:if>>社区消息</option>
					<option value="1"<c:if test="${!empty type && type == 1 }">selected</c:if>>个人消息</option>
					<option value="2"<c:if test="${!empty type && type == 2 }">selected</c:if>>活动消息</option>
					</select>
				<input type="button" id="searchButton" class="formButton" value="搜 索" />
				&nbsp;&nbsp;
				<label>每页显示: </label>
				<select name="pager.pageSize" id="pageSize">
					<option value="10"> 
						10
					</option>
					<option value="20">
						20
					</option>
					<option value="50">
						50
					</option>
					<option value="100">
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
						<span>发布时间</span>
					</th>
					<th>
						<span>消息类型</span>
					</th>
					<th>
						<span>发布人</span>
					</th>
					
					<th>
						<span>标题</span>
					</th>
					<th>
						<span>操作</span>
					</th>
					
				</tr>
				<c:if test="${fn:length(pager.dataList) > 0 }">
				<c:forEach var="message" items="${pager.dataList}">
					<tr>
						<td>
							<input type="checkbox" name="ids" value="${message.id}" />
						</td>
						<td>
							<fmt:formatDate value="${message.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/> 
						</td>
						<td>
							<c:if test="${message.messageType == 0}">社区</c:if>
							<c:if test="${message.messageType == 1}">个人</c:if>
							<c:if test="${message.messageType == 2}">活动</c:if>
						</td>
						<td>
							${message.publisher }
						</td>
						<td>
							${message.title }
						</td>
						<td>
						<a href="message/view?id=${message.id }">[消息内容]</a>
						  <sec:authorize ifAnyGranted="ROLE_MESSAGE_ADD">
						    <a href="message/resend?id=${message.id }">[再次发送]</a>
						  </sec:authorize>
						</td>
					</tr>
				</c:forEach>
				</c:if>
			</table>
			<c:if test="${fn:length(pager.dataList) > 0 }">
				<div class="pagerBar">
					<!-- <div class="delete">
						<input type="button" id="deleteButton" class="formButton" url="helpFeedBack/delete" value="删 除" disabled hidefocus />
					</div> -->
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
