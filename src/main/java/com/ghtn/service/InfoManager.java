package com.ghtn.service;

import java.util.List;

import com.ghtn.model.Info;
import com.ghtn.model.InfoField;
import com.ghtn.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: kangking
 * Date: 13-11-1
 * Time: 上午10:12
 * To change this template use File | Settings | File Templates.
 */
public interface InfoManager extends GenericManager<Info, Long> {
	/**
	 * 列出数据源
	 * @param fieldName
	 * @return
	 */
	List<InfoField> listField(String fieldName);
	
	/**
	 * 添加数据源
	 * @param fieldName
	 * @param value
	 * @return
	 */
	boolean addField(String fieldName, String value);
	
	/**
	 * 删除数据源
	 * @param fieldName
	 * @param value
	 * @return
	 */
	boolean removeField(String fieldName, String value);
}
