package com.ghtn.service.impl;

import com.ghtn.dao.PaperDao;
import com.ghtn.dao.PaperSubjectDao;
import com.ghtn.dao.SubjectDao;
import com.ghtn.model.Paper;
import com.ghtn.model.PaperSubject;
import com.ghtn.model.Subject;
import com.ghtn.service.PaperManager;
import com.ghtn.util.DateUtil;
import com.ghtn.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

    @Override
    public void addPaper(Paper paper, String paramStr) {
        paper.setCreator("李鹤");
        paper.setCreateTime(new Date());

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
    public void genPaper(Paper paper, String startDate, String endDate, int choiceSubNum, int judgeSubNum) throws ParseException {
        if (choiceSubNum < 0 || judgeSubNum < 0 || (choiceSubNum == 0 && judgeSubNum == 0)) {
            log.error("题目数量错误!!选择题数量 : " + choiceSubNum + ", 判断题数量 : " + judgeSubNum);
            return;
        }

        paper.setCreator("李鹤");
        paper.setCreateTime(new Date());
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
        List<Subject> choiceSubList = subjectDao.listSubjectByDate(DateUtil.stringToDate(startDate), DateUtil.stringToDate(endDate), 0);
        // 判断题列表
        List<Subject> judgeSubList = subjectDao.listSubjectByDate(DateUtil.stringToDate(startDate), DateUtil.stringToDate(endDate), 1);

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
}
