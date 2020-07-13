package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.entity.TblHeadList;

public interface TblHeadListMapper {
	/**
	 * 逻辑删除表头信息
	 * 
	 * @param id 表头信息标识
	 * @return 受影响行数
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除课程信息
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 受影响行数
	 */
	Integer deleteSelection(List<Integer> idList);
	/**
	 * 插入表头信息
	 * 
	 * @param TblHeadList 表头信息
	 * @return 受影响行数
	 */
	void insert(TblHeadList tblheadList);

	/**
	 * 根据表头信息标识查询表头信息
	 * 
	 * @param id 表头标识
	 * @return 表头信息类
	 */
	TblHeadList selectById(Integer id);

	/**
	 * 更新表头信息
	 * 
	 * @param TblHeadList 表头信息
	 * @return 受影响行数
	 */
	void update(TblHeadList tblheadList);

	/**
	 * 过滤查询表头信息
	 * 
	 * @param TblHeadList 表头信息
	 * @return 表头信息集合
	 */
	List<TblHeadList> listByTblHeadList(TblHeadList tblheadList);
}