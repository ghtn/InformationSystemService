package com.ghtn.dao;

import com.ghtn.model.Department;

import java.util.List;

/**
 * Created by lihe on 14-6-23.
 */
public interface DepartmentDao extends GenericDao<Department, Integer> {

    List<Department> getAll(int type);

    String getDeptName(int deptId);

}
