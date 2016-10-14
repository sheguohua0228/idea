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
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.tools.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.validate.methods.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/admin/js/base.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/admin/js/admin.js"></script>
	    <object id="ArgoxPrinter" classid="clsid:AEFC7183-44DE-463c-ACEF-8CAF33B96701"codebase="ArgoxWebPrint.cab" style="display:none;">
    </object>
	</head>
	<script type="text/javascript">
	var createtime;
	
	$().ready( function() {
	
		var $tab = $("#tab");
	
		// Tab效果
		$tab.tabs(".tabContent", {
			tabs: "input"
		});
	    
		createtime=$("#createtime").val();
		createtime=createtime.substring(5,10);
	});
	
	
	function ArgoxPrinterInit(sign){
		//枚举打印机
		ArgoxPrinter.A_EnumUSB();
		//USB首选打印机
		ArgoxPrinter.A_CreateUSBPort(sign);
		// 设定度量单位为英吋或公厘。
		ArgoxPrinter.A_Set_Unit("n");
		// 设定转印模式、启动 Cutter 或 Peel、设定标籤最长的长度、改变 slash zero
		//为 normal zero、设定每印一张暂停一次。
		ArgoxPrinter.A_Set_Syssetting(2, 1, 200, 0, 0);
		ArgoxPrinter.A_Set_Sensor_Mode('c', 60);

		//这个 A_Set_Backfeed 函数可以使打印完后，吐一小段标籤纸，好让使用者观察    印出成果及容易撕裂标籤。然后，它会在印下一张时，退回到适当点，再印下一    张，当一次印数张时，只有在第一张才会往后回缩。
		ArgoxPrinter.A_Set_Backfeed(320);
		//设定热感头打印热度。 0~20
		ArgoxPrinter.A_Set_Darkness(12);
		//设定打印速度。
		ArgoxPrinter.A_Set_Speed("E");
		
		//切刀
	     ArgoxPrinter.A_Set_Cutting(1);
	}

	
	function printCode(id) {
        
		if ($("#code" + id).val().length < 1) {
			alert("没有有效的条形码！");
			return;
		}

		//当前数量
		var j = parseInt(id) + 1;
		
		if($("#brand" + id).val().lastIndexOf(" ")==$("#brand" + id).val().length-1){ ArgoxPrinterInit(1);//如果是A类品牌用1号打印机
		}else{
			ArgoxPrinterInit(2);//b类用2号打印机
		}



		ArgoxPrinter.A_Prn_Text_TrueType(35, 0, 40, "宋体", 1, 600, 0, 0, 0,
				"A1", $("#code" + id).val(), 1);
		ArgoxPrinter.A_Prn_Barcode(18, 20, 1, "e", 2, 3, 30, "B", 1, $(
				"#code" + id).val());

		var usernamel=($("#username").val().replace(/(^\s*)|(\s*$)/g,'').length>4?$("#username").val().substring(0, 4):$("#username").val().substring(0, $("#username").val().replace(/(^\s*)|(\s*$)/g,'').length));
	    var username=usernamel[0];	
	    for(var i=1;i<usernamel.length;++i) username+="*"; 

		ArgoxPrinter.A_Prn_Text_TrueType(140, 42, 20, "宋体", 1, 600, 0, 0,
				0, "A2", "姓名:" + username, 1);

		ArgoxPrinter.A_Prn_Text_TrueType(205, 42, 20, "宋体", 1, 600, 0, 0,
				0, "A3", "手机:" + $("#tel").val().substring(0, 3)+"****"+$("#tel").val().substring(7, 11), 1);

		ArgoxPrinter.A_Prn_Text_TrueType(305, 42, 20, "宋体", 1, 600, 0, 0,
				0, "A4", "数量:" + ($("table[name='print'] tr").length - 1) + "件", 1);

		var color = $("#color" + id).val();
		color=color.substring(0,color.indexOf(";"));
		
			ArgoxPrinter.A_Prn_Text_TrueType(350, 42, 20, "宋体", 1, 600, 0,
					0, 0, "A5", "颜色:" + color, 1);
		
		//color=color.lastIndexOf(';')+1;
		//alert(color);


		var sendAddress = "地址:"
				+ $("#sendAddress").val().replace("成都市", "").replace("四川省",
						"");

		if (sendAddress.length > 18) {
			ArgoxPrinter.A_Prn_Text_TrueType(140, 32, 20, "宋体", 1, 600, 0,
					0, 0, "A6", sendAddress.substring(0, 18), 1);
			
		} else {
			ArgoxPrinter.A_Prn_Text_TrueType(140, 32, 20, "宋体", 1, 600, 0,
					0, 0, "A6", sendAddress, 1);
		}
		
		ArgoxPrinter.A_Prn_Text_TrueType(350, 32, 20, "宋体", 1, 600, 0, 0,
				0, "A12", "下单时间:" + createtime, 1);

		if($("#brand" + id).val().length>18){
			ArgoxPrinter.A_Prn_Text_TrueType(140, 22, 20, "宋体", 1, 600, 0, 0,
					0, "A7", "品牌:" + $("#brand" + id).val().substring(0,18), 1);
		}else{ 
		ArgoxPrinter.A_Prn_Text_TrueType(140, 22, 20, "宋体", 1, 600, 0, 0,
				0, "A7", "品牌:" + $("#brand" + id).val(), 1);
		 } 
		
		ArgoxPrinter.A_Prn_Text_TrueType(360, 22, 20, "宋体", 1, 600, 0, 0,
				0, "A8", "小工:" + $("#employeeName").val(), 1);

		//瑕疵最大字长40
		var xia = "备注:" + $("#defective" + id).text();
		if (xia.length > 40) {
			xia = xia.substring(0, 40);
			xia = xia.substring(0, xia.lastIndexOf(';') + 1);
		}
		if (xia.length > 31) {
			ArgoxPrinter.A_Prn_Text_TrueType(140, 12, 20, "宋体", 1, 600, 0,
					0, 0, "A9", xia.substring(0, 30), 1);
			ArgoxPrinter.A_Prn_Text_TrueType(140, 2, 20, "宋体", 1, 600, 0,
					0, 0, "A10", xia.substring(30, xia.length), 1);
		} else {
			ArgoxPrinter.A_Prn_Text_TrueType(140, 12, 20, "宋体", 1, 600, 0,
					0, 0, "A9", xia, 1);
		}

		//说明字段 最长 20
		var len = 0;
		if (xia.length > 31) {
			len = xia.length - 31;
		}
		var bz = $("#custom" + id).text();
		ArgoxPrinter.A_Prn_Text_TrueType(len * 10 + 140, 2, 20, "宋体", 1,
				600, 0, 0, 0, "A11", bz.substring(0, 20), 1);

		//  输出所有资料并设定宽及高的比例和跳号次数与打印份数。
		ArgoxPrinter.A_Print_Out(1, 1, 1, 1);

		//关闭打印机
		ArgoxPrinter.A_ClosePrn();
	}
	</script>
	<style>
		.falseIcon {
	    width: 24px;
	    height: 24px;
	    line-height: 24px;
	    display: inline-block;
	    //background: url("../images/base_icon.gif") no-repeat scroll 0px -60px transparent;
	    margin-top:15px;
	}
	</style>
	<body class="input admin">
	<div class="bar">
		洗衣订单
	</div>
	<div class="body">
			<input type="hidden" name="id" value="${id}" />
			<input type="hidden" name="clothesOrder.id" value="${id}" />
			<ul id="tab" class="tab">
				<li>
					<input type="button" value="基本信息" />
				</li>
				<c:if test="${clothesOrder.orderStatus != 0 }">
				<li>
					<input type="button" value="洗衣信息" />
				</li>
				<li>
					<input type="button" value="收发记录" />
				</li>
				<c:if test="${checkViewType==1 }">
					<li>
						<input type="button" value="历史订单" />
					</li>
				</c:if>
				</c:if>
			</ul>
			<div class="tabContent">
				<table class="inputTable">
				<tr>
					<th>
						订单状态: 
					</th>
					<td colspan="3">
						<span class="red">
							[<c:choose>
								<c:when test="${clothesOrder.status == 0 }">
									未处理
								</c:when>
								<c:when test="${clothesOrder.status == 1 }">
									已处理
								</c:when>
								<c:when test="${clothesOrder.status == 2 }">
									已完成
								</c:when>
								<c:when test="${clothesOrder.status == 3 }">
									已作废
								</c:when>
							</c:choose>]
							
							[<c:choose>
								<c:when test="${clothesOrder.orderStatus == 0 }">
									预约
								</c:when>
								<c:when test="${clothesOrder.orderStatus == 1 }">
									已收衣
								</c:when>
								<c:when test="${clothesOrder.orderStatus == 2 }">
									已分拣
								</c:when>
								<c:when test="${clothesOrder.orderStatus == 3 }">
									已洗涤
								</c:when>
								<c:when test="${clothesOrder.orderStatus == 4 }">
									派送中
								</c:when>
								<c:when test="${clothesOrder.orderStatus == 5 }">
									已完成
								</c:when>
							</c:choose>]
							[<c:choose>
								<c:when test="${clothesOrder.payStatus == 0 }">
									未支付
								</c:when>
								<c:when test="${clothesOrder.payStatus == 1 }">
									支付成功
								</c:when>
								<c:when test="${clothesOrder.payStatus == 2 }">
									支付失败
								</c:when>
							</c:choose>]
						</span>
					</td>
				</tr>
				
				<tr>
					<td colspan="4">
						&nbsp;
					</td>
				</tr>
				
				<tr>
					<th>订单编号:</th>
					<td>${clothesOrder.orderNumber}</td>
					<th>下单时间:</th>
					<td>
						<fmt:formatDate value="${clothesOrder.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				
				<tr>
					<th>订单总金额:</th>
					<td>${clothesOrder.price}￥</td>
					<c:if test="${clothesOrder.payStatus == 1 }">
						<th>支付方式:</th>
						<td>
						<c:choose>
							<c:when test="${clothesOrder.payType == '0' }">
								支付宝支付
							</c:when>
							<c:when test="${clothesOrder.payType == '1' }">
								微信支付
							</c:when>
							<c:when test="${clothesOrder.payType == '2' }">
								现金支付
							</c:when>
							<c:when test="${clothesOrder.payType == '3' }">
								积分支付
							</c:when>
						</c:choose>
					</td>
				</c:if>
				</tr>
				
				
				<tr>
					<td colspan="4">
						&nbsp;
					</td>
				</tr>
				
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
					<td colspan="4">
						&nbsp;
					</td>
				</tr>
				
				<tr>
					<th>收件/派件人姓名:</th>
					<td>${clothesOrder.employee.realName }&nbsp;[${clothesOrder.employee.username}]</td>
					<th>联系电话:</th>
					<td>${clothesOrder.employee.phone }</td>
				</tr>
				
				<tr>
					<td colspan="4">
						&nbsp;
					</td>
				</tr>
				</table>
				</div>
				<c:if test="${clothesOrder.orderStatus !=0 }">
				<table class="inputTable tabContent" name="print">
					<tr class="title">
						<th>名称</th>
						<th>数量</th>
						<th>条码</th>
						<c:if test="${clothesOrder.orderStatus==3 or clothesOrder.orderStatus==4 }"><th>洗涤状态<th></c:if>	
					    <th>品牌</th>
					    <th>颜色</th>
					    <th>瑕疵</th>
					    <th>说明</th>
					    <th>操作
					    <input id="createtime" type="hidden"  value="<fmt:formatDate value="${clothesOrder.createTime}" pattern="yyyy-MM-dd HH:ss:mm"/>">  <!-- 下单时间 -->
					    <input id="fartherQrCode" type="hidden" name="fartherQrCode" value="${clothesOrder.barCode}">  <!-- 订单条形码 -->
			            <input id="tel" type="hidden" name="tel" value="${clothesOrder.clothesAddress.phoneNumber} "><!--电话号码  -->
			            <input id="username" type="hidden" name="username" value="${clothesOrder.clothesAddress.username} "><!-- 用户名 -->
			            <input id="deliveryAddress" type="hidden" name="deliveryAddress" value="${clothesOrder.clothesAddress.deliveryAddress} "><!-- 收货地址 -->
			            <input id="sendAddress" type="hidden" name="sendAddress" value="${clothesOrder.clothesAddress.sendAddress} "><!-- 送货地址 -->
			            <input id="customerRequire" type="hidden" name="customerRequire" value="${clothesOrder.desc} "><!-- 客户需求描述 -->
			            <input id="orderNumber" type="hidden" name="orderNumber" value="${clothesOrder.orderNumber} "><!-- 订单编号 -->
			            <input id="employeeName" type="hidden" name="employeeName" value="${clothesOrder.employee.username} "><!-- 员工姓名 -->
					</th>
					</tr>
					<c:if test="${!empty clothesOrder.washClothesSet }">
						<c:forEach items="${clothesOrder.washClothesSet}" var="clothes" varStatus="status">
							<tr>
								<td >
									${clothes.clothesName}
								</td>
								
								<td>
								<c:if test="${clothes.amount==0 }">
									${clothes.number}</c:if>
									<c:if test="${clothes.amount!=0 }">
									${clothes.amount }${clothes.size }
									</c:if>
								</td>
								
								<td>
									<input id="code${status.index}" readonly style="border:none;" onfocus="return false;"   value="${clothes.childBarCode}">
								</td>
								
								<c:if test="${clothesOrder.orderStatus==3 or clothesOrder.orderStatus==4 }"><td id="clothesWashStatus">
							[<c:choose>
								<c:when test="${clothes.washStatus == 0 }">未洗涤</c:when>
								<c:when test="${clothes.washStatus == 1 }">洗涤中</c:when>
								<c:when test="${clothes.washStatus == 2 }">返洗中</c:when>
								<c:when test="${clothes.washStatus == 3 }">已洗涤</c:when>
								<c:when test="${clothes.washStatus == 4 }">派送中</c:when>
								<c:when test="${clothes.washStatus == 5 }">已完成</c:when>
							</c:choose>]
							<td>
							<input type="hidden" id="childBarCode" value="${clothes.childBarCode }"/>
						</c:if>	
						        
						        <td >
						       
						       <c:if test="${!empty clothes.brand }">
						       <input id="brand${status.index}" readonly style="border:none;"   value="${clothes.brand}">
						       </c:if>
						        </td>
						        
						        <td >
						       <c:if test="${!empty clothes.color }">
						       <input id="color${status.index}" readonly style="border:none;"   value="${clothes.color}">
						       </c:if>
						        </td>
						        
						        <td >
						       <c:if test="${!empty clothes.defective }">
						       <li id="defective${status.index}" readonly style="border:none;list-style: none;color:black;" value="${clothes.defective}"  >${clothes.defective}</li>
						       </c:if>
						        </td>
						        
						        <td >
						       <c:if test="${!empty clothes.custom }">
						        <li id="custom${status.index}" readonly style="border:none;list-style: none;color:black;"   value="${clothes.custom}">${clothes.custom}</li>
						       </c:if>
						        </td>
						        
						        <td>
							
								<input id="print${status.index}" type="button" class="formButton" value="打印" onclick="javascript:printCode('${status.index}');"/>
								
						        </td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
				<table class="inputTable tabContent">
					<tr class="title">
						<th>服务状态</th>
						<th>图片</th>
						<th>描述</th>
						<th>时间</th>
					</tr>
					<c:if test="${!empty clothesOrder.washStatusSet }">
					<c:forEach items="${clothesOrder.washStatusSet}" var="item">
						<tr>
							<td>
								<c:choose>
									<c:when test="${item.clothesOrderStatus == 0 }">
										预约
									</c:when>
									<c:when test="${item.clothesOrderStatus == 1 }">
										已收衣
									</c:when>
									<c:when test="${item.clothesOrderStatus == 2 }">
										已分拣
									</c:when>
									<c:when test="${item.clothesOrderStatus == 3 }">
										已洗涤
									</c:when>
									<c:when test="${item.clothesOrderStatus == 4 }">
										派送中
									</c:when>
									<c:when test="${item.clothesOrderStatus == 5 }">
										已完成
									</c:when>
								</c:choose>
							</td>
							<td>
								<c:if test="${!empty item.clothesImageUrl}">
									<a href="/lottery-api/${item.clothesImageUrl }" target="_blank" title="点击查看图片">查看</a>
								</c:if>
							</td>
							<td>
								${item.description}
							</td>
							<td>
								<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</td>
						</tr>
					</c:forEach>
					</c:if>
				</table>
				<c:if test="${checkViewType==1 }">
				<table class="inputTable tabContent">
					<tr>
					<th>
						<span>订单编号</span>
					</th>
					 
					<sec:authorize ifAnyGranted="ROLE_CLOTHES_CANCLE_ORDER">
					<th>
						<span>订单总额</span>
					</th>
					</sec:authorize>
					<th>
						<span>状态</span>
					</th>
					
					<th>
						<span>洗衣状态</span>
					</th>
					<th>
						<span>下单时间</span>
					</th>
					<th>
						<span>取衣时间</span>
					</th>
					<th>
						<span>操作</span>
					</th>
				</tr>
				<c:if test="${fn:length(pager.dataList) > 0 }">
				<c:forEach var="order" items="${pager.dataList}">
					<tr>
						 
						<td>
							${order.orderNumber}
						</td>
						<sec:authorize ifAnyGranted="ROLE_CLOTHES_CANCLE_ORDER">
						<td>
							${order.price }元
						</td>
						</sec:authorize>
						<td>
							<c:if test="${order.status == 0}">未处理</c:if>
							<c:if test="${order.status == 1}">已处理</c:if>
							<c:if test="${order.status == 2}">已完成</c:if>
							<c:if test="${order.status == 3}">已作废</c:if>
						</td>
						<td>
							<c:if test="${order.orderStatus == 0}">预约</c:if>
							<c:if test="${order.orderStatus == 1}">已收衣</c:if>
							<c:if test="${order.orderStatus == 2}">已分拣</c:if>
							<c:if test="${order.orderStatus == 3}">已洗涤</c:if>
							<c:if test="${order.orderStatus == 4}">派送中</c:if>
							<c:if test="${order.orderStatus == 5}">已完成</c:if>
						</td>
						<td>
							<fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<fmt:formatDate value="${order.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<a href="clothesOrder/view?id=${order.id}" title="查看">[查看]</a>
						</td>
					</tr>
				</c:forEach>
				</c:if>
				<c:if test="${fn:length(pager.dataList) <= 0 }">
					<tr><td colspan="7"><div class="noRecord" style="text-align: center;">没有找到任何记录!</div></td></tr>
				</c:if>
			</table>
			<c:if test="${fn:length(pager.dataList) > 0 }">
				<div class="pagerBar">
					<div class="pager">
						<%@ include file="pager.jsp"%>
					</div>
				</div>
			</c:if>
			
			</c:if>
			</c:if>
			<div class="buttonArea">
				<input type="button" class="formButton" onclick="window.history.back();" value="返  回" />
			</div>
	</div>
</body>
</html>
