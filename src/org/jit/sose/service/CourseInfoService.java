package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.CourseInfo;

import com.github.pagehelper.PageInfo;

public interface CourseInfoService {
	/**
	 * 查询课程信息和id集合
	 * 
	 * @return 课程信息和id集合
	 */
	List<CourseInfo> listCourseInfo();

	/**
	 * 插入课程信息
	 * 
	 * @param courseInfo 课程信息类
	 * @return 重复属性信息
	 */
	String insert(CourseInfo courseInfo);

	/**
	 * 更新课程信息
	 * 
	 * @param courseInfo 课程信息类
	 * @return 重复属性信息
	 */
	String update(CourseInfo courseInfo);

	/**
	 * 删除课程信息
	 * 
	 * @param id 课程信息标识
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除课程信息
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 成功删除的记录数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 根据课程信息标识查询课程信息
	 * 
	 * @param id 课程标识
	 * @return 课程信息类
	 */
	CourseInfo selectById(Integer id);

	/**
	 * 分页条件查询
	 * 
	 * @param courseInfo 需要作为查询条件的课程信息类
	 * @param pageNum    当前页索引
	 * @param pageSize   设置分页参数
	 * @return PageInfo分页数据
	 */
	PageInfo<CourseInfo> selectPageInfo(CourseInfo courseInfo, Integer pageNum, Integer pageSize);
}
