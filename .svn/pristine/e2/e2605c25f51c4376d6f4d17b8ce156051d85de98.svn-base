package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.TermInfo;

import com.github.pagehelper.PageInfo;

public interface TermInfoService {
	
	/**
	 * 查询学期信息和id集合
	 * 
	 * @author 王越
	 * @return 学期信息和id集合
	 */
	List<TermInfo> selectTermInfoList();
	
	/**
	 * 插入学期信息
	 * 
	 * @param termInfo 课程信息类
	 */
	void insertTermInfo(TermInfo termInfo);

	/**
	 * 更新学期信息
	 * 
	 * @param termInfo 课程信息类
	 */
	void updateTermInfo(TermInfo termInfo);

	/**
	 * 删除学期信息
	 * 
	 * @param id 课程信息标识
	 */
	void deleteTermInfo(Integer id);

	/**
	 * 批量逻辑删除课程信息
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 成功删除的记录数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 根据课程信息标识查询课程信息
	 * 
	 * @param id 课程标识
	 * @return 课程信息类
	 */
	TermInfo selectById(Integer id);

	/**
	 * 过滤查询
	 * 
	 * @param termInfo 学期信息类
	 * @return termInfo集合
	 */
	PageInfo<TermInfo> listTermName(TermInfo termInfo, Integer pageNum, Integer pageSize);
	

	
}
