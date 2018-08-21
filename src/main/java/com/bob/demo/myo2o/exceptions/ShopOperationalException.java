package com.bob.demo.myo2o.exceptions;
/** 
* @author bob 
* @version 创建时间：2018年8月20日 下午4:09:00 
* 类说明 
*/
public class ShopOperationalException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5686142151817426900L;

	public ShopOperationalException(String msg) {
		super(msg);
	}
}
