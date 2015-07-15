package com.mimi.zfw.web.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;

public class ZFWLogoutFilter extends LogoutFilter {
	private String redirectUrl = DEFAULT_REDIRECT_URL;
	private String redirectMIUrl = DEFAULT_REDIRECT_URL;

	@Override
	protected String getRedirectUrl(ServletRequest request,
			ServletResponse response, Subject subject) {

		String curUrl = WebUtils.getPathWithinApplication(WebUtils
				.toHttp(request));
		if (curUrl.indexOf(getRedirectMIUrl()) > -1) {
			return getRedirectMIUrl();
		} else {
			return getRedirectUrl();
		}
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getRedirectMIUrl() {
		return redirectMIUrl;
	}

	public void setRedirectMIUrl(String redirectMIUrl) {
		this.redirectMIUrl = redirectMIUrl;
	}
}
