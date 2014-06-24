package com.ghtn.model;

import javax.persistence.*;

/**
 * Created by lihe on 14-6-20.
 */
@Entity
@Table(name = "subject_answer", schema = "", catalog = "information_system")
public class SubjectAnswer {
    private int id;
    private Integer subjectId;
    private String description;
    private Integer correct;

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
    @Column(name = "subjectId", nullable = true, insertable = true, updatable = true)
    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
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
    @Column(name = "correct", nullable = true, insertable = true, updatable = true)
    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubjectAnswer that = (SubjectAnswer) o;

        if (id != that.id) return false;
        if (correct != null ? !correct.equals(that.correct) : that.correct != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (subjectId != null ? !subjectId.equals(that.subjectId) : that.subjectId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (subjectId != null ? subjectId.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (correct != null ? correct.hashCode() : 0);
        return result;
    }
}
