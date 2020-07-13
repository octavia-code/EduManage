package org.jit.sose.service;

import java.util.List;
import java.util.Map;

import org.jit.sose.entity.TableTitle;

import com.github.pagehelper.PageInfo;

public interface TableTitleService {

	/**
	 * 通过表名查询表头信息
	 * 
	 * @param tableName
	 *            数据库表名
	 * @return 表头集合信息
	 */
	Map<String, Object> listByTableName(String tableName);

	/**
	 * 查询表头列表
	 * 
	 * @return 表头集合
	 */
	List<TableTitle> selectTableTitleList();

	/**
	 * 过滤查询
	 * 
	 * @param tableTitle
	 *            表头类
	 * @return 表头项集合
	 */
	PageInfo<TableTitle> selectById(TableTitle tableTitle, Integer pageNum, Integer pageSize);

	/**
	 * 新增表头项
	 * 
	 * @param tableTitle
	 *            表头类
	 */
	void insert(TableTitle tableTitle);

	/**
	 * 更新表头项
	 * 
	 * @param tableTitle
	 *            表头类
	 */
	void update(TableTitle tableTitle);

	/**
	 * 逻辑删除表头项
	 * 
	 * @param tableTitle
	 *            表头类
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除表头项
	 * 
	 * @param tableTitle
	 *            表头类
	 */
	void deleteSelection(List<String> idList);
}
