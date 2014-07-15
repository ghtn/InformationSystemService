package com.ghtn.dao.hibernate;

import com.ghtn.dao.ScoreDao;
import com.ghtn.model.Score;
import com.ghtn.util.StringUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lihe on 14-7-10.
 */
@Repository("scoreDao")
public class ScoreDaoHibernate extends GenericDaoHibernate<Score, Integer> implements ScoreDao {

    public ScoreDaoHibernate() {
        super(Score.class);
    }

    @Override
    public Score getScore(int examId, String idCard) {
        Object o = getSession().createCriteria(Score.class)
                .add(Restrictions.eq("examId", examId))
                .add(Restrictions.eq("idCard", idCard)).uniqueResult();
        if (o != null) {
            return (Score) o;
        }
        return null;
    }

    @Override
    public List<Score> listScoreByPage(int start, int limit, String idCard, String name, String empNumber, int examId, int examScore, int pass, int errorCount, int deptId) {
        Criteria c = getSession().createCriteria(Score.class);
        if (!StringUtil.isNullStr(idCard)) {
            c.add(Restrictions.like("idCard", "%" + idCard + "%"));
        }
        if (!StringUtil.isNullStr(name)) {
            c.add(Restrictions.like("name", "%" + name + "%"));
        }
        if (!StringUtil.isNullStr(empNumber)) {
            c.add(Restrictions.like("empNumber", "%" + empNumber + "%"));
        }
        if (examId > 0) {
            c.add(Restrictions.eq("examId", examId));
        }
        if (examScore > 0) {
            c.add(Restrictions.eq("examScore", examScore));
        }
        if (pass > 0) {
            c.add(Restrictions.eq("pass", pass));
        }
        if (errorCount > 0) {
            c.add(Restrictions.eq("errorCount", errorCount));
        }
        if (deptId > 0) {
            c.add(Restrictions.eq("deptId", deptId));
        }
        return c.setFirstResult(start).setMaxResults(limit).addOrder(Order.asc("id")).list();
    }

    @Override
    public Long getCount(String idCard, String name, String empNumber, int examId, int examScore, int pass, int errorCount, int deptId) {
        Criteria c = getSession().createCriteria(Score.class);
        if (!StringUtil.isNullStr(idCard)) {
            c.add(Restrictions.like("idCard", "%" + idCard + "%"));
        }
        if (!StringUtil.isNullStr(name)) {
            c.add(Restrictions.like("name", "%" + name + "%"));
        }
        if (!StringUtil.isNullStr(empNumber)) {
            c.add(Restrictions.like("empNumber", "%" + empNumber + "%"));
        }
        if (examId > 0) {
            c.add(Restrictions.eq("examId", examId));
        }
        if (examScore > 0) {
            c.add(Restrictions.eq("examScore", examScore));
        }
        if (pass > 0) {
            c.add(Restrictions.eq("pass", pass));
        }
        if (errorCount > 0) {
            c.add(Restrictions.eq("errorCount", errorCount));
        }
        if (deptId > 0) {
            c.add(Restrictions.eq("deptId", deptId));
        }
        return (Long) c.setProjection(Projections.count("id")).uniqueResult();
    }

    @Override
    public List<Score> listScore(String idCard, String name, String empNumber, int examId, int examScore, int pass, int errorCount, int deptId) {
        Criteria c = getSession().createCriteria(Score.class);
        if (!StringUtil.isNullStr(idCard)) {
            c.add(Restrictions.like("idCard", "%" + idCard + "%"));
        }
        if (!StringUtil.isNullStr(name)) {
            c.add(Restrictions.like("name", "%" + name + "%"));
        }
        if (!StringUtil.isNullStr(empNumber)) {
            c.add(Restrictions.like("empNumber", "%" + empNumber + "%"));
        }
        if (examId > 0) {
            c.add(Restrictions.eq("examId", examId));
        }
        if (examScore > 0) {
            c.add(Restrictions.eq("examScore", examScore));
        }
        if (pass > 0) {
            c.add(Restrictions.eq("pass", pass));
        }
        if (errorCount > 0) {
            c.add(Restrictions.eq("errorCount", errorCount));
        }
        if (deptId > 0) {
            c.add(Restrictions.eq("deptId", deptId));
        }
        return c.addOrder(Order.asc("id")).list();
    }
}
