package com.ghtn.controller;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ghtn.service.TransferManager;
import com.ghtn.util.FileUtil;

@Controller
@RequestMapping(value="/transfer")
public class TransferController extends BaseController{
	
	private TransferManager transferManager;
	
	
	@RequestMapping("/listTransferByCard")
	@ResponseBody
	public Map<String, Object> listEmployeeByPage(String card)throws Exception{
		Map<String, Object> map = new HashMap<>();
		map.put("items", transferManager.listTransferByCard(card));
		return map;
	}


	public TransferManager getTransferManager() {
		return transferManager;
	}

	@Resource
	public void setTransferManager(TransferManager transferManager) {
		this.transferManager = transferManager;
	}

}
