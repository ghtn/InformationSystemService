package com.ghtn.controller;

import com.ghtn.model.Paper;
import com.ghtn.service.PaperManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Map;

/**
 * Created by lihe on 14-6-30.
 */
@Controller
@RequestMapping("/paper")
public class PaperController extends BaseController {

    private PaperManager paperManager;

    @Resource
    public void setPaperManager(PaperManager paperManager) {
        this.paperManager = paperManager;
    }

    @RequestMapping("/add")
    @ResponseBody
    public Map<String, Object> addPaper(Paper paper, String paramStr) {
        paperManager.addPaper(paper, paramStr);
        return operationSuccess();
    }

    @RequestMapping("/gen")
    @ResponseBody
    public Map<String, Object> genPaper(Paper paper, String startDate, String endDate, int choiceSubNum, int judgeSubNumber) throws ParseException {
        paperManager.genPaper(paper, startDate, endDate, choiceSubNum, judgeSubNumber);
        return operationSuccess();
    }

}
