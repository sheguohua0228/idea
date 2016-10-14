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
						"useCondition":{
						    required: true
						},
						"discountPrice":{
						    required: true
						},
						"denomination":{
							required: true
						},
						"startTime":{
							required:true
						},
						"price":{
							required:true
						},
						 "num":{
							required:true
						},"time":{
							required:true
						}
					},
					messages: {
						"useCondition":{
						    required: "请输入洗鞋次数"
						},
						"discountPrice":{
						    required: "请输入优惠金额"
						},
						"denomination":{
							required: "请输入免费洗衣次数"
						},
						"startTime":{
							required:"请输入有效时间"
						},
						"price":{
							required:"请输入免费代收次数"
						},
						 "num":{
							required:"请输入张数"
						},"time":{
							required:"请输入第几次优惠卡"
						}
					},
					submitHandler: function(form) {
						$(form).find(":submit").attr("disabled", true);
						form.submit();
					}
				});
			})
			
		</script>

	</head>

	<body class="input">
		<div class="bar">	
			添加优惠卷/充值卡(560的  包含80件夏装，8双鞋，6次代收
			1200  包含200件夏装，16双鞋，12次代收，鞋包8.1折，家居6.3折)
		</div>
		<div id="validateErrorContainer" class="validateErrorContainer">
			<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
			<ul></ul>
		</div>
		<div class="body">
			<form id="validateForm" action="../save" method="post">
				 
				<table class="inputTable">
					<tr>
						<th>
							充值类型:
						</th>
						<td>
							<input type="text" name="rechargeType"   checked="checked"/>（拼团卡）
						</td>
					</tr>
					<tr id="jin">
						<th>
							卡卷金额:
						</th>
						<td>
							<input type="text" name="discountPrice"  class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr>
					<tr>
						<th>
							生效时间:
						</th>
						<td>
							<input type="date" name="startTime" class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr>
					 <tr>
						<th>
							过期时间:
						</th>
						<td>
							<input type="date" name="invalidTime" class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr>
					 <tr>
						<th>
							免费洗衣次数:
						</th>
						<td>
							<input type="text" name="denomination" class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr>
					  <tr>
						<th>
							免费洗鞋次数:
						</th>
						<td>
							<input type="text" name="useCondition"   class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr>
					 <tr id="jin">
						<th>
							免费代收次数:
						</th>
						<td>
							<input type="text" name="price"   class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr>
					<tr id="jin">
						<th>
							精工织补次数:
						</th>
						<td>
							<input type="text" name="darnNum"   class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr>
					<tr id="jin">
						<th>
							签证照次数:
						</th>
						<td>
							<input type="text" name="visaPhotoNum"   class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr>
					<tr id="jin">
						<th>
							护照次数:
						</th>
						<td>
							<input type="text" name="passportNum"   class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr>
	
					<tr>
						<th>
							描述:
						</th>
						<td>
							<input type="text" name="remark" class="formText" />
						</td>
					</tr>
					<tr>
						<th>
							张数:
						</th>
						<td>
							<input type="number" name="num" class="formText" />
						</td>
					</tr>
					<tr>
						<th>
							第几次活动优惠卡:
						</th>
						<td>
							<input type="number" name="time" class="formText" />
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