package com.ghtn.service.impl;

import com.ghtn.Exception.ExistScoreException;
import com.ghtn.Exception.NullParamStrException;
import com.ghtn.dao.*;
import com.ghtn.model.*;
import com.ghtn.service.ExamManager;
import com.ghtn.service.PaperManager;
import com.ghtn.util.DateUtil;
import com.ghtn.util.StringUtil;
import com.ghtn.vo.EmpVO;
import com.ghtn.vo.ExamVO;
import com.ghtn.vo.SubjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private PaperManager paperManager;

    @Resource
    public void setPaperManager(PaperManager paperManager) {
        this.paperManager = paperManager;
    }

    private SubjectDao subjectDao;

    @Resource
    public void setSubjectDao(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    private SubjectAnswerDao subjectAnswerDao;

    @Resource
    public void setSubjectAnswerDao(SubjectAnswerDao subjectAnswerDao) {
        this.subjectAnswerDao = subjectAnswerDao;
    }

    private EmployeeDao employeeDao;

    @Resource
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    private ScoreDao scoreDao;

    @Resource
    public void setScoreDao(ScoreDao scoreDao) {
        this.scoreDao = scoreDao;
    }

    private ErrorSubjectDao errorSubjectDao;

    @Resource
    public void setErrorSubjectDao(ErrorSubjectDao errorSubjectDao) {
        this.errorSubjectDao = errorSubjectDao;
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
        return examDao.listEmp(deptId, idCard.toUpperCase(), name);
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

    @Override
    public EmpVO login(String idCard) {
        if (StringUtil.isNullStr(idCard)) {
            log.error("身份证号为空!idCard = " + idCard);
            return null;
        }

        // 身份证号正则表达式
        String idCardReg = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
        Pattern pattern = Pattern.compile(idCardReg);
        Matcher matcher = pattern.matcher(idCard);
        if (!matcher.matches()) {
            log.error("身份证号格式不正确!idCard = " + idCard);
            return null;
        }

        idCard = idCard.toUpperCase();

        Employee employee = examDao.login(idCard);
        if (employee == null) {
            log.error("没有获取到相应的员工信息!idCard = " + idCard);
            return null;
        }

        EmpVO vo = new EmpVO();
        vo.setName(employee.getName());
        vo.setIdCard(employee.getCard());
        vo.setDeptId(employee.getDeptId());
        vo.setDeptName(employee.getDeptName());

        return vo;
    }

    @Override
    public boolean checkExamEmp(int examId, String idCard) {
        List<Employee> empList = getEmps(examId);
        if (empList != null && empList.size() > 0) {
            for (Employee employee : empList) {
                if (employee.getCard().trim().toUpperCase().equals(idCard.trim().toUpperCase())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<ExamVO> listExam(int deptId) {
        if (deptId <= 0) {
            log.error("部门id错误! deptId = " + deptId);
            return null;
        }
        return transformToVO(examDao.listExam(deptId));
    }

    @Override
    public List<SubjectVO> loadPaper(int examId) throws Exception {
        return paperManager.loadPaper(get(examId).getPaperId());
    }

    @Override
    public Map<String, Object> finishExam(String paramStr) throws Exception {
        Map<String, Object> map = new HashMap<>();

        if (StringUtil.isNullStr(paramStr)) {
            String msg = "答题信息为空! paramStr = " + paramStr;
            log.error(msg);
            map.put("msg", msg);
            throw new NullParamStrException();
        }

        int examScore = 0; // 考试分数
        List<Subject> errorList = new ArrayList<>(); // 错题list
        String[] items = paramStr.split("@");
        int examId = Integer.parseInt(items[0].split("#")[0]);
        String idCard = items[0].split("#")[1].toUpperCase();

        if (scoreDao.getScore(examId, idCard) != null) {
            String msg = "已经存在该人的考试记录! examId = " + examId + ", idCard = " + idCard;
            log.error(msg);
            map.put("msg", msg);
            throw new ExistScoreException();
        }

        // 评判试题
        for (int i = 0; i < items.length; i++) {
            String[] args = items[i].split("#");
            int subjectId = Integer.parseInt(args[2]);
            if (args.length < 4 || StringUtil.isNullStr(args[3])) {
                log.warn("这道题没有作答!跳过此次循环, 评判下一道试题! subjectId = " + subjectId);
                continue;
            }

            Subject subject = subjectDao.get(subjectId);
            if (subject.getType() == 0) {
                // 单选题
                List<SubjectAnswer> correctAnswers = subjectAnswerDao.getCorrectAnswer(subject);
                if (correctAnswers == null || correctAnswers.size() == 0) {
                    log.error("这道题没有正确答案!跳过此次循环, 评判下一道试题! subjectId = " + subject.getId());
                    continue;
                }
                if (correctAnswers.size() > 1) {
                    log.error("单选题的正确答案不止一个!跳过此次循环, 评判下一道试题! subjectId = " + subject.getId());
                    continue;
                }

                if (Integer.parseInt(args[3]) == correctAnswers.get(0).getId()) {
                    // 如果答对
                    examScore += subject.getMark();
                } else {
                    // 如果答错
                    errorList.add(subject);
                }
            }

            if (subject.getType() == 1) {
                // 判断题
                if (subject.getCorrect() == null) {
                    log.error("判断题的答案为空!跳过此次循环, 评判下一道试题! subjectId = " + subject.getId());
                    continue;
                }
                int answer = args[3].equals("true") ? 1 : 0;
                if (answer == subject.getCorrect()) {
                    // 答对
                    examScore += subject.getMark();
                } else {
                    // 答错
                    errorList.add(subject);
                }
            }

            if (subject.getType() == 2) {
                // 多选题
                List<SubjectAnswer> correctAnswers = subjectAnswerDao.getCorrectAnswer(subject);
                if (correctAnswers == null || correctAnswers.size() == 0) {
                    log.error("这道题没有正确答案!跳过此次循环, 评判下一道试题! subjectId = " + subject.getId());
                    continue;
                }
                if (correctAnswers.size() <= 1) {
                    log.error("多选题的正确答案不能只有一个!跳过此次循环, 评判下一道试题! subjectId = " + subject.getId());
                    continue;
                }

                List<Integer> answerList = new ArrayList<>();
                for (int j = 3; j < args.length; j++) {
                    answerList.add(Integer.parseInt(args[j]));
                }

                boolean correct = true;

                for (SubjectAnswer subjectAnswer : correctAnswers) {
                    if (answerList.contains(subjectAnswer.getId())) {
                        answerList.remove(new Integer(subjectAnswer.getId()));

                    } else {
                        correct = false;
                        break;
                    }
                }
                if (correct && answerList.size() == 0) {
                    // 答对
                    examScore += subject.getMark();
                } else {
                    // 答错
                    errorList.add(subject);
                }
            }
        }

        // 把考试结果插入成绩表
        Employee employee = employeeDao.getEmployeeByIdCard(idCard);
        Exam exam = examDao.get(examId);
        Paper paper = paperDao.get(exam.getPaperId());
        Score score = new Score();
        score.setIdCard(idCard);
        score.setName(employee.getName());
        score.setDeptId(employee.getDeptId());
        score.setDeptName(employee.getDeptName());
        score.setEmpNumber(employee.getEmpNumber());
        score.setExamId(examId);
        score.setExamName(exam.getName());
        score.setFullScore(paper.getFullScore());
        score.setPassScore(paper.getPassScore());
        score.setExamScore(examScore);
        if (examScore >= paper.getPassScore()) {
            score.setPass(1);
        } else {
            score.setPass(0);
        }
        score.setErrorCount(errorList.size());
        scoreDao.save(score);


        // 把错误的题目插入错题表
        if (errorList.size() > 0) {
            for (Subject subject : errorList) {
                ErrorSubject errorSubject = new ErrorSubject();
                errorSubject.setExamId(examId);
                errorSubject.setIdCard(idCard);
                errorSubject.setEmpNumber(employee.getEmpNumber());
                errorSubject.setSubjectId(subject.getId());

                errorSubjectDao.save(errorSubject);
            }
        }

        map.put("examScore", examScore);
        map.put("errorList", errorList);
        map.put("errorCount", errorList.size());
        return map;
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
