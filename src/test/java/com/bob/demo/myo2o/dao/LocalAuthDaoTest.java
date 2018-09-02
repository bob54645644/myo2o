package com.bob.demo.myo2o.dao;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bob.demo.myo2o.entity.LocalAuth;
import com.bob.demo.myo2o.entity.PersonInfo;

/** 
* @author bob 
* @version 创建时间：2018年9月2日 下午8:49:35 
* 类说明 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)//按方法名字典顺序执行
public class LocalAuthDaoTest {
	@Autowired
	private LocalAuthDao localAuthDao;
	
	@Test
	@Ignore
	public void testInsertLocalAuth() {
		LocalAuth localAuth = new LocalAuth();
		localAuth.setUsername("test1");
		localAuth.setPassword("password");
		localAuth.setCreateTime(new Date());
		localAuth.setLastEditTime(new Date());
		
		PersonInfo personInfo = new PersonInfo();
		personInfo.setPersonId(1L);
		localAuth.setPersonInfo(personInfo);
		
		int i = localAuthDao.insertLocalAuth(localAuth);
		System.out.println(i);
		System.out.println(localAuth);
		
	}
	@Test
	@Ignore
	public void testQueryByUserNameAndPwd() {
		LocalAuth auth = localAuthDao.queryByUserNameAndPwd("test", "password");
		System.out.println(auth);
	}
	@Test
	@Ignore
	public void testUpdate() {
		int i = localAuthDao.updateLocalAuth(1L, "test1", "passwordddd", "Password", new Date());
		System.out.println(i);
	}
}
