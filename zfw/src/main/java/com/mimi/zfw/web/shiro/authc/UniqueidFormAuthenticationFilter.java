package com.mimi.zfw.web.shiro.authc;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mimi.zfw.util.RSAUtil;
import com.mimi.zfw.web.captcha.GeetestLib;
import com.mimi.zfw.web.shiro.exception.IncorrectCaptchaException;

public class UniqueidFormAuthenticationFilter extends FormAuthenticationFilter {
    
    private GeetestLib geetest;
    
    public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
    public static final String DEFAULT_LOGIN_TYPE_PARAM = "loginType";
    private String captchaParam = DEFAULT_CAPTCHA_PARAM;
    private String loginTypeParam = DEFAULT_LOGIN_TYPE_PARAM;

    private String loginMIUrl = DEFAULT_LOGIN_URL;
    private String successMIUrl = DEFAULT_SUCCESS_URL;


    private static final Logger LOG = LoggerFactory
	    .getLogger(UniqueidFormAuthenticationFilter.class);

    public UniqueidFormAuthenticationFilter() {
    }

    @Override
    /** 
     * 登录验证 
     */
    protected boolean executeLogin(ServletRequest request,
	    ServletResponse response) throws Exception {
	UniqueidUsernamePasswordToken token = createToken(request, response);
	try {
	    /* 图形验证码验证 */
	    doCaptchaValidate((HttpServletRequest) request, token);
	    Subject subject = getSubject(request, response);
	    subject.login(token);// 正常验证
	    LOG.info(token.getUsername() + "登录成功");
	    return onLoginSuccess(token, subject, request, response);
	} catch (AuthenticationException e) {
	    LOG.info(token.getUsername() + "登录失败--" + e);
	    return onLoginFailure(token, e, request, response);
	}
    }

    // 验证码校验
    protected void doCaptchaValidate(HttpServletRequest request,
	    UniqueidUsernamePasswordToken token) {
	boolean gtResult = false;

	if (geetest.resquestIsLegal(request)) {
	    gtResult = geetest.validateRequest(request);
	} else {
	    gtResult = false;
	}

	if (!gtResult) {
	    throw new IncorrectCaptchaException("验证码错误！");
	}
	// if (token.getCaptcha() == null ||
	// !"mimi".equalsIgnoreCase(token.getCaptcha())) {
	// throw new IncorrectCaptchaException("验证码错误！");
	// }
    }

    @Override
    protected UniqueidUsernamePasswordToken createToken(ServletRequest request,
	    ServletResponse response) {
	String username = getUsername(request);
	String password = getPassword(request);
	try {
	    password = RSAUtil.getResult(
		    request.getParameter("publicExponent"),
		    request.getParameter("modulus"),
		    request.getParameter("password"));
	} catch (Exception e) {
	    e.printStackTrace();
	}
	String captcha = getCaptcha(request);
	String loginType = getLoginType(request);
	boolean rememberMe = isRememberMe(request);
	String host = getHost(request);
	return new UniqueidUsernamePasswordToken(username,
		password.toCharArray(), rememberMe, host, captcha, loginType);
    }

    public String getCaptchaParam() {
	return captchaParam;
    }

    public void setCaptchaParam(String captchaParam) {
	this.captchaParam = captchaParam;
    }

    public String getLoginTypeParam() {
	return loginTypeParam;
    }

    public void setLoginTypeParam(String loginTypeParam) {
	this.loginTypeParam = loginTypeParam;
    }

    protected String getCaptcha(ServletRequest request) {
	return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    protected String getLoginType(ServletRequest request) {
	return WebUtils.getCleanParam(request, getLoginTypeParam());
    }

    @Override
    protected void issueSuccessRedirect(ServletRequest request,
	    ServletResponse response) throws Exception {
	if (pathsMatch(getLoginMIUrl(), request)) {
	    WebUtils.redirectToSavedRequest(request, response,
		    getSuccessMIUrl());
	} else {
	    WebUtils.redirectToSavedRequest(request, response, getSuccessUrl());
	}
    }

    @Override
    protected boolean isLoginRequest(ServletRequest request,
	    ServletResponse response) {
	return pathsMatch(getLoginUrl(), request)
		|| pathsMatch(getLoginMIUrl(), request);
    }

    @Override
    protected void redirectToLogin(ServletRequest request,
	    ServletResponse response) throws IOException {
	String curUrl = WebUtils.getPathWithinApplication(WebUtils
		.toHttp(request));
	String loginUrl = getLoginUrl();
	String loginMIUrl = getLoginMIUrl();
	String[] urls = loginMIUrl.split("/");
	String miUrl = "";
	for (int i = 0; i < urls.length; i++) {
	    if (urls[i].length() != 0) {
		miUrl = urls[i];
		break;
	    }
	}
	if (curUrl.indexOf("/" + miUrl + "/") != -1
		|| (curUrl.lastIndexOf("/" + miUrl) + miUrl.length()) == (curUrl
			.length() - 1)) {
	    WebUtils.issueRedirect(request, response, loginMIUrl);
	} else {
	    WebUtils.issueRedirect(request, response, loginUrl);
	}
    }
    public String getLoginMIUrl() {
	return loginMIUrl;
    }

    public void setLoginMIUrl(String loginMIUrl) {
	this.loginMIUrl = loginMIUrl;
    }

    public String getSuccessMIUrl() {
	return successMIUrl;
    }

    public void setSuccessMIUrl(String successMIUrl) {
	this.successMIUrl = successMIUrl;
    }

    public GeetestLib getGeetest() {
	return geetest;
    }

    public void setGeetest(GeetestLib geetest) {
	this.geetest = geetest;
    }

    // 保存异常对象到request
    // @Override
    // protected void setFailureAttribute(ServletRequest request,
    // AuthenticationException ae) {
    // request.setAttribute(getFailureKeyAttribute(), ae);
    // }
}
