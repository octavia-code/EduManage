package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.GradeForm;

import com.github.pagehelper.PageInfo;

public interface GradeFormService {

	/**
	 * 查询信息和id集合
	 * 
	 */
	List<GradeForm> selectGradeFormList();
	
	/**
	 * 插入
	 * 
	 * @param GradeForm 
	 */
	String insert(GradeForm gradeForm);

	/**
	 * 更新
	 * 
	 * @param GradeForm 
	 */
	void update(GradeForm gradeForm);

	/**
	 * 删除
	 * 
	 * @param id 
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 成功删除的记录数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 根据标识查询
	 * 
	 * @param id 课程标识
	 * @return 
	 */
	GradeForm selectById(Integer id);

	/**
	 * 过滤查询
	 * 
	 * @param GradeForm 
	 * @return gradeForm 集合
	 */
	PageInfo<GradeForm> listGradeForm(GradeForm gradeForm, Integer pageNum, Integer pageSize);
	/**
	 * 通过学院id 选课号id 课程班级id查询成绩单id
	 * 
	 * @param gradeForm
	 * @return
	 */
	Integer selectByGradeFromId(GradeForm gradeForm);
}
