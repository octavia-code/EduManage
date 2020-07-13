package org.jit.sose.entity;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class CourseClassStudentInfo {

	public CourseClassStudentInfo() {
		super();
	}

	public CourseClassStudentInfo(Integer courseClassInfoId, Integer studentInfoId) {
		super();
		this.courseClassInfoId = courseClassInfoId;
		this.studentInfoId = studentInfoId;
	}

	// 以下参数为Mybatis递归查询暂存对象
	/**
	 * 上课班级和学生的关联表id，查询考核成绩
	 */
	private Integer courseClassStudentInfoId;

	/**
	 * 成绩登记表id，查询考核成绩
	 */
	private Integer gradeFormId;

	/**
	 * 大纲id,查询考核集合
	 */
	private Integer courseOutlineId;
	//----------------------------------

	/*
	 * 学生姓名
	 */
	private String studentName;

	/**
	 * 序号
	 */
	private Integer seq;
	/**
	 * 学号
	 */
	private String studentNumber;


	
	/**
	 * 课程班级学生标识
	 */
	private Integer id;

	/**
	 * 课程班级标识
	 */
	private Integer courseClassInfoId;

	/**
	 * 学生标识
	 */
	private Integer studentInfoId;

	private String stuState;

	/**
	 * 考核成绩集合
	 */
	List<ScoreAssessment> scoreAssessmentList;

	/**
	 * 状态日期
	 */
	private Timestamp stateDate;

	/**
	 * 上传时间
	 */
	private Timestamp createdDate;

	/**
	 * 状态：在用-'A';删除-'X'
	 */
	private String state;
	
	/**
	 * 总成绩id
	 */
	private Integer scoreTotalId;

	/**
	 * 总成绩
	 */
	private double totalScore;
}