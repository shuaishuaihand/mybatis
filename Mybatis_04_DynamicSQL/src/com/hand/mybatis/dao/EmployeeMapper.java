package com.hand.mybatis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import com.hand.mybatis.bean.Employee;

public interface EmployeeMapper {
	
	Employee selectEmployee(Integer eid);
	
	//根据部门id,查询部门下所有员工信息
	List<Employee> getEmpByDeptId(Integer dId);
	
	int addEmp(Employee employee);
	
	Boolean updateEmp(Employee employee);
	
	int deleteEmpById(Integer id);
	
	//传入多个参数的情况
	Employee getEmp(@Param("id") Integer id,@Param("ename") String ename);
	
	//传入多个参数的情况(map)
	//Employee getEmp(Map<String, Object> map);
	
	//集合List
	List<Employee> getEmpByeNameLike(String ename);
	
	//返回一条记录的map;key就是列名，值是对应的值
	Map<String, Object> getEmpByIdReturnMap(Integer id);
	
	//多条记录封装一个map:Map<Integer,Employee>:键是这条记录的主键，值是记录封装后的javaBean
	//告诉mybatis封装这个map的时候使用哪个属性作为map的key
	 @MapKey("eId")
	 Map<Integer, Employee> getEmpByLastNameLikeReturnMap(String ename);
	/*@MapKey("eName")
	Map<String, Employee> getEmpByLastNameLikeReturnMap(String ename);*/
	

}
