package org.jit.sose.entity;

import java.util.Date;

import lombok.Data;

@Data
public class GradeForm {
    private Integer id;

    private Integer schoolInfoId;

    private Integer choiceCourseNoId;

    private Integer courseClassInfoId;

    private String state;

    private Date createdDate;

    private Date stateDate;
    
    private String schoolName;
    
    private String className;
    
    private String courseNo;
    
    private String staffName;
    
    private String courseName;
    
    private String scord;
    
    private String outlineName;
    
    private String termName;
    
    private String studentName;
    
    private String studentNumber;
    
    private String totalScore;
    
    private String seq;
    private String peaceTime;
    private String endTerm;
    private String experiment;
    private String remark;
    private Integer courseId;
	
	
}