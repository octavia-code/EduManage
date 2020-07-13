package org.jit.sose.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 成绩模块视图跳转<br>
 * 注意：<br>
 * 1、请求路径唯一，切勿与其他任何请求路径重复<br>
 * 2、请求返回的视图，切勿与其他任何请求路径重复<br>
 * 
 * @author: 王越
 * @date: 2019年4月18日 下午11:15:01
 */

@Controller
@RequestMapping(value = "/score")
public class ScoreView {
	/**
	 * 跳转成绩登记页面
	 * 
	 * @return 
	 */
	@RequestMapping(value = "/grade_form", method = RequestMethod.GET)
	public String specialty() {
		return "score/gradeForm";
	}
	
	@RequestMapping(value = "/score_assessment", method = RequestMethod.GET)
	public String score_assessment() {
		return "score/scoreAssessment";
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public String ueditor() {
		return "score/ueditor";
	}
}
