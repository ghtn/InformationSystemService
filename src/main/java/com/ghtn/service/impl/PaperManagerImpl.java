package com.ghtn.service.impl;

import com.ghtn.dao.*;
import com.ghtn.model.Paper;
import com.ghtn.model.PaperSubject;
import com.ghtn.model.Subject;
import com.ghtn.model.SubjectAnswer;
import com.ghtn.service.PaperManager;
import com.ghtn.service.SubjectManager;
import com.ghtn.util.ConstantUtil;
import com.ghtn.util.DateUtil;
import com.ghtn.util.FileUtil;
import com.ghtn.util.StringUtil;
import com.ghtn.vo.PaperVO;
import com.ghtn.vo.SubjectVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.ParseException;
import java.util.*;

/**
 * Created by lihe on 14-6-30.
 */
@Service("paperManager")
public class PaperManagerImpl extends GenericManagerImpl<Paper, Integer> implements PaperManager {

    private static Log log = LogFactory.getLog(PaperManagerImpl.class);


    private PaperDao paperDao;

    @Autowired
    public PaperManagerImpl(PaperDao paperDao) {
        super(paperDao);
        this.paperDao = paperDao;
    }

    private PaperSubjectDao paperSubjectDao;

    @Resource
    public void setPaperSubjectDao(PaperSubjectDao paperSubjectDao) {
        this.paperSubjectDao = paperSubjectDao;
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

    private DepartmentDao departmentDao;

    @Resource
    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    private SubjectManager subjectManager;

    @Resource
    public void setSubjectManager(SubjectManager subjectManager) {
        this.subjectManager = subjectManager;
    }

    @Override
    public void addPaper(Paper paper, String paramStr, HttpSession session) {
        // TODO : 修改创建者为登录者
        paper.setCreator(0);
        paper.setCreatorName("李鹤");
        paper.setCreateTime(new Date());
        paper.setStatus(0);
        paper.setDeptId(2);

        String[] subjectIds = null;

        if (!StringUtil.isNullStr(paramStr)) {
            subjectIds = paramStr.split("#");
            paper.setSubNum(subjectIds.length);
        }

        paper = paperDao.save(paper);

        if (subjectIds != null && subjectIds.length > 0) {
            for (int i = 0; i < subjectIds.length; i++) {
                PaperSubject paperSubject = new PaperSubject();

                paperSubject.setPaperId(paper.getId());
                paperSubject.setSubjectId(Integer.parseInt(subjectIds[i]));

                paperSubjectDao.save(paperSubject);
            }
        }

    }

    @Override
    public void genPaper(Paper paper, String startDate, String endDate, int choiceSubNum, int judgeSubNum, HttpSession session) throws ParseException {
        if (choiceSubNum < 0 || judgeSubNum < 0 || (choiceSubNum == 0 && judgeSubNum == 0)) {
            log.error("题目数量错误!!选择题数量 : " + choiceSubNum + ", 判断题数量 : " + judgeSubNum);
            return;
        }
        // TODO : 修改创建者
        int deptId = 2;
        paper.setCreator(0);
        paper.setCreatorName("李鹤");
        paper.setCreateTime(new Date());
        paper.setStatus(0);
        paper.setDeptId(deptId);
        // 保存paper, 获取paperId
        paper = paperDao.save(paper);

        // 试卷的题目总数量
        int subjectCount = 0;

        // 根据时间段, 分别获取题目列表
        if (!StringUtil.isNullStr(startDate)) {
            startDate += " 00:00:00";
        }
        if (!StringUtil.isNullStr(endDate)) {
            endDate += " 23:59:59";
        }
        // 选择题列表
        List<Subject> choiceSubList = subjectDao.listSubjectByDate(DateUtil.stringToDate(startDate), DateUtil.stringToDate(endDate), 0, deptId);
        // 判断题列表
        List<Subject> judgeSubList = subjectDao.listSubjectByDate(DateUtil.stringToDate(startDate), DateUtil.stringToDate(endDate), 1, deptId);

        // 随机选择题
        if (choiceSubList != null && choiceSubList.size() > 0) {
            subjectCount += randomAndSaveSubjects(choiceSubList, choiceSubNum, paper).size();
        }

        // 随机判断题
        if (judgeSubList != null && judgeSubList.size() > 0) {
            subjectCount += randomAndSaveSubjects(judgeSubList, judgeSubNum, paper).size();
        }

        // 更新题目数量
        paper.setSubNum(subjectCount);

        paperDao.save(paper);
    }

    @Override
    public void importPaper(HttpSession session) throws Exception {
        String fileName = ConstantUtil.UPLOAD_TEMP_PATH + "/" + session.getAttribute("paperFileName");
        int deptId = 2;
        List<String[]> list = FileUtil.ExcelReaderForList(fileName, 2);

        // list.size > 3, 确保有试题信息
        if (list != null && list.size() > 3) {
            // 试题信息list
            List<String[]> subjectInfoList = list.subList(2, list.size());
            if (subjectInfoList == null || subjectInfoList.size() < 1) {
                log.error("没有试题信息!");
                return;
            }

            // list的第一行为试卷信息
            String[] paperInfo = list.get(0);
            Paper paper = new Paper();
            paper.setName(paperInfo[0].trim());

            Double fullScore = Double.parseDouble(paperInfo[1].trim());
            paper.setFullScore(fullScore.intValue());

            Double passScore = Double.parseDouble(paperInfo[2].trim());
            paper.setPassScore(passScore.intValue());

            Double examTime = Double.parseDouble(paperInfo[3].trim());
            paper.setExamTime(examTime.intValue());

            paper.setDeptId(deptId);

            // TODO : 修改创建者
            paper.setCreator(0);
            paper.setCreatorName("李鹤");
            paper.setCreateTime(new Date());
            paper.setStatus(0);

            paper = paperDao.save(paper);

            int subjectCount = 0;

            for (String[] strArray : subjectInfoList) {
                String type = strArray[0].trim();
                if (!type.equals("单选题") && !type.equals("多选题") && !type.equals("判断题")) {
                    log.error("题目类型错误: 必须为\"单选题\"或\"多选题\"或\"判断题\", 将跳过此次循环, 继续导入下一个题目!");
                    continue;
                }

                int mark;
                try {
                    Double d = Double.parseDouble(strArray[1].trim());
                    mark = d.intValue();
                } catch (NumberFormatException e) {
                    log.error("分值类型错误, 必须为正整数, 将跳过此次循环, 继续导入下一个题目! 分值为: " + strArray[1]);
                    continue;
                }

                String desc = strArray[2].trim();
                if (StringUtil.isNullStr(desc)) {
                    log.error("题目描述为空, 将跳过此次循环, 继续导入下一个题目!");
                    continue;
                }

                Subject subject = new Subject();
                subject.setDeptId(deptId);
                subject.setDescription(desc);
                subject.setMark(mark);

                // TODO : 修改创建者为登录者
                subject.setCreator(0);
                subject.setCreatorName("李鹤");
                subject.setCreateTime(new Date());

                if (type.equals("单选题") || type.equals("多选题")) {
                    if (type.equals("单选题")) {
                        subject.setType(0);
                    } else {
                        subject.setType(2);
                    }

                    subject.setCorrect(null);
                    subject = subjectDao.save(subject);

                    for (int i = 3; i < strArray.length; i++) {
                        String answer = strArray[i];
                        if (!StringUtil.isNullStr(answer)) {
                            SubjectAnswer subjectAnswer = new SubjectAnswer();
                            subjectAnswer.setSubjectId(subject.getId());
                            subjectAnswer.setMark(answer.split("#")[0]);

                            answer = answer.split("#")[1];

                            if (answer.contains("$")) {
                                // 如果是正确答案
                                String[] s = answer.split("\\$");
                                subjectAnswer.setDescription(s[0]);

                                if (s[1].trim().equals("正确")) {
                                    subjectAnswer.setCorrect(1);
                                } else {
                                    subjectAnswer.setCorrect(0);
                                }
                            } else {
                                subjectAnswer.setDescription(answer);
                                subjectAnswer.setCorrect(0);
                            }

                            subjectAnswerDao.save(subjectAnswer);
                        }
                    }

                }

                if (type.equals("判断题")) {
                    subject.setType(1);
                    if (strArray[3].trim().equals("正确")) {
                        subject.setCorrect(1);
                    } else {
                        subject.setCorrect(0);
                    }
                    subject = subjectDao.save(subject);
                }

                // 如果程序可以运行到这里, subject一定进行过保存操作
                // 不满足条件的subject已经被前边的判断条件过滤掉了
                subjectCount++;

                PaperSubject paperSubject = new PaperSubject();
                paperSubject.setPaperId(paper.getId());
                paperSubject.setSubjectId(subject.getId());

                paperSubjectDao.save(paperSubject);
            }

            // 更新题目数量
            paper.setSubNum(subjectCount);
            paperDao.save(paper);
        } else {
            log.error("从excel文件读取的list为空!!");
        }

        // 删除临时文件
        FileUtil.deleteFile(new File(fileName));
    }

    @Override
    public List<PaperVO> listPaperByPage(int start, int limit, String startDate, String endDate, int deptId, int status) throws ParseException {
        if (!StringUtil.isNullStr(startDate)) {
            startDate += " 00:00:00";
        }
        if (!StringUtil.isNullStr(endDate)) {
            endDate += " 23:59:59";
        }

        List<Paper> list = paperDao.listPaperByPage(start, limit, DateUtil.stringToDate(startDate), DateUtil.stringToDate(endDate), deptId, status);

        return transformToVO(list);
    }

    @Override
    public Long getCount(String startDate, String endDate, int deptId, int status) throws ParseException {
        if (!StringUtil.isNullStr(startDate)) {
            startDate += " 00:00:00";
        }
        if (!StringUtil.isNullStr(endDate)) {
            endDate += " 23:59:59";
        }
        return paperDao.getCount(DateUtil.stringToDate(startDate), DateUtil.stringToDate(endDate), deptId, status);
    }

    @Override
    public void publishPaper(int id) {
        Paper paper = paperDao.get(id);
        if (paper != null) {
            paper.setStatus(1);
            paperDao.save(paper);
        } else {
            log.error("试卷为空!!");
        }
    }

    @Override
    public void revokePaper(int id) {
        Paper paper = paperDao.get(id);
        if (paper != null) {
            paper.setStatus(0);
            paperDao.save(paper);
        } else {
            log.error("试卷为空!!");
        }
    }

    @Override
    public void removePaper(int id) {
        paperDao.remove(id);
        paperSubjectDao.removePaperSubject(id);
    }

    @Override
    public List<SubjectVO> getSubjects(int paperId) throws Exception {
        List<Subject> list = paperDao.getSubjects(paperId);
        return subjectManager.transformToVO(list);
    }

    @Override
    public int updatePaperSubject(int id, String paramStr) throws Exception {
        // 删除以前的试题
        paperSubjectDao.removePaperSubject(id);

        // 更新题目数量
        Paper paper = paperDao.get(id);

        // TODO : 修改者为登录者
        paper.setEditor(0);
        paper.setEditorName("李鹤");
        paper.setEditTime(new Date());

        String[] subjectIds;
        if (!StringUtil.isNullStr(paramStr)) {
            subjectIds = paramStr.split("#");
            paper.setSubNum(subjectIds.length);
        } else {
            paper.setSubNum(0);
            throw new Exception("更新试卷出错, 题目数量为0!paramStr = " + paramStr);
        }
        paper = paperDao.save(paper);

        if (subjectIds != null && subjectIds.length > 0) {
            for (int i = 0; i < subjectIds.length; i++) {
                PaperSubject paperSubject = new PaperSubject();

                paperSubject.setPaperId(id);
                paperSubject.setSubjectId(Integer.parseInt(subjectIds[i]));

                paperSubjectDao.save(paperSubject);
            }
        }

        return paper.getSubNum();
    }

    @Override
    public void updatePaper(Paper paper) {
        Paper old = paperDao.get(paper.getId());
        paper.setCreator(old.getCreator());
        paper.setCreatorName(old.getCreatorName());
        paper.setCreateTime(old.getCreateTime());
        paper.setDeptId(old.getDeptId());

        // TODO : 修改者为登录者
        paper.setEditor(0);
        paper.setEditorName("李鹤");
        paper.setEditTime(new Date());
        paper.setStatus(0);

        paperDao.save(paper);
    }

    @Override
    public List<PaperVO> listPaper(int deptId, int status) {
        return transformToVO(paperDao.listPaper(deptId, status));
    }

    @Override
    public Map<String, Object> loadPaper(int paperId) throws Exception {
        Map<String, Object> returnMap = new HashMap<>();

        List<SubjectVO> returnList = new ArrayList<>();

        Paper paper = paperDao.get(paperId);
        returnMap.put("paperName", paper.getName());
        returnMap.put("fullScore", paper.getFullScore());
        returnMap.put("passScore", paper.getPassScore());
        returnMap.put("examTime", paper.getExamTime());
        returnMap.put("deptName", departmentDao.getDeptName(paper.getDeptId()));

        List<Subject> subjectList = paperDao.getSubjects(paperId);

        if (subjectList != null && subjectList.size() > 0) {
            for (Subject subject : subjectList) {
                SubjectVO vo = subjectManager.transformToVO(subject);
                if (subject.getType() == 0 || subject.getType() == 2) {
                    // 选择题
                    List<SubjectAnswer> answerList = subjectAnswerDao.getAnswers(subject);
                    vo.setAnswers(answerList);
                }

                returnList.add(vo);
            }
        }

        returnMap.put("subjectList", returnList);
        return returnMap;
    }

    /**
     * 随机并保存题库
     *
     * @param subjectList 题库
     * @param subjectNum  随机出来的题目数量
     * @param paper       试卷
     */
    private List<Subject> randomAndSaveSubjects(List<Subject> subjectList, int subjectNum, Paper paper) {
        List<Integer> randomList = getRandomList(subjectList.size(), subjectNum);
        List<Subject> returnList = new ArrayList<>();
        for (int index : randomList) {
            Subject subject = subjectList.get(index);

            PaperSubject paperSubject = new PaperSubject();
            paperSubject.setPaperId(paper.getId());
            paperSubject.setSubjectId(subject.getId());

            paperSubjectDao.save(paperSubject);

            returnList.add(subject);
        }

        return returnList;
    }

    /**
     * 生成随机数list
     *
     * @param range    随机数范围 0 ~ range-1
     * @param listSize list大小
     * @return 随机数list
     */
    private List<Integer> getRandomList(int range, int listSize) {
        Random random = new Random();

        List<Integer> randomList = new ArrayList<>(); //选择题随机数list

        if (listSize > range) {
            listSize = range;
        }

        while (true) {
            if (randomList.size() == listSize) {
                break;
            }
            int num = random.nextInt(range);
            if (!randomList.contains(num)) {
                randomList.add(num);
            }
        }

        return randomList;
    }

    private List<PaperVO> transformToVO(List<Paper> list) {
        List<PaperVO> returnList = new ArrayList<>();

        if (list != null && list.size() > 0) {
            for (Paper paper : list) {
                PaperVO vo = new PaperVO();

                try {
                    vo.setId(paper.getId());
                    vo.setName(paper.getName());
                    vo.setFullScore(paper.getFullScore());
                    vo.setPassScore(paper.getPassScore());
                    vo.setDeptId(paper.getDeptId());
                    vo.setDeptName(departmentDao.getDeptName(paper.getDeptId()));
                    vo.setExamTime(paper.getExamTime());
                    vo.setCreator(paper.getCreator());
                    vo.setCreatorName(paper.getCreatorName());
                    vo.setCreateTime(DateUtil.dateToString(paper.getCreateTime()));

                    if (paper.getEditor() != null) {
                        vo.setEditor(paper.getEditor());
                    }

                    if (!StringUtil.isNullStr(paper.getEditorName())) {
                        vo.setEditorName(paper.getEditorName());
                    }

                    if (paper.getEditTime() != null) {
                        vo.setEditTime(DateUtil.dateToString(paper.getEditTime()));
                    }


                    vo.setSubNum(paper.getSubNum());

                    vo.setStatus(paper.getStatus());
                    if (paper.getStatus() == 0) {
                        vo.setStatusDesc("未发布");
                    } else if (paper.getStatus() == 1) {
                        vo.setStatusDesc("已发布");
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
