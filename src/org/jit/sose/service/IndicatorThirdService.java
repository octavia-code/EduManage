package org.jit.sose.service;

import java.util.List;
import org.jit.sose.entity.IndicatorThird;

import com.github.pagehelper.PageInfo;

public interface IndicatorThirdService {

	/**
	 * 查询毕业要求三级指标和id集合
	 * 
	 * @return 毕业要求三级指标和id集合
	 */
	List<IndicatorThird> selectIndicatorThirdList();

	/**
	 * 插入毕业要求三级指标实体
	 * 
	 * @param indicatorThird 毕业要求三级指标实体类
	 */
	String insert(IndicatorThird indicatorThird);

	/**
	 * 更新毕业要求三级指标实体
	 * 
	 * @param indicatorThird 毕业要求三级指标实体类
	 */
	String update(IndicatorThird indicatorThird);

	/**
	 * 删除毕业要求三级指标实体
	 * 
	 * @param id 毕业要求三级指标实体标识
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除毕业要求三级指标实体
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 成功删除的记录数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 根据课程信息标识查询毕业要求三级指标实体
	 * 
	 * @param id 毕业要求三级指标实体
	 * @return 毕业要求三级指标实体类
	 */
	IndicatorThird selectById(Integer id);

	/**
	 * 分页条件查询
	 * 
	 * @param indicatorThird 需要作为查询条件的毕业要求三级指标类
	 * @param pageNum        当前页索引
	 * @param pageSize       设置分页参数
	 * @return PageInfo分页数据
	 */
	PageInfo<IndicatorThird> selectPageInfo(IndicatorThird indicatorThird, Integer pageNum, Integer pageSize);

}
