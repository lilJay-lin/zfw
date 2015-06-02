<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@page import="com.mimi.zfw.Constants"%> --%>
<%-- <%request.setAttribute("ctx", request.getContextPath());%> --%>
<%-- <%@include file="../../inc/taglib.jsp" %> --%>
<%@include file="../inc/top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="../inc/header.jsp" %>
</head>
<body>
<div class="main">
<header>
		<div class="left"><a href="javascript:void(0);" onclick="history.back(-1);" class="back"><i></i></a></div>
		<div class="cent">登录</div>
<!-- 		<div class="head-icon"></div> -->
	</header>
${errorMessage }<br/>
<div class="error" id="cp">${error}</div>
<form action="" method="post" onsubmit="return ckform(this);">
		<input type="hidden" id="publicExponent" name="publicExponent" value="${publicExponent }" />
		<input type="hidden" id="modulus" name="modulus" value="${modulus }"/>
    <input type="hidden" name="password">
    <input type="hidden" id="loginType" name="loginType" value ="password">
		<div>
			<input type="button" id="ltBtnP" value="普通登录" onclick="setLoginType('password')">
			<input type="button" id="ltBtnC" value="手机动态密码登录" onclick="setLoginType('captcha')">
		</div>
<!--     登录方式：<select name="loginType" > -->
<!--   <option value ="password">普通登录</option> -->
<!--   <option value ="captcha">手机动态密码登录</option> -->
<!-- </select><br/> -->
<%--     用户名：<input type="text" name="name" value="<shiro:principal/>"><br/> --%>
    <input tabindex="1" type="text" id="txtName" name="name" 
				value="请输入用户名/手机/邮箱" class="input" onkeyup="return contentValidated(event,this)" onFocus="if(value==defaultValue){value='';this.style.color='#000';}"
				onblur="if(!value){value=defaultValue;this.style.color='#999'}" style="color:#999999"/>
	
    <input tabindex="1" type="text" id="txtPhoneNum" name="name" 
				value="请填写您希望收到验证码的手机号" class="input" onkeyup="return contentValidated(event,this)" onFocus="if(value==defaultValue){value='';this.style.color='#000';}"
				onblur="if(!value){value=defaultValue;this.style.color='#999'}" style="color:#999999;display:none;" disabled/> 
				
<!--     密码：<input type="password" id="passwordDisplay" name="passwordDisplay"><br/> -->
    <input tabindex="2" type="text" maxlength="20" id="txtPwd"
				name="txtPwd" 
				value="请输入密碼" class="input" onkeyup="return contentValidated(event,this)" onFocus="if(value==defaultValue){value='';this.style.color='#000';this.setAttribute('type','password');}"
				onpaste="return true;" oncontextmenu="return false;"
				oncopy="return false;" oncut="return false;" class="input"
				onkeyup=""
				onblur="if(!value){value=defaultValue;this.style.color='#999';this.setAttribute('type','text');}" />
				<div id="captchaForm" style="display:none">
    验证码：<input type="text" id="captcha" name="captcha" disabled><input type="button" value="获取短信验证码" onclick="getCaptcha(this)"/> <br/>
				</div>
<%--  验证码：<input type="text" name="captcha" value="<shiro:principal/>"><br/> --%>
<!--     自动登录：<input type="checkbox" name="rememberMe" value="true"><br/> -->
    <input type="submit" value="登录">
<script  type="text/javascript" src="http://api.geetest.com/get.php?gt=${geetestId }&product=embed"></script>
</form>
</div>
<a href="${ctx }/user/register">注册</a>
<%--  <a href="<c:url value="/user"/>">返回</a> --%>
</body>
<%-- <c:set var="cssTheme" value="blue"/>   --%>
<%--  <c:choose>  --%>
<%--          <c:when test="${cssTheme=='blue'}">  --%>
<%--              <c:set var="cssMainColor" value="blue"/>  --%>
<%--          </c:when>  --%>
<%--          <c:when test="${cssTheme=='red'}">  --%>
<%--              <c:set var="cssMainColor" value="red"/>  --%>
<%--          </c:when>  --%>
<%--          <c:when test="${cssTheme=='green'}">  --%>
<%--              <c:set var="cssMainColor" value="green"/>  --%>
<%--          </c:when>  --%>
<%--          <c:otherwise>  --%>
<%--              <c:set var="cssMainColor" value="blue"/>  --%>
<%--          </c:otherwise>  --%>
<%--      </c:choose>  --%>
<%@include file="../inc/bottom.jsp" %>
<%request.setAttribute("encrypUrl", request.getContextPath()+"/assets/tools/encryption");%>
<script type="text/javascript" src="${encrypUrl}/RSA.js"></script>
<script type="text/javascript" src="${encrypUrl}/BigInt.js"></script>
<script type="text/javascript" src="${encrypUrl}/Barrett.js"></script>
<script type="text/javascript" src="${encrypUrl}/md5.js"></script>

