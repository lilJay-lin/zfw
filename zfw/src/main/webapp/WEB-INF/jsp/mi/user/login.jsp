<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("ctx", request.getContextPath());
%>
<%@include file="../../inc/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="${ctx}/assets/font-awesome/css/font-awesome.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/mi.css"/>

		<title>登录</title>
	</head>
	<body>
		<div class="login theme">
			<h2>登录</h2>
			<form class="login-form" action="{ctx}/user/login" method="post">
				<fieldset>
					<input type="hidden" name="publicExponent" id="publicExponent" value="${publicExponent }" />
					<input type="hidden" name="modulus" id="modulus" value="${modulus }"  />
					<input type="text" id="txtname" name="name" max="16" min="4" maxlength="16" error="用户名长度4~16只能包含小写字母、数字、下划线并以小写字母开头" 
					patterns = "^[a-z]([a-zA-Z0-9_]){3,15}$" require="require" require_msg ="用户名不能为空"  placeholder="输入用户名" />
					<input type="password" id="txtpsw" name="password" max="32" min="6"  error="密码长度6~32只能包含大小写字母、数字、部分特殊符号 !@#$%^&*()" 
					require="require" require_msg ="密码不能为空" patterns = "^[A-Za-z0-9\!\@\#\$\%\^\&\*\(\)]*$" placeholder="输入密码" />
					
					<label for="remember" class="login-form-rememver">
						<div class="checker">
							<span><input type="checkbox" id="remember"/>
							</span>
						</div>
						记住密码
					</label>
					
					<button type="button" class="btn" onclick="submit()">登录</button>
				</fieldset>
			</form>
		</div>
	</body>
	<script src="${ctx}/assets/js/jquery-1.10.2.min.js" type="text/javascript" charset="utf-8"></script>
</html>
