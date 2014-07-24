package com.ghtn.controller;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ghtn.model.Employee;
import com.ghtn.service.EmployeeManager;
import com.ghtn.util.FileUtil;

@Controller
@RequestMapping(value="/employee")
public class EmployeeController extends BaseController{
	
	private EmployeeManager employeeManager;
	
	
	@RequestMapping("/listEmployeeByPage")
	@ResponseBody
	public Map<String, Object> listEmployeeByPage(int start, int limit, String startDate, String endDate)throws Exception{
//		System.out.println("EmployeeController.listEmployeeByPage:start:" + start + ", limit:" + limit);
		Map<String, Object> map = new HashMap<>();
		map.put("total", employeeManager.getCount(startDate, endDate));
//		System.out.println(map.get("total"));
		map.put("items", employeeManager.listEmployeeByPage(start, limit, startDate, endDate));
		return map;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> add(Employee employee)throws Exception{
//		System.out.println("employee add");
//		System.out.println(employee.getName());
//		System.out.println(employee.getBirthday());
		employeeManager.save(employee);
		return operationSuccess();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String, Object> update(Employee employee)throws Exception{
//		System.out.println("employee update");
//		System.out.println(employee.getName());
//		System.out.println(employee.getBirthday());
		employeeManager.update(employee);
		return operationSuccess();
	}
	
	@RequestMapping("/remove")
	@ResponseBody
	public Map<String, Object> remove(String ids)throws Exception{
//		System.out.println("employee remove");
		String strId[] = ids.split("#");
		for( int i = 0; i < strId.length; i++){
			employeeManager.remove(new Employee(Integer.parseInt(strId[i])));
		}
		return operationSuccess();
	}
	

    @RequestMapping("/uploadFile")
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestParam("file") CommonsMultipartFile file, HttpSession session)
            throws Exception {
        String fileName = FileUtil.uploadFile(file);
        session.setAttribute("fileName", fileName);
        return operationSuccess();
    }
    

    @RequestMapping("/importEmployees")
    @ResponseBody
    public Map<String, Object> importEmployees(HttpSession session) throws Exception {
        // TODO : deptId从session中获取
        employeeManager.importEmployees(session);
    	return operationSuccess();
    }


	public EmployeeManager getEmployeeManager() {
		return employeeManager;
	}

	@Resource
	public void setEmployeeManager(EmployeeManager employeeManager) {
		this.employeeManager = employeeManager;
	}

}
