package com.ghtn.service.impl;

import com.ghtn.dao.ScoreDao;
import com.ghtn.model.Score;
import com.ghtn.service.ScoreManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lihe on 14-7-10.
 */
@Service("scoreManager")
public class ScoreManagerImpl extends GenericManagerImpl<Score, Integer> implements ScoreManager {

    private ScoreDao scoreDao;

    @Autowired
    public ScoreManagerImpl(ScoreDao scoreDao) {
        super(scoreDao);
        this.scoreDao = scoreDao;
    }

}
