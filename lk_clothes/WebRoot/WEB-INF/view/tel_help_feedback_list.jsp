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
    
    <title>帮助\反馈查询</title>
    
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
 	<script type="text/javascript">
			$.ajax({
				url : "",
				data : {},
				success : function() {

				},
				error : function() {

				}
			});
			function look(i) {
				layer.closeAll();
				layer.open({
					type : 2,
					title : '反馈信息',
					shadeClose : true,
					shade : 0.5,
					area : [ '500', '90%' ],
					content : 'helpFeedBack/telFeedDetail?feedId=' + i //iframe的url
				});
			}
		</script>  
	
  </head>
  
  <body class="list">
	<div class="bar">
		反馈列表&nbsp;总记录数: ${pager.totalRecords} (共${pager.totalPages}页)&nbsp;&nbsp;
	</div>
	<div class="body">
		<form id="listForm" action="helpFeedBack/queryTelFeedList" method="post">
			<div class="listBar">
				<label>查找: </label>
				用户手机号：
				<input type="text" name="phone" value="${phone}" />
				订单编号：
				<input id="barCode" type="text" name="orderNum" value="${orderNum }" />
				问题类型:<select name="type">
					<option value="">请选择..</option>
					<option value="0" <c:if test="${!empty type && type == 0 }">selected</c:if>>洗衣订单问题</option>
					<option value="1"<c:if test="${!empty type && type == 1 }">selected</c:if>>打印订单问题</option>
					<option value="2"<c:if test="${!empty type && type == 2 }">selected</c:if>>门票问题</option>
					<option value="3"<c:if test="${!empty type && type == 3 }">selected</c:if>>其他问题</option>
					<option value="4"<c:if test="${!empty type && type == 4 }">selected</c:if>>管家订单问题</option>
					</select>
				是否处理：<select name="handle">
					<option value="">请选择..</option>
					<option value="0" <c:if test="${!empty handle && handle == 0 }">selected</c:if>>未解决</option>
					<option value="1"<c:if test="${!empty handle && handle == 1 }">selected</c:if>>已解决</option>
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
					<th>
						<span>手机号</span>
					</th>
					<th>
						<span>订单编号</span>
					</th>
					<th>
						<span>问题标题</span>
					</th>
					<th>
						<span>问题类型</span>
					</th>
					 
					<th>
						<span>问题简述</span>
					</th>
					<th>
						<span>是否处理</span>
					</th>
					
					<th>
						<span>反馈时间</span>
					</th>
					<th>
						<span>操作</span>
					</th>
				</tr>
				<c:if test="${fn:length(pager.dataList) > 0 }">
				<c:forEach var="feed" items="${pager.dataList}">
					<tr>
						 
						<td>
							${feed.phoneNo}
						</td>
						<td>
							${feed.orderNum }
						</td>
						<td>
							${feed.question.title }
						</td>
						<td>
								<c:if test="${feed.question.questionType == 0}">洗衣订单</c:if>
								<c:if test="${feed.question.questionType == 1}">打印订单</c:if>
								<c:if test="${feed.question.questionType == 2}">门票</c:if>
								<c:if test="${feed.question.questionType == 3}">其他</c:if>
								<c:if test="${feed.question.questionType == 4}">管家订单</c:if>
						</td>
					 
						<td>
							${feed.questionDetail}
						</td>
						 <td>
							<c:if test="${feed.idHandle == true}">已处理</c:if>
							<c:if test="${feed.idHandle == false}">未处理</c:if>
						</td>
						<td>
							<fmt:formatDate value="${feed.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<a href="javascript:void(0);" title="查看" onclick="look('${feed.id}')">[查看]</a>
						</td>
					</tr>
				</c:forEach>
				</c:if>
			</table>
			<c:if test="${fn:length(pager.dataList) > 0 }">
				<div class="pagerBar">
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
