package com.ghtn.dao;

import java.util.Date;
import java.util.List;

import com.ghtn.model.Employee;
import com.ghtn.model.Transfer;

/**
 * Created by lihe on 14-7-10.
 */
public interface TransferDao extends GenericDao<Transfer, Integer> {

    List<Transfer> listTransferByCard(String card);

}
