package com.kingguanzhang.toptalk.utils;

/**
 * 用于处理图片路径的工具类
 */
public class PathUtil {

   // private static String seperator = System.getProperty("file.separator");
    private static String os = System.getProperty("os.name");
    /**
     	* 用于根据操作系统的不同获取对应的图片储存的基础路径;
     * @return
     */
    public static String getImgBasePath(){
        String basePath="";
        if (os.toLowerCase().startsWith("win")){
            basePath="C:/NotSystemSrc/projectdev/images";
        }else {
            basePath="/home/projectdev/images";
        }
        //return basePath.replace("/",seperator);
        return basePath;
    }
    
    /**
     * 用于根据操作系统的不同获取对应的图片资源的虚拟路径;
     * @return
     */
    public static String getResourceLocations(){
    	String ResourceLocations = "";
    	if (os.toLowerCase().startsWith("win")){
    		ResourceLocations = "file:C:/NotSystemSrc/projectdev/images/upload/";
    	}else {
    		ResourceLocations = "file:/home/projectdev/images/upload/";
    	}
    	//return basePath.replace("/",seperator);
    	return ResourceLocations;
    }
    /**
     * 用于根据操作系统的不同获取对应的文件上传临时目录;
     * @return
     */
    public static String getUploadTempLocation(){
    	String ResourceLocations = "";
    	if (os.toLowerCase().startsWith("win")){
    		ResourceLocations = "C:/NotSystemSrc/projectdev/temp";
    	}else {
    		ResourceLocations = "/usr/temp";
    	}
    	//return basePath.replace("/",seperator);
    	return ResourceLocations;
    }


}
