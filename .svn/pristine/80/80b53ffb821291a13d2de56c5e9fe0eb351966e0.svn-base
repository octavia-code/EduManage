package org.jit.sose.controller.info;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.jit.sose.entity.CourseClassStudentInfo;
import org.jit.sose.entity.StudentInfo;
import org.jit.sose.service.CourseClassStudentInfoService;
import org.jit.sose.util.FastjsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping("/info/courseClassStudentInfo")
public class CourseClassStudentInfoController {

	@Autowired
	private CourseClassStudentInfoService recordService;

	@RequestMapping(value = "/deleteSelection", method = RequestMethod.POST)
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return recordService.deleteSelection(idList);
	}

	/**
	 * 根据班级查询所有学生信息+学生对应的考核成绩
	 * 
	 * @param gradeFormId       成绩登记表id
	 * @param courseOutlineId   课程大纲id
	 * @param courseClassInfoId 上课班级id
	 * @return 所有学生信息+学生对应的考核成绩
	 */
	@RequestMapping(value = "/listStudentWithScore", method = RequestMethod.POST)
	public List<CourseClassStudentInfo> listStudentWithScore(@RequestBody String str) {
		JSONObject strj = JSON.parseObject(str);
		int gradeFormId = strj.getIntValue("gradeFormId");
		int courseOutlineId = strj.getIntValue("courseOutlineId");
		int courseClassInfoId = strj.getIntValue("courseClassInfoId");
//		System.out.println(recordService.listStudentWithScore(gradeFormId, courseOutlineId, courseClassInfoId).get(0));
		//System.out.println(recordService.listStudentWithScore(gradeFormId, courseOutlineId, courseClassInfoId).get(1));
		return recordService.listStudentWithScore(gradeFormId, courseOutlineId, courseClassInfoId);
	}

	@RequestMapping(value = "/insertList", method = RequestMethod.POST)
	public Integer insertList(@RequestBody String str) {
		JSONObject strj = JSONObject.parseObject(str);

		// 上课班级id
		int courseClassInfoId = strj.getIntValue("courseClassInfoId");
		// 学生id集合
		List<Integer> stuIdList = FastjsonUtil.toIntegerList(str, "studentInfoIdList");

		return recordService.insertList(courseClassInfoId, stuIdList);
	}

	/**
	 * 根据班级id与导入文件流从EXCEL插入数据库
	 */
	@RequestMapping(value = "/insertByExcel", method = RequestMethod.POST)
	public void insertByExcel(@RequestBody String str) {
		JSONObject strj = JSON.parseObject(str);
		Integer courseClassInfoId = strj.getInteger("courseClassInfoId");
		Integer fileInfoId = strj.getInteger("fileInfoId");
		recordService.insertByExcel(courseClassInfoId, fileInfoId);
	}

	/**
	 * 下载Excel文件
	 * 
	 * @param str
	 * @throws Exception
	 */
	@RequestMapping(value = "/excelDownload", method = RequestMethod.GET)
	@ResponseBody
	public void excelDownload(@RequestParam(value = "className") String className,
			@RequestParam(value = "courseClassInfoId") Integer courseClassInfoId, HttpServletResponse response)
			throws Exception {
		recordService.excelDownload(response, courseClassInfoId, className);
	}

	@RequestMapping(value = "/sortSeq", method = RequestMethod.POST)
	public void sortSeq(@RequestBody List<StudentInfo> studentInfo) {
		recordService.SortSeq(studentInfo);
	}
}
