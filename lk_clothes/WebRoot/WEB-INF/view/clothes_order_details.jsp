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
	    <title>洗衣订单查询</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<link rel="icon" href="favicon.ico" type="image/x-icon" />
		<link href="<%=basePath%>/resource/admin/css/base.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/resource/admin/css/admin.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/admin/js/base.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/admin/js/admin.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/layer/layer.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/audio/audio.min.js"></script>
		
	</head>
	<script type="text/javascript">
	function readsign(){
		$("#button").text("已读");
	}
	$().ready( function() {
		 $(document).keydown(function(e){
		     var target = e.target ;
		     var tag = e.target.tagName.toUpperCase();
		     if(e.keyCode == 8){
		      if((tag == 'INPUT' && !$(target).attr("readonly"))||(tag == 'TEXTAREA' && !$(target).attr("readonly"))){
		       if((target.type.toUpperCase() == "RADIO") || (target.type.toUpperCase() == "CHECKBOX")){
		        return false ;
		       }else{
		        return true ; 
		       }
		      }else{
		       return false ;
		      }
		     }
		 });
	
	if("${clothesOrder.orderStatus}"=="3"){
		var isWash=true;
		$('.eachStatus').each(function(){
			if($(this).val()<4){
				isWash=false;
			}
		});
		if(isWash){
			$("#submitBtn").show();
		}else{
			$("#submitBtn").hide();
		}
	}
	 
	
		var index = parent.layer.getFrameIndex(window.name);
		$("#closeBtn").click(function(){
			 parent.layer.close(index);
		});
		
		$validateForm = $("#validateForm");
		$validateForm.submit(function(){
		    var isSuccess = true;
			$(".childBarCode").each(function(){
				var codeValue = $(this).val();
				if(codeValue == null || codeValue.length == 0){
					layer.tips('请输入子条码',$(this));
					isSuccess = false;
				}else{
					validateChildBarCode(codeValue);
				}
			});
			if(isSuccess){
				$("#submitBtn").attr("disabled", true);
			}
			return isSuccess;
		});
		
		//$(".childBarCode")
		var barCode = $(".childBarCode");
		if(barCode.length > 0){
			barCode.get(0).focus();
			barCode.keydown(function(e){
				if(e.keyCode == 13){ // 监听回车按钮
					var nextInput = $(this).parent().parent().next().find(".childBarCode");
					if(nextInput == null || nextInput.length <= 0){
						$(this).blur();
					}else{
						nextInput.focus();
					}
				}
			});
			// 失去焦点，验证子条形码是否存在
			barCode.blur(function(){
				$this = $(this);
				var childBarCode = $this.val();
				if(childBarCode.length > 0){
					validateChildBarCode(childBarCode);
				}
			});
		}
		
		function validateChildBarCode(childBarCode){
// 			var arr = $.makeArray($(".childBarCode[va]"));
// 			console.log("验证:"+arr+"    "+childBarCode);
			$.getJSON("clothesOrder/checkChildBarCode?childBarCode="+childBarCode,function(result){
				if(!result || result == "false"){
					layer.tips('子条码已经存在，请重新输入',$this);
					$this.val("");
					$this.focus();
					isSuccess = false;
				}
			});
		}
		
		$("#submitBtn").click(function(){
	
			layer.confirm('是否确认提交?', {
			    btn: ['确定','取消'] //按钮
			}, function(){
				$("#validateForm").submit();
			}, function(){});
			//$("#validateForm").submit();
		});
		
		audiojs.events.ready(function() {
    		audiojs.createAll();
  		});
  		
		$pushBtn = $("#pushBtn");
		if($pushBtn.length > 0){
			$pushBtn.click(function(){
				var pushContent = $("#pushContent").val();
				if(pushContent.length <= 0){
					layer.msg("请输入回复内容");
				}else{
					$.ajax({
						url:"clothesOrder/processRemark",
						type:"post",
						data:{
							orderId:$("input[name='clothesOrderId']").val(),
							memberId: $("#memberId").val(),
							pushContent:pushContent
						},
						dataType:"json",
						success:function(result){
							if(result.status == 'success'){
								layer.msg("回复成功");
								$(".remarkVoice").remove();
								$("input[name='isProccessed']").val(true);
								
								if(barCode.length > 0){
									barCode.get(0).focus();
								}
								
							}else{
								layer.msg(result.message);
							}
						}
					});
				}
			});
		}
		
		washFun =function(){
			
			
			if($("#button").text()=="未读") {layer.msg("请先确认特殊要求备注");return false;}
			var _this=this;
			$.ajax({
				type:"post",
				url:"clothesOrder/washSingleClothes",
				data:{clothesOrderId:$("input[name='clothesOrderId']").val(),
					childBarCode:$(_this).attr("value")},
				success:function(result){
					if (result.status == "success") {
						$(_this).parent().prev(".clothesWashStatus").html("[ 已洗涤 ]");
						/* if(result.data==true){
							$.dialog({type: "warn", content: "所有衣服已洗涤，是否上传照片吗?",
									ok: "确 定", cancel: "取 消", modal: true,
									okCallback: uploadClothesImage});
							function uploadClothesImage(){
								$("#submitBtn").show();
								//$(_this).hide();
							} */
						//}else{
							$.message({type: result.status, content: result.message});
							$(_this).unbind("click",washFun);
							//$(_this).hide();
							$(_this).html("派送中");
							$(_this).addClass("sendClothes").removeClass("washClothes");
							$(_this).bind("click",sendFun);
					//	}
					}
				},
				error:function(){
					layer.msg("网络异常");
				}
			});
		}
		/* 点击已洗涤 */
		$(".washClothes").bind("click",washFun);
		
		sendFun=function(){
			var _this=this;
			$.ajax({
				type:"post",
				url:"clothesOrder/sendSingleClothes",
				data:{clothesOrderId:$("input[name='clothesOrderId']").val(),
					childBarCode:$(_this).attr("value")},
				success:function(result){
					if (result.status == "success") {
						$(_this).parent().prev(".clothesWashStatus").html("[ 派送中 ]");
						$.message({type: result.status, content: result.message});
						$(_this).hide();
						/* $(_this).unbind("click",sendFun);
						
						$(_this).html("已完成");
						$(_this).addClass("finishClothes").removeClass("sendClothes");
						$(_this).bind("click",finishFun); */
					}
					if(result.data==true) $("#submitBtn").show();
				},
				error:function(){
					layer.msg("网络异常");
				}
			});
		}
		/* 点击派送中 */
		$(".sendClothes").bind("click",sendFun);
		
		finishFun=function(){
			var _this=this;
			$.ajax({
				type:"post",
				url:"clothesOrder/finishSingleClothes",
				data:{clothesOrderId:$("input[name='clothesOrderId']").val(),
					childBarCode:$(_this).attr("value")},
				success:function(result){
					if (result.status == "success") {
						$(_this).parent().prev(".clothesWashStatus").html("[ 已完成 ]");
						$.message({type: result.status, content: result.message});
						$(_this).hide();
					}
					
					
				},
				error:function(){
					layer.msg("网络异常");
				}
			});
		}
		/* 点击已完成 */
		$(".finishClothes").one("click",finishFun);
	});
	
	</script>
	<style>
			
		body{
			min-width: 500px;
		}

		.falseIcon {
	    width: 24px;
	    height: 24px;
	    line-height: 24px;
	    display: inline-block;
	    margin-top:15px;
	}
	</style>
	<body class="input admin">
	<div class="body">
		<form id="validateForm" <c:if test="${clothesOrder.orderStatus == 1}">action="clothesOrder/collectToFactory"</c:if>
			  	<c:if test="${clothesOrder.orderStatus == 2}">action="clothesOrder/factoryToWash"</c:if>
			  	<c:if test="${clothesOrder.orderStatus == 3}">action="clothesOrder/washToSending"</c:if>
					method="post" enctype="multipart/form-data">
			<input type="hidden" name="clothesOrderId" value="${clothesOrder.id}" />
			<input type="hidden" name="isProccessed" value="false">
			<div class="tabContent">
				<table class="inputTable">
				<tr>
					<th>
						订单状态: 
					</th>
					<td colspan="3">
						<span class="red">
							[<c:choose>
								<c:when test="${clothesOrder.status == 0 }">未处理</c:when>
								<c:when test="${clothesOrder.status == 1 }">已处理</c:when>
								<c:when test="${clothesOrder.status == 2 }">已完成</c:when>
								<c:when test="${clothesOrder.status == 3 }">已作废</c:when>
							</c:choose>]
							
							[<c:choose>
								<c:when test="${clothesOrder.orderStatus == 0 }">预约</c:when>
								<c:when test="${clothesOrder.orderStatus == 1 }">已收衣</c:when>
								<c:when test="${clothesOrder.orderStatus == 2 }">已分拣</c:when>
								<c:when test="${clothesOrder.orderStatus == 3 }">已洗涤</c:when>
								<c:when test="${clothesOrder.orderStatus == 4 }">派送中</c:when>
								<c:when test="${clothesOrder.orderStatus == 5 }">已完成</c:when>
							</c:choose>]
							[<c:choose>
								<c:when test="${clothesOrder.payStatus == 0 }">未支付</c:when>
								<c:when test="${clothesOrder.payStatus == 1 }">支付成功</c:when>
								<c:when test="${clothesOrder.payStatus == 2 }">支付失败</c:when>
							</c:choose>]
						</span>
					</td>
				</tr>
				
				<tr>
					<th>订单编号：</th>
					<td colspan="3">${clothesOrder.orderNumber}</td>
				</tr>
				<tr>
					<th>下单时间：</th>
					<td colspan="3">
						<fmt:formatDate value="${clothesOrder.createTime}" pattern="yyyy-MM-dd HH:ss:mm"/>
					</td>
				</tr>
				<c:if test="${clothesOrder.orderStatus==3 }">
				<tr>
					<th>
						客户姓名: 
					</th>
					<td>
						${clothesOrder.clothesAddress.username}
					</td>
					<th>
						电话: 
					</th>
					<td>
						${clothesOrder.clothesAddress.phoneNumber}
					</td>
					
				</tr>
				<tr>
					<th>
						收衣地址: 
					</th>
					<td>
							<c:if test="${clothesOrder.takeAddress==null}">
							${clothesOrder.clothesAddress.deliveryAddress}
						</c:if>
						<c:if test="${clothesOrder.takeAddress!=null}">
							${clothesOrder.takeAddress}
						</c:if>
					</td>
					<th>
						送衣地址: 
					</th>
					<td>
						<c:if test="${clothesOrder.backAddress==null}">
							${clothesOrder.clothesAddress.sendAddress}
						</c:if>
						<c:if test="${clothesOrder.backAddress!=null}">
							${clothesOrder.backAddress} 
						</c:if>
					</td>
				</tr>
				<tr>
					<th>收件/派件人姓名:</th>
					<td>${clothesOrder.employee.realName }&nbsp;[${clothesOrder.employee.username}]</td>
					<th>联系电话:</th>
					<td>${clothesOrder.employee.phone }</td>
				</tr>
				</c:if>
				<sec:authorize ifAnyGranted="ROLE_CLOTHES_CANCLE_ORDER">
				<tr>
					<th>订单总金额：</th>
					<td colspan="3">${clothesOrder.price}￥</td>
				</tr>
				</sec:authorize>
				<tr>
					<th>附件上传：</th>
					<td colspan="3"><input type="file" name="imageUpload" style="width:300px;"></td>
				</tr>
				<tr>
					<th>小哥的备注：</th>
					<td colspan="3">${clothesOrder.desc }</td>
				</tr>
				<c:if test="${!empty clothesOrder.remarkVoiceUrl}">
				<tr class="remarkVoice">
					<th>用户留言：</th>
					<td colspan="2">
						<audio src="<%=basePath%>${clothesOrder.remarkVoiceUrl}" preload="auto" />
					</td>
				</tr>
				</c:if>
				<c:if test="${clothesOrder.orderStatus == 1 && !empty clothesOrder.remarkVoiceUrl && !clothesOrder.isProcessedOfRemark}">
				<tr class="remarkVoice">
					<td colspan="2">
						<audio src="<%=basePath%>${clothesOrder.remarkVoiceUrl}" preload="auto" />
					</td>
				</tr>
				<tr class="remarkVoice">
					<th>回复用户的留言：</th>
					<td>
						<input id="pushContent" type="text" class="formText">
						<input type="hidden" id="memberId" value="${clothesOrder.userId}" />
						<input id="pushBtn" type="button" class="formButton" value="回 复" />
					</td>
				</tr>
				</c:if>
				<tr>
					<th>工厂的备注：</th>
					<td colspan="3">${clothesOrder.factoryRemark}</td>
					
				</tr>
				<tr>
				    <th ><button id="button" type="button" onclick="readsign()">未读</button></th>
				    <td colspan="3" style="color:red;">请确认特殊邀请是否解决</td>
				</tr>
				<tr>
					<td colspan="2">
						洗衣信息
					</td>
				</tr>
				</table>
			</div>
			<table class="inputTable tabContent">
			<tr class="title">
				<th>名称</th>
				<th>件数</th>
				<!-- <th>尺寸</th> -->
				<th>条码</th>
			<c:if test="${clothesOrder.orderStatus==3 }"><th>洗涤状态</th><th>操作</th></c:if>	
			</tr>
			<c:if test="${!empty clothesOrder.washClothesSet}">
				<c:forEach items="${clothesOrder.washClothesSet}" var="clothes" varStatus="status">
					<tr>
						<td>
							<input name="washClothesList[${status.index}].id" type="hidden" value="${clothes.id}" />
							${clothes.clothesName}
						</td>
						<td>
							<c:if test="${clothes.amount==0 }">
									${clothes.number}</c:if>
									<c:if test="${clothes.amount!=0 }">
									${clothes.amount }${clothes.size }
									</c:if><%-- ${clothes.unit } --%>
						</td>
						<%-- <td>${clothes.amount}${clothes.size }</td> --%>
						<td>
							<c:if test="${clothesOrder.orderStatus == 1 }">
								<input name="washClothesList[${status.index}].childBarCode" type="text" value="${clothes.childBarCode }" class="formText childBarCode"/>
							</c:if>
							<c:if test="${clothesOrder.orderStatus == 2 || clothesOrder.orderStatus == 3}">
								${clothes.childBarCode }
							</c:if>
						</td>
						<c:if test="${clothesOrder.orderStatus==3 }"><td class="clothesWashStatus">
						<input type="hidden" class="eachStatus" value='${clothes.washStatus}'>
							[<c:choose>
								<c:when test="${clothes.washStatus == 0 }">未洗涤</c:when>
								<c:when test="${clothes.washStatus == 1 }">洗涤中</c:when>
								<c:when test="${clothes.washStatus == 2 }">返洗中</c:when>
								<c:when test="${clothes.washStatus == 3 }">已洗涤</c:when>
								<c:when test="${clothes.washStatus == 4 }">派送中</c:when>
								<c:when test="${clothes.washStatus == 5 }">已完成</c:when>
							</c:choose>]
							<td>
								<c:choose>
									<c:when test="${clothes.washStatus == 1 or clothes.washStatus==2 }">
										<button type="button" class="formButton washClothes" value="${clothes.childBarCode }">已洗涤</button>
									</c:when>
									<c:when test="${clothes.washStatus==3}">
										<button type="button" class="formButton sendClothes" value="${clothes.childBarCode }">派送中</button>
									</c:when>
									<%-- <c:when test="${clothes.washStatus == 4 }">
										<button type="button" class="formButton finishClothes" value="${clothes.childBarCode }">已完成</button>
									</c:when> --%>
								</c:choose>
							</td>
							 
						</c:if>	
					</tr>
				</c:forEach>
			</c:if>
			</table>
			<div class="buttonArea">
				<input id="submitBtn" type="button" class="formButton" value="确 定" />
				<input id="closeBtn" type="button" class="formButton" value="关 闭" />
			</div>
		</form>
	</div>
</body>
</html>
