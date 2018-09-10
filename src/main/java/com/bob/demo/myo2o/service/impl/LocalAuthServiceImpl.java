package com.bob.demo.myo2o.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bob.demo.myo2o.dao.LocalAuthDao;
import com.bob.demo.myo2o.dto.LocalAuthExecution;
import com.bob.demo.myo2o.entity.LocalAuth;
import com.bob.demo.myo2o.enums.LocalAuthStateEnum;
import com.bob.demo.myo2o.service.LocalAuthService;

/** 
* @author bob 
* @version 创建时间：2018年9月2日 下午9:42:37 
* 类说明 
*/
@Service
public class LocalAuthServiceImpl implements LocalAuthService{
	
	Logger logger = LoggerFactory.getLogger(LocalAuthServiceImpl.class);
	
	@Autowired
	private LocalAuthDao localAuthDao;

	@Override
	@Transactional
	public LocalAuthExecution addLocalAuth(LocalAuth localAuth) {
		// TODO Auto-generated method stub
		try {
			int effectedNum = localAuthDao.insertLocalAuth(localAuth);
			if(effectedNum>0) {
				return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
			}else {
				logger.error("新增localAuth到表失败");
				throw new RuntimeException("新增localAuth到表失败");
			}
		}catch(Exception e) {
			logger.error(LocalAuthStateEnum.INNER_ERROR.getStateInfo());
			return new LocalAuthExecution(LocalAuthStateEnum.INNER_ERROR);
		}
	}

	@Override
	public LocalAuthExecution getLocalAuthByUserNameAndPwd(String username, String password) {
		// TODO Auto-generated method stub
		try {
			LocalAuth localAuth = localAuthDao.queryByUserNameAndPwd(username, password);
			if(localAuth!=null) {
				return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS,localAuth);
			}else {
				logger.error(LocalAuthStateEnum.EMPTY.getStateInfo());
				return new LocalAuthExecution(LocalAuthStateEnum.EMPTY);
			}
		}catch(Exception e) {
			logger.error(LocalAuthStateEnum.EMPTY.getStateInfo());
			return new LocalAuthExecution(LocalAuthStateEnum.EMPTY);
		}
	}

	@Override
	public LocalAuthExecution modifyLocalAuth(long personInfoId, String username, String oldPassword,
			String newPassword) {
		try {
			int effectedNum = localAuthDao.updateLocalAuth(personInfoId, username, oldPassword, newPassword, new Date());
			if(effectedNum>0) {
				return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
			}else {
				logger.error("更新localAuth到表失败");
				throw new RuntimeException("更新localAuth到表失败");
			}
		}catch(Exception e) {
			logger.error(LocalAuthStateEnum.INNER_ERROR.getStateInfo());
			return new LocalAuthExecution(LocalAuthStateEnum.INNER_ERROR);
		}
	}

}
