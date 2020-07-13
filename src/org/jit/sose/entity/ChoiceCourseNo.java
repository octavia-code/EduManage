package org.jit.sose.entity;

import java.util.Date;

import lombok.Data;
/**
 * 
 * @author 王锐
 * 选课课号实体类
 *
 */
@Data
public class ChoiceCourseNo {
    /**
     * 选课课号标识
     */
	private Integer id;
	/**
	 * 学年标识
	 */
    private Integer yearTermId;
    
    /**
     * 教工标识
     */
    private Integer staffInfoId;
    /**
     * 课程大纲标识
     */
    private Integer courseOutlineId;
    /**
     * 选课课号
     */
    private String courseNo;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态
     */
    private String state;

    /**
     * 创建时间
     */
    private Date createdDate;

    /**
     * 状态时间
     */
    private Date stateDate;
    
    /**
     * 学年
     */
    private String termName;
    
    /**
     * 教师姓名
     */
    private String staffName;
    
    /**
     * 大纲姓名
     */
    private String outlineName;
    /**
     * 课程编码
     */
    private String courseCode;
    
    /**
     * 教师编码
     */
    private String staffCode;

    /**
     * 课程名
     */
    private String courseName;
    
    /**
	 * Mybatis判断数据库是否存在当前数据的依据
	 */
	private Integer count;
}