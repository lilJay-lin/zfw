package com.mimi.zfw.web.shiro.authc;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UniqueidUsernamePasswordToken extends UsernamePasswordToken {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7886631962594643116L;
	private String captcha;
	private String loginType;
	
	public UniqueidUsernamePasswordToken(){
		super();
	}
	
	public UniqueidUsernamePasswordToken(String username, char[] password,
			boolean rememberMe, String host, String captcha,String loginType) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
		this.loginType = loginType;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	
}
