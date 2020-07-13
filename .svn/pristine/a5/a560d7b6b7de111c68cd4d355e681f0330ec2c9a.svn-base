package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.entity.Eecstate;

public interface EecstateMapper {
	/**
	 * 逻辑删除域表信息
	 * 
	 * @param id 域表信息标识
	 * @return 受影响行数
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除域表信息
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 受影响行数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 插入域表信息
	 * 
	 * @param eecstate 域表信息
	 * @return 受影响行数
	 */
	void insert(Eecstate eecstate);

	/**
	 * 根据域表信息标识查询域表信息
	 * 
	 * @param id 域表标识
	 * @return 域表信息类
	 */
	Eecstate selectById(Integer id);

	/**
	 * 更新域表信息
	 * 
	 * @param eecstate 域表信息类
	 * @return 受影响行数
	 */
	void update(Eecstate eecstate);
	
	/**
	 * 查重
	 * @param eecstate
	 * @return 数据相同的条数
	 */
	Integer countByEecstate(Eecstate eecstate);

	/**
	 * 过滤查询域表信息
	 * 
	 * @param eecstate 域表信息类
	 * @return 域表信息集合
	 */
	List<Eecstate> listByEecstate(Eecstate eecstate);

}