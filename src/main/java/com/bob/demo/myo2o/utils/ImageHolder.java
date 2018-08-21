package com.bob.demo.myo2o.utils;

import java.io.InputStream;

/** 
* @author bob 
* @version 创建时间：2018年8月20日 下午4:26:50 
* 类说明 处理图片流的中间类
*/
public class ImageHolder {
	private InputStream inputStream;
	private String imageName;
	public ImageHolder(InputStream inputStream,String imageName) {
		this.inputStream = inputStream;
		this.imageName = imageName;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
}
