package com.ghtn.service.impl;

import com.ghtn.dao.DepartmentDao;
import com.ghtn.dao.ExamDao;
import com.ghtn.dao.ExamEmpDao;
import com.ghtn.dao.PaperDao;
import com.ghtn.model.Employee;
import com.ghtn.model.Exam;
import com.ghtn.model.ExamEmp;
import com.ghtn.service.ExamManager;
import com.ghtn.util.DateUtil;
import com.ghtn.util.StringUtil;
import com.ghtn.vo.ExamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lihe on 14-7-3.
 */
@Service("examManager")
public class ExamManagerImpl extends GenericManagerImpl<Exam, Integer> implements ExamManager {

    private ExamDao examDao;

    @Autowired
    public ExamManagerImpl(ExamDao examDao) {
        super(examDao);
        this.examDao = examDao;
    }

    private DepartmentDao departmentDao;

    @Resource
    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    private PaperDao paperDao;

    @Resource
    public void setPaperDao(PaperDao paperDao) {
        this.paperDao = paperDao;
    }

    private ExamEmpDao examEmpDao;

    @Resource
    public void setExamEmpDao(ExamEmpDao examEmpDao) {
        this.examEmpDao = examEmpDao;
    }

    @Override
    public List<ExamVO> listExamByPage(int start, int limit, String startDate, String endDate, int deptId) throws ParseException {
        if (!StringUtil.isNullStr(startDate)) {
            startDate += " 00:00:00";
        }
        if (!StringUtil.isNullStr(endDate)) {
            endDate += " 23:59:59";
        }

        List<Exam> list = examDao.listExamByPage(start, limit, DateUtil.stringToDate(startDate), DateUtil.stringToDate(endDate), deptId);

        return transformToVO(list);
    }

    @Override
    public Long getCount(String startDate, String endDate, int deptId) throws ParseException {
        if (!StringUtil.isNullStr(startDate)) {
            startDate += " 00:00:00";
        }
        if (!StringUtil.isNullStr(endDate)) {
            endDate += " 23:59:59";
        }
        return examDao.getCount(DateUtil.stringToDate(startDate), DateUtil.stringToDate(endDate), deptId);
    }

    @Override
    public void addExam(String name, int paperId, String place, String examTime, String paramStr, HttpSession session) throws ParseException {
        if (StringUtil.isNullStr(paramStr)) {
            log.error("考试人员为空!");
            return;
        }

        Exam exam = new Exam();
        exam.setName(name);
        exam.setPaperId(paperId);
        exam.setPlace(place);
        exam.setExamTime(DateUtil.stringToDate(examTime));

        // TODO : 从session中获取
        exam.setCreator(0);
        exam.setCreatorName("李鹤");
        exam.setCreateTime(new Date());
        exam.setDeptId(2);

        exam = examDao.save(exam);

        String[] empIds = paramStr.split("#");
        for (int i = 0; i < empIds.length; i++) {
            ExamEmp examEmp = new ExamEmp();
            examEmp.setExamId(exam.getId());
            examEmp.setEmpId(Integer.parseInt(empIds[i]));

            examEmpDao.save(examEmp);
        }
    }

    @Override
    public void removeExam(Exam exam) {
        examDao.remove(exam);
        examEmpDao.removeExamEmp(exam.getId());
    }

    @Override
    public List<Employee> listEmp(int deptId, String idCard, String name) {
        return examDao.listEmp(deptId, idCard, name);
    }

    @Override
    public List<Employee> getEmps(int examId) {
        List<Object[]> list = examDao.getEmps(examId);
        if (list != null && list.size() > 0) {
            List<Employee> returnList = new ArrayList<>();
            for (Object[] o : list) {
                returnList.add((Employee) o[0]);
            }
            return returnList;
        }
        return null;
    }

    @Override
    public void updateExam(int id, String name, int paperId, String place, String examTime, String paramStr, HttpSession session) throws ParseException {
        Exam old = examDao.get(id);
        old.setName(name);
        old.setPaperId(paperId);
        old.setPlace(place);
        old.setExamTime(DateUtil.stringToDate(examTime));

        // TODO : 从session中获取
        old.setEditor(0);
        old.setEditorName("李鹤");
        old.setEditTime(new Date());
        old.setDeptId(2);

        old = examDao.save(old);

        examEmpDao.removeExamEmp(id);

        String[] empIds = paramStr.split("#");
        for (int i = 0; i < empIds.length; i++) {
            ExamEmp examEmp = new ExamEmp();
            examEmp.setExamId(id);
            examEmp.setEmpId(Integer.parseInt(empIds[i]));

            examEmpDao.save(examEmp);
        }
    }

    private List<ExamVO> transformToVO(List<Exam> list) {
        List<ExamVO> returnList = new ArrayList<>();

        if (list != null && list.size() > 0) {
            for (Exam exam : list) {
                ExamVO vo = new ExamVO();

                try {
                    vo.setId(exam.getId());
                    vo.setDeptId(exam.getDeptId());
                    vo.setDeptName(departmentDao.getDeptName(exam.getDeptId()));
                    vo.setPaperId(exam.getPaperId());
                    vo.setPaperName(paperDao.getPaperName(exam.getPaperId()));
                    vo.setName(exam.getName());
                    vo.setPlace(exam.getPlace());
                    vo.setExamTime(DateUtil.dateToString(exam.getExamTime()));
                    vo.setCreator(exam.getCreator());
                    vo.setCreatorName(exam.getCreatorName());
                    vo.setCreateTime(DateUtil.dateToString(exam.getCreateTime()));

                    if (exam.getEditor() != null) {
                        vo.setEditor(exam.getEditor());
                    }

                    if (!StringUtil.isNullStr(exam.getEditorName())) {
                        vo.setEditorName(exam.getEditorName());
                    }

                    if (exam.getEditTime() != null) {
                        vo.setEditTime(DateUtil.dateToString(exam.getEditTime()));
                    }

                } catch (Exception e) {
                    log.error("转换视图错误!跳过此次循环!");
                    e.printStackTrace();
                    continue;
                }

                returnList.add(vo);
            }
        }

        return returnList;
    }
}
