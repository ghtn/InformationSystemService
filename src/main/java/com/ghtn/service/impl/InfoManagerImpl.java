package com.ghtn.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghtn.dao.InfoDao;
import com.ghtn.dao.UserDao;
import com.ghtn.model.Info;
import com.ghtn.model.InfoField;
import com.ghtn.model.User;
import com.ghtn.service.InfoManager;
import com.ghtn.util.DigestUtil;
import com.ghtn.util.StringUtil;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-11-1 Time: 上午10:12
 * To change this template use File | Settings | File Templates.
 */
@Service("infoManager")
public class InfoManagerImpl extends GenericManagerImpl<Info, Long> implements
		InfoManager {

	private InfoDao infoDao;

	@Autowired
	public InfoManagerImpl(InfoDao infoDao) {
		super(infoDao);
		this.infoDao = infoDao;
	}

	@Override
	public List<InfoField> listField(String fieldName) {
		if( !StringUtil.isNullStr(fieldName)){
			List<Info> infos = this.infoDao.listInfo();
			if( infos != null && infos.size() > 0){
				return this.getField(infos, fieldName);
			}
		}
		return null;
	}
	
	@Override
	public boolean addField(String fieldName, String value) {
		if( !StringUtil.isNullStr(fieldName) && !StringUtil.isNullStr(value)){
			Info info = this.newInfo(fieldName, value);
			return this.infoDao.save(info) != null;
		}
		return false;
	}
	

	@Override
	public boolean removeField(String fieldName, String value) {
		if( !StringUtil.isNullStr(fieldName) && !StringUtil.isNullStr(value)){
			Info info = this.infoDao.findByField(fieldName, value);
			if( info != null){
				info = this.clearField(info, fieldName);
				return this.infoDao.save(info) != null;
			}
		}
		return false;
	}
	
	private Info clearField(Info info, String fieldName){
		switch (fieldName) {
		case "duty":
			info.setDuty("");
			break;
		case "jobDist":
			info.setJobDist("");
			break;
		case "jobTitle":
			info.setJobTitle("");
			break;
		case "jobType":
			info.setJobType("");
			break;
		case "productionLine":
			info.setProductionLine("");
			break;
		case "source":
			info.setSource("");
			break;
		default:
			return null;
		}
		return info;
	}
	
	private List<InfoField> getField(List<Info> infos, String fieldName){
		List<InfoField> list = new ArrayList<InfoField>();
		String value = "";
		switch (fieldName) {
		case "duty":
			for (Info info: infos) {
				value = info.getDuty();
				if( !StringUtil.isNullStr(value)){
					list.add(new InfoField(value, value));
				}
			}
			break;
		case "jobDist":
			for (Info info: infos) {
				value = info.getJobDist();
				if( !StringUtil.isNullStr(value)){
					list.add(new InfoField(value, value));
				}
			}
			break;
		case "jobTitle":
			for (Info info: infos) {
				value = info.getJobTitle();
				if( !StringUtil.isNullStr(value)){
					list.add(new InfoField(value, value));
				}
			}
			break;
		case "jobType":
			for (Info info: infos) {
				value = info.getJobType();
				if( !StringUtil.isNullStr(value)){
					list.add(new InfoField(value, value));
				}
			}
			break;
		case "productionLine":
			for (Info info: infos) {
				value = info.getProductionLine();
				if( !StringUtil.isNullStr(value)){
					list.add(new InfoField(value, value));
				}
			}
			break;
		case "source":
			for (Info info: infos) {
				value = info.getSource();
				if( !StringUtil.isNullStr(value)){
					list.add(new InfoField(value, value));
				}
			}
			break;

		default:
			break;
		}
		return list;
	}
	
	private Info newInfo(String fieldName, String value){
		Info info = new Info();
		switch (fieldName) {
		case "duty":
			info.setDuty(value);
			break;
		case "jobDist":
			info.setJobDist(value);
			break;
		case "jobTitle":
			info.setJobTitle(value);
			break;
		case "jobType":
			info.setJobType(value);
			break;
		case "productionLine":
			info.setProductionLine(value);
			break;
		case "source":
			info.setSource(value);
			break;
		default:
			return null;
		}
		return info;
	}
	
}
