package com.ghtn.dao;

import java.util.Date;
import java.util.List;

import com.ghtn.model.Employee;

/**
 * Created by lihe on 14-7-10.
 */
public interface EmployeeDao extends GenericDao<Employee, Integer> {

    Employee getEmployeeByIdCard(String idCard);
    
    List<Employee> listEmployeeByPage(int start, int limit, Date startDate, Date endDate);
    
    Long getCount(Date startDate, Date endDate);

}
