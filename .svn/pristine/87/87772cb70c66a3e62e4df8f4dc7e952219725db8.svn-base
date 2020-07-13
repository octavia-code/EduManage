package org.jit.sose.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jit.sose.entity.GraduationRequireIndicator;
import org.jit.sose.entity.IndicatorFirst;
import org.jit.sose.entity.IndicatorRelat;
import org.jit.sose.entity.IndicatorSec;
import org.jit.sose.entity.query.IndicatorRelatQuery;

public interface IndicatorRelatMapper {

	/**
	 * 查询毕业要求指标点标题
	 * 
	 */
	List<IndicatorRelat> selectIndicatorList(IndicatorRelatQuery query);

	/**
	 * 根据毕业要求指标点标识查询一级指标
	 * 
	 */
	List<IndicatorRelat> selectIndicatorFirstList(IndicatorRelatQuery query);

	/**
	 * 根据毕业要求指标点标识和一级指标查询二级指标
	 * 
	 */
	List<IndicatorRelat> selectIndicatorSecList(IndicatorRelatQuery query);

	/**
	 * 查询在指标关联表中未使用的指标点集合
	 * 
	 * @return
	 */
	List<GraduationRequireIndicator> selectNotUseIndicatorList();

	/**
	 * 查询在指标关联表中未使用的一级指标集合<br>
	 * 根据指标点查询相应年份
	 * 
	 * @param indicatorId 指标点id
	 * @return
	 */
	List<IndicatorFirst> selectNotUseIndicatorFirstList(Integer indicatorId);

	/**
	 * 查询在指标关联表中未使用的二级指标集合<br>
	 * 根据指标点查询相应年份
	 * 
	 * @return
	 */
	List<IndicatorSec> selectNotUseIndicatorSecList(@Param("indicatorId") Integer indicatorId,
			@Param("indicatorFirstId") Integer indicatorFirstId);

	/**
	 * 逻辑删除指标关联，根据标题id，一级指标id，二级指标id
	 * 
	 * @param indicatorRelat
	 * @return 成功删除的记录
	 */
	Integer delete(IndicatorRelat indicatorRelat);

	/**
	 * 插入指标关联
	 * 
	 * @param indicatorRelat 指标关联
	 */
	void insert(IndicatorRelat indicatorRelat);

	/**
	 * 根据指标关联标识查询指标关联
	 * 
	 * @param id 课程标识
	 * @return 指标关联类
	 */
	IndicatorRelat selectById(Integer id);

	/**
	 * 更新指标关联
	 * 
	 * @param indicatorRelat 指标关联类
	 */
	void update(IndicatorRelat indicatorRelat);

	/**
	 * 过滤查询指标关联
	 * 
	 * @param indicatorRelat 指标关联类
	 * @return 指标关联集合
	 */
	List<IndicatorRelat> listByIndicatorRelat(IndicatorRelat indicatorRelat);
}