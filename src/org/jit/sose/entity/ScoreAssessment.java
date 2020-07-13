package org.jit.sose.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ScoreAssessment {

//	public ScoreAssessment(Integer gradeFormId, Integer courseClassStudentInfoId) {
//		super();
//		this.gradeFormId = gradeFormId;
//		this.courseClassStudentInfoId = courseClassStudentInfoId;
//	}

	public ScoreAssessment() {
		super();
	}

	public ScoreAssessment(Integer gradeFormId, Integer courseClassStudentInfoId, Integer scoreTotalId) {
	super();
	this.gradeFormId = gradeFormId;
	this.courseClassStudentInfoId = courseClassStudentInfoId;
	this.scoreTotalId = scoreTotalId;
}

	/**
	 * 考核成绩id
	 */
	private Integer scoreAssessmentId;

	/**
	 * 分数
	 */
	private Integer score;

	/**
	 * 课程考核id
	 */
	private Integer assessmentId;

	/**
	 * 课程考核名称
	 */
	private String assessName;

	private Integer id;

	/**
	 * 成绩登记表id
	 */
	private Integer gradeFormId;

	/**
	 * 上课班级和学生关联表id
	 */
	private Integer courseClassStudentInfoId;

	/**
	 * 总成绩id
	 */
	private Integer scoreTotalId;

	/**
	 * 总成绩
	 */
	private ScoreTotal scoreTotal;

	private String state;

	private String stuState;

	private Timestamp createdDate;

	private Timestamp stateDate;

	private String studentName;// 学生姓名

	private String studentNumber;// 学生学号

	private String schoolName;// 学院名称

	private String courseName;// 课程名称

	private String courseNo;// 选课号

	private String staffName;// 教工名称

}