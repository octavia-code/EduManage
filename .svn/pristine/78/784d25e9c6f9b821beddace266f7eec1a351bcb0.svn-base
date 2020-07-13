package org.jit.sose.controller.indicator;

import java.util.List;

import org.jit.sose.entity.SupportCoefficient;
import org.jit.sose.service.SupportCoefficientService;
import org.jit.sose.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

/**
 * 支撑系数控制器类
 * 
 * @author: 王越
 * @date: 2019年5月16日 下午11:02:16
 */
@RestController
@RequestMapping("/indicator/supportCoefficient")
public class SupportCoefficientController {

	@Autowired
	private SupportCoefficientService supportCoefficientService;

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insert(@RequestBody SupportCoefficient supportCoefficient) {
		supportCoefficient.setUserId(1);
		supportCoefficient.setIndicatorRelatId(1); // 暂设为1
		supportCoefficientService.insert(supportCoefficient);
	}

	/**
	 * 编辑操作，根据id判断是插入操作还是更新操作
	 * 
	 * @param supportCoefficient
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody SupportCoefficient supportCoefficient) {
		supportCoefficient.setUserId(1);
		supportCoefficientService.update(supportCoefficient);
	}

	/**
	 * 逻辑删除支撑系数
	 * 
	 * @param id 课程标识(Integer----长度5)
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		supportCoefficientService.delete(id);
	}

	/**
	 * 批量逻辑删除支撑系数
	 * 
	 * @param id 课程标识(Integer----长度5)
	 * @return 成功删除的记录数
	 */
	@RequestMapping(value = "/deleteSelection", method = RequestMethod.POST)
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return supportCoefficientService.deleteSelection(idList);
	}

	/**
	 * 过滤查询
	 * 
	 * @param start    当前页(Integer----长度5)
	 * @param pageSize 页面大小(Integer----长度3,最大值为100)
	 * @return 支撑系数集合
	 */
	@RequestMapping(value = "/selectPageInfo", method = RequestMethod.POST)
	public PageInfo<SupportCoefficient> selectPageInfo(@RequestBody String str) {
		// 使用fastjson转为json对象
		JSONObject strj = JSON.parseObject(str);
		SupportCoefficient supportCoefficient = new SupportCoefficient();

//		String yearPlanId = strj.getString("yearPlanId");
		String courseInfoId = strj.getString("courseInfoId");
		// 过滤查询条件
		supportCoefficient.setCourseInfoId(StringUtil.isEmpty(courseInfoId) ? null : strj.getIntValue("courseInfoId"));
		// 当前页码
		int pageNum = strj.getIntValue("pageNum");
		// 页面大小
		int pageSize = strj.getIntValue("pageSize");
		return supportCoefficientService.selectPageInfo(supportCoefficient, pageNum, pageSize);
	}

}
