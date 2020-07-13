package org.jit.sose.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 教学计划模块视图跳转<br>
 * 注意：<br>
 * 1、请求路径唯一，切勿与其他任何请求路径重复<br>
 * 2、请求返回的视图，切勿与其他任何请求路径重复<br>
 * 
 * @author: 王越
 * @date: 2019年4月18日 下午11:15:01
 */
@Controller
@RequestMapping(value = "/edu")
public class EduView {
	
	/**
	 * 跳转教学计划
	 */
	@RequestMapping(value = "/edu_plan", method = RequestMethod.GET)
	public String eduPlan() {
		return "edu/eduPlan";
	}

	@RequestMapping(value = "/course_prop", method = RequestMethod.GET)
	public String courseProp() {
		// 视图名
		return "edu/courseProp";
	}
	
	@RequestMapping(value = "/course_type", method = RequestMethod.GET)
	public String courseType() {
		// 视图名
		return "edu/courseType";
	}
	
	
	@RequestMapping(value = "/video_manage", method = RequestMethod.GET)
	public String videoManage() {
		// 视图名
		return "edu/videoManage";
	}
	
}