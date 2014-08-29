package com.ghtn.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.Length;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-1
 * Time: 上午10:04
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Indexed
public class Contract implements Serializable {
    private int id;						// ID
    private String number;				// 证书编号
    private String type;				// 证类型
    private String empNumber;			// 员工号
    private String name;				// 姓名
    private String card;				// 身份证号
    private Date entryDate;				// 录入时间
    private String path;				// 证书保存路径
    private String warn;				// 预警
    
    public Contract() {
		// TODO Auto-generated constructor stub
	}
    public Contract(int id) {
    	// TODO Auto-generated constructor stub
    	this.setId(id);
    }
    
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Basic
    @javax.persistence.Column(name = "number", nullable = true, insertable = true, updatable = true)
    public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	@Basic
    @javax.persistence.Column(name = "empNumber", nullable = true, insertable = true, updatable = true)
    public String getEmpNumber() {
		return empNumber;
	}
	public void setEmpNumber(String empNumber) {
		this.empNumber = empNumber;
	}
	
	@Basic
    @javax.persistence.Column(name = "name", nullable = true, insertable = true, updatable = true)
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Basic
    @javax.persistence.Column(name = "card", unique = false, nullable = false, insertable = true, updatable = true)
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	
	@Basic
    @javax.persistence.Column(name = "entryDate", nullable = true, insertable = true, updatable = true)
    public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	
	@Basic
    @javax.persistence.Column(name = "warn", nullable = true, insertable = true, updatable = true)
    public String getWarn() {
		return warn;
	}
	public void setWarn(String warn) {
		this.warn = warn;
	}
	
	@Basic
    @javax.persistence.Column(name = "path", nullable = true, insertable = true, updatable = true)
    public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	@Basic
    @javax.persistence.Column(name = "type", nullable = true, insertable = true, updatable = true)
    public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


}
