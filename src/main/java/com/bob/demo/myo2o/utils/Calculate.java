package com.bob.demo.myo2o.utils;
/** 
* @author bob 
* @version 创建时间：2018年8月20日 下午3:23:13 
* 类说明 根据页码数和每页的行数，将页码转换为行号
*/
public class Calculate {
	
	public static int calculateRowIndex(int pageIndex,int pageSize) {
		return pageIndex>0?(pageIndex-1)*pageSize:0;
	}

}
