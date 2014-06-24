package com.ghtn.dao;

import com.ghtn.BaseTestCase;
import com.ghtn.model.Subject;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by lihe on 14-6-23.
 */
public class SubjectDaoTest extends BaseTestCase {

    private SubjectDao subjectDao;

    @Resource
    public void setSubjectDao(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    @Test
    public void testSave() {
        Subject subject = new Subject();
        subject.setDescription("111");

        subjectDao.save(subject);
    }

}
