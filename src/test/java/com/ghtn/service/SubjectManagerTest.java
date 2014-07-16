package com.ghtn.service;

import com.ghtn.BaseTestCase;
import com.ghtn.model.Subject;
import com.ghtn.vo.SubjectVO;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
            subject.setCreator(0);
            subject.setCreatorName("李鹤");
            subject.setCreateTime(new Date());

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
//        System.out.println(subjectManager.getCount(-1, 2));
    }

    @Test
    public void testRemove() {
        Subject subject = new Subject();
        subject.setId(111);
        subjectManager.removeSubject(subject);
    }

    @Test
    public void testImport() throws Exception {
//        subjectManager.importSubjects(2, "/Users/lihe/Documents/题库模板.xlsx");
    }

    @Test
    public void testListSubject() throws Exception {
        List<SubjectVO> list = subjectManager.listSubjectByDate("2014-06-28", "2014-06-30", 2);
        System.out.println(list.size());
    }

}
