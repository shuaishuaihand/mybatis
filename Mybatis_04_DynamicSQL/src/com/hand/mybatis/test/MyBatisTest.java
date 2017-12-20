package com.hand.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.hand.mybatis.bean.Department;
import com.hand.mybatis.bean.Employee;
import com.hand.mybatis.dao.DepartmentMapper;
import com.hand.mybatis.dao.EmployeeMapper;
import com.hand.mybatis.dao.EmployeeMapperAnnotation;
import com.hand.mybatis.dao.EmployeeMapperDynamicSQL;
import com.hand.mybatis.dao.EmployeeMapperPlus;

//总结

/* 
 * 1.SqlSession 代表和数据库的一次会话，用完必须关闭
 * 2.sqlSession和connection一样都是非线性安全的，每次使用都应该去获取新的的对象
 * 3.mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象
 *    （将接口和Xml进行绑定）
 *     EmployeeMapper mapper=openSession.getMapper(EmployeeMapper.class);
 * 4.两个重要的配置文件：
 *   1）mybatis的全局配置文件，包含数据库连接池，事务管理器信息等...系统运行环境
 *   2）sql映射文件，保存了每一个sql语句的映射信息
 * */


public class MyBatisTest {
	
	public SqlSessionFactory getSqlSessionFactory() throws IOException{

		
		String resource = "mybatis-configuration.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
	    return new SqlSessionFactoryBuilder().build(inputStream);
		
	}
	
	//1.根据xml配置文件（全局配置文件）创建一个sqlSessionFactory对象
	//2.sql映射文件，配置了每一个SQL，以及sql的封装规则等
	//3.将sql映射文件注册在全局配置文件中
	//4.写代码
	     //1)根据配置文件得到SqlSessionFactory
	     //2)使用sqlsession工厂，获得sqlSession对象，使用他来执行增删改查
	
	@Test
	public void test() throws IOException{
		System.out.println("employee1");
	
	  //获得sqlSession实例，能直接执行已经映射的SQL语句
		SqlSession openSession= getSqlSessionFactory().openSession();
		try{
	        Employee employee=openSession.selectOne("com.hand.mybatis.dao.EmployeeMapper.selectEmployee", 101);
	        System.out.println(employee);
		}finally{
			openSession.close();
		}
	}
	
	@Test
	public void test1() throws IOException{
		System.out.println("employee2222222222222");
		
		//1.获取sqlSessionFactory
		SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
		//获取sqlSession对象
		 SqlSession openSession= sqlSessionFactory.openSession();
		 
		 try{
		//3.获取接口类对象
		//会为接口自动创建一个代理类，代理类去执行增删改查方法
		EmployeeMapper mapper=openSession.getMapper(EmployeeMapper.class);
		 Employee employee= mapper.selectEmployee(102);
		 System.out.println(employee);
		 }finally{
			 openSession.close();
		 }
		  
	}
	
	@Test
	public void test2() throws IOException{
		SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
		SqlSession openSession=sqlSessionFactory.openSession();
		
		 try{
			
			     EmployeeMapperAnnotation mapper=openSession.getMapper(EmployeeMapperAnnotation.class);
				 Employee employee= mapper.getEmployee(102);
				 System.out.println(employee);
				 }finally{
					 openSession.close();
				 }
		
	}
	
	
	@Test
	public void test3() throws IOException{
		/* 
		 * 测试增删改查
		 * 1.mybatis允许增删改查直接定义以下类型的返回值
		 * Integer,Long,Boolean,void
		 * 2.我们需要手动提交数据
		 *     sqlSessionFactory.openSession();===>手动提交
		 *     sqlSessionFactory.openSession(true):====>自动提交
		 * 
		 * */
		SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
		//1.获取到sqlSession不会自动提交数据
		SqlSession openSession=sqlSessionFactory.openSession(true);
		
		try{
		    EmployeeMapper mapper=openSession.getMapper(EmployeeMapper.class);
		    
		    Employee employee=new Employee(null,"倪帅",0,"28163809213060@163111.com");
		    //增加
		    // mapper.addEmp(employee);
		    // System.out.println(employee.geteId());
		    //修改
		    //Boolean tag= mapper.updateEmp(employee);
		     //System.out.println(tag);
		    //删除
		     //int tag=mapper.deleteEmpById(106);
		     //System.out.println(tag);
		    
		    //2.手动提交数据
		    openSession.commit();
		}finally{
			openSession.close();
		}
	}
	
	
	/*
	 * 方法传入多个参数的情况
	 * 1.@param()
	 * */
	
