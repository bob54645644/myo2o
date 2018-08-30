package com.bob.demo.myo2o.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * @author bob
 * @version 创建时间：2018年8月20日 下午4:59:56 类说明 处理缩略图工具类
 */
public class ThumbnailUtil {
	// 随机数
	public static Random random = new Random();
	// 时间模板
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

	// 生成缩略图
	public static String generateThumbnail(ImageHolder imageHolder, String targetAddr) throws IOException {
		// 生成目标文件夹
		String destDir = PathUtil.getImageBasePath() + targetAddr;
		makeDir(destDir);
		// 生成随机文件名
		String randomName = getRandomName();
		// 获得文件名后缀
		String suffix = getSuffix(imageHolder.getImageName());
		// 相对文件名
		String relativeName = targetAddr + randomName + suffix;
		// 最终文件名
		File file = new File(destDir + randomName + suffix);

		// 生成缩略图
		Thumbnails.of(imageHolder.getInputStream()).size(200, 200)
				.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("e:/watermark.jpg")), 0.25f).toFile(file);
		return relativeName;
	}

	// 生成详情图
	public static String generateImg(ImageHolder imageHolder, String targetAddr) throws IOException {
		// 生成目标文件夹
		String destDir = PathUtil.getImageBasePath() + targetAddr;
		makeDir(destDir);
		// 生成随机文件名
		String randomName = getRandomName();
		// 获得文件名后缀
		String suffix = getSuffix(imageHolder.getImageName());
		// 相对文件名
		String relativeName = targetAddr + randomName + suffix;
		// 最终文件
		File file = new File(destDir + randomName + suffix);

		// 生成详情图
		// Thumbnails.of(imageHolder.getInputStream()).size(width, height)
		// .toFile(file);
		inputSteamToFile(imageHolder.getInputStream(), file);
		return relativeName;
	}

	// inputStream转换为File
	private static void inputSteamToFile(InputStream inputStream, File file) throws IOException {
		// TODO Auto-generated method stub
		FileOutputStream outputStream = new FileOutputStream(file);
		int index = -1;
		byte[] bytes = new byte[1024];
		while ((index = inputStream.read(bytes)) != -1) {
			outputStream.write(bytes, 0, index);
			outputStream.flush();
		}
		outputStream.close();
		inputStream.close();
	}

	// 获得文件名后缀
	private static String getSuffix(String imageName) {
		// TODO Auto-generated method stub
		String suffix = imageName.substring(imageName.lastIndexOf("."));
		return suffix;
	}

	// 生成随机文件名
	private static String getRandomName() {
		// 数字
		int randomNum = random.nextInt(89999) + 10000;
		// 时间
		String nowDate = sdf.format(new Date());
		return "/" + randomNum + nowDate;
	}

	// 创建文件夹
	private static void makeDir(String destDir) {
		File file = new File(destDir);
		if (!file.exists()) {
			file.mkdirs();
		}

	}

	public static void main(String[] args) throws IOException {
		// File file = new File("e:/ftp.png");
		// FileInputStream inputStream = new FileInputStream(file);
		// Thumbnails.of(inputStream).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new
		// File("e:/watermark.jpg")), 0.5f)
		// .toFile("e:/b.jpg");
		// String randomName = getRandomName();
		// System.out.println(randomName);
		// String suffix = getSuffix("aaad.fff");
		// System.out.println(suffix);
	}
}
