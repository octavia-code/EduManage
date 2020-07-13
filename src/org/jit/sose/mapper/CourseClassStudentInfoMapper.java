package org.jit.sose.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jit.sose.entity.CourseClassStudentInfo;
import org.jit.sose.entity.StudentInfo;

public interface CourseClassStudentInfoMapper {

	/**
	 * 查找数据库与传进的数据一模一样的条数
	 * 
	 * @param courseClassStudentInfo
	 * @return 相同记录的条数
	 */
	Integer countSame(CourseClassStudentInfo courseClassStudentInfo);

	/**
	 * 批量插入课程班级学生信息
	 * 
	 * @param record
	 */
	Integer insertList(List<CourseClassStudentInfo> list);

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
	List<CourseClassStudentInfo> listStudentWithScore(@Param("gradeFormId") Integer gradeFormId,
			@Param("courseOutlineId") Integer courseOutlineId, @Param("courseClassInfoId") Integer courseClassInfoId);

	/**
	 * 批量插入或更新
	 * 
	 * @param courseClassInfoId
	 * @param courseClassStudentInfoList
	 * @return 受影响的行,不是插入的行数
	 */
	Integer insertOrUpdateList(@Param("courseClassInfoId") Integer courseClassInfoId,
			@Param("courseClassStudentInfoList") List<CourseClassStudentInfo> courseClassStudentInfoList);

	void SortSeq(StudentInfo studentInfo);
	
	List<CourseClassStudentInfo> exportStudentInfo(Integer courseClassInfoId);
	
	/**
	 * 通过课程班级id查询主键id
	 */
	List<Integer> listByCourseClassInfoId(Integer courseClassInfoId);

}