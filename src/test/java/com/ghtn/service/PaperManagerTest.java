package com.ghtn.service;

import com.ghtn.BaseTestCase;
import com.ghtn.model.Paper;
import org.junit.Test;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * Created by lihe on 14-6-30.
 */
public class PaperManagerTest extends BaseTestCase {

    private PaperManager paperManager;

    @Resource
    public void setPaperManager(PaperManager paperManager) {
        this.paperManager = paperManager;
    }

    @Test
    public void testGen() throws ParseException {
        Paper paper = new Paper();
        paper.setDeptId(2);
        paper.setExamTime(120);
        paper.setFullScore(100);
        paper.setName("test");
        paper.setPassScore(60);

        paperManager.genPaper(paper, "2014-06-01", "2014-07-01", 1000, 1000);
    }
}
