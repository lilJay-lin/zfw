<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="../inc/header.jsp" %>
</head>
<body>
	<div class="main">
		<header>
			<div class="left">
				<a href="javascript:void(0);" onclick="history.back(-1);"
					class="back"><i></i></a>
			</div>
			<div class="cent">
				<shiro:guest>找回密码</shiro:guest>
				<shiro:authenticated>修改密码</shiro:authenticated>
				</div>
		</header>
		<div class="mLogin">
			<div class="wapForm phone mt10">
				<div class="tips">${error }</div>
				<form action="" method="post">
					<input type="hidden" id="password" name="password"> 
					<input type="hidden" id="publicExponent" name="publicExponent" value="${publicExponent }" > 
					<input type="hidden" id="modulus" name="modulus" value="${modulus }" > 
					<input type="hidden" name="geetest_challenge"> 
					<input type="hidden" name="geetest_validate"> 
					<input type="hidden" name="geetest_seccode"> 
						<input type="text" id="txtPhoneNum" name="phoneNum" value="请输入手机号"
						class="ipt-text mt10" maxlength="11"
						onkeyup="return inputOnKeyup(event,this)"
						onFocus="return inputOnFocus(event,this)"
						onblur="return inputOnBlus(event,this)" style="color: #999999;" />
					<div id="phoneNumError" class="tips"></div>
					<div class="flexbox mt10">
						<input type="text" id="txtCaptcha" name="captcha" value="请输入手机验证码"
							class="ipt-text" maxlength="6"
							onkeyup="return inputOnKeyup(event,this)"
							onFocus="return inputOnFocus(event,this)"
							onblur="return inputOnBlus(event,this)" style="color: #999999;" />
						<input type="button" id="jsGetPhoneCaptchaBtn" class="formbtn01"
							onclick="getPhoneCaptcha(this);" value="获取验证码">
					</div>
					<div id="captchaError" class="tips"></div>

					<input type="text" maxlength="20" id="txtPwd" name="txtPwd"
						value="请输入新密码" class="ipt-text mt10" onpaste="return true;"
						oncontextmenu="return false;" oncopy="return false;"
						oncut="return false;" onkeyup="return inputOnKeyup(event,this)"
						onFocus="return inputOnFocus(event,this)"
						onblur="return inputOnBlus(event,this)" style="color: #999999" />
					<div id="pwdError" class="tips"></div>

					<input type="text" maxlength="20" id="txtPwdCheck"
						name="txtPwdCheck" value="请再输入一次密码" class="ipt-text mt10"
						onpaste="return true;" oncontextmenu="return false;"
						oncopy="return false;" oncut="return false;"
						onkeyup="return inputOnKeyup(event,this)"
						onFocus="return inputOnFocus(event,this)"
						onblur="return inputOnBlus(event,this)" style="color: #999999" />
					<div id="pwdCheckError" class="tips"></div>
				</form>
			</div>
			<div class="captcha">
				<script type="text/javascript"
					src="http://api.geetest.com/get.php?gt=${geetestId }&product=embed"></script>
			</div>
			<a href="javascript:void(0);"
				class="formbtn02 mt10 jsUserSubmit disabled" onclick="submitForm()">确定修改</a>
		</div>
	</div>
</body>
<%@include file="../inc/bottom.jsp" %>
<%request.setAttribute("encrypUrl", request.getContextPath()+"/assets/tools/encryption");%>
<script type="text/javascript" src="${encrypUrl}/RSA.js"></script>
<script type="text/javascript" src="${encrypUrl}/BigInt.js"></script>
<script type="text/javascript" src="${encrypUrl}/Barrett.js"></script>
<script type="text/javascript" src="${encrypUrl}/md5.js"></script>

<script type="text/javascript">
const
PHONE_ID = "txtPhoneNum";
const
CAPTCHA_ID = "txtCaptcha";
const
PWD_ID = "txtPwd";
const
PWD_CHECK_ID = "txtPwdCheck";

const GET_PHONE_CAPTCHA_URL = "${ctx}/public/json/user/getPhoneCaptcha";
const CHECK_PHONE_NUM_URL = "${ctx}/public/json/user/checkPhoneNumValidAndExisted";

const
GET_PHONE_CAPTCHA_WAIT_VALUE = 60;

var textIdArr = [ PHONE_ID, CAPTCHA_ID, PWD_ID, PWD_CHECK_ID ];
var textErrorArr = [ "phoneNumError", "captchaError", "pwdError",
		"pwdCheckError" ];
