package com.hand.mybatis.bean;

import org.apache.ibatis.type.Alias;

public class Employee {
	
	private Integer eId;
	private String eName;
	private Integer gender;
	private String email;
	
	public Integer geteId() {
		return eId;
	}
	public void seteId(Integer eId) {
		this.eId = eId;
	}
   
	public String getEName() {
		return eName;
	}
	public void setEname(String ename) {
		this.eName = ename;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Employee [eId=" + eId + ", ename=" + eName + ", gender=" + gender + ", email=" + email + "]";
	}
	
	
	
	
	

}
