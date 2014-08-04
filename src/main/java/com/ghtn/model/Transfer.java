package com.ghtn.model;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.search.annotations.DocumentId;

@Entity
public class Transfer{
	
	private int id;				// ID
	private String card;		// 身份证
	private Date transDate;		// 调动日期
	private String detail;		// 调动详情
	
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
    @javax.persistence.Column(name = "card", unique = false, nullable = false, insertable = true, updatable = true)
    public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	@Basic
    @javax.persistence.Column(name = "transDate", nullable = true, insertable = true, updatable = true)
    public Date getTransDate() {
		return transDate;
	}
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}
	@Basic
    @javax.persistence.Column(name = "detail", nullable = true, insertable = true, updatable = true)
    public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}