package com.ghtn.dao.hibernate;

import com.ghtn.dao.SubjectAnswerDao;
import com.ghtn.model.Subject;
import com.ghtn.model.SubjectAnswer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lihe on 14-6-24.
 */
@Repository("subjectAnswerDao")
public class SubjectAnswerDaoHibernate extends GenericDaoHibernate<SubjectAnswer, Integer>
        implements SubjectAnswerDao {

    public SubjectAnswerDaoHibernate() {
        super(SubjectAnswer.class);
    }

    @Override
    public void removeSubjectAnswer(Subject subject) {
        String hql = "delete from SubjectAnswer sa where sa.subjectId = ?";
        getSession().createQuery(hql).setInteger(0, subject.getId()).executeUpdate();
    }

    @Override
    public List<SubjectAnswer> getAnswers(Subject subject) {
        String hql = "from SubjectAnswer sa where sa.subjectId = " + subject.getId();
        return queryHql(hql);
    }
}
