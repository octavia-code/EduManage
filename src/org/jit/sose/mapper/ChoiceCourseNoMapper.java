package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.entity.ChoiceCourseNo;

public interface ChoiceCourseNoMapper {
	/**
	 * 查询选课课号信息和id集合
	 * 
	 * @return 选课课号信息和id集合
	 */
	List<ChoiceCourseNo> selectChoiceCourseNoList();
	
	/**
	 * 通过学年id，教师id，课程大纲id查询选课号
	 * @param choiceCourseNo选课课号信息
	 * @return 选课课号信息
	 */
	ChoiceCourseNo selectByChoiceCourseNo(ChoiceCourseNo choiceCourseNo);

	/**
	 * 逻辑删除选课课号信息
	 * 
	 * @param id 选课课号信息标识
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除选课课号信息
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 受影响行数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 插入选课课号信息
	 * 
	 * @param choiceCourseNo 选课课号信息
	 */
	void insert(ChoiceCourseNo choiceCourseNo);

	/**
	 * 根据选课课号信息标识查询选课课号信息
	 * 
	 * @param id 选课课号标识
	 * @return 选课课号信息类
	 */
	ChoiceCourseNo selectById(Integer id);

	/**
	 * 更新选课课号信息
	 * 
	 * @param choiceCourseNo 选课课号信息类
	 */
	void update(ChoiceCourseNo choiceCourseNo);

	/**
	 * 过滤查询选课课号信息
	 * 
	 * @param choiceCourseNo 选课课号信息类
	 * @return 选课课号信息集合
	 */
	List<ChoiceCourseNo> listByChoiceCourseNo(ChoiceCourseNo choiceCourseNo);
}