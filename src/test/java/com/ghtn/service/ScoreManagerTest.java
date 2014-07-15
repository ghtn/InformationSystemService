package com.ghtn.service;

import com.ghtn.BaseTestCase;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by lihe on 14-7-15.
 */
public class ScoreManagerTest extends BaseTestCase {

    private ScoreManager scoreManager;

    @Resource
    public void setScoreManager(ScoreManager scoreManager) {
        this.scoreManager = scoreManager;
    }

    @Test
    public void testExportScore() throws IOException {
//        scoreManager.exportScore("", "", "", -1, -1, -1, -1, -1);
    }

}
