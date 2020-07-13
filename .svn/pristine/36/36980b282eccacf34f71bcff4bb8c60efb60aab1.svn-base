package org.jit.sose.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jit.sose.entity.IndicatorSec;

public interface IndicatorSecMapper {

	/**
	 * 查询二级指标和id集合
	 * 
	 * @return 二级指标和id集合
	 */
	List<IndicatorSec> selectIndicatorSecList();

	/**
	 * 逻辑删除指标信息
	 * 
	 * @param id
	 *            指标信息标识
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除二级指标信息
	 * 
	 * @param idList
	 *            需要删除的id的集合
	 * @return 受影响行数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 插入二级指标信息
	 * 
	 * @param indicatorSec
	 *            指标信息
	 * @return 受影响行数
	 */
	void insert(IndicatorSec indicatorSec);

	/**
	 * 根据二级指标标识查询指标信息
	 * 
	 * @param id
	 *            指标标识
	 * @return 二级指标信息类
	 */
	IndicatorSec selectById(Integer id);

	/**
	 * 更新指标信息
	 * 
	 * @param courseInfo
	 *            指标信息类
	 */
	void update(IndicatorSec indicatorSec);

	/**
	 * 过滤查询指标信息
	 * 
	 * @param indicatorSec
	 *            指标信息类
	 * @return 指标信息集合
	 */
	List<IndicatorSec> listByIndicatorSec(@Param("yearPlanId") Integer yearPlanId,
			@Param("indicatorFirstId") Integer indicatorFirstId);
	
	}
