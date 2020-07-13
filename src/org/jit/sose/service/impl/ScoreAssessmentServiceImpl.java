package org.jit.sose.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jit.sose.entity.ScoreAssessment;
import org.jit.sose.entity.ScoreTotal;
import org.jit.sose.mapper.ScoreAssessmentMapper;
import org.jit.sose.mapper.ScoreTotalMapper;
import org.jit.sose.service.ScoreAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ScoreAssessmentServiceImpl implements ScoreAssessmentService {
	@Autowired
	private ScoreAssessmentMapper scoreAssessmentMapper;

	@Autowired
	private ScoreTotalMapper scoreTotalMapper;

	@Transactional
	@Override
	public void insertOrUpdateList(List<Integer> assessmentIdList, JSONArray scoreAssessmentListArray, String examKey) {
		// 定义考核成绩集合
		List<ScoreAssessment> scoreAssessmentList = new ArrayList<ScoreAssessment>();

		// 定义总成绩的集合
		List<ScoreTotal> scoreTotalList = new ArrayList<ScoreTotal>();

		// 遍历修改过的考核成绩集合
		for (int i = 0; i < scoreAssessmentListArray.size(); i++) {
			// 获取json数组中的json对象
			JSONObject scoreAssessmentJson = (JSONObject) scoreAssessmentListArray.get(i);
			// 总成绩id
			Integer scoreTotalId = scoreAssessmentJson.getIntValue("scoreTotalId");
			
			// 总成绩
			Double totalScore = scoreAssessmentJson.getDouble("totalScore");
			// 成绩登记表id
			int gradeFormId = scoreAssessmentJson.getIntValue("gradeFormId");
			// 上课班级学生关联表id
			int courseClassStudentInfoId = scoreAssessmentJson.getIntValue("courseClassStudentInfoId");

			ScoreTotal scoreTotal = new ScoreTotal(scoreTotalId, courseClassStudentInfoId, totalScore);
			//添加到总成绩list		
			scoreTotalList.add(scoreTotal);
			
			
			// 遍历考核id集合
			for (Integer assessmentId : assessmentIdList) {
				ScoreAssessment scoreAssessment = new ScoreAssessment(gradeFormId, courseClassStudentInfoId,
						scoreTotalId);
				// 考核id
				scoreAssessment.setAssessmentId(assessmentId);

				// 设置考核成绩id，若不存在设为空
				String idKey = "score_" + assessmentId + "_id";
				scoreAssessment.setId(scoreAssessmentJson.getInteger(idKey));

				// 设置考核成绩，若不存在设为空
				String scoreKey = "score_" + assessmentId;
				scoreAssessment.setScore(scoreAssessmentJson.getInteger(scoreKey));
				// if(scoreKey==examKey){
				// scoreAssessment.setStuState(scoreAssessmentJson.getString("stuState"));
				// }else{
				// scoreAssessment.setStuState("A");
				// }
				scoreAssessmentList.add(scoreAssessment);
				
			}
		}
		for (ScoreAssessment scoreAssessment : scoreAssessmentList) {
			log.debug("操作的对象" + scoreAssessment);
		}
		for (ScoreTotal scoreTotal : scoreTotalList) {
			log.debug("操作的对象" + scoreTotal);
		}
		System.out.println(scoreAssessmentList);
		//插入总成绩表
		scoreTotalMapper.insert(scoreTotalList);
		//插入课程考核项表
		scoreAssessmentMapper.insertOrUpdateList(scoreAssessmentList);
	}

	@Override
	public List<ScoreAssessment> selectScoreAssessmentList() {
		return scoreAssessmentMapper.selectScoreAssessmentList();
	}

	@Override
	public void insert(ScoreAssessment scoreAssessment) {
		scoreAssessmentMapper.insert(scoreAssessment);
	}

	@Override
	public void update(ScoreAssessment scoreAssessment) {
		scoreAssessmentMapper.update(scoreAssessment);
	}

	@Override
	public void delete(Integer id) {
		scoreAssessmentMapper.delete(id);
	}

	@Override
	public Integer deleteSelection(List<Integer> idList) {
		return scoreAssessmentMapper.deleteSelection(idList);
	}

	@Override
	public ScoreAssessment selectById(Integer id) {
		return scoreAssessmentMapper.selectById(id);
	}

	@Override
	public PageInfo<ScoreAssessment> listScoreAssessment(ScoreAssessment scoreAssessment, Integer pageNum,
			Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<ScoreAssessment> scoreAssessmentList = scoreAssessmentMapper.listByScoreAssessment(scoreAssessment);
		PageInfo<ScoreAssessment> pageInfo = new PageInfo<ScoreAssessment>(scoreAssessmentList);
		return pageInfo;
	}

	@Override
	public void updateState(Integer id, String stuState) {
		scoreAssessmentMapper.updateState(id, stuState);
		System.out.println(id);
		ScoreAssessment scoreAssessment = scoreAssessmentMapper.selectById(id);
		System.out.println(scoreAssessment.getScoreTotalId());
		System.out.println(scoreAssessment.getStuState());
		scoreTotalMapper.updateState(scoreAssessment.getScoreTotalId(), scoreAssessment.getStuState());
	}

}
