<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:useBean id="nowDate" class="java.util.Date" />
<%@ include file="taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>出厂衣物</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="<%=basePath%>/resource/admin/css/base.css" rel="stylesheet"
	type="text/css" />
<link href="<%=basePath%>/resource/admin/css/admin.css" rel="stylesheet"
	type="text/css" />
<%-- <script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.js"></script> --%>
 <script type="text/javascript"
	src="<%=basePath%>/resource/common/js/jquery-1.11.1.min.js"></script>

<script type="text/javascript"
	src="<%=basePath%>/resource/common/js/jquery.pager.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/resource/admin/js/base.js"></script>
<%-- <script type="text/javascript"
	src="<%=basePath%>/resource/admin/js/admin.js"></script> --%>

<script type="text/javascript"
	src="<%=basePath%>/resource/common/layer/layer.js"></script>


<style media="print">
.bar,.listBar,.pagerBar,.noprint {
	display: none;
}
</style>
<style type="text/css">
.list .listTable td {
	border-bottom: solid 1px #c6c9ca;
	border-right: solid 1px #c6c9ca;
	font-size: 12px;
}

.list .listTable th {
	text-align: center;
	font-size: 12px;
}

/* .listTable{*table-layout:fixed ; }
		 .list .listTable th span{padding: 0px;} */
</style>
<script>
	var $idsCheck;
	$().ready(
					function() {

						var $listForm = $("#listForm");// 列表表单

						var $allCheck = $("#listTable input.allCheck");// 全选复选框
						var $listTableTr = $("#listTable tr:gt(0)");
						var $ordercheck = $("#listTable  input[name='checkorder']");
						$idsCheck = $("#listTable  input[name='ids']");// 衣物列表复选框 
						var $nextButton = $("#nextButton");
						var idslist;
						// 全选
						$allCheck
								.click(function() {
									var $this = $(this);
									if ($this.prop("checked")) {
										idslist = $("#listTable input");
										idslist.prop("checked", true);
										/* 	$idsCheck.attr("checked", true); */
										$listTableTr = $("#listTable tr[id*='printTr']");
										$listTableTr.addClass("checked");
										$listTableTr.removeClass("noprint");
										$nextButton.prop("disabled", false);
									} else {
										/* $idsCheck.attr("checked", false); */
										idslist = $("#listTable input");
										idslist.prop("checked", false);
										$listTableTr = $("#listTable tr[id*='printTr']");
										$listTableTr.removeClass("checked");
										$listTableTr.addClass("noprint");
										$listTableTr.css("display","");
										$nextButton.prop("disabled", true);
									}
								});
						//单个订单被选时
						$ordercheck.click(function() {
							var $this = $(this);
							var index = $this.attr('id');
							if ($this.prop("checked")) {
								idslist = $("#listTable input[id=check" + index
										+ "]");
								idslist.prop("checked", true);
								/* 	$idsCheck.attr("checked", true); */

								$nextButton.prop("disabled", false);

								$listTableTr = $("#listTable tr[id='printTr"
										+ index + "']");
								$listTableTr.addClass("checked");
								$listTableTr.removeClass("noprint");
								
							} else {
								/* $idsCheck.attr("checked", false); */
								idslist = $("#listTable input[id=check" + index
										+ "]");
								idslist.prop("checked", false);
								$nextButton.prop("disabled", true);
								$listTableTr = $("#listTable tr[id='printTr"
										+ index + "']");
								$listTableTr.removeClass("checked");
								$listTableTr.addClass("noprint");
								$listTableTr.css("display","");
							}
						});
						// 单件衣物被选时
						$idsCheck
								.click(function() {

									var $this = $(this);
									if ($this.prop("checked")) {
										$this.parent().parent().addClass(
												"checked");
										$nextButton.prop("disabled", false);
										$this.parent().parent().removeClass(
												"noprint");
									} else {
										$this.parent().parent().removeClass(
												"checked");
										var $idsChecked = $("#listTable input[name='ids']:checked");
										if ($idsChecked.size() > 0) {
											$nextButton.prop("disabled", false);

										} else {
											$nextButton.prop("disabled", true);

										}
										$this.parent().parent().addClass(
												"noprint");
										$this.parent().parent().css("display","");
									}
								});

						$nextButton
								.click(function() {
									var url = $(this).attr("url");
									var $idsCheckedCheck = $("#listTable input[name='ids']:checked");
									var barcode = $(
											"#listTable input[name='ids']:checked")
											.serialize().split("&");

									var orderId = $(
											"#listTable input[name='ids']:checked")
											.next().serialize().split("&");

									for (var i = 0; i < barcode.length; ++i) {
										barcode[i] = barcode[i].substring(4,
												barcode[i].length);
										orderId[i] = orderId[i].substring(8,
												orderId[i].length);

									}

									 $.dialog({
										type : "warn",
										content : "您确定将所选都进入派送状态吗?",
										ok : "确 定",
										cancel : "取 消",
										modal : true,
										okCallback : batchDelete
									});
									function batchDelete() { 
										$.ajax({
											url : url,
											data : {
												"barcode" : barcode,
												"orderId" : orderId
											},
											type : "POST",
											dataType : "json",
											traditional: true,
											success : function(data) {
												/* if (data.status == "success") {
													
													$idsCheckedCheck.parent().parent().remove();
													for(var j=0;j<barcode.length;++j){
														
													for(var i=0;i<barCodes.length;++i) if(barCodes[i]==barcode[j]) {barCodes.splice(i,1);};
													}
													
												}
												$nextButton.attr("disabled", true);
												$allCheck.attr("checked", false);
												$idsCheckedCheck.attr("checked", false);  */
												/* $.message({type: data.status, content: data.message}); */
												location.reload();
											}
										});
									 } 
									});

						//菜单点击样式
					});
