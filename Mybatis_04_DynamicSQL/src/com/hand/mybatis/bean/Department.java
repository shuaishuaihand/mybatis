package com.hand.mybatis.bean;

import java.util.List;

public class Department {
	private Integer id;
	private String departName;
    private List<Employee> empList;
    
    
	
	public Department() {
		super();
	}
	
	
	public Department(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	
	public List<Employee> getEmpList() {
		return empList;
	}
	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}
	
	
	@Override
	public String toString() {
		return "Department [id=" + id + ", departName=" + departName + "]";
	}
	
	

}
