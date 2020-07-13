package org.jit.sose.mapper;

import java.util.List;
import org.jit.sose.entity.YearPlan;

public interface YearPlanMapper {
	/**
	 * 查询任务id和任务名称集合
	 * 
	 * @return 任务id和任务名称集合
	 * @author 牛开专
	 */
	List<YearPlan> selectYearPlanList();
	
	/**
	 * 逻辑删除年份计划信息
	 * 
	 * @param id
	 *            年份计划信息标识
	 */
    void delete(Integer id);
    /**
	 * 批量逻辑删除课程信息
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 受影响行数
	 */
    Integer deleteSelection(List<Integer> idList);

	/**
	 * 插入年份计划信息
	 * 
	 * @param yearPlan
	 *            年份计划类
	 * @return 受影响的行数
	 */
    void insert(YearPlan yearPlan);
	/**
	 * 根据年份计划标识查询年份信息
	 * 
	 * @param id
	 *            年份计划标识
	 * @return 年份计划类
	 */
    YearPlan selectById(Integer id);
	/**
	 * 更新年份计划信息
	 * 
	 * @param yearPlan
	 *            年份计划类
	 * @return 受影响的行数
	 */
    void update(YearPlan yearPlan);
	/**
	 * 过滤查询年份计划信息
	 * 
	 * @param yearPlan
	 *            年份计划类
	 * @return 教学计划集合
	 */
	List<YearPlan> listByYearPlan(YearPlan yearPlan);
}