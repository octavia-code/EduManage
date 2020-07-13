package org.jit.sose.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jit.sose.entity.Menu;
import org.jit.sose.entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 用户登陆信息存放session中，登陆时相关接口调用当前方法实现权限管理
 * 
 * @author: 王越
 * @date: 2019年5月22日 下午2:06:02
 */
public class SessionUtil {

	/**
	 * 获取session对象
	 * 
	 * @return
	 */
	public static HttpSession getSession() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		return request.getSession();
	}

	/**
	 * 获取用户信息
	 * 
	 * @return User
	 */
	public static User getUser() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		User user = (User) request.getSession().getAttribute("USER_SESSION");
		return user;
	}

	/**
	 * 获取用户当前对应的菜单集合
	 * 
	 * @return 菜单集合
	 */
	public static List<Menu> getMenuList() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		@SuppressWarnings("unchecked")
		List<Menu> meuList = (List<Menu>) request.getSession().getAttribute("MENU_LIST_SESSION");
		return meuList;
	}
	
	/**
	 * 获取用户当前对应的子菜单集合名称
	 * 
	 * @return 子菜单集合名称
	 */
	public static List<String> getMenuNameList() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		@SuppressWarnings("unchecked")
		List<String> menuNameList = (List<String>) request.getSession().getAttribute("MENU_NAME_LIST_SESSION");
		return menuNameList;
	}

}
