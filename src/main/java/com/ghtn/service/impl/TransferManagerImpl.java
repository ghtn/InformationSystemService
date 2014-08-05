package com.ghtn.service.impl;

import java.io.File;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghtn.dao.EmployeeDao;
import com.ghtn.dao.TransferDao;
import com.ghtn.model.Employee;
import com.ghtn.model.Transfer;
import com.ghtn.service.TransferManager;
import com.ghtn.util.ConstantUtil;
import com.ghtn.util.FileUtil;

/**
 * Created by lihe on 14-6-23.
 */
@Service("transferManager")
public class TransferManagerImpl extends GenericManagerImpl<Transfer, Integer> implements TransferManager {

    private static Log log = LogFactory.getLog(TransferManagerImpl.class);

    private TransferDao transferDao;

    @Autowired
    public TransferManagerImpl(TransferDao transferDao) {
        super(transferDao);
        this.transferDao = transferDao;
    }

	@Override
	public List<Transfer> listTransferByCard(String card)
			throws Exception {
		// TODO Auto-generated method stub
		return this.transferDao.listTransferByCard( card);
	}

}
