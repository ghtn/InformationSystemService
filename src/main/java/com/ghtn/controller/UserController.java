package com.ghtn.controller;

import com.ghtn.model.User;
import com.ghtn.service.UserManager;
import com.ghtn.session.UserSession;
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

import java.util.Date;
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
    public ModelAndView login(User user, HttpSession session, HttpServletResponse response) {
    	String viewName = "login";
    	Map<String, Object> map = new HashMap<String, Object>();
    	user.setPassword(DigestUtil.encryptByMD5(user.getPassword()));
    	if((user = this.userManager.login(user)) != null){
    		viewName = "index";
    		UserSession us = new UserSession();
    		us.setId(user.getId());
    		us.setName(user.getName());
    		us.setPassword(user.getPassword());
			session.setAttribute(user.getName(), us);
		}
    	map.put("result", viewName);
    	return new ModelAndView(viewName, map);
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
