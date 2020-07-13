package org.jit.sose.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jit.sose.entity.Menu;
import org.jit.sose.entity.vo.MenuVo;
import org.jit.sose.mapper.MenuMapper;
import org.jit.sose.service.MenuService;
import org.jit.sose.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public List<Menu> selectFatherMenuList() {
		return menuMapper.selectFatherMenuList();
	}

	@Override
	public List<MenuVo> listMenuByUserId(Integer userId) {
		System.out.println(userId);
		List<Menu> menuList = new ArrayList<Menu>();

		// 从session中获取菜单集合
//		menuList = SessionUtil.getMenuList();
//		if (menuList != null) {
//			return menuList;
//		}

		// ---------该方法从此结束，以下为测试用代码，省略登录过程---------

		// 菜单集合session不存在
//		User user = SessionUtil.getUser();
//		if (user == null) {
//			// 用户session不存在，直接跳转登录页面
//			log.error("用户未登录");
//			return null;
//		}

		// 根据用户id查询菜单集合
//		menuList = menuMapper.listMenuByUserId(userId);

		menuList = menuMapper.selectMenuList();

		// 返给客户端的菜单集合
		List<MenuVo> menuVoList = getMenuVoList(menuList);

		return menuVoList;
	}

	/**
	 * 获取返回去客户端的菜单集合
	 * 
	 * @param menuList 原始菜单集合
	 * @return 菜单集合
	 */
	private List<MenuVo> getMenuVoList(List<Menu> menuList) {
		// 返给客户端的菜单集合
		List<MenuVo> menuVoList = new ArrayList<MenuVo>();
		for (Menu parentMenu : menuList) {
			// 将menu转为menvuVo
			MenuVo menuVo = new MenuVo(parentMenu);
			menuVoList.add(menuVo);
		}
		return menuVoList;
	}

	@Override
	public List<Menu> selectMenuList() {
		// 从session中获取菜单集合
		List<Menu> menuList = SessionUtil.getMenuList();
		if (menuList != null) {
			return menuList;
		}
		// 查询菜单集合
		menuList = menuMapper.selectMenuList();

		return menuList;
	}

	@Override
	public PageInfo<Menu> selectMenuByTitle(Menu menu, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		List<Menu> nowData = menuMapper.listMenuByTitle(menu);
		for (int i = 0; i < nowData.size(); i++) {
			Menu menu2 = nowData.get(i);
			if (menu2.getParentId() == 0) {
				menu2.setParentTitle("父级菜单");
			}
			nowData.set(i, menu2);
		}
		PageInfo<Menu> pageInfo = new PageInfo<>(nowData);
		return pageInfo;
	}

	@Override
	public void insert(Menu menu) {
		// TODO Auto-generated method stub
		menuMapper.insert(menu);
	}

	@Override
	public void update(Menu menu) {
		// TODO Auto-generated method stub
		menuMapper.update(menu);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		menuMapper.delete(id);
	}

	@Override
	public void deleteSelection(List<String> TitleList) {
		// TODO Auto-generated method stub
		menuMapper.deleteSelection(TitleList);
	}

	@Override
	public PageInfo<Menu> detail(Menu menu, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		List<Menu> nowData = menuMapper.detail(menu);
		for (int i = 0; i < nowData.size(); i++) {
			Menu menu2 = nowData.get(i);
			if (menu2.getParentId() == 0) {
				menu2.setParentTitle("父级菜单");
			}
			nowData.set(i, menu2);
		}
		PageInfo<Menu> pageInfo = new PageInfo<>(nowData);
		return pageInfo;
	}

	@Override
	public PageInfo<Menu> selectMenuByTitleDesc(Menu menu, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		List<Menu> nowData = menuMapper.listMenuByTitleDesc(menu);
		for (int i = 0; i < nowData.size(); i++) {
			Menu menu2 = nowData.get(i);
			if (menu2.getParentId() == 0) {
				menu2.setParentTitle("父级菜单");
			}
			nowData.set(i, menu2);
		}
		PageInfo<Menu> pageInfo = new PageInfo<>(nowData);
		return pageInfo;
	}

	@Override
	public String addCheckSame(Menu menu) {
		// TODO Auto-generated method stub
		String str = "";
		List<Menu> nowData = menuMapper.addCheckSame(menu);
		if (nowData.size() > 0) {
			for (int i = 0; i < nowData.size(); i++) {
				Menu addCheckTitle = nowData.get(i);
				if (addCheckTitle.getTitle().equals(menu.getTitle())) {
					str += "title_";
					break;
				}
			}
			for (int j = 0; j < nowData.size(); j++) {
				Menu addCheckPath = nowData.get(j);
				if (menu.getPath() != null && addCheckPath.getPath().equals(menu.getPath())) {
					str += "path_";
					break;
				}
			}
			str += "exist";
			return str;
		} else {
			return null;
		}
	}

}
