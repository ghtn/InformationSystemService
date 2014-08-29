package com.ghtn.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ghtn.dao.ContractDao;
import com.ghtn.model.Contract;
import com.ghtn.model.Employee;
import com.ghtn.util.StringUtil;

/**
 * Created by lihe on 14-7-10.
 */
@Repository("contractDao")
public class ContractDaoHibernate extends
		GenericDaoHibernate<Contract, Integer> implements ContractDao {

	public ContractDaoHibernate() {
		super(Contract.class);
	}

	@Override
	public List<Contract> listContractByPage(int start, int limit,
			String queryCondition, String queryValue) {
		Criteria c = getSession().createCriteria(Contract.class);
		if (!StringUtil.isNullStr(queryCondition)) {
			c.add(Restrictions.ilike(queryCondition, queryValue,
					MatchMode.ANYWHERE));
		}
		return c.setFirstResult(start).setMaxResults(limit)
				.addOrder(Order.asc("id")).list();
	}

	@Override
	public Long getCount(String queryCondition, String queryValue) {
		// TODO Auto-generated method stub
		Criteria c = getSession().createCriteria(Contract.class);
		if (!StringUtil.isNullStr(queryCondition)) {
			c.add(Restrictions.ilike(queryCondition, queryValue,
					MatchMode.ANYWHERE));
		}
		return (Long) c.setProjection(Projections.count("id")).uniqueResult();
	}

}
