package com.ghtn.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ghtn.dao.InfoDao;
import com.ghtn.model.Info;
import com.ghtn.model.User;
import com.ghtn.util.StringUtil;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-11-1 Time: 上午10:11
 * To change this template use File | Settings | File Templates.
 */
@Repository("infoDao")
public class InfoDaoHibernate extends GenericDaoHibernate<Info, Long> implements
		InfoDao {
	public InfoDaoHibernate() {
		super(Info.class);
	}

	@Override
	public List<Info> listInfo() {
		return getSession().createCriteria(Info.class)
				.addOrder(Order.asc("id")).list();
	}

	@Override
	public Info findByField(String fieldName, String value) {
		Criteria c = getSession().createCriteria(Info.class);
		if (!StringUtil.isNullStr(fieldName) && !StringUtil.isNullStr(value)) {
			c.add(Restrictions.eq(fieldName, value));
			return (Info) c.list().get(0);
		}
		return null;
	}

}
