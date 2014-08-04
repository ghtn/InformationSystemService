package com.ghtn.dao.hibernate;

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
import com.ghtn.util.StringUtil;

/**
 * Created by lihe on 14-7-10.
 */
@Repository("employeeDao")
public class EmployeeDaoHibernate extends GenericDaoHibernate<Employee, Integer>
        implements EmployeeDao {

    public EmployeeDaoHibernate() {
        super(Employee.class);
    }

    @Override
    public Employee getEmployeeByIdCard(String idCard) {
        return (Employee) getSession().createCriteria(Employee.class)
                .add(Restrictions.eq("card", idCard)).uniqueResult();
    }

	@Override
	public List<Employee> listEmployeeByPage(int start, int limit, String queryCondition, String queryValue, String postState, String retire) {
		// TODO Auto-generated method stub
	 Criteria c = getSession().createCriteria(Employee.class);
	 if ( !StringUtil.isNullStr(queryCondition)) {
         c.add(Restrictions.ilike(queryCondition, queryValue, MatchMode.ANYWHERE));
     }
	 if ( !StringUtil.isNullStr(postState)) {
		 c.add(Restrictions.eq("postState", postState));
	 }
	 if ( !StringUtil.isNullStr(retire)) {
		 c.add(Restrictions.ilike("warn", retire, MatchMode.ANYWHERE));
	 }
     return c.setFirstResult(start).setMaxResults(limit)
                .addOrder(Order.asc("id")).list();
	}

	@Override
	public Long getCount(String queryCondition, String queryValue, String postState, String retire) {
		// TODO Auto-generated method stub
		Criteria c = getSession().createCriteria(Employee.class);
		if ( !StringUtil.isNullStr(queryCondition)) {
	         c.add(Restrictions.ilike(queryCondition, queryValue, MatchMode.ANYWHERE));
	     }
		if ( !StringUtil.isNullStr(postState)) {
			 c.add(Restrictions.eq("postState", postState));
		 }
		if ( !StringUtil.isNullStr(retire)) {
			c.add(Restrictions.ilike("warn", retire, MatchMode.ANYWHERE));
		}
		return (Long) c.setProjection(Projections.count("id")).uniqueResult();
	}
}