	@Test
	public void test4() throws IOException{
		SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
		SqlSession openSession= sqlSessionFactory.openSession();
		
		try{
		  EmployeeMapper employeeMapper= openSession.getMapper(EmployeeMapper.class);
		  Employee employee=employeeMapper.getEmp(101, "张三");
		  System.out.println(employee);
		 /* Map<String, Object> map=new HashMap<>();
		  map.put("id", 101);
		  map.put("ename", "张三");*/ 
		  
		  //Employee employee=employeeMapper.getEmp(map);
		  //System.out.println(employee);
		  
		 
		  /* List<Employee> like=employeeMapper.getEmpByeNameLike("%张%");
		  for (Employee employee : like) {
			System.out.println(employee);
		  }*/
		  
		/* Map<String, Object> map= employeeMapper.getEmpByIdReturnMap(101);
		 System.out.println(map);*/
		  
		 /* Map<Integer, Employee> map=employeeMapper.getEmpByLastNameLikeReturnMap("%张%");
		  System.out.println(map);*/
		
		}finally{
			openSession.close();
		}
		
		
	}
	
	@Test
	public void test5() throws IOException{
		SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
		SqlSession openSession= sqlSessionFactory.openSession();
		try {
	       EmployeeMapperPlus   mapper= openSession.getMapper(EmployeeMapperPlus.class);
	      // Employee emp=  mapper.selectEmployee(101);
	      // Employee emp= mapper.getEmpAndDept(101);
	      // Employee emp= mapper.getEmpByIdStep(103);
	       Employee emp= mapper.getEmpByIdDis(103);
	       System.out.println(emp);
	       System.out.println(emp.getDept());
		} finally {
			openSession.close();
		}
	}
	

	@Test
	public void test6() throws IOException{
		SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
		SqlSession openSession= sqlSessionFactory.openSession();
		try{
		    DepartmentMapper deptMapper=openSession.getMapper(DepartmentMapper.class);
		    //Department dept=deptMapper.getDeptById(1);
		   Department dept= deptMapper.getDeptByIdPlus(1);
		  /* Department dept= deptMapper.getDeptByIdStep(1);*/
		    System.out.println(dept);
		    System.out.println(dept.getEmpList());
		}finally{
			openSession.close();
		}
	}
	
	@Test
	public void test7() throws IOException{
		SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
		SqlSession openSession= sqlSessionFactory.openSession();
		try{
			 EmployeeMapperDynamicSQL dymamicMapper=openSession.getMapper(EmployeeMapperDynamicSQL.class);
			//Employee employee=new Employee(null,null,null,null);
		    //List<Employee> emp= dymamicMapper.getEmpsByconditionIf(employee);
		    //System.out.println(emp);
		    
		    //查询的时候，如果某些条件没带可能sql拼装会有问题
		    //1.给where后面加上1=1，以后的条件都加and
		    //2.mybatis使用where标签将所有的查询条件包括在内
		    // mybatis 就会将where标签中拼装的sql多出来的and或者or去掉
		    
		    /*List<Employee> emplist=dymamicMapper.getEmpsByconditionTrim(employee);
		    for (Employee employee2 : emplist) {
				System.out.println(employee2);
			}*/
			
			//测试choose标签
			/*List<Employee> emplist=dymamicMapper.getEmpsByconditionChoose(employee);
			for (Employee employee2 : emplist) {
				System.out.println(employee2);
			}*/
			
			//测试set标签
			/*int i=dymamicMapper.updateEmp(employee);
			System.out.println(i);
			openSession.commit();*/
			
			/*ArrayList<Integer> arrlist=new ArrayList<>();
			arrlist.add(101);
			arrlist.add(102);
			arrlist.add(103);*/
			
			/*测试foreach标签*/
			/*List<Employee> emplist=dymamicMapper.getEmpsByConditionForeach(arrlist);
			for (Employee employee2 : emplist) {
				System.out.println(employee2);
			}*/
			
			
			ArrayList<Employee> arrlist=new ArrayList<>();
			Employee emp1=new Employee(null,"张1",1,"1@qq.com",new Department(1));
			Employee emp2=new Employee(null,"张2",0,"2@qq.com",new Department(2));
			Employee emp3=new Employee(null,"张3",0,"3@qq.com",new Department(3));
			arrlist.add(emp1);
			arrlist.add(emp2);
			arrlist.add(emp3);
			
			//测试批量保存
		    int result=dymamicMapper.addEmpsBatch(arrlist);
		    System.out.println(result);
		    openSession.commit();
			 
		    
		}finally{
			openSession.close();
		}
	}
	
	@Test
	public void testInnerParam() throws IOException{
		SqlSessionFactory sqlsessionfactory=getSqlSessionFactory();
		SqlSession openSesssion=sqlsessionfactory.openSession();
		
		try{
			EmployeeMapperDynamicSQL mapper=openSesssion.getMapper(EmployeeMapperDynamicSQL.class);
			/*List<Employee> list=mapper.getEmpsTestInnerParameter(new Employee());
			for (Employee employee : list) {
				System.out.println(employee);
			}*/
			Employee emp=new Employee();
			emp.setEname("张");
			List<Employee> list=mapper.getEmpsTestInnerParameter(emp);
			for (Employee employee : list) {
				System.out.println(employee);
			}
			
			
		}finally{
			openSesssion.close();
		}
	}
	
}
