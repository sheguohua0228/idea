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
	    <base href="<%=basePath%>">
	    <title>反馈</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<link rel="icon" href="favicon.ico" type="image/x-icon" />
		<link href="<%=basePath%>/resource/admin/css/base.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/resource/admin/css/admin.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.tools.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.validate.methods.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/admin/js/base.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/admin/js/admin.js"></script>
	</head>
	<script type="text/javascript">
	$().ready( function() {
	
		var $tab = $("#tab");
	
		// Tab效果
		$tab.tabs(".tabContent", {
			tabs: "input"
		});
		 
			var $validateErrorContainer = $("#validateErrorContainer");
			var $validateErrorLabelContainer = $("#validateErrorContainer ul");
			var $validateForm = $("#validateForm");
		
			// 表单验证
			$validateForm.validate({
				errorContainer: $validateErrorContainer,
				errorLabelContainer: $validateErrorLabelContainer,
				wrapper: "li",
				errorClass: "validateError",
				ignoreTitle: true,
				rules: {
					"answer":{
						required:true
					} 
				},
				messages: {
					"answer":{
						required:"请输入答案",
					} 
				},
				submitHandler: function(form) {
					$(form).find(":submit").attr("disabled", true);
					$.post("helpFeedBack/reply",
							{feedId:$("#feedId").val(),answer:$("#answer").val()},
							function(data){
								if(data=="success"){
									alert("回复成功");
								}else{
									alert("回复失败");	
								}
								//当你在iframe页面关闭自身时
								var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
								parent.layer.close(index); //再执行关闭     
							}
					);
				}
			});
			
			
		  
	});
	</script>
	<style>
		.falseIcon {
	    width: 24px;
	    height: 24px;
	    line-height: 24px;
	    display: inline-block;
	    *background: url("../images/base_icon.gif") no-repeat scroll 0px -60px transparent;
	    margin-top:15px;
	}
	</style>
	<body class="input admin" style="max-width: 500px !important;min-width: 480px !important;">
	<div class="bar">
		反馈详情
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
			<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
			<ul></ul>
		</div>
	<div class="body"style="max-width: 500px !important;min-width: 480px !important;">
	<form id="validateForm" action="helpFeedBack/reply" method="post" onsubmit="return false;"> 
			<input type="hidden" id="feedId" name="id" value="${feed.id}" />
			<div class="tabContent">
				<table class="inputTable">
				<tr>
					 <th>
						<span>用户名</span>
					</th>
					<th>${feed.member.username}</th>
				</tr>
				<tr>
					<th>
						<span>手机号</span>
					</th>
					<th>${feed.member.mobile}</th>
				</tr>
				<tr>
					<th>
						<span>订单编号</span>
					</th>
					<th>${feed.orderNum }</th>
				</tr>
				<tr>
					<th>
						<span>问题标题</span>
					</th>
					<th>
					${feed.question.title }</th>
				</tr>
				<tr>
					<th>
						<span>问题类型</span>
					</th>
					<th><c:if test="${feed.question.questionType == 0}">洗衣订单</c:if>
						<c:if test="${feed.question.questionType == 1}">打印订单</c:if>
						<c:if test="${feed.question.questionType == 4}">管家订单</c:if>
					</th>
				</tr>
				<tr>	
					<th>
						<span>问题简述</span>
					</th>
					<th>${feed.questionDetail}</th>
			    </tr>
				<tr>
					<th>
						<span>是否处理</span>
					</th>
					<th><c:if test="${feed.idHandle == true}">已处理</c:if>
							<c:if test="${feed.idHandle == false}">未处理</c:if></th>
				</tr>
				<tr>	
					<th>
						<span>反馈时间</span>
					</th>
					 <th><fmt:formatDate value="${feed.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</th>
				</tr>
				<tr>	
					<th>
						<span>问题答案</span>
					</th>
					 <th>
					  	<textarea name="answer" id="answer" class="formTextarea" <c:if test="${feed.idHandle==true}">disabled="disabled"</c:if> >${feed.answer}</textarea>
					</th>
				</tr>
				<tr>
					<td colspan="4">
						&nbsp;
					</td>
				</tr>
				 
				</table>
				</div>
				 
			<div class="buttonArea">
				<c:if test="${feed.idHandle==false}"><input type="submit" class="formButton" value="回复"></c:if>
				<input type="button" class="formButton" onclick="var index = parent.layer.getFrameIndex(window.name); parent.layer.close(index);" value="返  回" />
			</div>
			</form>
	</div>
</body>
</html>
