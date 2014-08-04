package com.ghtn.dao;

import java.util.Date;
import java.util.List;

import com.ghtn.model.Employee;

/**
 * Created by lihe on 14-7-10.
 */
public interface EmployeeDao extends GenericDao<Employee, Integer> {

    Employee getEmployeeByIdCard(String idCard);
    
    List<Employee> listEmployeeByPage(int start, int limit, String queryCondition, String queryValue, String postState, String retire);
    
    Long getCount(String queryCondition, String queryValue, String postState, String retire);

}
