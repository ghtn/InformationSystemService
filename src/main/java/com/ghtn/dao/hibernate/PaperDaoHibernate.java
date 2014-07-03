package com.ghtn.dao.hibernate;

import com.ghtn.dao.PaperDao;
import com.ghtn.model.Paper;
import com.ghtn.model.Subject;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by lihe on 14-6-30.
 */
@Repository("paperDao")
public class PaperDaoHibernate extends GenericDaoHibernate<Paper, Integer> implements PaperDao {

    public PaperDaoHibernate() {
        super(Paper.class);
    }

    @Override
    public List<Paper> listPaperByPage(int start, int limit, Date startDate, Date endDate, int deptId, int status) {
        Criteria c = getSession().createCriteria(Paper.class);
        if (startDate != null) {
            c.add(Restrictions.ge("createTime", startDate));
        }
        if (endDate != null) {
            c.add(Restrictions.le("createTime", endDate));
        }
        if (deptId > 0) {
            c.add(Restrictions.eq("deptId", deptId));
        }
        if (status >= 0) {
            c.add(Restrictions.eq("status", status));
        }
        return c.setFirstResult(start).setMaxResults(limit).addOrder(Order.desc("id")).list();
    }

    @Override
    public Long getCount(Date startDate, Date endDate, int deptId, int status) {
        Criteria c = getSession().createCriteria(Paper.class);
        if (startDate != null) {
            c.add(Restrictions.ge("createTime", startDate));
        }
        if (endDate != null) {
            c.add(Restrictions.le("createTime", endDate));
        }
        if (deptId > 0) {
            c.add(Restrictions.eq("deptId", deptId));
        }
        if (status >= 0) {
            c.add(Restrictions.eq("status", status));
        }
        return (Long) c.setProjection(Projections.count("id")).uniqueResult();
    }

    @Override
    public List<Subject> getSubjects(int paperId) {
        String hql = "select new Subject (s.id, s.deptId, s.description, s.mark, s.type, s.correct, s.creator, s.createTime)from Subject s , PaperSubject ps";
        hql += " where ps.paperId = ? and s.id = ps.subjectId";
        return getSession().createQuery(hql).setInteger(0, paperId).list();
    }
}
