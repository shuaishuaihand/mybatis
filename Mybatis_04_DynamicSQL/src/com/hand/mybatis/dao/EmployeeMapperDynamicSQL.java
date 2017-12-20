package com.hand.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hand.mybatis.bean.Employee;
public interface EmployeeMapperDynamicSQL {
	
	List<Employee> getEmpsTestInnerParameter(Employee employee);
	
	//使用if
	List<Employee> getEmpsByconditionIf(Employee employee);
	
	//使用trim()
	List<Employee> getEmpsByconditionTrim(Employee employee);
	
	//使用choose
	List<Employee> getEmpsByconditionChoose(Employee employee);
	
	//跟新字段（set）
	int updateEmp(Employee employee);
	
	//根据list集合条件，查询集合（foreach遍历list集合条件）
	List<Employee> getEmpsByConditionForeach(@Param("idlist") List<Integer> idlist);
		
	//批量保存(foreach插入多条数据)
	int addEmpsBatch(@Param("emps") List<Employee> emps);
	
	

}
