package org.jit.sose.controller.config;

import java.util.List;
import java.util.Map;

import org.jit.sose.entity.TableTitle;
import org.jit.sose.service.TableTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

/**
 * 表格表头控制层
 * 
 * 
 * @date: 2019年4月29日 下午3:00:26
 */
@RestController
@RequestMapping("/config/tableTitle")
public class TableTitleController {

	@Autowired
	private TableTitleService tableTitleService;

	/**
	 * 通过数据库表名获取表头信息
	 * 
	 * @author: 王越
	 * @param tableName
	 *            数据库表名
	 * @return (key:表头key) <br>
	 *         (title:表头名称)
	 */
	@RequestMapping("/listByTableName")
	public Map<String, Object> listByTableName(@RequestBody String str) {
		JSONObject strj = JSON.parseObject(str);
		String tableName = strj.getString("tableName");
		return tableTitleService.listByTableName(tableName);
	}

	/**
	 * 查询表头列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectTableTitleList", method = RequestMethod.GET)
	public List<TableTitle> selectTableTitleList() {
		return tableTitleService.selectTableTitleList();
	}

	/**
	 * 过滤查找
	 * 
	 * @param tableTitle
	 *            要查询的信息集合
	 * @return 过滤查询得到信息的集合
	 */
	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public PageInfo<TableTitle> filter(@RequestBody String str) {
		JSONObject strj = JSON.parseObject(str);
		TableTitle tableTitle = new TableTitle();
		// 过滤查询条件
		tableTitle.setTableName("".equals(strj.getString("tableName")) ? null : strj.getString("tableName"));
		// 当前页索引
		Integer pageNum = strj.getIntValue("pageNum");
		// 当前页页面大小
		Integer pageSize = strj.getIntValue("pageSize");

		return tableTitleService.selectById(tableTitle, pageNum, pageSize);
	}

	/**
	 * 添加新表头项
	 * 
	 * @param tableTitle
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insert(@RequestBody TableTitle tableTitle) {

		tableTitleService.insert(tableTitle);
	}

	/**
	 * 更新表头项
	 * 
	 * @param tableTitle
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody TableTitle tableTitle) {

		tableTitleService.update(tableTitle);
	}

	/**
	 * 删除表头项
	 * 
	 * @param tableTitle
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {

		tableTitleService.delete(id);

	}

	/**
	 * 批量删除表头项
	 * 
	 * @param tableTitle
	 */
	@RequestMapping(value = "/deleteSelection", method = RequestMethod.POST)
	public void deleteSelection(@RequestBody List<String> idList) {
		tableTitleService.deleteSelection(idList);
	}
}
