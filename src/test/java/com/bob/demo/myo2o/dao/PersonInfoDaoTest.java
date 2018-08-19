package com.bob.demo.myo2o.dao;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bob.demo.myo2o.entity.PersonInfo;

/** 
* @author bob 
* @version 创建时间：2018年8月19日 下午10:06:44 
* 类说明 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)//按方法名字典顺序执行
public class PersonInfoDaoTest {
	
	@Autowired
	private PersonInfoDao personInfoDao;
	
	@Test
	@Ignore
	public void testInsertPersonInfo() {
		PersonInfo personInfo = new PersonInfo();
		personInfo.setPersonName("xiu");
		personInfo.setGender("女");
		personInfo.setEnableStatus(1);
		personInfo.setPersonType(2);
		personInfo.setCreateTime(new Date());
		personInfo.setLastEditTime(new Date());
		
		int i = personInfoDao.insertPersonInfo(personInfo);
		System.out.println(i);
		System.out.println(personInfo);
	}
	@Test
	public void testQueryPersonInfoById() {
		PersonInfo info = personInfoDao.queryPersonInfoById(1L);
		System.out.println(info);
	}

}
