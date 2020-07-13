package org.jit.sose.entity;

import java.sql.Timestamp;

import lombok.Data;

/**
 * 毕业要求三级指标实体类
 * 
 * @author: 单爽
 * @date ：2019年6月22日 22:44:34
 */

@Data
public class IndicatorThird {

	/**
	 * 毕业要求三级指标标识
	 */
	private Integer id;

	/**
	 * 内容
	 */

	private String content;
	/**
	 * 序号
	 */
	private Integer seq;
	/**
	 * 用户标识
	 */
	private Integer userId;
	/**
	 * 创建时间
	 */
	private Timestamp createDate;
	/**
	 * 状态时间
	 */
	private Timestamp stateDate;
	/**
	 * 状态码
	 */
	private String state;
	

}