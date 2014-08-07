package com.ghtn.controller;

import com.ghtn.model.User;
import com.ghtn.service.UserManager;
import com.ghtn.util.DigestUtil;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-1
 * Time: 上午10:10
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private static Logger logger = Logger.getLogger(UserController.class);
    private UserManager userManager;

    @Resource
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public Map<String, Object> addUser(User user) {
        userManager.save(user);
        return operationSuccess();
    }
    
    @RequestMapping("/login")
    public ModelAndView login(User user, HttpSession session) {
    	String viewName = "login";
    	user.setPassword(DigestUtil.encryptByMD5(user.getPassword()));
    	if(this.userManager.login(user)){
    		viewName = "index";
			session.setMaxInactiveInterval(60*60*12);// 保存12小时
			session.setAttribute(user.getName(), user.getName());
		}
    	return new ModelAndView(viewName);
    } 
    
    @RequestMapping("/check")
	@ResponseBody
	public boolean check()throws Exception{
		return true;
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public void logout(String userName, HttpSession session)throws Exception{
		session.removeAttribute(userName);
	}
	

	@RequestMapping("/updatePassword")
	@ResponseBody
	public boolean updatePassword(String name, String passwordOld, String passwordNew, String passwordReNew)throws Exception{
		passwordNew = DigestUtil.encryptByMD5(passwordNew);
		passwordReNew = DigestUtil.encryptByMD5(passwordReNew);
		passwordOld = DigestUtil.encryptByMD5(passwordOld);
		if( passwordNew.equals(passwordReNew)){
			if( this.userManager.updatePassword(name, passwordOld, passwordNew)){
				return true;
			}
		}
		return false;
	}
	
}
