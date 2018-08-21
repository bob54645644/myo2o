package com.bob.demo.myo2o.dto;
/** 
* @author bob 
* @version 创建时间：2018年8月20日 下午3:09:37 
* 类说明 
*/

import java.util.List;

import com.bob.demo.myo2o.entity.Shop;
import com.bob.demo.myo2o.enums.ShopStateEnum;

public class ShopExecution {
	private int state;
	private String stateInfo;
	private int count;
	private Shop shop;
	private List<Shop> shopList;
	public ShopExecution() {
		super();
	}
	//构造器一
	public ShopExecution(ShopStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	//构造器二
	public ShopExecution(ShopStateEnum stateEnum, Shop shop) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shop = shop;
	}
	//构造器三
	public ShopExecution(ShopStateEnum stateEnum, int count, List<Shop> shopList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.count = count;
		this.shopList = shopList;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public List<Shop> getShopList() {
		return shopList;
	}
	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}
	
	
}
