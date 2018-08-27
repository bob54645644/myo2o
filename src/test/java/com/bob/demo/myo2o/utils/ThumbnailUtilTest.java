package com.bob.demo.myo2o.utils;
/** 
* @author bob 
* @version 创建时间：2018年8月20日 下午7:29:38 
* 类说明 
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import com.bob.demo.myo2o.entity.Area;
import com.bob.demo.myo2o.entity.Product;

public class ThumbnailUtilTest {
	@Test
	@Ignore
	public void testThumbnail() throws IOException {
		File file = new File("e:/ftp.png");
//		FileInputStream inputStream = new FileInputStream(file);
//		ImageHolder imageHolder = new ImageHolder(inputStream, file.getName());
//		String string = ThumbnailUtil.generateThumbnail(imageHolder, "/test");
//		System.out.println(string);
		System.out.println(file.getName());
	}
	@Test
	@Ignore
	public void testRemove() {
		PathUtil.removeByPath("/1");
	}
	@Test
	public void testa() {
		Area area = new Area();
		
		Product product = (Product)new Object();
		System.out.println(product);
	}

}
