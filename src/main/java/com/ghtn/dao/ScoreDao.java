package com.ghtn.dao;

import com.ghtn.model.Score;

import java.util.List;

/**
 * Created by lihe on 14-7-10.
 */
public interface ScoreDao extends GenericDao<Score, Integer> {

    Score getScore(int examId, String idCard);

    List<Score> listScoreByPage(int start, int limit, String idCard, String name, String empNumber, int examId, int examScore, int pass, int errorCount, int deptId);

    Long getCount(String idCard, String name, String empNumber, int examId, int examScore, int pass, int errorCount, int deptId);

    List<Score> listScore(String idCard, String name, String empNumber, int examId, int examScore, int pass, int errorCount, int deptId);

}
