package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.entity.TermInfo;

public interface TermInfoMapper {

	/**
	 * 查询学期信息和id集合
	 * 
	 * @author 王越
	 * @return 学期信息和id集合
	 */
	List<TermInfo> selectTermInfoList();

	/**
	 * 通过id逻辑删除学期信息
	 * 
	 * @param id
	 * @return
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除学期信息
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 受影响行数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 插入学期信息
	 * 
	 * @param termInfo
	 * @return
	 */
	void insert(TermInfo termInfo);

	/**
	 * 根据学期信息标识查询学期信息
	 * 
	 * @param id 标识
	 * @return
	 */
	TermInfo selectById(Integer id);

	/**
	 * 更新学期信息
	 * 
	 * @param termInfo
	 * @return
	 */
	void update(TermInfo termInfo);

	/**
	 * 过滤查询学期信息
	 * 
	 * @param courseType 课程类别类
	 * @return 学期信息集合
	 */
	List<TermInfo> listByTermInfo(TermInfo termInfo);

}