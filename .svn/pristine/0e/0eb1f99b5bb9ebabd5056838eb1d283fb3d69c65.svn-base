package org.jit.sose.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 系统管理模块视图跳转<br>
 * 注意：<br>
 * 1、请求路径唯一，切勿与其他任何请求路径重复<br>
 * 2、请求返回的视图，切勿与其他任何请求路径重复<br>
 * 
 * @author: 王越
 * @date: 2019年4月18日 下午11:15:01
 */
@Controller
@RequestMapping(value = "/manage")
public class ManageView {

	/**
	 * 跳转课程信息页面
	 * 
	 * @return 课程信息页面路径
	 */
	@RequestMapping(value = "/course_info", method = RequestMethod.GET)
	public String courseInfo() {
		return "manage/courseInfo";
	}
	/**
	 * 跳转专业信息页面
	 * 
	 * @return 专业信息页面路径
	 */
	@RequestMapping(value = "/specialty_", method = RequestMethod.GET)
	public String specialty() {
		return "manage/specialty";
	}
	/**
	 * 跳转年份信息页面
	 * 
	 * @return 年份页面路径
	 */
	@RequestMapping(value = "/year_plan", method = RequestMethod.GET)
	public String yearPlan() {
		return "manage/yearPlan";
	}
	
	@RequestMapping(value = "/year_term", method = RequestMethod.GET)
	public String yearTerm() {
		return "manage/yearTerm";
	}

	
	@RequestMapping(value = "/term_info", method = RequestMethod.GET)
	public String termInfo() {
		// 视图名
		return "manage/termInfo";
	}

}
