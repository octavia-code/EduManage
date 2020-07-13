package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.entity.AssessItem;
import org.jit.sose.entity.ScoreAssessItem;

public interface ScoreAssessItemMapper {

    List<AssessItem> selectItemByAssessmentId(Integer assessmentId);
    
    List<ScoreAssessItem> listStuAssessmentIdByClassAssessChoiceCourse(ScoreAssessItem scoreAssessItem);
    
    void insert(List<ScoreAssessItem> assessItemScore);
}