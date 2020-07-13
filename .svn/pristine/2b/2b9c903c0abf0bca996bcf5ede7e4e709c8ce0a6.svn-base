package org.jit.sose.controller;

import java.util.List;
import org.jit.sose.entity.TblHeadList;
import org.jit.sose.service.TblHeadListService;
import org.jit.sose.util.DataExceptionUtil;
import org.jit.sose.util.StringUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 表头信息控制器类
 * 
 * @author wr
 *
 */
@RestController
@RequestMapping("/tblHeadList")
public class TblHeadListController {

	@Autowired
	private TblHeadListService tblHeadListService;

	/**
	 * 插入表头信息
	 * 
	 * @param planId     教学计划表标识 (Integer----长度3)
	 * @param colName    列展示名(String----长度200)
	 * @param colNbr     列字段(Integer----长度2)
	 * @param rowNbr     列长度(Integer----长度2)
	 * @param isEmpty    列是否为空(Tinyint----长度1)
	 * @param colStart   列开始号(Integer----长度2)
	 * @param colEnd     列终止号(Integer----长度2)
	 * @param rowStart   行开始号(Integer----长度2)
	 * @param rowEnd     行终止号(String----长度2)
	 * @return success/fail
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insert(@RequestBody TblHeadList tblHeadList) {
		// 验证数据格式是否正确==========未写好

		// 验证数据是否为空
		DataExceptionUtil.stringEmpty(tblHeadList.getCourseName(), "课程名不能为空");
		// 验证数据长度
		DataExceptionUtil.stringEmpty(tblHeadList.getColName(), "列展示名不能为空");

		// 调用添加数据接口
		tblHeadListService.insert(tblHeadList);
	}

	/**
	 * 更新课程信息
	 * 
	 * @param id         课程标识(Integer----长度5)
	 * @param planId     教学计划表标识 (Integer----长度3)
	 * @param colName    列展示名(String----长度200)
	 * @param colNbr     列字段(Integer----长度2)
	 * @param rowNbr     列长度(Integer----长度2)
	 * @param isEmpty    列是否为空(Tinyint----长度1)
	 * @param colStart   列开始号(Integer----长度2)
	 * @param colEnd     列终止号(Integer----长度2)
	 * @param rowStart   行开始号(Integer----长度2)
	 * @param rowEnd     行终止号(String----长度2)
	 * @return success/fail
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody TblHeadList tblHeadList) {
		// @RequestBody 自动根据参数类型转换值
		// 验证数据是否合法

		// 更新操作
		tblHeadListService.update(tblHeadList);
	}

	/**
	 * 逻辑删除课程信息
	 * 
	 * @param id 课程标识(Integer----长度5)
	 * @return map
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		tblHeadListService.delete(id);
	}

	/**
	 * 批量逻辑删除表头信息
	 * 
	 * @param id 标识(Integer----长度5)
	 * @return map
	 */
	@RequestMapping(value = "/deleteSelection", method = RequestMethod.POST)
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return tblHeadListService.deleteSelection(idList);
	}

	/**
	 * 过滤查询
	 * 
	 * @param start      当前页(Integer----长度5)
	 * @param pageSize   页面大小(Integer----长度3,最大值为100)
	 * @param colName    列展示名 (String----长度20)
	 * @param courseName 课程名 (String----长度20)
	 * @return 课程信息集合
	 */
	@RequestMapping(value = "/selectPageInfo", method = RequestMethod.POST)
	public PageInfo<TblHeadList> selectPageInfo(@RequestBody String str) {
		// 使用fastjson转为json对象
		com.alibaba.fastjson.JSONObject strj = JSON.parseObject(str);
		TblHeadList tblHeadList = new TblHeadList();

		// 过滤查询条件
		String colName = strj.getString("colName");
		String courseName = strj.getString("courseName");
		tblHeadList.setColName(StringUtil.isEmpty(colName) ? null : colName);
		tblHeadList.setCourseName(StringUtil.isEmpty(courseName) ? null : courseName);

		// 当前页码
		int pageNum = strj.getIntValue("pageNum");
		// 页面大小
		int pageSize = strj.getIntValue("pageSize");
		return tblHeadListService.selectPageInfo(tblHeadList, pageNum, pageSize);
	}

}
