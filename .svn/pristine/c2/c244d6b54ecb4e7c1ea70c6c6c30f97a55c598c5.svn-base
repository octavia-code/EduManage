package org.jit.sose.controller.indicator;

import java.util.List;

import org.jit.sose.entity.CourseInfo;
import org.jit.sose.entity.GraduationRequireIndicator;
import org.jit.sose.service.GraduationRequireIndicatorService;
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
 * 毕业要求指标点控制器类
 * 
 * @author: 王越
 * @date: 2019-07-02 01:34:25
 */
@RestController
@RequestMapping("/indicator/graduation")
public class GraduationRequireIndicatorController {

	@Autowired
	private GraduationRequireIndicatorService gIndicatorService;
	
	/**
	 * 查询毕业要求指标点和id集合
	 * 
	 * @return 毕业要求指标点和id集合
	 */
	@RequestMapping(value = "/selectGraduationList", method = RequestMethod.GET)
	public List<GraduationRequireIndicator> selectGraduationList() {
		return gIndicatorService.selectGraduationList();
	}

	/**
	 * 新增记录
	 * 
	 * @param gIndicator
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insert(@RequestBody GraduationRequireIndicator gIndicator) {
		// 暂设置用户id为1
		gIndicator.setUserId(1);
		gIndicatorService.insert(gIndicator);
	}

	/**
	 * 修改记录
	 * 
	 * @param gIndicator
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody GraduationRequireIndicator gIndicator) {
		// 暂设置用户id为1
		gIndicator.setUserId(1);
		gIndicatorService.update(gIndicator);
	}

	/**
	 * 逻辑删除毕业要求指标点
	 * 
	 * @param id 课程标识(Integer----长度5)
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		gIndicatorService.delete(id);
	}
	
	/**
	 * 修改毕业指标点状态为"已提交"：state=‘A’
	 * 
	 * @param id 课程标识(Integer----长度5)
	 */
	@RequestMapping(value = "/admit", method = RequestMethod.POST)
	public void admit(@RequestBody Integer id) {
		gIndicatorService.admit(id);
	}

	/**
	 * 批量逻辑删除毕业要求指标点
	 * 
	 * @param id 课程标识(Integer----长度5)
	 * @return 成功删除的记录数
	 */
	@RequestMapping(value = "/deleteSelection", method = RequestMethod.POST)
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return gIndicatorService.deleteSelection(idList);
	}

	/**
	 * 过滤查询
	 * 
	 * @param start    当前页(Integer----长度5)
	 * @param pageSize 页面大小(Integer----长度3,最大值为100)
	 * @return 毕业要求指标点集合
	 */
	@RequestMapping(value = "/selectPageInfo", method = RequestMethod.POST)
	public PageInfo<GraduationRequireIndicator> selectPageInfo(@RequestBody String str) {
		// 使用fastjson转为json对象
		JSONObject strj = JSON.parseObject(str);
		GraduationRequireIndicator gIndicator = new GraduationRequireIndicator();

		// 过滤查询条件
		String title = strj.getString("title");
		String yearPlanId = strj.getString("yearPlanId");
		String specialtyId = strj.getString("specialtyId");
		gIndicator.setTitle(StringUtil.isEmpty(title) ? null : title);
		gIndicator.setYearPlanId(StringUtil.isEmpty(yearPlanId) ? null : strj.getIntValue("yearPlanId"));
		gIndicator.setSpecialtyId(StringUtil.isEmpty(specialtyId) ? null : strj.getIntValue("specialtyId"));
//		try {
//			gIndicator.setYearPlanId(strj.getIntValue("yearPlanId"));
//		} catch (Exception e) {
//			gIndicator.setYearPlanId(null);
//		}

//		try {
//			gIndicator.setSpecialtyId(strj.getIntValue("specialtyId"));
//		} catch (Exception e) {
//			gIndicator.setSpecialtyId(null);
//		}

		// 当前页码
		int pageNum = strj.getIntValue("pageNum");
		// 页面大小
		int pageSize = strj.getIntValue("pageSize");
		return gIndicatorService.selectPageInfo(gIndicator, pageNum, pageSize);
	}

}
