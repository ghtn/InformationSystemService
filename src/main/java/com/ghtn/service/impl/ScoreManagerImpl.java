package com.ghtn.service.impl;

import com.ghtn.dao.ScoreDao;
import com.ghtn.model.Score;
import com.ghtn.service.ScoreManager;
import com.ghtn.util.ConstantUtil;
import com.ghtn.util.FileUtil;
import com.ghtn.vo.ScoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihe on 14-7-10.
 */
@Service("scoreManager")
public class ScoreManagerImpl extends GenericManagerImpl<Score, Integer> implements ScoreManager {

    private ScoreDao scoreDao;

    @Autowired
    public ScoreManagerImpl(ScoreDao scoreDao) {
        super(scoreDao);
        this.scoreDao = scoreDao;
    }

    @Override
    public List<ScoreVO> listScoreByPage(int start, int limit, String idCard, String name, String empNumber, int examId, int examScore, int pass, int errorCount, int deptId) {
        return transFormToVO(scoreDao.listScoreByPage(start, limit, idCard, name, empNumber, examId, examScore, pass, errorCount, deptId));
    }

    @Override
    public Long getCount(String idCard, String name, String empNumber, int examId, int examScore, int pass, int errorCount, int deptId) {
        return scoreDao.getCount(idCard, name, empNumber, examId, examScore, pass, errorCount, deptId);
    }

    @Override
    public void exportScore(String idCard, String name, String empNumber, int examId, int examScore, int pass, int errorCount, int deptId, HttpServletResponse resp) throws IOException {
        List<Score> list = scoreDao.listScore(idCard, name, empNumber, examId, examScore, pass, errorCount, deptId);
        if (list != null && list.size() > 0) {
            List<String[]> dataList = new ArrayList<>();
            String[] title = {"身份证号", "姓名", "部门", "员工号", "考试名称", "满分", "及格分", "考试分数", "及格"};
            dataList.add(title);
            for (Score score : list) {
                ScoreVO vo = transFormToVO(score);
                String[] data = {vo.getIdCard(), vo.getName(), vo.getDeptName(), vo.getEmpNumber(),
                        vo.getExamName(), vo.getFullScore() + "", vo.getPassScore() + "",
                        vo.getExamScore() + "", vo.getPassDesc()};

                dataList.add(data);
            }

            String strExcelFile = FileUtil.exportExcel(dataList);
            File file = new File(ConstantUtil.UPLOAD_TEMP_PATH + "/"
                    + strExcelFile);

            resp.reset();
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("content-disposition", "attachment; filename="
                    + strExcelFile);

            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                bis = new BufferedInputStream(new FileInputStream(file));
                bos = new BufferedOutputStream(resp.getOutputStream());
                byte[] buff = new byte[2048];
                int bytesread;
                while (-1 != (bytesread = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesread);
                }
            } catch (final IOException e) {
                System.out.println("出现ioexception." + e);
            } finally {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
                FileUtil.deleteFile(file);
            }
        } else {
            log.error("导出的结果集为空!");
        }
    }

    private ScoreVO transFormToVO(Score score) {
        ScoreVO vo = new ScoreVO();
        vo.setId(score.getId());
        vo.setIdCard(score.getIdCard());
        vo.setName(score.getName());
        vo.setDeptId(score.getDeptId());
        vo.setDeptName(score.getDeptName());
        vo.setEmpNumber(score.getEmpNumber());
        vo.setExamId(score.getExamId());
        vo.setExamName(score.getExamName());
        vo.setFullScore(score.getFullScore());
        vo.setPassScore(score.getPassScore());
        vo.setExamScore(score.getExamScore());
        vo.setPass(score.getPass());
        if (score.getPass() == 0) {
            vo.setPassDesc("不及格");
        } else if (score.getPass() == 1) {
            vo.setPassDesc("及格");
        } else {
            vo.setPassDesc("未知");
            log.error("是否及格未知! pass = " + score.getPass());
        }
        vo.setErrorCount(score.getErrorCount());

        return vo;
    }

    private List<ScoreVO> transFormToVO(List<Score> list) {
        List<ScoreVO> returnList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (Score score : list) {
                returnList.add(transFormToVO(score));
            }
        }

        return returnList;
    }
}
