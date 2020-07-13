package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.entity.IndicatorFirst;

public interface IndicatorFirstMapper {

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
	 * 逻辑删除一级指标
	 * 
	 * @param id 一级指标标识
	 */
	void delete(Integer id);

	/**
	 * 批量删除一级指标
	 * 
	 * @param idList 需要删除的id的集合
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 插入一级指标
	 * 
	 * @param indicatorFirst 一级指标
	 * @return 受影响的行数
	 */
	void insert(IndicatorFirst indicatorFirst);

	/**
	 * 根据一级计划标识查询一级指标
	 * 
	 * @param id 一级指标标识
	 * @return 一级指标类
	 */
	IndicatorFirst selectById(Integer id);

	/**
	 * 更新一级指标
	 * 
	 * @param indicatorFirst 一级指标类
	 */
	void update(IndicatorFirst indicatorFirst);

	/**
	 * 过滤查询一级指标
	 * 
	 * @param indicatorFirst 一级指标类
	 * @return 一级指标集合
	 */
	List<IndicatorFirst> listByIndicatorFirst(IndicatorFirst indicatorFirst);
}