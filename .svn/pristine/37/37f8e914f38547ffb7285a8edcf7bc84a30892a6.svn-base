package org.jit.sose.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jit.sose.entity.Menu;

public interface MenuMapper {

	/**
	 * 通过用户id递归查询所有菜单
	 * 
	 * @param userId 用户id
	 * @return 菜单集合
	 */
	List<Menu> listMenuByUserId(Integer userId);

	/**
	 * 通过父菜单id和用户id查询子菜单列表
	 * 
	 * @param parentId 父菜单id
	 * @param userId   用户id
	 * @return 子菜单列表
	 */
	List<Menu> lsitMenuByParentIdAndUserId(@Param("parentId") Integer parentId, @Param("userId") Integer userId);

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
	 * 通过父菜单id查询子菜单列表
	 * 
	 * @param parentId 父菜单id
	 * @return 子菜单集合
	 */
	List<Menu> seleMenuByParentId(Integer parentId);

	/**
	 * 新增菜单项
	 * 
	 * @param menu集合
	 */
	void insert(Menu menu);

	/**
	 * 更新菜单项
	 * 
	 * @param menu集合
	 */
	void update(Menu menu);

	/**
	 * 逻辑菜单项
	 * 
	 * @param menu集合
	 */
	void delete(Integer id);

	/**
	 * 逻辑批量菜单项
	 * 
	 * @param 要删除的id集合
	 */
	void deleteSelection(List<String> TitleList);

	/**
	 * 过滤查询
	 * 
	 * @param menu集合
	 * @return 菜单集合
	 */
	List<Menu> listMenuByTitle(Menu menu);

	/**
	 * 详细查询
	 * 
	 * @param menu集合
	 * @return 菜单集合
	 */
	List<Menu> detail(Menu menu);

	/**
	 * 倒序过滤查询
	 * 
	 * @param menu集合
	 * @return 菜单集合
	 */
	List<Menu> listMenuByTitleDesc(Menu menu);

	/**
	 * 新增前重复检查
	 * 
	 * @param menu集合
	 * @return 重复项集合
	 */
	List<Menu> addCheckSame(Menu menu);
	
	/**
	 * 查询父级id
	 * @param userId
	 * @return
	 */
	List<Menu> selectParentByUserId(Integer userId);
	
	/**
	 * 查询权限树
	 * @param userId
	 * @param parentId
	 * @return
	 */
	List<Menu> listByParentId(@Param("userId") Integer userId,@Param("parentId") Integer parentId);
	
	/**
	 * 查询父菜单
	 * @return
	 */
	List<Menu> selectParentMenu();
	
	/**
	 * 查询权限树
	 * @param parentId
	 * @return
	 */
	List<Menu> selectyParentId(@Param("parentId") Integer parentId);
}