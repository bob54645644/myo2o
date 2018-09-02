package com.bob.demo.myo2o.service;
/** 
* @author bob 
* @version 创建时间：2018年9月2日 下午9:32:01 
* 类说明 
*/

import com.bob.demo.myo2o.dto.LocalAuthExecution;
import com.bob.demo.myo2o.entity.LocalAuth;

public interface LocalAuthService {
	// 新增
	LocalAuthExecution addLocalAuth(LocalAuth localAuth);

	// 查询
	LocalAuthExecution getLocalAuthByUserNameAndPwd(String username, String password);

	// 修改
	LocalAuthExecution modifyLocalAuth(long personInfoId, String username, String oldPassword, String newPassword);

}
