package com.ghtn.dao.hibernate;

import com.ghtn.dao.ExamDao;
import com.ghtn.model.Exam;
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
}
