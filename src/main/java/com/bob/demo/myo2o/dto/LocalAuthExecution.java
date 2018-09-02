package com.bob.demo.myo2o.dto;
/** 
* @author bob 
* @version 创建时间：2018年9月2日 下午9:36:43 
* 类说明 
*/

import com.bob.demo.myo2o.entity.LocalAuth;
import com.bob.demo.myo2o.enums.LocalAuthStateEnum;

public class LocalAuthExecution {
	private int state;
	private String stateInfo;
	private LocalAuth localAuth;
	public LocalAuthExecution() {
	}
	public LocalAuthExecution(LocalAuthStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo =stateEnum.getStateInfo();
	}
	public LocalAuthExecution(LocalAuthStateEnum stateEnum,LocalAuth localAuth) {
		this.state = stateEnum.getState();
		this.stateInfo =stateEnum.getStateInfo();
		this.localAuth = localAuth;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	public LocalAuth getLocalAuth() {
		return localAuth;
	}
	public void setLocalAuth(LocalAuth localAuth) {
		this.localAuth = localAuth;
	}
	
}
