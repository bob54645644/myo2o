package com.bob.demo.myo2o.dto;
/** 
* @author bob 
* @version 创建时间：2018年8月27日 下午7:08:23 
* 类说明 
*/

import java.util.List;

import com.bob.demo.myo2o.entity.Product;
import com.bob.demo.myo2o.enums.ProductStateEnum;

public class ProductExecution {
	private int state;
	private String stateInfo;
	private Product product;
	private List<Product> productList;
	private int count;
	public ProductExecution() {
		super();
	}
	public ProductExecution(ProductStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	public ProductExecution(ProductStateEnum stateEnum,
			Product product) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.product = product;
	}
	public ProductExecution(ProductStateEnum stateEnum,
			List<Product> productList,int count) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.productList = productList;
		this.count = count;
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
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
