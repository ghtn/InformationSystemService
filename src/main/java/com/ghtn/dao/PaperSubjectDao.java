package com.ghtn.dao;

import com.ghtn.model.PaperSubject;

/**
 * Created by lihe on 14-6-30.
 */
public interface PaperSubjectDao extends GenericDao<PaperSubject, Integer> {

    void removePaperSubject(int paperId);

}
