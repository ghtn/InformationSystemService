package com.ghtn.service;

import com.ghtn.BaseTestCase;
import com.ghtn.model.Department;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by lihe on 14-6-23.
 */
public class DepartmentManagerTest extends BaseTestCase {

    private DepartmentManager departmentManager;

    @Resource
    public void setDepartmentManager(DepartmentManager departmentManager) {
        this.departmentManager = departmentManager;
    }

    @Test
    public void testSave() {
        Department department1 = new Department();
        department1.setName("人事部");
        department1.setType(0);
        departmentManager.save(department1);

        Department department2 = new Department();
        department2.setName("研发部");
        department2.setType(0);
        departmentManager.save(department2);

        Department department3 = new Department();
        department3.setName("综采一队");
        department3.setType(1);
        departmentManager.save(department3);

        Department department4 = new Department();
        department4.setName("综采二队");
        department4.setType(1);
        departmentManager.save(department4);
    }
}
