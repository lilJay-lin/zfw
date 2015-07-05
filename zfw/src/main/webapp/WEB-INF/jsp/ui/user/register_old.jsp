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
	<form:form method="POST" onsubmit="return ckform(this);">
		<input type="hidden" id="publicExponent" name="publicExponent"
			value="${publicExponent }" />
		<input type="hidden" id="modulus" name="modulus" value="${modulus }" />
		<form:input type="hidden" name="password" path="password" />
		<ul>
			<li><form:input tabindex="1" type="text" path="phoneNum"
					name="phoneNum" value="请输入手机号码"
					onkeyup="return contentValidated(event,this)"
					onFocus="if(value==defaultValue){value='';this.style.color='#000'}"
					onblur="if(!value){value=defaultValue;this.style.color='#999'}  return checkElement(this)"
					style="color:#999999" /></li>
			<li><script async type="text/javascript"
					src="http://api.geetest.com/get.php?gt=${geetestId }&product=embed"></script><br/></li>
			<li><input tabindex="2" type="text" name="passwordDisplay"
				value="请输入密码" onkeyup="return contentValidated(event,this)"
				onFocus="if(value==defaultValue){value='';this.style.color='#000';this.setAttribute('type','password');}"
				onblur="if(!value){value=defaultValue;this.style.color='#999';this.setAttribute('type','text');}  return checkElement(this)"
				style="color: #999999" /><br /></li>
			<li><input tabindex="3" type="text" name="passwordCheck"
				value="请再一次输入密码" onkeyup="return contentValidated(event,this)"
				onFocus="return passwordTypeOnFocus(event,this)"
				onblur="return passwordTypeOnblus(event,this)"
				style="color: #999999" /><br /></li>
		<li><input tabindex="3" type="submit" value="提交" /></li>
		<li><a tabindex="3" href="javascript:history.go(-1)"
			class="ui-btn ui-btn-inline ui-icon-delete ui-btn-icon-left">取消</a></li>
		</ul>
	</form:form>
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
function gt_custom_ajax(result, selector) {
	var challenge = selector(".geetest_challenge").value;
	var validate = selector(".geetest_validate").value;
	var seccode = selector(".geetest_seccode").value;
// 	alert(result+"_"+challenge+"_"+validate+"_"+seccode);
if (result) {
    setTimeout(function() {
      selector(".gt_refresh_button").click();
    }, 1000)
    //当验证成功时,延迟1秒自动刷新验证码
  }
    //如果 result 为 false 的话，则禁用提交按钮等
}
	function ckform() {
		var thisPwd = document.getElementsByName("passwordDisplay")[0].value;
		thisPwd = hex_md5(thisPwd);
		bodyRSA();
		var result = encryptedString(key, encodeURIComponent(thisPwd));
		document.getElementsByName("password")[0].value = encodeURIComponent(result);
		alert(document.getElementsByName("password")[0].value);
		return true;
	}
	var key;
	function bodyRSA() {
		setMaxDigits(130);
		var publicExponent = document.getElementById("publicExponent").value;
		var modulus = document.getElementById("modulus").value;
		key = new RSAKeyPair(publicExponent, "", modulus);
	}

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
	function checkElement(element) {
		return true;
	}
	
	function passwordTypeOnFocus(event,element){
		if(element.value==element.defaultValue){element.value='';element.style.color='#000';element.setAttribute('type','password');}
	}
	
	function passwordTypeOnblus(event,element){
		if(!element.value){element.value=element.defaultValue;element.style.color='#999';element.setAttribute('type','text');} 
		return checkElement(element);
	}
</script>
</html>