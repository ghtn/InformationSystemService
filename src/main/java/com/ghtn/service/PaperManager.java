package com.ghtn.service;

import com.ghtn.model.Paper;
import com.ghtn.vo.PaperVO;
import com.ghtn.vo.SubjectVO;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

/**
 * Created by lihe on 14-6-30.
 */
public interface PaperManager extends GenericManager<Paper, Integer> {

    void addPaper(Paper paper, String paramStr, HttpSession session);

    void genPaper(Paper paper, String startDate, String endDate, int choiceSubNum, int judgeSubNum, HttpSession session) throws ParseException;

    void importPaper(HttpSession session) throws Exception;

    List<PaperVO> listPaperByPage(int start, int limit, String startDate, String endDate, int deptId, int status) throws ParseException;

    Long getCount(String startDate, String endDate, int deptId, int status) throws ParseException;

    void publishPaper(int id);

    void revokePaper(int id);

    void removePaper(int id);

    List<SubjectVO> getSubjects(int paperId) throws Exception;

    int updatePaperSubject(int id, String paramStr) throws Exception;

    void updatePaper(Paper paper);

    List<PaperVO> listPaper(int deptId, int status);

    List<SubjectVO> loadPaper(int paperId) throws Exception;

}
