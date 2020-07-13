package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.entity.GradeForm;

public interface GradeFormMapper {
   
    /**
	 * 查询成绩登记信息和id集合
	 * 
	 */
	List<GradeForm> selectGradeFormList();
	
	/**
	 * 通过id逻辑删除
	 * 
	 * @param id
	 * @return
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 受影响行数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 插入
	 * 
	 * @param gradeForm
	 * @return
	 */
	void insert(GradeForm gradeForm);

	/**
	 * 查询成绩单的记录数
	 * @param gradeForm
	 * @return
	 */
	Integer countByGradeForm(GradeForm gradeForm);
	/**
	 * 根据成绩登记标识查询
	 * 
	 * @param id 标识
	 * @return
	 */
	GradeForm selectById(Integer id);

	/**
	 * 更新
	 * 
	 * @param gradeForm
	 * @return
	 */
	void update(GradeForm gradeForm);

	/**
	 * 过滤查询
	 * 
	 * @param  
	 * @return 信息集合
	 */
	List<GradeForm> listByGradeForm(GradeForm gradeForm);
	/**
	 * 通过学院id 选课号id 课程班级id查询成绩单id
	 * @param gradeForm
	 * @return
	 */
	Integer selectByGradeFromId(GradeForm gradeForm);
}