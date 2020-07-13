package org.jit.sose.entity.vo;

import lombok.Data;

/**
 * 考核项树形结构
 * 
 * @author: 王越
 * @date: 2019-08-01 09:39:39
 */
@Data
public class AssessItemTreeVo {

	/**
	 * 节点id
	 */
	private String id;

	/**
	 * 指定节点标签为节点对象的某个属性值
	 */
	private String label;

	/**
	 * 树层级别
	 */
	private Integer treeLevel;
	
	/**
	 * 课程考核标识
	 */
	private Integer assessmentId;

	/**
	 * 毕业达成度指标点(当前为二级指标)
	 */
	private Integer indicatorSecId;

	/**
	 * 二级指标内容
	 */
	private String indicatorSecContent;

	/**
	 * 考核名称（内容）
	 */
	private String content;

	/**
	 * 满分
	 */
	private Integer maxScore;
	
	private String remark;

}
