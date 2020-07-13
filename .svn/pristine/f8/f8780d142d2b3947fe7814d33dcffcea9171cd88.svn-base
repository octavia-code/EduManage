package org.jit.sose.entity;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

/**
 * 课程考核
 * 
 * @author: 王越
 * @date: 2019-07-30 18:07:46
 */
@Data
public class Assessment {
	private Integer id;

	/**
	 * 考核名称
	 */
	private String assessName;

	/**
	 * 考核比例
	 */
	private Integer assessRate;

	/**
	 * 大纲标识
	 */
	private Integer courseOutlineId;

	private Integer seq;

	private String state;

	private Timestamp createdDate;

	private Timestamp stateDate;

	/**
	 * 课程考核集合
	 */
	private List<AssessItem> assessItemList;

}