package org.jit.sose.controller.score;

import java.util.List;

import org.jit.sose.entity.ScoreAssessment;
import org.jit.sose.service.ScoreAssessmentService;
import org.jit.sose.util.FastjsonUtil;
import org.jit.sose.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

/**
 * 考核成绩表控制器类
 * 
 * @author nkz
 *
 */
@RestController
@RequestMapping("/score/scoreAssessment")
public class ScoreAssessmentController {
	@Autowired
	private ScoreAssessmentService scoreAssessmentService;

	/**
	 * 根据考核id集合，考核成绩json数组更新或插入考核成绩
	 * 
	 * @param assessmentIdList         考核id集合
	 * @param scoreAssessmentListArray 考核成绩json数组
	 */
	@RequestMapping(value = "/insertOrUpdateList", method = RequestMethod.POST)
	public void insert(@RequestBody String str) {
		JSONObject strj = JSON.parseObject(str);
		// 考核id集合
		List<Integer> assessmentIdList = FastjsonUtil.toIntegerList(str, "assessmentIdList");
		// 考试列的Key
		String examKey = strj.getString("examKey");
		// 修改过的考核成绩集合
		JSONArray scoreAssessmentListArray = strj.getJSONArray("scoreAssessmentList");
		// 调用添加数据接口
		scoreAssessmentService.insertOrUpdateList(assessmentIdList, scoreAssessmentListArray, examKey);
	}

	/**
	 * 查询考核成绩和id集合
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectScoreAssessmentList", method = RequestMethod.GET)
	public List<ScoreAssessment> selectScoreAssessmentList() {
		return scoreAssessmentService.selectScoreAssessmentList();
	}

	/**
	 * 过滤查询
	 * 
	 * @param start    当前页(Integer----长度5)
	 * @param pageSize 页面大小(Integer----长度3,最大值为100)
	 *
	 * @return
	 */
	@RequestMapping(value = "/selectScoreAssessment", method = RequestMethod.POST)
	public PageInfo<ScoreAssessment> selectScoreAssessment(@RequestBody String str) {
		// 使用fastjson转为json对象
		JSONObject jsonObject = JSON.parseObject(str);
		ScoreAssessment scoreAssessment = new ScoreAssessment();
		// 过滤查询条件
		String schoolName = jsonObject.getString("schoolName");
		String courseName = jsonObject.getString("courseName");
		String courseNo = jsonObject.getString("courseNo");
		String staffName = jsonObject.getString("staffName");
		scoreAssessment.setSchoolName(StringUtil.isEmpty(schoolName) ? null : schoolName);
		scoreAssessment.setCourseName(StringUtil.isEmpty(courseName) ? null : courseName);
		scoreAssessment.setCourseNo(StringUtil.isEmpty(courseNo) ? null : courseNo);
		scoreAssessment.setStaffName(StringUtil.isEmpty(staffName) ? null : staffName);
		// 当前页面
		int pageNum = jsonObject.getIntValue("pageNum");
		// 页面大小
		int pageSize = jsonObject.getIntValue("pageSize");
		return scoreAssessmentService.listScoreAssessment(scoreAssessment, pageNum, pageSize);
	}

	/**
	 * 更新
	 * 
	 * @param id                       标识列(Integer----长度7)
	 * @param gradeFormId              成绩登记标识(Integer----长度5)
	 * @param assessmentId             课程考核标识(Integer----长度5)
	 * @param courseClassStudentInfoId 课程班级学生信息标识(Integer----长度5)
	 * @param score                    考核成绩(Integer----长度3)
	 * @param state                    状态(Char----长度1)
	 * @param createdDate              创建日期(Date----长度0)
	 * @param stateDate                状态日期(Date----长度0)
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody ScoreAssessment scoreAssessment) {
		// 验证数据是否合法

		// 更新操作
		scoreAssessmentService.update(scoreAssessment);
	}

	/**
	 * 逻辑删除
	 * 
	 * @param id 考核成绩标识(Integer----长度7)
	 * 
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		scoreAssessmentService.delete(id);
	}

	/**
	 * 批量逻辑删除
	 * 
	 * @param id 考核成绩标识(Integer----长度7)
	 * @return
	 */
	@RequestMapping(value = "/deleteSelection", method = RequestMethod.POST)
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return scoreAssessmentService.deleteSelection(idList);
	}
	
	@RequestMapping(value = "/updateState", method = RequestMethod.POST)
	public void updateState(@RequestBody String str) {
		JSONObject jsonObject = JSON.parseObject(str);
		Integer id = jsonObject.getInteger("id");
		String stuState = jsonObject.getString("stuState");
		System.out.println(stuState);
		scoreAssessmentService.updateState(id,stuState);
	}
}
