package com.ghtn.service;

import com.ghtn.model.Paper;

import java.text.ParseException;

/**
 * Created by lihe on 14-6-30.
 */
public interface PaperManager extends GenericManager<Paper, Integer> {

    void addPaper(Paper paper, String paramStr);

    void genPaper(Paper paper, String startDate, String endDate, int choiceSubNum, int judgeSubNum) throws ParseException;

    void importPaper(int deptId, String fileName) throws Exception;
}
