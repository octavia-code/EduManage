package org.jit.sose.entity.vo;

import java.util.List;

import lombok.Data;

/**
 * 指标关联树形结构
 * 
 * @author: dylan
 * @date: 2019-07-18 21:56:20
 */
@Data
public class RelatTreeVo {

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
	private List<RelatTreeVo> children;

	/**
	 * 树层级别
	 */
	private Integer TreeLevel;
	
	// 以下数据应该分类存放，防止日后有变动，暂时放在一个类中

	/**
	 * 指标内容
	 */
	private String content;

	/**
	 * 支撑系数标识
	 */
	private Integer supportCoefficientId;

	/**
	 * 支撑系数
	 */
	private Double supportCoefficientValue;
	
	// 给级联选择使用的格式，存放二级指标id，暂时放在一个类中
	private Integer value;
	
	/**
	 * 指标点状态
	 */
	private String graduationState;
	
	/**
	 * 指标点id
	 */
	private Integer  indicatorId;

}
