package com.ghtn.controller;

import com.ghtn.Exception.ExistScoreException;
import com.ghtn.Exception.NullParamStrException;
import com.ghtn.model.Employee;
import com.ghtn.model.Exam;
import com.ghtn.service.ExamManager;
import com.ghtn.vo.EmpVO;
import com.ghtn.vo.ExamVO;
import com.ghtn.vo.SubjectVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
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
    public Map<String, Object> addExam(String name, int paperId, String place, String examTime, String paramStr, HttpSession session) throws ParseException {
        examManager.addExam(name, paperId, place, examTime, paramStr, session);
        return operationSuccess();
    }

    @RequestMapping("/remove")
    @ResponseBody
    public Map<String, Object> removeExam(Exam exam) throws Exception {
        examManager.removeExam(exam);
        return operationSuccess();
    }

    @RequestMapping("/listEmp")
    @ResponseBody
    public List<Employee> listEmp(String idCard, String name, HttpSession session) {
        // TODO :  从session中取得deptId
        return examManager.listEmp(2, idCard, name);
    }

    @RequestMapping("/getEmps")
    @ResponseBody
    public List<Employee> getEmps(int examId) {
        return examManager.getEmps(examId);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map<String, Object> updateExam(int id, String name, int paperId, String place, String examTime, String paramStr, HttpSession session) throws ParseException {
        examManager.updateExam(id, name, paperId, place, examTime, paramStr, session);
        return operationSuccess();
    }

    @RequestMapping("/listExam")
    @ResponseBody
    public Map<String, Object> listExam(HttpSession session) throws ParseException {
        Map<String, Object> map = new HashMap<>();
        EmpVO vo = (EmpVO) session.getAttribute("examPerson");
        if (vo != null) {
            List<ExamVO> list = examManager.listExam(vo.getDeptId());

            if (list != null && list.size() > 0) {
                map.put("code", 1);
                map.put("examList", list);
            } else {
                map.put("code", -1);
            }
        } else {
            map.put("code", -1);
        }

        return map;
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> login(String idCard, HttpSession session) throws ParseException {
        Map<String, Object> map = new HashMap<>();
        EmpVO vo = examManager.login(idCard);
        if (vo != null) {
            map.put("code", 1);
            map.put("name", vo.getName());
            map.put("idCard", vo.getIdCard());
            map.put("deptName", vo.getDeptName());
            session.setAttribute("examPerson", vo);
        } else {
            map.put("code", -1);
        }

        return map;
    }

    @RequestMapping("/checkExamEmp")
    @ResponseBody
    public Map<String, Object> checkExamEmp(int examId, String idCard) throws ParseException {
        Map<String, Object> map = new HashMap<>();
        if (examManager.checkExamEmp(examId, idCard)) {
            map.put("code", 1);
        } else {
            map.put("code", -1);
        }
        return map;
    }

    @RequestMapping("/loadPaper")
    @ResponseBody
    public Map<String, Object> loadPaper(int examId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<SubjectVO> list = examManager.loadPaper(examId);
        if (list != null && list.size() > 0) {
            map.put("code", 1);
            map.put("subjectList", list);
        } else {
            map.put("code", -1);
        }
        return map;
    }

    @RequestMapping("/finishExam")
    @ResponseBody
    public Map<String, Object> finishExam(String paramStr) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = examManager.finishExam(paramStr);
            map.put("code", 1);
        } catch (NullParamStrException e) {
            e.printStackTrace();
            map.put("code", -1);
            map.put("msg", "答题信息为空!");
        } catch (ExistScoreException e) {
            e.printStackTrace();
            map.put("code", -1);
            map.put("msg", "已经存在该人的考试记录!");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", -1);
            map.put("msg", "执行错误!");
        }

        return map;
    }
}
