package com.ghtn.service;

import com.ghtn.model.Paper;
import com.ghtn.vo.PaperVO;

import java.text.ParseException;
import java.util.List;

/**
 * Created by lihe on 14-6-30.
 */
public interface PaperManager extends GenericManager<Paper, Integer> {

    void addPaper(Paper paper, String paramStr);

    void genPaper(Paper paper, String startDate, String endDate, int choiceSubNum, int judgeSubNum) throws ParseException;

    void importPaper(int deptId, String fileName) throws Exception;

    List<PaperVO> listPaperByPage(int start, int limit, String startDate, String endDate, int deptId, int status) throws ParseException;

    Long getCount(String startDate, String endDate, int deptId, int status) throws ParseException;

    void publishPaper(int id);

    void revokePaper(int id);

    void removePaper(int id);

}
