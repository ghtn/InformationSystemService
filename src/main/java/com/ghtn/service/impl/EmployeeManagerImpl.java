package com.ghtn.service.impl;

import com.ghtn.dao.EmployeeDao;
import com.ghtn.model.Employee;
import com.ghtn.model.Subject;
import com.ghtn.model.SubjectAnswer;
import com.ghtn.service.EmployeeManager;
import com.ghtn.util.ConstantUtil;
import com.ghtn.util.DateUtil;
import com.ghtn.util.FileUtil;
import com.ghtn.util.StringUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihe on 14-6-23.
 */
@Service("employeeManager")
public class EmployeeManagerImpl extends GenericManagerImpl<Employee, Integer> implements EmployeeManager {

    private static Log log = LogFactory.getLog(EmployeeManagerImpl.class);

    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeManagerImpl(EmployeeDao employeeDao) {
        super(employeeDao);
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> listEmployeeByPage(int start, int limit, String queryCondition, String queryValue, String postState, String retire) throws Exception {
    	return this.employeeDao.listEmployeeByPage(start, limit, queryCondition, queryValue, postState, retire);
    }

    @Override
    public Long getCount(String queryCondition, String queryValue, String postState, String retire) throws ParseException {
        return this.employeeDao.getCount(queryCondition, queryValue, postState, retire);
    }

    @Override
    public void addEmployee(Employee employee) throws Exception {
    	employeeDao.save(employee);
    }

    @Override
    public void removeEmployee(Employee employee) {
    	this.employeeDao.remove(employee);
    }

    @Override
    public void updateEmployee(Employee employee) throws Exception {
    	this.employeeDao.save(employee);
    }
    
    @Override
    public void updatePostState(Employee employee, String postState) throws Exception {
    	System.out.println(employee.getId());
    	employee = this.employeeDao.get(employee.getId());
    	System.out.println(employee.getId());
    	if( postState.equals("在职") || postState.equals("离职")){
    		employee.setPostState(postState);
    	}
    	this.employeeDao.save(employee);
    }

    @Override
    public void importEmployees(HttpSession session) throws Exception {
        String fileName = ConstantUtil.UPLOAD_TEMP_PATH + "/" + session.getAttribute("fileName");
        List<String[]> list = FileUtil.ExcelReaderForList(fileName, 2);
        if (list != null && list.size() > 0) {
        	String fieldName[] = list.get(0);
        	for(int i = 1; i < list.size(); i++){
        		Employee employee = new Employee();
        		for(int j = 0; j < list.get(i).length; j++){
        			if(!StringUtil.isNullStr(list.get(i)[j])){
        				this.setFieldValue(employee, fieldName, j, list.get(i)[j]);
        			}
        		}
        		employeeDao.save(employee);
        	}
        	
        } else {
            log.error("从excel文件读取的list为空!!");
        }

        // 删除临时文件
        FileUtil.deleteFile(new File(fileName));
    }
    
    private void setFieldValue(Employee e, String []fieldName, int col, String fieldValue) throws ParseException{
    	System.out.println(fieldName[col] + ": " + fieldValue);
    	switch(fieldName[col]){
    		case "员工号(*)":
    			e.setEmpNumber(fieldValue);
    			break;
    		case "姓名(*)":
    			e.setName(fieldValue);
    			break;
    		case "性别(*)":
    			e.setSex(fieldValue);
    			break;
    		case "身份证号(*)":
    			e.setCard(fieldValue);
    			if( fieldValue.length() == 15){
    				String time = "19";
    				time += fieldValue.substring(6, 8);
    				time += "-";
    				time += fieldValue.substring(8, 10);
    				time += "-";
    				time += fieldValue.substring(10, 12);
    				e.setCardBirthday(new Date(DateUtil.stringToDate(time, "yyyy-MM-dd").getTime()));
    			}else if(fieldValue.length() == 18){
    				String time = fieldValue.substring(6, 10);
    				time += "-";
    				time += fieldValue.substring(10, 12);
    				time += "-";
    				time += fieldValue.substring(12, 14);
    				e.setCardBirthday(new Date(DateUtil.stringToDate(time, "yyyy-MM-dd").getTime()));
    			}
    			break;
    		case "所在部门(*)":
    			e.setDeptName(fieldValue);
    			break;
    		case "部门号(*)":
    			e.setDeptId(Integer.parseInt(fieldValue));
    			break;
    		case "工种(*)":
    			e.setJobType(fieldValue);
    			break;
    		case "来本单位时间(*)":{
	    			e.setUnitTime(new Date(DateUtil.stringToDate(fieldValue, "yyyy-MM-dd").getTime()));
	    			break;
    			}
    		case "在岗状态(*)":
    			e.setPostState(fieldValue);
    			break;
    		case "曾姓名":
    			e.setSecondName(fieldValue);
    			break;
    		case "国家地区":
    			e.setCountry(fieldValue);
    			break;
    		case "配置方式":
    			e.setCfgStyle(fieldValue);
    			break;
    		case "行政待遇级别":
    			e.setSalaryGrade(fieldValue);
    			break;
    		case "技术人员":
    			e.setTechnicist(fieldValue);
    			break;
    		case "出身日期":{
	    			e.setBirthday(new Date(DateUtil.stringToDate(fieldValue, "yyyy-MM-dd").getTime()));
	    			break;
    			}
    		case "血型":
    			e.setBloodType(fieldValue);
    			break;
    		case "来源":
    			e.setSource(fieldValue);
    			break;
    		case "参加工作时间":{
    			e.setWorkTime(new Date(DateUtil.stringToDate(fieldValue, "yyyy-MM-dd").getTime()));
    			break;
    			}
    		case "职务":
    			e.setDuty(fieldValue);
    			break;
    		case "职务任职时间":{
    			e.setDutyTime(new Date(DateUtil.stringToDate(fieldValue, "yyyy-MM-dd").getTime()));
    			break;
    			}
    		case "职称":
    			e.setJobTitle(fieldValue);
    			break;
    		case "职称任职时间":{
    			e.setJobTitleTime(new Date(DateUtil.stringToDate(fieldValue, "yyyy-MM-dd").getTime()));
    			break;
    			}
    		case "工别":
    			e.setJobDist(fieldValue);
    			break;
    		case "工种任职时间":{
    			e.setJobTypeTime(new Date(DateUtil.stringToDate(fieldValue, "yyyy-MM-dd").getTime()));
    			break;
    			}
    		case "文化程度":
    			e.setEducation(fieldValue);
    			break;
    		case "政治面貌":
    			e.setPoliticsStatus(fieldValue);
    			break;
    		case "在岗状态":
    			e.setPostState(fieldValue);
    			break;
    		case "本人成分":
    			e.setMyIngredient(fieldValue);
    			break;
    		case "健康状况":
    			e.setHealthStatus(fieldValue);
    			break;
    		case "家庭出身":
    			e.setFamilyOrigin(fieldValue);
    			break;
    		case "岗位工资":
    			e.setPostSalary(Float.parseFloat(fieldValue));
    			break;
    		case "技能工资":
    			e.setSkillSalary(Float.parseFloat(fieldValue));
    			break;
    		case "第一学历":
    			e.setFirstEducation(fieldValue);
    			break;
    		case "学校名称":
    			e.setSchoolName(fieldValue);
    			break;
    		case "就读形式":
    			e.setStudyStyle(fieldValue);
    			break;
    		case "所学专业":
    			e.setProfession(fieldValue);
    			break;
    		case "毕业时间":{
    			e.setGraduateTime(new Date(DateUtil.stringToDate(fieldValue, "yyyy-MM-dd").getTime()));
    			break;
    			}
    		case "本人特长":
    			e.setSpeciality(fieldValue);
    			break;
    		case "户籍所在地":
    			e.setDomicilePlace(fieldValue);
    			break;
    		case "家庭住址":
    			e.setHomeAddress(fieldValue);
    			break;
    		case "邮政编码":
    			e.setPostalCode(fieldValue);
    			break;
    		case "联系电话":
    			e.setTelphone(fieldValue);
    			break;
    		case "个人简历":
    			e.setResume(fieldValue);
    			break;
    		case "参军时间":{
    			e.setArmTime(new Date(DateUtil.stringToDate(fieldValue, "yyyy-MM-dd").getTime()));
    			break;
    			}
    		case "退伍时间":{
    			e.setEndArmTime(new Date(DateUtil.stringToDate(fieldValue, "yyyy-MM-dd").getTime()));
    			break;
    			}
    		case "转制时间":{
    			e.setConversionTime(new Date(DateUtil.stringToDate(fieldValue, "yyyy-MM-dd").getTime()));
    			break;
    			}
    		case "生产线":
    			e.setProductLine(fieldValue);
    			break;
    		case "岗位":
    			e.setJob(fieldValue);
    			break;
    		case "技术级别":
    			e.setSkillGrage(fieldValue);
    			break;
    		case "职称资质":
    			e.setJobQualification(fieldValue);
    			break;
    		case "本人籍贯":
    			e.setSelfNationality(fieldValue);
    			break;
    		case "备注":
    			e.setComment(fieldValue);
    			break;
    	}
    }
}
