package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.UserMenu;
import org.jit.sose.entity.vo.MenuTreeVo;

import com.github.pagehelper.PageInfo;

public interface UserMenuService {

	/**
	 * 根据用户Id查询权限树
	 * @param userId
	 * @return
	 */
	List<MenuTreeVo> listByUserMenuTree(Integer userId);

	/**
	 * 查询用户信息集合
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<UserMenu> selectPageInfo(Integer pageNum, Integer pageSize);

	/**
	 * 查询权限菜单
	 * @return
	 */
	List<MenuTreeVo> selectParentMenu();

	/**
	 * 通过删除权限后增加权限来实现编辑功能
	 * @param userId
	 * @param menuIdList
	 * @return
	 */
	String editUserMenu(Integer userId, List<Object> menuIdList);
}
