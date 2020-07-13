package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.CourseType;

import com.github.pagehelper.PageInfo;

public interface CourseTypeService {

	/**
	 * 查询课程类别和id集合
	 * 
	 * @author 王越
	 * @return 课程类别和id集合
	 */
	List<CourseType> selectCourseTypeList();

	/**
	 * 过滤查询(查询条件为课程类别类里面相关属性)
	 * 
	 * @param courseType 课程类别类
	 * @return CourseType集合
	 */
	PageInfo<CourseType> listByCourseType(CourseType courseType, Integer pageNum, Integer pageSize);

	/**
	 * 插入课程类别
	 * 
	 * @param courseType 课程类别类
	 * @return success/fail
	 */
	void insert(CourseType courseType);

	/**
	 * 更新课程类别
	 * 
	 * @param courseType 课程类别类
	 * @return success/fail
	 */
	void update(CourseType courseType);

	/**
	 * 逻辑删除课程类别
	 * 
	 * @param id 需要删除的id
	 * @return success/fail
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除课程类别
	 * 
	 * @param idList 需要删除的id的集合
	 * @return success/fail
	 */
	void deleteSelection(List<Integer> idList);

	/**
	 * 倒序过滤查询(查询条件为课程信息类里面相关属性)
	 * 
	 * @param courseType 课程类别类
	 * @return CourseType集合
	 */
	PageInfo<CourseType> listByCourseTypeDESC(CourseType courseType, Integer pageNum, Integer pageSize);

	/**
	 * 
	 * @param courseType 新增前需要查重的类别名称
	 * @return 含有或不含有同样名称的数据的CourseType集合
	 */
	List<CourseType> checkInsert(CourseType courseType);

	/**
	 * 
	 * @param courseType 编辑时需要查重的类别名称
	 * @return 含有或不含有同样名称的数据的CourseType集合
	 */
	List<CourseType> checkUpdate(CourseType courseType);
}
