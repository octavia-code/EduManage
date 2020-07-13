package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.AssessItem;
import org.jit.sose.entity.ScoreAssessItem;

public interface ScoreAssessItemService {

	List<AssessItem> selectItemByAssessmentId(Integer assessmentId);
	
	List<ScoreAssessItem> listStuAssessmentIdByClassAssessChoiceCourse(ScoreAssessItem scoreAssessItem);
	
	void sumbitScore(List<ScoreAssessItem> assessItemScore);
}
