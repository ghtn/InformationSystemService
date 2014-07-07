package com.ghtn.service;

import com.ghtn.BaseTestCase;
import com.ghtn.model.Exam;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by lihe on 14-7-4.
 */
public class ExamManagerTest extends BaseTestCase {

    private ExamManager examManager;

    @Resource
    public void setExamManager(ExamManager examManager) {
        this.examManager = examManager;
    }

    @Test
    public void testSave() {
        Exam exam = new Exam();
        exam.setDeptId(2);
        exam.setPaperId(4);
        exam.setName("测试");
        exam.setPlace("考试地点");
        exam.setCreator(0);
        exam.setCreatorName("111");
        exam.setCreateTime(new Date());

        exam.setExamTime(new Date());

        examManager.save(exam);
    }
}
