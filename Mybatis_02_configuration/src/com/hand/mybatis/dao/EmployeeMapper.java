package com.hand.mybatis.dao;

import com.hand.mybatis.bean.Employee;

public interface EmployeeMapper {
	
	Employee selectEmployee(Integer eid);
	

}
