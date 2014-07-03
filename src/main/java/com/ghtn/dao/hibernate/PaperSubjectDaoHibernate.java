package com.ghtn.dao.hibernate;

import com.ghtn.dao.PaperSubjectDao;
import com.ghtn.model.PaperSubject;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lihe on 14-6-30.
 */
@Repository("paperSubjectDao")
public class PaperSubjectDaoHibernate extends GenericDaoHibernate<PaperSubject, Integer>
        implements PaperSubjectDao {

    public PaperSubjectDaoHibernate() {
        super(PaperSubject.class);
    }

    @Override
    public void removePaperSubject(int paperId) {
        String hql = "delete from PaperSubject ps where ps.paperId = ?";
        getSession().createQuery(hql).setInteger(0, paperId).executeUpdate();
    }

    @Override
    public List<PaperSubject> getPaperSubject(int paperId) {
        String hql = "from PaperSubject ps where ps.paperId = ?";
        return getSession().createQuery(hql).setInteger(0, paperId).list();
    }
}
