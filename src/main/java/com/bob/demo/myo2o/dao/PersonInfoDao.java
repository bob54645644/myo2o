package com.bob.demo.myo2o.dao;

import com.bob.demo.myo2o.entity.PersonInfo;

/** 
* @author bob 
* @version 创建时间：2018年8月19日 下午8:53:05 
* 类说明 
*/
public interface PersonInfoDao {
	//新建用户信息
	int insertPersonInfo(PersonInfo personInfo);
	//根据id查询用户信息
	PersonInfo queryPersonInfoById(long personInfoId);
}
