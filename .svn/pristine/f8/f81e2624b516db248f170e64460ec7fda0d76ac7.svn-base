package org.jit.sose.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.jit.sose.entity.ClassStudent;

import com.github.pagehelper.PageInfo;

public interface ClassStudentService {
	/**
	 * 插入班级学生关联
	 * 
	 * @param classStudent 班级学生关联类
	 */
	void insert(ClassStudent classStudent);

	/**
	 * 更新班级学生关联
	 * 
	 * @param classStudent 班级学生关联类
	 */
	void update(ClassStudent classStudent);

	/**
	 * 删除班级学生关联
	 * 
	 * @param id 班级学生关联标识
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除班级学生关联
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 成功删除的记录数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 根据班级学生关联标识查询班级学生关联
	 * 
	 * @param id 班级学生标识
	 * @return 班级学生关联类
	 */
	ClassStudent selectById(Integer id);

	/**
	 * 分页条件查询
	 * 
	 * @param classStudent 需要作为查询条件的班级学生关联类
	 * @param pageNum    当前页索引
	 * @param pageSize   设置分页参数
	 * @return PageInfo分页数据
	 */
	PageInfo<ClassStudent> selectPageInfo(ClassStudent classStudent, Integer pageNum, Integer pageSize);
	
	/**
	 * 查询学生状态和id集合
	 * 
	 * @author 徐国皓
	 * @return 学生状态和id集合
	 */
	List<ClassStudent> selectStudentStateList();

	List<ClassStudent> listByClassStudent();
	
	/**
	 *依据班级ID查询学生姓名与状态
	 *
	 */
	void listByClassInfoId(Integer classInfoId,HttpServletResponse response);
	
	/**
	 * 批量插入数据库
	 */
	public void insertByExcel(Integer classInfoId, Integer fileInfoId);
}
