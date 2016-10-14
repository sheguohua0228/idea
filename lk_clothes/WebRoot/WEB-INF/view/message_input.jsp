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
<style type="text/css">
.hidden{
display: none;
}
.show{
display: inline-block;
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
						"title":{
							required:true
						},
						 "messageType":{
							 required:true
						 },
						 "sendObject":{
							 required:true
						 }
					},
					messages: {
						"title":{
							required:"请输入消息标题"
						},
						 "messageType":{
							 required:"请选择消息类型"
						 },
						 "sendObject":{
							 required:"请选择发送对象"
						 }
					},
					submitHandler: function(form) {
						$(form).find(":submit").attr("disabled", true);
						$('#sendObject').attr("disabled",false);
						form.submit();
					}
				});
				$("#mesType").change(function(){
					var _this=this;
					var chooseVal= $(_this).val();
					
					if(chooseVal==0){
						$('#sendObject').val(2);
						$("#communityChoose").removeClass("hidden").addClass("show");
						$('#sendObject').attr("disabled",false);
						$('#orderNumber').removeClass("show").addClass("hidden");
						$("#sendObject option[value='4']").remove();  
					}else if($("#orderMsg").is(":selected")){
						$('#sendObject').append("<option value='4'>此订单订单用户</option>"); 
						$('#sendObject').val(4);
						$('#orderNumber').removeClass("hidden").addClass("show");
						$('#sendObject').attr("disabled",true);
						$("#communityChoose").removeClass("show").addClass("hidden");
					}else{
						$('#sendObject').attr("disabled",false);
						$('#sendObject').val(-1);
						$('#orderNumber').removeClass("show").addClass("hidden");
						$("#communityChoose").removeClass("show").addClass("hidden");
						$("#sendObject option[value='4']").remove();  
					}
				});
				$('#sendObject').change(function(){
					var chooseVal= $(this).val();
					if(chooseVal==0||chooseVal==1){
						$("#communityChoose").removeClass("show").addClass("hidden");
						$("#mobiles").removeClass("show").addClass("hidden");
					}else if(chooseVal==2){
						$("#mobiles").removeClass("show").addClass("hidden");
						$("#communityChoose").removeClass("hidden").addClass("show");
					}else if(chooseVal==3){
						$("#mobiles").removeClass("hidden").addClass("show");
						$("#communityChoose").removeClass("show").addClass("hidden");
						$("#mobiles").focus();
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
				var jsonObject=JSON.stringify('${memberInfo}');
				$("#mobiles").keyup(function(event){ 
					var _this=this;
					 if(event.which==186){
						var array= $(_this).val().split(";");
						 array.pop();
						 var vLen=array.length;
						 var lastPhone=array[vLen-1];
						 var reg =/^1[3|4|5|8|7]\d{9}$/;
						 if(!reg.test(lastPhone)){
							 alert(lastPhone+"不是手机号!");
						 }
						 if(jsonObject.indexOf(lastPhone)==-1){
							 alert(lastPhone+"不是平台用户!");
						 }
					 }
					
					  
				}); 
			});
			var editor;
			KindEditor.ready(function(K) {
				editor = K.editor({
						allowFileManager : false, //允许图片管理 开启后再挑选图片的时候可以直接从图片空间内挑选
						uploadJson: "<%=basePath%>/file/upload?type=message"
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
			<form id="validateForm" action='save' method="post">
				<table class="inputTable">
					<tr>
						<th>
							标题:
						</th>
						<td colspan="3">
							<input type="text" name="title"  placeholder="请输入标题" class="formText" value="${message.title }" required/>
							<label class="requireField">*</label>
						</td>
					</tr>
						<tr>
						<th>
							消息类型:
						</th>
						<td colspan="3">
						<select id="mesType" name="messageType" style="width: 192px;height: 30px;">
							<option value="-1">请选择.</option>
							<option value="0">社区消息</option>
							<option value="1">个人消息</option>
							<option value="2">活动消息</option>
							<!-- <option value="1" id="orderMsg">订单消息</option> -->
						</select>	
							<label class="requireField">*</label>
						</td>
					</tr>
					<tr>
						<th>群发对象:</th>
						<td colspan="3"><select id="sendObject" name="sendObject" style="width: 192px;height: 30px;" >
							<option value="">请选择..</option>
							<option value="1">全体用户</option>
							<option value="2">社区用户</option>
							<option value="3">选择用户</option>
						</select> 
							<select id="communityChoose" class="hidden" name="communityId" style="width: 192px;height: 30px;" >
								<option value="">请选择.</option>
								<c:forEach items="${idAndName}" var="community">
									<option value="${community.id}">${community.name}</option>				 		
							 	</c:forEach>
							</select>
							<span id="lesss"></span>
							<input type="text" id="mobiles" name="mobile" class="formText hidden" style="width: 95%;" placeholder="多个用英文分号隔开"/>
							<input type="text" id="orderNumber" name="orderNumber" class="formText hidden" placeholder="请输入订单号"/>
						</td>
						
					</tr>
					<!-- <tr>
						<th>接收者手机号码:</th>
						<td><input type="text" name="mobile" class="formText" />
						<label class="requireField">*社区或者接收者二选一</label></td>
					</tr> -->
					
					<%--  <tr >
						<th>
							预览图片:
						</th>
						<td>
							<img class="photoImg"  style="width:200px; height:150px;" src="${message.previewImageUrl }"/>
							<input type="hidden" class="imageUrl"  name="previewImageUrl" value="${message.previewImageUrl }" />
							<label class="requireField"><input type="button" id="imageServer" value="选择图片" /></label>
						</td>
					</tr>  --%>
					<c:if test="${!empty message.contents }"></c:if>
					
							<c:forEach items="${message.contents }" var="item">
								<c:if test="${item.contentType==0}">
								<tr>
								<th></th>
								<td>
									<textarea class="formTextarea" name="contents[${item.sort }].content" >${item.content }</textarea> 
									<input type="hidden" class="imageUrl" name="contents[${item.sort }].type" value="0" />
									<input type="hidden" class="imageUrl" name="contents[${item.sort }].order" value="${item.sort }" /> 
									<span class="deleteIcon delDelete" title="删 除">&nbsp;</span>
								</td></tr>
							</c:if>
								<c:if test="${item.contentType==1}">
								<tr>
								<th></th>
								<td>
								<input type="hidden" class="imageUrl" name="contents[${item.sort }].type" value="1" />
								<input type="hidden" class="imageUrl" name="contents[${item.sort }].order" value="${item.sort }"/> 
								<img class="photoImg" style="width: 200px; height: 150px;" src="${item.content }" /> 
								<input type="hidden" class="imageUrl" name="contents[${item.sort }].content" value="${item.content }"/> 
								<label class="requireField">
								<input type="button" value="选择图片" onclick="uploadImge(this)" /></label>
								<span class="deleteIcon delDelete" title="删 除">&nbsp;</span></td></tr>
							</c:if>
								<c:if test="${item.contentType==2}">
								<tr>
								<th></th>
								<td>
								<input type="hidden" class="imageUrl" name="contents[${item.sort }].type" value="2" /> 
								<input type="hidden" class="imageUrl" name="contents[${item.sort }].order" value="${item.sort }" /> 
								<input type="text" class="formText" name="contents[${item.sort }].content" placeholder="请输入视频地址" value="${item.content }"/> 
								<label class="requireField">
								<input type="button" value="选择视频" onclick="uploadImge(this)" /></label>
								<span class="deleteIcon delDelete" title="删 除">&nbsp;</span></td></tr>
							</c:if>
							</c:forEach>
				 
						</td>
					</tr>
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