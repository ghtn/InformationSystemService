package com.ghtn.dao;

import com.ghtn.model.Subject;
import com.ghtn.model.SubjectAnswer;

import java.util.List;

/**
 * Created by lihe on 14-6-24.
 */
public interface SubjectAnswerDao extends GenericDao<SubjectAnswer, Integer> {

    void removeSubjectAnswer(Subject subject);

    List<SubjectAnswer> getAnswers(Subject subject);

    List<SubjectAnswer> getCorrectAnswer(Subject subject);
}
