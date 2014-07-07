package com.ghtn.controller;

import com.ghtn.model.Exam;
import com.ghtn.service.ExamManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihe on 14-7-3.
 */
@Controller
@RequestMapping("/exam")
public class ExamController extends BaseController {

    private ExamManager examManager;

    @Resource
    public void setExamManager(ExamManager examManager) {
        this.examManager = examManager;
    }

    @RequestMapping("/listExamByPage")
    @ResponseBody
    public Map<String, Object> listExamByPage(int start, int limit, String startDate, String endDate) throws ParseException {
        Map<String, Object> map = new HashMap<>();
        // TODO : deptId从session中获取
        map.put("total", examManager.getCount(startDate, endDate, 2));
        map.put("items", examManager.listExamByPage(start, limit, startDate, endDate, 2));

        return map;
    }

    @RequestMapping("/add")
    @ResponseBody
    public Map<String, Object> addExam(String name, int paperId, String place, String examTime, HttpSession session) throws ParseException {
        examManager.addExam(name, paperId, place, examTime, session);
        return operationSuccess();
    }

    @RequestMapping("/remove")
    @ResponseBody
    public Map<String, Object> removeExam(Exam exam) throws Exception {
        examManager.removeExam(exam);
        return operationSuccess();
    }
}
