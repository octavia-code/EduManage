package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.entity.CourseType;

public interface CourseTypeMapper {

	/**
	 * 查询课程类别和id集合
	 * 
	 * @author 王越
	 * @return 课程类别和id集合
	 */
	List<CourseType> selectCourseTypeList();

	/**
	 * 检查新增时是否已存在相同课程类别
	 * 
	 * @param courseType 课程类别类
	 * @return 含有或不含有与新增的类别同名的集合
	 */
	List<CourseType> checkInsert(CourseType courseType);

	/**
	 * 添加课程类别
	 * 
	 * @param courseType 课程类别类
	 * @return 受影响行数
	 */
	void insert(CourseType courseType);

	/**
	 * 检查编辑时是否已存在相同课程类别
	 * 
	 * @param courseType 课程类别类
	 * @return 含有或不含有与新增的类别同名的集合
	 */
	List<CourseType> checkUpdate(CourseType courseType);

	/**
	 * 更新课程类别
	 * 
	 * @param courseType 课程类别类
	 * @return 受影响行数
	 */
	void update(CourseType courseType);

	/**
	 * 过滤查询课程信息
	 * 
	 * @param courseType 课程类别类
	 * @return 课程信息集合
	 */
	List<CourseType> listByCourseType(CourseType courseType);

	/**
	 * 倒序过滤查询课程信息
	 * 
	 * @param courseType 课程类别类
	 * @return 课程信息集合
	 */
	List<CourseType> listByCourseTypeDESC(CourseType courseType);

	/**
	 * 逻辑删除课程类别
	 * 
	 * @param id 要删除的课程类别id
	 * @return 受影响行数
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除课程类别
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 受影响行数
	 */
	void deleteSelection(List<Integer> idList);

}