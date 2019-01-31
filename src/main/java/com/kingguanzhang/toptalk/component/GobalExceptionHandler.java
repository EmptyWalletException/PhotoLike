package com.kingguanzhang.toptalk.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * 处理一些没有刻意捕捉的全局异常,可以被springboot自动匹配4XX或5XX页面的异常处理机制代替;
 * @author kingguanzhang
 *
 */
//@ControllerAdvice //将此注解关闭掉,使用springboot的自动处理异常的机制,以后需要特殊处理某类异常的时候再开启;
public class GobalExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(GobalExceptionHandler.class);

	/**
	 * 处理运行时异常;
	 * @param e
	 * @param model
	 * @return
	 */
	@ExceptionHandler
	public String RuntimeException(RuntimeException e,Model model) {
		if (logger.isInfoEnabled()) {
			logger.info(e.getMessage());
		}
		e.printStackTrace();
		model.addAttribute("errorMsg", "服务器好像出了一点小问题,请等待管理员修复.");
		return "error/promptMessage";
	}
	
	/**
	 * 处理空指针异常;
	 * @param e
	 * @param model
	 * @return
	 */
	@ExceptionHandler
	public String NullPointerException(NullPointerException e,Model model) {
		if (logger.isInfoEnabled()) {
			logger.info(e.getMessage());
		}
		e.printStackTrace();
		model.addAttribute("errorMsg", "服务器好像出了一点小问题,请等待管理员修复.");
		return "error/promptMessage";
	}

}
