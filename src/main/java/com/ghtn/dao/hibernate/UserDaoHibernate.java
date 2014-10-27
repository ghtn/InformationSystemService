package com.ghtn.dao.hibernate;

import java.util.List;

import com.ghtn.dao.UserDao;
import com.ghtn.model.Employee;
import com.ghtn.model.User;
import com.ghtn.util.StringUtil;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-11-1 Time: 上午10:11
 * To change this template use File | Settings | File Templates.
 */
@Repository("userDao")
public class UserDaoHibernate extends GenericDaoHibernate<User, Long> implements
		UserDao {
	public UserDaoHibernate() {
		super(User.class);
	}

	@Override
	public User find(String account) {
		Criteria c = getSession().createCriteria(User.class);
		if (!StringUtil.isNullStr(account)) {
			c.add(Restrictions.eq("account", account));
			List<User> list = c.list();
			return list.size() == 1 ? list.get(0) : null;
		}
		return null;
	}

	@Override
	public List<User> listUserByPage(int start, int limit, String condition,
			String value) {
		Criteria c = getSession().createCriteria(User.class);
		if (!StringUtil.isNullStr(condition)) {
			c.add(Restrictions.ilike(condition, value, MatchMode.ANYWHERE));
		}
		return c.setFirstResult(start).setMaxResults(limit)
				.addOrder(Order.asc("id")).list();
	}

	@Override
	public int getCount(String condition, String value) {
		Criteria c = getSession().createCriteria(User.class);
		if (!StringUtil.isNullStr(condition)) {
			c.add(Restrictions.ilike(condition, value, MatchMode.ANYWHERE));
		}
		return c.list().size();
	}

}
