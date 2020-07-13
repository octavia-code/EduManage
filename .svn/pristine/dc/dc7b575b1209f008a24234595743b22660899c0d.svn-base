package org.jit.sose.entity.vo;

import java.util.ArrayList;
import java.util.List;

import org.jit.sose.entity.Menu;

import lombok.Data;
@Data
public class MenuTreeVo {

	public MenuTreeVo() {
		super();
	}

	public MenuTreeVo(Menu menu) {
		super();
		this.id = menu.getId();
		this.label = menu.getTitle();
		if (menu.getMenuList() != null) {
			// 初始化，否则add会空指针异常
			this.children = new ArrayList<MenuTreeVo>();
			for (Menu childMenu : menu.getMenuList()) {
				// 创建子菜单时，会递归的往下创建子菜单的子菜单
				MenuTreeVo childMenuVo = new MenuTreeVo(childMenu);
				this.children.add(childMenuVo);
			}
		}
	}
	
	private Integer id;
	
	private String label;
	
	private List<MenuTreeVo> children;
	
}
