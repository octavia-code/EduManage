package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.GraduationRequireIndicator;

import com.github.pagehelper.PageInfo;

public interface GraduationRequireIndicatorService {

	/**
	 * 查询毕业要求指标点和id集合
	 * 
	 * @return 毕业要求指标点和id集合
	 */
	List<GraduationRequireIndicator> selectGraduationList();

	/**
	 * 插入毕业要求指标点
	 * 
	 * @param GraduationRequireIndicator 毕业要求指标点类
	 */
	void insert(GraduationRequireIndicator gIndicator);

	/**
	 * 更新毕业要求指标点
	 * 
	 * @param GraduationRequireIndicator 毕业要求指标点类
	 */
	void update(GraduationRequireIndicator gIndicator);

	/**
	 * 删除毕业要求指标点
	 * 
	 * @param id 毕业要求指标点标识
	 */
	void delete(Integer id);
	
	/**
	 * 修改毕业指标点状态为"已提交"：state=‘A’
	 * @param id
	 */
	void admit(Integer id);

	/**
	 * 批量逻辑删除毕业要求指标点
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 成功删除的记录数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 根据毕业要求指标点标识查询毕业要求指标点
	 * 
	 * @param id 课程标识
	 * @return 毕业要求指标点类
	 */
	GraduationRequireIndicator selectById(Integer id);

	/**
	 * 分页条件查询
	 * 
	 * @param GraduationRequireIndicator 需要作为查询条件的毕业要求指标点类
	 * @param pageNum                    当前页索引
	 * @param pageSize                   设置分页参数
	 * @return PageInfo分页数据
	 */
	PageInfo<GraduationRequireIndicator> selectPageInfo(GraduationRequireIndicator gIndicator, Integer pageNum,
			Integer pageSize);
}
