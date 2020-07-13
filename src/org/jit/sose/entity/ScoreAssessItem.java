package org.jit.sose.entity;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class ScoreAssessItem {
    private Integer id;

    private Integer scoreAssessmentId;

    private Integer assessItemId;

    private Integer score;

    private String remark;

    private String state;

    private Timestamp createdDate;

    private Timestamp stateDate;
    
    private String studentNumber;
    
	private String studentName;
    
    private Integer stuId;
    
    private Integer classId;
    
    private Integer assessmentId;
    
    private Integer choiceCourseNoId;
    
	private List<ScoreAssessItem> scoreAssessItemList;

}