package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.entity.ClassStudent;

public interface ClassStudentMapper {
	/**
	 * 通过id逻辑删除学生状态关联
	 * @param id
	 * @return
	 */
    void delete(Integer id);
    /**
   	 * 批量逻辑删除班级学生状态关联
   	 * 
   	 * @param idList 需要删除的id的集合
   	 * @return 受影响行数
   	 */
    Integer deleteSelection(List<Integer> idList);
    /**
     * 插入学生状态关联
     * 
     * @param classStudent
     * @return
     */    
    void insert(ClassStudent classStudent);
    /**
	 * 根据班级学生关联标识查询学生状态关联
	 * 
	 * @param class_info_id、student_info_id 标识
	 * @return 
	 */
    ClassStudent selectById(Integer id);
    /**
     * 更新学生状态关联
     * 
     * @param classStudent
     * @return
     */
    void update(ClassStudent classStudent);
    /**
	 * 过滤查询班级、学生关联
	 * 
	 * @param classStudent 班级学生类
	 * @return 班级学生关联集合
	 */
    List<ClassStudent> listByClassStudent(ClassStudent classStudent);
	/**
	 * 查询班级学生集合
	 * 
	 * @return 班级学生集合
	 */
	List<ClassStudent> selectClassStudentList();
	/**
	 * 查询学生状态
	 * 
	 * @author 徐国皓
	 * @return 学生状态
	 */
	List<ClassStudent> selectStudentStateList();
	
	List<ClassStudent> listByClassStudent();
	
	/**
	 *依据班级ID查询学生姓名与状态
	 */
	List<ClassStudent> listByClassInfoId(Integer classInfoId);
	/**
	 * 批量插入或更新
	 */
	void insertOrUpdateList(Integer classInfoId,List<ClassStudent> classStudentList);
}