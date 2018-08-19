package com.bob.demo.myo2o.entity;
/** 
* @author bob 
* @version 创建时间：2018年8月19日 下午12:48:38 
* 类说明 店铺实体类
*/

import java.util.Date;

public class Shop {
	private Long shopId;
	//店铺名
	private String shopName;
	//店铺描述
	private String shopDesc;
	//店铺地址
	private String shopAddr;
	//电话
	private String phone;
	//店铺缩略图
	private String shopImg;
	//权重
	private Integer priority;
	//创建时间
	private Date createTime;
	//最后修改时间
	private Date lastEditTime;
	//店铺状态 -1、不可用 0、待审核 1、可用
	private Integer enableStatus;
	//建议
	private String advice;
	//所属区域
	private Area area;
	//所属类别
	private ShopCategory shopCategory;
	//拥有者
	private PersonInfo owner;
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopDesc() {
		return shopDesc;
	}
	public void setShopDesc(String shopDesc) {
		this.shopDesc = shopDesc;
	}
	public String getShopAddr() {
		return shopAddr;
	}
	public void setShopAddr(String shopAddr) {
		this.shopAddr = shopAddr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getShopImg() {
		return shopImg;
	}
	public void setShopImg(String shopImg) {
		this.shopImg = shopImg;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	public Integer getEnableStatus() {
		return enableStatus;
	}
	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public ShopCategory getShopCategory() {
		return shopCategory;
	}
	public void setShopCategory(ShopCategory shopCategory) {
		this.shopCategory = shopCategory;
	}
	public PersonInfo getOwner() {
		return owner;
	}
	public void setOwner(PersonInfo owner) {
		this.owner = owner;
	}
	@Override
	public String toString() {
		return "Shop [shopId=" + shopId + ", shopName=" + shopName + ", shopDesc=" + shopDesc + ", shopAddr=" + shopAddr
				+ ", phone=" + phone + ", shopImg=" + shopImg + ", priority=" + priority + ", createTime=" + createTime
				+ ", lastEditTime=" + lastEditTime + ", enableStatus=" + enableStatus + ", advice=" + advice + ", area="
				+ area + ", shopCategory=" + shopCategory + ", owner=" + owner + "]";
	}
	
}
