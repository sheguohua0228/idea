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
		<title></title>
		
		<link href="<%=basePath%>/resource/admin/css/base.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/resource/admin/css/admin.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.validate.methods.js"></script>
		
		<script type="text/javascript" src="<%=basePath%>/resource/common/editor/kindeditor.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/editor/lang/zh_CN.js"></script>
		
		<script type="text/javascript" src="<%=basePath%>/resource/admin/js/base.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/admin/js/admin.js"></script>

		<script>

			$().ready(function(){
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
						"name":{
							required:true
						},
						"alias":{
							required:true
						},
						"type":"required",
						"originalPrice":{
							required:true,
							digits:true
						},
						"discountPrice":{
							required:true,
							digits:true
						}
					},
					messages: {
						"name":{
							required:"请输入衣服名"
						},
						"alias":{
							required:"请输入别名"
						},
						"type":"请选择衣服类型",
						"originalPrice":{
							required:"请输入原价",
							digits:"衣服原价只能为非负整数"
						},
						"discountPrice":{
							required:"请输入折后价",
							digits:"折后价只能为非负整数"
						}
					},
					submitHandler: function(form) {
						$(form).find(":submit").attr("disabled", true);
						form.submit();
					}
				});
				
				$discountPrice = $("input[name='discountPrice']");
				$("input[name='originalPrice']").blur(function(){
					$this = $(this);
					if($discountPrice.val().length == 0){
						$discountPrice.val($this.val());
					}
				});
				
			})
			
			KindEditor.ready(function(K) {
				var editor = K.editor({
						allowFileManager : false, //允许图片管理 开启后再挑选图片的时候可以直接从图片空间内挑选
						uploadJson: "<%=basePath%>/file/upload?type=clothes"
				});
			    //给按钮添加click事件
				K('#imageServer').click(function() {
					editor.loadPlugin('image', function() {
	                        //图片弹窗的基本参数配置
							editor.plugin.imageDialog({
								imageWidth:100,
								imageHeight:50, 
								//点击弹窗内”确定“按钮所执行的逻辑
								clickFn : function(url, title, width, height, border, align) {
									//var u = K('#url').val(url);//获取图片地址
									$("#photoImg").attr("src","<%=basePath%>"+url);
									$("#imageUrl").val(url);
									editor.hideDialog(); //隐藏弹窗
								}
							});
					   });
				});
			});
			
		</script>

	</head>

	<body class="input">
		<div class="bar">	
			<c:if test="${!empty clothesPrice}">修改</c:if><c:if test="${empty clothesPrice}">添加</c:if>洗衣价格
		</div>
		<div id="validateErrorContainer" class="validateErrorContainer">
			<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
			<ul></ul>
		</div>
		<div class="body">
			<form id="validateForm" <c:if test="${empty clothesPrice}">action="save"</c:if><c:if test="${!empty clothesPrice}">action="update"</c:if> method="post">
				<c:if test="${!empty clothesPrice}">
					<input name="id" type="hidden" value="${clothesPrice.id }">
				</c:if>
				<table class="inputTable">
					<tr>
						<th>
							示例图片:
						</th>
						<td>
							<img id = "photoImg"  <c:if test="${!empty clothesPrice && !empty clothesPrice.imageUrl }">src="${clothesPrice.imageUrl}"</c:if> style="width:200px; height:150px;" />
							<input type="hidden" id="imageUrl"  name="imageUrl" value="${clothesPrice.imageUrl }" />
							<label class="requireField"><input type="button" id="imageServer" value="选择图片" /></label>
						</td>
					</tr>
					
					<tr>
						<th>
							衣服类型名称:
						</th>
						<td>
							<input type="text" name="name" value="${clothesPrice.name}" class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr>
					
					<tr>
						<th>
							别名:
						</th>
						<td>
							<input type="text" name="alias" value="${clothesPrice.alias}" class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr>

					<tr>
						<th>类型:</th>
						<td>
							<select name="type" >
								<option value="">请选择.</option>
								<option value="0" <c:if test="${!empty clothesPrice && clothesPrice.type == 0}">selected</c:if>>上装</option>
								<option value="1" <c:if test="${!empty clothesPrice && clothesPrice.type == 1}">selected</c:if>>下装配饰</option>
								<option value="2" <c:if test="${!empty clothesPrice && clothesPrice.type == 2}">selected</c:if>>居家用品</option>
								<option value="3" <c:if test="${!empty clothesPrice && clothesPrice.type == 3}">selected</c:if>>熨烫</option>
								<option value="4" <c:if test="${!empty clothesPrice && clothesPrice.type == 4}">selected</c:if>>奢侈品护理</option>
								<option value="5" <c:if test="${!empty clothesPrice && clothesPrice.type == 5}">selected</c:if>>鞋包</option>
							</select>
						</td>
					</tr>

					<tr>
						<th>
							原价:
						</th>
						<td>
							<input type="text" name="originalPrice" value="${clothesPrice.originalPrice}" class="formText">
							<label class="requireField">*</label>
						</td>
					</tr>
					<tr>
						<th>
							折后价:
						</th>
						<td>
							<input type="text" name="discountPrice" value="${clothesPrice.discountPrice}" class="formText">
							<label class="requireField">*</label>
						</td>
					</tr>
					<tr>
						<th>
							描述
						</th>
						<td>
							<textarea name="desc" class="formTextarea">${clothesPrice.desc}</textarea>
						</td>
					</tr>
					<tr>
						<th>
							&nbsp;
						</th>
						<td>
							&nbsp;
						</td>
					</tr>
				</table>
				<div class="buttonArea">
					<input type="submit" class="formButton" value="确  定"/>&nbsp;&nbsp;
					<input type="button" class="formButton" onclick="window.history.back();" value="返  回"/>
				</div>
			</form>
		</div>
	</body>

</html>