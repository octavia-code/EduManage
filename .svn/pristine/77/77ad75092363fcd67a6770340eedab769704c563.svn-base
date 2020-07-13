package org.jit.sose.controller.indicator;

import java.util.List;
import org.jit.sose.entity.IndicatorFirst;
import org.jit.sose.service.IndicatorFirstService;
import org.jit.sose.util.DataExceptionUtil;
import org.jit.sose.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;

/**
 * 一级指标控制类
 * 
 * @author 王丹梦
 *
 */
@RestController
@RequestMapping("/indicator/indicatorFirst") // /模板名/类名
public class IndicatorFirstController {
	@Autowired
	private IndicatorFirstService indicatorFirstService;

	/**
	 * 根据年份查询一级指标标题
	 * 
	 * @return 一级指标标题和yearPlanId集合
	 */
	@RequestMapping(value = "/selectIndicatorFirstListByYearPlanId", method = RequestMethod.POST)
	public List<IndicatorFirst> selectIndicatorFirstListByYearPlanId(@RequestBody Integer yearPlanId) {
		List<IndicatorFirst> indicatorFirstList = indicatorFirstService.selectIndicatorFirstListByYearPlanId(yearPlanId);
		return indicatorFirstList;
	}

	/**
	 * 查询一级指标和id集合
	 * 
	 * @author 王越
	 * @return 一级指标和id集合
	 */
	@RequestMapping(value = "/selectIndicatorFirstList", method = RequestMethod.GET)
	public List<IndicatorFirst> selectIndicatorFirstList() {
		return indicatorFirstService.selectIndicatorFirstList();
	}

	/**
	 * 插入一级指标
	 * 
	 * @param title
	 *            一级指标要求
	 * @param content
	 *            一级指标要求内容
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insert(@RequestBody IndicatorFirst indicatorFirst) {
		// 设置用户id
		indicatorFirst.setUserId(1);
		// 验证数据是否为空
		DataExceptionUtil.stringEmpty(indicatorFirst.getTitle(), "一级指标不能为空");
		// 验证数据长度
		DataExceptionUtil.stringLength(indicatorFirst.getContent(), 200, "一级指标内容长度最多为200");
		// 调用添加数据接口
		indicatorFirstService.insert(indicatorFirst);
	}

	/**
	 * 更新一级指标
	 * 
	 * @param title
	 *            一级指标要求
	 * @param content
	 *            一级指标要求内容
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody IndicatorFirst indicatorFirst) {
		// 设置用户id
		indicatorFirst.setUserId(1);
		// 验证数据是否为空
		DataExceptionUtil.stringEmpty(indicatorFirst.getTitle(), "一级指标不能为空");
		// 验证数据长度
		DataExceptionUtil.stringLength(indicatorFirst.getContent(), 200, "一级指标内容长度最多为200");
		// 更新操作
		indicatorFirstService.update(indicatorFirst);
	}

	/**
	 * 逻辑删除一级指标
	 * 
	 * @param id
	 *            一级指标标识(Integer----长度5)
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		indicatorFirstService.delete(id);
	}

	/**
	 * 批量逻辑删除一级指标
	 * 
	 * @param id
	 *            一级指标标识(Integer----长度5)
	 * @return map
	 */
	@RequestMapping(value = "/deleteSelection", method = RequestMethod.POST)
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return indicatorFirstService.deleteSelection(idList);
	}

	/**
	 * 过滤查询
	 * 
	 * @param start
	 *            当前页(Integer----长度5)
	 * @param pageSize
	 *            页面大小(Integer----长度3,最大值为100)
	 * @param title
	 *            一级指标标题 (String----长度20)
	 * @param content
	 *            一级指标要求1名称 (String----长度20)
	 * @return 一级指标集合
	 */
	@RequestMapping(value = "/selectPageInfo", method = RequestMethod.POST)
	public PageInfo<IndicatorFirst> selectPageInfo(@RequestBody String str) {
		// 使用fastjson转为json对象
		JSONObject strj = JSON.parseObject(str);
		IndicatorFirst indicatorFirst = new IndicatorFirst();
		// 过滤查询条件
		String title = strj.getString("title");
		String content = strj.getString("content");
		String yearPlanId = strj.getString("yearPlanId");
		indicatorFirst.setYearPlanId(StringUtil.isEmpty(yearPlanId) ? null : strj.getInteger("yearPlanId"));
		indicatorFirst.setTitle(StringUtil.isEmpty(title) ? null : title);
		indicatorFirst.setContent(StringUtil.isEmpty(content) ? null : content);
		// 当前页码
		int pageNum = strj.getIntValue("pageNum");
		// 页面大小
		int pageSize = strj.getIntValue("pageSize");
		return indicatorFirstService.selectPageInfo(indicatorFirst, pageNum, pageSize);
	}

}
