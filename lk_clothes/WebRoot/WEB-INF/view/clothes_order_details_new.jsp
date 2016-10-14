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
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<link rel="icon" href="favicon.ico" type="image/x-icon" />
	    
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/admin/js/base.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/admin/js/admin.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/layer/layer.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/audio/audio.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/admin/js/MultiSelectDropList.js"></script>
		
		<script type="text/javascript" src="<%=basePath%>/resource/common/editor/kindeditor.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/editor/lang/zh_CN.js"></script>
		
		<link href="<%=basePath%>/resource/admin/css/base.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/resource/admin/css/admin.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/resource/admin/css/multi.css" rel="stylesheet" type="text/css" />
	<link href="http://leyes.me/lk_clothes//resource/common/editor/themes/default/default.css" rel="stylesheet">
	<style type="text/css">
	#page{width: 500px; margin: 0 auto; text-align: left;}
	#test{width: 212px; padding: 2px; height: 22px; border: 1px solid #ddd; margin: 0 5px 0 0;}
	#test option{padding: 2px 5px;}
	#test option:first-child{display: none;}
	#pre{position:relative; float: left; max-height: 200px; overflow-y: scroll;}
	#itest{width: 190px; padding: 2px 15px 2px 5px; margin-bottom: -1px; background: url(http://bbs.blueidea.com/data/attachment/album/201410/28/114755leueqeys1ps8dtzy.jpg.thumb.jpg) no-repeat scroll right center; border: 1px solid #ccc; box-shadow: 0 1px 0 0 #eee inset; cursor: default;}
	#itest:focus{cursor: text;}
	.dtest{width: 173px; border: 1px solid #ddd; border-radius: 0 0 3px 3px; display: none;z-index: 999;}
	.soption{display: block; padding: 2px 5px;}
	.soption:hover{background: #f9f9f9; border-top: 1px dotted #ddd; border-bottom: 1px dotted #ddd; padding: 1px 5px;}
	
	.black_border{border-bottom: 2px solid black !important;}
</style>
	
	 <script type="text/javascript">
	 $().ready(function(){
	var input = '<div id="pre">';
	var j;
	var strDiv = '<div id="dtest';
	var idnum='" class="dtest">';
	var strSpan = '';
	var L = $('#test option').size();
	for(var i = 1; i < L; i ++){
		strSpan += '<span class="soption" data-val="'+ $('#test option').eq(i).attr('value') +'"   data-grade="'+$('#test option').eq(i).attr('grade')+'">' + $('#test option').eq(i).html() + '</span>';
	}
	idnum += strSpan + '</div></div>';
	//$('#test').after(input + strDiv);
	
	//初始化结束
	$("input[id*='brand']").bind("focus",function(){//获得焦点，模仿select，显示模拟下拉框
		j=$(this).attr("id").replace(/[^0-9]/ig,"");
		$(this).after(input + strDiv+j+idnum);
		var st = $(this).val().trim();
		if(st == ''){
			$('#dtest'+j).html(strSpan);
		}
		else{
			var strDiv2 = '';
			for(var i = 0; i < L; i ++){
				var html = $('#test option').eq(i).html();
				if(html.match(st)){
					strDiv2 += '<span class="soption" data-val="'+ $('#test option').eq(i).attr('value') +'"   data-grade="'+$('#test option').eq(i).attr('grade')+'">' + $('#test option').eq(i).html() + '</span>';
				}
			}
			$('#dtest'+j).html(strDiv2);
		}
		$('#dtest'+j).slideDown(250);
	});

	$("input[id*='brand']").bind('blur',function(){//失去焦点，隐藏模拟下拉框
		//$(this).next().remove();
		$('#dtest'+j).slideUp(50);
	});

	$("input[id*='brand']").bind('keyup',function(){//输入信息，自动匹配，暂时不支持鼠标右键
		var st = $(this).val().trim().toLowerCase();
		if(st == ''){
			$('#dtest'+j).html(strSpan);
			return false;
		}
		var strDiv2 = '';
		for(var i = 0; i < L; i ++){
			var html = $('#test option').eq(i).html().toLowerCase();
			if(html.match(st)){
				strDiv2 += '<span class="soption" data-val="'+ $('#test option').eq(i).attr('value') +'" data-grade="'+$('#test option').eq(i).attr('grade')+'">' + $('#test option').eq(i).html() + '</span>';
			}
		}
		$('#dtest'+j).html(strDiv2);
	});

	$(document).on('mousedown','.soption',function(){//选择下拉选项
		var html = $(this).html();
		var val = $(this).data('val');
		var grade=$(this).attr("data-grade");
		if(grade=='1'){
			html=html+" ";
			$('#washClothesTr1'+j).css("background-color","#FF7575");
			$('#washClothesTr2'+j).css("background-color","#FF7575");
			$('#washClothesTr3'+j).css("background-color","#FF7575");
			$('#washClothesTr4'+j).css("background-color","#FF7575");
		}else{
			$('#washClothesTr1'+j).css("background-color","");
			$('#washClothesTr2'+j).css("background-color","");
			$('#washClothesTr3'+j).css("background-color","");
			$('#washClothesTr4'+j).css("background-color","");
		}
		$('#brand'+j).val(html).data('val',val).blur();
		$('#brand'+j).attr("grade",$(this).attr("data-grade"));
		if(val!=''){
			$.ajax({
				url:"clothesOrder/brand/washHistory",
				cache:false,
				data:{phone:$("#tel").val(),clothesName:$("#clothesName"+j).val(),brand:val},
				success:function(result){
					if(result.status=='success'){
						if(result.data!=null){
						  $("#washType"+j).val(result.data.washType); $("#partsDesc"+j).val(result.data.partsDesc);
						  $("#sortingDesc"+j).val(result.data.sortingDesc); $("#washDesc"+j).val(result.data.washDesc);
						  $("#dryDesc"+j).val(result.data.dryDesc);  $("#ironingDesc"+j).val(result.data.ironingDesc);
						  $("#arrangeDesc"+j).val(result.data.arrangeDesc);  $("#qcDesc"+j).val(result.data.qcDesc);
						}
						}
				} 
			});
		}
	});

});
</script>
	
		
	</head>
	<object id="ArgoxPrinter" classid="clsid:AEFC7183-44DE-463c-ACEF-8CAF33B96701"codebase="ArgoxWebPrint.cab">
    </object>
	
	<script type="text/javascript">
	
	
	    function deleteframe(){
	    	$("div[id*='colorblock']").css("display","none");
	    	$("div[id*='xiablock']").css("display","none");
	    };    
	
	//  摄像头加载开始
	
	
	    var createtime;
		function StringBuilder(value) {
			this.strings = new Array("");
			this.append(value);
		}
		// Appends the given value to the end of this instance.
		StringBuilder.prototype.append = function(value) {
			if (value) {
				this.strings.push(value);
			}
		}

		// Clears the string buffer

		StringBuilder.prototype.clear = function() {
			this.strings.length = 1;
		}

		// Converts this instance to a String.
		StringBuilder.prototype.toString = function() {
			return this.strings.join("");
		}

		function readCamInfo() {

			var capActivexObject = document.getElementById('cap1');
			//移除所有的摄像头信息
			var camsObject = document.getElementById("cams");
			var templength = camsObject.options.length;
			for (var j = templength; j > 0; j--) {
				camsObject.options.remove(j);
			}

			var camCount = capActivexObject.camCount;//读取客户端计算机一共连接了多少个摄像头
			for (var i = 0; i < camCount; i++) {
				var theOption = document.createElement("option");
				theOption.innerHTML = capActivexObject.getCamName(i); //读取每一个摄像头的名称 
				theOption.value = i;
				document.getElementById('cams').appendChild(theOption);
			}
		}
		function reloadFormats() {
			var capActivexObject = document.getElementById('cap1');

			//设置当前选中的摄像头是哪一个
			capActivexObject
					.setCurrentCam(document.getElementById('cams').value);

			//先移除以前的所有的视频规格选项
			var formatsObject = document.getElementById("videoformat");
			var templength = formatsObject.options.length;
			for (var j = templength; j > 0; j--) {
				formatsObject.options.remove(j);
			}

			var formatCount = capActivexObject.formatCount;
			for (var i = 0; i < formatCount; i++) {
				var theOption = document.createElement("option");
				theOption.innerHTML = capActivexObject.getFormatName(i); //读取每一种视频规格的名称
				theOption.value = i;
				document.getElementById('videoformat').appendChild(theOption);
			}
		}

		function startCam() {
			var capActivexObject = document.getElementById('cap1');

			//设置当前选中的摄像头是哪一个
			capActivexObject
					.setCurrentCam(document.getElementById('cams').value);

			//设置当前选中视频规格是哪一个
			capActivexObject.setCurrentFormat(document
					.getElementById('videoformat').value);

			//启动摄像头
			capActivexObject.start();
		}

		function submitToServer() {
			
			
			for(var i = 0; i < $("#tab tr").length - 1; i++){
				//颜色
				var color = $("#color" + i).val();
				if(color!=null && color.indexOf(";")>=0){
					color=color.substring(0,color.indexOf(";")+1);
				}
				$("#color"+i).val(color);
				//品牌
				var brand=$("#brand" + i).val();
				if(brand!=null && brand.length>18){
					brand=brand.substring(0,18);
				}
				$("#brand" + i).val(brand);
				//瑕疵
				var xia = $("#defective" + i).val();
				if (xia !=null && xia.length > 37) {
					xia = xia.substring(0, 37);
					xia = xia.substring(0, xia.lastIndexOf(';') + 1);
				}
				$("#defective" + i).val(xia);
				//自定义
				var custom=$("#custom" + i).val();
				if(custom!=null && custom.length>17){
					custom=custom.substring(0,17);
				}
				$("#custom" + i).val(custom);
				
			};
			//读取控件的拍照结果到hidden输入项中
			var base64_data = document.getElementById('cap1').jpegBase64Data;
 			if (base64_data.length == 0) {
 				alert('请先拍照!');
 				return false;
 			}  
			//alert(base64_data);
			document.getElementById('picData').value = base64_data;
			document.getElementById('picExt').value = '.jpg';
           
			
			
			/*注意不同的服务器端技术要配置不同的接收数据的url,可以参考submit.html的示例
			例如asp.net的程序员可以查看submit.aspx，php程序员可以查看submit.php，asp程序员可以查看submit.asp
			 */
			// return false;
			//document.forms[0].submit(); 
		}
		
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


		//摄像头加载结束

		//打印开始

		function printCode(id) {
            
			if ($("#code" + id).val().length < 1) {
				alert("没有有效的条形码！");
				return;
			}

			//当前数量
			var j = parseInt(id) + 1;
            
			ArgoxPrinterInit($("#brand" + id).attr("grade"));
			

			//语法：
			// int A_Prn_Text_TrueType(int x, int y, int FSize, string FType, int Fspin,
			//   int FWeight, int FItalic, int FUnline, int FStrikeOut, string id_name,
			//   string data, int mem_mode);

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
					0, "A4", "数量:" + ($("#tab tr").length - 1)/4+"件", 1);

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
			var xia = "备注:" + $("#defective" + id).val();
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
			var bz = $("#custom" + id).val();
			ArgoxPrinter.A_Prn_Text_TrueType(len * 10 + 140, 2, 20, "宋体", 1,
					600, 0, 0, 0, "A11", bz.substring(0, 20), 1);

			//  输出所有资料并设定宽及高的比例和跳号次数与打印份数。
			ArgoxPrinter.A_Print_Out(1, 1, 1, 1);

			//关闭打印机
			ArgoxPrinter.A_ClosePrn();
		}

		function printAll() {
			var flag = true;
			for (var i = 0; i < $("#tab tr").length - 1; i++) {
				if($("#code" + i).val()!=null ){
					if ($("#code" + i).val().length < 1) {
						continue;
					}
				}
				falg = false;
				
			}

			if (!flag) {
				alert("没有有效的条形码！");
				return;
			}

		

			for (var i = 0; i < $("#tab tr").length - 1; i++) {
				
				
				ArgoxPrinterInit($("#brand" + i).attr("grade"));
				//语法：
				// int A_Prn_Text_TrueType(int x, int y, int FSize, string FType, int Fspin,
				//   int FWeight, int FItalic, int FUnline, int FStrikeOut, string id_name,
				//   string data, int mem_mode);

				ArgoxPrinter.A_Prn_Text_TrueType(35, 0, 40, "宋体", 1, 600, 0, 0,
						0, "A1", $("#code" + i).val(), 1);
				ArgoxPrinter.A_Prn_Barcode(18, 20, 1, "e", 2, 3, 30, "B", 1, $(
						"#code" + i).val());

				var usernamel=($("#username").val().replace(/(^\s*)|(\s*$)/g,'').length>4?$("#username").val().substring(0, 4):$("#username").val().substring(0, $("#username").val().replace(/(^\s*)|(\s*$)/g,'').length));
			    var username=usernamel[0];	
			    for(var n=1;n<usernamel.length;++n) username+="*";
				
				ArgoxPrinter.A_Prn_Text_TrueType(140, 42, 20, "宋体", 1, 600, 0,
						0, 0, "A2", "姓名:"
								+username, 1);

				ArgoxPrinter
						.A_Prn_Text_TrueType(205, 42, 20, "宋体", 1, 600, 0, 0,
								0, "A3", "手机:"
										+ $("#tel").val().substring(0, 3)+"****"+$("#tel").val().substring(7, 11), 1);

				ArgoxPrinter.A_Prn_Text_TrueType(305, 42, 20, "宋体", 1, 600, 0,
						0, 0, "A4", "数量:" + ($("#tab tr").length - 1)/4+"件" , 1);

				var color = $("#color" + i).val();
				color=color.substring(0,color.indexOf(";"));
				
					ArgoxPrinter.A_Prn_Text_TrueType(350, 42, 20, "宋体", 1, 600, 0,
							0, 0, "A5", "颜色:" + color, 1);
				
				//color=color.lastIndexOf(';')+1;
				//alert(color);


				var sendAddress = "地址:"
						+ $("#sendAddress").val().replace("成都市", "").replace(
								"四川省", "");

				if (sendAddress.length > 18) {
					ArgoxPrinter.A_Prn_Text_TrueType(140, 32, 20, "宋体", 1, 600,
							0, 0, 0, "A6", sendAddress.substring(0, 18), 1);
				} else {
					ArgoxPrinter.A_Prn_Text_TrueType(140, 32, 20, "宋体", 1, 600,
							0, 0, 0, "A6", sendAddress, 1);
				}
				
				ArgoxPrinter.A_Prn_Text_TrueType(350, 32, 20, "宋体", 1, 600, 0, 0,
						0, "A12", "下单时间:" + createtime, 1);
				
				if($("#brand" + i).val().length>18){
					ArgoxPrinter.A_Prn_Text_TrueType(140, 22, 20, "宋体", 1, 600, 0, 0,
							0, "A7", "品牌:" + $("#brand" + i).val().substring(0,18), 1);
				}else{
				ArgoxPrinter.A_Prn_Text_TrueType(140, 22, 20, "宋体", 1, 600, 0, 0,
						0, "A7", "品牌:" + $("#brand" + i).val(), 1);
				}
				

				ArgoxPrinter.A_Prn_Text_TrueType(360, 22, 20, "宋体", 1, 600, 0,
						0, 0, "A8", "小工:" + $("#employeeName").val(), 1);

				//瑕疵最大字长40
				var xia = "备注:" + $("#defective" + i).val();
				if (xia.length > 40) {
					xia = xia.substring(0, 40);
					xia = xia.substring(0, xia.lastIndexOf(';') + 1);
				}
				if (xia.length > 31) {
					ArgoxPrinter.A_Prn_Text_TrueType(140, 12, 20, "宋体", 1, 600,
							0, 0, 0, "A9", xia.substring(0, 30), 1);
					ArgoxPrinter.A_Prn_Text_TrueType(140, 2, 20, "宋体", 1, 600,
							0, 0, 0, "A10", xia.substring(30, xia.length), 1);
				} else {
					ArgoxPrinter.A_Prn_Text_TrueType(140, 12, 20, "宋体", 1, 600,
							0, 0, 0, "A9", xia, 1);
				}

				//说明字段 最长 20
				var len = 0;
				if (xia.length > 31) {
					len = xia.length - 31;
				}
				var bz = $("#custom" + i).val();
				ArgoxPrinter.A_Prn_Text_TrueType(len * 10 + 140, 2, 20, "宋体",
						1, 600, 0, 0, 0, "A11", bz.substring(0, 20), 1);

				

				ArgoxPrinter.A_Draw_Line("A", 0, 0, 600, 2);
				//  输出所有资料并设定宽及高的比例和跳号次数与打印份数。
				ArgoxPrinter.A_Print_Out(1, 1, 1, 1);
				
				//关闭打印机
				ArgoxPrinter.A_ClosePrn();
			}

			

		}

		//打印结束

		$().ready(function() {
			
					//var createtime=$("#createtime").val().subString(4,9);
		            createtime=$("#createtime").text();
					createtime=createtime.substring(5,10);
					
		            /*
					 <c:if test="${!empty clothesOrder.washClothesSet}">
					 <c:forEach items="${clothesOrder.washClothesSet}" var="clothes" varStatus="status">
					 //颜色
					 $('#color${status.index}').on('click',function(){$('#colorblock${status.index}').css('display','block');});
					 $('#que${status.index}').on('click',function(){
					 var str="";  
					 $("#colorblock${status.index} input[name='color']:checked").each(function(){  
					 str+=$(this).val()+";";
					 });
					 $('#color${status.index}').val(str);
					 $('#colorblock${status.index}').css('display','none');
					 });
					 $('#qu${status.index}').on('click',function(){$('#colorblock${status.index}').css('display','none');});
					
					 //瑕疵
					 $('#defective${status.index}').on('click',function(){$('#xiablock${status.index}').css('display','block');});
					 $('#quel${status.index}').on('click',function(){
					 var str="";  
					 $("#xiablock${status.index} input[name='xia']:checked").each(function(){  
					 str+=$(this).val()+";";
					 });
					 $('#defective${status.index}').val(str);
					 $('#xiablock${status.index}').css('display','none');
					 });
					 $('#qu1${status.index}').on('click',function(){$('#xiablock${status.index}').css('display','none');});
					
					 </c:forEach>
					 </c:if> */
					//  $('#color0').on('click',function(){$('#colorblock0').css('display','block');});

					for (var i = 0; i < $("#tab tr").length - 1; i++) {

				
						 var a=new Array();
						a[1]="#color"+i;
						a[2]="#colorblock"+i;
						a[3]="#que"+i;
						a[4]="#colorblock"+i+" input[name='color']:checked";
						a[5]='#qu'+i; 
				
						a[6]="#defective"+i;
						a[7]="#xiablock"+i;
						a[8]="#quel"+i;
						a[9]="#xiablock"+i+" input[name='xia']:checked";
						a[10]="#qul"+i;
						 var scri = new StringBuilder(); 
						 //颜色
						 scri.append("$("+"'"+a[1]+"'"+").on('click',function(){deleteframe();$("+"'"+a[2]+"'"+").css('display','block');});");
						 scri.append("$("+"'"+a[5]+"'"+").on('click',function(){$("+"'"+a[2]+"'"+").css('display','none');});");
						 scri.append("$("+"'"+a[3]+"'"+").on('click',function(){var str=\"\";$("+"\""+a[4]+"\""+").each(function(){str+=$(this).val()+\";\";});$("+"'"+a[1]+"'"+").val(str);$("+"'"+a[2]+"'"+").css('display','none');}); ");
						 //瑕疵
						 scri.append("$("+"'"+a[6]+"'"+").on('click',function(){deleteframe();$("+"'"+a[7]+"'"+").css('display','block');});");
						 scri.append("$("+"'"+a[10]+"'"+").on('click',function(){$("+"'"+a[7]+"'"+").css('display','none');});");
						 scri.append("$("+"'"+a[8]+"'"+").on('click',function(){var str=\"\";$("+"\""+a[9]+"\""+").each(function(){str+=$(this).val()+\";\";});$("+"'"+a[6]+"'"+").val(str);$("+"'"+a[7]+"'"+").css('display','none');}); ");
						 
						
						 jQuery("#scri").html(
								"<script> $().ready(function(){" +scri.toString()+ "})<\/script>");
						
							/*    $('.color'+i).MSDL({
							'width': '100',
							maxheight: '180',//下拉列表最大高度
							'data': ['迷彩色','焦橙色','裸粉色','紫红色','灰白','浅咖啡','肉色','姜黄色','浅粉','天蓝','点点花','黑黄','拼色','藕红','驼色','米白色','灰麻','血青','银灰','金黄','白/红','黑/白','深蓝/白','花','咖啡','枣红色','桔黄色','军绿色','黑条','白格','灰条','灰格','蓝条','蓝格','梅红色','粉红格','深灰','条纹','格子','深紫','深蓝','深清','深绿','土黄','深棕','大红','浅灰','米色','淡紫','淡蓝','淡青','淡绿','淡黄','浅棕色','粉红','灰色','白色','黑色','紫色','蓝色','青色','绿色','黄色','橙色','红色','深咖啡','灰紫','灰绿','黑灰','深红','米黄','深蓝条','粉色','卡其色','砖红'],
							selectallTxt: '',//全选文本
							selectokTxt: 'ok', //确认文本
							type:'1',  //类型
							value:i    //动态字节
							}); */
						 /* 			$('.defective'+i).MSDL({
						 'width': '100',
						 maxheight: '180',//下拉列表最大高度
						 'data': ['无帽子','挂丝严重','多处有挂丝','领口磨毛','挂印','领口脏','皮发硬','白渍','咖啡渍','有墨水印','发红','粘补过','袖口开线','多出有染色','败色','无里子','有袖子','无带','印花有掉','糖渍','水印','笔印','泥渍','红印','脚边脏','袖口领口脏','皮开裂','开胶','漏里料','药渍','多霉斑','有袖毛','多处有黄渍','无袖毛','多处有黑印','有刮痕','无领','无领带','自身染色','挂破','皮会掉色','内衬有烂','衣物不对称','烫伤','有领边','有毛领','有围脖','有佩肩','刮伤','有袖扣','绿色不明污渍','有异味','表面金粉有掉落','毛领泛红','毛领发黄','皮毛有掉','填充物成球','变形','皮毛发卷','局部掉绒','有手带','掉毛','涂层有掉','合成革裂口','刮印','红渍','巧克力','松紧坏','扣花','燃料','涂层已掉','里衬掉','有胸花','有织布','掉皮','冰激凌渍','发硬','蜡烛','磨白','有领带','口红渍','茶渍','口香糖','酒残渍','有围巾','蛋糕渍','裤脚脱线','油漆','粘毛','黑子','胶水','黄渍','血渍','汗渍','油渍','有洞','磨亮','有内衬','里衬长','掉色','有腰带','泛黄','整体脏','拉链掉色','有帽子','拉链有环','装饰物易掉','装饰物已掉','倒绒','缩水','虫蛀','磨损','磨破','染色','色差','掉色严重','抠破','少扣','开线','衬长','滑丝','起泡','起球','刮丝','脏尽洗','无帽毛'],
						 selectallTxt: '',//全选文本
						 selectokTxt: 'ok', //确认文本
						 type:'2',  //类型
						 value:i    //动态字节  
						 }); */
						/* 			$('.remarks'+i).MSDL({
						 'width': '100',
						 maxheight: '180',//下拉列表最大高度
						 'data': ['织补','整袖口','整边','衣服整形','修补','洗毛领','贴花','特污','特殊衣物','锁边','水印','染色','去渍','去球','浅色','拼色','拼皮','漂白','皮条护理','皮革护理','精洗','加急','换拉链','缝领子','防尘袋','订扣','单锅','处理酶','除锈','除毛绒','除激光'],
						 selectallTxt: '',//全选文本
						 selectokTxt: 'ok', //确认文本
						 type:'3',  //类型
						 value:i    //动态字节  
						 }); */
					}

					var index = parent.layer.getFrameIndex(window.name);
					$("#closeBtn").click(function() {
						parent.layer.close(index);
					});

					$validateForm = $("#validateForm");

					$validateForm.submit(function() {

						var flag = $("#tempState").val();

						if (flag == 0) {
							return false;
						}

						var isSuccess = true;
						$(".childBarCode").each(function() {
							var codeValue = $(this).val();
							if (codeValue == null || codeValue.length == 0) {
								layer.tips('请输入子条码', $(this));
								isSuccess = false;
							} else {
								validateChildBarCode(codeValue);
							}
						});
						if (isSuccess) {
							$("#submitBtn").attr("disabled", true);
						}
						return isSuccess;
						//			alert($(this).toString());
						//			return false;
					});

					//$(".childBarCode")
					var barCode = $(".childBarCode");
					if (barCode.length > 0) {
						barCode.get(0).focus();
						barCode
								.keydown(function(e) {
									if (e.keyCode == 13) { // 监听回车按钮
										var nextInput = $(this).parent()
												.parent().next().find(
														".childBarCode");
										if (nextInput == null
												|| nextInput.length <= 0) {
											$(this).blur();
										} else {
											console.log(nextInput);
											nextInput.focus();
										}
									}
								});
						// 失去焦点，验证子条形码是否存在
						barCode.blur(function() {
							//$this = $(this);
							//var childBarCode = $this.val();
							//if(childBarCode.length > 0){
							//	validateChildBarCode(childBarCode);
							//}
						});
					}

					function validateChildBarCode(childBarCode) {
						$.getJSON(
								"clothesOrder/checkChildBarCode?childBarCode="
										+ childBarCode, function(result) {
									if (!result || result == "false") {
										layer.tips('子条码已经存在，请重新输入', $this);
										$this.val("");
										$this.focus();
										isSuccess = false;
									}
								});
					}

					$("#submitBtn").click(function() {
						layer.confirm('是否确认提交?', {
							btn : [ '确定', '取消' ]
						//按钮
						}, function() {
							submitToServer();
							$("#tempState").val("1");
							$("#validateForm").submit();
						}, function() {
						});
						//submitToServer();
						//$("#validateForm").submit();
					});

					audiojs.events.ready(function() {
						audiojs.createAll();
					});

					$pushBtn = $("#pushBtn");
					if ($pushBtn.length > 0) {
						$pushBtn.click(function() {
							var pushContent = $("#pushContent").val();
							if (pushContent.length <= 0) {
								layer.msg("请输入推送内容");
							} else {
								$.ajax({
									url : "clothesOrder/processRemark",
									type : "post",
									data : {
										orderId : $(
												"input[name='clothesOrderId']")
												.val(),
										memberId : $("#memberId").val(),
										pushContent : pushContent,
										orderNumber:$("#orderNumber").val(),
										phone:$("#tel").val(),
										imageUrl:$("#previewImageUrl").val()
									},
									dataType : "json",
									success : function(result) {
										if (result.status == 'success') {
											layer.msg("推送成功");
											$(".remarkVoice").remove();
											$("input[name='isProccessed']")
													.val(true);

											if (barCode.length > 0) {
												barCode.get(0).focus();
											}

										} else {
											layer.msg(result.message);
										}
									}
								});
							}
						});
					}
					var editor;
					KindEditor.ready(function(K) {
						editor = K.editor({
								allowFileManager : false, //允许图片管理 开启后再挑选图片的时候可以直接从图片空间内挑选
								uploadJson: "<%=basePath%>/file/factoryUpload?type=message"
						});
						editorOut = K.editor({
							allowFileManager : false, //允许图片管理 开启后再挑选图片的时候可以直接从图片空间内挑选
							uploadJson: "<%=basePath%>/file/factoryUpload?type=status"
						});
					    //给按钮添加click事件
						K('#imageServer').click(function() {
						
							uploadImge(this);
						});
						K('#imageServerOut').click(function() {
							
							uploadImgeOut(this);
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
					function uploadImgeOut(i){
						editorOut.loadPlugin('image', function() {
		                    //图片弹窗的基本参数配置
							editorOut.plugin.imageDialog({
								imageWidth:100,
								imageHeight:50, 
								//点击弹窗内”确定“按钮所执行的逻辑
								clickFn : function(url, title, width, height, border, align) {
									//var u = K('#url').val(url);//获取图片地址
									if(url.indexOf("http:") == -1){
										url="<%=basePath%>"+url;
									}
									$(i).parent(".requireFieldOut").prev().prev().attr("src",url);
									
									$(i).parent(".requireFieldOut").prev().val(url);
									editorOut.hideDialog(); //隐藏弹窗
								}
							});
					   });
						
					}
					
					/* $("#sendBtn").click(function(){
						var sendContent=$("#sendMsg").val();
						if (sendContent.length <= 0) {
							layer.msg("请输入回复内容");
						} else {
							$.ajax({
								url : "clothesOrder/processRemark",
								type : "post",
								data : {
									orderId : $(
											"input[name='clothesOrderId']")
											.val(),
									memberId : $("#memberId").val(),
									sendContent : sendContent
								},
								dataType : "json",
								success : function(result) {
									if (result.status == 'success') {
										layer.msg("回复成功");
									} else {
										layer.msg(result.message);
									}
								}
							});
						}
					}); */
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
	<div id="scri" style="display:none;"></div>
	<div class="body">
		<form id="validateForm" <c:if test="${clothesOrder.orderStatus == 1}">action="clothesOrder/collectToFactory"</c:if>
			  	<c:if test="${clothesOrder.orderStatus == 2}">action="clothesOrder/factoryToWash"</c:if>
			  	<c:if test="${clothesOrder.orderStatus == 3}">action="clothesOrder/washToSending"</c:if>
					method="post" enctype="multipart/form-data">
			<input type="hidden" name="clothesOrderId" value="${clothesOrder.id}" /> 
			<input type="hidden" name="isProccessed" value="${clothesOrder.isProcessedOfRemark}">
			<input id="fartherQrCode" type="hidden" name="fartherQrCode" value="${clothesOrder.barCode}">  <!-- 订单条形码 -->
			<input id="tel" type="hidden" name="tel" value="${clothesOrder.clothesAddress.phoneNumber} "><!--电话号码  -->
			<input id="username" type="hidden" name="username" value="${clothesOrder.clothesAddress.username} "><!-- 用户名 -->
			<input id="deliveryAddress" type="hidden" name="deliveryAddress" value="${clothesOrder.clothesAddress.deliveryAddress} "><!-- 收货地址 -->
			<input id="sendAddress" type="hidden" name="sendAddress" value="${clothesOrder.clothesAddress.sendAddress} "><!-- 送货地址 -->
			<input id="customerRequire" type="hidden" name="customerRequire" value="${clothesOrder.desc} "><!-- 客户需求描述 -->
			<input id="orderNumber" type="hidden" name="orderNumber" value="${clothesOrder.orderNumber} "><!-- 订单编号 -->
			<input id="employeeName" type="hidden" name="employeeName" value="${clothesOrder.employee.username} "><!-- 员工姓名 -->
			
		
			
			<!-- 图片数据隐藏域 开始 -->
			 <input type="hidden" id="picData" name="picData"/>
  	  		 <input type="hidden" id="picExt"  name="picExt"/>
			<!-- 图片数据隐藏域结束 -->
			
			
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
					<td>${clothesOrder.orderNumber}</td>
					<th>订单条码：</th>
					<td>${clothesOrder.barCode}</td>
					
				</tr>
				<tr>
					<th>下单时间：</th>
					<td id="createtime"><fmt:formatDate value="${clothesOrder.createTime}" pattern="yyyy-MM-dd HH:ss:mm"/></td>
				</tr>
				
				<tr>
					<th>订单总金额：</th>
					<td>${clothesOrder.price}￥</td>
				</tr>
				<tr>
					<!--<th> 附件上传：</th> -->
					<th>&nbsp;</th>
					<td>
					
					<!--<input type="file" name="imageUpload" style="width:300px;"><br> -->
					
						请选择摄像头:<select id="cams" name="cams"  style="width:200px" onchange="javascript:reloadFormats();" ></select><br>
						  当前摄像头支持的视频格式:<select id="videoformat" name="videoformat"  style="width:200px"></select><br/>
						  <input type="button" value="启动摄像头" onclick="javascript:startCam();"  /> 
						  <input type="button" value="拍照" onclick="javascript:document.getElementById('cap1').cap();"  />
						  <input type="button" value="清除结果" onclick="javascript:document.getElementById('cap1').clear();"  />
						<!--   <input type="button" value="提交到服务器端" onclick="javascript:submitToServer();"  /> <br/> -->  
						  </p>
						  <object classid="clsid:34681DB3-58E6-4512-86F2-9477F1A9F3D8" id="cap1" width="480" height="360" codebase="../cabs/ImageCapOnWeb.cab#version=2,0,0,0" style="z-index:1 !important">
						    <param name="Visible" value="0">
						    <param name="AutoScroll" value="0">
						    <param name="AutoSize" value="0">
						    <param name="AxBorderStyle" value="1">
						    <param name="Caption" value="scaner">
						    <param name="Color" value="4278190095">
						    <param name="Font" value="宋体">
						    <param name="KeyPreview" value="0">
						    <param name="PixelsPerInch" value="96">
						    <param name="PrintScale" value="1">
						    <param name="Scaled" value="-1">
						    <param name="DropTarget" value="0">
						    <param name="HelpFile" value>
						    <param name="PopupMode" value="0">
						    <param name="ScreenSnap" value="0">
						    <param name="SnapBuffer" value="10">
						    <param name="DockSite" value="0">
						    <param name="DoubleBuffered" value="0">
						    <param name="ParentDoubleBuffered" value="0">
						    <param name="UseDockManager" value="0">
						    <param name="Enabled" value="-1">
						    <param name="AlignWithMargins" value="0">
						    <param name="ParentCustomHint" value="-1">
						    <param name="licenseMode" value="2">
						    <param name="key1" value="pUR5SB+npDKgPN1adgv6fRCMWTezqjYRLGAcZ0Ds6WoW1oaYc6jzzJivgVTyK1hk8KB00Wvjd8Ye1KDUJMKud2g1lx/JcQMQ9YlKzYIZ">
						    <param name="key2" value="bqq5tlpM29pmeouMBU3dO5mJ7sRxjiVFBmxMZQ8yUga1w6yGru3RIqTCaVrbvBo4u9Srpg+KTUhCtMAfP7Pam2t/BRTuKt57iZP5DzoliH79BgZf+isQDXlgy+yYHxcR1cCIeONM54WR3Al0+LKjOmnH9x6qoJGBN0Bg1bnwFua68TNBX7op2m1ih64=">
						  </object>
					
					
					
					</td>
				</tr>
				<tr>
					<th>小哥的备注：</th>
					<td>${clothesOrder.desc }</td>
				</tr>
				<c:if test="${!empty clothesOrder.remarkVoiceUrl}">
				<tr class="remarkVoice">
					<th>用户留言：</th>
					<td colspan="2">
						<audio src="<%=basePath%>${clothesOrder.remarkVoiceUrl}" preload="auto" />
					</td>
				</tr>
				</c:if>
				<c:if test="${clothesOrder.orderStatus == 1 && !clothesOrder.isProcessedOfRemark}">
				
				<tr class="remarkVoice">
					<th>消息推送<c:if test="${isNew==1}"><span style="color: red">(新用户)</span></c:if>：</th>
					
					<td>
						
						<input type="hidden" id="memberId" value="${clothesOrder.userId}" />
						<img class="photoImg" style="width:200px; height:150px;">
							<input type="hidden" class="imageUrl" id="previewImageUrl">
							<label class="requireField"><input type="button" id="imageServer" value="选择图片"></label>
						<input id="pushContent" type="text" class="formText" style="width: 90%;">
						<input id="pushBtn" type="button" class="formButton" value="推 送" />
					</td>
				</tr>
				 
				</c:if>
				<tr>
					<th>工厂的备注：</th>
					<td><input type="text" name="factoryRemark"style="width: 90%;"> </td>
				</tr>
				<tr>
					<th>外包图片上传：</th>
					<td>
						<img class="outImg" style="width:200px; height:150px;">
						<input type="hidden" name="outImageUrl" id="previewImageUrlOut">
							<label class="requireFieldOut"><input type="button" id="imageServerOut" value="选择图片"></label>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						洗衣信息
					</td>
				</tr>
				</table>
			</div>
			<table class="inputTable tabContent" id="tab">
			<tr class="title">
				<th  style="width: 35pt;">名称</th>
				<th style="width: 45pt;">条码</th>
				<th style="width: 80pt;">品牌</th>
				<th style="width: 45pt;">颜色</th>
				<th style="width: 45pt;">瑕疵</th>
				<!-- <th style="width: 45pt;">备注</th> -->
				<th style="width: 80pt;">自定义</th>
				<th style="width: 35pt;">打印</th>
			</tr>
			<c:if test="${!empty clothesOrder.washClothesSet}">
				<c:forEach items="${clothesOrder.washClothesSet}" var="clothes" varStatus="status">
					<tr class="clothes_tab" id="washClothesTr1${status.index}" >
						<td>
							<input name="washClothesList[${status.index}].id" type="hidden" value="${clothes.id}" />
							<input type="hidden" value="${clothes.clothesName}" id="clothesName${status.index}">
							${clothes.clothesName}
						</td>
						
						<td>
							<c:if test="${clothesOrder.orderStatus == 1 }">
								<input id="code${status.index}"  readonly name="washClothesList[${status.index}].childBarCode" type="text" value="${clothes.childBarCode }" class="formText childBarCode" style="width: 45pt;"/>
							</c:if>
							<c:if test="${clothesOrder.orderStatus == 2 || clothesOrder.orderStatus == 3}">
								${clothes.childBarCode }
							</c:if>
						</td>
						<td style="">
							<input id="brand${status.index}" name="washClothesList[${status.index}].brand" grade="2" type="text" value="" class="formText"/>
						     
						
						</td>
						<td>
							<div class='color${status.index}'></div>
						<input id="color${status.index}" type="text" name="washClothesList[${status.index}].color"  class="formText" value="">
<!--							${clothes.number}-->
<!--							<input id="attr${status.index}" type="button" class="formButton" value="属性" onclick="javascript:setAttr('${status.index}','${clothes.childBarCode }');"/>-->
<!--								<input id="tempBrand${status.index}" type="hidden" name="tempBrand${status.index}" value="">品牌 -->
<!--								<input id="tempColor${status.index}" type="hidden" name="tempColor${status.index}" value="">颜色 -->
<!--								<input id="tempDefective${status.index}" type="hidden" name="tempDefective${status.index}" value="">瑕疵 -->
<!--							    <input id="tempRemarks${status.index}" type="hidden" name="tempRemarks${status.index}" value="">备注 -->
						 						
						<div  id="colorblock${status.index}" style="display:none;position:absolute;background: #fff; border:solid #999 1px;" >
 
                       
                              <input type="checkbox" name="color" value="白色"/>白色<input type="checkbox" name="color" value="白格"/>白格<input type="checkbox" name="color" value="橙色"/>橙色<input type="checkbox" name="color" value="大红"/>大红<input type="checkbox" name="color" value="淡紫"/>淡紫<input type="checkbox" name="color" value="淡蓝"/>淡蓝<input type="checkbox" name="color" value="淡青"/>淡青<br />
                              <input type="checkbox" name="color" value="淡绿"/>淡绿<input type="checkbox" name="color" value="淡黄"/>淡黄<input type="checkbox" name="color" value="粉红"/>粉红<input type="checkbox" name="color" value="粉色"/>粉色<input type="checkbox" name="color" value="格子"/>格子<input type="checkbox" name="color" value="黑色"/>黑色<input type="checkbox" name="color" value="黑条"/>黑条<br />
                              <input type="checkbox" name="color" value="黑灰"/>黑灰<input type="checkbox" name="color" value="黑黄"/>黑黄<input type="checkbox" name="color" value="灰格"/>灰格<input type="checkbox" name="color" value="灰条"/>灰条<input type="checkbox" name="color" value="灰麻"/>灰麻<input type="checkbox" name="color" value="灰白"/>灰白<input type="checkbox" name="color" value="灰色"/>灰色<br />
                              <input type="checkbox" name="color" value="灰紫"/>灰紫<input type="checkbox" name="color" value="灰绿"/>灰绿<input type="checkbox" name="color" value="红色"/>红色<input type="checkbox" name="color" value="黄色"/>黄色<input type="checkbox" name="color" value="咖啡"/>咖啡<input type="checkbox" name="color" value="蓝色"/>蓝色<input type="checkbox" name="color" value="绿色"/>绿色<br />
                              <input type="checkbox" name="color" value="蓝条"/>蓝条<input type="checkbox" name="color" value="蓝格"/>蓝格<input type="checkbox" name="color" value="米色"/>米色<input type="checkbox" name="color" value="米黄"/>米黄<input type="checkbox" name="color" value="藕红"/>藕红<input type="checkbox" name="color" value="拼色"/>拼色<input type="checkbox" name="color" value="青色"/>青色<br />
                              <input type="checkbox" name="color" value="浅灰"/>浅灰<input type="checkbox" name="color" value="浅粉"/>浅粉<input type="checkbox" name="color" value="肉色"/>肉色<input type="checkbox" name="color" value="深灰"/>深灰<input type="checkbox" name="color" value="深紫"/>深紫<input type="checkbox" name="color" value="深蓝"/>深蓝<input type="checkbox" name="color" value="深青"/>深青<br />
                              <input type="checkbox" name="color" value="深绿"/>深绿<input type="checkbox" name="color" value="深棕"/>深棕<input type="checkbox" name="color" value="深红"/>深红<input type="checkbox" name="color" value="驼色"/>驼色<input type="checkbox" name="color" value="条纹"/>条纹<input type="checkbox" name="color" value="土黄"/>土黄<input type="checkbox" name="color" value="天蓝"/>天蓝<br />
                              <input type="checkbox" name="color" value="血青"/>血青<input type="checkbox" name="color" value="银灰"/>银灰<input type="checkbox" name="color" value="紫色"/>紫色<input type="checkbox" name="color" value="砖红"/>砖红<br />

                              <input type="checkbox" name="color" value="白/红"/>白/红<input type="checkbox" name="color" value="黑/白"/>黑/白<input type="checkbox" name="color" value="深蓝/白"/>深蓝/白<input type="checkbox" name="color" value="花"/>花<br />
                              
                              <input type="checkbox" name="color" value="粉红格"/>粉红格<input type="checkbox" name="color" value="姜黄色"/>姜黄色<input type="checkbox" name="color" value="焦橙色"/>焦橙色<input type="checkbox" name="color" value="桔黄色"/>桔黄色<input type="checkbox" name="color" value="军绿色"/>军绿色<input type="checkbox" name="color" value="裸粉色"/>裸粉色<br />
                              <input type="checkbox" name="color" value="米白色"/>米白色<input type="checkbox" name="color"  value="迷彩色"/>迷彩色<input type="checkbox" name="color" value="梅红色"/>梅红色<input type="checkbox"  name="color" value="卡其色"/>卡其色<input type="checkbox" name="color"  value="浅棕色"/>浅棕色<input type="checkbox" name="color" value="浅咖啡"/>浅咖啡<br />
                              <input type="checkbox" name="color" value="深蓝条"/>深蓝条<input type="checkbox" name="color" value="深咖啡"/>深咖啡<input type="checkbox" name="color" value="紫红色"/>紫红色<input type="checkbox" name="color" value="枣红色"/>枣红色<br />
                              &nbsp;&nbsp;&nbsp;&nbsp;<button id="que${status.index}">确认</button>&nbsp;&nbsp;&nbsp;&nbsp;<button id="qu${status.index}">取消</button>

                             </div>
						</td>    
						     

						<td>
							<div class='defective${status.index}'></div>
						  <input id="defective${status.index}" type="text" name="washClothesList[${status.index}].defective" class="formText" value="">
						<div  id="xiablock${status.index}" style="display:none;position:absolute;background: #fff; border:solid #999 1px;">
 
                           <input type="checkbox" name="xia" value="白渍"/>白渍<input type="checkbox" name="xia" value="白印"/>白印<input type="checkbox" name="xia" value="败色"/>败色<input type="checkbox" name="xia" value="笔印"/>笔印<input type="checkbox" name="xia" value="变形"/>变形<input type="checkbox" name="xia" value="茶渍"/>茶渍<input type="checkbox" name="xia" value="虫蛀"/>虫蛀<input type="checkbox" name="xia" value="掉毛"/>掉毛<br />
                           <input type="checkbox" name="xia" value="掉色"/>掉色<input type="checkbox" name="xia" value="掉漆"/>掉漆<input type="checkbox" name="xia" value="掉绒"/>掉绒<input type="checkbox" name="xia" value="掉皮"/>掉皮<input type="checkbox" name="xia" value="倒绒"/>倒绒<input type="checkbox" name="xia" value="发红"/>发红<input type="checkbox" name="xia" value="发花"/>发花<input type="checkbox" name="xia" value="发白"/>发白<br />
                           <input type="checkbox" name="xia" value="发硬"/>发硬<input type="checkbox" name="xia" value="泛黄"/>泛黄<input type="checkbox" name="xia" value="挂印"/>挂印<input type="checkbox" name="xia" value="挂破"/>挂破<input type="checkbox" name="xia" value="挂丝"/>挂丝<input type="checkbox" name="xia" value="挂伤"/>挂伤<input type="checkbox" name="xia" value="红印"/>红印<input type="checkbox" name="xia" value="黑渍"/>黑渍<br />
                           <input type="checkbox" name="xia" value="黑印"/>黑印<input type="checkbox" name="xia" value="黄渍"/>黄渍<input type="checkbox" name="xia" value="汗渍"/>汗渍<input type="checkbox" name="xia" value="滑丝"/>滑丝<input type="checkbox" name="xia" value="红渍"/>红渍<input type="checkbox" name="xia" value="胶水"/>胶水<input type="checkbox" name="xia" value="开胶"/>开胶<input type="checkbox" name="xia" value="扣破"/>扣破<br />
                           <input type="checkbox" name="xia" value="开线"/>开线<input type="checkbox" name="xia" value="扣花"/>扣花<input type="checkbox" name="xia" value="蜡烛"/>蜡烛<input type="checkbox" name="xia" value="磨白"/>磨白<input type="checkbox" name="xia" value="磨亮"/>磨亮<input type="checkbox" name="xia" value="磨损"/>磨损<input type="checkbox" name="xia" value="磨破"/>磨破<input type="checkbox" name="xia" value="磨毛"/>磨毛<br />
                           <input type="checkbox" name="xia" value="泥渍"/>泥渍<input type="checkbox" name="xia" value="粘毛"/>粘毛<input type="checkbox" name="xia" value="皮烂"/>皮烂<input type="checkbox" name="xia" value="起泡"/>起泡<input type="checkbox" name="xia" value="起球"/>起球<input type="checkbox" name="xia" value="起毛"/>起毛<input type="checkbox" name="xia" value="起皱"/>起皱<input type="checkbox" name="xia" value="染色"/>染色<br />
                           <input type="checkbox" name="xia" value="水印"/>水印<input type="checkbox" name="xia" value="缩水"/>缩水<input type="checkbox" name="xia" value="色差"/>色差<input type="checkbox" name="xia" value="少扣"/>少扣<input type="checkbox" name="xia" value="糖渍"/>糖渍<input type="checkbox" name="xia" value="烫伤"/>烫伤<input type="checkbox" name="xia" value="血渍"/>血渍<input type="checkbox" name="xia" value="药渍"/>药渍<br />
                           <input type="checkbox" name="xia" value="油漆"/>油漆<input type="checkbox" name="xia" value="油渍"/>油渍<input type="checkbox" name="xia" value="有洞"/>有洞<br />
                           

                              
                           <input type="checkbox" name="xia" value="多霉斑"/>多霉斑<input type="checkbox" name="xia" value="蛋糕渍"/>蛋糕渍<input type="checkbox" name="xia" value="脚边脏"/>脚边脏<input type="checkbox" name="xia" value="酒残渍"/>酒残渍<input type="checkbox" name="xia" value="咖啡渍"/>咖啡渍<input type="checkbox" name="xia" value="口红渍"/>口红渍<input type="checkbox" name="xia" value="口香糖"/>口香糖<br />
                           <input type="checkbox" name="xia" value="领口脏"/>领口脏<input type="checkbox" name="xia" value="里衬掉"/>里衬掉<input type="checkbox" name="xia" value="里衬长"/>里衬长<input type="checkbox" name="xia" value="粘补过"/>粘补过<input type="checkbox" name="xia" value="皮发硬"/>皮发硬<input type="checkbox" name="xia" value="皮开裂"/>皮开裂<input type="checkbox" name="xia" value="皮磨损"/>皮磨损<br />
                           <input type="checkbox" name="xia" value="巧克力"/>巧克力<input type="checkbox" name="xia" value="松紧坏"/>松紧坏<input type="checkbox" name="xia" value="无帽子"/>无帽子<input type="checkbox" name="xia" value="无内衬"/>无内衬<input type="checkbox" name="xia" value="无袖毛"/>无袖毛<input type="checkbox" name="xia" value="无领带"/>无领带<input type="checkbox" name="xia" value="无帽毛"/>无帽毛<br />
                           <input type="checkbox" name="xia" value="无腰带"/>无腰带<input type="checkbox" name="xia" value="无毛领"/>无毛领<input type="checkbox" name="xia" value="袖口脏"/>袖口脏<input type="checkbox" name="xia" value="有袖子"/>有袖子<input type="checkbox" name="xia" value="有袖毛"/>有袖毛<input type="checkbox" name="xia" value="有织补"/>有织补<input type="checkbox" name="xia" value="有刮痕"/>有刮痕<br />
                           <input type="checkbox" name="xia" value="有领边"/>有领边<input type="checkbox" name="xia" value="有毛领"/>有毛领<input type="checkbox" name="xia" value="有围脖"/>有围脖<input type="checkbox" name="xia" value="有佩肩"/>有佩肩<input type="checkbox" name="xia" value="有袖扣"/>有袖扣<input type="checkbox" name="xia" value="有异味"/>有异味<input type="checkbox" name="xia" value="有袖带"/>有袖带<br />
                           <input type="checkbox" name="xia" value="有胸花"/>有胸花<input type="checkbox" name="xia" value="有织布"/>有织布<input type="checkbox" name="xia" value="有领带"/>有领带<input type="checkbox" name="xia" value="有围巾"/>有围巾<input type="checkbox" name="xia" value="有内衬"/>有内衬<input type="checkbox" name="xia" value="有腰带"/>有腰带<input type="checkbox" name="xia" value="有帽子"/>有帽子<br />
                           <input type="checkbox" name="xia" value="整体脏"/>整体脏<input type="checkbox" name="xia" value="脏尽洗"/>脏尽洗<br />
                           
                           
                           <input type="checkbox" name="xia" value="冰激凌渍"/>冰激凌渍<input type="checkbox" name="xia" value="领口磨毛"/>领口磨毛<input type="checkbox" name="xia" value="掉色严重"/>掉色严重<input type="checkbox" name="xia" value="多处油渍"/>多处油渍<input type="checkbox" name="xia" value="挂丝严重"/>挂丝严重<input type="checkbox" name="xia" value="局部掉绒"/>局部掉绒<input type="checkbox" name="xia" value="裤脚脱线"/>裤脚脱线<br />
                           <input type="checkbox" name="xia" value="领口磨毛"/>领口磨毛<input type="checkbox" name="xia" value="领口泛黄"/>领口泛黄<input type="checkbox" name="xia" value="拉链有坏"/>拉链有坏<input type="checkbox" name="xia" value="拉链有环"/>拉链有环<input type="checkbox" name="xia" value="毛领泛红"/>毛领泛红<input type="checkbox" name="xia" value="毛领发黄"/>毛领发黄<input type="checkbox" name="xia" value="面料损伤"/>面料损伤<br />
                           <input type="checkbox" name="xia" value="内衬有烂"/>内衬有烂<input type="checkbox" name="xia" value="皮毛有掉"/>皮毛有掉<input type="checkbox" name="xia" value="皮毛发卷"/>皮毛发卷<input type="checkbox" name="xia" value="皮会掉色"/>皮会掉色<input type="checkbox" name="xia" value="涂层有掉"/>涂层有掉<input type="checkbox" name="xia" value="涂层已掉"/>涂层已掉<input type="checkbox" name="xia" value="袖口开线"/>袖口开线<br />
                           <input type="checkbox" name="xia" value="袖口泛黄"/>袖口泛黄<input type="checkbox" name="xia" value="有墨水印"/>有墨水印<input type="checkbox" name="xia" value="印花有掉"/>印花有掉<input type="checkbox" name="xia" value="自身染色"/>自身染色<br />
                           
  
                           <input type="checkbox" name="xia" value="多处有挂丝"/>多处有挂丝<input type="checkbox" name="xia" value="多出有染色"/>多出有染色<input type="checkbox" name="xia" value="多处有黄渍"/>多处有黄渍<input type="checkbox" name="xia" value="多处有黑印"/>多处有黑印<input type="checkbox" name="xia" value="合成革裂口"/>合成革裂口<br />
                           <input type="checkbox" name="xia" value="拉链头掉色"/>拉链头掉色<input type="checkbox" name="xia" value="填充物成球"/>填充物成球<input type="checkbox" name="xia" value="袖口领口脏"/>袖口领口脏<input type="checkbox" name="xia" value="衣物不对称"/>衣物不对称<input type="checkbox" name="xia" value="装饰物易掉"/>装饰物易掉<br />
                           <input type="checkbox" name="xia" value="装饰物已掉"/>装饰物已掉<input type="checkbox" name="xia" value="装饰物有掉"/>装饰物有掉<br />
                           
                           <input type="checkbox" name="xia" value="绿色不明污渍"/>绿色不明污渍<input type="checkbox" name="xia" value="表面金粉有掉落"/>表面金粉有掉落<br /> &nbsp;&nbsp;&nbsp;&nbsp;<button id="quel${status.index}">确认</button>&nbsp;&nbsp;&nbsp;&nbsp;<button id="qul${status.index}">取消</button>

</div>
						
						</td>
						
						<%--  <input id="remarks${status.index}" type="hidden" name="remarks${status.index}" value=""> --%>
						<%-- <td>
							<div  display:none; type="hidden" class='remarks${status.index}'></div>
						   <input id="remarks${status.index}" type="hidden" name="remarks${status.index}" value="">
						</td> --%>
						<td>
							<input id="custom${status.index}" name="washClothesList[${status.index}].custom" type="text" value="" class="formText"/>
						
						</td>
						<td rowspan="4" class="black_border">
							<c:if test="${clothesOrder.orderStatus == 1 }">
								<input id="print${status.index}" type="button" class="formButton" value="打印" onclick="javascript:printCode('${status.index}');"/>
							</c:if>
						</td>
					</tr>
					<tr id="washClothesTr2${status.index}"><td style="border-bottom: none;"></td><td style="border-bottom: none;"></td><td><span>洗涤方式 </span>
					<select id="washType${status.index }" name="washClothesList[${status.index}].washType" class="formText" style="width: 192px;height: 27px;"><option value=""></option><option value="干洗">干洗</option><option value="水洗">水洗</option><option value="手洗">手洗</option><option value="特殊处理">特殊处理</option></select></td>
					<td><span>配件说明</span><input id="partsDesc${status.index }" name="washClothesList[${status.index}].partsDesc" class="formText" value=""></td>
					<td><span>分拣说明</span><input id="sortingDesc${status.index }" name="washClothesList[${status.index}].sortingDesc" class="formText" value=""></td>
					<td><span>洗涤说明</span><input id="washDesc${status.index }" name="washClothesList[${status.index}].washDesc" class="formText" value=""></td></tr>
					<tr id="washClothesTr3${status.index}"><td ></td><td ></td>
					<td ><span>烘干说明</span><input id="dryDesc${status.index }" name="washClothesList[${status.index}].dryDesc" class="formText" value=""></td>
					<td ><span>熨烫说明</span><input id="ironingDesc${status.index }" name="washClothesList[${status.index}].ironingDesc" class="formText" value=""></td>
					<td ><span>整理说明</span><input id="arrangeDesc${status.index }" name="washClothesList[${status.index}].arrangeDesc" class="formText" value=""></td>
					<td ><span>质检说明</span><input id="qcDesc${status.index }" name="washClothesList[${status.index}].qcDesc" class="formText" value=""></td></tr>
					<tr id="washClothesTr4${status.index}"><td class="black_border"/><td class="black_border"/><td class="black_border"><span>外包</span>
						<input id="isOut${status.index }" name="washClothesList[${status.index}].isOut" type="checkbox" /></td><td class="black_border"/><td class="black_border"/><td class="black_border"/>
					</tr>
				</c:forEach>
			</c:if>
			</table>
			<div class="buttonArea">
			<input id="tempState" type="hidden" name="tempState" value="0">
				<input id="pAll" type="button" class="formButton" value="打印所有"  onclick="javascript:printAll();"/>
				<input id="submitBtn" type="button" class="formButton" value="确 定" />
				 <input type="button" class="formButton" onclick="window.history.back();" value="返  回"/>
			</div>
			
			
			
		</form>
		

	</div>
<div id="page" style="display: none;">
	<select id="test" name="test">
		<c:forEach items="${brands }" var="brand">
	   		<option grade="${brand.brandGrade}" value="${brand.brandName }">${brand.brandName }</option>
		</c:forEach>
	</select>
</div>
	
	<script type="text/javascript">
	  document.all.cap1.SwitchWatchOnly();  //切换到只显示摄像头画面形式，隐藏编辑按钮等图标.
	  readCamInfo();
	  reloadFormats();
	</script>
</body>
</html>
