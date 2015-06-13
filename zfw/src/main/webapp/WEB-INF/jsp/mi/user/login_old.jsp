<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.mimi.zfw.Constants"%>
<%
    request.setAttribute("ctx", request.getContextPath());
%>
<%@include file="../../inc/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	${errorMessage }
	<br />
	<div class="error">${error}</div>
	<form action="" method="post" onsubmit="return ckform(this);">
		<ul class="inputlist">
			<li id="liname"><label> 用户名</label><input
				tabindex="1" type="text" id="txtUsername" name="txtUsername"
				value="" class="input" onkeyup="return contentValidated(event,this)"
				onblur="return checkElement(this)" /> <span style="width: auto;"
				id="Span1"> <label id="nameError"
					style="color: Red; width: auto;"> </label>
			</span></li>
			<li id="liPwd"><label> 登录密码</label><input
				tabindex="2" type="password" maxlength="20" id="txtPwd"
				name="txtPwd" onpaste="return false;" oncontextmenu="return false;"
				oncopy="return false;" oncut="return false;" class="input"
				onkeyup="return contentValidated(event,this)"
				onblur="return checkElement(this)" /><span style="width: auto;"
				id="pwdArea"><label id="pwdError"
					style="color: Red; width: auto;"> </label> </span></li>
		</ul>
		自动登录：<input type="checkbox" name="rememberMe" value="true"><br />
		<input type="submit" value="登录">
		<script async type="text/javascript"
			src="http://api.geetest.com/get.php?gt=${geetestId }&product=embed"></script>
	</form>
	<%--  <a href="<c:url value="/user"/>">返回</a> --%>
</body>
<%
    request.setAttribute("encrypUrl", request.getContextPath()
					+ "/assets/tools/encryption");
%>
<script type="text/javascript" src="${encrypUrl}/RSA.js"></script>
<script type="text/javascript" src="${encrypUrl}/BigInt.js"></script>
<script type="text/javascript" src="${encrypUrl}/Barrett.js"></script>
<script type="text/javascript" src="${encrypUrl}/md5.js"></script>

