package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.AssessItem;
import org.jit.sose.entity.ScoreAssessItem;
import org.jit.sose.mapper.ScoreAssessItemMapper;
import org.jit.sose.service.ScoreAssessItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreAssessItemServiceImpl implements ScoreAssessItemService{

	@Autowired
	private ScoreAssessItemMapper scoreAssessItemMapper;
	
	@Override
	public List<AssessItem> selectItemByAssessmentId(Integer assessmentId)
	{
		System.out.println(scoreAssessItemMapper.selectItemByAssessmentId(assessmentId));
		return scoreAssessItemMapper.selectItemByAssessmentId(assessmentId);
	}

	@Override
	public List<ScoreAssessItem> listStuAssessmentIdByClassAssessChoiceCourse(ScoreAssessItem scoreAssessItem) {
		return scoreAssessItemMapper.listStuAssessmentIdByClassAssessChoiceCourse(scoreAssessItem);
	}

	@Override
	public void sumbitScore(List<ScoreAssessItem> assessItemScore) {
		scoreAssessItemMapper.insert(assessItemScore);
		
	}

	
	
}
