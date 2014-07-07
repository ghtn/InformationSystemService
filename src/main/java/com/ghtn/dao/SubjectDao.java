package com.ghtn.dao;

import com.ghtn.model.Subject;

import java.util.Date;
import java.util.List;

/**
 * Created by lihe on 14-6-23.
 */
public interface SubjectDao extends GenericDao<Subject, Integer> {

    List<Subject> listSubjectByPage(int start, int limit, int deptId);

    List<Subject> listSubjectByPage(int start, int limit, int type, int deptId);

    Long getCount(int deptId);

    Long getCount(int type, int deptId);

    List<Subject> listSubjectByDate(Date startDate, Date endDate, int deptId);

    List<Subject> listSubjectByDate(Date startDate, Date endDate, int type, int deptId);
}
