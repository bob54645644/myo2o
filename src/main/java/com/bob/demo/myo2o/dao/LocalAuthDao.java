package com.bob.demo.myo2o.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.bob.demo.myo2o.entity.LocalAuth;

/**
 * @author bob
 * @version 创建时间：2018年9月2日 下午8:11:50 类说明
 */
public interface LocalAuthDao {
	// 新增本地账户，username,password
	int insertLocalAuth(LocalAuth localAuth);

	// 修改,localAuthDaoId ,username,password
	int updateLocalAuth(@Param("personInfoId")long personInfoId,
			@Param("username") String username, @Param("oldPassword") String oldPassword,
			@Param("newPassword") String newPassword, @Param("lastEditTime") Date lastEditTime);
	// 查询，username,password
	LocalAuth queryByUserNameAndPwd(@Param("username")String username,
			@Param("password")String password);
}
