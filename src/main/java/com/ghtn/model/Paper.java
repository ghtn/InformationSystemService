package com.ghtn.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by lihe on 14-6-20.
 */
@Entity
public class Paper {
    private int id;
    private String name;
    private Integer fullScore;
    private Integer passScore;
    private Integer deptId;
    private Integer examTime;
    private String creator;
    private Timestamp createTime;
    private Integer subNum;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "deptId", nullable = true, insertable = true, updatable = true)
    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    @Basic
    @Column(name = "examTime", nullable = true, insertable = true, updatable = true)
    public Integer getExamTime() {
        return examTime;
    }

    public void setExamTime(Integer examTime) {
        this.examTime = examTime;
    }

    @Basic
    @Column(name = "creator", nullable = true, insertable = true, updatable = true, length = 255)
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "createTime", nullable = true, insertable = true, updatable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "subNum", nullable = true, insertable = true, updatable = true)
    public Integer getSubNum() {
        return subNum;
    }

    public void setSubNum(Integer subNum) {
        this.subNum = subNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paper paper = (Paper) o;

        if (id != paper.id) return false;
        if (createTime != null ? !createTime.equals(paper.createTime) : paper.createTime != null) return false;
        if (creator != null ? !creator.equals(paper.creator) : paper.creator != null) return false;
        if (deptId != null ? !deptId.equals(paper.deptId) : paper.deptId != null) return false;
        if (examTime != null ? !examTime.equals(paper.examTime) : paper.examTime != null) return false;
        if (fullScore != null ? !fullScore.equals(paper.fullScore) : paper.fullScore != null) return false;
        if (name != null ? !name.equals(paper.name) : paper.name != null) return false;
        if (passScore != null ? !passScore.equals(paper.passScore) : paper.passScore != null) return false;
        if (subNum != null ? !subNum.equals(paper.subNum) : paper.subNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (fullScore != null ? fullScore.hashCode() : 0);
        result = 31 * result + (passScore != null ? passScore.hashCode() : 0);
        result = 31 * result + (deptId != null ? deptId.hashCode() : 0);
        result = 31 * result + (examTime != null ? examTime.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (subNum != null ? subNum.hashCode() : 0);
        return result;
    }
}
