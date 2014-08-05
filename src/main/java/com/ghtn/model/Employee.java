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
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(int id) {
		// TODO Auto-generated constructor stub
		this.setId(id);
	}
	
	
    private int id;

    @Id
    @GeneratedValue
    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    private int age;
//
//    @Basic
//    @javax.persistence.Column(name = "age", nullable = true, insertable = true, updatable = true)
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }

    private Date armTime;

    @Basic
    @javax.persistence.Column(name = "armTime", nullable = true, insertable = true, updatable = true)
    public Date getArmTime() {
        return armTime;
    }

    public void setArmTime(Date armTime) {
        this.armTime = armTime;
    }

    private Date birthday;

    @Basic
    @javax.persistence.Column(name = "birthday", nullable = true, insertable = true, updatable = true)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    private String bloodType;

    @Basic
    @javax.persistence.Column(name = "bloodType", nullable = true, insertable = true, updatable = true, length = 255)
    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    private String card;

    @Basic
    @javax.persistence.Column(name = "card", unique = true, nullable = false, insertable = true, updatable = true, length = 255)
    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    private Date cardBirthday;

    @Basic
    @javax.persistence.Column(name = "cardBirthday", nullable = true, insertable = true, updatable = true)
    public Date getCardBirthday() {
        return cardBirthday;
    }

    public void setCardBirthday(Date cardBirthday) {
        this.cardBirthday = cardBirthday;
    }

    private String cfgStyle;

    @Basic
    @javax.persistence.Column(name = "cfgStyle", nullable = true, insertable = true, updatable = true, length = 255)
    public String getCfgStyle() {
        return cfgStyle;
    }

    public void setCfgStyle(String cfgStyle) {
        this.cfgStyle = cfgStyle;
    }
    
    private String warn;
    
    @Basic
    @javax.persistence.Column(name = "warn", nullable = true, insertable = true, updatable = true, length = 255)
    public String getWarn() {
    	return warn;
    }
    
    public void setWarn(String warn) {
    	this.warn = warn;
    }

    private String comment;

    @Basic
    @javax.persistence.Column(name = "comment", nullable = true, insertable = true, updatable = true, length = 255)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private Date conversionTime;

    @Basic
    @javax.persistence.Column(name = "conversionTime", nullable = true, insertable = true, updatable = true)
    public Date getConversionTime() {
        return conversionTime;
    }

    public void setConversionTime(Date conversionTime) {
        this.conversionTime = conversionTime;
    }

    private String country;

    @Basic
    @javax.persistence.Column(name = "country", nullable = true, insertable = true, updatable = true, length = 255)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private Integer deptId;

    @Basic
    @javax.persistence.Column(name = "deptId", nullable = false, insertable = true, updatable = true)
    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    private String deptName;

    @Basic
    @javax.persistence.Column(name = "deptName", nullable = false, insertable = true, updatable = true, length = 255)
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    
    private String selfNationality;
    
    @Basic
    @javax.persistence.Column(name = "selfNationality", nullable = true, insertable = true, updatable = true, length = 255)
    public String getSelfNationality() {
    	return selfNationality;
    }
    
    public void setSelfNationality(String selfNationality) {
    	this.selfNationality = selfNationality;
    }

    private String domicilePlace;

    @Basic
    @javax.persistence.Column(name = "domicilePlace", nullable = true, insertable = true, updatable = true, length = 255)
    public String getDomicilePlace() {
        return domicilePlace;
    }

    public void setDomicilePlace(String domicilePlace) {
        this.domicilePlace = domicilePlace;
    }

    private String duty;

    @Basic
    @javax.persistence.Column(name = "duty", nullable = true, insertable = true, updatable = true, length = 255)
    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    private Date dutyTime;

    @Basic
    @javax.persistence.Column(name = "dutyTime", nullable = true, insertable = true, updatable = true)
    public Date getDutyTime() {
        return dutyTime;
    }

    public void setDutyTime(Date dutyTime) {
        this.dutyTime = dutyTime;
    }

    private String education;

    @Basic
    @javax.persistence.Column(name = "education", nullable = true, insertable = true, updatable = true, length = 255)
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    private String empNumber;

    @Basic
    @javax.persistence.Column(name = "empNumber", nullable = false, insertable = true, updatable = true, length = 255)
    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    private Date endArmTime;

    @Basic
    @javax.persistence.Column(name = "endArmTime", nullable = true, insertable = true, updatable = true)
    public Date getEndArmTime() {
        return endArmTime;
    }

    public void setEndArmTime(Date endArmTime) {
        this.endArmTime = endArmTime;
    }

    private String familyOrigin;

    @Basic
    @javax.persistence.Column(name = "familyOrigin", nullable = true, insertable = true, updatable = true, length = 255)
    public String getFamilyOrigin() {
        return familyOrigin;
    }

    public void setFamilyOrigin(String familyOrigin) {
        this.familyOrigin = familyOrigin;
    }

    private String firstEducation;

    @Basic
    @javax.persistence.Column(name = "firstEducation", nullable = true, insertable = true, updatable = true, length = 255)
    public String getFirstEducation() {
        return firstEducation;
    }

    public void setFirstEducation(String firstEducation) {
        this.firstEducation = firstEducation;
    }

    private Date graduateTime;

    @Basic
    @javax.persistence.Column(name = "graduateTime", nullable = true, insertable = true, updatable = true)
    public Date getGraduateTime() {
        return graduateTime;
    }

    public void setGraduateTime(Date graduateTime) {
        this.graduateTime = graduateTime;
    }

    private String healthStatus;

    @Basic
    @javax.persistence.Column(name = "healthStatus", nullable = true, insertable = true, updatable = true, length = 255)
    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    private String homeAddress;

    @Basic
    @javax.persistence.Column(name = "homeAddress", nullable = true, insertable = true, updatable = true, length = 255)
    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    private String job;

    @Basic
    @javax.persistence.Column(name = "job", nullable = true, insertable = true, updatable = true, length = 255)
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    private String jobDist;

    @Basic
    @javax.persistence.Column(name = "jobDist", nullable = true, insertable = true, updatable = true, length = 255)
    public String getJobDist() {
        return jobDist;
    }

    public void setJobDist(String jobDist) {
        this.jobDist = jobDist;
    }

    private String jobQualification;

    @Basic
    @javax.persistence.Column(name = "jobQualification", nullable = true, insertable = true, updatable = true, length = 255)
    public String getJobQualification() {
        return jobQualification;
    }

    public void setJobQualification(String jobQualification) {
        this.jobQualification = jobQualification;
    }

    private String jobTitle;

    @Basic
    @javax.persistence.Column(name = "jobTitle", nullable = true, insertable = true, updatable = true, length = 255)
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    private Date jobTitleTime;

    @Basic
    @javax.persistence.Column(name = "jobTitleTime", nullable = true, insertable = true, updatable = true)
    public Date getJobTitleTime() {
        return jobTitleTime;
    }

    public void setJobTitleTime(Date jobTitleTime) {
        this.jobTitleTime = jobTitleTime;
    }

    private String jobType;

    @Basic
    @javax.persistence.Column(name = "jobType", nullable = false, insertable = true, updatable = true, length = 255)
    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    private Date jobTypeTime;

    @Basic
    @javax.persistence.Column(name = "jobTypeTime", nullable = true, insertable = true, updatable = true, length = 255)
    public Date getJobTypeTime() {
        return jobTypeTime;
    }

    public void setJobTypeTime(Date jobTypeTime) {
        this.jobTypeTime = jobTypeTime;
    }

    private Date memberShipTime;

    @Basic
    @javax.persistence.Column(name = "memberShipTime", nullable = true, insertable = true, updatable = true)
    public Date getMemberShipTime() {
        return memberShipTime;
    }

    public void setMemberShipTime(Date memberShipTime) {
        this.memberShipTime = memberShipTime;
    }

    private String myIngredient;

    @Basic
    @javax.persistence.Column(name = "myIngredient", nullable = true, insertable = true, updatable = true, length = 255)
    public String getMyIngredient() {
        return myIngredient;
    }

    public void setMyIngredient(String myIngredient) {
        this.myIngredient = myIngredient;
    }

    private String name;

    @Basic
    @javax.persistence.Column(name = "name", nullable = false, insertable = true, updatable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String nationality;

    @Basic
    @javax.persistence.Column(name = "nationality", nullable = true, insertable = true, updatable = true, length = 255)
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    private Date partyTime;

    @Basic
    @javax.persistence.Column(name = "partyTime", nullable = true, insertable = true, updatable = true)
    public Date getPartyTime() {
        return partyTime;
    }

    public void setPartyTime(Date partyTime) {
        this.partyTime = partyTime;
    }

    private String politicsStatus;

    @Basic
    @javax.persistence.Column(name = "politicsStatus", nullable = true, insertable = true, updatable = true, length = 255)
    public String getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(String politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    private float postSalary;

    @Basic
    @javax.persistence.Column(name = "postSalary", nullable = false, insertable = true, updatable = true, precision = 0)
    public float getPostSalary() {
        return postSalary;
    }

    public void setPostSalary(float postSalary) {
        this.postSalary = postSalary;
    }

    private String postState;

    @Basic
    @javax.persistence.Column(name = "postState", nullable = true, insertable = true, updatable = true, length = 255)
    public String getPostState() {
        return postState;
    }

    public void setPostState(String postState) {
        this.postState = postState;
    }

    private String postalCode;

    @Basic
    @javax.persistence.Column(name = "postalCode", nullable = true, insertable = true, updatable = true, length = 255)
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    private String productLine;

    @Basic
    @javax.persistence.Column(name = "productLine", nullable = true, insertable = true, updatable = true, length = 255)
    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    private String profession;

    @Basic
    @javax.persistence.Column(name = "profession", nullable = true, insertable = true, updatable = true, length = 255)
    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    private String resume;

    @Basic
    @javax.persistence.Column(name = "resume", nullable = true, insertable = true, updatable = true, length = 255)
    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    private String salaryGrade;

    @Basic
    @javax.persistence.Column(name = "salaryGrade", nullable = true, insertable = true, updatable = true, length = 255)
    public String getSalaryGrade() {
        return salaryGrade;
    }

    public void setSalaryGrade(String salaryGrade) {
        this.salaryGrade = salaryGrade;
    }

    private String schoolName;

    @Basic
    @javax.persistence.Column(name = "schoolName", nullable = true, insertable = true, updatable = true, length = 255)
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    private String secondName;

    @Basic
    @javax.persistence.Column(name = "secondName", nullable = true, insertable = true, updatable = true, length = 255)
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    private String sex;

    @Basic
    @javax.persistence.Column(name = "sex", nullable = false, insertable = true, updatable = true, length = 255)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    private String skillGrage;

    @Basic
    @javax.persistence.Column(name = "skillGrage", nullable = true, insertable = true, updatable = true, length = 255)
    public String getSkillGrage() {
        return skillGrage;
    }

    public void setSkillGrage(String skillGrage) {
        this.skillGrage = skillGrage;
    }

    private float skillSalary;

    @Basic
    @javax.persistence.Column(name = "skillSalary", nullable = false, insertable = true, updatable = true, precision = 0)
    public float getSkillSalary() {
        return skillSalary;
    }

    public void setSkillSalary(float skillSalary) {
        this.skillSalary = skillSalary;
    }

    private String source;

    @Basic
    @javax.persistence.Column(name = "source", nullable = true, insertable = true, updatable = true, length = 255)
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    private String speciality;

    @Basic
    @javax.persistence.Column(name = "speciality", nullable = true, insertable = true, updatable = true, length = 255)
    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    private String studyStyle;

    @Basic
    @javax.persistence.Column(name = "studyStyle", nullable = true, insertable = true, updatable = true, length = 255)
    public String getStudyStyle() {
        return studyStyle;
    }

    public void setStudyStyle(String studyStyle) {
        this.studyStyle = studyStyle;
    }

    private String technicist;

    @Basic
    @javax.persistence.Column(name = "technicist", nullable = true, insertable = true, updatable = true, length = 255)
    public String getTechnicist() {
        return technicist;
    }

    public void setTechnicist(String technicist) {
        this.technicist = technicist;
    }

    private String telphone;

    @Basic
    @javax.persistence.Column(name = "telphone", nullable = true, insertable = true, updatable = true, length = 255)
    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    private Date unitTime;

    @Basic
    @javax.persistence.Column(name = "unitTime", nullable = false, insertable = true, updatable = true)
    public Date getUnitTime() {
        return unitTime;
    }

    public void setUnitTime(Date unitTime) {
        this.unitTime = unitTime;
    }

    private Date workTime;

    @Basic
    @javax.persistence.Column(name = "workTime", nullable = true, insertable = true, updatable = true)
    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    private Integer userId;

    @Basic
    @javax.persistence.Column(name = "userId", nullable = true, insertable = true, updatable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

//        if (age != employee.age) return false;
        if (id != employee.id) return false;
        if (Float.compare(employee.postSalary, postSalary) != 0) return false;
        if (Float.compare(employee.skillSalary, skillSalary) != 0) return false;
        if (armTime != null ? !armTime.equals(employee.armTime) : employee.armTime != null) return false;
        if (birthday != null ? !birthday.equals(employee.birthday) : employee.birthday != null) return false;
        if (bloodType != null ? !bloodType.equals(employee.bloodType) : employee.bloodType != null) return false;
        if (card != null ? !card.equals(employee.card) : employee.card != null) return false;
        if (cardBirthday != null ? !cardBirthday.equals(employee.cardBirthday) : employee.cardBirthday != null)
            return false;
        if (cfgStyle != null ? !cfgStyle.equals(employee.cfgStyle) : employee.cfgStyle != null) return false;
        if (warn != null ? !warn.equals(employee.warn) : employee.warn != null) return false;
        if (comment != null ? !comment.equals(employee.comment) : employee.comment != null) return false;
        if (conversionTime != null ? !conversionTime.equals(employee.conversionTime) : employee.conversionTime != null)
            return false;
        if (country != null ? !country.equals(employee.country) : employee.country != null) return false;
        if (deptId != null ? !deptId.equals(employee.deptId) : employee.deptId != null) return false;
        if (deptName != null ? !deptName.equals(employee.deptName) : employee.deptName != null) return false;
        if (selfNationality != null ? !selfNationality.equals(employee.selfNationality) : employee.selfNationality != null) return false;
        if (domicilePlace != null ? !domicilePlace.equals(employee.domicilePlace) : employee.domicilePlace != null)
            return false;
        if (duty != null ? !duty.equals(employee.duty) : employee.duty != null) return false;
        if (dutyTime != null ? !dutyTime.equals(employee.dutyTime) : employee.dutyTime != null) return false;
        if (education != null ? !education.equals(employee.education) : employee.education != null) return false;
        if (empNumber != null ? !empNumber.equals(employee.empNumber) : employee.empNumber != null) return false;
        if (endArmTime != null ? !endArmTime.equals(employee.endArmTime) : employee.endArmTime != null) return false;
        if (familyOrigin != null ? !familyOrigin.equals(employee.familyOrigin) : employee.familyOrigin != null)
            return false;
        if (firstEducation != null ? !firstEducation.equals(employee.firstEducation) : employee.firstEducation != null)
            return false;
        if (graduateTime != null ? !graduateTime.equals(employee.graduateTime) : employee.graduateTime != null)
            return false;
        if (healthStatus != null ? !healthStatus.equals(employee.healthStatus) : employee.healthStatus != null)
            return false;
        if (homeAddress != null ? !homeAddress.equals(employee.homeAddress) : employee.homeAddress != null)
            return false;
        if (job != null ? !job.equals(employee.job) : employee.job != null) return false;
        if (jobDist != null ? !jobDist.equals(employee.jobDist) : employee.jobDist != null) return false;
        if (jobQualification != null ? !jobQualification.equals(employee.jobQualification) : employee.jobQualification != null)
            return false;
        if (jobTitle != null ? !jobTitle.equals(employee.jobTitle) : employee.jobTitle != null) return false;
        if (jobTitleTime != null ? !jobTitleTime.equals(employee.jobTitleTime) : employee.jobTitleTime != null)
            return false;
        if (jobType != null ? !jobType.equals(employee.jobType) : employee.jobType != null) return false;
        if (jobTypeTime != null ? !jobTypeTime.equals(employee.jobTypeTime) : employee.jobTypeTime != null)
            return false;
        if (memberShipTime != null ? !memberShipTime.equals(employee.memberShipTime) : employee.memberShipTime != null)
            return false;
        if (myIngredient != null ? !myIngredient.equals(employee.myIngredient) : employee.myIngredient != null)
            return false;
        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (nationality != null ? !nationality.equals(employee.nationality) : employee.nationality != null)
            return false;
        if (partyTime != null ? !partyTime.equals(employee.partyTime) : employee.partyTime != null) return false;
        if (politicsStatus != null ? !politicsStatus.equals(employee.politicsStatus) : employee.politicsStatus != null)
            return false;
        if (postState != null ? !postState.equals(employee.postState) : employee.postState != null) return false;
        if (postalCode != null ? !postalCode.equals(employee.postalCode) : employee.postalCode != null) return false;
        if (productLine != null ? !productLine.equals(employee.productLine) : employee.productLine != null)
            return false;
        if (profession != null ? !profession.equals(employee.profession) : employee.profession != null) return false;
        if (resume != null ? !resume.equals(employee.resume) : employee.resume != null) return false;
        if (salaryGrade != null ? !salaryGrade.equals(employee.salaryGrade) : employee.salaryGrade != null)
            return false;
        if (schoolName != null ? !schoolName.equals(employee.schoolName) : employee.schoolName != null) return false;
        if (secondName != null ? !secondName.equals(employee.secondName) : employee.secondName != null) return false;
        if (sex != null ? !sex.equals(employee.sex) : employee.sex != null) return false;
        if (skillGrage != null ? !skillGrage.equals(employee.skillGrage) : employee.skillGrage != null) return false;
        if (source != null ? !source.equals(employee.source) : employee.source != null) return false;
        if (speciality != null ? !speciality.equals(employee.speciality) : employee.speciality != null) return false;
        if (studyStyle != null ? !studyStyle.equals(employee.studyStyle) : employee.studyStyle != null) return false;
        if (technicist != null ? !technicist.equals(employee.technicist) : employee.technicist != null) return false;
        if (telphone != null ? !telphone.equals(employee.telphone) : employee.telphone != null) return false;
        if (unitTime != null ? !unitTime.equals(employee.unitTime) : employee.unitTime != null) return false;
        if (userId != null ? !userId.equals(employee.userId) : employee.userId != null) return false;
        if (workTime != null ? !workTime.equals(employee.workTime) : employee.workTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
//        result = 31 * result + age;
        result = 31 * result + (armTime != null ? armTime.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (bloodType != null ? bloodType.hashCode() : 0);
        result = 31 * result + (card != null ? card.hashCode() : 0);
        result = 31 * result + (cardBirthday != null ? cardBirthday.hashCode() : 0);
        result = 31 * result + (cfgStyle != null ? cfgStyle.hashCode() : 0);
        result = 31 * result + (warn != null ? warn.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (conversionTime != null ? conversionTime.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (deptId != null ? deptId.hashCode() : 0);
        result = 31 * result + (deptName != null ? deptName.hashCode() : 0);
        result = 31 * result + (selfNationality != null ? selfNationality.hashCode() : 0);
        result = 31 * result + (domicilePlace != null ? domicilePlace.hashCode() : 0);
        result = 31 * result + (duty != null ? duty.hashCode() : 0);
        result = 31 * result + (dutyTime != null ? dutyTime.hashCode() : 0);
        result = 31 * result + (education != null ? education.hashCode() : 0);
        result = 31 * result + (empNumber != null ? empNumber.hashCode() : 0);
        result = 31 * result + (endArmTime != null ? endArmTime.hashCode() : 0);
        result = 31 * result + (familyOrigin != null ? familyOrigin.hashCode() : 0);
        result = 31 * result + (firstEducation != null ? firstEducation.hashCode() : 0);
        result = 31 * result + (graduateTime != null ? graduateTime.hashCode() : 0);
        result = 31 * result + (healthStatus != null ? healthStatus.hashCode() : 0);
        result = 31 * result + (homeAddress != null ? homeAddress.hashCode() : 0);
        result = 31 * result + (job != null ? job.hashCode() : 0);
        result = 31 * result + (jobDist != null ? jobDist.hashCode() : 0);
        result = 31 * result + (jobQualification != null ? jobQualification.hashCode() : 0);
        result = 31 * result + (jobTitle != null ? jobTitle.hashCode() : 0);
        result = 31 * result + (jobTitleTime != null ? jobTitleTime.hashCode() : 0);
        result = 31 * result + (jobType != null ? jobType.hashCode() : 0);
        result = 31 * result + (jobTypeTime != null ? jobTypeTime.hashCode() : 0);
        result = 31 * result + (memberShipTime != null ? memberShipTime.hashCode() : 0);
        result = 31 * result + (myIngredient != null ? myIngredient.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        result = 31 * result + (partyTime != null ? partyTime.hashCode() : 0);
        result = 31 * result + (politicsStatus != null ? politicsStatus.hashCode() : 0);
        result = 31 * result + (postSalary != +0.0f ? Float.floatToIntBits(postSalary) : 0);
        result = 31 * result + (postState != null ? postState.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (productLine != null ? productLine.hashCode() : 0);
        result = 31 * result + (profession != null ? profession.hashCode() : 0);
        result = 31 * result + (resume != null ? resume.hashCode() : 0);
        result = 31 * result + (salaryGrade != null ? salaryGrade.hashCode() : 0);
        result = 31 * result + (schoolName != null ? schoolName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (skillGrage != null ? skillGrage.hashCode() : 0);
        result = 31 * result + (skillSalary != +0.0f ? Float.floatToIntBits(skillSalary) : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (speciality != null ? speciality.hashCode() : 0);
        result = 31 * result + (studyStyle != null ? studyStyle.hashCode() : 0);
        result = 31 * result + (technicist != null ? technicist.hashCode() : 0);
        result = 31 * result + (telphone != null ? telphone.hashCode() : 0);
        result = 31 * result + (unitTime != null ? unitTime.hashCode() : 0);
        result = 31 * result + (workTime != null ? workTime.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
