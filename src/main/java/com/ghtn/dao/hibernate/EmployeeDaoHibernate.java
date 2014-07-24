package com.ghtn.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ghtn.dao.EmployeeDao;
import com.ghtn.model.Employee;

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
	public List<Employee> listEmployeeByPage(int start, int limit, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
//		System.out.println("==== employeeDAOHibernate/listEmployeeByPage ====");
//		return null;
	 Criteria c = getSession().createCriteria(Employee.class);
	 if (startDate != null) {
         c.add(Restrictions.ge("unitTime", startDate));
     }
     if (endDate != null) {
         c.add(Restrictions.le("unitTime", endDate));
     }
     return c.setFirstResult(start).setMaxResults(limit)
                .addOrder(Order.asc("id")).list();
	}

	@Override
	public Long getCount(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		Criteria c = getSession().createCriteria(Employee.class);
		if (startDate != null) {
			c.add(Restrictions.ge("unitTime", startDate));
		}
		if (endDate != null) {
			c.add(Restrictions.le("unitTime", endDate));
		}
        return (Long) c.setProjection(Projections.count("id")).uniqueResult();
	}
}
