package com.ghtn.dao;

import java.util.List;

import com.ghtn.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: lh
 * Date: 13-11-1
 * Time: 上午10:10
 * To change this template use File | Settings | File Templates.
 */
public interface UserDao extends GenericDao<User, Long>{
	/**
	 * 根据账号获取用户
	 * @param account
	 * @return
	 */
	User find(String account);
	
	List<User> listUserByPage(int start, int limit, String condition, String value);
	
	int getCount(String condition, String value);
}
