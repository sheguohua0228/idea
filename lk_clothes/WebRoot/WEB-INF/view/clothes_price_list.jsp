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
    
    <title>洗衣价格查询</title>
    
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
		
		$().ready(function(){
			$(".deletePrice").click(function(){
				var $this = $(this);
				layer.confirm('确定删除该价格？',{shade:false},function(){
					var id = $this.attr("targetId");
					$.getJSON("clothesPrice/delete","id="+id,function(data){
						if(data.status == 'success'){
							layer.msg("删除成功");
							$this.parent().parent().remove();
						}
					});
				})
			})
		})
		
	</script>
	
	<style>
		.deletePrice{
			cursor: pointer;
		}
	</style>
	
  </head>
  
  <body class="list">
	<div class="bar">
		衣服价格列表&nbsp;总记录数:${fn:length(clothesPriceList) }
	</div>
	<div class="body">
		<form id="listForm" action="clothesPrice/query" method="post">
			<div class="listBar">
				<input type="button" class="formButton" onclick="location.href='clothesPrice/add'" value="添加价格" />
				&nbsp;&nbsp;
				<label>查找: </label>
				名称：
				<input type="text" name="name" value="${name }" />
				<input type="button" id="searchButton" class="formButton" value="搜 索" />
				&nbsp;&nbsp;
			</div>
			<table id="listTable" class="listTable">
				<tr>
					<th>
						<span>类型名</span>
					</th>
					<th>
						<span>别名</span>
					</th>
					<th>
						<span>父类型</span>
					</th>
					<th>
						<span>原价</span>
					</th>
					<th>
						<span>折后价</span>
					</th>
					<th>
						<span>示例图片</span>
					</th>
					<th>
						<span>描述</span>
					</th>
					<th>
						<span>操作</span>
					</th>
				</tr>
				<c:if test="${fn:length(clothesPriceList) > 0}">
					<c:forEach var="price" items="${clothesPriceList}">
						<tr>
							<td>
								${price.name}
							</td>
							<td>
								${price.alias}
							</td>
							<td>
								<c:if test="${price.type == 0}">上装</c:if>
								<c:if test="${price.type == 1}">下装</c:if>
								<c:if test="${price.type == 2}">鞋包</c:if>
								<c:if test="${price.type == 3}">儿童</c:if>
								<c:if test="${price.type == 4}">精工织补</c:if>
								<c:if test="${price.type == 5}">居家用品</c:if>
							</td>
							<td>
								${price.originalPrice}
							</td>
							<td>
								${price.discountPrice }
							</td>
							<td>
								<c:if test="${!empty price.imageUrl}">
									<a href="<%=basePath%>${price.imageUrl}" target="_blank" title="点击查看">查看</a>
								</c:if>
								<c:if test="${empty price.imageUrl}">
									&nbsp;
								</c:if>
							</td>
							<td>
								${price.desc}
							</td>
							<td>
								<a href="clothesPrice/edit?id=${price.id}" title="修改">[修改]</a>
								<a class="deletePrice" targetId="${price.id}" title="删除">[删除]</a>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
			<c:if test="${fn:length(clothesPriceList) <= 0}">
				<div class="noRecord">没有找到任何记录!</div>
			</c:if>
		</form>
	</div>
</body>
</html>
