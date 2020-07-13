package org.jit.sose.entity;

import java.sql.Timestamp;

import lombok.Data;

/**
 * @author wr
 */
@Data
public class UserMenu {
	/*
	 * 标识
	 */
	private Integer id;

	/*
	 * 用户标识
	 */
	private Integer userId;

	/*
	 * 菜单标识
	 */
	private Integer menuId;

	/*
	 * 状态
	 */
	private String state;

	/*
	 * 创建时间
	 */
	private Timestamp createdDate;

	/*
	 * 状态时间
	 */
	private Timestamp stateDate;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 电话号码
	 */
	private String phone;

	/**
	 * 邮箱
	 */
	private String email;
}