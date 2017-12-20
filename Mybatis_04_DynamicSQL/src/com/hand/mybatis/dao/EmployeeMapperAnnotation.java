package com.hand.mybatis.dao;

import org.apache.ibatis.annotations.Select;

import com.hand.mybatis.bean.Employee;

public interface EmployeeMapperAnnotation {

	@Select("select * from emp Where eid=#{eid}")
	Employee getEmployee(Integer eid);
}
