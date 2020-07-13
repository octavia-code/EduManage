package org.jit.sose.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 自定义错误页面
 * 
 * @author: dylan
 * @date: 2019年5月7日 下午2:18:50
 */
@Controller
@RequestMapping(value = "/err")
public class ErrorView {

	/**
	 * 403页面跳转
	 * 
	 * @return 403页面
	 */
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String error403View() {
		return "error/403";
	}

	/**
	 * 404页面跳转
	 * 
	 * @return 404页面
	 */
	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String error404View() {
		return "error/404";
	}

	/**
	 * 500页面跳转
	 * 
	 * @return 500页面
	 */
	@RequestMapping(value = "/500", method = RequestMethod.GET)
	public String error500View() {
		return "error/500";
	}

}
