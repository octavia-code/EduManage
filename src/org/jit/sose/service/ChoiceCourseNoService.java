package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.ChoiceCourseNo;

import com.github.pagehelper.PageInfo;

public interface ChoiceCourseNoService {
	/**
	 * 查询选课课号信息id和选课课号名称集合
	 * 
	 * @return 选课课号信息id和选课课号名称集合
	 */
	List<ChoiceCourseNo> selectChoiceCourseNoList();

	/**
	 * 插入选课课号信息
	 * 
	 * @param 选课课号信息类
	 */
	void insert(ChoiceCourseNo choiceCourseNo);

	/**
	 * 更新选课课号信息
	 * 
	 * @param 选课课号信息类
	 */
	void update(ChoiceCourseNo choiceCourseNo);

	/**
	 * 删除选课课号信息
	 * 
	 * @param id
	 *            选课课号信息标识
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除选课课号信息
	 * 
	 * @param idList
	 *            需要删除的id的集合
	 * @return 成功删除的记录数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 根据选课课号信息标识查询选课课号信息
	 * 
	 * @param id
	 *            选课课号标识
	 * @return 选课课号信息类
	 */
	ChoiceCourseNo selectById(Integer id);

	/**
	 * 过滤查询
	 * 
	 * @param 选课课号信息类
	 * @return choiceCourseNo集合
	 */
	PageInfo<ChoiceCourseNo> listChoiceCourseNo(ChoiceCourseNo choiceCourseNo, Integer pageNum, Integer pageSize);

	/**
	 * 
	 * @param choiceCourseNo选课课号信息
	 * @return 选课课号信息
	 */
	ChoiceCourseNo selectByChoiceCourseNo(ChoiceCourseNo choiceCourseNo);

}
