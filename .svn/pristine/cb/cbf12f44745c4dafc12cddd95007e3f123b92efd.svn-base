package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.Assessment;

public interface AssessmentService {

	/**
	 * 根据课程大纲标识查询课程考核集合
	 * 
	 * @param courseOutlineId 课程大纲标识
	 * @return 课程考核集合
	 */
	List<Assessment> listByCourseOutlineId(Integer courseOutlineId);

	/**
	 * 新增课程考核
	 * 
	 * @param record
	 */
	void insert(Assessment record);

	/**
	 * 更新课程考核
	 * 
	 * @param record
	 */
	void update(Assessment record);

	/**
	 * 通过id逻辑删除考核
	 * 
	 * @param id
	 */
	void delete(Integer id);
}