</script>

<script language="JavaScript" type="text/javascript">
	//定义浏览器页面设置———>页边距设置默认值（0.750000*25.4=19.05毫米）
	var top = '0.3937007874';
	var bottom = '0.0196850394';
	var left = '0.3937007874';
	var right = '0.3937007874';
	//调用js控件来修改IE的注册信息，设置网页打印的页眉页脚为空且把页边距(上下左右边界)修改为浏览器默认值：19.05毫米
	//设置网页打印的页眉页脚和页边距为默认值  
	var HKEY_Root, HKEY_Path, HKEY_Key;
	HKEY_Root = "HKEY_CURRENT_USER";
	HKEY_Path = "\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
    var count = 0;//总件数
	function printSet() {
		try {
			var Wsh = new ActiveXObject("WScript.Shell"); //vbscript的控件核心文件

			HKEY_Key = "header";
			Wsh.RegWrite(HKEY_Root + HKEY_Path + HKEY_Key, "洗涤跟踪交接表"); //清空注册表信息中IE的页眉

			HKEY_Key = "footer";
			Wsh.RegWrite(HKEY_Root + HKEY_Path + HKEY_Key, getNowFormatDate()+"总件数："+count); //清空注册表信息中IE的页脚
			HKEY_Key = "margin_top";
			Wsh.RegWrite(HKEY_Root + HKEY_Path + HKEY_Key, top); //键值设定--上边边界

			HKEY_Key = "margin_bottom";
			Wsh.RegWrite(HKEY_Root + HKEY_Path + HKEY_Key, bottom); //键值设定--下边边界
			HKEY_Key = "margin_left";
			Wsh.RegWrite(HKEY_Root + HKEY_Path + HKEY_Key, left); //键值设定--左边边界
			HKEY_Key = "margin_right";
			Wsh.RegWrite(HKEY_Root + HKEY_Path + HKEY_Key, right); //键值设定--右边边界
		} catch (e) {
		}

	}

	function printPreview() {
        count = 0;
		/* var e = event.srcElement; */
		$("input[id*='check']").each(function() {
			if ($(this).prop("checked")) {
				$(this).closest("tr").css('display', 'block');
				count++;
			}
	         
		});
		printSet();
		wb.ExecWB(7, 1);
		/* $("input[id*='check']").each(function() {
			$(this).closest("tr").css('display', 'none');
		}); */
		/* e.style.visibility = "visible"; */
	}
	function getNowFormatDate() {
		var date = new Date();
		var seperator1 = "-";
		var month = date.getMonth() + 1;
		var strDate = date.getDate();
		if (month >= 1 && month <= 9) {
			month = "0" + month;
		}
		if (strDate >= 0 && strDate <= 9) {
			strDate = "0" + strDate;
		}
		var currentdate = date.getFullYear() + seperator1 + month + seperator1
				+ strDate;
		return currentdate;
	}
