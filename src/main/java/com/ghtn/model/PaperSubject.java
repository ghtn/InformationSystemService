package com.ghtn.model;

import javax.persistence.*;

/**
 * Created by lihe on 14-6-30.
 */
@Entity
@Table(name = "paper_subject", schema = "", catalog = "information_system")
public class PaperSubject {
    private int id;
    private int paperId;
    private int subjectId;

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
    @Column(name = "paperId", nullable = false, insertable = true, updatable = true)
    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    @Basic
    @Column(name = "subjectId", nullable = false, insertable = true, updatable = true)
    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaperSubject that = (PaperSubject) o;

        if (id != that.id) return false;
        if (paperId != that.paperId) return false;
        if (subjectId != that.subjectId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + paperId;
        result = 31 * result + subjectId;
        return result;
    }
}
