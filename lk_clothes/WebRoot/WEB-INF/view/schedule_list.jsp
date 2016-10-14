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
  </head>
  
  <body class="list">
	<div class="bar">
		排班列表&nbsp;总记录数: ${pager.totalRecords} (共${pager.totalPages}页)&nbsp;&nbsp;
	</div>
	<div class="body">
		<form id="listForm" action="schedule/query" method="post">
			<div class="listBar">
				<input type="button" class="formButton" onclick="location.href='schedule/modify'" value="请假/调休" />
				<input type="button" class="formButton" onclick="location.href='schedule/change'" value="换班" />
				&nbsp;&nbsp;
				<%-- <span>查找:&nbsp;</span>
				 社区名称：
				<input type="text" name="name" value="${name}" />
				<input type="button" onclick="location.href='schedule/queryByName'" class="formButton" value="搜 索" /> --%>
				&nbsp;&nbsp;
				<%-- <label>每页显示: </label>
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
				</select> --%>
			</div>
			<table id="listTable" class="listTable">
				<tr>
					<th>
						<span>社区名称</span>
					</th>
					<th>
						<span>星期一(${mondayTime})</span>
					</th>
					<th>
						<span>星期二(${tuesdayTime})</span>
					</th>
					<th>
						<span>星期三(${wednesdayTime})</span>
					</th>
					<th>
						<span>星期四(${thursdayTime})</span>
					</th>
					<th>
						<span>星期五(${fridayTime})</span>
					</th>
					<th>
						<span>星期六(${saturday})</span>
					</th>
					<th>
						<span>星期天(${sundayTime})</span>
					</th>
				</tr>
				
				<c:if test="${fn:length(pager.dataList) > 0 }">
				<c:forEach var="schedule" items="${pager.dataList}">
					<tr>
						<!--<td>
							<input type="checkbox" name="ids" value="${banner.id}" />
						</td>-->
						<td>
							${schedule.name}
						</td>
						<td>
							<c:if test="${schedule.mondayEmployyeeName == null || schedule.mondayEmployyeeName == ''}"><span style="color:red">无人值班</span></c:if>
							${schedule.mondayEmployyeeName}
						</td>
						<td>
							<c:if test="${schedule.tuesdayEmployyeeName == null || schedule.tuesdayEmployyeeName == ''}"><span style="color:red">无人值班</span></c:if>
							${schedule.tuesdayEmployyeeName}
						</td>
						<td>
							<c:if test="${schedule.wednesdayEmployyeeName == null || schedule.wednesdayEmployyeeName == ''}"><span style="color:red">无人值班</span></c:if>
							${schedule.wednesdayEmployyeeName}
						</td>
						<td>
							<c:if test="${schedule.thursdayEmployyeeName == null || schedule.thursdayEmployyeeName == ''}"><span style="color:red">无人值班</span></c:if>
							${schedule.thursdayEmployyeeName}
						</td>
						<td>
							<c:if test="${schedule.fridayEmployyeeName == null || schedule.fridayEmployyeeName == ''}"><span style="color:red">无人值班</span></c:if>
							${schedule.fridayEmployyeeName}
						</td>
						<td>
							<c:if test="${schedule.saturdayEmployyeeName == null || schedule.saturdayEmployyeeName == ''}"><span style="color:red">无人值班</span></c:if>
							${schedule.saturdayEmployyeeName}
						</td>
						<td>
							<c:if test="${schedule.sundayEmployyeeName == null || schedule.sundayEmployyeeName == ''}"><span style="color:red">无人值班</span></c:if>
							${schedule.sundayEmployyeeName}
						</td>
					</tr>
				</c:forEach>
				</c:if>
			</table>
			<c:if test="${fn:length(pager.dataList) > 0 }">
				<div class="pagerBar">
					<!--<div class="delete">
						<input type="button" id="deleteButton" class="formButton" url="banner/delete" value="删 除" disabled />
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
