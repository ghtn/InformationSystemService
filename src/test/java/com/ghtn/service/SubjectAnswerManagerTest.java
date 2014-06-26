package com.ghtn.service;

import com.ghtn.BaseTestCase;
import com.ghtn.model.Subject;
import com.ghtn.model.SubjectAnswer;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihe on 14-6-26.
 */
public class SubjectAnswerManagerTest extends BaseTestCase {

    private SubjectAnswerManager subjectAnswerManager;

    @Resource
    public void setSubjectAnswerManager(SubjectAnswerManager subjectAnswerManager) {
        this.subjectAnswerManager = subjectAnswerManager;
    }

    @Test
    public void testGetAnswers() {
        Subject subject = new Subject();
        subject.setId(117);
        List<SubjectAnswer> list = subjectAnswerManager.getAnswers(subject);
        System.out.println(list.size());
    }
}
