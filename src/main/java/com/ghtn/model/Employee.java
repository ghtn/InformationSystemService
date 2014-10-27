package com.ghtn.model;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by lihe on 14-7-7.
 */
@Entity
public class Employee {

	private int id;							// ID
	private Date armTime;					// 参军时间
	private Date birthday;					// 出身日期
	private String bloodType;				// 血型
	private String card;					// 身份证号
	private Date cardBirthday;				// 身份证出生日期
	private String cfgStyle;				// 配置方式
	private String warn;					// 预警
	private String comment;					// 备注
	private Date conversionTime;			// 转制时间
	private String country;					// 国家地区
	private Integer deptId;					// 部门号
	private String deptName;				// 所在部门
	private String selfNationality;			// 本人籍贯
	private String domicilePlace;			// 户籍所在地
	private String duty;					// 职务
	private Date dutyTime;					// 职务任职时间
	private String education;				// 文化程度
	private String empNumber;				// 员工号
	private Date endArmTime;				// 退伍时间
	private String familyOrigin;			// 家庭出身
	private String firstEducation;			// 第一学历
	private Date graduateTime;				// 毕业时间
	private String healthStatus;			// 健康状况
	private String homeAddress;				// 家庭住址
	private String job;						// 岗位
	private String jobDist;					// 工别
	private String jobQualification;		// 职称资质
	private String jobTitle;				// 职称
	private Date jobTitleTime;				// 职称任职时间
	private String jobType;					// 工种
	private Date jobTypeTime;				// 工种任职时间
	private String myIngredient;			// 本人成分
	private String name;					// 姓名
	private String politicsStatus;			// 政治面貌
	private float postSalary;				// 岗位工资
	private String postState;				// 在岗状态
	private String postalCode;				// 邮政编码
	private String profession;				// 所学专业
	private String resume;					// 个人简历
	private String salaryGrade;				// 行政待遇级别
	private String schoolName;				// 学校名称
	private String secondName;				// 曾姓名
	private String sex;						// 性别
	private String skillGrage;				// 技术级别
	private float skillSalary;				// 技能工资
	private String source;					// 来源
	private String speciality;				// 本人特长
	private String studyStyle;				// 就读形式
	private String technicist;				// 技术人员
	private String telphone;				// 联系电话
	private Date unitTime;					// 来本单位时间
	private Date workTime;					// 参加工作时间
	private String productionLine;			// 生产线
	
	public Employee() {
	}
	
