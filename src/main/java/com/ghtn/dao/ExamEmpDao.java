package com.ghtn.dao;

import com.ghtn.model.ExamEmp;

/**
 * Created by lihe on 14-7-7.
 */
public interface ExamEmpDao extends GenericDao<ExamEmp, Integer> {

    void removeExamEmp(int examId);

}
