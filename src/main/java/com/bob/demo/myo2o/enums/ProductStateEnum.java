package com.bob.demo.myo2o.enums;
/** 
* @author bob 
* @version 创建时间：2018年8月27日 下午7:03:53 
* 类说明 
*/
public enum ProductStateEnum {
	SUCCESS(1,"操作成功"),EMPTY(0,"信息为空"),INNER_ERROR(-1,"内部错误");
	private int state;
	private String stateInfo;
	private ProductStateEnum(int state,String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	public int getState() {
		return state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public static ProductStateEnum stateOf(int state) {
		for(ProductStateEnum pse:values()) {
			if(pse.getState()==state) {
				return pse;
			}
		}
		return null;
	}

}
