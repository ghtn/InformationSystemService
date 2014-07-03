package com.ghtn.dao;

import com.ghtn.BaseTestCase;
import com.ghtn.model.Subject;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihe on 14-7-3.
 */
public class PaperDaoTest extends BaseTestCase {

    private PaperDao paperDao;

    @Resource
    public void setPaperDao(PaperDao paperDao) {
        this.paperDao = paperDao;
    }

    @Test
    public void testGetSubjects() {
        List<Subject> list = paperDao.getSubjects(8);
        System.out.println(list.size());
        Subject subject = list.get(0);
        System.out.println(subject.getDescription());
    }
}
