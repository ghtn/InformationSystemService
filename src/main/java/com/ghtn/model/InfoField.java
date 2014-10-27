package com.ghtn.model;

public class InfoField {
	private String disp;
	private String value;
	
	public InfoField() {
	}

	public InfoField(String disp, String value) {
		this.disp = disp;
		this.value = value;
	}
	
	public String getDisp() {
		return disp;
	}
	public void setDisp(String disp) {
		this.disp = disp;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
