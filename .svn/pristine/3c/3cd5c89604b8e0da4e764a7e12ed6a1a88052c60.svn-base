package org.jit.sose.test.util;

import org.jit.sose.mapper.MenuMapper;
import org.jit.sose.test.BaseTest;
import org.jit.sose.util.DateFormatUtil;
import org.jit.sose.util.JWTUtil;
import org.jit.sose.util.StringUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JWTUtilTest extends BaseTest {

	@Autowired
	private MenuMapper menuMapper;

	@Test
	public void accountLogin() {
		test();
	}

	public void test() {
//		User user = new User();
//		user.setId(27);
//		user.setUserName("dylan");
//
//		List<Menu> menuList = menuMapper.listMenuByUserId(user.getId());
//		List<MenuVo> menuVoList = new ArrayList<MenuVo>();
//		for (Menu parentMenu : menuList) {
//			MenuVo menuVo = new MenuVo(parentMenu);
//			menuVoList.add(menuVo);
//		}
//
//		// 子菜单集合名称，即当前用户的权限
//		List<String> menuNameList = new ArrayList<String>();
//		for (Menu parentMenu : menuList) {
//			for (Menu menu : parentMenu.getMenuList()) {
//				menuNameList.add(menu.getName());
//			}
//		}

		// 2分钟
		long time = 1000 * 60 * 20L;

		System.out.println("==================" + DateFormatUtil.getTimestamp());
		System.out.println();

//		JWTQuery query = new JWTQuery(user, "admin", menuNameList, time);
//		String token = JWTUtil.createToken(query);

		String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiYWRtaW4iLCJtZW51TmFtZUxpc3QiOlsiL2FjY291bnQvcm9sZV8iLCIvYWNjb3VudC9tZW51XyIsIi9jb25maWcvdGFibGVfdGl0bGUiLCIvY29uZmlnL2VlY3N0YXRlXyIsIi9tYW5hZ2UvY291cnNlX2luZm8iLCIvbWFuYWdlL3NwZWNpYWx0eV8iLCIvbWFuYWdlL3Rlcm1faW5mbyIsIi9tYW5hZ2UveWVhcl9wbGFuIiwiL21hbmFnZS95ZWFyX3Rlcm0iLCIvZWR1L2VkdV9wbGFuIiwiL2VkdS9jb3Vyc2VfdHlwZSIsIi9lZHUvY291cnNlX3Byb3AiLCIvaW5kaWNhdG9yL3N1cHBvcnRfY29lZmZpY2llbnQiLCIvaW5kaWNhdG9yL2luZGljYXRvcl9yZWxhdCIsIi9pbmRpY2F0b3IvZ3JhZHVhdGlvbl9yZXF1aXJlX2luZGljYXRvciIsIi9pbmRpY2F0b3IvaW5kaWNhdG9yX2ZpcnN0IiwiL2luZGljYXRvci9pbmRpY2F0b3Jfc2VjIiwiL2luZGljYXRvci9pbmRpY2F0b3JfdGhpcmQiLCIvaW5mby9jbGFzc19pbmZvIiwiL2luZm8vc2Nob29sX2luZm8iLCIvaW5mby9zdGFmZl9pbmZvIiwiL2luZm8vc3R1ZGVudF9pbmZvIiwiL2luZm8vY2hvaWNlX2NvdXJzZV9ubyIsIi9pbmZvL2NsYXNzX3N0dWRlbnQiLCIvaW5mby9jb3Vyc2VfY2xhc3NfaW5mbyIsIi9vdXRsaW5lL2NvdXJzZV9vdXRMaW5lIiwiL3Njb3JlL2dyYWRlX2Zvcm0iLCIvc2NvcmUvc2NvcmVfYXNzZXNzbWVudCJdLCJpc3MiOiJEeWxhbi5XIiwidXNlck5hbWUiOiJkeWxhbiIsImV4cCI6MTU2ODcxNDk0MCwidXNlcklkIjoyNywiaWF0IjoxNTY4NzE0MzE0fQ.tqfGb21dqyEXsbHUTfbQOGrj5uRyGP4wAI_n6lT-by4";

		String newToken = JWTUtil.refreshToken(token);
		if (StringUtil.isNotEmpty(newToken)) {
			token = newToken;
		}

		System.out.println(token);

//		Claims claims = JWTUtil.parseJWT(token);

//		if (!JWTUtil.isExpiration(token)) {
////			System.out.println(JWTUtil.getUsername(claims) + "   " + JWTUtil.getUserRole(claims));
////			System.out.println("签发人：" + JWTUtil.parseJWT(token).getIssuer());
////			System.out.println("过期时间：" + JWTUtil.getExpiration(claims));
//
//			System.out.println("过期时间：" + JWTUtil.parseJWT(token).getExpiration());
////			System.out.println("用户权限：" + JWTUtil.getMenuNameList(claims));
//
//		} else {
//			System.out.println("token fail...");
//		}

		System.out.println();
		System.out.println("==================" + DateFormatUtil.getTimestamp());

	}

}
