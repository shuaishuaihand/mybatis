package com.hand.mybatis.dao;

import com.hand.mybatis.bean.Employee;

public interface EmployeeMapperPlus {
	
	Employee selectEmployee(Integer eid);
	
	Employee getEmpAndDept(Integer eid);
	
	Employee getEmpByIdStep(Integer eid);
	
	
	Employee getEmpByIdDis(Integer eid);
	

}
