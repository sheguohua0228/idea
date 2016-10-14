<%@page import="com.aplus.lk.clothes.entity.AddressCenterPO"%>
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
	<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.validate.methods.js"></script>
			<script type="text/javascript" src="<%=basePath%>/resource/common/layer/layer.js"></script>
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
						"employeeId":{
							required:true
						},
						"addressCenterId":{
							required:true
						},
						
					},
					messages: {
						"employeeId":{
							required:"请选择收衣员工"
						},
						"addressCenterId":{
							required:"请选择社区"
						},
						
					},
					submitHandler: function(form) {
						$(form).find(":submit").attr("disabled", true);
						form.submit();
					}
				});
				
				var $viewMap = $("#viewMap");
				$viewMap.click(function(){
					if($("input[name='deliveryAddress']").val() == ''){
						 $.dialog({type: "warn", content: "请先输入收衣位置", ok: "确 定", cancel: "取 消", modal: true});
					}else{
						getPositon($("input[name='deliveryAddress']").val(),"成都市");
					}
				});
				
				var $employee = $("#employee");
				var $addressCenter = $("#addressCenter");
				$addressCenter.change(function(){
					$this = $(this);				
					$employee.children(":not(:first)").remove();
					if($this.length > 0){
						$.ajax({
							url : "<%=basePath%>/employee/queryByAddressCenterId",
							type: "post",
							data: {
								addressCenterId:$this.val()
							},
							dataType: "json",
							success:function(result){
								for(var i = 0 ;i < result.length; i++){
									$employee.append("<option value='"+result[i].id+"'>"+result[i].realName+"["+result[i].username+"]</option>")
								}
							}
						});
					}
				});
				
				
				$("#centerType").change(function(){
					$this = $(this);
					$addressCenter.children(":not(:first)").remove();
					$.ajax({
						url : "<%=basePath%>/addressCenter/queryByCenterType",
						type: "post",
						data: {
							centerType:parseInt($this.val())
						},
						dataType: "json",
						success:function(result){
							for(var i = 0 ;i < result.length; i++){
								$addressCenter.append("<option value='"+result[i].id+"'>"+result[i].name+"</option>");
							}
						}
					});
				});
				
			});
			
			var iMarker;
			function getPositon(address, city){
				myGeo.getPoint(address, function(point){
						if (point) {
							if(iMarker != 'undefined'){
								map.removeOverlay(iMarker);
							}
					        commMarker(point);
					    }
					}, city);
			}
			
			var myIcon = new BMap.Icon("<%=basePath%>/resource/common/images/ic_map_loca.png", new BMap.Size(25, 44), {
				offset: new BMap.Size(10, 25)// 指定定位位置
			});
			
			function commMarker(point){
				iMarker = new BMap.Marker(point, {enableDragging: true, icon: myIcon});
				// 为Marker添加移动事件
				iMarker.addEventListener('dragend', function(e){
				    $.dialog({type: "warn", content: "是否将设备位置标记在此处?", ok: "确 定", cancel: "取 消", modal: true, okCallback: markerEnter});
				    function markerEnter(){
				        $("input[name=longitude]").val(e.point.lng);
				  		$("input[name=latitude]").val(e.point.lat);
				       	buildGEOByLngLat(e.point.lng,e.point.lat);
				    }
				})
			    map.addOverlay(iMarker);
			    buildGEOByLngLat(point.lng,point.lat);
			    $("input[name=longitude]").val(point.lng);
			    $("input[name=latitude]").val(point.lat);
			}
		</script>

	</head>

	<body class="input">
		<div class="bar">	
			预约订单地址完善
		</div>
		<div id="validateErrorContainer" class="validateErrorContainer">
			<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
			<ul></ul>
		</div>
		<div class="body">
			<form id="validateForm" action="changeOrderEmployee" method="post" onsubmit="return false;">
				<table class="inputTable">
					<tr>
						<th>
							取件地址:
						</th>
						<td>
							<input type="hidden" value="${order.id}" name="orderId">
							<input type="hidden" value="${order.employeeId}" name="fromEmployeeId">
							
							
							<input type="text" name="deliveryAddress" class="formText" value="${order.managerAddress.beiDeliveryAddress}" />
							<input id="viewMap" type="button" class="formButton" value="地图定位">
							<label class="requireField">*</label>
						</td>
						<td rowspan = 10><div id="allmap" style="width:500px; height:350px;"></div></td>
					</tr>
					
					
					
					
					
					
					<tr>
						<th>
							员工:
						</th>
						<td>
							<select id="centerType">
								<option value="">社区类型..</option>
								<option value="0">主城区</option>
								<option value="1">机动区</option>
							</select>
							<select id="addressCenter" name="addressCenterId">
								<option value="">请选择社区..</option>
							</select>
							<select id="employee" name="toEmployeeId">
								<option value="">请选择员工..</option>
							</select>
							<label class="requireField">*</label>
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
					<input type="button" class="formButton" value="确  定" onclick="createOrder()"/>&nbsp;&nbsp;
					<input type="button" class="formButton" onclick="window.history.back();" value="返  回"/>
				</div>
			</form> 
		</div>
	</body>
	
	<script type="text/javascript" src="<%=basePath%>/resource/admin/js/BMap.js"></script>
	<script type="text/javascript">
		setCenterAndZoom("成都");
		<%
			List<AddressCenterPO> addressCenterList = (List<AddressCenterPO>) request.getAttribute("addressCenterList");
			for(AddressCenterPO address : addressCenterList){
		%>
				bdGEOWithOverlay('<%=address.getLongitude()%>', '<%=address.getLatitude()%>', '社区：<%=address.getName()%>', '地址：<%=address.getAddress()%>', '<%=address.getServiceRange()%>')
		<%
			}
		%>
		function createOrder(){
			var ajaxCallUrl=$("#validateForm").attr("action");
			$.ajax({
                cache: true,
                type: "POST",
                url:ajaxCallUrl,
                data:$('#validateForm').serialize(),// 你的formid
                async: false,
                error: function(request) {
                   layer.msg("请求异常");
                },
                success: function(data) {
                     if(data.status=="success"){
                    	 layer.msg("订单改派成功");
                    	 setTimeout(function(){
                    		 history.back();
                    	 },1500);
                     }else{
                    	 layer.msg("改派失败");
                     }
                }
            });
		}
	</script>

</html>