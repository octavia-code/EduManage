package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.CourseOutline;
import org.jit.sose.entity.vo.CourseOutlineTreeVo;

public interface CourseOutlineService {

	/**
	 * 查询课程大纲集合
	 * 
	 * @param outlineNo           大纲版本号
	 * @param courseOutlineTypeId 大纲类型id
	 * @param courseInfoId        课程信息id
	 * @return 课程大纲集合
	 */
	CourseOutlineTreeVo selectCourseOutlineTree(String outlineNo, Integer courseOutlineTypeId, Integer courseInfoId);

	/**
	 * 通过域表查询大纲版本号集合
	 * 
	 * @return 大纲版本号集合
	 */
	List<CourseOutline> listOutlineNoByEecstate();

	/**
	 * 检查大纲是否存在
	 * 
	 * @param record
	 * @return exist:记录已存在；null:正常执行
	 */
	String checkIsExist(CourseOutline record);

	/**
	 * 添加大纲
	 * 
	 * @param record
	 */
	void insert(CourseOutline record);

	/**
	 * 更新课程大纲
	 * 
	 * @param record
	 */
	void update(CourseOutline record);
	
	/**
	 * 通过id逻辑删除
	 * 
	 * @param id
	 */
	void delete(Integer id);
	
	
	/**
	 * 通过课程大纲信息查询具体记录
	 * 
	 * @param record
	 * @return
	 */
	List<CourseOutline> listByCourseOutline(CourseOutline record);

	List<CourseOutline> selectCourseOutlineList();
	
}
