package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.entity.IndicatorThird;

public interface IndicatorThirdMapper {
	/**
	 * 查询毕业要求三级指标和id集合
	 * 
	 * @return 毕业要求三级指标和id集合
	 */
	List<IndicatorThird> selectIndicatorThirdList();

	/**
	 * 逻辑删除毕业要求三级指标
	 * 
	 * @param id 毕业要求三级指标标识
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除毕业要求三级指标
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 受影响行数
	 */

	Integer deleteSelection(List<Integer> idList);

	/**
	 * 插入毕业要求三级指标
	 * 
	 * @param IndicatorThird 毕业要求三级指标
	 */
	void insert(IndicatorThird indicatorThird);

	/**
	 * 根据课程信息标识查询毕业要求三级指标
	 * 
	 * @param id 毕业要求三级指标标识
	 * @return 毕业要求三级指标类
	 */

	IndicatorThird selectById(Integer id);

	/**
	 * 更新毕业要求三级指标
	 * 
	 * @param indicatorThird 毕业要求三级指标类
	 */
	void update(IndicatorThird indicatorThird);

	/**
	 * 过滤查询毕业要求三级指标
	 * 
	 * @param indicatorThird 毕业要求三级指标类
	 * @return 毕业要求三级指标集合
	 */
	List<IndicatorThird> listByIndicatorThird(IndicatorThird indicatorThird);
	
	/**
	 * 查询课程班级信息的记录数
	 * @param courseClassInfo
	 * @return
	 */
	Integer countByIndicatorThird(IndicatorThird indicatorThird);
}