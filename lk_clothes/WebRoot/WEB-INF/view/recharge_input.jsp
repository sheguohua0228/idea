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
            function showblock(){
            	
            	$("#jin").replaceWith("<tr id='jin'><th>使用条件:</th><td><input type='text' name='useCondition'   class='formText' /><label class='requireField'> *</label></td></tr>");
            	$("#jia").replaceWith("<tr id='jia'><th>优惠金额:</th><td><input type='text' name='discountPrice'   class='formText' /><label class='requireField'> *</label></td></tr>");
          		$("#jia").after("<tr id='sence'><th>使用场景:	</th><td><input type='radio' name='useType' value='0' checked='checked' />全场通用<input type='radio' name='useType' value='1' />打印支付<input type='radio' name='useType' value='2' />洗衣支付<input type='radio' name='useType' value='3' />管家支付<label class='requireField'>*</label></td></tr>");
            }
            function resetblock(){
            	$("#jin").replaceWith("<tr id='jin'><th>充值金额:</th><td><input type='text' name='denomination'   class='formText' /><label class='requireField'> *</label></td></tr>");
            	$("#jia").replaceWith("<tr id='jia'><th>价格:</th><td><input type='text' name='price'   class='formText' /></td></tr>");
            	$("#jia").next("#sence").remove();
            }
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
						"invalidTime":{
							required:true
						},"num":{
							required:true
						},"time":{
							required:true
						}
					},
					messages: {
						"useCondition":{
						    required: "请输入优惠条件"
						},
						"discountPrice":{
						    required: "请输入优惠金额"
						},
						"denomination":{
							required: "请输入金额"
						},
						"startTime":{
							required:"请输入有效时间"
						},
						"invalidTime":{
							required:"请选择过期时间"
						}
						,"num":{
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
			添加优惠卷/充值卡
		</div>
		<div id="validateErrorContainer" class="validateErrorContainer">
			<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
			<ul></ul>
		</div>
		<div class="body">
			<form id="validateForm" action="save" method="post">
				 
				<table class="inputTable">
					<tr>
						<th>
							充值类型:
						</th>
						<td>
							<input type="radio" name="rechargeType" value="0" onclick="javascript:resetblock()"/>积分
							<input type="radio" name="rechargeType" value="1" onclick="javascript:resetblock()"/>余额
							<input type="radio" name="rechargeType" value="2" onclick="javascript:showblock()"/>优惠卡
							<label class="requireField">*</label>
						</td>
					</tr>
					<tr id="jin">
						<th>
							充值金额:
						</th>
						<td>
							<input type="text" name="denomination"   class="formText" />
							<label class="requireField">*</label>
						</td>
					</tr>
					<tr id="jia">
						<th>
							价格:
						</th>
						<td>
							<input type="text" name="price"   class="formText" />
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