package com.ghtn.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghtn.dao.ContractDao;
import com.ghtn.model.Contract;
import com.ghtn.service.ContractManager;

/**
 * Created by lihe on 14-6-23.
 */
@Service("contractManager")
public class ContractManagerImpl extends GenericManagerImpl<Contract, Integer> implements ContractManager {

    private static Log log = LogFactory.getLog(ContractManagerImpl.class);

    private ContractDao contractDao;

    @Autowired
    public ContractManagerImpl(ContractDao contractDao) {
        super(contractDao);
        this.contractDao = contractDao;
    }

	@Override
	public List<Contract> listContractByPage(int start, int limit,
			String queryCondition, String queryValue) {
		// TODO Auto-generated method stub
		return this.contractDao.listContractByPage(start, limit, queryCondition, queryValue);
	}

	@Override
	public Long getCount(String queryCondition, String queryValue) {
		// TODO Auto-generated method stub
		return this.contractDao.getCount(queryCondition, queryValue);
	}

	@Override
	public void removeContract(Contract contract) {
		// TODO Auto-generated method stub
		this.contractDao.remove(contract);
	}

	@Override
	public void addContract(Contract contract) throws Exception {
		// TODO Auto-generated method stub
		this.contractDao.save(contract);
	}

	@Override
	public void updateContract(Contract contract) throws Exception {
		// TODO Auto-generated method stub
		this.contractDao.save(contract);
	}

}
