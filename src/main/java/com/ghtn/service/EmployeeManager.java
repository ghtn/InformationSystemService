package com.ghtn.service;

import com.ghtn.model.Employee;
import com.ghtn.model.Subject;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.text.ParseException;
import java.util.List;

/**
 * Created by lihe on 14-6-23.
 */
public interface EmployeeManager extends GenericManager<Employee, Integer> {

    List<Employee> listEmployeeByPage(int start, int limit, String queryCondition, String queryValue, String postState, String retire) throws Exception;

    Long getCount(String queryCondition, String queryValue, String postState, String retire) throws ParseException;

    void addEmployee(Employee employee) throws Exception;

    void removeEmployee(Employee employee);

    void updateEmployee(Employee employee) throws Exception;
    
    void updatePostState(Employee employee, String postState) throws Exception;

    void importEmployees(String fileName) throws Exception;

    void exportEmployee(String ids, HttpServletResponse resp) throws Exception ;
}
