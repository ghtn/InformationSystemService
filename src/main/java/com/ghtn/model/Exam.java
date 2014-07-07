package com.ghtn.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lihe on 14-7-3.
 */
@Entity
public class Exam {
    private int id;
    private int deptId;
    private int paperId;
    private String name;
    private String place;
    private Date examTime;
    private int creator;
    private String creatorName;
    private Date createTime;
    private Integer editor;
    private String editorName;
    private Date editTime;

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
    @Column(name = "paperId", nullable = false, insertable = true, updatable = true)
    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "place", nullable = true, insertable = true, updatable = true, length = 255)
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "examTime", nullable = false, insertable = true, updatable = true)
    public Date getExamTime() {
        return examTime;
    }

    public void setExamTime(Date examTime) {
        this.examTime = examTime;
    }

    @Basic
    @Column(name = "creator", nullable = false, insertable = true, updatable = true)
    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "creatorName", nullable = true, insertable = true, updatable = true, length = 255)
    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createTime", nullable = false, insertable = true, updatable = true)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "editor", nullable = true, insertable = true, updatable = true)
    public Integer getEditor() {
        return editor;
    }

    public void setEditor(Integer editor) {
        this.editor = editor;
    }

    @Basic
    @Column(name = "editorName", nullable = true, insertable = true, updatable = true, length = 255)
    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "editTime", nullable = true, insertable = true, updatable = true)
    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Exam exam = (Exam) o;

        if (creator != exam.creator) return false;
        if (deptId != exam.deptId) return false;
        if (id != exam.id) return false;
        if (paperId != exam.paperId) return false;
        if (createTime != null ? !createTime.equals(exam.createTime) : exam.createTime != null) return false;
        if (creatorName != null ? !creatorName.equals(exam.creatorName) : exam.creatorName != null) return false;
        if (editTime != null ? !editTime.equals(exam.editTime) : exam.editTime != null) return false;
        if (editor != null ? !editor.equals(exam.editor) : exam.editor != null) return false;
        if (editorName != null ? !editorName.equals(exam.editorName) : exam.editorName != null) return false;
        if (examTime != null ? !examTime.equals(exam.examTime) : exam.examTime != null) return false;
        if (name != null ? !name.equals(exam.name) : exam.name != null) return false;
        if (place != null ? !place.equals(exam.place) : exam.place != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + deptId;
        result = 31 * result + paperId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (examTime != null ? examTime.hashCode() : 0);
        result = 31 * result + creator;
        result = 31 * result + (creatorName != null ? creatorName.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (editor != null ? editor.hashCode() : 0);
        result = 31 * result + (editorName != null ? editorName.hashCode() : 0);
        result = 31 * result + (editTime != null ? editTime.hashCode() : 0);
        return result;
    }
}
