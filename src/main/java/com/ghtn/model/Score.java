package com.ghtn.model;

import javax.persistence.*;

/**
 * Created by lihe on 14-7-10.
 */
@Entity
public class Score {
    private int id;
    private String idCard;
    private String name;
    private Integer deptId;
    private String deptName;
    private String empNumber;
    private Integer examId;
    private String examName;
    private Integer fullScore;
    private Integer passScore;
    private Integer examScore;
    private Integer pass;
    private Integer errorCount;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "idCard", nullable = true, insertable = true, updatable = true, length = 255)
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Basic
    @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "deptId", nullable = true, insertable = true, updatable = true)
    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    @Basic
    @Column(name = "deptName", nullable = true, insertable = true, updatable = true, length = 255)
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Basic
    @Column(name = "empNumber", nullable = true, insertable = true, updatable = true, length = 255)
    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    @Basic
    @Column(name = "examId", nullable = true, insertable = true, updatable = true)
    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    @Basic
    @Column(name = "examName", nullable = true, insertable = true, updatable = true, length = 255)
    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    @Basic
    @Column(name = "fullScore", nullable = true, insertable = true, updatable = true)
    public Integer getFullScore() {
        return fullScore;
    }

    public void setFullScore(Integer fullScore) {
        this.fullScore = fullScore;
    }

    @Basic
    @Column(name = "passScore", nullable = true, insertable = true, updatable = true)
    public Integer getPassScore() {
        return passScore;
    }

    public void setPassScore(Integer passScore) {
        this.passScore = passScore;
    }

    @Basic
    @Column(name = "examScore", nullable = true, insertable = true, updatable = true)
    public Integer getExamScore() {
        return examScore;
    }

    public void setExamScore(Integer examScore) {
        this.examScore = examScore;
    }

    @Basic
    @Column(name = "pass", nullable = true, insertable = true, updatable = true)
    public Integer getPass() {
        return pass;
    }

    public void setPass(Integer pass) {
        this.pass = pass;
    }

    @Basic
    @Column(name = "errorCount", nullable = true, insertable = true, updatable = true)
    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Score score = (Score) o;

        if (id != score.id) return false;
        if (deptId != null ? !deptId.equals(score.deptId) : score.deptId != null) return false;
        if (deptName != null ? !deptName.equals(score.deptName) : score.deptName != null) return false;
        if (empNumber != null ? !empNumber.equals(score.empNumber) : score.empNumber != null) return false;
        if (errorCount != null ? !errorCount.equals(score.errorCount) : score.errorCount != null) return false;
        if (examId != null ? !examId.equals(score.examId) : score.examId != null) return false;
        if (examName != null ? !examName.equals(score.examName) : score.examName != null) return false;
        if (examScore != null ? !examScore.equals(score.examScore) : score.examScore != null) return false;
        if (fullScore != null ? !fullScore.equals(score.fullScore) : score.fullScore != null) return false;
        if (idCard != null ? !idCard.equals(score.idCard) : score.idCard != null) return false;
        if (name != null ? !name.equals(score.name) : score.name != null) return false;
        if (pass != null ? !pass.equals(score.pass) : score.pass != null) return false;
        if (passScore != null ? !passScore.equals(score.passScore) : score.passScore != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (idCard != null ? idCard.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (deptId != null ? deptId.hashCode() : 0);
        result = 31 * result + (deptName != null ? deptName.hashCode() : 0);
        result = 31 * result + (empNumber != null ? empNumber.hashCode() : 0);
        result = 31 * result + (examId != null ? examId.hashCode() : 0);
        result = 31 * result + (examName != null ? examName.hashCode() : 0);
        result = 31 * result + (fullScore != null ? fullScore.hashCode() : 0);
        result = 31 * result + (passScore != null ? passScore.hashCode() : 0);
        result = 31 * result + (examScore != null ? examScore.hashCode() : 0);
        result = 31 * result + (pass != null ? pass.hashCode() : 0);
        result = 31 * result + (errorCount != null ? errorCount.hashCode() : 0);
        return result;
    }
}
