package com.ghtn.service;

import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.ghtn.BaseTestCase;
import com.ghtn.dao.EmployeeDao;

@Component
public class EmployeeManagerTest extends BaseTestCase{
	private EmployeeManager employeeManager;

	public EmployeeManager getEmployeeManager() {
		return employeeManager;
	}

	@Resource
	public void setEmployeeManager(EmployeeManager employeeManager) {
		this.employeeManager = employeeManager;
	}
	
	@Test
	public void testExportEmployee() throws Exception{
		String ids = "1#2#3#4";
		this.employeeManager.exportEmployee(ids, null);
	}
}
