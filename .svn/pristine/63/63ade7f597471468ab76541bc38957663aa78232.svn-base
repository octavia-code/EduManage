package org.jit.sose.controller.score;

import java.util.List;

import org.jit.sose.entity.AssessItem;
import org.jit.sose.entity.ScoreAssessItem;
import org.jit.sose.service.ScoreAssessItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/score/scoreAssessItem")
public class ScoreAssessItemController {

	@Autowired
	private ScoreAssessItemService scoreAssessItemService;

	@RequestMapping(value = "/selectItemByAssessmentId", method = RequestMethod.POST)
	public List<AssessItem> selectItemByAssessmentId(@RequestBody Integer assessmentId) {
		return scoreAssessItemService.selectItemByAssessmentId(assessmentId);
	}

	@RequestMapping(value = "/sumbitScore", method = RequestMethod.POST)
	public void sumbitScore(@RequestBody List<ScoreAssessItem> assessItemScore) {
		scoreAssessItemService.sumbitScore(assessItemScore);
	}

	@RequestMapping(value = "/getScore", method = RequestMethod.POST)
	public List<ScoreAssessItem> GetScore(@RequestBody ScoreAssessItem scoreAssessItem) {
		return scoreAssessItemService.listStuAssessmentIdByClassAssessChoiceCourse(scoreAssessItem);
	}
}
