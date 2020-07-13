package org.jit.sose.entity;

import java.sql.Timestamp;

import lombok.Data;

/**
 * 用户类
 * 
 * @author: 王越
 * @date: 2019年4月18日 下午3:13:59
 */
@Data
public class User {

	public User() {
		super();
	}

	public User(Integer id, String userName) {
		super();
		this.id = id;
		this.userName = userName;
	}

	public User(String userName, String password, String phone, String email) {
		super();
		this.userName = userName;
		this.password = password;
		this.phone = phone;
		this.email = email;
	}

	/**
	 * 标识
	 */
	private Integer id;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 手机号
	 */
	private String phone;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 创建时间
	 */
	private Timestamp createdDate;

	/**
	 * 变更时间
	 */
	private Timestamp stateDate;

	/**
	 * 状态：（A-可用）（X-删除）
	 */
	private String state;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\"User\":\"");
		builder.append(getClass().getName());
		builder.append("\",\"id\":\"");
		builder.append(id);
		builder.append("\",\"userName\":\"");
		builder.append(userName);
		builder.append("\",\"password\":\"");
		builder.append(password);
		builder.append("\",\"phone\":\"");
		builder.append(phone);
		builder.append("\",\"email\":\"");
		builder.append(email);
		builder.append("\",\"createdDate\":\"");
		builder.append(createdDate);
		builder.append("\",\"stateDate\":\"");
		builder.append(stateDate);
		builder.append("\",\"state\":\"");
		builder.append(state);
		builder.append("\"}  ");
		return builder.toString();
	}

}