<%@page import="org.springframework.security.core.userdetails.UsernameNotFoundException"%>
<%@page import="com.aplus.lk.clothes.entity.Admin"%>
<%@page import="com.aplus.lk.clothes.utils.SpringUtil"%>
<%@page import="com.aplus.lk.clothes.service.AdminService"%>
<%@page import="org.springframework.security.authentication.AccountExpiredException"%>
<%@page import="org.springframework.security.authentication.LockedException"%>
<%@page import="org.springframework.security.authentication.DisabledException"%>
<%@page import="org.springframework.security.authentication.BadCredentialsException"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String username = null;
	String message = null;
	final String SPRING_SECURITY_LAST_EXCEPTION = "SPRING_SECURITY_LAST_EXCEPTION";
	
	String error = request.getParameter("error");
	if ("captcha".equalsIgnoreCase(error)) {
		message = "验证码错误,请重新输入!";
	}else if("sessioncontrol".equalsIgnoreCase(error)){
		message = "该账号已在其他地方登陆!";
	} else {
		Exception springSecurityLastException = (Exception) session.getAttribute(SPRING_SECURITY_LAST_EXCEPTION);
		if (springSecurityLastException != null) {
			if (springSecurityLastException instanceof BadCredentialsException) {
				message = "您的用户名或密码错误!";
			} else if (springSecurityLastException instanceof DisabledException) {
				message = "您的账号已被禁用,无法登录!";
			} else if (springSecurityLastException instanceof LockedException) {
				message = "您的账号已被锁定,无法登录!";
			} else if (springSecurityLastException instanceof AccountExpiredException) {
				message = "您的账号已过期,无法登录!";
			} else {
				System.out.println(springSecurityLastException);
				message = "出现未知错误,无法登录!";
			}
			session.removeAttribute(SPRING_SECURITY_LAST_EXCEPTION);
		}
	}

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
		<script type="text/javascript" src="<%=basePath%>/resource/common/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/admin/js/base.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/admin/js/admin.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/common/layer/layer.js"></script>
		
	</head>
	<body class="login">
	<script type="text/javascript">

		// 登录页面若在框架内，则跳出框架
		if (self != top) {
			top.location = self.location;
		};
		
		$().ready(function(){
		
			randomImg();
			
			var $loginForm = $("#loginForm");
			var $username = $("#username");
			var $password = $("#password");
			var $captcha = $("#captcha");
			var $isRememberUsername = $("#isRememberUsername");
		
			// 判断"记住用户名"功能是否默认选中,并自动填充登录用户名
			if(getCookie("adminUsername") != null) {
				$isRememberUsername.attr("checked", true);
				$username.val(getCookie("adminUsername"));
				$password.focus();
			} else {
				$isRememberUsername.attr("checked", false);
				$username.focus();
			}
		
			// 提交表单验证,记住登录用户名
			$loginForm.submit( function() {
				if ($username.val() == "") {
					layer.msg("请输入您的用户名!");
					return false;
				}
				if ($password.val() == "") {
					layer.msg("请输入您的密码!");
					return false;
				}
				if ($captcha.val() == "") {
					layer.msg("请输入您的验证码!");
					return false;
				}
				
				if ($isRememberUsername.attr("checked") == true) {
					var expires = new Date();
					expires.setTime(expires.getTime() + 1000 * 60 * 60 * 24 * 7);
					setCookie("adminUsername", $username.val(), expires);
				} else {
					removeCookie("adminUsername");
				}
				
			});
			
		})
		
		function randomImg(){
			var random = new Date().getTime();
			$("#captchaImage").attr("src","<%=basePath%>/valid/image/getValidImage?random="+random);
		}
		
		
		
		<%if (message != null) {%>
			layer.msg("<%=message%>");
		<%}%>

	</script>
	<div class="blank"></div>
	<div class="blank"></div>
	<div class="blank"></div>
	<div class="body">
		<form id="loginForm" action="admin/check" method="post">
            <table class="loginTable">
            	<tr>
            		<td rowspan="3">
            			<img src="" alt="联坤管理中心" />
            		</td>
                    <th>
                    	用户名:
                    </th>
					<td>
                    	<input type="text" id="username" name="j_username" class="formText" />
                    </td>
                </tr>
                <tr>
					<th>
						密&nbsp;&nbsp;&nbsp;码:
					</th>
                    <td>
                    	<input type="password" id="password" name="j_password" class="formText" />
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
                        <input type="button" class="homeButton" value="" onclick="window.open('<%=basePath%>/')" />
                        <input type="submit" class="submitButton" value="登 录" />
                    </td>
                </tr>
            </table>
            <div class="powered">
            </div>
		</form>
	</div>
</body>

</html>
