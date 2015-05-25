<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.mimi.zfw.Constants"%>
<%request.setAttribute("ctx", request.getContextPath());%>
<%@include file="../../inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
            <form:form method="POST"  onsubmit="return ckform(this);">
		<ul data-role="listview">
<li class="ui-field-contain"><label for="name" class="label">名称:</label>
	<form:input path="name" /></li>
<li class="ui-field-contain"><label for="password" class="label">密码:</label>
	<form:input path="password" /></li>
<li class="ui-field-contain"><input type="submit" value="提交" /></li>
<li class="ui-field-contain"><a href="javascript:history.go(-1)"
	class="ui-btn ui-btn-inline ui-icon-delete ui-btn-icon-left">取消</a></li>
		</ul>
            </form:form>

 <a href="<c:url value="/user"/>">返回</a>
</body>
<%request.setAttribute("encrypUrl", request.getContextPath()+"/assets/tools/encryption");%>
	<script type="text/javascript" src="${encrypUrl}/RSA.js"></script>
	<script type="text/javascript" src="${encrypUrl}/BigInt.js"></script>
	<script type="text/javascript" src="${encrypUrl}/Barrett.js"></script>
<script type="text/javascript" src="${encrypUrl}/md5.js"></script>
	
	<script type="text/javascript">
function ckform(){
	var thisPwd = document.getElementsByName("passwordDiaplay")[0].value;
	thisPwd = hex_md5(thisPwd);
	bodyRSA();
	var result = encryptedString(key, encodeURIComponent(thisPwd));
	document.getElementsByName("password")[0].value = encodeURIComponent(result);
	return true;
}
var key ;
function bodyRSA(){
	setMaxDigits(130);
	var publicExponent = document.getElementById("publicExponent").value;
	var modulus = document.getElementById("modulus").value;
  	key = new RSAKeyPair(publicExponent,"",modulus); 
}
</script>
</html>