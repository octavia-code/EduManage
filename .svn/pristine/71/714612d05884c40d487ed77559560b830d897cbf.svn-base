package org.jit.sose.controller.score;

import java.util.List;

import org.jit.sose.entity.GradeForm;
import org.jit.sose.service.GradeFormService;
import org.jit.sose.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

/**
 * 成绩单控制器类
 * 
 * @author nkz
 *
 */
@RestController
@RequestMapping("/score/gradeForm")
public class GradeFormController {
	@Autowired
	private GradeFormService gradeFormService;
	
	@RequestMapping(value = "/changeGradeForm", method = RequestMethod.POST)
	public GradeForm changeCourseName(Integer id){
		return gradeFormService.selectById(id);
	}
	
	/**
	 * 查询成绩单和id集合
	 * @return
	 */
	@RequestMapping(value = "/selectGradeFormList", method = RequestMethod.GET)
	public List<GradeForm> selectGradeFormList() {
		return gradeFormService.selectGradeFormList();
	}
	
	/**
	 * 过滤查询
	 * 
	 * @param start      当前页(Integer----长度5)
	 * @param pageSize   页面大小(Integer----长度3,最大值为100)
	 *
	 * @return 
	 */
	
	@RequestMapping(value = "/selectGradeForm",method = RequestMethod.POST)
	public PageInfo<GradeForm> selectGradeForm(@RequestBody String str) {
		// 使用fastjson转为json对象
		JSONObject jsonObject = JSON.parseObject(str);
		GradeForm gradeForm = new GradeForm();
		// 过滤查询条件
		String schoolName = jsonObject.getString("schoolName");
		String courseName = jsonObject.getString("courseName");
		String courseNo = jsonObject.getString("courseNo");
		String staffName = jsonObject.getString("staffName");
		gradeForm.setSchoolName(StringUtil.isEmpty(schoolName) ? null : schoolName);
		gradeForm.setCourseName(StringUtil.isEmpty(courseName) ? null : courseName);
		gradeForm.setCourseNo(StringUtil.isEmpty(courseNo) ? null : courseNo);
		gradeForm.setStaffName(StringUtil.isEmpty(staffName) ? null : staffName);
		// 当前页面
		int pageNum = jsonObject.getIntValue("pageNum");
		// 页面大小
		int pageSize = jsonObject.getIntValue("pageSize");
		return gradeFormService.listGradeForm(gradeForm, pageNum, pageSize);
	}
	
	/**
	 * 插入
	 * 
	 * @param id                 	标识列(Integer----长度7)
	 * @param schoolInfoId    		学院标识(Integer----长度2)
	 * @param choiceCourseNoId		选课课号标识(Integer----长度7)
	 * @param courseClassInfoId		选课班级信息标识(Integer----长度5)
	 * @param state       			状态(Char----长度1)
	 * @param createdDate 			创建日期(Date----长度0)
	 * @param stateDate   			状态日期(Date----长度0)
	 */
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	public void insert(@RequestBody GradeForm gradeForm){
		// 验证数据是否为空
		
		// 调用添加数据接口
		gradeFormService.insert(gradeForm);
	}
	
	/**
	 * 更新
	 * 
	 * @param id                 	标识列(Integer----长度7)
	 * @param schoolInfoId    		学院标识(Integer----长度2)
	 * @param choiceCourseNoId		选课课号标识(Integer----长度7)
	 * @param courseClassInfoId		选课班级信息标识(Integer----长度5)
	 * @param state       			状态(Char----长度1)
	 * @param createdDate 			创建日期(Date----长度0)
	 * @param stateDate   			状态日期(Date----长度0)
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public void update(@RequestBody GradeForm gradeForm) {
		// 验证数据是否合法

		// 更新操作
		gradeFormService.update(gradeForm);
	}

	/**
	 * 逻辑删除
	 * 
	 * @param id 成绩单标识(Integer----长度7)
	 * 
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		gradeFormService.delete(id);
	}
	
	/**
	 * 批量逻辑删除
	 * 
	 * @param id 成绩单标识(Integer----长度7)
	 * @return
	 */
	@RequestMapping(value = "/deleteSelection",method = RequestMethod.POST)
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return gradeFormService.deleteSelection(idList);
	}
	
	/**
	 * 通过学院id 选课号id 课程班级id查询成绩单id
	 * @param gradeForm
	 */
	@RequestMapping(value = "/selectByGradeFromId",method = RequestMethod.POST)
	public Integer selectByGradeFromId(@RequestBody GradeForm gradeForm) {
		return gradeFormService.selectByGradeFromId(gradeForm);
	}
}
