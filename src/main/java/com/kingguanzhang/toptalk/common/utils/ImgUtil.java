package com.kingguanzhang.toptalk.common.utils;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 用于处理图片的工具类
 */
public class ImgUtil {

	// 获取当前线程运行所在的路径;
	// private static String basePath =
	// Thread.currentThread().getContextClassLoader().getResource("").getPath();
	// 简单时间格式对象和随机数,用于拼接成唯一文件名;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r = new Random();
	private static final String errorJpg = "/upload/images/error.jpg";// 当上传错误时返回的默认错误提示图片的路径;

	/**
	 * * 保存图片到本地并返回图片的后半截路径(与操作系统根路径无关);
	 * 
	 * @param upfile
	 * @param centreAddr
	 * @param width
	 * @param height
	 * @return
	 */
	public static String generateThumbnail(MultipartFile upfile, String centreAddr, Integer width, Integer height) {
		// 获取随机文件名
		String randomName = getRandomFileName();
		// 获取原始数据的文件名
		String fileName = upfile.getOriginalFilename();
		// 从原始数据的文件名中截取出文件扩展名
		String extension = fileName.substring(fileName.lastIndexOf("."));

		// 拼接成随机文件名: 234415.jpg
		String relativeName = randomName + extension;
		// 确保文件夹存在
		makeDirPath(PathUtil.getImgBasePath() + "/upload" + centreAddr);
		// 拼接成完整文件路径; D:/projectdev/images/upload/235545.jpg
		String fullImgAddr = PathUtil.getImgBasePath() + "/upload" + centreAddr + relativeName;
		// 建立文件连接;
		File dest = new File(fullImgAddr);
		try {
			// 将Thumbnail传入进来的图片流写入到指定的文件夹路径里;
			Thumbnails.of(upfile.getInputStream()).size(width, height).outputQuality(1.0D).toFile(dest);// size是将图片自动缩放成适应的最大像素,会保持长宽比;
		} catch (IOException e) {
			// 如果图片文件保存失败则返回一个默认的图片路径;
			e.printStackTrace();
			return errorJpg;
		}
		return "/upload" + centreAddr + relativeName;
	}

	/**
	 * 生成随机文件名;
	 * 
	 * @return
	 */
	private static String getRandomFileName() {
		int randowNum = r.nextInt(89999) + 10000;
		String datetime = sdf.format(new Date());
		String randomName = randowNum + datetime;
		return randomName;
	}

	/**
	 * 生成文件路径所涉及到的目录;
	 * 
	 * @param addr
	 */
	private static void makeDirPath(String addr) {
		File dirPath = new File(addr);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	/*
	 * public static void main(String[] args) throws IOException { Thumbnails.of(new
	 * File("D:test.jpg")).size(100,100).toFile("D:test2.jpg"); }
	 */
}
