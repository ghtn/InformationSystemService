package com.ghtn.controller;

import com.ghtn.service.ScoreManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihe on 14-7-15.
 */
@Controller
@RequestMapping("/score")
public class ScoreController extends BaseController {

    private ScoreManager scoreManager;

    @Resource
    public void setScoreManager(ScoreManager scoreManager) {
        this.scoreManager = scoreManager;
    }

    @RequestMapping("/listScoreByPage")
    @ResponseBody
    public Map<String, Object> listScoreByPage(int start, int limit, String idCard, String name, String empNumber, int examId, int examScore, int pass, int errorCount, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        // TODO : deptId从session获取
        int deptId = 2;

        map.put("total", scoreManager.getCount(idCard, name, empNumber, examId, examScore, pass, errorCount, deptId));
        map.put("items", scoreManager.listScoreByPage(start, limit, idCard, name, empNumber, examId, examScore, pass, errorCount, deptId));

        return map;
    }

    @RequestMapping("/exportScore")
    @ResponseBody
    public String exportScore(String idCard, String name, String empNumber, int examId, int examScore, int pass, int errorCount, HttpSession session, HttpServletResponse resp) throws Exception {
        try {
            // TODO : deptId从session中获取
            scoreManager.exportScore(idCard, name, empNumber, examId, examScore, pass, errorCount, 2, resp);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

}
