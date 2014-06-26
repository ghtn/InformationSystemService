package com.ghtn.service.impl;

import com.ghtn.dao.SubjectAnswerDao;
import com.ghtn.model.Subject;
import com.ghtn.model.SubjectAnswer;
import com.ghtn.service.SubjectAnswerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihe on 14-6-26.
 */
@Service("subjectAnswerManager")
public class SubjectAnswerManagerImpl extends GenericManagerImpl<SubjectAnswer, Integer>
        implements SubjectAnswerManager {

    private SubjectAnswerDao subjectAnswerDao;

    @Autowired
    public SubjectAnswerManagerImpl(SubjectAnswerDao subjectAnswerDao) {
        super(subjectAnswerDao);
        this.subjectAnswerDao = subjectAnswerDao;
    }

    @Override
    public List<SubjectAnswer> getAnswers(Subject subject) {
        return subjectAnswerDao.getAnswers(subject);
    }
}
