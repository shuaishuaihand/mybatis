package com.hand.mybatis.bean;

public class Employee {
	
	private Integer eid;
	private String name;
	private Integer gender;
	private String email;
	public Integer getEid() {
		return eid;
	}
	
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
		return "Employee [eid=" + eid + ", name=" + name + ", gender=" + gender + ", email=" + email + "]";
	}
	
	

}
