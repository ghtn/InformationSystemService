package com.ghtn.service;

import com.ghtn.Exception.ExistScoreException;
import com.ghtn.Exception.NullParamStrException;
import com.ghtn.model.Employee;
import com.ghtn.model.Exam;
import com.ghtn.vo.EmpVO;
import com.ghtn.vo.ExamVO;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by lihe on 14-7-3.
 */
public interface ExamManager extends GenericManager<Exam, Integer> {
    List<ExamVO> listExamByPage(int start, int limit, String startDate, String endDate, int deptId) throws ParseException;

    Long getCount(String startDate, String endDate, int deptId) throws ParseException;

    void addExam(String name, int paperId, String place, String examTime, String paramStr, HttpSession session) throws ParseException;

    void removeExam(Exam exam);

    List<Employee> listEmp(int deptId, String idCard, String name);

    List<Employee> getEmps(int examId);

    void updateExam(int id, String name, int paperId, String place, String examTime, String paramStr, HttpSession session) throws ParseException;

    EmpVO login(String idCard);

    boolean checkExamEmp(int examId, String idCard) throws ExistScoreException;

    List<ExamVO> listExam(int deptId);

    Map<String, Object> loadPaper(int examId) throws Exception;

    Map<String, Object> finishExam(String paramStr) throws Exception, NullParamStrException;
}
