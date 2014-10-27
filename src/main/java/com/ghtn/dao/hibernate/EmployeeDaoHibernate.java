package com.ghtn.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ghtn.dao.EmployeeDao;
import com.ghtn.model.Employee;
import com.ghtn.model.Transfer;
import com.ghtn.util.StringUtil;

/**
 * Created by lihe on 14-7-10.
 */
@Repository("employeeDao")
public class EmployeeDaoHibernate extends
		GenericDaoHibernate<Employee, Integer> implements EmployeeDao {

	public EmployeeDaoHibernate() {
		super(Employee.class);
	}

	@Override
	public Employee getEmployeeByIdCard(String idCard) {
		return (Employee) getSession().createCriteria(Employee.class)
				.add(Restrictions.eq("card", idCard)).uniqueResult();
	}

	@Override
	public List<Employee> listEmployeeByPage(int start, int limit,
			String queryCondition, String queryValue, String postState,
			String retire) {
		// TODO Auto-generated method stub
		Criteria c = getSession().createCriteria(Employee.class);
		if (!StringUtil.isNullStr(queryCondition)) {
			c.add(Restrictions.ilike(queryCondition, queryValue,
					MatchMode.ANYWHERE));
		}
		if (!StringUtil.isNullStr(retire)) {
			c.add(Restrictions.ilike("warn", retire, MatchMode.ANYWHERE));
		}
		if (!StringUtil.isNullStr(postState)) {
			c.add(Restrictions
					.ilike("postState", postState, MatchMode.ANYWHERE));
		}
		return c.setFirstResult(start).setMaxResults(limit)
				.addOrder(Order.asc("id")).list();
	}

	@Override
	public Long getCount(String queryCondition, String queryValue,
			String postState, String retire) {
		// TODO Auto-generated method stub
		Criteria c = getSession().createCriteria(Employee.class);
		if (!StringUtil.isNullStr(queryCondition)) {
			c.add(Restrictions.ilike(queryCondition, queryValue,
					MatchMode.ANYWHERE));
		}
		if (!StringUtil.isNullStr(postState)) {
			c.add(Restrictions
					.ilike("postState", postState, MatchMode.ANYWHERE));
		}
		if (!StringUtil.isNullStr(retire)) {
			c.add(Restrictions.ilike("warn", retire, MatchMode.ANYWHERE));
		}
		return (Long) c.setProjection(Projections.count("id")).uniqueResult();
	}

	@Override
	public List<Employee> getEmployeesById(String ids) {
		// TODO Auto-generated method stub
		List<Employee> list = new ArrayList<Employee>();
		String str[] = ids.split("_");
		for (int i = 0; i < str.length; i++) {
			list.add(this.getEmployeeById(Integer.parseInt(str[i])));
		}
		return list;
	}

	private Employee getEmployeeById(int id) {
		return (Employee) getSession().createCriteria(Employee.class)
				.add(Restrictions.eq("id", id)).uniqueResult();
	}

	@Override
	public List<Employee> listTransferEmployeeByPage(int start, int limit,
			String queryCondition, String queryValue) {
		List<Transfer> lists = getSession().createCriteria(Transfer.class)
				.setFirstResult(start).setMaxResults(limit).list();
		// 得到所有调动记录
		if (lists != null && lists.size() > 0) {
			// 去掉重复的身份证
			List<String> cards = new ArrayList<String>();
			for (Transfer transfer : lists) {
				boolean exist = false;
				for (String string : cards) {
					if (transfer.getCard().equals(string)) {
						exist = true;
						break;
					}
				}
				if (!exist) {
					cards.add(transfer.getCard());
				}
			}
			// 根据身份证查询人员
			if (cards.size() > 0) {
				List<Employee> list = new ArrayList<Employee>();
				for (String string : cards) {
					Employee e = this.getEmployeeByIdCard(string);
					if (e != null) {
						list.add(e);
					}
				}
				return list;
			}
		}
		return null;
	}

	@Override
	public Long getTransferCount(String queryCondition, String queryValue) {
		List<Transfer> lists = getSession().createCriteria(Transfer.class)
				.list();
		// 得到所有调动记录
		if (lists != null && lists.size() > 0) {
			// 去掉重复的身份证
			List<String> cards = new ArrayList<String>();
			for (Transfer transfer : lists) {
				boolean exist = false;
				for (String string : cards) {
					if (transfer.getCard().equals(string)) {
						exist = true;
						break;
					}
				}
				if (!exist) {
					cards.add(transfer.getCard());
				}
			}
			// 根据身份证查询人员
			if (cards.size() > 0) {
				Long count = (long) 0;
				for (String string : cards) {
					Employee e = this.getEmployeeByIdCard(string);
					if (e != null) {
						count++;
					}
				}
				return count;
			}
		}
		return (long) 0;
	}
}
