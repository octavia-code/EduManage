package org.jit.sose.controller.manage;

import java.util.List;

import org.jit.sose.entity.YearPlan;
import org.jit.sose.service.YearPlanService;
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
 * 年份控制类
 * 
 * @author  徐国皓
 *
 */
@RestController
@RequestMapping("/manage/yearPlan")
public class YearPlanController {
	@Autowired
	private YearPlanService yearPlanService;
	/**
	 * 查询任务id和任务名称集合
	 * 
	 * @return 任务id和任务名称集合
	 * @author 牛开专
	 */
	@RequestMapping(value = "/selectYearPlanList", method = RequestMethod.GET)
	public List<YearPlan> selectYearPlanList() {
		List<YearPlan> yearPlanList = yearPlanService.selectYearPlanList();
		return yearPlanList;
	}
	/**
	 * 插入年份
	 * 
	 * @param yearName 年份名称(String----长度200)
	 * @return
	 */
	@RequestMapping(value = "/insert")
	public void insert(@RequestBody YearPlan yearPlan) {
		yearPlanService.insert(yearPlan);
	}

	/**
	 * 更新年份
	 * 
	 * @param yearName 年份名称(String----长度200)
	 * @return
	 */
	@RequestMapping(value = "/update")
	public void update(@RequestBody YearPlan yearPlan) {
		yearPlanService.update(yearPlan);
	}

	/**
	 * 逻辑删除年份
	 * 
	 * @param id 年份标识
	 * @return map
	 */
	@RequestMapping(value = "/delete")
	public void delete(@RequestBody Integer id) {
		yearPlanService.delete(id);
	}

	/**
	 * 批量逻辑删除年份
	 * 
	 * @param id 课程标识(Integer----长度5)
	 * @return 成功删除的记录数
	 */
	@RequestMapping(value = "/deleteSelection", method = RequestMethod.POST)
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return yearPlanService.deleteSelection(idList);
	}

	/**
	 * 过滤查询
	 * 
	 * @param pageNum  当前页(Integer----长度5)
	 * @param pageSize 页面大小(Integer----长度3,最大值为100)
	 * @param yearName   年份名称 (String----长度200)
	 * @return 年份集合
	 */
	@RequestMapping(value = "/selectPageInfo", method = RequestMethod.POST)
	public PageInfo<YearPlan> filter(@RequestBody String str) {
		// 使用fastjson转为json对象
		JSONObject strj = JSON.parseObject(str);
		YearPlan yearPlan = new YearPlan();

		// 过滤查询条件
		String yearName = strj.getString("yearName");
		yearPlan.setYearName(StringUtil.isEmpty(yearName) ? null : yearName);

		// 当前页码
		int pageNum = strj.getIntValue("pageNum");
		// 页面大小
		int pageSize = strj.getIntValue("pageSize");
		return yearPlanService.selectPageInfo(yearPlan, pageNum, pageSize);
	}
}
