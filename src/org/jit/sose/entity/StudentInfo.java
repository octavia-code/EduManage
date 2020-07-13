package org.jit.sose.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class StudentInfo {
	/**
	 * 标识
	 */
	private Integer id;

	/**
	 * 学生姓名
	 */
	private String studentName;

	/**
	 * 学生学号
	 */
	private String studentNumber;

	/**
	 * 学生性别
	 */
	private String sex;

	/**
	 * 性别
	 */
	private String sexString;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建时间
	 */
	private Timestamp createdDate;

	/**
	 * 状态日期
	 */
	private Timestamp stateDate;

	/**
	 * 状态
	 */
	private String state;

	/**
	 * 序号
	 */
	private Integer seq;

	/**
	 * 课程班级标识
	 */
	private Integer courseClassInfoId;

	/**
	 * 年份计划标识
	 */
	private Integer yearPlanId;

	/**
	 * 学校信息标识
	 */
	private Integer schoolInfoId;
	
	/**
	 * 专业标识
	 */
	private Integer specialtyId;

	/**
	 * 班级信息标识
	 */
	private Integer classInfoId;
	
	/**
	 * 学生标识
	 */
	private Integer studentId;


}