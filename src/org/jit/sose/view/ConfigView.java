package org.jit.sose.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 系统配置模块<br>
 * 注意：<br>
 * 1、请求路径唯一，切勿与其他任何请求路径重复<br>
 * 2、请求返回的视图，切勿与其他任何请求路径重复<br>
 * 
 * @author: 王越
 * @date: 2019-06-27 14:49:18
 */
@Controller
@RequestMapping(value = "/config")
public class ConfigView {
	/**
	 * 跳转域表页面
	 * 
	 * @return 域表页面路径
	 */
	@RequestMapping(value = "/eecstate_", method = RequestMethod.GET)
	public String eecstate() {
		return "config/eecstate";
	}

	@RequestMapping(value = "/table_title", method = RequestMethod.GET)
	public String tableTitle() {
		return "config/tableTitle";
	}
}