</script>
<script>
	function showHide(arg, id) {

		if ($("#" + id).html() == "[+]") {
			$("tr[name=" + arg + "]").show();
			$("tr[name=" + arg + "]").css("display","");
			$("#" + id).html("[-]");
		} else {
			$("tr[name=" + arg + "]").hide();
			$("#" + id).html("[+]");
		}
	}
	function showPic(url) {

		var index = layer.open({
			type : 2,
			title : '订单信息',
			area : [ '640px', '480px' ],
			maxmin : true,
			content : 'http://www.leyes.me/' + url, //iframe的url
			cancel : function(index) {
				layer.close(index);
			}
		});
		/* layer.full(index); */
	}
$().ready(function(){
	$("b[name='outSou']").each(function(){ if($(this).text()!=null) $('#show'+$(this).attr('id')).html("<b style='color:red;'>有外包</b>");});
})
  function showinput(number){
	$('#value'+number).focus();
	$('#showButton'+number).css('display', 'none');
	$('#button'+number).css('display','inline');
}
  function determine(number){
	$('#button'+number).css('display','none');  
	$('#showButton'+number).css('display', 'inline');
	$('#value'+number).focus();
	 $.ajax({
		url:'clothesOrder/modifyFactoryRemark',
		data:{"orderNumber":number,"factoryRemark":$('#value'+number).val()},
		type : "POST",
		dataType : "json",
		traditional: true,
		success : function(data) {
			 if(data.status=='success')
				 layer.msg('修改成功');
			 else layer.msg('修改失败');
		}
	}); 
  }
  function saveFactoryMatters(orderNumber,childbarCode){
	  $.ajax({
			url:'clothesOrder/modifyFactoryMatter',
			data:{"orderNumber":orderNumber,"childBarCode":childbarCode,factoryMatter:$('#value'+childbarCode).val()},
			type : "POST",
			dataType : "json",
			traditional: true,
			success : function(data) {
				 if(data.status=='success')
					 layer.msg('修改成功');
				 else layer.msg('修改失败');
			}
		}); 
  }
</script>
</head>

