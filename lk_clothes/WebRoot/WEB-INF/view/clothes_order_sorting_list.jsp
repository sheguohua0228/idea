<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>洗涤跟踪交接表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="description" content="This is my page">
	<link rel="icon" href="favicon.ico" type="image/x-icon" />
	<link href="<%=basePath%>/resource/admin/css/base.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>/resource/admin/css/admin.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.pager.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resource/admin/js/base.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resource/admin/js/admin.js"></script>
	
	<script type="text/javascript" src="<%=basePath%>/resource/common/layer/layer.js"></script>
	
	 
	<style media="print">
		.bar,.listBar,.pagerBar,.checkitem,.check{display: none;}
		.noprint{display: none};
	</style>
	<style type="text/css">
	.list .listTable td{
		    border-bottom: solid 1px #c6c9ca;
    		border-right: solid 1px #c6c9ca;
    		font-size: 12px;
		}
		.list .listTable th{text-align: center;font-size: 12px;}
		
		/* .listTable{*table-layout:fixed ; }
		 .list .listTable th span{padding: 0px;} */
		 .checkitem{border-left:1px solid #c6c9ca;}
	</style>
  </head>
  <body class="list" onload="this.focus();">
  <OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" id="wb" name="wb" style="display: none;" ></OBJECT>
	<div class="bar">
		已分拣订单列表&nbsp;总记录数: ${pager.totalRecords} (共${pager.totalPages}页)&nbsp;&nbsp;
	</div>
	<div class="body">
		<form id="listForm" action="clothesOrder/sorting${orderIds}" method="post">
			<div class="listBar">
				<label>查找: </label>
				子条形码：
				<input id="barCode" type="text" name="barCode" value="${barCode }" />
				用户姓名：
				<input type="text" name="userName" value="${userName }" />
				电话号码：
				<input type="text" name="phoneNumber" value="${phoneNumber }" />
				洗涤方式：
				<%-- <select name="washType">
					<option value="">请选择.</option>
					<option value="干洗"  <c:if test="${washType=='干洗' }">selected="selected"</c:if>>干洗</option>
					<option value="水洗" <c:if test="${washType=='水洗' }">selected="selected"</c:if>>水洗</option>
					<option value="手洗" <c:if test="${washType=='手洗' }">selected="selected"</c:if>>手洗</option>
					<option value="特殊处理" <c:if test="${washType=='特殊处理' }">selected="selected"</c:if>>特殊处理</option>
				
				</select> --%>
				 
				<input type="checkbox" name="washType" value="干洗" />干洗
				<input type="checkbox" name="washType" value="水洗"/>水洗
				<input type="checkbox" name="washType" value="手洗"/>手洗
				<input type="checkbox" name="washType" value="特殊处理"/>特殊处理
				<input type="button" id="searchButton" class="formButton" value="搜 索" />
				&nbsp;&nbsp;
				<label>每页显示: </label>
				 <select name="pageSize" id="pageSize">
					<option value="19" <c:if test="${pager.pageSize == 19 }">selected</c:if>> 
						19
					</option>
					<option value="38" <c:if test="${pager.pageSize == 38 }">selected</c:if>>
						38
					</option>
				</select>
			</div>
			<!--startprint-->
			<table id="listTable" class="listTable">
				<tr>
					<th class="check" rowspan="2">
						<input type="checkbox" class="allCheck" />
					</th>
					<th rowspan="2">
						<label>序号</label>
					</th>
					<th rowspan="2">
						<span style="padding: 0px 10px;">姓&nbsp;&nbsp;名</span>
					</th>
					<th rowspan="2">
						<label>&nbsp;衣物类型&nbsp;</label>
					</th>
					<th rowspan="2">
						<span>条形码</span>
					</th>
					<th rowspan="2">
						<span>品牌</span>
					</th>
					<th rowspan="2">
						<span>颜色</span>
					</th>
					
					<th rowspan="2">
						<span>配件说明</span>
					</th>
					<th colspan="3">分拣岗</th>
					
					
					<th colspan="2">洗涤岗</th>
					
					 <th colspan="2">烘干岗</th>
					 <th colspan="2">熨烫岗</th>
					 <th colspan="2">整理岗</th>
					 <th colspan="3">质检岗</th>
					
					 
				</tr>
				<tr><th>
						<span>洗涤方式</span>
					</th>
					
					<th>
						<span>分拣说明</span>
					</th>
					<th>签字</th>
					<th>
						<span>洗涤说明</span>
					</th>
					<th>签字</th>
					<th>
						<span>烘干说明</span>
					</th>
					<th>签字</th>
					<th>
						<span>熨烫说明</span>
					</th>
					<th>签字</th>
					<th>
						<span>整理说明</span>
					</th>
					<th>签字</th>
					<th>
						<span>质检说明</span>
					</th>
					<th>出厂日期</th>
					<th>签字</th>
					</tr>
				<c:if test="${fn:length(pager.dataList) > 0 }">
				<c:forEach var="clothes" items="${pager.dataList}" varStatus="status">
					<tr class="noprint">
						<td class="checkitem">
							<input type="checkbox" name="ids" value="${clothes.clothesOrderId}" />
						</td>
						<td>${status.count}</td>
						<td>${clothes.username}</td>
						<td>
							${clothes.clothesName}
						</td>
						<td>
							${clothes.childBarCode}
						</td>
						<td>
							${clothes.brand} 
						</td>
						<td>
							${clothes.color }
						</td>
						 <td>
							${clothes.partsDesc }
						</td><td>
							${clothes.washType }
						</td><td>
							${clothes.sortingDesc }
						</td>
						<td></td>
						<td>
							${clothes.washDesc }
						</td><td></td><td>
							${clothes.dryDesc }
						</td><td></td><td>
							${clothes.ironingDesc }
						</td><td></td>
						<td>
							${clothes.arrangeDesc }
						</td><td></td>
						<td>
							${clothes.qcDesc }
						</td>
						 <td></td>
						 <td></td>
					</tr>
				</c:forEach>
				</c:if>
			</table>
			<!--endprint-->
			<c:if test="${fn:length(pager.dataList) > 0 }">
				<div class="pagerBar">
				<div class="delete">
						<input type="button" id="nextButton" class="formButton"
						 url="clothesOrder/directFactoryTowash" value="下一步" disabled/>
						 <button type="button" onclick="printPreview();" id="printBtn"  class="formButton" disabled>打印预览</button>
					
					</div>
					<div class="pager">
						<%@ include file="pager.jsp"%>
					</div>
				</div>
			</c:if>
			<c:if test="${fn:length(pager.dataList) <= 0 }">
				<div class="noRecord">没有找到任何记录!</div>
			</c:if><!-- $('#listTable').jqprint(); -->
		</form>
	</div>
	<script>
		$().ready(function(){
			var barCode = $("#barCode");
			barCode.focus();
			barCode.keydown(function(e){
				if(e.keyCode == 13){ // 监听回车按钮
					if(barCode != null && barCode.length > 0){// 获取订单详情
						layer.closeAll();
						layer.open({
							type: 2,
						    title: '订单信息',
						    shadeClose: true,
						    shade: 0.5,
						    offset:  ['50px', '100px'], 
						    area: ['75%', '90%'],
						    content: 'clothesOrder/details?orderStatus=2&barCode='+barCode.val() //iframe的url
						});
						barCode.val("");
					}
				}
			})
		});
		
		 
		  
		function printSet(){  
			 //定义浏览器页面设置———>页边距设置默认值(0.750000*25.4=19.05毫米)
			   var top = '0.1968503937';
			   var bottom = '0.1968503937';
			   var left = '0.1968503937';
			   var right = '0.1968503937';
			  //调用js控件来修改IE的注册信息，设置网页打印的页眉页脚为空且把页边距(上下左右边界)修改为浏览器默认值：19.05毫米
			  //设置网页打印的页眉页脚和页边距为默认值  
			  var HKEY_Root,HKEY_Path,HKEY_Key;
			  HKEY_Root="HKEY_CURRENT_USER";
			  HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
			 try{
		  var Wsh=new ActiveXObject("WScript.Shell");    //vbscript的控件核心文件
		 
		  HKEY_Key="header";
		  Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key, "");    //页眉
		 
		  HKEY_Key="footer";
		  Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key, getNowFormatDate());    //页脚
		  HKEY_Key="margin_top";
		  Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,top); //键值设定--上边边界
		 
		  HKEY_Key="margin_bottom";
		  Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,bottom); //键值设定--下边边界
		  HKEY_Key="margin_left";
		  Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,left); //键值设定--左边边界
		   HKEY_Key="margin_right";
		   Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,right); //键值设定--右边边界
		}catch(e){}
			 
	}
		function printPreview(){
			printSet();
			wb.ExecWB(7,1);
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
</body>
</html>
