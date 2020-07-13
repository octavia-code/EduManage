package org.jit.sose.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.jit.sose.entity.CourseClassStudentInfo;
import org.jit.sose.entity.StudentInfo;

public interface CourseClassStudentInfoService {
	

	/**
	 * 批量插入课程班级学生信息
	 * 
	 * @param courseClassInfoId 班级id
	 * @param stuIdList         学生id集合
	 * @return 成功操作的条数
	 */
	Integer insertList(Integer courseClassInfoId, List<Integer> stuIdList);

	/**
	 * 批量删除
	 * 
	 * @param idList
	 * @return
	 */
	Integer deleteSelection(List<Integer> idList);

	
	/**
	 * 根据班级查询所有学生信息+学生对应的考核成绩
	 * 
	 * @param gradeFormId       成绩登记表id
	 * @param courseOutlineId   课程大纲id
	 * @param courseClassInfoId 上课班级id
	 * @return 所有学生信息+学生对应的考核成绩
	 */
	List<CourseClassStudentInfo> listStudentWithScore(Integer gradeFormId, Integer courseOutlineId,
			Integer courseClassInfoId);
	
	/**
	 * 通过Excel向库中增加数据
	 *@param courseClassInfo
	 */
	void insertByExcel (Integer courseClassInfoId,Integer fileInfoId);
	
	void SortSeq(List<StudentInfo> studentInfo);
	
	/**
	 * excelDownload
	 */
	void excelDownload(HttpServletResponse response,Integer courseClassInfoId,String fileName);
}
