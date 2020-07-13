package org.jit.sose.controller.account;

import java.util.List;

import org.jit.sose.entity.User;
import org.jit.sose.entity.UserMenu;
import org.jit.sose.entity.vo.MenuTreeVo;
import org.jit.sose.mapper.UserMapper;
import org.jit.sose.service.UserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/account/userMenu")
public class UserMenuController {

	@Autowired
	private UserMenuService userMenuService;

	@Autowired
	UserMapper userMapper;

	/**
	 * 根据用户id查询权限树
	 * 
	 * @param userId
	 * @return 该用户的权限树
	 */
	@RequestMapping(value = "/listByUserMenuTree", method = RequestMethod.POST)
	public List<MenuTreeVo> listByUserMenuTree(@RequestBody Integer userId) {
		List<MenuTreeVo> menuTreeVoTree = userMenuService.listByUserMenuTree(userId);
		return menuTreeVoTree;
	}

	/**
	 * 查询用户名集合（未使用）
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectUserNameList", method = RequestMethod.GET)
	public List<User> selectUserNameList() {
		return userMapper.selectUserNameList();

	}

	/**
	 * 查询用户信息集合
	 * 
	 * @param pageNum,pageSize
	 * @return
	 */
	@RequestMapping(value = "/listUserMenu", method = RequestMethod.POST)
	public PageInfo<UserMenu> listUserMenu(@RequestBody String str) {
		// 使用fastjson转为json对象
		JSONObject jsonObject = JSON.parseObject(str);
		// 当前页面
		int pageNum = jsonObject.getIntValue("pageNum");
		// 页面大小
		int pageSize = jsonObject.getIntValue("pageSize");
		return userMenuService.selectPageInfo(pageNum, pageSize);
	}

	/**
	 * 获取用户已有权限的id集合
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectParentMenu", method = RequestMethod.GET)
	public List<MenuTreeVo> selectParentMenu() {
		return userMenuService.selectParentMenu();

	}

	/**
	 * 根据userId删除该用户所有权限再根据menuIdList进行增加从而实现编辑功能
	 * 
	 * @param userId,menuIdList
	 * @return flag
	 */
	@RequestMapping(value = "/editUserMenu", method = RequestMethod.POST)
	public String editUserMenu(@RequestBody String str) {
		String flag = null;
		JSONObject jsonObject = JSON.parseObject(str);
		int userId = jsonObject.getIntValue("userId");
		List<Object> menuIdList = jsonObject.getJSONArray("menuIdList");
		flag = userMenuService.editUserMenu(userId, menuIdList);
		return JSON.toJSONString(flag);

	}
}
