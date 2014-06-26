package com.ghtn.controller;

import com.ghtn.model.Subject;
import com.ghtn.service.SubjectManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihe on 14-6-23.
 */
@Controller
@RequestMapping("/subject")
public class SubjectController extends BaseController {

    private SubjectManager subjectManager;

    @Resource
    public void setSubjectManager(SubjectManager subjectManager) {
        this.subjectManager = subjectManager;
    }

    @RequestMapping("/listSubjectByPage")
    @ResponseBody
    public Map<String, Object> listSubjectByPage(int start, int limit, int type) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("total", subjectManager.getCount(type));
        map.put("items", subjectManager.listSubjectByPage(start, limit, type));

        return map;
    }

    @RequestMapping("/add")
    @ResponseBody
    public Map<String, Object> addSubject(Subject subject, String paramStr) throws Exception {
        subjectManager.addSubject(subject, paramStr);
        return operationSuccess();
    }

    @RequestMapping("/remove")
    @ResponseBody
    public Map<String, Object> removeSubject(Subject subject) throws Exception {
        subjectManager.removeSubject(subject);
        return operationSuccess();
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map<String, Object> updateSubject(Subject subject, String paramStr) throws Exception {
        subjectManager.updateSubject(subject, paramStr);
        return operationSuccess();
    }
}
