package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.YearTerm;

import com.github.pagehelper.PageInfo;

public interface YearTermService {
	/**
	 * 插入学年信息
	 * 
	 * @param yearTerm 学年类
	 */
	void insert(YearTerm yearTerm);
	
	/**
	 * 更新学年信息
	 * 
	 * @param yearTerm 学年类
	 * @return 受影响的行数
	 */
	void update(YearTerm yearTerm);
	/**
	 * 逻辑删除学年信息
	 * 
	 * @param id 学年信息标识
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 受影响行数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 根据学年标识查询学年信息
	 * 
	 * @param id 学年标识
	 * @return 学年类
	 */
	YearTerm selectById(Integer id);

	/**
	 * 分页条件查询
	 * 
	 * @param yearTerm  需要作为查询条件的类
	 * @param pageNum  当前页索引
	 * @param pageSize 设置分页参数
	 * @return PageInfo分页数据
	 */
	PageInfo<YearTerm> selectPageInfo(YearTerm yearTerm, Integer pageNum, Integer pageSize);
	/**
	 * 查询学年列表
	 */
	List<YearTerm> selectYearTermList();
}
