package com.ghtn.dao.hibernate;

import com.ghtn.dao.ErrorSubjectDao;
import com.ghtn.model.ErrorSubject;
import org.springframework.stereotype.Repository;

/**
 * Created by lihe on 14-7-10.
 */
@Repository("errorSubjectDao")
public class ErrorSubjectDaoHibernate extends GenericDaoHibernate<ErrorSubject, Integer>
        implements ErrorSubjectDao {

    public ErrorSubjectDaoHibernate() {
        super(ErrorSubject.class);
    }

}
