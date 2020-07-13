package org.jit.sose.test.mapper;

import java.util.ArrayList;
import java.util.List;

import org.jit.sose.entity.Menu;
import org.jit.sose.entity.vo.MenuTreeVo;
import org.jit.sose.entity.vo.MenuVo;
import org.jit.sose.mapper.MenuMapper;
import org.jit.sose.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MenuMapperTest extends BaseTest {

	@Autowired
	private MenuMapper menuMapper;

	@Test
	public void selectMenuList() {
		List<Menu> menuList = menuMapper.selectMenuList();
		for (Menu menu : menuList) {
			System.out.println(menu);
		}
	}

	@Test
	public void seleMenuByParentId() {
		System.out.println(menuMapper.seleMenuByParentId(1));
	}

	@Test
	public void listMenuByUserId() {
		List<Menu> menuList = menuMapper.listMenuByUserId(27);
		List<MenuVo> menuVoList = new ArrayList<MenuVo>();

		for (Menu parentMenu : menuList) {
			MenuVo menuVo = new MenuVo(parentMenu);

			System.out.println(menuVo);
			for (MenuVo childmenuVo : menuVo.getMenuList()) {
				System.out.println("     " + childmenuVo);
			}

			menuVoList.add(menuVo);
		}

	}
	@Test
	public void wrTest(){
		List<Menu> menuList = menuMapper.selectParentByUserId(7);
		System.out.println("TreeList--"+menuList);
		List<MenuTreeVo> menuTreeVoList = new ArrayList<MenuTreeVo>();
		
		for (Menu parentMenu : menuList) {
		      MenuTreeVo menuTreeVo = new MenuTreeVo(parentMenu);
		      System.out.println("menuTreeVo--"+menuTreeVo);
		      menuTreeVoList.add(menuTreeVo);
		}
		
		System.out.println("menuTreeVoList--"+menuTreeVoList);
	}

}
