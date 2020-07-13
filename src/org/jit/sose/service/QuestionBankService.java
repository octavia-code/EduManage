package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.QuestionBank;

import com.github.pagehelper.PageInfo;

public interface QuestionBankService {

	/**
	 * 查询题库信息
	 * 
	 * @author 王锐
	 * @return 课程信息和id集合
	 */
	List<QuestionBank> selectQuestionBankList();

	/**
	 * 逻辑删除
	 * 
	 * @param id 标识
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
	 * 插入信息
	 * 
	 * @param SchoolInfo 学院信息
	 */
	void insert(QuestionBank questionBank);

	/**
	 * 根据信息标识查询信息
	 * 
	 * @param id 信息标识
	 * @return 信息类
	 */

	QuestionBank selectById(Integer id);

	/**
	 * 更新信息
	 * 
	 * @param SchoolInfo 学院信息类
	 */
	void update(QuestionBank questionBank);

	/**
	 * 分页条件查询
	 * 
	 * @param QuestionBank 信息类
	 * @return 学院信息集合
	 */
	PageInfo<QuestionBank> selectPageInfo(QuestionBank questionBank , Integer pageNum, Integer pageSize);
	
	
	List<QuestionBank> selectFiveQuestion();
}
