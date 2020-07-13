package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.SupportCoefficient;

import com.github.pagehelper.PageInfo;

public interface SupportCoefficientService {
	/**
	 * 插入支撑系数
	 * 
	 * @param SupportCoefficient 支撑系数类
	 */
	void insert(SupportCoefficient supportCoefficient);

	/**
	 * 更新支撑系数
	 * 
	 * @param SupportCoefficient 支撑系数类
	 */
	void update(SupportCoefficient supportCoefficient);

	/**
	 * 删除支撑系数
	 * 
	 * @param id 支撑系数标识
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除支撑系数
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 成功删除的记录数
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
	 * 分页条件查询
	 * 
	 * @param SupportCoefficient 需要作为查询条件的支撑系数类
	 * @param pageNum                    当前页索引
	 * @param pageSize                   设置分页参数
	 * @return PageInfo分页数据
	 */
	PageInfo<SupportCoefficient> selectPageInfo(SupportCoefficient supportCoefficient,
			Integer pageNum, Integer pageSize);
}
