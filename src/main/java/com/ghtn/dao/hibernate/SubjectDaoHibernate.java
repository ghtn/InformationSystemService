package com.ghtn.dao.hibernate;

import com.ghtn.dao.SubjectDao;
import com.ghtn.model.Subject;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by lihe on 14-6-23.
 */
@Repository("subjectDao")
public class SubjectDaoHibernate extends GenericDaoHibernate<Subject, Integer> implements SubjectDao {

    public SubjectDaoHibernate() {
        super(Subject.class);
    }

    @Override
    public List<Subject> listSubjectByPage(int start, int limit) {
        return getSession().createCriteria(Subject.class)
                .setFirstResult(start).setMaxResults(limit)
                .addOrder(Order.asc("id")).list();
    }

    @Override
    public List<Subject> listSubjectByPage(int start, int limit, int type) {
        return getSession().createCriteria(Subject.class)
                .add(Restrictions.eq("type", type))
                .setFirstResult(start).setMaxResults(limit)
                .addOrder(Order.asc("id")).list();
    }

    @Override
    public Long getCount() {
        return (Long) getSession().createCriteria(Subject.class)
                .setProjection(Projections.count("id")).uniqueResult();
    }

    @Override
    public Long getCount(int type) {
        return (Long) getSession().createCriteria(Subject.class)
                .add(Restrictions.eq("type", type))
                .setProjection(Projections.count("id")).uniqueResult();
    }

    @Override
    public List<Subject> listSubjectByDate(Date startDate, Date endDate) {
        Criteria c = getSession().createCriteria(Subject.class);
        if (startDate != null) {
            c.add(Restrictions.ge("createTime", startDate));
        }
        if (endDate != null) {
            c.add(Restrictions.le("createTime", endDate));
        }
        return c.addOrder(Order.asc("id")).list();
    }

    @Override
    public List<Subject> listSubjectByDate(Date startDate, Date endDate, int type) {
        Criteria c = getSession().createCriteria(Subject.class);
        if (startDate != null) {
            c.add(Restrictions.ge("createTime", startDate));
        }
        if (endDate != null) {
            c.add(Restrictions.le("createTime", endDate));
        }
        c.add(Restrictions.eq("type", type));
        return c.addOrder(Order.asc("id")).list();
    }
}
