package com.hand.mybatis.dao;

import com.hand.mybatis.bean.Department;

public interface DepartmentMapper {

	Department getDeptById(Integer did);
	
	//根据部门id，查询部门信息，同时将部门下的所有员工信息查询出来
	 Department getDeptByIdPlus(Integer did);
	 
	 //分步查询部门信息
	 Department getDeptByIdStep(Integer did);
}
