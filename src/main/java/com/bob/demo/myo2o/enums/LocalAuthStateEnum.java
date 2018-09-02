package com.bob.demo.myo2o.enums;
/** 
* @author bob 
* @version 创建时间：2018年9月2日 下午9:32:35 
* 类说明 
*/
public enum LocalAuthStateEnum {
	SUCCESS(1,"操作成功"),INNER_ERROR(-1,"内部错误"),EMPTY(0,"信息为空");
	private int state;
	private String stateInfo;
	private LocalAuthStateEnum(int state,String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	public int getState() {
		return state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public static LocalAuthStateEnum stateOf(int state) {
		for(LocalAuthStateEnum lase:values()) {
			if(lase.getState()==state) {
				return lase;
			}
		}
		return null;
	}
	
}
