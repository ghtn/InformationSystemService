package com.ghtn.controller;

import com.ghtn.model.Subject;
import com.ghtn.service.SubjectManager;
import com.ghtn.util.ConstantUtil;
import com.ghtn.util.FileUtil;
import com.ghtn.vo.SubjectVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
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

    @RequestMapping("/downloadTemplate")
    @ResponseBody
    public String downloadTemplate(String fileName, HttpServletResponse response) throws Exception {
        return FileUtil.downloadFile(fileName, response);
    }

    @RequestMapping("/uploadFile")
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestParam("file") CommonsMultipartFile file, HttpSession session)
            throws Exception {
        String fileName = FileUtil.uploadFile(file);
        session.setAttribute("fileName", fileName);
        return operationSuccess();
    }


    @RequestMapping("/importSubjects")
    @ResponseBody
    public Map<String, Object> importSubjects(int deptId, HttpSession session) throws Exception {
        subjectManager.importSubjects(deptId, ConstantUtil.UPLOAD_TEMP_PATH + "/" + session.getAttribute("fileName"));
        return operationSuccess();
    }

    @RequestMapping("/listSubjectByDate")
    @ResponseBody
    public List<SubjectVO> listSubjectByDate(String startDate, String endDate) throws Exception {
        return subjectManager.listSubjectByDate(startDate, endDate);
    }
}
