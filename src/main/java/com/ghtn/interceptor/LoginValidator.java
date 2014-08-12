package com.ghtn.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 身份验证拦截器
 * User: Administrator
 * Date: 13-11-8
 * Time: 下午5:17
 */
public class LoginValidator extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
    	// 只有登录不需要验证
    	if( request.getRequestURI().indexOf("/user/login") < 0
    			&& request.getRequestURI().indexOf("InformationSystemService/resources/") < 0){
    		String userName = request.getParameter("userName");
    		if(request.getSession().getAttribute(userName) == null){
    			return false;
    		}
    	}
    	return super.preHandle(request, response, handler);
    }
}
