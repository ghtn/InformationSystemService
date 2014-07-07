package com.ghtn.dao;

import com.ghtn.model.Exam;

import java.util.Date;
import java.util.List;

/**
 * Created by lihe on 14-7-3.
 */
public interface ExamDao extends GenericDao<Exam, Integer> {

    List<Exam> listExamByPage(int start, int limit, Date startDate, Date endDate, int deptId);

    Long getCount(Date startDate, Date endDate, int deptId);
}
