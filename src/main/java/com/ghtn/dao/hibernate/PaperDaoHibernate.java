package com.ghtn.dao.hibernate;

import com.ghtn.dao.PaperDao;
import com.ghtn.model.Paper;
import org.springframework.stereotype.Repository;

/**
 * Created by lihe on 14-6-30.
 */
@Repository("paperDao")
public class PaperDaoHibernate extends GenericDaoHibernate<Paper, Integer> implements PaperDao {

    public PaperDaoHibernate() {
        super(Paper.class);
    }

}
