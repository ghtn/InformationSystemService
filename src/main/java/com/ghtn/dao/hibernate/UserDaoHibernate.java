package com.ghtn.dao.hibernate;

import java.util.List;

import com.ghtn.dao.UserDao;
import com.ghtn.model.User;
import com.ghtn.util.StringUtil;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-1
 * Time: 上午10:11
 * To change this template use File | Settings | File Templates.
 */
@Repository("userDao")
public class UserDaoHibernate extends GenericDaoHibernate<User, Long> implements UserDao {
    public UserDaoHibernate() {
        super(User.class);
    }
    @Override
    public boolean login(User user) {
    	// TODO Auto-generated method stub
    	Criteria c = getSession().createCriteria(User.class);
		if( !StringUtil.isNullStr(user.getName())){
			c.add(Restrictions.eq("name", user.getName()));
		}
		if( !StringUtil.isNullStr(user.getPassword())){
			c.add(Restrictions.eq("password", user.getPassword()));
		}
	    return c.list().size() == 1 ? true : false;
    }
    

	@Override
	public boolean updatePassword(String name, String passwordOld, String passwordNew) {
		// TODO Auto-generated method stub
		Criteria c = getSession().createCriteria(User.class);
		if( !StringUtil.isNullStr(name)){
			c.add(Restrictions.eq("name", name));
		}
		if( !StringUtil.isNullStr(passwordOld)){
			c.add(Restrictions.eq("password", passwordOld));
		}
		List list = c.list();
		if( list.size() == 1){
			User user = (User) list.get(0);
			user.setPassword(passwordNew);
			this.save(user);
			return true;
		}
		return false;
	}

}
