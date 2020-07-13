package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.entity.CourseClassStudentInfo;
import org.jit.sose.entity.StudentInfo;

public interface StudentInfoMapper {
	/**
	 * 查询学生id和学生名称集合
	 * 
	 * @return 学生id和学生名称集合
	 */
	List<StudentInfo> selectStudentInfoList();
	/**
	 * 逻辑删除学生信息
	 * 
	 * @param id 学生信息标识
	 */
    void delete(Integer id);
    
    /**
	 * 批量逻辑删除学生信息
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 受影响行数
	 */
	Integer deleteSelection(List<Integer> idList);
	
	/**
	 * 插入学生信息
	 * 
	 * @param studentInfo 学生信息
	 */
    void insert(StudentInfo studentInfo);

    /**
	 * 根据学生信息标识查询学生信息
	 * 
	 * @param id 学生标识
	 * @return 学生信息类
	 */
    StudentInfo selectById(Integer id);

    /**
	 * 更新学生信息
	 * 
	 * @param studentInfo 学生信息类
	 */
    void update(StudentInfo studentInfo);
    
    /**
	 * 过滤查询学生信息
	 * 
	 * @param studentInfo 学生信息类
	 * @return 学生信息集合
	 */
	List<StudentInfo> listByStudentInfo(StudentInfo studentInfo);
	
	/**
	 * 上课学生信息查询
	 * @param courseClassInfoId 课程班级信息标识（对应学生信息标识）
	 * @return 学生信息集合
	 */
	List<StudentInfo> selectStudentManage(Integer courseClassInfoId);
	
	
	/**
	 * 通过学院id,课程id,班级id查询学生信息
	 * @param studentInfo  
	 * @return
	 */
	List<StudentInfo> listByMessage(StudentInfo studentInfo);
	
	/**
	 * 查询学生信息记录数
	 */
	Integer countByStudentInfo(StudentInfo studentInfo);
	
	/**
	 * 查询序号、学号、姓名
	 * @param courseClassInfoId
	 * @return
	 */
	List<CourseClassStudentInfo> exportStudentInfo(Integer courseClassInfoId);
	
}