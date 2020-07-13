package org.jit.sose.entity.query;

import java.util.List;

import org.jit.sose.entity.User;

import lombok.Data;

/**
 * 封装jwt参数
 * 
 * @author: 王越
 * @date: 2019-09-12 10:48:09
 */
@Data
public class JWTQuery {

	public JWTQuery() {
		super();
	}

	/**
	 * 参数封装构造方法
	 * 
	 * @param user         User
	 * @param role         角色
	 * @param menuNameList 可以访问的菜单名称集合，即权限
	 */
	public JWTQuery(User user, String role, List<String> menuNameList) {
		super();
		this.userId = user.getId();
		this.userName = user.getUserName();
		this.role = role;
		this.menuNameList = menuNameList;
	}

	/**
	 * 参数封装构造方法
	 * 
	 * @param user         User
	 * @param role         角色
	 * @param menuNameList 可以访问的菜单名称集合，即权限
	 * @param expiration   token有效时间，单位毫秒
	 */
	public JWTQuery(User user, String role, List<String> menuNameList, Long expiration) {
		super();
		this.userId = user.getId();
		this.userName = user.getUserName();
		this.role = role;
		this.menuNameList = menuNameList;
		this.expiration = expiration;
	}

	/**
	 * 用户id
	 */
	Integer userId;

	/**
	 * 用户名
	 */
	String userName;

	/**
	 * 角色
	 */
	String role;

	/**
	 * 用户能访问的页面：用户权限
	 */
	List<String> menuNameList;

	/**
	 * 有效时间
	 */
	Long expiration;

}
