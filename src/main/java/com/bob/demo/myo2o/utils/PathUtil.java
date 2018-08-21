package com.bob.demo.myo2o.utils;
/** 
* @author bob 
* @version 创建时间：2018年8月20日 下午6:52:18 
* 类说明 根据系统类型，返回相应的路径
*/
public class PathUtil {
	public static String getImageBasePath() {
		String os = System.getProperty("os.name");
		String basePath = null;
		if(os.toLowerCase().startsWith("win")) {
			basePath = "d:/myo2o/image";
		}else {
			basePath="/user/bob/myo2o/image";
		}
		return basePath;
	}
	public static String getShopImagePath(long shopId) {
		String shopImagePath = getImageBasePath()+"/upload/item/shop/"+shopId;
		return shopImagePath;
	}
}
