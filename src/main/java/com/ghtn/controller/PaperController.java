package com.ghtn.controller;

import com.ghtn.model.Paper;
import com.ghtn.service.PaperManager;
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
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
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

    @RequestMapping("/uploadFile")
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestParam("file") CommonsMultipartFile file, HttpSession session)
            throws Exception {
        String fileName = FileUtil.uploadFile(file);
        session.setAttribute("paperFileName", fileName);
        return operationSuccess();
    }

    @RequestMapping("/import")
    @ResponseBody
    public Map<String, Object> importPaper(int deptId, HttpSession session) throws Exception {
        paperManager.importPaper(deptId, ConstantUtil.UPLOAD_TEMP_PATH + "/" + session.getAttribute("paperFileName"));
        return operationSuccess();
    }

    @RequestMapping("/downloadTemplate")
    @ResponseBody
    public String downloadTemplate(String fileName, HttpServletResponse response) throws Exception {
        return FileUtil.downloadFile(fileName, response);
    }

    @RequestMapping("/listPaperByPage")
    @ResponseBody
    public Map<String, Object> listPaperByPage(int start, int limit, String startDate, String endDate, int deptId, int status) throws ParseException {
        Map<String, Object> map = new HashMap<>();
        map.put("total", paperManager.getCount(startDate, endDate, deptId, status));
        map.put("items", paperManager.listPaperByPage(start, limit, startDate, endDate, deptId, status));

        return map;
    }

    @RequestMapping("/publish")
    @ResponseBody
    public Map<String, Object> publishPaper(int id) throws Exception {
        paperManager.publishPaper(id);
        return operationSuccess();
    }

    @RequestMapping("/revoke")
    @ResponseBody
    public Map<String, Object> revokePaper(int id) throws Exception {
        paperManager.revokePaper(id);
        return operationSuccess();
    }

    @RequestMapping("/remove")
    @ResponseBody
    public Map<String, Object> removePaper(int id) throws Exception {
        paperManager.removePaper(id);
        return operationSuccess();
    }

    @RequestMapping("/getSubjects")
    @ResponseBody
    public List<SubjectVO> getSubjects(int paperId) throws Exception {
        return paperManager.getSubjects(paperId);
    }

    @RequestMapping("/updateSubject")
    @ResponseBody
    public Map<String, Object> updatePaperSubject(int id, String paramStr) throws Exception {
        int subNum = paperManager.updatePaperSubject(id, paramStr);
        Map<String, Object> map = operationSuccess();
        map.put("subNum", subNum);
        return map;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map<String, Object> updatePaper(Paper paper) throws Exception {
        paperManager.updatePaper(paper);
        return operationSuccess();
    }

}
