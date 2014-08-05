package com.ghtn.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ghtn.dao.TransferDao;
import com.ghtn.model.Transfer;
import com.ghtn.util.StringUtil;

/**
 * Created by lihe on 14-7-10.
 */
@Repository("transferDao")
public class TransferDaoHibernate extends GenericDaoHibernate<Transfer, Integer>
        implements TransferDao {

    public TransferDaoHibernate() {
        super(Transfer.class);
    }


	@Override
	public List<Transfer> listTransferByCard(String card) {
		// TODO Auto-generated method stub
		Criteria c = getSession().createCriteria(Transfer.class);
		 if ( !StringUtil.isNullStr(card)) {
			 c.add(Restrictions.eq("card", card));
		 }
	     return c.addOrder(Order.asc("id")).list();
	}
}
