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
@RequestMapping(value = "/account")
public class AccountView {

	/**
	 * 跳转菜单项页面
	 * 
	 * @return 菜单项页面路径
	 */
	@RequestMapping(value = "/menu_", method = RequestMethod.GET)
	public String menu() {
		return "account/menu";
	}
	
	/**
	 * 跳转角色页面
	 * 
	 * @return 角色页面路径
	 */
	@RequestMapping(value = "/role_", method = RequestMethod.GET)
	public String role() {
		return "account/role";
	}
	
	@RequestMapping(value = "/person_", method = RequestMethod.GET)
	public String person() {
		return "account/person";
	}
	

}
