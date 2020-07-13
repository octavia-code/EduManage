package org.jit.sose.mapper;

import java.util.List;
import org.jit.sose.entity.CourseInfo;

public interface CourseInfoMapper {

	/**
	 * 查询课程信息和id集合
	 * 
	 * @return 课程信息和id集合
	 */
	List<CourseInfo> listCourseInfo();

	/**
	 * 逻辑删除课程信息
	 * 
	 * @param id 课程信息标识
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除课程信息
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 受影响行数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 获取当前传入的课程编码的总数，编辑时去除当前记录
	 * 
	 * @param courseInfo
	 * @return 查到的总数
	 */
	Integer countByCourseInfo(CourseInfo courseInfo);

	/**
	 * 插入课程信息
	 * 
	 * @param courseInfo 课程信息
	 */
	void insert(CourseInfo courseInfo);

	/**
	 * 根据课程信息标识查询课程信息
	 * 
	 * @param id 课程标识
	 * @return 课程信息类
	 */
	CourseInfo selectById(Integer id);

	/**
	 * 更新课程信息
	 * 
	 * @param courseInfo 课程信息类
	 */
	void update(CourseInfo courseInfo);

	/**
	 * 过滤查询课程信息
	 * 
	 * @param courseInfo 课程信息类
	 * @return 课程信息集合
	 */
	List<CourseInfo> listByCourseInfo(CourseInfo courseInfo);

}