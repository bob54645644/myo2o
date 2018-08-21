package com.bob.demo.myo2o.enums;
/** 
* @author bob 
* @version 创建时间：2018年8月20日 下午3:10:06 
* 类说明 
*/
public enum ShopStateEnum {
	SUCCESS(1,"操作成功"),EMPTY(0,"空值异常"),INNER_ERROR(-1001,"内部错误");
	private int state;
	private String stateInfo;
	private ShopStateEnum(int state,String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	public static ShopStateEnum stateOf(int state) {
		for(ShopStateEnum sse:values()) {
			if(sse.getState() == state) {
				return sse;
			}
		}
		return null;
	}
	public int getState() {
		return state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	
}
