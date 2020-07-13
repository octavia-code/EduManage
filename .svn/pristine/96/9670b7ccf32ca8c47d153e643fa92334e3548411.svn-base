package org.jit.sose.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 毕业要求指标点模块视图跳转<br>
 * 注意：<br>
 * 1、请求路径唯一，切勿与其他任何请求路径重复<br>
 * 2、请求返回的视图，切勿与其他任何请求路径重复<br>
 * 
 * @author: 王越
 * @date: 2019年4月18日 下午11:15:01
 */
@Controller
@RequestMapping(value = "/indicator")
public class IndicatorView {

	/**
	 * 跳转支撑系数
	 */
	@RequestMapping(value = "/support_coefficient", method = RequestMethod.GET)
	public String supportCoefficient() {
		return "indicator/supportCoefficient";
	}

	/**
	 * 跳转指标关联
	 */
	@RequestMapping(value = "/indicator_relat", method = RequestMethod.GET)
	public String indicatorRelat() {
		return "indicator/indicatorRelat";
	}

	/**
	 * 跳转毕业要求指标点
	 */
	@RequestMapping(value = "/graduation_require_indicator", method = RequestMethod.GET)
	public String graduationRequireIndicator() {
		return "indicator/graduationRequireIndicator";
	}

	/**
	 * 跳转二级指标信息页面
	 * 
	 * @return 二级指标信息页面路径
	 */
	@RequestMapping(value = "/indicator_sec", method = RequestMethod.GET)
	public String indicatorSec() {
		return "indicator/indicatorSec";
	}

	/**
	 * 跳转一级指标信息页面
	 * 
	 * @return 二级指标信息页面路径
	 */
	@RequestMapping(value = "/indicator_first", method = RequestMethod.GET)
	public String indicatorFirst() {
		return "indicator/indicatorFirst";
	}

	/**
	 * 跳转三级指标信息页面
	 * 
	 * @return 三级指标信息页面路径
	 */
	@RequestMapping(value = "/indicator_third", method = RequestMethod.GET)
	public String indicatorThird() {
		return "indicator/indicatorThird";
	}

}
