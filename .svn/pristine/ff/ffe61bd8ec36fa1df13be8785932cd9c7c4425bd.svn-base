package org.jit.sose.test.mapper;

import org.jit.sose.entity.Role;
import org.jit.sose.entity.User;
import org.jit.sose.mapper.RoleMapper;
import org.jit.sose.mapper.UserMapper;
import org.jit.sose.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMapperTest extends BaseTest {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RoleMapper roleMapper;

	@Test
	public void selectByUserName() {
		User user = userMapper.selectByUserName("dylan");
		System.out.println(user);
		logger.debug(user.toString());
	}
	
	@Test
	public void selectByUserName2() {
		Role role = new Role();
		role.setMykey("1");
		role.setName("1");
		//role 的insert方法无返回值
		roleMapper.insert(role);
	}


	@Test
	public void password() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		// 哈希算法+加盐
		// $2a$10$CoS7S7kjPsie3HeUhww6FeotcViw5mjJLxdzIZ1KlXU9qpKHlj61i
		// $2a$10$o9u89OIsNSmpBy7lPb6JkO.aRx/kRyhlG42aH9ETmnisesIyzcfSS
		String passworld = passwordEncoder.encode("123456");
		System.out.println(passworld);
		System.out.println(passworld.length());
	}

}
