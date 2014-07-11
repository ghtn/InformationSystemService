package com.ghtn.dao;

import com.ghtn.model.Employee;

/**
 * Created by lihe on 14-7-10.
 */
public interface EmployeeDao extends GenericDao<Employee, Integer> {

    Employee getEmployeeByIdCard(String idCard);

}
