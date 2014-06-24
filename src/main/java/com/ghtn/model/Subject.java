package com.ghtn.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lihe on 14-6-20.
 */
@Entity
public class Subject {
    private int id;
    private int deptId;
    private String description;
    private Integer mark;
    private Integer type;
    private String creator;
    private Date creatTime;

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
    @Column(name = "deptId", nullable = false, insertable = true, updatable = true)
    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    @Basic
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "mark", nullable = true, insertable = true, updatable = true)
    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    @Basic
    @Column(name = "type", nullable = true, insertable = true, updatable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creatTime", nullable = true, insertable = true, updatable = true)
    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (deptId != subject.deptId) return false;
        if (id != subject.id) return false;
        if (creatTime != null ? !creatTime.equals(subject.creatTime) : subject.creatTime != null) return false;
        if (creator != null ? !creator.equals(subject.creator) : subject.creator != null) return false;
        if (description != null ? !description.equals(subject.description) : subject.description != null) return false;
        if (mark != null ? !mark.equals(subject.mark) : subject.mark != null) return false;
        if (type != null ? !type.equals(subject.type) : subject.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + deptId;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (creatTime != null ? creatTime.hashCode() : 0);
        return result;
    }
}
