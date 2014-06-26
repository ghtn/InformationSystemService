package com.ghtn.service;

import com.ghtn.model.Subject;
import com.ghtn.vo.SubjectVO;

import java.util.List;

/**
 * Created by lihe on 14-6-23.
 */
public interface SubjectManager extends GenericManager<Subject, Integer> {

    List<SubjectVO> listSubjectByPage(int start, int limit, int type) throws Exception;

    Long getCount(int type);

    void addSubject(Subject subject, String paramStr) throws Exception;

    void removeSubject(Subject subject);

    void updateSubject(Subject subject, String paramStr) throws Exception;

    void importSubjects(int deptId, String fileName) throws Exception;

}
