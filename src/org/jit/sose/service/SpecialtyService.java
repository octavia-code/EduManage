package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.Specialty;

import com.github.pagehelper.PageInfo;

public interface SpecialtyService {
	/**
	 * 插入专业信息
	 * 
	 * @param Specialty 专业信息类
	 */
	String insert(Specialty specialty);

	/**
	 * 更新专业信息
	 * 
	 * @param Specialty 专业信息类
	 */
	void update(Specialty specialty);

	/**
	 * 删除专业信息
	 * 
	 * @param id 专业信息标识
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除专业信息
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 成功删除的记录数
	 */
	Integer deleteSelection(List<Integer> idList);
	/**
	 * 根据专业信息标识查询专业信息
	 * 
	 * @param id 专业标识
	 * @return 专业信息类
	 */
	Specialty selectById(Integer id);

	/**
	 * 分页条件查询
	 * 
	 * @param Specialty 需要作为查询条件的专业信息类
	 * @param pageNum    当前页索引
	 * @param pageSize   设置分页参数
	 * @return PageInfo分页数据
	 */
	PageInfo<Specialty> selectPageInfo(Specialty specialty, Integer pageNum, Integer pageSize);
	/**
	 * 查询专业id和专业名称集合
	 * 
	 * @return 专业id和专业名称集合
	 * @author 王锐
	 */
	List<Specialty> selectSpecialtyList();

	/**
	 * 根据学院标识查询id与专业名称
	 * @return id和专业名称集合
	 */
	List<Specialty> selectBySpecialty(Specialty specialty);
	
}
