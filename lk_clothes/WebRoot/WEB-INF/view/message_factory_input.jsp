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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=basePath%>/resource/admin/css/base.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/resource/admin/css/admin.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.validate.methods.js"></script>
		
		<script type="text/javascript" src="<%=basePath%>/resource/common/editor/kindeditor.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/editor/lang/zh_CN.js"></script>
		
		<script type="text/javascript" src="<%=basePath%>/resource/admin/js/base.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/admin/js/admin.js"></script>
<style type="text/css">
.hidden{
display: none;
}
.show{
display: inline-block;
}
.ke-dialog-row:nth-child(3),.ke-dialog-row:nth-child(4),.ke-dialog-row:nth-child(5){
display: none;
}
</style>
		<script>
		$(document).keydown(function(event) { 
			if (event.keyCode == 13) { 
				event.preventDefault(); 
				  } });
			$(document).ready(function(){
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
						"orderNumber":{
							required:true
						} 
					},
					messages: {
						"orderNumber":{
							required:"请输入订单编号"
						} 
					},
					submitHandler: function(form) {
						$(form).find(":submit").attr("disabled", true);
						$('#sendObject').attr("disabled",false);
						form.submit();
					}
				});
				 
				 
				var order=-1;
				$("#addContentBtn").click(function(){
					order++;
					$(".addTr").before('<tr><th></th><td><textarea class="formTextarea" name="contents['+order+'].content" placeholder="请输入一段消息内容"></textarea><input type="hidden" class="imageUrl" name="contents['+order+'].type" value="0"  /><input type="hidden" class="imageUrl" name="contents['+order+'].order" value="'+order+'"/><span class="deleteIcon delDelete" title="删 除">&nbsp;</span></td></tr>');
				});
				$("#addImgBtn").click(function(){
					order++;
					$(".addTr").before('<tr><th></th><td><input type="hidden" class="imageUrl" name="contents['+order+'].type" value="1"  /><input type="hidden" class="imageUrl" name="contents['+order+'].order" value="'+order+'"/><img class="photoImg" style="width:200px; height:150px;"/><input type="hidden" class="imageUrl" name="contents['+order+'].content" /><label class="requireField"><input type="button"  value="选择图片" onclick="uploadImge(this)"/></label><span class="deleteIcon delDelete" title="删 除">&nbsp;</span></td></tr>');
				});
				$("#addVideoBtn").click(function(){
					order++;
					$(".addTr").before('<tr><th></th><td><input type="hidden" class="imageUrl" name="contents['+order+'].type" value="2"  /><input type="hidden" class="imageUrl" name="contents['+order+'].order" value="'+order+'"/><input type="text" class="formText" name="contents['+order+'].content" placeholder="请输入视频地址"/><label class="requireField"><input type="button"  value="选择视频" onclick="uploadImge(this)"/></label><span class="deleteIcon delDelete" title="删 除">&nbsp;</span></td></tr>');
				});
				
				// 数据库 删除
				$(".delDelete").live("click", function() {
					var $this = $(this);
					$.dialog({type: "warn", content: "您确定要删除吗?", ok: "确 定", cancel: "取 消", modal: true, okCallback: deleteinfoAppendix});
					function deleteinfoAppendix() {
						$this.parent().parent().remove();
					}
				});
				  
			});
			var editor;
			KindEditor.ready(function(K) {
				editor = K.editor({
						allowFileManager : false, //允许图片管理 开启后再挑选图片的时候可以直接从图片空间内挑选
						uploadJson: "<%=basePath%>/file/factoryUpload?type=message"
				});
			    //给按钮添加click事件
				K('#imageServer').click(function() {
				
					uploadImge(this);
				});
			});
			function uploadImge(i){
				editor.loadPlugin('image', function() {
                    //图片弹窗的基本参数配置
					editor.plugin.imageDialog({
						imageWidth:100,
						imageHeight:50, 
						//点击弹窗内”确定“按钮所执行的逻辑
						clickFn : function(url, title, width, height, border, align) {
							//var u = K('#url').val(url);//获取图片地址
							if(url.indexOf("http:") == -1){
								url="<%=basePath%>"+url;
							}
							$(i).parent(".requireField").prev().prev().attr("src",url);
							
							$(i).parent(".requireField").prev().val(url);
							editor.hideDialog(); //隐藏弹窗
						}
					});
			   });
				
			}
		</script>

	</head>

	<body class="input">
		<div class="bar">	
			添加消息
		</div>
		<div id="validateErrorContainer" class="validateErrorContainer">
			<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
			<ul></ul>
		</div>
		<div class="body">
			<form id="validateForm" action='factoryReplyWashOrder' method="post">
				<table class="inputTable">
					<!-- <tr>
						<th>
							标题:
						</th>
						<td colspan="3">
							<input type="text" name="title"  placeholder="请输入标题" class="formText" required/>
							<label class="requireField">*</label>
						</td>
					</tr> -->
						<!-- <tr>
						<th>18684006668
							消息类型:
						</th>
						<td colspan="3">
						<select id="mesType" name="messageType" style="width: 192px;height: 30px;" >
							<option value="-1">请选择.</option>
							<option value="0">社区消息</option>
							<option value="1">个人消息</option>
							<option value="2">活动消息</option>
							<option value="1" id="orderMsg">订单消息</option>
						</select>	
							<label class="requireField">*</label>
						</td>
					</tr> -->
					<tr>
						<th>订单号:</th>
						<td colspan="3"> 
							<input type="text" id="orderNumber" name="orderNumber" class="formText" placeholder="请输入订单号"/>
						</td>
						
					</tr>
					<!-- <tr>
						<th>接收者手机号码:</th>
						<td><input type="text" name="mobile" class="formText" />
						<label class="requireField">*社区或者接收者二选一</label></td>
					</tr> -->
					
					 <!-- <tr >
						<th>
							预览图片:
						</th>
						<td>
							<img class="photoImg"  style="width:200px; height:150px;"/>
							<input type="hidden" class="imageUrl"  name="previewImageUrl" />
							<label class="requireField"><input type="button" id="imageServer" value="选择图片" /></label>
						</td>
					</tr>  -->
					
					<tr class="addTr">
						<th>
						<input id="addContentBtn" type="button" class="formButton" value="添加内容">  </th>
					<td colspan="3">
						<input id="addImgBtn" type="button" class="formButton" value="添加图片">
						<input id="addVideoBtn" type="button" class="formButton" value="添加视频">
					</td>
					</tr>
					<tr>
						<th>
							&nbsp;
						</th>
						<td colspan="3">
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