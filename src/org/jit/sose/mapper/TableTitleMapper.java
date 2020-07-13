package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.entity.TableTitle;

public interface TableTitleMapper {
	/**
	 * 通过表名查询表头信息
	 * 
	 * @param tableName
	 *            数据库表名
	 * @return 表头集合信息
	 */
	List<TableTitle> listByTableName(String tableName);

	/**
	 * 查询表头列表
	 * 
	 * @return 表头集合
	 */
	List<TableTitle> selectTableTitleList();

	/**
	 * 新增表头项
	 * 
	 * @param tableTitle集合
	 */
	void insert(TableTitle tableTitle);

	/**
	 * 更新表头项
	 * 
	 * @param tableTitle集合
	 */
	void update(TableTitle tableTitle);

	/**
	 * 逻辑表头项
	 * 
	 * @param tableTitle集合
	 */
	void delete(Integer id);

	/**
	 * 逻辑批量表头项
	 * 
	 * @param 要删除的id集合
	 */
	void deleteSelection(List<String> idList);

	/**
	 * 过滤查询
	 * 
	 * @param tableTitle集合
	 * @return 表头集合
	 */
	List<TableTitle> selectById(TableTitle tableTitle);

}