package org.jit.sose.entity;

import lombok.Data;

/**
 * 角色菜单关联表
 * 
 * @author: 王越
 * @date: 2019-08-22 09:29:00
 */
@Data
public class MenuRole {
	private Integer id;

	/**
	 * 角色标识
	 */
	private Integer roleId;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 菜单id
	 */
	private Integer menuId;

	/**
	 * 菜单标题
	 */
	private String menuTitle;

	private String state;

}