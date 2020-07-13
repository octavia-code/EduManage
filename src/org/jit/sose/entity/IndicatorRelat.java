package org.jit.sose.entity;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

/**
 * 指标关联
 * 
 * @author: 王越
 * @date: 2019年6月2日 上午12:27:37
 */
@Data
public class IndicatorRelat {
	/**
	 * 标识
	 */
	private Integer id;

	/**
	 * 毕业要求指标点标识
	 */
	private Integer indicatorId;

	/**
	 * 毕业要求指标点标题
	 */
	private String indicatorTitle;

	/*
	 * 一级指标集合(一对多查询)
	 */
	private List<IndicatorRelat> indicatorFirstList;

	/**
	 * 一级指标标识
	 */
	private Integer indicatorFirstId;

	/**
	 * 一级指标标题
	 */
	private String firstTitle;

	/**
	 * 一级指标内容
	 */
	private String firstContent;
	
	/**
	 * 一级指标序号
	 */
	private Integer firstSeq;
	
	/*
	 * 二级指标集合(一对多查询)
	 */
	private List<IndicatorRelat> indicatorSecList;

	/**
	 * 二级指标标识
	 */
	private Integer indicatorSecId;

	/**
	 * 二级指标内容
	 */
	private String secContent;
	
	/**
	 * 二级指标序号
	 */
	private Integer secSeq;

	/**
	 * 支撑系数标识
	 */
	private Integer supportCoefficientId;
	
	/**
	 * 支撑系数
	 */
	private Double value;
	
	/**
	 * 三级指标标识
	 */
	private Integer indicatorThirdId;

	/**
	 * 三级指标内容
	 */
	private String thirdContent;

	/**
	 * 备注
	 */
	private Integer seq;

	/**
	 * 状态时间
	 */
	private Timestamp stateDate;

	/**
	 * 状态码
	 */
	private String state; 
	
	/**
	 * 指标点状态
	 */
	private String graduationState;

}