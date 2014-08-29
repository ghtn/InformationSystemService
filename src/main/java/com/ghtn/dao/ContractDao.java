package com.ghtn.dao;

import java.util.Date;
import java.util.List;

import com.ghtn.model.Contract;
import com.ghtn.model.Employee;
import com.ghtn.model.Transfer;

/**
 * Created by lihe on 14-7-10.
 */
public interface ContractDao extends GenericDao<Contract, Integer> {

    List<Contract> listContractByPage(int start, int limit, String queryCondition, String queryValue);
    Long getCount(String queryCondition, String queryValue);
}
