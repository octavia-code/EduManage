package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.CourseProp;

import com.github.pagehelper.PageInfo;

public interface CoursePropService {

	/**
	 * 查询课程性质和id集合
	 * 
	 * @author 王越
	 * @return 课程性质和id集合
	 */
	List<CourseProp> selectCoursePropList();

	/**
	 * 过滤查询
	 * 
	 * @param courseProp 课程性质类
	 * @return courseProp集合
	 */
	PageInfo<CourseProp> listByCourseProp(CourseProp courseProp, Integer pageNum, Integer pageSize);

	/**
	 * 插入课程性质
	 * 
	 * @param courseProp 课程性质类
	 * @return success/fail
	 */
	void insert(CourseProp courseProp);

	/**
	 * 更新课程信息
	 * 
	 * @param courseProp 课程性质类
	 * @return success/fail
	 */
	void update(CourseProp courseProp);

	/**
	 * 删除信息
	 * 
	 * @param id 课程信息标识
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除课程性质
	 * 
	 * @param idList 需要删除的id的集合
	 * @return success/fail
	 */
	Integer deleteSelection(List<Integer> idList);
}
