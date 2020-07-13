package org.jit.sose.entity;

import java.sql.Timestamp;

import lombok.Data;

/**
 * 课程大纲类别
 * 
 * @author: 王越
 * @date: 2019-07-30 18:15:39
 */
@Data
public class CourseOutlineType {
	private Integer id;

	/**
	 * 大纲类别名称
	 */
	private String typeName;

	private String state;

	private Timestamp createdDate;

	private Timestamp stateDate;

}