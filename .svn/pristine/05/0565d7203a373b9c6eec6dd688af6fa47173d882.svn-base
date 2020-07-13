package org.jit.sose.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * SpringSecurity配置类
 * 
 * @author: dylan
 * @date: 2019年5月14日 上午10:12:20
 */
@Configuration // 声明为配置类
@EnableWebSecurity // 启动SpringSecurity过滤器链
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * 代替配置文件中的<security:authentication-manager>:认证管理器
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("dylan").password("123456").authorities("ROLR_ADMIN");
	}

	/**
	 * 代替配置文件中的<security:http>:权限配置
	 * .httpBasic() // 使用HttpBasic方式进行登录（认证）
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() // 发出一个请求拦截
				.antMatchers("/test/course_info").hasAuthority("ROLR_ADMIN")
				.antMatchers("/login").permitAll() // 放行登录页面
				.antMatchers("/**") // 拦截内容
				.fullyAuthenticated() // 所有资源都需要认证
				.and() // 连接符
				.formLogin().loginPage("/login") // 使用FormLogin方式进行权限实现，并自定义登录页面
				.and()
				.csrf().disable() // 关闭跨站拦截
		;

	}

}
