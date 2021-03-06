package com.ghtn.service;

import com.ghtn.model.Subject;
import com.ghtn.vo.SubjectVO;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

/**
 * Created by lihe on 14-6-23.
 */
public interface SubjectManager extends GenericManager<Subject, Integer> {

    List<SubjectVO> listSubjectByPage(int start, int limit, String startDate, String endDate, int type, int mark, int deptId) throws Exception;

    Long getCount(String startDate, String endDate, int type, int mark, int deptId) throws ParseException;

    void addSubject(Subject subject, String paramStr, HttpSession session) throws Exception;

    void removeSubject(Subject subject);

    void updateSubject(Subject subject, String paramStr, HttpSession session) throws Exception;

    void importSubjects(HttpSession session) throws Exception;

    List<SubjectVO> listSubjectByDate(String startDate, String endDate, int deptId) throws Exception;

    List<SubjectVO> transformToVO(List<Subject> list) throws Exception;

    SubjectVO transformToVO(Subject subject) throws Exception;

}
