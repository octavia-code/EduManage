package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.CourseInfo;
import org.jit.sose.entity.TblHeadList;

import com.github.pagehelper.PageInfo;

public interface TblHeadListService {
	/**
	 * 插入表头信息
	 * 
	 * @param TblHeadList 表头信息类
	 * @return success/fail
	 */
	void insert(TblHeadList tblheadList);

	/**
	 * 更新课程信息
	 * 
	 * @param TblHeadList 表头信息类
	 * @return success/fail
	 */
	void update(TblHeadList tblheadList);

	/**
	 * 删除课程信息
	 * 
	 * @param id 表头信息标识
	 * @return success/fail
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除表头信息
	 * 
	 * @param idList 需要删除的id的集合
	 * @return success/fail
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 根据表头信息标识查询课程信息
	 * 
	 * @param id 课程标识
	 * @return 课程信息类
	 */
	TblHeadList selectById(Integer id);

	/**
	 * 分页条件查询
	 * 
	 * @param tblHeadList 需要作为查询条件的课程信息类
	 * @param pageNum    当前页索引
	 * @param pageSize   设置分页参数
	 * @return PageInfo分页数据
	 */
	PageInfo<TblHeadList> selectPageInfo(TblHeadList tblHeadList, Integer pageNum, Integer pageSize);
}
