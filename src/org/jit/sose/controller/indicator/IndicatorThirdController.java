package org.jit.sose.controller.indicator;

import java.util.List;

import org.jit.sose.entity.IndicatorThird;
import org.jit.sose.service.IndicatorThirdService;
import org.jit.sose.util.DataExceptionUtil;
import org.jit.sose.util.ResponseUtil;
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
 * 毕业要求三级指标控制器类
 * 
 * @author 单爽
 *
 */

@RestController
@RequestMapping("/indicator/indicatorThird")
public class IndicatorThirdController {
	@Autowired
	private IndicatorThirdService indicatorThirdService;

	/**
	 * 查询毕业要求三级指标和id集合
	 * 
	 * @return 毕业要求三级指标和id集合
	 */
	@RequestMapping(value = "/selectIndicatorThirdList", method = RequestMethod.GET)
	public List<IndicatorThird> selectIndicatorThirdList() {
		return indicatorThirdService.selectIndicatorThirdList();
	}

	/**
	 * 插入毕业要求三级指标
	 * 
	 * @param content 内容 (varchar----长度2000)
	 * @param seq     序号(int----长度2)
	 * @param userId  状态(int----长度2)
	 * @return success/fail
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@RequestBody IndicatorThird indicatorThird) {
		// 设置用户id
		indicatorThird.setUserId(1);
		// 验证数据格式是否正确==========未写好
		// 验证数据是否为空
		// 验证数据长度

		// 调用添加数据接口
		String result=indicatorThirdService.insert(indicatorThird);
		return ResponseUtil.success(result);
	}

	/**
	 * 更新毕业要求三级指标
	 * 
	 * @param content 内容 (varchar----长度2000)
	 * @param seq     序号(int----长度2)
	 * @param userId  用户标识(int----长度2)
	 * @return success/fail
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@RequestBody IndicatorThird indicatorThird) {	
		// @RequestBody 自动根据参数类型转换值
		// 验证数据是否合法
		indicatorThird.setUserId(1);
		// 更新操作
		String result=indicatorThirdService.update(indicatorThird);
		return ResponseUtil.success(result);
	}

	/**
	 * 逻辑删除毕业要求三级指标标识
	 * 
	 * @param id 毕业要求三级指标标识标识(Integer----长度5)
	 * @return map
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		indicatorThirdService.delete(id);
	}

	/**
	 * 批量逻辑删除毕业要求三级指标
	 * 
	 * @param id 毕业要求三级指标标识标识(Integer----长度5)
	 * @return 成功删除的记录数
	 */
	@RequestMapping(value = "/deleteSelection", method = RequestMethod.POST)
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return indicatorThirdService.deleteSelection(idList);
	}

	/**
	 * 过滤查询
	 * 
	 * @param start    当前页(Integer----长度5)
	 * @param pageSize 页面大小(Integer----长度3,最大值为100)
	 * @param content  内容 (varchar----长度2000)
	 * @return 毕业要求三级指标集合
	 */
	@RequestMapping(value = "/selectPageInfo", method = RequestMethod.POST)
	public PageInfo<IndicatorThird> selectPageInfo(@RequestBody String str) {
		// 使用fastjson转为json对象
		JSONObject strj = JSON.parseObject(str);
		IndicatorThird indicatorThird = new IndicatorThird();
		// 过滤查询条件
		String content = strj.getString("content");

		indicatorThird.setContent(StringUtil.isEmpty(content) ? null : content);

		// 当前页码
		int pageNum = strj.getIntValue("pageNum");
		// 页面大小
		int pageSize = strj.getIntValue("pageSize");
		return indicatorThirdService.selectPageInfo(indicatorThird, pageNum, pageSize);
	}

}
