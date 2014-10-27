package com.ghtn.model;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.hibernate.search.annotations.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-1
 * Time: 上午10:04
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Indexed
public class User implements Serializable {
    private Long id;				// ID
	private String account; 		// 账号
	private String password; 		// 密码
	private int grade; 			// 权限
	private String type; 			// 类型
	@Id
	@GeneratedValue
	@javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Basic
	@javax.persistence.Column(name = "account", unique = true, nullable = false, insertable = true, updatable = true, length = 255)
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	@Basic
	@javax.persistence.Column(name = "password", unique = false, nullable = false, insertable = true, updatable = true, length = 255)
	public void setPassword(String password) {
		this.password = password;
	}
	@Basic
	@javax.persistence.Column(name = "grade", unique = false, nullable = false, insertable = true, updatable = true, length = 255)
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	@Basic
	@javax.persistence.Column(name = "type", unique = false, nullable = false, insertable = true, updatable = true, length = 255)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
