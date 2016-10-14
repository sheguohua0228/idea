<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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

<script type="text/javascript"
	src="<%=basePath%>/resource/common/js/jquery.js"></script>
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
$().ready( function(){
	
	
	var $listForm = $("#listForm");// 列表表单
	
		
		var $allCheck = $("#listTable input.allCheck");// 全选复选框
		var $listTableTr = $("#listTable tr:gt(0)");
		$idsCheck = $("#listTable  input[name='ids']");// ID复选框 
		var $nextButton=$("#nextButton");
		var idslist;
		// 全选
		$allCheck.click( function() {
			var $this = $(this);
			if ($this.attr("checked")) {
				idslist = $("#listTable input:lt(37)");
				idslist.attr("checked", true); 
			/* 	$idsCheck.attr("checked", true); */
				
				$nextButton.attr("disabled", false);
				
				$listTableTr = $("#listTable tr:lt(19)");
				$listTableTr.addClass("checked");
				$listTableTr.removeClass("noprint");
			} else {
				/* $idsCheck.attr("checked", false); */
				idslist = $("#listTable input");
				idslist.attr("checked", false); 
				$nextButton.attr("disabled", true);
				$listTableTr = $("#listTable tr");  
				$listTableTr.removeClass("checked");
				$listTableTr.addClass("noprint");
			}
		});
		
		// 无复选框被选中时,删除按钮不可用
		$idsCheck.live("click", function() {
			
			var $this = $(this);
			if ($this.attr("checked")) {
				$this.parent().parent().addClass("checked");
				$nextButton.attr("disabled", false);
				$this.parent().parent().removeClass("noprint");
			} else {
				$this.parent().parent().removeClass("checked");
				var $idsChecked = $("#listTable input[name='ids']:checked");
				if ($idsChecked.size() > 0) {
					$nextButton.attr("disabled", false);	
					
				} else {
                    $nextButton.attr("disabled", true);	
                    
				}
				$this.parent().parent().addClass("noprint");
			}
		});
		
		
 		$nextButton.click(function(){
			var url = $(this).attr("url");
			var $idsCheckedCheck = $("#listTable input[name='ids']:checked");
			var barcode = $("#listTable input[name='ids']:checked").serialize().split("&");
		    
			var orderId = $("#listTable input[name='ids']:checked").next().serialize().split("&"); 
			
			for(var i=0;i<barcode.length;++i){
				barcode[i]=barcode[i].substring(4,barcode[i].length); 
				orderId[i]=orderId[i].substring(8,orderId[i].length); 
				
			}
			
			$.dialog({type: "warn", content: "您确定将所选都进入派送状态吗?", ok: "确 定", cancel: "取 消", modal: true, okCallback: batchDelete});
			function batchDelete() {
				$.ajax({
					url: url,
					data: {"barcode":barcode,"orderId":orderId},
					type: "POST",
					dataType: "json",
					cache: false,
					success: function(data) {
						if (data.status == "success") {
							
							$idsCheckedCheck.parent().parent().remove();
							for(var j=0;j<barcode.length;++j){
								
							for(var i=0;i<barCodes.length;++i) if(barCodes[i]==barcode[j]) {barCodes.splice(i,1);};
							}
							
						}
						$nextButton.attr("disabled", true);
						$allCheck.attr("checked", false);
						$idsCheckedCheck.attr("checked", false);
						$.message({type: data.status, content: data.message});
					}
				});
			}
			
		}); 
		

	//菜单点击样式
});
</script>
<script>
    
	var barCodes = new Array();//全局标签	
	var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
	var isOpera = userAgent.indexOf("Opera") > -1;
	$().ready(
			
			function() {
				
				var barCode = $("#barCode");
				barCode.focus();

				$("#barCode").bind(
						"change  keyup",
						function() {
                            
							/* if(barCodes.length == 18){
								$.message({type:"success", content: "已经存在18件衣物 请打印"});
								return false;
							} */
								
							if ((userAgent.indexOf("Chrome") > -1)
									|| (userAgent.indexOf("Firefox") > -1)) {

								if (barCodes.indexOf(barCode.val().trim()) != -1 && barCode.val().trim().length!=8)
									{$("#barCode").val("");barCode.focus();return false;}
							} else {

								for (var i = 0; i < barCodes.length; i++)
									if (barCodes[i] == barCode.val().trim() && barCode.val().trim().length!=8)
									{$("#barCode").val("");barCode.focus();return false;}
							}
								
							
							if (barCode.val().trim().length >= 8) {
								$.ajax({
									type : "GET",
									url : "clothesOrder/queryBarCode",
									data : {barCode:barCode.val().trim()},
									cache:false,
									async:false,
									dataType : "json",
									success : function(data) {
										var arg;
                                        for(var i=0;i<data.length;++i){
                                        	for (var j = 0; j < barCodes.length; j++)if (barCodes[j] == data[i].childBarCode) {$("#barCode").val("");barCode.focus();arg=data[i].childBarCode;break;}
            							if(arg == data[i].childBarCode) continue;
                                        barCodes.push(data[i].childBarCode);
										var sel = document
												.getElementById("listTable");
										var tbodys = document
												.createElement("tbody");
										var trs = document.createElement("tr");
										trs.setAttribute("class", "noprint");
										var inputs = document
												.createElement("input");
										inputs.type = "checkbox";
										inputs.setAttribute("name", "ids"); 
										inputs.value = data[i].childBarCode;
										var tds = document.createElement("td");
										tds.setAttribute("class", "checkitem"); 
										var orderId= document.createElement("input");
										orderId.type = "checkbox";
										orderId.value=data[i].clothesOrderId;
										orderId.style.display="none";
										orderId.checked="true";
										orderId.setAttribute("name", "orderId"); 
										tds.appendChild(inputs);
										tds.appendChild(orderId);
										trs.appendChild(tds);

										tds = document.createElement("td");
										tds.innerHTML = data[i].orderNumber;
										trs.appendChild(tds);
										tds = document.createElement("td");
										tds.innerHTML = data[i].childBarCode;
										trs.appendChild(tds);
										tds = document.createElement("td");
										tds.innerHTML = data[i].clothesName;
										trs.appendChild(tds);
										tds = document.createElement("td");
										tds.innerHTML = data[i].brand;
										trs.appendChild(tds);
										tds = document.createElement("td");
										tds.innerHTML = data[i].userName;
										trs.appendChild(tds);
										tds = document.createElement("td");
										tds.innerHTML = data[i].phoneNumber;
										trs.appendChild(tds);
										tds = document.createElement("td");
										tds.innerHTML = data[i].realName;
										trs.appendChild(tds);
										tds = document.createElement("td");
										tds.innerHTML = data[i].phone;
										trs.appendChild(tds);
										tds = document.createElement("td");
										tds.innerHTML = data[i].createTime+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
										var deleteb = document.createElement("a");
										deleteb.innerHTML="[删除]";
										deleteb.setAttribute("href","javascript:");
										deleteb.setAttribute("class","noprint");
										deleteb.setAttribute("onclick", "deletetr('"+data[i].childBarCode+"')");
										tds.appendChild(deleteb);
										trs.appendChild(tds);
										trs.setAttribute("id",data[i].childBarCode);
										tbodys.appendChild(trs);
										sel.appendChild(tbodys);
										$idsCheck = $("#listTable input[name='ids']");
                                        }
										$("#barCode").val("");
										barCode.focus();
									}
								});

							}
							
						});
			})
			
		function deletetr(arg){
		for(var i=0;i<barCodes.length;++i) if(barCodes[i]==arg) barCodes.splice(i,1);
		$("#"+arg).parent().remove();
		//document.getElementById('mainFrame').contentWindow.document.body.removeChild(document.getElementById(arg).parentNode);
	}
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

	function printSet() {
		try {
			var Wsh = new ActiveXObject("WScript.Shell"); //vbscript的控件核心文件

			HKEY_Key = "header";
			Wsh.RegWrite(HKEY_Root + HKEY_Path + HKEY_Key, "洗涤跟踪交接表"); //清空注册表信息中IE的页眉

			HKEY_Key = "footer";
			Wsh.RegWrite(HKEY_Root + HKEY_Path + HKEY_Key, getNowFormatDate()); //清空注册表信息中IE的页脚
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
		
		/* var e = event.srcElement; */
		printSet();
		wb.ExecWB(7,1);
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
	    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
	    return currentdate;
	}
</script>
</head>

<body class="list">
<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" id="wb" name="wb" style="display: none;"></OBJECT>
	<div class="bar">已分拣订单列表&nbsp;总记录数: 页&nbsp;&nbsp;</div>
	<div class="body">
		<form id="listForm" action="clothesOrder/factory" method="post" onsubmit="return false">
			<div class="listBar">
				<label>查找: </label> 子条形码： <input id="barCode" type="text"
					name="barCode" value="${barCode }" /> 
					
				</select> <label>每页显示: </label> 18条
			</div>

			
				<table id="listTable" class="listTable">
					<tr>
						<th class="check"><input type="checkbox" class="allCheck" />
						</th>
						<th><span>订单编号</span></th>
						<th><span>子条码</span></th>
						<th><span>名称</span></th>
						<th><span>品牌</span></th>

						<th><span>用户名</span></th>
						<th><span>联系电话</span></th>


						<th><span>小哥姓名</span></th>

						<th><span>小哥电话</span></th>

						<th><span>下单时间</span></th>
                        
                        
					</tr>

				</table>
			
			<div class="pagerBar">
				<div class="delete">
					<input type="button" id="nextButton" class="formButton"
						url="clothesOrder/directToOutFactory" value="下一步" />
					<button type="button" onclick="printPreview();" id="printBtn"
						class="formButton" >打印预览</button>

				</div>
				<div class="pager">
					<%@ include file="pager.jsp"%>
				</div>
			</div>
		</form>
	</div>
	
	
</body>
</html>
