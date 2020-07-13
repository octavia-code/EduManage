package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.IndicatorSec;

import com.github.pagehelper.PageInfo;

public interface IndicatorSecService {
	
	/**
	 * 查询二级指标和id集合
	 * 
	 * @return 二级指标和id集合
	 */
	List<IndicatorSec> selectIndicatorSecList();
	
	/**
	 * 删除指标信息
	 * 
	 * @param id 指标信息标识
	 */
	void delete(Integer id);
	
	/**
	 * 批量逻辑删除指标信息
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 成功删除的记录数
	 */
	Integer deleteSelection(List<Integer> idList);
	
	/**
	 * 插入二级指标信息
	 * 
	 * @param indicatorSec 指标信息类
	 */
	void insert(IndicatorSec indicatorSec);
	
	/**
	 * 根据指标信息标识查询指标信息
	 * 
	 * @param id 指标标识
	 * @return 指标信息类
	 */
	IndicatorSec selectById(Integer id);
	
	/**
	 * 更新指标信息
	 * 
	 * @param indicatorSec 指标信息类
	 * @return 受影响的行数
	 */
	void update(IndicatorSec indicatorSec);

	/**
	 * 分页条件查询
	 * 
	 * @param indicatorSec 需要作为查询条件的指标信息类
	 * @param pageNum      当前页索引
	 * @param pageSize     设置分页参数
	 * @return PageInfo分页数据
	 */
	PageInfo<IndicatorSec> selectPageInfo(IndicatorSec indicatorSec, Integer pageNum, Integer pageSize);

}