<script type="text/javascript">
function ckform(){
	var thisPwd = document.getElementsByName("passwordDisplay")[0].value;
	var thisName = document.getElementsByName("name")[0].value;
    var bValid = true;
    bValid = bValid && checkLength( thisName, "用户名", 5, 16 );
//     bValid = bValid && checkLength( email, "email", 6, 80 );
    bValid = bValid && checkLength( thisPwd, "密码", 5, 16 );

    bValid = bValid && checkRegexp( thisName, /^[a-z]([0-9a-z_])+$/i, "只包含小写字母，数字及下划线，并以小写字母开头" );
    // From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
//     bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com" );
    bValid = bValid && checkRegexp( thisPwd, /^([0-9a-zA-Z])+$/, "只包含小写字母及数字" );
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

function RSAEncrypt() {
	var thisPwd = document.getElementsByName("passwordDisplay")[0].value;
	thisPwd = hex_md5(thisPwd);
	setMaxDigits(130);
	var publicExponent = document.getElementById("publicExponent").value;
	var modulus = document.getElementById("modulus").value;
	var key = new RSAKeyPair(publicExponent, "", modulus);
	var result = encryptedString(key, encodeURIComponent(thisPwd));
	document.getElementsByName("password")[0].value = encodeURIComponent(result);
}
function checkRegexp( val, regexp, n ) {
    if ( !( regexp.test( val ) ) ) {
      updateTips( n );
      return false;
    } else {
      return true;
    }
  }
function checkLength( val, name, min, max ) {
    if ( val.length > max || val.length < min ) {
      updateTips( name + " 长度必须在 " +
        min + " 到  " + max +  " 之间" );
      return false;
    } else {
      return true;
    }
  }
var wait=60; 
function time(o) { 
    if (wait == 0) { 
      o.removeAttribute("disabled");      
      o.value="免费获取验证码"; 
      wait = 60; 
    } else { 
      o.setAttribute("disabled", true); 
      o.value=wait+"秒后可以重新发送"; 
      wait--; 
      setTimeout(function() { 
        time(o) 
      }, 
      1000) 
    } 
  } 
  function getCaptcha(element){
	  
  }
  function gt_custom_ajax(result, selector) {
		var challenge = selector(".geetest_challenge").value;
		var validate = selector(".geetest_validate").value;
		var seccode = selector(".geetest_seccode").value;
//	 	alert(result+"_"+challenge+"_"+validate+"_"+seccode);
	if (result) {
	    setTimeout(function() {
	      selector(".gt_refresh_button").click();
	    }, 1000)
	    //当验证成功时,延迟1秒自动刷新验证码
	  }
	    //如果 result 为 false 的话，则禁用提交按钮等
	}
  function setLoginType(type){
	  if(type=="password"){
		  document.getElementById("ltBtnP").style.color = "red";
		  document.getElementById("ltBtnC").style.color = "black";

		  document.getElementById("txtName").style.display = "block";
		  document.getElementById("txtName").disabled = false;
		  document.getElementById("txtPwd").style.display = "block";
		  document.getElementById("txtPwd").disabled = false;
		  
		  document.getElementById("txtPhoneNum").style.display = "none";
		  document.getElementById("txtPhoneNum").disabled = true;
		  document.getElementById("captchaForm").style.display = "none";
		  document.getElementById("captcha").disabled = true;
	  }else{
		  document.getElementById("ltBtnP").style.color = "black";
		  document.getElementById("ltBtnC").style.color = "red";

		  document.getElementById("txtPhoneNum").style.display = "block";
		  document.getElementById("txtPhoneNum").disabled = false;
		  document.getElementById("captchaForm").style.display = "block";
		  document.getElementById("captcha").disabled = false;
		  
		  document.getElementById("txtName").style.display = "none";
		  document.getElementById("txtName").disabled = true;
		  document.getElementById("txtPwd").style.display = "none";
		  document.getElementById("txtPwd").disabled = true;
	  }
	  document.getElementById("publicExponent").value = type;
  }
  function contentValidated(event,element){
	  
  }

</script>
</html>