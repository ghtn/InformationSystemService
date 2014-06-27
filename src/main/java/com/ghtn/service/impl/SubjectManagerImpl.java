package com.ghtn.service.impl;

import com.ghtn.dao.DepartmentDao;
import com.ghtn.dao.SubjectAnswerDao;
import com.ghtn.dao.SubjectDao;
import com.ghtn.model.Subject;
import com.ghtn.model.SubjectAnswer;
import com.ghtn.service.SubjectManager;
import com.ghtn.util.DateUtil;
import com.ghtn.util.FileUtil;
import com.ghtn.util.StringUtil;
import com.ghtn.vo.SubjectVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lihe on 14-6-23.
 */
@Service("subjectManager")
public class SubjectManagerImpl extends GenericManagerImpl<Subject, Integer> implements SubjectManager {

    private static Log log = LogFactory.getLog(SubjectManagerImpl.class);

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
                vo.setCorrect(subject.getCorrect());

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

        // 如果是选择题, 把"是否正确"置为null, 此字段只用于判断题
        if (subject.getType() == 0) {
            subject.setCorrect(null);
        }
        subject = subjectDao.save(subject);

        // 如果是选择题, 并且答案信息不为空
        if (subject.getType() == 0 && !StringUtil.isNullStr(paramStr)) {
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

    @Override
    public void removeSubject(Subject subject) {
        subjectAnswerDao.removeSubjectAnswer(subject);
        subjectDao.remove(subject);
    }

    @Override
    public void updateSubject(Subject subject, String paramStr) throws Exception {
        // TODO : 修改创建者为当前登录者
        subject.setCreator("李鹤");

        subject.setCreatTime(new Date());

        // 如果是选择题, 把"是否正确"置为null, 此字段只用于判断题
        if (subject.getType() == 0) {
            subject.setCorrect(null);
        }
        subjectDao.save(subject);

        if (subject.getType() == 0 && !StringUtil.isNullStr(paramStr)) {
            String[] answers = paramStr.split("@");
            for (int i = 0; i < answers.length; i++) {
                String answer = answers[i];
                String[] items = answer.split("#");

                SubjectAnswer subjectAnswer = new SubjectAnswer();

                int editId = Integer.parseInt(items[0]);
                if (editId != 0) {
                    subjectAnswer.setId(editId);
                }

                subjectAnswer.setSubjectId(subject.getId());
                subjectAnswer.setDescription(items[1]);
                if (items[2].equals("true")) {
                    subjectAnswer.setCorrect(1);
                } else if (items[2].equals("false")) {
                    subjectAnswer.setCorrect(0);
                } else {
                    throw new Exception("答案类型错误!答案类型只能是\"正确答案\"或\"非正确答案\"!");
                }

                subjectAnswerDao.save(subjectAnswer);
            }
        }

        // 是判断题的情况下, 删除subject_answer表中对应的选择题答案
        if (subject.getType() == 1) {
            subjectAnswerDao.removeSubjectAnswer(subject);
        }
    }

    @Override
    public void importSubjects(int deptId, String fileName) throws Exception {
        List<String[]> list = FileUtil.ExcelReaderForList(fileName, 2);
        if (list != null && list.size() > 0) {
            for (String[] strArray : list) {
                String type = strArray[0].trim();
                if (!type.equals("选择题") && !type.equals("判断题")) {
                    log.error("题目类型错误: 必须为\"选择题\"或\"判断题\", 将跳过此次循环, 继续导入下一个题目!");
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
                subject.setCreator("李鹤");
                subject.setCreatTime(new Date());

                if (type.equals("选择题")) {
                    subject.setType(0);
                    subject.setCorrect(null);
                    subject = subjectDao.save(subject);

                    for (int i = 3; i < strArray.length; i++) {
                        String answer = strArray[i];
                        if (!StringUtil.isNullStr(answer)) {
                            SubjectAnswer subjectAnswer = new SubjectAnswer();
                            subjectAnswer.setSubjectId(subject.getId());

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
                    subjectDao.save(subject);
                }
            }
        } else {
            log.error("从excel文件读取的list为空!!");
        }

        // 删除临时文件
        FileUtil.deleteFile(new File(fileName));
    }
}
