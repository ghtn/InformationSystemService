package com.ghtn.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lihe on 14-7-3.
 */
@Entity
public class Paper {
    private int id;
    private String name;
    private Integer fullScore;
    private Integer passScore;
    private Integer deptId;
    private Integer examTime;
    private int creator;
    private String creatorName;
    private Date createTime;
    private Integer subNum;
    private Integer status;
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
    @Column(name = "subNum", nullable = true, insertable = true, updatable = true)
    public Integer getSubNum() {
        return subNum;
    }

    public void setSubNum(Integer subNum) {
        this.subNum = subNum;
    }

    @Basic
    @Column(name = "status", nullable = true, insertable = true, updatable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

        Paper paper = (Paper) o;

        if (creator != paper.creator) return false;
        if (id != paper.id) return false;
        if (createTime != null ? !createTime.equals(paper.createTime) : paper.createTime != null) return false;
        if (creatorName != null ? !creatorName.equals(paper.creatorName) : paper.creatorName != null) return false;
        if (deptId != null ? !deptId.equals(paper.deptId) : paper.deptId != null) return false;
        if (editTime != null ? !editTime.equals(paper.editTime) : paper.editTime != null) return false;
        if (editor != null ? !editor.equals(paper.editor) : paper.editor != null) return false;
        if (editorName != null ? !editorName.equals(paper.editorName) : paper.editorName != null) return false;
        if (examTime != null ? !examTime.equals(paper.examTime) : paper.examTime != null) return false;
        if (fullScore != null ? !fullScore.equals(paper.fullScore) : paper.fullScore != null) return false;
        if (name != null ? !name.equals(paper.name) : paper.name != null) return false;
        if (passScore != null ? !passScore.equals(paper.passScore) : paper.passScore != null) return false;
        if (status != null ? !status.equals(paper.status) : paper.status != null) return false;
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
        result = 31 * result + creator;
        result = 31 * result + (creatorName != null ? creatorName.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (subNum != null ? subNum.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (editor != null ? editor.hashCode() : 0);
        result = 31 * result + (editorName != null ? editorName.hashCode() : 0);
        result = 31 * result + (editTime != null ? editTime.hashCode() : 0);
        return result;
    }
}
