package org.jit.sose.entity;

import java.util.List;

import lombok.Data;

/**
 * 菜单实体类
 * 
 * @author: 王越
 * @date: 2019年5月15日 下午5:05:13
 */
@Data
public class Menu {
	/**
	 * 标识
	 */
	private Integer id;

	/**
	 * 使用页面请求路径作为菜单项的唯一标识
	 */
	private String name;

	/**
	 * 菜单标题
	 */
	private String title;

	/**
	 * 页面在项目中的路径
	 */
	private String path;

	/**
	 * 父级菜单 0表示是父级菜单
	 */
	private Integer parentId;

	/**
	 * 菜单排序
	 */
	private Integer seq;

	/**
	 * 是否启用: 1-启用 0-禁用
	 */
	private Boolean enable;
	
	/**
	 * 是否启用
	 */
	private String enableString;

	/**
	 * 子菜单
	 */
	private List<Menu> menuList;
	
	private String parentTitle;
	
  
    /**
	 * Mybatis判断数据库是否存在当前数据的依据
	 */
	private Integer count;
}