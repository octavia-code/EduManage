package org.jit.sose.service;

import java.io.InputStream;
import java.util.List;
import org.jit.sose.entity.CourseClassInfo;
import com.github.pagehelper.PageInfo;

public interface CourseClassInfoService {
	/**
	 * 查询课程班级信息id和选课课号集合
	 * 
	 * @return 课程班级信息id和选课课号集合
	 */
	List<CourseClassInfo> selectCourseClassInfoList();

	/**
	 * 插入课程班级信息
	 * 
	 * @param courseClassInfo
	 *            课程班级信息类
	 */
	String insert(CourseClassInfo courseClassInfo);

	/**
	 * 更新课程班级信息
	 * 
	 * @param courseClassInfo
	 *            课程班级信息类
	 */
	void update(CourseClassInfo courseClassInfo);

	/**
	 * 删除课程班级信息
	 * 
	 * @param id
	 *            课程班级信息标识
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除课程班级信息
	 * 
	 * @param idList
	 *            需要删除的id的集合
	 * @return 成功删除的记录数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 根据课程班级信息标识查询课程班级信息
	 * 
	 * @param id
	 *            课程标识
	 * @return 课程班级信息类
	 */
	CourseClassInfo selectById(Integer id);

	/**
	 * 分页条件查询
	 * 
	 * @param courseClassInfo
	 *            需要作为查询条件的课程班级信息类
	 * @param pageNum
	 *            当前页索引
	 * @param pageSize
	 *            设置分页参数
	 * @return PageInfo分页数据
	 */
	PageInfo<CourseClassInfo> selectPageInfo(CourseClassInfo courseClassInfo, Integer pageNum, Integer pageSize);
	
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
	 * 通过学院id 学年id 课程id 选课号id 教工id 班级id 查询课程班级id
	 */
	Integer getCourseClassInfoId(CourseClassInfo courseClassInfo);
}
