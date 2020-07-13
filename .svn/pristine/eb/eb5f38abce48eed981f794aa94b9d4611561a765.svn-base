package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.entity.GraduationRequireIndicator;

public interface GraduationRequireIndicatorMapper {

	/**
	 * 查询毕业要求指标点和id集合
	 * 
	 * @return 毕业要求指标点和id集合
	 */
	List<GraduationRequireIndicator> selectGraduationList();

	/**
	 * 逻辑删除毕业要求指标点
	 * 
	 * @param id 毕业要求指标点标识
	 */
	void delete(Integer id);
	
	/**
	 * 修改毕业指标点状态为"已提交"：state=‘A’
	 * @param id
	 */
	void admit(Integer id);
	

	/**
	 * 批量逻辑删除毕业要求指标点
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 受影响行数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 根据毕业要求指标点标识查询毕业要求指标点
	 * 
	 * @param id 课程标识
	 * @return 毕业要求指标点类
	 */
	GraduationRequireIndicator selectById(Integer id);

	/**
	 * 插入毕业要求指标点
	 * 
	 * @param courseInfo 毕业要求指标点
	 */
	void insert(GraduationRequireIndicator gIndicator);

	/**
	 * 更新毕业要求指标点
	 * 
	 * @param courseInfo 毕业要求指标点类
	 */
	void update(GraduationRequireIndicator gIndicator);

	/**
	 * 过滤查询毕业要求指标点
	 * 
	 * @param courseInfo 毕业要求指标点类
	 * @return 毕业要求指标点集合
	 */
	List<GraduationRequireIndicator> listByGraduationRequireIndicator(GraduationRequireIndicator gIndicator);
}