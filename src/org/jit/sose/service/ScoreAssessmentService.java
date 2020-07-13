package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.ScoreAssessment;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;

public interface ScoreAssessmentService {

	/**
	 * 根据考核id集合，考核成绩json数组更新或插入考核成绩
	 * 
	 * @param assessmentIdList         考核id集合
	 * @param scoreAssessmentListArray 考核成绩json数组
	 */
	void insertOrUpdateList(List<Integer> assessmentIdList, JSONArray scoreAssessmentListArray, String examKey);

	/**
	 * 查询信息和id集合
	 * 
	 */
	List<ScoreAssessment> selectScoreAssessmentList();

	/**
	 * 插入
	 * 
	 * @param scoreAssessment
	 */
	void insert(ScoreAssessment scoreAssessment);

	/**
	 * 更新
	 * 
	 * @param scoreAssessment
	 */
	void update(ScoreAssessment scoreAssessment);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 成功删除的记录数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 根据标识查询
	 * 
	 * @param id 课程标识
	 * @return
	 */
	ScoreAssessment selectById(Integer id);

	/**
	 * 过滤查询
	 * 
	 * @param ScoreAssessment
	 * @return scoreAssessment 集合
	 */
	PageInfo<ScoreAssessment> listScoreAssessment(ScoreAssessment scoreAssessment, Integer pageNum, Integer pageSize);
	/**
	 * 修改学生考试状态
	 * 
	 * @param gradeFormId
	 * @param assessmentId
	 * @param courseClassStudentInfoId
	 */
	void updateState(Integer id,String stuState);
}
