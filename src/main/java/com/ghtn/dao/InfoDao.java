package com.ghtn.dao;

import java.util.List;

import com.ghtn.model.Info;

/**
 * Created with IntelliJ IDEA.
 * User: lh
 * Date: 13-11-1
 * Time: 上午10:10
 * To change this template use File | Settings | File Templates.
 */
public interface InfoDao extends GenericDao<Info, Long>{
	/**
	 * 列出数据源
	 * @return
	 */
	List<Info> listInfo();
	
	Info findByField(String fieldName, String value);
}
