package com.ghtn.service.impl;

import com.ghtn.dao.DepartmentDao;
import com.ghtn.dao.SubjectAnswerDao;
import com.ghtn.dao.SubjectDao;
import com.ghtn.model.Subject;
import com.ghtn.model.SubjectAnswer;
import com.ghtn.service.SubjectManager;
import com.ghtn.util.DateUtil;
import com.ghtn.util.StringUtil;
import com.ghtn.vo.SubjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lihe on 14-6-23.
 */
@Service("subjectManager")
public class SubjectManagerImpl extends GenericManagerImpl<Subject, Integer> implements SubjectManager {

    private SubjectDao subjectDao;

    @Autowired
    public SubjectManagerImpl(SubjectDao subjectDao) {
        super(subjectDao);
        this.subjectDao = subjectDao;
    }

    private DepartmentDao departmentDao;

    @Resource
    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    private SubjectAnswerDao subjectAnswerDao;

    @Resource
    public void setSubjectAnswerDao(SubjectAnswerDao subjectAnswerDao) {
        this.subjectAnswerDao = subjectAnswerDao;
    }

    @Override
    public List<SubjectVO> listSubjectByPage(int start, int limit, int type) throws Exception {
        List<Subject> list;
        List<SubjectVO> returnList = new ArrayList<>();

        if (type == -1) {
            // 返回所有类型的题目
            list = subjectDao.listSubjectByPage(start, limit);
        } else {
            // 返回指定类型的题目
            list = subjectDao.listSubjectByPage(start, limit, type);
        }

        if (list != null && list.size() > 0) {
            for (Subject subject : list) {
                SubjectVO vo = new SubjectVO();
                vo.setId(subject.getId());
                vo.setDeptId(subject.getDeptId());
                vo.setDeptName(departmentDao.getDeptName(subject.getDeptId()));
                vo.setDescription(subject.getDescription());
                vo.setType(subject.getType());
                if (subject.getType() == 0) {
                    vo.setTypeDesc("选择题");
                } else if (subject.getType() == 1) {
                    vo.setTypeDesc("判断题");
                } else {
                    throw new Exception("题目类型错误!!!");
                }
                vo.setCreator(subject.getCreator());
                vo.setMark(subject.getMark());
                vo.setCreatTime(DateUtil.dateToString(subject.getCreatTime()));

                returnList.add(vo);
            }
        }

        return returnList;
    }

    @Override
    public Long getCount(int type) {
        if (type == -1) {
            // 返回题目总量
            return subjectDao.getCount();
        } else {
            // 返回指定类型的题目总量
            return subjectDao.getCount(type);
        }
    }

    @Override
    public void addSubject(Subject subject, String paramStr) throws Exception {
        // TODO : 修改创建者为当前登录者
        subject.setCreator("李鹤");

        subject.setCreatTime(new Date());
        subject = subjectDao.save(subject);
        if (!StringUtil.isNullStr(paramStr)) {
            String[] answers = paramStr.split("@");
            for (int i = 0; i < answers.length; i++) {
                String answer = answers[i];
                String[] items = answer.split("#");

                SubjectAnswer subjectAnswer = new SubjectAnswer();
                subjectAnswer.setSubjectId(subject.getId());
                subjectAnswer.setDescription(items[0]);
                if (items[1].equals("true")) {
                    subjectAnswer.setCorrect(1);
                } else if (items[1].equals("false")) {
                    subjectAnswer.setCorrect(0);
                } else {
                    throw new Exception("答案类型错误!答案类型只能是\"正确答案\"或\"非正确答案\"!");
                }

                subjectAnswerDao.save(subjectAnswer);
            }
        }

    }
}
