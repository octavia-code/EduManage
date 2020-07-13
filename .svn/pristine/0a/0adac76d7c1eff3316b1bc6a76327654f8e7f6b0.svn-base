package org.jit.sose.security;

import org.jit.sose.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 自定义配置认证信息
 * 
 * @author: dylan   
 * @date: 2019年5月7日 下午4:37:51
 */
public class UserDetailService implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 读取用户信息
	 */
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// UserDetails:封装用户数据的接口
		User user =new User("dylan","123456",AuthorityUtils.commaSeparatedStringToAuthorityList("ROLR_USER"));
		
		// 这里的User需要继承UserDetails
//		User user = userMapper.selectByUserName(userName);

		
		return user;
	}

}
