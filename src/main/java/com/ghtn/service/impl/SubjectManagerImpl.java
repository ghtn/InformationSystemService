package com.ghtn.service.impl;

import com.ghtn.dao.DepartmentDao;
import com.ghtn.dao.SubjectAnswerDao;
import com.ghtn.dao.SubjectDao;
import com.ghtn.model.Subject;
import com.ghtn.model.SubjectAnswer;
import com.ghtn.service.SubjectManager;
import com.ghtn.util.ConstantUtil;
import com.ghtn.util.DateUtil;
import com.ghtn.util.FileUtil;
import com.ghtn.util.StringUtil;
import com.ghtn.vo.SubjectVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.ParseException;
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
    public List<SubjectVO> listSubjectByPage(int start, int limit, String startDate, String endDate, int type, int mark, int deptId) throws Exception {
        if (!StringUtil.isNullStr(startDate)) {
            startDate += " 00:00:00";
        }
        if (!StringUtil.isNullStr(endDate)) {
            endDate += " 23:59:59";
        }
        List<Subject> list = subjectDao.listSubjectByPage(start, limit, DateUtil.stringToDate(startDate), DateUtil.stringToDate(endDate), type, mark, deptId);
        return transformToVO(list);
    }

    @Override
    public Long getCount(String startDate, String endDate, int type, int mark, int deptId) throws ParseException {
        /*if (type == -1) {
            // 返回题目总量
            return subjectDao.getCount(deptId);
        } else {
            // 返回指定类型的题目总量
            return subjectDao.getCount(type, deptId);
        }*/
        if (!StringUtil.isNullStr(startDate)) {
            startDate += " 00:00:00";
        }
        if (!StringUtil.isNullStr(endDate)) {
            endDate += " 23:59:59";
        }
        return subjectDao.getCount(DateUtil.stringToDate(startDate), DateUtil.stringToDate(endDate), type, mark, deptId);
    }

    @Override
    public void addSubject(Subject subject, String paramStr, HttpSession session) throws Exception {
        // TODO : 修改创建者为当前登录者
        subject.setCreator(0);
        subject.setCreatorName("李鹤");
        subject.setCreateTime(new Date());
        subject.setDeptId(2);

        // 如果是选择题, 把"是否正确"置为null, 此字段只用于判断题
        if (subject.getType() == 0 || subject.getType() == 2) {
            subject.setCorrect(null);
        }
        subject = subjectDao.save(subject);

        // 如果是选择题, 并且答案信息不为空
        if ((subject.getType() == 0 || subject.getType() == 2) && !StringUtil.isNullStr(paramStr)) {
            String[] answers = paramStr.split("@");
            for (int i = 0; i < answers.length; i++) {
                String answer = answers[i];
                String[] items = answer.split("#");

                SubjectAnswer subjectAnswer = new SubjectAnswer();
                subjectAnswer.setSubjectId(subject.getId());
                subjectAnswer.setMark(items[0].toUpperCase());
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

    }

    @Override
    public void removeSubject(Subject subject) {
        subjectAnswerDao.removeSubjectAnswer(subject);
        subjectDao.remove(subject);
    }

    @Override
    public void updateSubject(Subject subject, String paramStr, HttpSession session) throws Exception {
        Subject old = subjectDao.get(subject.getId());
        subject.setCreator(old.getCreator());
        subject.setCreatorName(old.getCreatorName());
        subject.setCreateTime(old.getCreateTime());
        subject.setDeptId(old.getDeptId());

        // TODO : 修改者为当前登录者
        subject.setEditor(0);
        subject.setEditorName("李鹤");
        subject.setEditTime(new Date());

        // 如果是选择题, 把"是否正确"置为null, 此字段只用于判断题
        if (subject.getType() == 0 || subject.getType() == 2) {
            subject.setCorrect(null);
        }
        subjectDao.save(subject);

        subjectAnswerDao.removeSubjectAnswer(subject);

        if ((subject.getType() == 0 || subject.getType() == 2) && !StringUtil.isNullStr(paramStr)) {
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
                subjectAnswer.setMark(items[1].toUpperCase());
                subjectAnswer.setDescription(items[2]);
                if (items[3].equals("true")) {
                    subjectAnswer.setCorrect(1);
                } else if (items[3].equals("false")) {
                    subjectAnswer.setCorrect(0);
                } else {
                    throw new Exception("答案类型错误!答案类型只能是\"正确答案\"或\"非正确答案\"!");
                }

                subjectAnswerDao.save(subjectAnswer);
            }
        }

    }

    @Override
    public void importSubjects(HttpSession session) throws Exception {
        String fileName = ConstantUtil.UPLOAD_TEMP_PATH + "/" + session.getAttribute("fileName");
        List<String[]> list = FileUtil.ExcelReaderForList(fileName, 2);
        if (list != null && list.size() > 0) {
            for (String[] strArray : list) {
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
                subject.setDescription(desc);
                subject.setMark(mark);

                // TODO : 创建者为当前登录者
                subject.setDeptId(2);
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
                            subjectAnswer.setMark(answer.split("#")[0].toUpperCase());
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
                    subjectDao.save(subject);
                }
            }
        } else {
            log.error("从excel文件读取的list为空!!");
        }

        // 删除临时文件
        FileUtil.deleteFile(new File(fileName));
    }

    @Override
    public List<SubjectVO> listSubjectByDate(String startDate, String endDate, int deptId) throws Exception {
        if (!StringUtil.isNullStr(startDate)) {
            startDate += " 00:00:00";
        }
        if (!StringUtil.isNullStr(endDate)) {
            endDate += " 23:59:59";
        }
        List<Subject> list = subjectDao.listSubjectByDate(DateUtil.stringToDate(startDate), DateUtil.stringToDate(endDate), deptId);
        return transformToVO(list);
    }

    public List<SubjectVO> transformToVO(List<Subject> list) throws Exception {
        List<SubjectVO> returnList = new ArrayList<>();

        if (list != null && list.size() > 0) {
            for (Subject subject : list) {
                returnList.add(transformToVO(subject));
            }
        }

        return returnList;
    }

    @Override
    public SubjectVO transformToVO(Subject subject) throws Exception {
        SubjectVO vo = new SubjectVO();
        vo.setId(subject.getId());
        vo.setDeptId(subject.getDeptId());
        vo.setDeptName(departmentDao.getDeptName(subject.getDeptId()));
        vo.setDescription(subject.getDescription());
        vo.setType(subject.getType());
        if (subject.getType() == 0) {
            vo.setTypeDesc("单选题");
        } else if (subject.getType() == 1) {
            vo.setTypeDesc("判断题");
        } else if (subject.getType() == 2) {
            vo.setTypeDesc("多选题");
        } else {
            throw new Exception("题目类型错误!!!");
        }
        vo.setCreator(subject.getCreator());
        vo.setCreatorName(subject.getCreatorName());
        vo.setMark(subject.getMark());
        vo.setCreateTime(DateUtil.dateToString(subject.getCreateTime()));
        vo.setCorrect(subject.getCorrect());

        if (subject.getEditor() != null) {
            vo.setEditor(subject.getEditor());
        }

        if (!StringUtil.isNullStr(subject.getEditorName())) {
            vo.setEditorName(subject.getEditorName());
        }

        if (subject.getEditTime() != null) {
            vo.setEditTime(DateUtil.dateToString(subject.getEditTime()));
        }

        return vo;
    }


}
