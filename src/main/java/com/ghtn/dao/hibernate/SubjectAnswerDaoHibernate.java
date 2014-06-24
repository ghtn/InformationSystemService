package com.ghtn.dao.hibernate;

import com.ghtn.dao.SubjectAnswerDao;
import com.ghtn.model.SubjectAnswer;
import org.springframework.stereotype.Repository;

/**
 * Created by lihe on 14-6-24.
 */
@Repository
public class SubjectAnswerDaoHibernate extends GenericDaoHibernate<SubjectAnswer, Integer>
        implements SubjectAnswerDao {

    public SubjectAnswerDaoHibernate() {
        super(SubjectAnswer.class);
    }
}
