package org.jit.sose.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jit.sose.entity.Assessment;

public interface AssessmentMapper {

	/**
	 * 通过课程大纲标识查询课程考核id集合
	 * 
	 * @param courseOutlineId 课程大纲标识
	 * @return 课程考核id集合
	 */
//	List<Assessment> listIdByCourseOutlineId(Integer courseOutlineId);
	
	/**
	 * 根据课程大纲标识查询课程考核集合
	 * 
	 * @param courseOutlineId 课程大纲标识
	 * @return 课程考核集合
	 */
	List<Assessment> listByCourseOutlineId(@Param("courseOutlineId") Integer courseOutlineId);

	/**
	 * 根据课程大纲标识查询课程考核集合，递归查询课程考核项
	 * 
	 * @param courseOutlineId 课程大纲标识
	 * @return 课程考核集合
	 */
	List<Assessment> listTreeByCourseOutlineId(Integer courseOutlineId);

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
	 * @return
	 */
	Integer update(Assessment record);

	/**
	 * 通过id逻辑删除
	 * 
	 * @param id
	 */
	void delete(Integer id);

	/**
	 * 通过courseOutlineId逻辑删除课程考核
	 * 
	 * @param id
	 */
	void deleteByCourseOutlineId(Integer id);

	int deleteByPrimaryKey(Integer id);

	Assessment selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Assessment record);

}