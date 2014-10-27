package com.ghtn.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghtn.dao.EmployeeDao;
import com.ghtn.model.Employee;
import com.ghtn.model.Transfer;
import com.ghtn.service.EmployeeManager;
import com.ghtn.util.ConstantUtil;
import com.ghtn.util.DateUtil;
import com.ghtn.util.FileUtil;
import com.ghtn.util.StringUtil;

/**
 * Created by lihe on 14-6-23.
 */
@Service("employeeManager")
public class EmployeeManagerImpl extends GenericManagerImpl<Employee, Integer>
		implements EmployeeManager {

	private static Log log = LogFactory.getLog(EmployeeManagerImpl.class);

	private EmployeeDao employeeDao;

	@Autowired
	public EmployeeManagerImpl(EmployeeDao employeeDao) {
		super(employeeDao);
		this.employeeDao = employeeDao;
	}

	@Override
	public List<Employee> listEmployeeByPage(int start, int limit,
			String queryCondition, String queryValue, String postState, String retire)
			throws Exception {
		return this.employeeDao.listEmployeeByPage(start, limit,
				queryCondition, queryValue, postState, retire);
	}

	@Override
	public Long getCount(String queryCondition, String queryValue, String postState, String retire)
			throws ParseException {
		return this.employeeDao.getCount(queryCondition, queryValue, postState, retire);
	}

	@Override
	public void updatePostState(Employee employee, String postState)
			throws Exception {
		employee = this.employeeDao.get(employee.getId());
		if (postState.equals("在职") || postState.equals("离职")) {
			employee.setPostState(postState);
		}
		this.employeeDao.save(employee);
	}
	

	@Override
	public List<Employee> listTransferEmployeeByPage(int start, int limit,
			String queryCondition, String queryValue) {
		return this.employeeDao.listTransferEmployeeByPage(start, limit, queryCondition, queryValue);
	}

	@Override
	public Long getTransferCount(String queryCondition, String queryValue) {
		return this.employeeDao.getTransferCount(queryCondition, queryValue);
	}

	@Override
	public void importEmployees(String fileName) throws Exception {
		fileName = ConstantUtil.UPLOAD_TEMP_PATH + "/" + fileName;
		List<String[]> list = FileUtil.ExcelReaderForList(fileName, 2);
		if (list != null && list.size() > 0) {
			String fieldName[] = list.get(0);
			for (int i = 1; i < list.size(); i++) {
				Employee employee = new Employee();
				for (int j = 0; j < list.get(i).length; j++) {
					if (!StringUtil.isNullStr(list.get(i)[j])) {
						this.setFieldValue(employee, fieldName, j,
								list.get(i)[j]);
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

	private void setFieldValue(Employee e, String[] fieldName, int col,
			String fieldValue) throws ParseException {
		switch (fieldName[col]) {
		case "员工号":
			e.setEmpNumber(fieldValue);
			break;
		case "姓名":
			e.setName(fieldValue);
			break;
		case "性别":
			e.setSex(fieldValue);
			break;
		case "身份证号":
			e.setCard(fieldValue);
			if (fieldValue.length() == 15) {
				String time = "19";
				time += fieldValue.substring(6, 8);
				time += "-";
				time += fieldValue.substring(8, 10);
				time += "-";
				time += fieldValue.substring(10, 12);
				e.setCardBirthday(new Date(DateUtil.stringToDate(time,
						"yyyy-MM-dd").getTime()));
			} else if (fieldValue.length() == 18) {
				String time = fieldValue.substring(6, 10);
				time += "-";
				time += fieldValue.substring(10, 12);
				time += "-";
				time += fieldValue.substring(12, 14);
				e.setCardBirthday(new Date(DateUtil.stringToDate(time,
						"yyyy-MM-dd").getTime()));
			}
			break;
		case "所在部门":
			e.setDeptName(fieldValue);
			break;
		case "部门号":
			e.setDeptId(Integer.parseInt(fieldValue));
			break;
		case "工种":
			e.setJobType(fieldValue);
			break;
		case "来本单位时间": {
			e.setUnitTime(new Date(DateUtil.stringToDate(fieldValue,
					"yyyy-MM-dd").getTime()));
			break;
		}
		case "在岗状态":
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
		case "出身日期": {
			e.setBirthday(new Date(DateUtil.stringToDate(fieldValue,
					"yyyy-MM-dd").getTime()));
			break;
		}
		case "血型":
			e.setBloodType(fieldValue);
			break;
		case "来源":
			e.setSource(fieldValue);
			break;
		case "参加工作时间": {
			e.setWorkTime(new Date(DateUtil.stringToDate(fieldValue,
					"yyyy-MM-dd").getTime()));
			break;
		}
		case "职务":
			e.setDuty(fieldValue);
			break;
		case "职务任职时间": {
			e.setDutyTime(new Date(DateUtil.stringToDate(fieldValue,
					"yyyy-MM-dd").getTime()));
			break;
		}
		case "职称":
			e.setJobTitle(fieldValue);
			break;
		case "职称任职时间": {
			e.setJobTitleTime(new Date(DateUtil.stringToDate(fieldValue,
					"yyyy-MM-dd").getTime()));
			break;
		}
		case "工别":
			e.setJobDist(fieldValue);
			break;
		case "工种任职时间": {
			e.setJobTypeTime(new Date(DateUtil.stringToDate(fieldValue,
					"yyyy-MM-dd").getTime()));
			break;
		}
		case "文化程度":
			e.setEducation(fieldValue);
			break;
		case "政治面貌":
			e.setPoliticsStatus(fieldValue);
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
		case "毕业时间": {
			e.setGraduateTime(new Date(DateUtil.stringToDate(fieldValue,
					"yyyy-MM-dd").getTime()));
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
		case "参军时间": {
			e.setArmTime(new Date(DateUtil.stringToDate(fieldValue,
					"yyyy-MM-dd").getTime()));
			break;
		}
		case "退伍时间": {
			e.setEndArmTime(new Date(DateUtil.stringToDate(fieldValue,
					"yyyy-MM-dd").getTime()));
			break;
		}
		case "转制时间": {
			e.setConversionTime(new Date(DateUtil.stringToDate(fieldValue,
					"yyyy-MM-dd").getTime()));
			break;
		}
		case "岗位":
			e.setJob(fieldValue);
			break;
		case "生产线":
			e.setProductionLine(fieldValue);
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

	@Override
	public void exportEmployee(String ids, HttpServletResponse resp)
			throws Exception {
		// TODO Auto-generated method stub
		List<Employee> list = employeeDao.getEmployeesById(ids);
		if (list != null && list.size() > 0) {
			List<String[]> dataList = new ArrayList<>();
			String[] title = { "员工号", "姓名", "曾姓名", "性别", "国家地区", "配置方式",
					"行政待遇级别", "技术人员", "出身日期", "身份证号", "血型", "来源", "参加工作时间",
					"来本单位时间", "所在部门", "职务", "职务任职时间", "职称", "职称任职时间", "工别",
					"工种", "工种任职时间", "文化程度", "政治面貌", "在岗状态", "本人成分", "健康状况",
					"家庭出身", "岗位工资", "技能工资", "第一学历", "学校名称", "就读形式", "所学专业",
					"毕业时间", "本人特长", "户籍所在地", "家庭住址", "邮政编码", "联系电话", "个人简历",
					"参军时间", "退伍时间", "转制时间", "生产线", "岗位", "技术级别", "职称资质",
					"身份证出生日期", "备注", "本人籍贯", "部门号", "预警" };
			dataList.add(title);
			for (int i = 0; i < list.size(); i++) {
				Employee e = list.get(i);
				String[] data = {
						e.getEmpNumber(),
						e.getName(),
						e.getSecondName(),
						e.getSex(),
						e.getCountry(),
						e.getCfgStyle(),
						e.getSalaryGrade(),
						e.getTechnicist(),
						e.getBirthday() == null ? "" : e.getBirthday() + "",
						e.getCard(),
						e.getBloodType(),
						e.getSource(),
						e.getWorkTime() == null ? "" : e.getWorkTime() + "",
						e.getUnitTime() == null ? "" : e.getUnitTime() + "",
						e.getDeptName(),
						e.getDuty(),
						e.getDutyTime() == null ? "" : e.getDutyTime() + "",
						e.getJobTitle(),
						e.getJobTitleTime() == null ? "" : e.getJobTitleTime()
								+ "",
						e.getJobDist(),
						e.getJobType(),
						e.getJobTypeTime() == null ? "" : e.getJobTypeTime()
								+ "",
						e.getEducation(),
						e.getPoliticsStatus(),
						e.getPostState(),
						e.getMyIngredient(),
						e.getHealthStatus(),
						e.getFamilyOrigin(),
						e.getPostSalary() + "",
						e.getSkillSalary() + "",
						e.getFirstEducation(),
						e.getSchoolName(),
						e.getStudyStyle(),
						e.getProfession(),
						e.getGraduateTime() == null ? "" : e.getGraduateTime()
								+ "",
						e.getSpeciality(),
						e.getDomicilePlace(),
						e.getHomeAddress(),
						e.getPostalCode(),
						e.getTelphone(),
						e.getResume(),
						e.getArmTime() == null ? "" : e.getArmTime() + "",
						e.getEndArmTime() == null ? "" : e.getEndArmTime() + "",
						e.getConversionTime() == null ? "" : e
								.getConversionTime() + "",
						e.getProductionLine(),
						e.getJob(),
						e.getSkillGrage(),
						e.getJobQualification(),
						e.getCardBirthday() == null ? "" : e.getCardBirthday()
								+ "", e.getComment(), e.getSelfNationality(),
						e.getDeptId() + "", e.getWarn() };
				dataList.add(data);
			}

			String strExcelFile = FileUtil.exportExcel(dataList);
			File file = new File(ConstantUtil.UPLOAD_TEMP_PATH + "/"
					+ strExcelFile);

			resp.reset();
			resp.setContentType("application/vnd.ms-excel");
			resp.setHeader("content-disposition", "attachment; filename="
					+ strExcelFile);

			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				bis = new BufferedInputStream(new FileInputStream(file));
				bos = new BufferedOutputStream(resp.getOutputStream());
				byte[] buff = new byte[2048];
				int bytesread;
				while (-1 != (bytesread = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesread);
				}
			} catch (final IOException e) {
				System.out.println("出现ioexception." + e);
			} finally {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
				FileUtil.deleteFile(file);
			}
		} else {
			log.error("导出的结果集为空!");
		}
	}
}
