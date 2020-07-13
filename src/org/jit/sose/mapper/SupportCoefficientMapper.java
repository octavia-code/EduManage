package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.entity.SupportCoefficient;

public interface SupportCoefficientMapper {
	/**
	 * 逻辑删除支撑系数
	 * 
	 * @param id 支撑系数标识
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除支撑系数
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 受影响行数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 根据支撑系数标识查询支撑系数
	 * 
	 * @param id 课程标识
	 * @return 支撑系数类
	 */
	SupportCoefficient selectById(Integer id);

	/**
	 * 插入支撑系数
	 * 
	 * @param courseInfo 支撑系数
	 */
	void insert(SupportCoefficient supportCoefficient);

	/**
	 * 更新支撑系数
	 * 
	 * @param courseInfo 支撑系数类
	 */
	void update(SupportCoefficient supportCoefficient);

	/**
	 * 过滤查询支撑系数
	 * 
	 * @param courseInfo 支撑系数类
	 * @return 支撑系数集合
	 */
	List<SupportCoefficient> listBySupportCoefficient(SupportCoefficient supportCoefficient);
}