<script type="text/javascript">
	function ckform() {
		var thisPwd = document.getElementsByName("passwordDisplay")[0].value;
		thisPwd = hex_md5(thisPwd);
		bodyRSA();
		var result = encryptedString(key, encodeURIComponent(thisPwd));
		document.getElementsByName("password")[0].value = encodeURIComponent(result);
		return true;
	}
	var key;
	function bodyRSA() {
		setMaxDigits(130);
		var publicExponent = document.getElementById("publicExponent").value;
		var modulus = document.getElementById("modulus").value;
		key = new RSAKeyPair(publicExponent, "", modulus);
	}
	var textNameArr = ["txtUsername", "txtEmail", "txtPwd", "txtRePwd", "txtTel", "txtQQ"];
	var textErrorArr = ["nameError", "emailError", "pwdError", "repwdError", "telError", "qqError"];
	var regArr = [/([^a-z0-9_])+/g, /([^a-zA-Z0-9\@\._-])+/g, /([^A-Za-z0-9\!\@\#\$\%\^\&\*\(\)])+/g, /([^A-Za-z0-9\!\@\#\$\%\^\&\*\(\)])+/g, /([^0-9])+/g, /([^0-9])+/g];
	var regFormatArr = [/^[a-z]([a-z0-9_]){3,32}/, /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/, /^[A-Za-z0-9\!\@\#\$\%\^\&\*\(\)]{6,32}$/, /^[A-Za-z0-9\!\@\#\$\%\^\&\*\(\)]{6,32}$/, /^[0-9]*$/, /^[0-9]*$/];
	var formatErrorArr = ["用户名长度4~16只能包含小写字母、数字、下划线并以小写字母开头", "邮箱格式有误", "密码长度6~32只能包含大小写字母、数字、部分特殊符号 !@#$%^&*()", "密码长度6~32只能包含大小写字母、数字、部分特殊符号 !@#$%^&*()", "电话格式有误", "QQ格式有误"];

	var regMap = new Object();
	for (var i = 0; i < textNameArr.length; i++) {
		regMap[textNameArr[i]] = regArr[i];
	};

	function contentValidated(event, element) {
		var char_code = event.charCode ? event.charCode : event.keyCode;
		if (char_code >= 37 && char_code <= 40) {
			return true;
		}
		var str = element.value;
		if (str) {
			element.value = str.replace(regMap[element.name], "");
		}
		return true;
	}

	function checkElementValueFormat(elementId, errorId, regText, errorText) {
		var flag = true;
		var element = document.getElementById(elementId);
		var errorLabel = document.getElementById(errorId);
		var reg = regText;
		if (!reg.test(element.value)) {
			errorLabel.innerHTML = errorText;
			flag = false;
		} else {
			errorLabel.innerHTML = "";
			flag = true;
		}
		return flag;
	}

	function md5Exce(str) {
		var pwd = hex_md5(str);
		pwd = hex_md5(pwd);
		return pwd;
	}

	function checkElement(element) {
		for (var i = 0; i < textNameArr.length; i++) {
			if (element.id == textNameArr[i]) {
				var value = document.getElementById(textNameArr[i]).value;
				var errorLabel = document.getElementById(textErrorArr[i]);
				if (i < 4 && !value) {
					errorLabel.innerHTML = "不能为空！";
				} else {
					errorLabel.innerHTML = "";
				}
				if (!errorLabel.innerHTML) {
					checkElementValueFormat(textNameArr[i], textErrorArr[i], regFormatArr[i], formatErrorArr[i]);
					if (i == 0 && !errorLabel.innerHTML) {
						UsernameValid();
					}
					if (i == 3 || i == 2) {
						var pwd = document.getElementById(textNameArr[2]).value;
						var repwd = document.getElementById(textNameArr[3]).value;
						var pwdError = document.getElementById(textErrorArr[2]);
						var repwdError = document.getElementById(textErrorArr[3]);
						if (pwd && repwd && !pwdError.innerHTML && !repwdError.innerHTML) {
							if (pwd != repwd) {
								repwdError.innerHTML = "密码不一致！";
							}
						}
					}
				}
				if (!errorLabel.innerHTML) {
					return true;
				}
			}
		}
		return false;
	}
	function checkAll() {
		var access = true;
		for (var i = 0; i < textNameArr.length; i++) {
			access = checkElement(document.getElementById(textNameArr[i])) ? access : false;
		}
		if (access) {
			regist();
		}

	}
	function regist() {
		var nameError = document.getElementById(textErrorArr[0]);
		var name = document.getElementById(textNameArr[0]).value;
		var email = document.getElementById("txtEmail").value;
		var mima = document.getElementById("txtPwd").value;
		var tel = document.getElementById("txtTel").value;
		var qq = document.getElementById("txtQQ").value;
		var type = "normal";
		var urlOp = "ID=-1&LoginTimes=0&UserName=" + name + "&Password=" + md5Exce(mima) + "&Tel=" + tel + "&QQ=" + qq + "&Email=" + email + "&Leixing=" + type + "&UserIcon=&Nicheng=" + name + "&OpenID=&tkn=" + getTkn();
		urlOp = encodeURI(urlOp);
		$.ajax({
			async: true,
			url: 'Service.asmx/SaveSanga_User?' + urlOp,
			dataType: "json",
			success: function (data) {
				if (data.success) {
					nameError.innerHTML = "注册成功，正跳转到主页.....";
					addCookie("userName", data.user.Nicheng);
					addCookie("userID", data.user.ID);
					setTimeout(function () { window.location.href = 'index.html'; }, 500);
					//denglu(name, mima);
				} else {
					nameError.innerHTML = data.msg;
				}
			}
		});
	}

	function UsernameValid() {
		var nameError = document.getElementById(textErrorArr[0]);
		var nameElement = document.getElementById(textNameArr[0]);
		var name = nameElement.value;
		if (!name) {
			nameError.innerHTML = "用户名不能为空";
			return false;
		}
		var urlOp = "UserName=" + name + "&tkn=" + getTkn();
		urlOp = encodeURI(urlOp);
		var result = false;
		$.ajax({
			async: false,
			url: 'Service.asmx/UserNameExist?' + urlOp,
			dataType: "json",
			success: function (data) {
				if (data) {
					nameError.innerHTML = "用户名已存在。";
				}
				else {
					nameError.innerHTML = "";
					result = true;
				}
			},
			error: function (data) {
				nameError.innerHTML = "校验请求失败！";
			}
		});
		return result;
	}
</script>
</html>