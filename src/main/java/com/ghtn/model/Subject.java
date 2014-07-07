package com.ghtn.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lihe on 14-7-3.
 */
@Entity
public class Subject {
    private int id;
    private int deptId;
    private String description;
    private Integer mark;
    private Integer type;
    private Integer correct;
    private int creator;
    private String creatorName;
    private Date createTime;
    private Integer editor;
    private String editorName;
    private Date editTime;

    public Subject() {
    }

    public Subject(int id, int deptId, String description, Integer mark, Integer type, Integer correct, int creator, String creatorName, Date createTime, Integer editor, String editorName, Date editTime) {
        this.id = id;
        this.deptId = deptId;
        this.description = description;
        this.mark = mark;
        this.type = type;
        this.correct = correct;
        this.creator = creator;
        this.creatorName = creatorName;
        this.createTime = createTime;
        this.editor = editor;
        this.editorName = editorName;
        this.editTime = editTime;
    }

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
    @Column(name = "correct", nullable = true, insertable = true, updatable = true)
    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
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
    @Column(name = "createTime", nullable = true, insertable = true, updatable = true)
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

        Subject subject = (Subject) o;

        if (creator != subject.creator) return false;
        if (deptId != subject.deptId) return false;
        if (id != subject.id) return false;
        if (correct != null ? !correct.equals(subject.correct) : subject.correct != null) return false;
        if (createTime != null ? !createTime.equals(subject.createTime) : subject.createTime != null) return false;
        if (creatorName != null ? !creatorName.equals(subject.creatorName) : subject.creatorName != null) return false;
        if (description != null ? !description.equals(subject.description) : subject.description != null) return false;
        if (editTime != null ? !editTime.equals(subject.editTime) : subject.editTime != null) return false;
        if (editor != null ? !editor.equals(subject.editor) : subject.editor != null) return false;
        if (editorName != null ? !editorName.equals(subject.editorName) : subject.editorName != null) return false;
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
        result = 31 * result + (correct != null ? correct.hashCode() : 0);
        result = 31 * result + creator;
        result = 31 * result + (creatorName != null ? creatorName.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (editor != null ? editor.hashCode() : 0);
        result = 31 * result + (editorName != null ? editorName.hashCode() : 0);
        result = 31 * result + (editTime != null ? editTime.hashCode() : 0);
        return result;
    }
}
