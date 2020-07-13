package org.jit.sose.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 模块视图跳转
 * 注意：
 * 1、请求路径唯一，切勿与其他任何请求路径重复<br>
 * 2、请求返回的视图，切勿与其他任何请求路径重复<br>
 * 
 * @author: 王越
 * @date: 2019年4月18日 下午11:15:01
 */
@Controller
@RequestMapping(value = "/test")
public class TestView {

	/**
	 * 跳转课程信息页面
	 * 
	 * @return 课程信息页面路径
	 */
	@RequestMapping(value = "/test_info", method = RequestMethod.GET)
	public String test() {
		return "test/test";
	}

	
	@RequestMapping(value = "/show_PDF", method = RequestMethod.GET)
	public String showPDF() {
		return "test/showPDF";
	}
	
	@RequestMapping(value = "/video", method = RequestMethod.GET)
	public String showVideo() {
		return "test/video";
	}




}
