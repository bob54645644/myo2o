package com.bob.demo.myo2o.entity;
/** 
* @author bob 
* @version 创建时间：2018年8月19日 下午1:12:33 
* 类说明 产品类别
*/

import java.util.Date;

public class ProductCategory {
	private Long productCategoryId;
	//名称
	private String productCategoryName;
	//权重
	private Integer priority;
	//创建时间
	private Date createTime;
	//属与的商店
	private Long shopId;
	public Long getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	public String getProductCategoryName() {
		return productCategoryName;
	}
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
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
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	@Override
	public String toString() {
		return "ProductCategory [productCategoryId=" + productCategoryId + ", productCategoryName="
				+ productCategoryName + ", priority=" + priority + ", createTime=" + createTime + ", shopId=" + shopId
				+ "]";
	}
	
}
