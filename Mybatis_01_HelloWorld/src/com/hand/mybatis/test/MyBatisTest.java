package com.hand.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.hand.mybatis.bean.Employee;
import com.hand.mybatis.dao.EmployeeMapper;

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
		System.out.println("employee2");
		
		//1.获取sqlSessionFactory
		SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
		//获取sqlSession对象
		 SqlSession openSession= sqlSessionFactory.openSession();
		 
		 try{
		//3.获取接口类对象
		//会为接口自动创建一个代理类，代理类去执行增删改查方法
		 EmployeeMapper mapper=openSession.getMapper(EmployeeMapper.class);
		 Employee employee= mapper.selectEmployee(101);
		 System.out.println(employee);
		 }finally{
			 openSession.close();
		 }
		 
		 
		 
	}
	
}
