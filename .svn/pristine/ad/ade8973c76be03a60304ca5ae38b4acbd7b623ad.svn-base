package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.entity.CourseProp;

public interface CoursePropMapper {
	/**
	 * 查询课程性质和id集合
	 * 
	 * @author 王越
	 * @return 课程性质和id集合
	 */
	List<CourseProp> selectCoursePropList();

	/**
	 * 通过id逻辑删除
	 * 
	 * @param id
	 * @return
	 */
	void delete(Integer id);

	/**
	 * 插入课程性质
	 * 
	 * @param courseProp
	 * @return
	 */
	void insert(CourseProp courseProp);

	/**
	 * 根据课程性质标识查询
	 * 
	 * @param id 标识
	 * @return
	 */
	CourseProp selectById(Integer id);

	/**
	 * 更新课程性质
	 * 
	 * @param courseProp
	 * @return
	 */
	void update(CourseProp courseProp);

	/**
	 * 过滤查询课程性质
	 * 
	 * @param courseType 课程性质类
	 * @return 课程信息集合
	 */
	List<CourseProp> listByCourseProp(CourseProp courseProp);

	/**
	 * 批量逻辑删除课程性质
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 受影响行数
	 */
	Integer deleteSelection(List<Integer> idList);
}