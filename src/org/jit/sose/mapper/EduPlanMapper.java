package org.jit.sose.mapper;

import java.util.List;
import org.jit.sose.entity.EduPlan;

public interface EduPlanMapper {
	/**
	 * 逻辑删除教学计划信息
	 * 
	 * @param id 教学计划信息标识
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
	 * 插入教学计划信息
	 * 
	 * @param eduPlan 教学计划类
	 */
	void insert(EduPlan eduPlan);

	/**
	 * 根据教学计划标识查询教学计划信息
	 * 
	 * @param id 教学计划标识
	 * @return 教学计划类
	 */
	EduPlan selectById(Integer id);

	/**
	 * 更新教学计划信息
	 * 
	 * @param eduPlan 教学计划类
	 * @return 受影响的行数
	 */
	void update(EduPlan eduPlan);

	/**
	 * 过滤查询教学计划信息
	 * 
	 * @param eduPlan 教学计划类
	 * @return 教学计划集合
	 */
	List<EduPlan> listByCourseInfo(EduPlan eduPlan);
}