package com.bob.demo.myo2o.enums;
/** 
* @author bob 
* @version 创建时间：2018年8月26日 下午4:18:18 
* 类说明 
*/
public enum ProductCategoryStateEnum {
	SUCCESS(1,"操作成功"),EMPTY(0,"信息为空"),INNER_ERROR(-1,"内部错误");
	private int state;
	private String stateInfo;
	private ProductCategoryStateEnum(int state,String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	public int getState() {
		return state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public static ProductCategoryStateEnum stateOf(int state) {
		for(ProductCategoryStateEnum pe:values()) {
			if(pe.getState() == state) {
				return pe;
			}
		}
		return null;
	}
	
}
