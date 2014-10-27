package com.ghtn.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Info {
	private int id;						// ID
	private String duty;				// 职务
	private String jobDist	;			// 工别
	private String jobTitle	;			// 职称
	private String jobType	;			// 工种
	private String productionLine;		// 生产线
	private String source;				// 来源
	@Id
	@GeneratedValue
	@javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Basic
	@javax.persistence.Column(name = "duty", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	@Basic
	@javax.persistence.Column(name = "jobDist", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getJobDist() {
		return jobDist;
	}
	public void setJobDist(String jobDist) {
		this.jobDist = jobDist;
	}
	@Basic
	@javax.persistence.Column(name = "jobTitle", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	@Basic
	@javax.persistence.Column(name = "jobType", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	@Basic
	@javax.persistence.Column(name = "productionLine", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getProductionLine() {
		return productionLine;
	}
	public void setProductionLine(String productionLine) {
		this.productionLine = productionLine;
	}
	@Basic
	@javax.persistence.Column(name = "source", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
}
