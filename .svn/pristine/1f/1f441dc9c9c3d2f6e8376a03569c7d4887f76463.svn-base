package org.jit.sose.controller.manage;

import java.util.List;

import org.jit.sose.entity.YearTerm;
import org.jit.sose.service.YearTermService;
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
 * 学年控制类
 * 
 * @author  孔维国
 *
 */
@RestController
@RequestMapping("/manage/yearTerm")
public class YearTermController {
	@Autowired
	private YearTermService yearTermService;
	/**
	 * 查询任务id和任务名称集合
	 * 
	 * @return 任务id和任务名称集合
	 */
	@RequestMapping(value = "/selectYearTermList", method = RequestMethod.GET)
	public List<YearTerm> selectYearTermList() {
		List<YearTerm> yearTermList = yearTermService.selectYearTermList();
		return yearTermList;
	}
	/**
	 * 插入学年
	 * 
	 * @param termName 学年名称(String----长度200)
	 * @return
	 */
	@RequestMapping(value = "/insert")
	public void insert(@RequestBody YearTerm yearTerm) {
		yearTermService.insert(yearTerm);
	}

	/**
	 * 更新学年
	 * 
	 * @param termName 学年名称(String----长度200)
	 * @return
	 */
	@RequestMapping(value = "/update")
	public void update(@RequestBody YearTerm yearTerm) {
		yearTermService.update(yearTerm);
	}

	/**
	 * 逻辑删除学年
	 * 
	 * @param id 学年标识
	 * @return map
	 */
	@RequestMapping(value = "/delete")
	public void delete(@RequestBody Integer id) {
		yearTermService.delete(id);
	}

	/**
	 * 批量逻辑删除学年
	 * 
	 * @param id 学年标识(Integer----长度5)
	 * @return 成功删除的记录数
	 */
	@RequestMapping(value = "/deleteSelection", method = RequestMethod.POST)
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return yearTermService.deleteSelection(idList);
	}

	/**
	 * 过滤查询
	 * 
	 * @param pageNum  当前页(Integer----长度5)
	 * @param pageSize 页面大小(Integer----长度3,最大值为100)
	 * @param termName   学年名称 (String----长度200)
	 * @return 学年集合
	 */
	@RequestMapping(value = "/selectPageInfo", method = RequestMethod.POST)
	public PageInfo<YearTerm> filter(@RequestBody String str) {
		// 使用fastjson转为json对象
		JSONObject strj = JSON.parseObject(str);
		YearTerm yearTerm = new YearTerm();

		// 过滤查询条件
		String termName = strj.getString("termName");
		yearTerm.setTermName(StringUtil.isEmpty(termName) ? null : termName);

		// 当前页码
		int pageNum = strj.getIntValue("pageNum");
		// 页面大小
		int pageSize = strj.getIntValue("pageSize");
		return yearTermService.selectPageInfo(yearTerm, pageNum, pageSize);
	}
}
