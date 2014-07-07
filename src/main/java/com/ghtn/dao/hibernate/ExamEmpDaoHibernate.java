package com.ghtn.dao.hibernate;

import com.ghtn.dao.ExamEmpDao;
import com.ghtn.model.ExamEmp;
import org.springframework.stereotype.Repository;

/**
 * Created by lihe on 14-7-7.
 */
@Repository("examEmpDao")
public class ExamEmpDaoHibernate extends GenericDaoHibernate<ExamEmp, Integer> implements ExamEmpDao {

    public ExamEmpDaoHibernate() {
        super(ExamEmp.class);
    }

    @Override
    public void removeExamEmp(int examId) {
        String hql = "delete from ExamEmp ee where ee.examId = ?";
        getSession().createQuery(hql).setInteger(0, examId).executeUpdate();
    }
}
