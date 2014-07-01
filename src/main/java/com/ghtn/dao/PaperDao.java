package com.ghtn.dao;

import com.ghtn.model.Paper;

import java.util.Date;
import java.util.List;

/**
 * Created by lihe on 14-6-30.
 */
public interface PaperDao extends GenericDao<Paper, Integer> {

    List<Paper> listPaperByPage(int start, int limit, Date startDate, Date endDate, int deptId, int status);

    Long getCount(Date startDate, Date endDate, int deptId, int status);
}
