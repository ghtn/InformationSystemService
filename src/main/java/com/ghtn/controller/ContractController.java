package com.ghtn.controller;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.poi.xslf.model.geom.Context;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ghtn.model.Contract;
import com.ghtn.model.Employee;
import com.ghtn.service.ContractManager;
import com.ghtn.service.TransferManager;
import com.ghtn.util.FileUtil;

@Controller
@RequestMapping(value="/contract")
public class ContractController extends BaseController{
	
	private ContractManager contractManager;
	
	
	public ContractManager getContractManager() {
		return contractManager;
	}
	
	@RequestMapping("/listContractByPage")
	@ResponseBody
	public Map<String, Object> listContractByPage(int start, int limit, String queryCondition, String queryValue)throws Exception{
		Map<String, Object> map = new HashMap<>();
		map.put("total", contractManager.getCount(queryCondition, queryValue));
		map.put("items", contractManager.listContractByPage(start, limit, queryCondition, queryValue));
		return map;
	}

	@Resource
	public void setContractManager(ContractManager contractManager) {
		this.contractManager = contractManager;
	}
	
	@RequestMapping("/remove")
	@ResponseBody
	public Map<String, Object> remove(String id)throws Exception{
		contractManager.removeContract(new Contract(Integer.parseInt(id)));
		return operationSuccess();
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> add(Contract contract)throws Exception{
		contractManager.save(contract);
		return operationSuccess();
	}

}
