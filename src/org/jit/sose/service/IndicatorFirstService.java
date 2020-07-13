package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.IndicatorFirst;

import com.github.pagehelper.PageInfo;

public interface IndicatorFirstService {
	
	/**
	 * 根据年份查询一级指标标题
	 * 
	 * @return 一级指标标题和yearPlanId集合
	 */
	List<IndicatorFirst> selectIndicatorFirstListByYearPlanId(Integer yearPlanId);
	
	/**
	 * 查询一级指标和id集合
	 * 
	 * @return 一级指标和id集合
	 */
	List<IndicatorFirst> selectIndicatorFirstList();

	/**
	 * 添加一级指标
	 * 
	 * @param indicatorFirst 一级指标类
	 */
	void insert(IndicatorFirst indicatorFirst);

	/**
	 * 更新一级指标
	 * 
	 * @param indicatorFirst 一级指标类
	 */
	void update(IndicatorFirst indicatorFirst);

	/**
	 * 删除一级指标
	 * 
	 * @param id 一级指标标识
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除课程信息
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 成功删除的记录数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 根据一级指标标识查询一级指标
	 * 
	 * @param id 一级指标标识
	 * @return 一级指标类
	 */
	IndicatorFirst selectById(Integer id);

	/**
	 * 分页条件查询
	 * 
	 * @param indicatorFirst 需要作为查询条件的一级指标类
	 * @param pageNum        当前页索引
	 * @param pageSize       设置分页参数
	 * @return PageInfo 分页数据
	 */
	PageInfo<IndicatorFirst> selectPageInfo(IndicatorFirst indicatorFirst, Integer pageNum, Integer pageSize);

}
