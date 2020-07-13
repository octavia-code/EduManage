package org.jit.sose.controller.indicator;

import java.util.List;

import org.jit.sose.entity.IndicatorSec;
import org.jit.sose.service.IndicatorSecService;
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
 * 二级指标信息控制器类
 * 
 * @author 任志杰
 */
@RestController
@RequestMapping("/indicator/indicatorSec")
public class IndicatorSecController {

	@Autowired
	private IndicatorSecService indicatorSecService;

	/**
	 * 查询二级指标和id集合
	 * 
	 * @return 二级指标和id集合
	 */
	@RequestMapping(value = "/selectIndicatorSecList", method = RequestMethod.GET)
	public List<IndicatorSec> selectIndicatorSecList() {
		return indicatorSecService.selectIndicatorSecList();
	}

	/**
	 * 插入指标信息
	 * 
	 * @param yearPlanId       年份标识(是Integer----长度2)
	 * @param indicatorFirstId 一级指标标识(Integer----长度2)
	 * @param content          详情内容(String----长度2000)
	 * @param userId           用户标识(Integer----长度2)
	 * @param seq              序号(Integer----长度2)
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insert(@RequestBody IndicatorSec indicatorSec) {
		// 暂设置用户id为1
		indicatorSec.setUserId(1);
		// 调用添加数据接口
		indicatorSecService.insert(indicatorSec);
	}

	/**
	 * 更新指标信息
	 * 
	 * @param id               指标信息标识(Integer----长度4)
	 * @param indicatorFirstId 一级指标标识(Integer----长度2)
	 * @param yearPlanId       年份标识(是Integer----长度2)
	 * @param content          详情内容(String----长度2000)
	 * @param userId           用户标识(Integer----长度2)
	 * @param seq              序号(Integer----长度2)
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody IndicatorSec indicatorSec) {
		// 暂设置用户id为1
		indicatorSec.setUserId(1);
		// 更新操作
		indicatorSecService.update(indicatorSec);
	}

	/**
	 * 逻辑删除指标信息
	 * 
	 * @param id 指标信息标识(Integer----长度4)
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		indicatorSecService.delete(id);
	}

	/**
	 * 批量逻辑删除指标信息
	 * 
	 * @param id 指标标识(Integer----长度5)
	 * @return 成功删除的记录数
	 */
	@RequestMapping(value = "/deleteSelection", method = RequestMethod.POST)
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return indicatorSecService.deleteSelection(idList);
	}

	/**
	 * 过滤查询
	 * 
	 * @param pageNum          当前页(Integer----长度5)
	 * @param pageSize         页面大小(Integer----长度3,最大值为100)
	 * @param yearPlanId       标题
	 * @param indicatorFirstId 年份
	 * @return 二级指标信息集合
	 */
	@RequestMapping(value = "/selectPageInfo", method = RequestMethod.POST)
	public PageInfo<IndicatorSec> selectPageInfo(@RequestBody String str) {
		// 使用fastjson转为json对象
		JSONObject strj = JSON.parseObject(str);
		IndicatorSec indicatorSec = new IndicatorSec();

		// 过滤查询条件
		indicatorSec.setYearPlanId(
				StringUtil.isEmpty(strj.getString("yearPlanId")) ? null : strj.getIntValue("yearPlanId"));
		indicatorSec.setIndicatorFirstId(
				StringUtil.isEmpty(strj.getString("indicatorFirstId")) ? null : strj.getIntValue("indicatorFirstId"));

		// 当前页码
		int pageNum = strj.getIntValue("pageNum");
		// 页面大小
		int pageSize = strj.getIntValue("pageSize");
		return indicatorSecService.selectPageInfo(indicatorSec, pageNum, pageSize);
	}

}
