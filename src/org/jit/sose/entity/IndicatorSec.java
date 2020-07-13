package org.jit.sose.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 二级指标实体类
 * 
 * @author: 任志杰
 * @date: 2019年6月14日 上午11:06:02
 */
@Data
public class IndicatorSec {
	
	/**
	 * 毕业要求(二级指标)标识
	 */
	private Integer id;
	
	/**
	 * 年份标识
	 */
	private Integer yearPlanId;
	
	/**
	 * 一级指标标识
	 */
	private Integer indicatorFirstId;
	/**
	 * 详情内容
	 */
	private String content;
	/**
	 * 用户标识
	 */
	private Integer userId;
	/**
	 * 序号
	 */
	private Integer seq;
	/**
	 * 创建时间
	 */
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createDate;
	/**
	 * 状态时间
	 */
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date stateDate;
	/**
	 * 状态码
	 */
	private String state;
	
	private String title;
	
	private String yearName;
	
	/**
	 * Mybatis判断数据库是否存在当前数据的依据
	 */
	private Integer count;
	
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getYearName() {
		return yearName;
	}

	public void setYearName(String yearName) {
		this.yearName = yearName;
	}
	
	public Integer getIndicatorFirstId() {
        return indicatorFirstId;
    }

    public void setIndicatorFirstId(Integer indicatorFirstId) {
        this.indicatorFirstId = indicatorFirstId;
    }
    
    public Integer getYearPlanId() {
        return yearPlanId;
    }

    public void setYearPlanId(Integer yearPlanId) {
        this.yearPlanId = yearPlanId;
    }
    
    
}
