package com.ghtn.service;

import com.ghtn.BaseTestCase;
import com.ghtn.model.Subject;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by lihe on 14-6-23.
 */
public class SubjectManagerTest extends BaseTestCase {

    private SubjectManager subjectManager;

    @Resource
    public void setSubjectManager(SubjectManager subjectManager) {
        this.subjectManager = subjectManager;
    }

    @Test
    public void testSave() {
        for (int i = 0; i < 100; i++) {
            Subject subject = new Subject();
            subject.setDescription("题目" + i);
            subject.setCreator("李鹤");
            subject.setCreatTime(new Date());

            if (i % 2 == 0) {
                subject.setType(0);
            } else {
                subject.setType(1);
            }

            subjectManager.save(subject);
        }
    }

    @Test
    public void testGetCount() {
        System.out.println(subjectManager.getCount(-1));
    }

    @Test
    public void testRemove() {
        Subject subject = new Subject();
        subject.setId(111);
        subjectManager.removeSubject(subject);
    }
}
