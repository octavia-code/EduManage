package org.jit.sose.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jit.sose.entity.CourseOutline;

public interface CourseOutlineMapper {
	

	/**
	 * 查询课程大纲集合
	 * 
	 * @param outlineNo           大纲版本号
	 * @param courseOutlineTypeId 大纲类型id
	 * @param courseInfoId        课程信息id
	 * @return 课程大纲集合
	 */
	CourseOutline selectByCourseOutline(@Param("outlineNo") String outlineNo,
			@Param("courseOutlineTypeId") Integer courseOutlineTypeId, @Param("courseInfoId") Integer courseInfoId);

	/**
	 * 通过域表查询大纲版本号集合
	 * 
	 * @return 大纲版本号集合
	 */
	List<CourseOutline> listOutlineNoByEecstate();

	/**
	 * 添加大纲
	 * 
	 * @param record
	 */
	void insert(CourseOutline record);

	/**
	 * 通过课程大纲信息查询具体记录
	 * 
	 * @param record
	 * @return
	 */
	List<CourseOutline> listByCourseOutline(CourseOutline record);
	
	/**
	 * 更新课程大纲
	 * 
	 * @param record
	 * @return
	 */
	Integer update(CourseOutline record);
	
	/**
	 * 通过id逻辑删除
	 * 
	 * @param id
	 * @return
	 */
	void delete(Integer id);
	
	int deleteByPrimaryKey(Integer id);

	CourseOutline selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(CourseOutline record);

	List<CourseOutline> selectCourseOutlineList();
}