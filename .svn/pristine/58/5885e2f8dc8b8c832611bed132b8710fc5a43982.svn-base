package org.jit.sose.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jit.sose.entity.GraduationRequireIndicator;
import org.jit.sose.entity.IndicatorFirst;
import org.jit.sose.entity.IndicatorRelat;
import org.jit.sose.entity.IndicatorSec;
import org.jit.sose.entity.query.IndicatorRelatQuery;
import org.jit.sose.entity.vo.RelatTreeVo;

import com.github.pagehelper.PageInfo;

public interface IndicatorRelatService {

	/**
	 * 查询毕业要求指标点标题
	 * 
	 * @return
	 */
	List<RelatTreeVo> selectIndicatorListTree(IndicatorRelatQuery query);

	/**
	 * 查询在指标关联表中未使用的指标点集合
	 * 
	 * @return
	 */
	List<GraduationRequireIndicator> selectNotUseIndicatorList();

	/**
	 * 查询在指标关联表中未使用的一级指标集合
	 * 
	 * @return
	 */
	List<IndicatorFirst> selectNotUseIndicatorFirstList(Integer indicatorId);

	/**
	 * 查询在指标关联表中未使用的二级指标集合
	 * 
	 * @return
	 */
	List<IndicatorSec> selectNotUseIndicatorSecList(@Param("indicatorId") Integer indicatorId,
			@Param("indicatorFirstId") Integer indicatorFirstId);

	/**
	 * 插入指标关联
	 * 
	 * @param IndicatorRelat 指标关联类
	 */
	void insert(IndicatorRelat indicatorRelat);

	/**
	 * 更新指标关联
	 * 
	 * @param IndicatorRelat 指标关联类
	 */
	void update(IndicatorRelat indicatorRelat);

	/**
	 * 逻辑删除指标关联，根据标题id，一级指标id，二级指标id
	 * 
	 * @param indicatorRelat
	 * @return 成功删除的记录
	 */
	Integer delete(IndicatorRelat indicatorRelat);

	/**
	 * 根据指标关联标识查询指标关联
	 * 
	 * @param id 课程标识
	 * @return 指标关联类
	 */
	IndicatorRelat selectById(Integer id);

	/**
	 * 分页条件查询
	 * 
	 * @param IndicatorRelat 需要作为查询条件的指标关联类
	 * @param pageNum        当前页索引
	 * @param pageSize       设置分页参数
	 * @return PageInfo分页数据
	 */
	PageInfo<IndicatorRelat> selectPageInfo(IndicatorRelat indicatorRelat, Integer pageNum, Integer pageSize);
}
