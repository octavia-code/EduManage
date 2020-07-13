package org.jit.sose.entity.vo;

import java.util.List;

import lombok.Data;

/**
 * 课程考核树形结构
 * 
 * @author: 王越
 * @date: 2019-08-01 09:39:39
 */
@Data
public class AssessmentTreeVo {

	/**
	 * 节点id
	 */
	private String id;

	/**
	 * 指定节点标签为节点对象的某个属性值
	 */
	private String label;

	/**
	 * 子节点列表
	 */
	private List<AssessItemTreeVo> children;

	/**
	 * 树层级别
	 */
	private Integer treeLevel;

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


}
