package com.hand.mybatis.bean;

public class Employee {
	
	private Integer eId;
	private String eName;
	private Integer gender;
	private String email;
	private Department dept;
	
	
	
	public Employee() {
		super();
	}
	
	
	public Employee(Integer eId,String eName, Integer gender, String email) {
		super();
		this.eId=eId;
		this.eName = eName;
		this.gender = gender;
		this.email = email;
	}


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
	
	
	
	public Department getDept() {
		return dept;
	}


	public void setDept(Department dept) {
		this.dept = dept;
	}


	@Override
	public String toString() {
		return "Employee [eId=" + eId + ", ename=" + eName + ", gender=" + gender + ", email=" + email + "]";
	}
	
	
	
	
	

}
