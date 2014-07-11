package com.ghtn.dao.hibernate;

import com.ghtn.dao.ScoreDao;
import com.ghtn.model.Score;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

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
}
