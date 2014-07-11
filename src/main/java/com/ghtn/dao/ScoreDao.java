package com.ghtn.dao;

import com.ghtn.model.Score;

/**
 * Created by lihe on 14-7-10.
 */
public interface ScoreDao extends GenericDao<Score, Integer> {

    Score getScore(int examId, String idCard);

}
