package com.kingguanzhang.toptalk.utils;

/**
 * 用于处理图片路径的工具类
 */
public class PathUtil {

    private static String seperator = System.getProperty("file.separator");

    /**
     * 用于根据操作系统的不同获取对应的图片储存的基础路径;
     * @return
     */
    public static String getImgBasePath(){
        String os = System.getProperty("os.name");
        String basePath="";
        if (os.toLowerCase().startsWith("win")){
            basePath="D:/projectdev/images/upload/";
        }else {
            basePath="/home/projectdev/images/upload/";
        }
        //return basePath.replace("/",seperator);
        return basePath;
    }


}
