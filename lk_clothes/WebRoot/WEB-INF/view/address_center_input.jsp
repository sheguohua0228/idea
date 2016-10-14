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
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=E40859b35337939f73f5bdd19ea5f741"></script>
		

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
						"name": {
							required: true,
							remote: "checkName<c:if test='${!empty addressCenter}'>?oldValue=${addressCenter.name}</c:if>"
						},
						"address":{
							required:true
						},
						"centerType":"required",
						"serviceRange":"required",
						"longitude":"required"
					},
					messages: {
						"name": {
							required: "请填写用户名",
							remote: "社区名已存在"
						},
						"address":{
							required:"请输入社区地址"
						},
						"centerType":"请选择社区类型",
						"serviceRange":"请输入服务范围",
						"longitude":"请在地图上标记设备位置"
					},
					submitHandler: function(form) {
						$(form).find(":submit").attr("disabled", true);
						form.submit();
					}
				});
				
				var $viewMap = $("#viewMap");
				$viewMap.click(function(){
					if($("input[name='address']").val() == ''){
						 $.dialog({type: "warn", content: "请先输入社区位置", ok: "确 定", cancel: "取 消", modal: true});
					}else{
						getPositon($("input[name='address']").val(),"成都市");
					}
				});
			});
			
			
			function getPositon(address, city){
				myGeo.getPoint(address, function(point){
						if (point) {
							if(marker != 'undefined'){
								map.removeOverlay(marker);
							}
					        commMarker(point);
					    }
					}, city);
			}
			
			function commMarker(point){
				map.clearOverlays();
				marker = new BMap.Marker(point, {enableDragging: true});
				// 为Marker添加移动事件
				marker.addEventListener('dragend', function(e){
				    $.dialog({type: "warn", content: "是否将设备位置标记在此处?", ok: "确 定", cancel: "取 消", modal: true, okCallback: markerEnter});
				    function markerEnter(){
				        $("input[name=longitude]").val(e.point.lng);
				  		$("input[name=latitude]").val(e.point.lat);
				       	buildGEOByLngLat(e.point.lng,e.point.lat);
				    }
				});
			    map.addOverlay(marker);
			    buildGEOByLngLat(point.lng,point.lat);
			    $("input[name=longitude]").val(point.lng);
			    $("input[name=latitude]").val(point.lat);
			    writeWidthOverlay(point, $("input[name=serviceRange]").val());
			}
			
			function writeWidthOverlay(point, width){
				if(width==null||width==''||width==0){
					width=600;
				}
				var circle = new BMap.Circle(point,width,{fillColor: "red", strokeColor:"blue", strokeWeight:1, strokeOpacity:0.2}); //创建圆
				map.addOverlay(circle);
			}
		</script>

	</head>

	<body class="input">
		<div class="bar">	
			<c:if test="${!empty addressCenter}">修改</c:if><c:if test="${empty addressCenter}">添加</c:if>社区地址
		</div>
		<div id="validateErrorContainer" class="validateErrorContainer">
			<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
			<ul></ul>
		</div>
		<div class="body">
			<form enctype="multipart/form-data" id="validateForm" <c:if test="${empty addressCenter}">action="save"</c:if><c:if test="${!empty addressCenter}">action="update"</c:if> method="post">
				<c:if test="${!empty addressCenter}">
					<input name="id" type="hidden" value="${addressCenter.id }">
				</c:if>
				<table class="inputTable">
					
					<tr>
						<th>
							社区名:
						</th>
						<td>
							<input type="text" name="name" value="${addressCenter.name}" class="formText" />
							<label class="requireField">*</label>
						</td>
						<td rowspan = 10><div id="allmap" style="width:400px; height:350px;"></div></td>
					</tr>

					<tr>
						<th>地址:</th>
						<td>
							<input type="text" name="address" class="formText" value="${addressCenter.address }" />
							<label class="requireField">*</label>
							<input id="viewMap" type="button" class="formButton" value="地图定位">
						</td>
					</tr>
					
					<tr>
						<th>
							经度:
						</th>
						<td>
							<input type="text" name="longitude" value="${addressCenter.longitude}" class="formText" readonly="readonly">
							<label class="requireField">*</label>
						</td>
					</tr>
					
					
					<tr>
						<th>
							纬度:
						</th>
						<td>
							<input type="text" name=latitude value="${addressCenter.latitude}" class="formText" readonly="readonly">
							<label class="requireField">*</label>
						</td>
					</tr>
					
					<tr>
						<th>类型:</th>
						<td>
							<select name="centerType">
								<option value="">请选择...</option>
								<option value="0" <c:if test="${!empty addressCenter && addressCenter.centerType == 0}">selected</c:if> >主城区</option>
								<option value="1" <c:if test="${!empty addressCenter && addressCenter.centerType == 1}">selected</c:if>>机动</option>
							</select>
							<label class="requireField">*</label>
						</td>
					</tr>

					<tr>
						<th>服务范围:</th>
						<td>
							<input type="text" name="serviceRange" class="formText" value="${addressCenter.serviceRange }" />
							<label class="requireField">*</label>
						</td>
					</tr>
					
					<tr>
						<th>
							街景图:
						</th>
						<td>
							<img  <c:if test="${!empty addressCenter.icon }"> src="${addressCenter.icon }" width="300" height="200" </c:if>>
							<input type="file" name="imageUpload" class="formText">
						</td>
					</tr>
					<tr>
						<th>
							设置: 
						</th>
						<td>
							<label>
								<input name="flag" type="checkbox" <c:if test="${!empty addressCenter && addressCenter.flag}">checked</c:if> value="true">启用
							</label>
						</td>
					</tr>
					<tr>
					<th>负责小哥</th>
					<td><select name="employeeId">
							<option value="">空余</option>
							<c:forEach items="${data.dataList }" var="item" >
								<option value="${item.id}"  <c:if test="${item.id==addressCenter.employeeId}">selected="selected"</c:if>>${item.realName }</option>
							</c:forEach>
						</select>  
					
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
	
	<script type="text/javascript" src="<%=basePath%>/resource/admin/js/BMap.js"></script>
	<script type="text/javascript">
		var address = "${addressCenter.longitude}";;
		if(address.length <= 0){
			setCenterAndZoom("成都");
		}else{
			commMarker(new BMap.Point("${addressCenter.longitude}", "${addressCenter.latitude}"));
		}
	</script>

</html>