<body class="list">
	<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" id="wb"
		name="wb" style="display: none;"></OBJECT>
	<div class="bar">已分拣订单列表&nbsp;总记录数: 页&nbsp;&nbsp;</div>
	<div class="body">
		<form id="listForm" action="clothesOrder/waitfactorylist"
			method="post">
			<div class="listBar">
				<label>查找: </label> 子条形码： <input id="barCode" type="text"
					name="barCode" value="${barCode }" /> 用户手机号码： <input type="text"
					name="phoneNumber" value="${phoneNumber }" /> <input type="submit"
					id="searchButton" class="formButton" value="搜 索" />
				<!-- <label>每页显示: </label> 18条 -->
			</div>


			<table id="listTable" class="listTable">
				<tr class="noprint">
					<th colspan="1" class="check"><input type="checkbox"
						class="allCheck" /></th>
					<th colspan="2"><span>用户姓名</span></th>
					<th colspan="3"><span>用户号码</span></th>
					<th colspan="1"><span>图片</span></th>
					<th colspan="3"><span>小哥备注</span></th>
					<th colspan="3"><span>工厂备注</span></th>
					<th colspan="2"><span>是否有外包</span></th>
					<th colspan="2"><span>合计(件)</span></th>
					<th colspan="4"><span>送回地址</span></th>
					<th colspan="3"><span>下单时间</span></th>
					<th colspan="2"><span>存留总时长（小时）</span></th>
				</tr>

				<c:forEach items="${clothes}" var="clothes" varStatus="status" >
					<tr style="background-color: #C8F5F5;" class="noprint">
						<td colspan="1" class="checkitem"><input type="checkbox"
							name="checkorder" id="${status.index}" /></td>
						<td colspan="2"><c:if test="${!empty clothes.clothesList}">
								<a id="${clothes.orderNumber}"
									href="javascript:showHide('${clothes.childBarCode}','${clothes.orderNumber}')">[+]</a>&nbsp;&nbsp;</c:if>
							${clothes.userName}</td>
						<td colspan="3">${clothes.phoneNumber}</td>
						<td colspan="1">
						<a href="javascript:showPic('/lk_clothes/${clothes.imgUrl}')">[图片]</a>
							<c:if test="${!empty clothes.outImageUrl}">
							  <a href="javascript:showPic('/lk_clothes/${clothes.outImageUrl}')">[外包图]</a>
							</c:if>
						</td>
						<td colspan="3">${clothes.desc}</td>
						<td colspan="3" ><input type="text" id="value${clothes.orderNumber}" style="border:none;background:rgba(0,0,0,0)" value="${clothes.factoryRemark}"/>
						<a id="showButton${clothes.orderNumber}" onclick="showinput('${clothes.orderNumber}')">[修改]</a><a style="display:none;" id="button${clothes.orderNumber}" onclick="determine('${clothes.orderNumber}')">[确定]</a></td>
						<td colspan="2" id="show${status.index}">${clothes.outSourcing}</td>
						<td colspan="2">${fn:length(clothes.clothesList)}</td>	
						<td colspan="4">${clothes.sendAddress}</td>
						<td colspan="3">${clothes.createTime}</td>
                        
						<fmt:parseDate var="someDate" value="${clothes.createTime}"
							pattern="yyyy-MM-dd HH:mm:ss" />
						<c:set var="interval" value="${nowDate.time - someDate.time}" />
						<c:choose>
							<c:when test="${interval/1000/60/60>=72}">
								<td colspan="2" id="time" style="color: red;"><b><fmt:formatNumber
											value="${interval/1000/60/60}" pattern="#0.0" /></b></td>
							</c:when>
							<c:when test="${interval/1000/60/60>=48}">
								<td colspan="2" style="color: #E9C341;"><b><fmt:formatNumber
											value="${interval/1000/60/60}" pattern="#0.0" /></b></td>
							</c:when>
							<c:otherwise >
								<td colspan="2" style="color: green;"><b><fmt:formatNumber
											value="${interval/1000/60/60}" pattern="#0.0" /></b></td>
							</c:otherwise>
						</c:choose>
					</tr>


					<c:if test="${!empty clothes.clothesList}">

						<tr name="${clothes.childBarCode}" style="display: none;"
							class="noprint">
							<td colspan="1"></td>
							<td colspan="1"></td>
							<td colspan="2">送回地址</td>
							<td colspan="1">用户姓名</td>
							<td colspan="3">衣物名</td>
							<td colspan="3">品牌</td>
							<td colspan="1">订单号</td>
							<td colspan="1">订单条码</td>
							<td colspan="3">子条码</td>
							<td colspan="4">配件</td>
							<td colspan="2" >是否外包</td>
							<td colspan="2" >注意事项</td>
							<td colspan="2" >小哥签字</td>
							<!-- <td colspan="1" style="display:none;">检查岗</td>
							<td colspan="1" style="display:none;">司机</td>
							<td colspan="1" style="display:none;">小哥</td> -->
						</tr>

						<c:forEach items="${clothes.clothesList}" var="clothesList" >

							<tr name="${clothes.childBarCode}" id="printTr${status.index}"
								style="display: none;" class="noprint">
								<td colspan="1" class="noprint"></td>
								<td colspan="1" class="checkitem noprint"><input
									type="checkbox" name="ids" value="${clothesList.childBarCode}"
									id="check${status.index}"> <input type="checkbox"
									value="${clothes.clothesOrderId}" name="orderId" checked="true"
									style="display: none;"></td>
								<td colspan="2">${clothes.sendAddress}</td>
								<td colspan="1">${clothes.userName}</td>
								<td colspan="3">${clothesList.clothesName}</td>
								<td colspan="3">${clothesList.brand}</td>
								<td colspan="1">${clothes.orderNumber}</td>
								<td colspan="1">${clothes.childBarCode}</td>
								<td colspan="3">${clothesList.childBarCode}</td>
								<td colspan="4">${clothesList.partsDesc} </td>
								<c:choose>
									<c:when test="${clothesList.outSourcing==1}">
										<td colspan="2" style="color: red; text-align:center"><b id="${status.index}" name="outSou">外包</b></td>
									</c:when>
									<c:otherwise>
										<td colspan="2"></td>
									</c:otherwise>
								</c:choose>
								<td colspan="2" ><textarea id="value${clothesList.childBarCode}" rows="2"  style="border:none;overflow:hidden;padding:0;margin:0;height: 100%;">${clothesList.outDesc}</textarea>
								<a onclick="saveFactoryMatters('${clothes.orderNumber}','${clothesList.childBarCode}')" class="noprint">[保存]</a></td>
                                <td colspan="2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;</td>
                             </tr>

						</c:forEach>

					</c:if>

				</c:forEach>
			</table>

			<div class="pagerBar">
				<div class="delete">
					<input type="button" id="nextButton" class="formButton"
						url="clothesOrder/directToOutFactory" value="下一步" />
					<button type="button" onclick="printPreview();" id="printBtn"
						class="formButton">打印预览</button>

				</div>
				<div class="pager">
					<%@ include file="pager.jsp"%>
				</div>
			</div>
		</form>
	</div>


</body>
</html>
