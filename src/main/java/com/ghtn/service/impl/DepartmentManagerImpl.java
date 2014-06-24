package com.ghtn.service.impl;

import com.ghtn.dao.DepartmentDao;
import com.ghtn.model.Department;
import com.ghtn.service.DepartmentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lihe on 14-6-23.
 */
@Service("departmentManager")
public class DepartmentManagerImpl extends GenericManagerImpl<Department, Integer>
        implements DepartmentManager {

    private DepartmentDao departmentDao;

    @Autowired
    public DepartmentManagerImpl(DepartmentDao departmentDao) {
        super(departmentDao);
        this.departmentDao = departmentDao;
    }

}
