package com.ghtn.controller;

import com.ghtn.model.Department;
import com.ghtn.service.DepartmentManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihe on 14-6-23.
 */
@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController {

    private DepartmentManager departmentManager;

    @Resource
    public void setDepartmentManager(DepartmentManager departmentManager) {
        this.departmentManager = departmentManager;
    }

    @RequestMapping
    @ResponseBody
    public List<Department> getAllDepartments() {
        return departmentManager.getAll();
    }

}
