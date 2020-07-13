package org.jit.sose.entity;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class YearTerm {
	/**
	 * 学期标识
	 */
	private Integer id;
	/**
	 * 学期
	 */
    private String termName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态时间
     */
    private Timestamp stateDate;
	/**
	 * 状态码
	 */
    private String state;

    /**
	 * Mybatis判断数据库是否存在当前数据的依据
	 */
	private Integer count;

}