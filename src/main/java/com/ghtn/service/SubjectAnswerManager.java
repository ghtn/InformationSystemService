package com.ghtn.service;

import com.ghtn.model.Subject;
import com.ghtn.model.SubjectAnswer;

import java.util.List;

/**
 * Created by lihe on 14-6-26.
 */
public interface SubjectAnswerManager extends GenericManager<SubjectAnswer, Integer> {

    List<SubjectAnswer> getAnswers(Subject subject);

}