	public Employee(int id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue
	@javax.persistence.Column(name = "id", nullable = true, insertable = true, updatable = true)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Basic
	@javax.persistence.Column(name = "armTime", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public Date getArmTime() {
		return armTime;
	}
	public void setArmTime(Date armTime) {
		this.armTime = armTime;
	}
	@Basic
	@javax.persistence.Column(name = "birthday", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Basic
	@javax.persistence.Column(name = "bloodType", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	@Basic
	@javax.persistence.Column(name = "card", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	@Basic
	@javax.persistence.Column(name = "cardBirthday", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public Date getCardBirthday() {
		return cardBirthday;
	}
	public void setCardBirthday(Date cardBirthday) {
		this.cardBirthday = cardBirthday;
	}
	@Basic
	@javax.persistence.Column(name = "cfgStyle", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getCfgStyle() {
		return cfgStyle;
	}
	public void setCfgStyle(String cfgStyle) {
		this.cfgStyle = cfgStyle;
	}
	@Basic
	@javax.persistence.Column(name = "warn", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getWarn() {
		return warn;
	}
	public void setWarn(String warn) {
		this.warn = warn;
	}
	@Basic
	@javax.persistence.Column(name = "comment", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Basic
	@javax.persistence.Column(name = "conversionTime", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public Date getConversionTime() {
		return conversionTime;
	}
	public void setConversionTime(Date conversionTime) {
		this.conversionTime = conversionTime;
	}
	@Basic
	@javax.persistence.Column(name = "country", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Basic
	@javax.persistence.Column(name = "deptId", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	@Basic
	@javax.persistence.Column(name = "deptName", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Basic
	@javax.persistence.Column(name = "selfNationality", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getSelfNationality() {
		return selfNationality;
	}
	public void setSelfNationality(String selfNationality) {
		this.selfNationality = selfNationality;
	}
	@Basic
	@javax.persistence.Column(name = "domicilePlace", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getDomicilePlace() {
		return domicilePlace;
	}
	public void setDomicilePlace(String domicilePlace) {
		this.domicilePlace = domicilePlace;
	}
	@Basic
	@javax.persistence.Column(name = "duty", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	@Basic
	@javax.persistence.Column(name = "dutyTime", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public Date getDutyTime() {
		return dutyTime;
	}
	public void setDutyTime(Date dutyTime) {
		this.dutyTime = dutyTime;
	}
	@Basic
	@javax.persistence.Column(name = "education", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	@Basic
	@javax.persistence.Column(name = "empNumber", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getEmpNumber() {
		return empNumber;
	}
	public void setEmpNumber(String empNumber) {
		this.empNumber = empNumber;
	}
	@Basic
	@javax.persistence.Column(name = "endArmTime", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public Date getEndArmTime() {
		return endArmTime;
	}
	public void setEndArmTime(Date endArmTime) {
		this.endArmTime = endArmTime;
	}
	@Basic
	@javax.persistence.Column(name = "familyOrigin", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getFamilyOrigin() {
		return familyOrigin;
	}
	public void setFamilyOrigin(String familyOrigin) {
		this.familyOrigin = familyOrigin;
	}
	@Basic
	@javax.persistence.Column(name = "firstEducation", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getFirstEducation() {
		return firstEducation;
	}
	public void setFirstEducation(String firstEducation) {
		this.firstEducation = firstEducation;
	}
	@Basic
	@javax.persistence.Column(name = "graduateTime", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public Date getGraduateTime() {
		return graduateTime;
	}
	public void setGraduateTime(Date graduateTime) {
		this.graduateTime = graduateTime;
	}
	@Basic
	@javax.persistence.Column(name = "healthStatus", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getHealthStatus() {
		return healthStatus;
	}
	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}
	@Basic
	@javax.persistence.Column(name = "homeAddress", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	@Basic
	@javax.persistence.Column(name = "job", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	@Basic
	@javax.persistence.Column(name = "jobDist", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getJobDist() {
		return jobDist;
	}
	public void setJobDist(String jobDist) {
		this.jobDist = jobDist;
	}
	@Basic
	@javax.persistence.Column(name = "jobQualification", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getJobQualification() {
		return jobQualification;
	}
	public void setJobQualification(String jobQualification) {
		this.jobQualification = jobQualification;
	}
	@Basic
	@javax.persistence.Column(name = "jobTitle", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	@Basic
	@javax.persistence.Column(name = "jobTitleTime", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public Date getJobTitleTime() {
		return jobTitleTime;
	}
	public void setJobTitleTime(Date jobTitleTime) {
		this.jobTitleTime = jobTitleTime;
	}
	@Basic
	@javax.persistence.Column(name = "jobType", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	@Basic
	@javax.persistence.Column(name = "jobTypeTime", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public Date getJobTypeTime() {
		return jobTypeTime;
	}
	public void setJobTypeTime(Date jobTypeTime) {
		this.jobTypeTime = jobTypeTime;
	}
	@Basic
	@javax.persistence.Column(name = "myIngredient", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getMyIngredient() {
		return myIngredient;
	}
	public void setMyIngredient(String myIngredient) {
		this.myIngredient = myIngredient;
	}
	@Basic
	@javax.persistence.Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Basic
	@javax.persistence.Column(name = "politicsStatus", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getPoliticsStatus() {
		return politicsStatus;
	}
	public void setPoliticsStatus(String politicsStatus) {
		this.politicsStatus = politicsStatus;
	}
	@Basic
	@javax.persistence.Column(name = "postSalary", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public float getPostSalary() {
		return postSalary;
	}
	public void setPostSalary(float postSalary) {
		this.postSalary = postSalary;
	}
	@Basic
	@javax.persistence.Column(name = "postState", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getPostState() {
		return postState;
	}
	public void setPostState(String postState) {
		this.postState = postState;
	}
	@Basic
	@javax.persistence.Column(name = "postalCode", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	@Basic
	@javax.persistence.Column(name = "profession", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	@Basic
	@javax.persistence.Column(name = "resume", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	@Basic
	@javax.persistence.Column(name = "salaryGrade", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getSalaryGrade() {
		return salaryGrade;
	}
	public void setSalaryGrade(String salaryGrade) {
		this.salaryGrade = salaryGrade;
	}
	@Basic
	@javax.persistence.Column(name = "schoolName", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	@Basic
	@javax.persistence.Column(name = "secondName", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	@Basic
	@javax.persistence.Column(name = "sex", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Basic
	@javax.persistence.Column(name = "skillGrage", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getSkillGrage() {
		return skillGrage;
	}
	public void setSkillGrage(String skillGrage) {
		this.skillGrage = skillGrage;
	}
	@Basic
	@javax.persistence.Column(name = "skillSalary", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public float getSkillSalary() {
		return skillSalary;
	}
	public void setSkillSalary(float skillSalary) {
		this.skillSalary = skillSalary;
	}
	@Basic
	@javax.persistence.Column(name = "source", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	@Basic
	@javax.persistence.Column(name = "speciality", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	@Basic
	@javax.persistence.Column(name = "studyStyle", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getStudyStyle() {
		return studyStyle;
	}
	public void setStudyStyle(String studyStyle) {
		this.studyStyle = studyStyle;
	}
	@Basic
	@javax.persistence.Column(name = "technicist", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getTechnicist() {
		return technicist;
	}
	public void setTechnicist(String technicist) {
		this.technicist = technicist;
	}
	@Basic
	@javax.persistence.Column(name = "telphone", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	@Basic
	@javax.persistence.Column(name = "unitTime", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public Date getUnitTime() {
		return unitTime;
	}
	public void setUnitTime(Date unitTime) {
		this.unitTime = unitTime;
	}
	@Basic
	@javax.persistence.Column(name = "workTime", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public Date getWorkTime() {
		return workTime;
	}
	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
	}

	@Basic
	@javax.persistence.Column(name = "productionLine", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getProductionLine() {
		return productionLine;
	}

	public void setProductionLine(String productionLine) {
		this.productionLine = productionLine;
	}
}
