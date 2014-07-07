package com.ghtn.model;

import javax.persistence.*;

/**
 * Created by lihe on 14-7-3.
 */
@Entity
@Table(name = "exam_emp", schema = "", catalog = "information_system")
public class ExamEmp {
    private int id;
    private int examId;
    private int empId;

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
    @Column(name = "examId", nullable = false, insertable = true, updatable = true)
    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    @Basic
    @Column(name = "empId", nullable = false, insertable = true, updatable = true)
    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamEmp examEmp = (ExamEmp) o;

        if (empId != examEmp.empId) return false;
        if (examId != examEmp.examId) return false;
        if (id != examEmp.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + examId;
        result = 31 * result + empId;
        return result;
    }
}
