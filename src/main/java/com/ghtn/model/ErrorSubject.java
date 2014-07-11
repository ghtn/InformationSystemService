package com.ghtn.model;

import javax.persistence.*;

/**
 * Created by lihe on 14-7-10.
 */
@Entity
@Table(name = "error_subject", schema = "", catalog = "information_system")
public class ErrorSubject {
    private int id;
    private Integer examId;
    private String idCard;
    private String empNumber;
    private Integer subjectId;

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
    @Column(name = "examId", nullable = true, insertable = true, updatable = true)
    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
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
    @Column(name = "empNumber", nullable = true, insertable = true, updatable = true, length = 255)
    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    @Basic
    @Column(name = "subjectId", nullable = true, insertable = true, updatable = true)
    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErrorSubject that = (ErrorSubject) o;

        if (id != that.id) return false;
        if (empNumber != null ? !empNumber.equals(that.empNumber) : that.empNumber != null) return false;
        if (examId != null ? !examId.equals(that.examId) : that.examId != null) return false;
        if (idCard != null ? !idCard.equals(that.idCard) : that.idCard != null) return false;
        if (subjectId != null ? !subjectId.equals(that.subjectId) : that.subjectId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (examId != null ? examId.hashCode() : 0);
        result = 31 * result + (idCard != null ? idCard.hashCode() : 0);
        result = 31 * result + (empNumber != null ? empNumber.hashCode() : 0);
        result = 31 * result + (subjectId != null ? subjectId.hashCode() : 0);
        return result;
    }
}
