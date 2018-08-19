package com.bob.demo.myo2o.entity;
/** 
* @author bob 
* @version 创建时间：2018年8月19日 下午1:26:22 
* 类说明 商品详情图
*/

import java.util.Date;

public class ProductImg {
	private Long imgId;
	//图片地址
	private String imgAddr;
	//图片简介
	private String imgDesc;
	//权重
	private Integer priority;
	//创建时间
	private Date createTime;
	//属与的产品
	private Long productId;
	public Long getImgId() {
		return imgId;
	}
	public void setImgId(Long imgId) {
		this.imgId = imgId;
	}
	public String getImgAddr() {
		return imgAddr;
	}
	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}
	public String getImgDesc() {
		return imgDesc;
	}
	public void setImgDesc(String imgDesc) {
		this.imgDesc = imgDesc;
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
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	@Override
	public String toString() {
		return "ProductImg [imgId=" + imgId + ", imgAddr=" + imgAddr + ", imgDesc=" + imgDesc + ", priority=" + priority
				+ ", createTime=" + createTime + ", productId=" + productId + "]";
	}
	
}