var notNullArr = [ true, true, true, true ];
var regArr = [ /([^0-9])+/g, /([^0-9])+/g,
		/([^A-Za-z0-9\!\@\#\$\%\^\&\*\(\)])+/g,
		/([^A-Za-z0-9\!\@\#\$\%\^\&\*\(\)])+/g ];
var regFormatArr = [ /^1[0-9]{10}/, /^[0-9]{6}$/,
		/^[A-Za-z0-9\!\@\#\$\%\^\&\*\(\)]{6,32}$/,
		/^[A-Za-z0-9\!\@\#\$\%\^\&\*\(\)]{6,32}$/ ];
var formatErrorArr = [ "电话号码格式有误", "验证码格式有误", "密码至少6位，大小写、数字及对应特殊字符",
		"密码至少6位，大小写、数字及对应特殊字符" ];

var regMap = new Object();
var errorMap = new Object();
var notNullMap = new Object();
var regFormatMap = new Object();
var formatErrorMap = new Object();
for (var i = 0; i < textIdArr.length; i++) {
	regMap[textIdArr[i]] = regArr[i];
	errorMap[textIdArr[i]] = textErrorArr[i];
	notNullMap[textIdArr[i]] = notNullArr[i];
	regFormatMap[textIdArr[i]] = regFormatArr[i];
	formatErrorMap[textIdArr[i]] = formatErrorArr[i];
};

var phoneNumCheckObject = new Object();
phoneNumCheckObject.url = CHECK_PHONE_NUM_URL;
var smoothCaptchObject = new Object();
smoothCaptchObject.captchaReady = false;

function RSAEncrypt() {
	var thisPwd = document.getElementById(PWD_ID).value;
	thisPwd = hex_md5(thisPwd);
	setMaxDigits(130);
	var publicExponent = document.getElementById("publicExponent").value;
	var modulus = document.getElementById("modulus").value;
	var key = new RSAKeyPair(publicExponent, "", modulus);
	var result = encryptedString(key, encodeURIComponent(thisPwd));
	document.getElementById("password").value = encodeURIComponent(result);
}

function inputOnKeyup(event, element) {
	contentValidated(event, element);
	checkElement(element);
	refreshSubmitBtn();
}

function inputOnFocus(event, element) {
	hideDefaultText(element);
}

function inputOnBlus(event, element) {
	showDefaultText(element);
	checkElement(element);
	if (element.id == PHONE_ID) {
		phoneNumValid();
	}
	refreshSubmitBtn();
}

function showDefaultText(element) {
	if (!element.value) {
		element.value = element.defaultValue;
		element.style.color = '#999';
		if ((element.id == PWD_ID || element.id == PWD_CHECK_ID)
				&& element.getAttribute('type') == "password") {
			element.setAttribute('type', 'text');
		}
	}
}

function hideDefaultText(element) {
	element.style.color = '#000';
	if ((element.id == PWD_ID || element.id == PWD_CHECK_ID)
			&& element.getAttribute('type') == "text") {
		element.setAttribute('type', 'password');
	}
	if (element.value == element.defaultValue) {
		element.value = '';
	}
}

function contentValidated(event, element) {
	var char_code = event.charCode ? event.charCode : event.keyCode;
	if (char_code >= 37 && char_code <= 40) {
		return true;
	}
	var str = element.value;
	if (str) {
		element.value = str.replace(regMap[element.id], "");
	}
	return true;
}

function checkElement(element, withoutTips) {
	var ready = true;
	var value = element.value;
	var id = element.id;
	var errorLabel = document.getElementById(errorMap[id]);
	if (!withoutTips) {
		errorLabel.innerHTML = "";
	}
	if (notNullMap[id]) {
		if (!value || value == element.defaultValue) {
			ready = false;
			if (!withoutTips) {
				errorLabel.innerHTML = "不能为空！";
			}
		}
	}
	if (ready && !regFormatMap[id].test(value)) {
		ready = false;
		if (!withoutTips) {
			errorLabel.innerHTML = formatErrorMap[id];
		}
	}
	if(ready && id==PWD_CHECK_ID){
		if(document.getElementById(PWD_ID).value!=value){
			ready = false;
			if (!withoutTips) {
				errorLabel.innerHTML = "两次密码不一致";
			}
		}
	}
	return ready;
}

function phoneNumValid(){
	checkValidAction();
}
function checkValidAction() {
	var elementId;
	var checkObject;
	elementId = PHONE_ID;
	checkObject = phoneNumCheckObject;
	var element = document.getElementById(elementId);
	if (!checkElement(element)) {
		return;
	}
	checkObject.str2Check = element.value;
	if (checkObject.checking) {
		return;
	}
	checkObject.checking = true;
	errorElement = document.getElementById(errorMap[elementId]);
	$
			.ajax({
				type : "GET",
				async : true,
				url : checkObject.url,
				data : {
					name : element.value
				},
				dataType : "json",
				timeout : 10000,
				success : function(data) {
					checkObject.checking = false;
					if (data.success) {
						if (data.valid) {
							if (data.existed) {
								checkObject.accessValue = data.value;
								if (checkObject.accessValue != checkObject.str2Check) {
									checkValidAction();
								} else if (checkObject.toSubmit) {
									submitForm();
								} else if (checkObject.toCaptcha) {
									getPhoneCaptcha($("#jsGetPhoneCaptchaBtn")[0]);
								}
							} else {
								checkObject.accessValue = "";
								errorElement.innerHTML = "该号码未注册";
							}
						} else {
							checkObject.accessValue = "";
							errorElement.innerHTML = "登录名格式有误";
						}
					} else {
						checkObject.accessValue = "";
						errorElement.innerHTML = data.msg;
					}
				},
				error : function(data) {
					checkObject.checking = false;
					errorElement.innerHTML = "校验请求失败！";
				},
				complete : function(data) {
					checkObject.toSubmit = false;
					checkObject.toCaptcha = false;
					refreshSubmitBtn();
				}
			});
}

var waitSeconds = GET_PHONE_CAPTCHA_WAIT_VALUE;
function captchaTimeSpan(element) {
	if (waitSeconds == 0) {
		$(element).removeClass("disabled");
		element.value = "获取验证码";
		waitSeconds = waitDefaultValue;
	} else {
		$(element).addClass("disabled");
		element.value = "重新发送(" + waitSeconds + ")";
		waitSeconds--;
		setTimeout(function() {
			captchaTimeSpan(element)
		}, 1000)
	}
}
function getPhoneCaptcha(element) {
	if ($(element).hasClass("disabled")) {
		return false;
	}
	var phoneNumReady = checkElement(document.getElementById(PHONE_ID));
	if (phoneNumReady && smoothCaptchObject.captchaReady) {
		document.getElementById(errorMap[CAPTCHA_ID]).innerHTML = "";
		if (phoneNumCheckObject.accessValue != document
				.getElementById(PHONE_ID).value) {
			phoneNumCheckObject.toCaptcha = true;
			phoneNumValid();
			return false;
		}
		var errorElement = document.getElementById(errorMap[CAPTCHA_ID]);
		$.ajax({
			type : "GET",
			async : true,
			url : GET_PHONE_CAPTCHA_URL,
			data : {
				phoneNum : phoneNumCheckObject.accessValue,
				geetest_challenge : smoothCaptchObject.challenge,
				geetest_validate : smoothCaptchObject.validate,
				geetest_seccode : smoothCaptchObject.seccode
			},
			dataType : "json",
			success : function(data) {
				if (!data.success) {
					errorElement.innerHTML = data.msg;
				}
			},
			error : function(data) {
				errorElement.innerHTML = "验证码发送失败，请稍后尝试！";
			}
		});
		captchaTimeSpan(element);
		return true;
	} else {
		var errStr = "";
		if (!phoneNumReady && !smoothCaptchObject.captchaReady) {
			errStr = "请输入正确的电话号码和滑动验证图片";
		} else if (!phoneNumReady && smoothCaptchObject.captchaReady) {
			errStr = "请输入正确的电话号码";
		} else if (phoneNumReady && !smoothCaptchObject.captchaReady) {
			errStr = "请滑动验证图片";
		}
		document.getElementById(errorMap[CAPTCHA_ID]).innerHTML = errStr;
	}
	return false;
}
function gt_custom_ajax(result, selector) {
	if (result) {
		smoothCaptchObject.captchaReady = true;
		smoothCaptchObject.challenge = selector(".geetest_challenge").value;
		smoothCaptchObject.validate = selector(".geetest_validate").value;
		smoothCaptchObject.seccode = selector(".geetest_seccode").value;
		$("input[name='geetest_challenge']").val(smoothCaptchObject.challenge);
		$("input[name='geetest_validate']").val(smoothCaptchObject.validate);
		$("input[name='geetest_seccode']").val(smoothCaptchObject.seccode);
		refreshSubmitBtn();
// 		setTimeout(function() {
// 			$(".captcha").hide();
// 		}, 600)
	}
}
function refreshSubmitBtn() {
	var checkObject;
	checkObject = phoneNumCheckObject;
	var validReady = checkObject.accessValue == checkObject.str2Check;
	if (smoothCaptchObject.captchaReady && checkAll(true) && validReady) {
		$(".jsUserSubmit").removeClass("disabled");
	} else {
		$(".jsUserSubmit").addClass("disabled");
	}
}
function checkAll(withoutTips) {
	var ready2submit = true;
	ready2submit = ready2submit
			&& checkElement(document.getElementById(PHONE_ID), withoutTips);
	ready2submit = ready2submit
			&& checkElement(document.getElementById(CAPTCHA_ID),
					withoutTips);
	ready2submit = ready2submit
			&& checkElement(document.getElementById(PWD_ID), withoutTips);
	ready2submit = ready2submit
			&& checkElement(document.getElementById(PWD_CHECK_ID),
					withoutTips);
	return ready2submit;
}
function submitForm() {
	if ($(".jsUserSubmit").hasClass("disabled")) {
		return false;
	}
	var checkObject;
	checkObject = phoneNumCheckObject;
	if (checkObject.checking) {
		checkObject.toSubmit = true;
		return;
	}
	if (smoothCaptchObject.captchaReady && checkAll()) {
		RSAEncrypt();
		var curForm = $(".wapForm.phone").children("form");
		$("#txtPwd").val("");
		$("#txtPwdCheck").val("");
		curForm.submit();
	}
}

$(function(){
	var defaultPhoneNum = "<shiro:principal></shiro:principal>";
	if(defaultPhoneNum){
		var element = document.getElementById(PHONE_ID);
		element.value = defaultPhoneNum;
		hideDefaultText(element);
	}
})
</script>
</html>