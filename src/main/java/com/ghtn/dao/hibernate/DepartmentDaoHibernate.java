package com.ghtn.dao.hibernate;

import com.ghtn.dao.DepartmentDao;
import com.ghtn.model.Department;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lihe on 14-6-23.
 */
@Repository("departmentDao")
public class DepartmentDaoHibernate extends GenericDaoHibernate<Department, Integer>
        implements DepartmentDao {

    public DepartmentDaoHibernate() {
        super(Department.class);
    }

    @Override
    public List<Department> getAll(int type) {
        return getSession().createCriteria(Department.class)
                .add(Restrictions.eq("type", type))
                .addOrder(Order.asc("id")).list();
    }

    @Override
    public String getDeptName(int deptId) {
        return get(deptId).getName();
    }
}
