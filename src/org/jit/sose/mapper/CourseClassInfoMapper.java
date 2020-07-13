package org.jit.sose.mapper;

import java.util.List;
import org.jit.sose.entity.CourseClassInfo;

public interface CourseClassInfoMapper {
	/**
	 * 逻辑删除课程班级信息
	 * 
	 * @param id
	 *            课程班级信息标识
	 */
	void delete(Integer id);

	/**c
	 * 批量删除课程班级信息
	 * 
	 * @param idList
	 *            需要删除的id的集合
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 查询课程班级信息的记录数
	 * @param courseClassInfo
	 * @return
	 */
	Integer countByCourseClassInfo(CourseClassInfo courseClassInfo);
	
	/**
	 * 插入课程班级信息
	 * 
	 * @param courseClassInfo
	 *            课程班级信息
	 * @return 受影响的行数
	 */
	void insert(CourseClassInfo courseClassInfo);

	/**
	 * 根据一级计划标识查询课程班级信息
	 * 
	 * @param id
	 *            课程班级信息标识
	 * @return 课程班级信息类
	 */
	CourseClassInfo selectById(Integer id);

	/**
	 * 更新课程班级信息
	 * 
	 * @param courseClassInfo
	 *            课程班级信息类
	 */
	void update(CourseClassInfo courseClassInfo);

	/**
	 * 过滤查询课程班级信息
	 * 
	 * @param courseClassInfo
	 *            课程班级信息类
	 * @return 课程班级信息集合
	 */
	List<CourseClassInfo> listByCourseClassInfo(CourseClassInfo courseClassInfo);

	/**
	 * 查询课程班级id和选课课号集合
	 * 
	 * @return 课程班级id和选课课号集合
	 */
	List<CourseClassInfo> selectCourseClassInfoList();
	
	/**
	 * 通过选课号id查询课程班级id
	 * @param record
	 * @return
	 */
	List<CourseClassInfo> selectByChoiceNoId(CourseClassInfo record);
	
	/**
	 * 通过班级id查课程班级id
	 * 
	 * @param record
	 * @return
	 */
	List<CourseClassInfo> selectByClassId(CourseClassInfo record);
	

	/**
	 * 通过Excel向库中增加数据
	 *@param courseClassInfo
	 */
//	void insertByExcel (@Param("courseClassInfoId") Integer courseClassInfoId,@Param("seq") Integer seq,@);

	/**
	 * 通过学院id 学年id 课程id 选课号id 教工id 班级id 查询课程班级id
	 */
	Integer getCourseClassInfoId(CourseClassInfo courseClassInfo);
	
	
	
}