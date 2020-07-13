package org.jit.sose.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jit.sose.entity.UserMenu;

public interface UserMenuMapper {
	/**
	 * 查询用户信息集合
	 * 
	 * @return
	 */
	List<UserMenu> listUserMenu();

	/**
	 * 根据用户id删除所有相关数据
	 */
	void deleteAllByUserId(Integer userId);

	/**
	 * 批量插入父级节点与子级节点数据
	 */
	void addByMenuId(@Param("userId") Integer userId, @Param("menuIdList") List<Object> menuIdList);

}