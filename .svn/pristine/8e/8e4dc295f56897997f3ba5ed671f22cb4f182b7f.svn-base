package org.jit.sose.test.mapper;

import java.util.ArrayList;
import java.util.List;

import org.jit.sose.entity.CourseClassStudentInfo;
import org.jit.sose.entity.ScoreAssessment;
import org.jit.sose.mapper.CourseClassStudentInfoMapper;
import org.jit.sose.mapper.ScoreAssessmentMapper;
import org.jit.sose.test.BaseTest;
import org.jit.sose.util.FastjsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class StudentScoreTest extends BaseTest {

	@Autowired
	private CourseClassStudentInfoMapper courseClassStudentInfoMapper;

	@Autowired
	private ScoreAssessmentMapper scoreAssessmentMapper;

	@Test
	public void insertList() {
		String str = "{\"assessmentIdList\":[1,2,3,4],\"scoreAssessmentList\":[{\"gradeFormId\":1,\"courseClassStudentInfoId\":1,\"_index\":0,\"studentNumber\":\"1512011130\",\"score_2\":50,\"score_1\":99,\"score_4\":89,\"score_3\":59,\"_rowKey\":1,\"scoreAssessmentList\":[{\"assessName\":\"期末考试\",\"score\":56,\"scoreAssessmentId\":21,\"assessmentId\":1},{\"assessName\":\"实践考核\",\"score\":36,\"scoreAssessmentId\":22,\"assessmentId\":2},{\"assessName\":\"测试分析与设计研讨与报告撰写\",\"score\":59,\"scoreAssessmentId\":23,\"assessmentId\":3},{\"assessName\":\"作业\",\"score\":89,\"scoreAssessmentId\":24,\"assessmentId\":4}],\"studentName\":\"王\",\"score_4_id\":24,\"courseOutlineId\":1,\"score_2_id\":22,\"seq\":1,\"score_3_id\":23,\"score_1_id\":21},{\"gradeFormId\":1,\"courseClassStudentInfoId\":3,\"_index\":2,\"studentNumber\":\"1612011058\",\"score_2\":23,\"score_1\":100,\"score_4\":\"\",\"score_3\":24,\"_rowKey\":3,\"scoreAssessmentList\":[{\"assessName\":\"实践考核\",\"score\":23,\"scoreAssessmentId\":9,\"assessmentId\":2},{\"assessName\":\"测试分析与设计研讨与报告撰写\",\"score\":24,\"scoreAssessmentId\":10,\"assessmentId\":3}],\"studentName\":\"王锐\",\"score_4_id\":\"\",\"courseOutlineId\":1,\"score_2_id\":9,\"seq\":3,\"score_3_id\":10,\"score_1_id\":\"\"}]}";
		JSONObject strj = JSON.parseObject(str);
		// 考核id集合
		List<Integer> assessmentIdList = FastjsonUtil.toIntegerList(str, "assessmentIdList");
		// 修改过的考核成绩集合
		JSONArray scoreAssessmentListArray = strj.getJSONArray("scoreAssessmentList");

		// 定义考核成绩集合
		List<ScoreAssessment> scoreAssessmentList = new ArrayList<ScoreAssessment>();
		// 遍历修改过的考核成绩集合
		for (int i = 0; i < scoreAssessmentListArray.size(); i++) {
			// 获取json数组中的json对象
			JSONObject scoreAssessmentJson = (JSONObject) scoreAssessmentListArray.get(i);
			//总成绩id
			Integer scoreTotalId = scoreAssessmentJson.getIntValue("scoreTotalId");
			// 成绩登记表id
			int gradeFormId = scoreAssessmentJson.getIntValue("gradeFormId");
			// 上课班级学生关联表id
			int courseClassStudentInfoId = scoreAssessmentJson.getIntValue("courseClassStudentInfoId");

			// 遍历考核id集合
			for (Integer assessmentId : assessmentIdList) {
				ScoreAssessment scoreAssessment = new ScoreAssessment(gradeFormId, courseClassStudentInfoId,scoreTotalId);
				// 考核id
				scoreAssessment.setAssessmentId(assessmentId);

				// 设置考核成绩id，若不存在设为空
				String idKey = "score_" + assessmentId + "_id";
				scoreAssessment.setId(scoreAssessmentJson.getInteger(idKey));
				// 设置考核成绩，若不存在设为空
				String scoreKey = "score_" + assessmentId;
				scoreAssessment.setScore(scoreAssessmentJson.getInteger(scoreKey));
				scoreAssessmentList.add(scoreAssessment);
			}
		}
		for (ScoreAssessment scoreAssessment : scoreAssessmentList) {
			System.out.println(scoreAssessment);
		}
		
		int result = scoreAssessmentMapper.insertOrUpdateList(scoreAssessmentList);
		System.out.println(result);
		
	}

	@Test
	public void listStudentWithScore() {
		List<CourseClassStudentInfo> studentInfoList = courseClassStudentInfoMapper.listStudentWithScore(1, 1, 1);
		for (CourseClassStudentInfo studentInfo : studentInfoList) {
			System.out.println(studentInfo);
			for (ScoreAssessment scoreAssessment : studentInfo.getScoreAssessmentList()) {
				System.out.println("    " + scoreAssessment);
			}
		}
	}

	@Test
	public void listWithStudent() {
//		List<Integer> assessmentIdList = Arrays.asList(1, 2, 3, 4);
//		int gradeFormId = 1;
//		int courseClassStudentInfoId = 1;
//		List<ScoreAssessment> scoreAssessmentList = scoreAssessmentMapper.listWithStudent(assessmentIdList, gradeFormId,
//				courseClassStudentInfoId);
//		for (ScoreAssessment scoreAssessment : scoreAssessmentList) {
//			System.out.println(scoreAssessment);
//		}
	}

}
