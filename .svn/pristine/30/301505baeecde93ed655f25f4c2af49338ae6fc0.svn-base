package org.jit.sose.controller.account;

import java.util.List;

import org.jit.sose.entity.Menu;
import org.jit.sose.entity.vo.MenuVo;
import org.jit.sose.service.MenuService;
import org.jit.sose.util.ResponseUtil;
import org.jit.sose.util.StringUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

/**
 * 菜单
 * 
 * @author: 陈子扬
 * @date: 2019年8月25日04:57:20
 */
@RestController
@RequestMapping("/account/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	/**
	 * 通过用户id递归查询所有菜单
	 * 
	 * @param userId 用户id,从token中获取
	 * @return 菜单集合
	 */
	@RequestMapping(value = "/listMenuByUserId", method = RequestMethod.GET)
	public List<MenuVo> listMenuByUserId(Integer userId){
		return menuService.listMenuByUserId(userId);
	}

	/**
	 * 查找父级菜单项
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectFatherMenuList", method = RequestMethod.GET)
	public List<Menu> selectFatherMenuList() {
		return menuService.selectFatherMenuList();
	}

	/**
	 * 查询菜单列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectMenuList", method = RequestMethod.GET)
	public List<Menu> selectMenuList() {
		return menuService.selectMenuList();
	}

	/**
	 * 新增前检查是否有重复
	 * 
	 * @param menu 要查重的信息集合
	 * @return 重复信息
	 */
	@RequestMapping(value = "/addCheckSame", method = RequestMethod.POST)
	public String addCheckSame(@RequestBody Menu menu) {
		menu.setPath(StringUtil.isEmpty(menu.getPath()) ? null : menu.getPath());
		System.out.println(menuService.addCheckSame(menu));
		return ResponseUtil.success(menuService.addCheckSame(menu));
	}

	/**
	 * 详细查找
	 * 
	 * @param menu 要查询的信息集合
	 * @return 过滤详细查询得到信息的集合
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public PageInfo<Menu> detail(@RequestBody String str) {
		JSONObject strj = new JSONObject(str);
		Menu menu = new Menu();
		// 过滤查询条件
		menu.setTitle(StringUtil.isEmpty(strj.getString("title")) ? null : strj.getString("title"));
		menu.setParentId(strj.getInt("parentId"));
		menu.setEnableString(strj.getString("enableString"));
		// 当前页索引
		Integer pageNum = strj.getInt("pageNum");
		// 当前页页面大小
		Integer pageSize = strj.getInt("pageSize");
		return menuService.detail(menu, pageNum, pageSize);
	}

	/**
	 * 过滤查找
	 * 
	 * @param menu 要查询的信息集合
	 * @return 过滤查询得到信息的集合
	 */
	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public PageInfo<Menu> filter(@RequestBody String str) {
		JSONObject strj = new JSONObject(str);
		Menu menu = new Menu();
		// 过滤查询条件
		menu.setTitle("".equals(strj.getString("title")) ? null : strj.getString("title"));
		// 当前页索引
		Integer pageNum = strj.getInt("pageNum");
		// 当前页页面大小
		Integer pageSize = strj.getInt("pageSize");

		return menuService.selectMenuByTitle(menu, pageNum, pageSize);
	}

	/**
	 * 倒序过滤查找
	 * 
	 * @param menu 要查询的信息集合
	 * @return 过滤查询得到信息的集合
	 */
	@RequestMapping(value = "/filterDesc", method = RequestMethod.POST)
	public PageInfo<Menu> filterDesc(@RequestBody String str) {
		JSONObject strj = new JSONObject(str);
		Menu menu = new Menu();
		// 过滤查询条件
		menu.setTitle("".equals(strj.getString("title")) ? null : strj.getString("title"));
		// 当前页索引
		Integer pageNum = strj.getInt("pageNum");
		// 当前页页面大小
		Integer pageSize = strj.getInt("pageSize");

		return menuService.selectMenuByTitleDesc(menu, pageNum, pageSize);
	}

	/**
	 * 添加新菜单项
	 * 
	 * @param menu
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insert(@RequestBody Menu menu) {

		menuService.insert(menu);
	}

	/**
	 * 更新菜单项
	 * 
	 * @param menu
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody Menu menu) {

		menuService.update(menu);
	}

	/**
	 * 删除菜单项
	 * 
	 * @param menu
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {

		menuService.delete(id);

	}

	/**
	 * 批量删除菜单项
	 * 
	 * @param menu
	 */
	@RequestMapping(value = "/deleteSelection", method = RequestMethod.POST)
	public void deleteSelection(@RequestBody List<String> TitleList) {
		menuService.deleteSelection(TitleList);
	}
	
	/**
	 * 通过用户id递归查询所有菜单
	 * 
	 * @param userId 用户id,从token中获取
	 * @return 菜单集合
	 */
	@RequestMapping(value = "/listMenuTreeByUserId", method = RequestMethod.POST)
	public List<MenuVo> listMenuTreeByUserId(Integer userId){
		return menuService.listMenuByUserId(userId);
	}

}
