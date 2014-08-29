package com.ghtn.service;

import javax.servlet.http.HttpSession;

import com.ghtn.model.Contract;
import com.ghtn.model.Employee;
import com.ghtn.model.Transfer;

import java.text.ParseException;
import java.util.List;

/**
 * Created by lihe on 14-6-23.
 */
public interface ContractManager extends GenericManager<Contract, Integer> {
	List<Contract> listContractByPage(int start, int limit, String queryCondition, String queryValue);
    Long getCount(String queryCondition, String queryValue);
    void removeContract(Contract contract);
    void addContract(Contract contract) throws Exception;

}
