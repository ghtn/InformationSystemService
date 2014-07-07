package com.ghtn.dao;

import com.ghtn.BaseTestCase;
import com.ghtn.model.Employee;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihe on 14-7-7.
 */
public class ExamDaoTest extends BaseTestCase {

    private ExamDao examDao;

    @Resource
    public void setExamDao(ExamDao examDao) {
        this.examDao = examDao;
    }

    @Test
    public void testListEmp() {
        List<Employee> list = examDao.listEmp(2, "3", "测试");
        System.out.println(list.size());
    }
}
