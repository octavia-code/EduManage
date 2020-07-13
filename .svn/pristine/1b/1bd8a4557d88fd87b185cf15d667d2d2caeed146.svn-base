package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.YearPlan;

import com.github.pagehelper.PageInfo;

public interface YearPlanService {
	/**
	 * 插入年份信息
	 * 
	 * @param yearPlan 年份类
	 */
	void insert(YearPlan yearPlan);
	
	/**
	 * 更新年份信息
	 * 
	 * @param yearPlan 年份类
	 * @return 受影响的行数
	 */
	void update(YearPlan yearPlan);
	/**
	 * 逻辑删除年份信息
	 * 
	 * @param id 年份信息标识
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
	 * 根据年份标识查询年份信息
	 * 
	 * @param id 年份标识
	 * @return 年份类
	 */
	YearPlan selectById(Integer id);

	/**
	 * 分页条件查询
	 * 
	 * @param yearPlan  需要作为查询条件的类
	 * @param pageNum  当前页索引
	 * @param pageSize 设置分页参数
	 * @return PageInfo分页数据
	 */
	PageInfo<YearPlan> selectPageInfo(YearPlan yearPlan, Integer pageNum, Integer pageSize);
	
	/**
	 * 查询任务id和任务名称集合
	 * 
	 * @return 任务id和任务名称集合
	 * @author 牛开专
	 */
	List<YearPlan> selectYearPlanList();
}
