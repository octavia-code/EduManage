package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.entity.CourseOutlineType;

public interface CourseOutlineTypeMapper {
	/**
	 * 查询课程大纲类别集合
	 * 
	 * @return 课程大纲类别集合
	 */
	List<CourseOutlineType> ListCourseOutlineType();

	int deleteByPrimaryKey(Integer id);

	int insert(CourseOutlineType record);

	int insertSelective(CourseOutlineType record);

	CourseOutlineType selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(CourseOutlineType record);

	int updateByPrimaryKey(CourseOutlineType record);
}