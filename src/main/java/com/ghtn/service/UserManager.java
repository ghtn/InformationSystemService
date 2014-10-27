package com.ghtn.service;

import java.util.List;

import com.ghtn.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-1
 * Time: 上午10:12
 * To change this template use File | Settings | File Templates.
 */
public interface UserManager extends GenericManager<User, Long> {
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	User login(User user);
	
	/**
	 * 更改密码
	 * @param account
	 * @param passwordOld
	 * @param passwordNew
	 * @return
	 */
	boolean updatePassword(String account, String passwordOld, String passwordNew);
	
	
	List<User> listUserByPage(int start, int limit, String condition, String value);
	
	int getCount(String condition, String value);
	
	boolean updateInfo(User user);
	boolean updateGrade(User user);
	
}
