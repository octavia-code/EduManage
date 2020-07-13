package org.jit.sose.entity;



import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.Data;
@Data
public class TblHeadList {
	/**
	 * 标识
	 */
    private Integer id;
    /**
	 * 教学计划表标识
	 */
    private Integer planId;
    /**
	 * 列展示名
	 */
    private String colName;
    /**
	 * 列字段
	 */
    private Integer colNbr;
    /**
	 * 列长度
	 */
    private Integer rowNbr;
    /**
	 * 列是否为空
	 */
    private Boolean isEmpty;
    /**
	 * 用户标识
	 */
    private Integer userId;
    /**
	 * 创建时间
	 */
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createdDate;
	/**
	 * 状态
	 */
    private String state;
    /**
	 * 状态时间
	 */
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp stateDate;
	/**
	 * 列开始号
	 */
    private Integer colStart;
    /**
	 * 列终止号
	 */
    private Integer colEnd;
    /**
	 * 行开始号
	 */
    private Integer rowStart;
    /**
	 * 行终止号
	 */
    private Integer rowEnd;
    /**
	 * 课程表表名
	 */
    private String courseName;
  
}