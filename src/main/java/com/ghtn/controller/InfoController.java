package com.ghtn.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ghtn.model.User;
import com.ghtn.service.InfoManager;
import com.ghtn.util.ConstantUtil;
import com.ghtn.util.DigestUtil;
import com.ghtn.util.JsonUtil;
import com.ghtn.util.StringUtil;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-11-1 Time: 上午10:10
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/info")
public class InfoController extends BaseController {

	private static Logger logger = Logger.getLogger(InfoController.class);
	private InfoManager infoManager;

	@Resource
	public void setInfoManager(InfoManager infoManager) {
		this.infoManager = infoManager;
	}

	@RequestMapping("/listField")
	@ResponseBody
	public Map<String, Object> listField(String account, String fieldName,
			HttpSession session) {
		if (!StringUtil.isNullStr(account) && !StringUtil.isNullStr(fieldName)) {
			String gson = (String) session.getAttribute(account);
			if (gson != null) {
				User user = JsonUtil.gsonToUser(gson);
				if (user != null && user.getGrade() == ConstantUtil.MAX_GRADE) {// 超级管理员
					Map<String, Object> map = new HashMap<>();
					map.put("items", this.infoManager.listField(fieldName));
					return map;
				}
			}
		}
		return handleException(null);
	}

	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> add(String account, String fieldName,
			String value, HttpSession session) {
		if (!StringUtil.isNullStr(account) && !StringUtil.isNullStr(fieldName)
				&& !StringUtil.isNullStr(value)) {
			String gson = (String) session.getAttribute(account);
			if (gson != null) {
				User user = JsonUtil.gsonToUser(gson);
				if (user != null && user.getGrade() == ConstantUtil.MAX_GRADE) {// 超级管理员
					if( this.infoManager.addField(fieldName, value)){
						return operationSuccess();
					}
				}
			}
		}
		return handleException(null);
	}
	
	@RequestMapping("/remove")
	@ResponseBody
	public Map<String, Object> remove(String account, String fieldName,
			String value, HttpSession session) {
		if (!StringUtil.isNullStr(account) && !StringUtil.isNullStr(fieldName)
				&& !StringUtil.isNullStr(value)) {
			String gson = (String) session.getAttribute(account);
			if (gson != null) {
				User user = JsonUtil.gsonToUser(gson);
				if (user != null && user.getGrade() == ConstantUtil.MAX_GRADE) {// 超级管理员
					if( this.infoManager.removeField(fieldName, value)){
						return operationSuccess();
					}
				}
			}
		}
		return handleException(null);
	}
}
