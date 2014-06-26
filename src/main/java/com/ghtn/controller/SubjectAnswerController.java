package com.ghtn.controller;

import com.ghtn.model.Subject;
import com.ghtn.model.SubjectAnswer;
import com.ghtn.service.SubjectAnswerManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihe on 14-6-26.
 */
@Controller
@RequestMapping("/subjectAnswer")
public class SubjectAnswerController extends BaseController {

    private SubjectAnswerManager subjectAnswerManager;

    @Resource
    public void setSubjectAnswerManager(SubjectAnswerManager subjectAnswerManager) {
        this.subjectAnswerManager = subjectAnswerManager;
    }

    @RequestMapping("/getAnswers")
    @ResponseBody
    public List<SubjectAnswer> getAnswers(Subject subject) {
        return subjectAnswerManager.getAnswers(subject);
    }
}
