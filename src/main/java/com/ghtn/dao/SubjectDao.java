package com.ghtn.dao;

import com.ghtn.model.Subject;

import java.util.List;

/**
 * Created by lihe on 14-6-23.
 */
public interface SubjectDao extends GenericDao<Subject, Integer> {

    List<Subject> listSubjectByPage(int start, int limit);

    List<Subject> listSubjectByPage(int start, int limit, int type);

    Long getCount();

    Long getCount(int type);

}
