package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.Menu;
import org.jit.sose.entity.vo.MenuVo;

import com.github.pagehelper.PageInfo;

public interface MenuService {

	/**
	 * 通过用户id递归查询所有菜单
	 * 
	 * @param userId 用户id
	 * @return 菜单集合
	 */
	List<MenuVo> listMenuByUserId(Integer userId);

	/**
	 * 查询父级菜单列表
	 * 
	 * @return 父级菜单集合
	 */
	List<Menu> selectFatherMenuList();

	/**
	 * 查询菜单列表
	 * 
	 * @return 菜单集合
	 */
	List<Menu> selectMenuList();

	/**
	 * 过滤查询
	 * 
	 * @param menu 菜单类
	 * @return 菜单项集合
	 */
	PageInfo<Menu> selectMenuByTitle(Menu menu, Integer pageNum, Integer pageSize);

	/**
	 * 新增菜单项
	 * 
	 * @param menu 菜单类
	 */
	void insert(Menu menu);

	/**
	 * 更新菜单项
	 * 
	 * @param menu 菜单类
	 */
	void update(Menu menu);

	/**
	 * 逻辑删除菜单项
	 * 
	 * @param menu 菜单类
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除菜单项
	 * 
	 * @param menu 菜单类
	 */
	void deleteSelection(List<String> TitleList);

	/**
	 * 详细过滤查询
	 * 
	 * @param menu     菜单类
	 * @param pageNum  页数
	 * @param pageSize 每页大小
	 * @return 菜单项集合
	 */
	PageInfo<Menu> detail(Menu menu, Integer pageNum, Integer pageSize);

	/**
	 * 倒序过滤查询
	 * 
	 * @param menu     菜单类
	 * @param pageNum  页数
	 * @param pageSize 每页大小
	 * @return 菜单项集合
	 */
	PageInfo<Menu> selectMenuByTitleDesc(Menu menu, Integer pageNum, Integer pageSize);

	/**
	 * 新增前重复检查
	 * 
	 * @param menu 菜单类
	 * @return 重复信息
	 */
	String addCheckSame(Menu menu);

}
