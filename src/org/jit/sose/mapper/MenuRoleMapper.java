package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.entity.MenuRole;

public interface MenuRoleMapper {
	/**
	 * 逻辑删除数据
	 * 
	 * @param id
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 受影响行数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 添加
	 * 
	 * @param record
	 */
	void insert(MenuRole record);

	/**
	 * 批量添加
	 * 
	 * @param recordList
	 * @return 受影响行数
	 */
	Integer insertList(List<MenuRole> recordList);

	/**
	 * 查询单条记录
	 * 
	 * @param id
	 * @return 记录信息
	 */
	MenuRole selectById(Integer id);

	/**
	 * 过滤查询
	 * 
	 * @param record
	 * @return 菜单角色集合
	 */
	List<MenuRole> listByMenuRole(MenuRole record);

}