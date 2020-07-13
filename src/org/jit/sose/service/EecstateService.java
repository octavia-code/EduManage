package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.Eecstate;

import com.github.pagehelper.PageInfo;

public interface EecstateService {
	/**
	 * 插入域表信息
	 * 
	 * @param eecstate 域表信息类
	 * @return success/fail
	 */
	String insert(Eecstate eecstate);

	/**
	 * 更新域表信息
	 * 
	 * @param eecstate 域表信息类
	 * @return success/fail
	 */
	void update(Eecstate eecstate);

	/**
	 * 删除域表信息
	 * 
	 * @param id 课程域表标识
	 * @return success/fail
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除域表信息
	 * 
	 * @param idList 需要删除的id的集合
	 * @return success/fail
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 过滤查询(查询条件为课程信息类里面相关属性)
	 * 
	 * @param eecstate 课程信息类
	 * @return  Eecstate集合
	 */
	PageInfo<Eecstate> selectPageInfo(Eecstate eecstate, Integer pageNum, Integer pageSize);
		
	/**根据id查询
	 *
	 * 
	 * 
	 * */
	Eecstate selectById(Integer id);
		
			
		
}
