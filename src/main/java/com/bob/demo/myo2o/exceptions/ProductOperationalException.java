package com.bob.demo.myo2o.exceptions;
/** 
* @author bob 
* @version 创建时间：2018年8月26日 下午5:07:02 
* 类说明 
*/
public class ProductOperationalException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6052084058880267922L;

	public ProductOperationalException(String msg) {
		super(msg);
	}
}
