<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="utf-8">
	    <base href="<%=basePath%>">
    
	    <title>员工登录</title>
	    
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<link rel="icon" href="favicon.ico" type="image/x-icon" />
		<link href="<%=basePath%>/resource/admin/css/base.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/resource/admin/css/admin.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/admin/js/base.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/admin/js/admin.js"></script>
		
	</head>
	<body class="login">
	<script type="text/javascript">

		// 登录页面若在框架内，则跳出框架
		if (self != top) {
			top.location = self.location;
		};
		
		$().ready(function(){
			$(".submitButton").click(function(){
				var paramter = $("#loginForm").serialize();
				console.log(paramter);
				$.post("employee/login",paramter,function(data){
					if(data.status == 'success'){
						window.location.href = "index";
					}else{
						alert("登录失败，"+data.message);
					}
				},"json");
			})
			randomImg();
		})
		
		function randomImg(){
			console.log("切换验证码");
			var random = new Date().getTime();
			$("#captchaImage").attr("src","<%=basePath%>/valid/image/getValidImage?random="+random);
		}

	</script>
	<div class="blank"></div>
	<div class="blank"></div>
	<div class="blank"></div>
	<div class="body">
		<form id="loginForm" action="" method="post">
            <table class="loginTable">
            	<tr>
            		<td rowspan="3">
            			<img src="" alt="联坤管理中心" />
            		</td>
                    <th>
                    	用户名:
                    </th>
					<td>
                    	<input type="text" id="username" name="username" class="formText" />
                    </td>
                </tr>
                <tr>
					<th>
						密&nbsp;&nbsp;&nbsp;码:
					</th>
                    <td>
                    	<input type="password" id="password" name="password" class="formText" />
                    </td>
                </tr>
                <tr>
                	<th>
                		验证码:
                	</th>
                    <td>
                    	<input type="text" id="captcha" name="validCode" class="formText captcha" />
                   		<img id="captchaImage" class="captchaImage" alt="换一张" onclick="randomImg()" />
                    </td>
                </tr>
                <tr>
                	<td>
                		&nbsp;
                	</td>
                	<th>
                		&nbsp;
                	</th>
                    <td>
                    	<label>
                    		<input type="checkbox" id="isRememberUsername" />&nbsp;记住用户名
                    	</label>
                    </td>
                </tr>
                <tr>
                	<td>
                		&nbsp;
                	</td>
                	<th>
                		&nbsp;
                	</th>
                    <td>
                        <input type="button" class="homeButton" value="" onclick="window.open('<%=basePath%>/')" hidefocus />
                        <input type="button" class="submitButton" value="登 录" hidefocus />
                    </td>
                </tr>
            </table>
            <div class="powered">
            </div>
		</form>
	</div>
</body>

</html>
