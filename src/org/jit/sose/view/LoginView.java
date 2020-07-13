package org.jit.sose.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 账户模块视图跳转：用户登录、注册、忘记密码、重置密码<br>
 * 注意：<br>
 * 1、请求路径唯一，切勿与其他任何请求路径重复<br>
 * 2、请求返回的视图，切勿与其他任何请求路径重复<br>
 * 
 * @author: 王越
 * @date: 2019年4月18日 下午11:15:01
 */
@Controller
public class LoginView {

	/**
	 * 跳转平台首页，目前为登录页面
	 * 
	 * @return 登录页面路径
	 */
	@RequestMapping(value = {"/","/login"}, method = RequestMethod.GET)
	public String indexView() {
		return "account/login";
	}

	/**
	 * 跳转注册页面
	 * 
	 * @return 注册页面路径
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerView() {
		return "account/register";
	}

	/**
	 * 跳转忘记密码页面
	 * 
	 * @return 忘记密码页面路径
	 */
	@RequestMapping(value = "/reset_password", method = RequestMethod.GET)
	public String forgetPwdView() {
		return "account/forgetPwd";
	}
	

}
