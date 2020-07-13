package org.jit.sose.entity;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

/**
 * 课程大纲
 * 
 * @author: 王越
 * @date: 2019-07-30 18:11:35
 */
@Data
public class CourseOutline {
	private Integer id;

	/**
	 * 版本号(域)
	 */
	private String outlineNo;

	/**
	 * 版本号名称
	 */
	private String outlineNoName;

	/**
	 * 课程大纲类别标识
	 */
	private Integer courseOutlineTypeId;

	/**
	 * 课程大纲类别名称
	 */
	private String typeName;

	/**
	 * 课程信息标识
	 */
	private Integer courseInfoId;

	/**
	 * 课程信息名称
	 */
	private String courseName;

	/**
	 * 大纲名称
	 */
	private String outlineName;

	/**
	 * 文件标识
	 */
	private Integer fileInfoId;
	
	/**
	 * 旧文件标识
	 */
	private Integer fileInfoOldId;

	/**
	 * 文件名称
	 */
	private String fileName;

	/**
	 * 访问名称
	 */
	private String accessUrl;

	private String remark;

	private String state;

	private Timestamp createdDate;

	private Timestamp stateDate;

	/**
	 * 课程考核集合
	 */
	private List<Assessment> assessmentList;

}