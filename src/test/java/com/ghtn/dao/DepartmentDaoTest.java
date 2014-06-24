package com.ghtn.dao;

import com.ghtn.BaseTestCase;
import com.ghtn.model.Department;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by lihe on 14-6-23.
 */
public class DepartmentDaoTest extends BaseTestCase {

    private DepartmentDao departmentDao;

    @Resource
    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Test
    public void testSave() {
        Department department = new Department();
        department.setName("部门1");

        departmentDao.save(department);
    }
}
