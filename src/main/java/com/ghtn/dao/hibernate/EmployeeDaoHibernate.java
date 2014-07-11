package com.ghtn.dao.hibernate;

import com.ghtn.dao.EmployeeDao;
import com.ghtn.model.Employee;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

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
}
