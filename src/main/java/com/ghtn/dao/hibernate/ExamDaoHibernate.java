package com.ghtn.dao.hibernate;

import com.ghtn.dao.ExamDao;
import com.ghtn.model.Employee;
import com.ghtn.model.Exam;
import com.ghtn.util.StringUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by lihe on 14-7-3.
 */
@Repository("examDao")
public class ExamDaoHibernate extends GenericDaoHibernate<Exam, Integer> implements ExamDao {

    public ExamDaoHibernate() {
        super(Exam.class);
    }

    @Override
    public List<Exam> listExamByPage(int start, int limit, Date startDate, Date endDate, int deptId) {
        Criteria c = getSession().createCriteria(Exam.class);
        if (startDate != null) {
            c.add(Restrictions.ge("createTime", startDate));
        }
        if (endDate != null) {
            c.add(Restrictions.le("createTime", endDate));
        }
        if (deptId > 0) {
            c.add(Restrictions.eq("deptId", deptId));
        }
        return c.setFirstResult(start).setMaxResults(limit).addOrder(Order.desc("id")).list();
    }

    @Override
    public Long getCount(Date startDate, Date endDate, int deptId) {
        Criteria c = getSession().createCriteria(Exam.class);
        if (startDate != null) {
            c.add(Restrictions.ge("createTime", startDate));
        }
        if (endDate != null) {
            c.add(Restrictions.le("createTime", endDate));
        }
        if (deptId > 0) {
            c.add(Restrictions.eq("deptId", deptId));
        }
        return (Long) c.setProjection(Projections.count("id")).uniqueResult();
    }

    @Override
    public List<Employee> listEmp(int deptId, String idCard, String name) {
        Criteria c = getSession().createCriteria(Employee.class);
        if (deptId > 0) {
            c.add(Restrictions.eq("deptId", deptId));
        }
        if (!StringUtil.isNullStr(idCard)) {
            c.add(Restrictions.like("card", "%" + idCard + "%"));
        }
        if (!StringUtil.isNullStr(name)) {
            c.add(Restrictions.like("name", "%" + name + "%"));
        }

        return c.list();
    }

    @Override
    public List<Object[]> getEmps(int examId) {
        String hql = "from Employee e, ExamEmp ee where e.id = ee.empId and ee.examId = :examId";
        return getSession().createQuery(hql).setInteger("examId", examId).list();
    }


}
