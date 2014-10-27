package com.ghtn.service.impl;

import java.util.List;

import com.ghtn.dao.GenericDao;
import com.ghtn.dao.UserDao;
import com.ghtn.model.User;
import com.ghtn.service.UserManager;
import com.ghtn.util.DigestUtil;
import com.ghtn.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-11-1 Time: 上午10:12
 * To change this template use File | Settings | File Templates.
 */
@Service("userManager")
public class UserManagerImpl extends GenericManagerImpl<User, Long> implements
		UserManager {

	private UserDao userDao;

	@Autowired
	public UserManagerImpl(UserDao userDao) {
		super(userDao);
		this.userDao = userDao;
	}

	@Override
	public User login(User user) {
		if (user != null && !StringUtil.isNullStr(user.getAccount())
				&& !StringUtil.isNullStr(user.getPassword())) {
			// 密码加密后与再与数据库密码比较
			user.setPassword(DigestUtil.encryptByMD5(user.getPassword()));
			User u = this.userDao.find(user.getAccount());
			if( u.getPassword().equals(user.getPassword())){
				return u;
			}
		}
		return null;
	}

	@Override
	public boolean updatePassword(String account, String passwordOld,
			String passwordNew) {
		if( !StringUtil.isNullString(account, passwordNew, passwordOld)){
			User user = this.userDao.find(account);
			if( user != null && user.getPassword().equals(DigestUtil.encryptByMD5(passwordOld))){
				user.setPassword(DigestUtil.encryptByMD5(passwordNew));
				return this.userDao.save(user) != null;
			}
		}
		return false;
	}

	@Override
	public List<User> listUserByPage(int start, int limit, String condition,
			String value) {
		return this.userDao.listUserByPage(start, limit, condition, value);
	}

	@Override
	public int getCount(String condition, String value) {
		return this.userDao.getCount(condition, value);
	}

	@Override
	public boolean updateInfo(User user) {
		if( user != null && !StringUtil.isNullStr(user.getAccount())){
			User u = this.userDao.find(user.getAccount());
			if( u != null){
				user.setId(u.getId());
				user.setGrade(u.getGrade());
				if( StringUtil.isNullStr(user.getPassword())){
					user.setPassword(u.getPassword());
				}else{
					user.setPassword(DigestUtil.encryptByMD5(user.getPassword()));
				}
				return this.userDao.save(user) != null;
			}
		}
		return false;
	}

	@Override
	public boolean updateGrade(User user) {
		if(user != null && !StringUtil.isNullStr(user.getAccount())){
			User u = this.userDao.find(user.getAccount());
			u.setGrade(user.getGrade());
			return this.userDao.save(u) != null;
		}
		return false;
	}
}
