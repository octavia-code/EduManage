package org.jit.sose.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jit.sose.entity.ScoreAssessment;

public interface ScoreAssessmentMapper {
	
	/**
	 * 根据集合中的每个对象的id判断<br>
	 * 根据成绩登记表id，考核id，班级学生表id组成唯一索引<br>
	 * 若索引存在，执行更新操作<br>
	 * 若索引不存在，执行插入操作
	 * 
	 * @param scoreAssessmentList
	 * @return
	 */
	Integer insertOrUpdateList(List<ScoreAssessment> scoreAssessmentList);
	
	/**
	 * 查询对应学生的各考核成绩<br>
	 * 先通过courseOutlineId查询出关联的考核集合
	 * 
	 * @param gradeFormId 成绩登记表id
	 * @param courseOutlineId 课程大纲id
	 * @param courseClassStudentInfoId 上课班级和学生关联表id
	 * @return 成绩集合
	 */
	List<ScoreAssessment> listWithStudent(@Param("gradeFormId") Integer gradeFormId,
			@Param("courseOutlineId") Integer courseOutlineId,
			@Param("courseClassStudentInfoId") Integer courseClassStudentInfoId);
	
	/**
	 * 查询信息和id集合
	 * 
	 */
	List<ScoreAssessment> selectScoreAssessmentList();

	/**
	 * 通过id逻辑删除
	 * 
	 * @param id
	 * @return
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 受影响行数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 插入
	 * 
	 * @param scoreAssessment
	 * @return
	 */
	void insert(ScoreAssessment scoreAssessment);

	/**
	 * 根据成绩登记标识查询
	 * 
	 * @param id 标识
	 * @return
	 */
	ScoreAssessment selectById(@Param("id") Integer id);

	/**
	 * 更新
	 * 
	 * @param scoreAssessment
	 * @return
	 */
	void update(ScoreAssessment scoreAssessment);

	/**
	 * 过滤查询
	 * 
	 * @param  
	 * @return 信息集合
	 */
	List<ScoreAssessment> listByScoreAssessment(ScoreAssessment scoreAssessment);
	/**
	 * 修改学生状态
	 * 
	 * @param gradeFormId
	 * @param assessmentId
	 * @param courseClassStudentInfoId
	 */
	void updateState(@Param("id") Integer id,
			@Param("stuState") String stuState);

}