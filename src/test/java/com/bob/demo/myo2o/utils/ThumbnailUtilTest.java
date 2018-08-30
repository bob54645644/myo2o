package com.bob.demo.myo2o.utils;
/** 
* @author bob 
* @version 创建时间：2018年8月20日 下午7:29:38 
* 类说明 
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	@Ignore
	public void testa() {
		Area area = new Area();
		
		Product product = (Product)new Object();
		System.out.println(product);
	}
	@Test
	public void testGenerateImg() throws IOException {
		File file = new File("e:/ftp.png");
		FileInputStream inputStream = new FileInputStream(file);
		
		List<ImageHolder> imageHolders = new ArrayList<>();
		ImageHolder imageHolder = new ImageHolder(inputStream, file.getName());
		imageHolders.add(imageHolder);
		
		String relative = ThumbnailUtil.generateImg(imageHolder, "/test/28");
		System.out.println(relative);
		
	}

}
