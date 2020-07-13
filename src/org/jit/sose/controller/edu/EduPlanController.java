package org.jit.sose.controller.edu;

import java.util.List;

import org.jit.sose.entity.EduPlan;
import org.jit.sose.service.EduPlanService;
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
 * 教学计划控制类
 * 
 * @author 王越
 *
 */
@RestController
@RequestMapping("/edu/eduPlan")
public class EduPlanController {
	@Autowired
	private EduPlanService eduPlanService;

	/**
	 * 插入教学计划
	 * 
	 * @param epName 教学计划名称(String----长度200)
	 * @param userId 用户标识(Integer----长度2)
	 * @return
	 */
	@RequestMapping(value = "/insert")
	public void insert(@RequestBody EduPlan eduPlan) {
		eduPlan.setUserId(1);
		eduPlanService.insert(eduPlan);
	}

	/**
	 * 更新教学计划
	 * 
	 * @param epName 教学计划名称(String----长度200)
	 * @param userId 用户标识(Integer----长度2)
	 * @return
	 */
	@RequestMapping(value = "/update")
	public void update(@RequestBody EduPlan eduPlan) {
		eduPlan.setUserId(1);
		eduPlanService.update(eduPlan);
	}

	/**
	 * 逻辑删除教学计划
	 * 
	 * @param id 教学计划标识
	 * @return map
	 */
	@RequestMapping(value = "/delete")
	public void delete(@RequestBody Integer id) {
		eduPlanService.delete(id);
	}

	/**
	 * 批量逻辑删除教学计划
	 * 
	 * @param id 课程标识(Integer----长度5)
	 * @return 成功删除的记录数
	 */
	@RequestMapping(value = "/deleteSelection", method = RequestMethod.POST)
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return eduPlanService.deleteSelection(idList);
	}

	/**
	 * 过滤查询
	 * 
	 * @param pageNum  当前页(Integer----长度5)
	 * @param pageSize 页面大小(Integer----长度3,最大值为100)
	 * @param epName   教学计划名称 (String----长度200)
	 * @return 教学计划集合
	 */
	@RequestMapping(value = "/selectPageInfo", method = RequestMethod.POST)
	public PageInfo<EduPlan> selectPageInfo(@RequestBody String str) {
		// 使用fastjson转为json对象
		JSONObject strj = JSON.parseObject(str);
		EduPlan eduPlan = new EduPlan();

		// 过滤查询条件
		eduPlan.setEpName(StringUtil.isEmpty(strj.getString("epName")) ? null : strj.getString("epName"));
		eduPlan.setYearPlanId(StringUtil.isEmpty(strj.getString("yearPlanId")) ? null : strj.getIntValue("yearPlanId"));
		eduPlan.setSchoolInfoId(
				StringUtil.isEmpty(strj.getString("schoolInfoId")) ? null : strj.getIntValue("schoolInfoId"));
		eduPlan.setSpecialtyId(
				StringUtil.isEmpty(strj.getString("specialtyId")) ? null : strj.getIntValue("specialtyId"));
		eduPlan.setCoursePropId(
				StringUtil.isEmpty(strj.getString("coursePropId")) ? null : strj.getIntValue("coursePropId"));
		eduPlan.setCourseTypeId(
				StringUtil.isEmpty(strj.getString("courseTypeId")) ? null : strj.getIntValue("courseTypeId"));
		eduPlan.setCourseInfoId(
				StringUtil.isEmpty(strj.getString("courseInfoId")) ? null : strj.getIntValue("courseInfoId"));
		eduPlan.setTermInfoId(StringUtil.isEmpty(strj.getString("termInfoId")) ? null : strj.getIntValue("termInfoId"));

		// 当前页码
		int pageNum = strj.getIntValue("pageNum");
		// 页面大小
		int pageSize = strj.getIntValue("pageSize");
		return eduPlanService.selectPageInfo(eduPlan, pageNum, pageSize);
	}
}
