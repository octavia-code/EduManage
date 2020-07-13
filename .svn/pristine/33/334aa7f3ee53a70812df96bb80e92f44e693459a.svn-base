package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.entity.AssessItem;

public interface AssessItemMapper {
	/**
	 * 根据考核标识查询考核项集合
	 * 
	 * @param assessmentId 考核标识
	 * @return 考核项集合
	 */
	List<AssessItem> listByAssessmentId(Integer assessmentId);

	/**
	 * 添加考核项
	 * 
	 * @param record
	 */
	void insert(AssessItem record);
	
	/**
	 * 更新考核项
	 * 
	 * @param record
	 * @return
	 */
	Integer update(AssessItem record);
	
	/**
	 * 通过id逻辑删除
	 * 
	 * @param id
	 */
	void delete(Integer id);
	
	/**
	 * 通过courseOutlineId逻辑删除课程考核项
	 * 
	 * @param id
	 */
	void deleteByCourseOutlineId(Integer id);
	
	/**
	 * 通过assessmentId逻辑删除课程考核项
	 * 
	 * @param id
	 */
	void deleteByAssessmentId(Integer id);
	
	int deleteByPrimaryKey(Integer id);

	AssessItem selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(AssessItem record);

	

	
}