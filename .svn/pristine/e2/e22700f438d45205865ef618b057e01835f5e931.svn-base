package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.StudentInfo;

import com.github.pagehelper.PageInfo;

public interface StudentInfoService {
	/**
	 * 查询学生信息和id集合
	 * 
	 * @return 学生信息和id集合
	 */
	List<StudentInfo> selectStudentInfoList();
	
	/**
	 * 插入学生信息
	 * 
	 * @param studentInfo 学生信息类
	 */
	String insert(StudentInfo studentInfo);

	/**
	 * 更新学生信息
	 * 
	 * @param studentInfo 学生信息类
	 */
	void update(StudentInfo studentInfo);

	/**
	 * 删除学生信息
	 * 
	 * @param id 学生信息标识
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除学生信息
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 成功删除的记录数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 根据学生信息标识查询学生信息
	 * 
	 * @param id 学生标识
	 * @return 学生信息类
	 */
	StudentInfo selectById(Integer id);

	/**
	 * 分页条件查询
	 * 
	 * @param studentInfo 需要作为查询条件的学生信息类
	 * @param pageNum    当前页索引
	 * @param pageSize   设置分页参数
	 * @return PageInfo分页数据
	 */
	PageInfo<StudentInfo> selectPageInfo(StudentInfo studentInfo, Integer pageNum, Integer pageSize);
	
	/**
	 * 上课学生信息查询
	 * @param courseClassInfoId  课程班级信息标识（对应学生信息标识）
	 * @param pageNum 当前页索引
	 * @param pageSize  设置分页参数
	 * @return
	 */
	PageInfo<StudentInfo> selectStudentManage(Integer courseClassInfoId);
	
	
	/**
	 * 通过学院id,课程id,班级id查询学生信息
	 * @param studentInfo  
	 * @return
	 */
	PageInfo<StudentInfo> listByMessage(StudentInfo studentInfo);
}
