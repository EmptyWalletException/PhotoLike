package com.kingguanzhang.toptalk.utils;


import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


/**
 * 用于处理图片的工具类
 */
public class ImgUtil {

    //获取当前线程运行所在的路径;
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    //简单时间格式对象和随机数,用于拼接成唯一文件名;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();

    public static String generateThumbnail(MultipartFile upfile,String centreAddr,Integer width,Integer height){
        //获取随机文件名
        String randomName = getRandomFileName();
        // 获取文件名
        String fileName = upfile.getOriginalFilename();
        //获取文件扩展名
        String extension = fileName.substring(fileName.lastIndexOf("."));

        //评价成文件名: 234415.jpg
        String relativeName =  randomName +extension;
        //拼接成完整文件路径; D:/projectdev/images/upload/235545.jpg
        String imgAddr = PathUtil.getImgBasePath()+centreAddr+relativeName;
        //确保文件夹存在
         makeDirPath(PathUtil.getImgBasePath()+centreAddr);
        //建立文件连接;
        File dest = new File (imgAddr);
        try{
            //将Thumbnail传入进来的图片流写入到指定的文件夹路径里;
           // File dest2 = new File ("D:/test.jpg");
           // Thumbnails.of(dest2).size(200,200).toFile(dest);
            //Thumbnails.of(dest2).size(200,200).toFile("D:/1.jgp");
            Thumbnails.of(upfile.getInputStream()).size(width,height).outputQuality(1.0D).toFile(dest);//size是将图片自动缩放成适应的最大像素,会保持长宽比;
           // Thumbnails.of(shopImgInputStream).toFile(dest);
        }catch (IOException e) {
            //如果图片文件保存失败则返回一个默认的图片路径;
            imgAddr = "D:/test.jpg";
            e.printStackTrace();
        }
        return "/upload"+centreAddr+relativeName;
    }

    /**
     * 生成随机文件名;
     * @return
     */
    private static String getRandomFileName() {
        int randowNum = r.nextInt(89999) + 10000;
        String datetime = sdf.format(new Date());
        String randomName =randowNum + datetime;
        return randomName;
    }


    /**
     * 生成文件路径所涉及到的目录;
     * @param addr
     */
    private static void makeDirPath(String addr) {
        File dirPath = new File(addr);
        if(!dirPath.exists()){
            dirPath.mkdirs();
        }
    }

    public static void main(String[] args) throws IOException {
        Thumbnails.of(new File("D:test.jpg")).size(100,100).toFile("D:test2.jpg");
    }
}
