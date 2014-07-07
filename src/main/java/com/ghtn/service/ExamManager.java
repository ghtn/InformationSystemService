package com.ghtn.service;

import com.ghtn.model.Exam;
import com.ghtn.vo.ExamVO;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

/**
 * Created by lihe on 14-7-3.
 */
public interface ExamManager extends GenericManager<Exam, Integer> {
    List<ExamVO> listExamByPage(int start, int limit, String startDate, String endDate, int deptId) throws ParseException;

    Long getCount(String startDate, String endDate, int deptId) throws ParseException;

    void addExam(String name, int paperId, String place, String examTime, HttpSession session) throws ParseException;

    void removeExam(Exam exam);

}
