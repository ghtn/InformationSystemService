package com.ghtn.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 身份验证拦截器 User: Administrator Date: 13-11-8 Time: 下午5:17
 */
public class LoginValidator extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		// 一些请求不需要验证
		if (uri.indexOf("/InformationSystemService/user/login") < 0
				&& uri.indexOf("/InformationSystemService/resources/") < 0
				&& uri.indexOf("/InformationSystemService/site/") < 0) {
			String account = request.getParameter("account");
			if (request.getSession().getAttribute(account) == null) {
				return false;
			}
		}
		return super.preHandle(request, response, handler);
	}
}
