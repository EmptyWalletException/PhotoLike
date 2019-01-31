package com.kingguanzhang.toptalk.component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.kingguanzhang.toptalk.entity.User;
import com.kingguanzhang.toptalk.service.UserServiceImpl;


/**
 * 自定义的登录后初始化用户数据并处理一些统计数据的handler,security验证成功之后会进入到此类中的onAuthenticationSuccess方法,
 * 此方法主要目的是往session中存入user信息,以后还可以增加统计登陆次数或查看登陆ip等功能;
 * 并在登录成功后跳转到登录前被拦截的url;
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

	@Autowired
	private UserServiceImpl userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		/**
		 * 处理一下被权限拦截而中断的访问;
		 * 将取出来的登录前的缓存到SavedRequest里的url请求保存到redirectUrl字符串中,然后判断该跳往redirectUrl还是主页;
		 */
		Object savedRequestObject = request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
		String redirectUrl = "/index";

		if (null != savedRequestObject) {
			// 处理一下url,去掉前面的http://localhost:8080,然后截取请求url:"/xxxx/xxxxx?xxxx=xxxx"
			redirectUrl = ((SavedRequest) savedRequestObject).getRedirectUrl().substring(redirectUrl.indexOf("/", 8));
			request.getSession().removeAttribute("SPRING_SECURITY_SAVED_REQUEST");
		}
		// 使用security在session中取出登陆用户名;
		String username = authentication.getName();
		User user = new User();
		if (null != username) {
			user.setAccount(username);
			ExampleMatcher exampleMatcher5 = ExampleMatcher.matching().withIgnorePaths("id");// 因为id是Long类型,默认为0,需要忽略掉;
			Example<User> example = Example.of(user, exampleMatcher5);
			try {
				user = userService.findOne(example);
			} catch (Exception e) {
				if (logger.isInfoEnabled()) {
					logger.info("查询用户名为:" + username + "的用户出现异常:" + e);
				}
				request.getSession().setAttribute("session.SPRING_SECURITY_LAST_EXCEPTION.message", "没有查询到此用户信息!");
				response.sendRedirect("/login");
			}
			// 将用户信息写入session
			request.getSession().setAttribute("user", user);
			response.sendRedirect(redirectUrl);
			// request.getRequestDispatcher(redirectUrl).forward(request,
			// response);这里用转发并不合适;

		} else {
			request.getSession().setAttribute("session.SPRING_SECURITY_LAST_EXCEPTION.message", "未检测到用户账号信息!");
			response.sendRedirect("/login");
		}

	}
}
