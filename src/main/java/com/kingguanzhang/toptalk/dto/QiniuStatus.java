package com.kingguanzhang.toptalk.dto;

import org.springframework.beans.factory.annotation.Value;

/**
 * 用于切换七牛云相关功能的状态，项目需要根据运行的操作系统和网络状态选择是否开启七牛云功能,
 * 如果在application.properties里配置为开启;如果没有,则会根据操作系统自动选择是否开启,这是为了方便在windows开发环境下测试;
 * @author kingguanzhang
 *
 */
public class QiniuStatus {
	@Value("${qiniu.enableqiniu}")
	public static boolean enableQiniu = false;
	@Value("${qiniu.enable-autoconfiger")
	public static boolean enableAutoConfiger = true ;
	static {
		
		 //在linux生产环境下开启七牛云；
        if ( enableAutoConfiger && !System.getProperty("os.name").toLowerCase().startsWith("win")){
        	enableQiniu = true;
        }
	}
	
	/**
	 * 设置开启七牛云
	 */
	public static void enableQiniuStatus() {
		enableQiniu = true;
	}
	
	/**
	 * 禁用七牛云
	 */
	public static void disableQiniuStatus() {
		enableQiniu = false;
	}

}
