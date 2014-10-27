package com.ghtn.controller;

import com.ghtn.model.Employee;
import com.ghtn.model.User;
import com.ghtn.service.UserManager;
import com.ghtn.session.UserSession;
import com.ghtn.util.ConstantUtil;
import com.ghtn.util.DigestUtil;
import com.ghtn.util.JsonUtil;
import com.ghtn.util.StringUtil;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.apache.regexp.recompile;
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
 * Created with IntelliJ IDEA. User: Administrator Date: 13-11-1 Time: 上午10:10
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

	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(User user, HttpSession session) {
		if (user != null && !StringUtil.isNullStr(user.getAccount())
				&& !StringUtil.isNullStr(user.getPassword())) {
			if ((user = this.userManager.login(user)) != null) {// 登录成功
				Map<String, Object> map = new HashMap<>();
				map.put("success", true);
				map.put("user", user);
				// 放入session
				session.setAttribute(user.getAccount(),
						JsonUtil.objectToGson(user));
				return map;
			}
		}
		return handleException(null);
	}

	@RequestMapping("/listUserByPage")
	@ResponseBody
	public Map<String, Object> listUserByPage(String account, int start,
			int limit, String condition, String value, HttpSession session) {
		if (!StringUtil.isNullStr(account)) {
			String gson = (String) session.getAttribute(account);
			if (gson != null) {
				User user = JsonUtil.gsonToUser(gson);
				if (user != null && user.getGrade() == ConstantUtil.MAX_GRADE) {// 超级管理员
					Map<String, Object> map = new HashMap<>();
					map.put("total", userManager.getCount(condition, value));
					map.put("items", userManager.listUserByPage(start, limit,
							condition, value));
					return map;
				}
			}

		}
		return handleException(null);
	}

	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> add(String _account, String rePassword,
			User user, HttpSession session) {
		if (user != null && rePassword != null
				&& rePassword.equals(user.getPassword())) {
			String gson = (String) session.getAttribute(user.getAccount());
			if (gson != null) {
				User u = JsonUtil.gsonToUser(gson);
				if (u != null && u.getGrade() == ConstantUtil.MAX_GRADE) {// 超级管理员
					user.setAccount(_account);
					user.setPassword(DigestUtil.encryptByMD5(user.getPassword()));
					user = this.userManager.save(user);
					return operationSuccess();
				}
			}
		}
		return handleException(null);
	}

	@RequestMapping("/updateInfo")
	@ResponseBody
	public Map<String, Object> updateInfo(String _account, String rePassword,
			User user, HttpSession session) {
		if (user != null && rePassword != null
				&& rePassword.equals(user.getPassword())) {
			String gson = (String) session.getAttribute(user.getAccount());
			if (gson != null) {
				User u = JsonUtil.gsonToUser(gson);
				if (u != null && u.getGrade() == ConstantUtil.MAX_GRADE) {// 超级管理员
					user.setAccount(_account);
					if (this.userManager.updateInfo(user)) {
						return operationSuccess();
					}
				}
			}
		}
		return handleException(null);
	}

	@RequestMapping("/updateGrade")
	@ResponseBody
	public Map<String, Object> updateGrade(String _account, User user,
			HttpSession session) {
		if (user != null && !StringUtil.isNullStr(_account)) {
			String gson = (String) session.getAttribute(user.getAccount());
			if (gson != null) {
				User u = JsonUtil.gsonToUser(gson);
				if (u != null && u.getGrade() == ConstantUtil.MAX_GRADE) {// 超级管理员
					if( _account.equals(user.getAccount())){
						user.setGrade(ConstantUtil.MAX_GRADE);
					}
					user.setAccount(_account);
					if (this.userManager.updateGrade(user)) {
						return operationSuccess();
					}
				}
			}
		}
		return handleException(null);
	}

	@RequestMapping("/remove")
	@ResponseBody
	public Map<String, Object> remove(String account, String ids,
			HttpSession session) throws Exception {
		if (!StringUtil.isNullStr(account)) {
			String gson = (String) session.getAttribute(account);
			if (!StringUtil.isNullStr(gson)) {
				User user = JsonUtil.gsonToUser(gson);
				if (user != null && user.getGrade() == ConstantUtil.MAX_GRADE) {// 超级管理员
					String strId[] = ids.split("#");
					for (int i = 0; i < strId.length; i++) {
						this.userManager.remove(Long.parseLong(strId[i]));
					}
					return operationSuccess();
				}
			}
		}
		return handleException(null);
	}

	@RequestMapping("/check")
	@ResponseBody
	public boolean check() throws Exception {
		return true;
	}

	@RequestMapping("/logout")
	@ResponseBody
	public void logout(String account, HttpSession session) throws Exception {
		session.removeAttribute(account);
	}

	@RequestMapping("/updatePassword")
	@ResponseBody
	public boolean updatePassword(String account, String passwordOld,
			String passwordNew, String passwordReNew) throws Exception {
		if (!StringUtil.isNullString(account, passwordOld, passwordNew,
				passwordReNew)) {
			if (passwordNew.equals(passwordReNew)) {
				return this.userManager.updatePassword(account, passwordOld,
						passwordNew);
			}
		}
		return false;
	}

}
