package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.ClassInfo;
import com.github.pagehelper.PageInfo;

public interface ClassInfoService {
	/**
	 * 查询班级信息id和班级名称集合
	 * 
	 * @return 班级信息id和班级名称集合
	 */
	List<ClassInfo> selectClassInfoList();

	/**
	 * 插入班级信息
	 * 
	 * @param 班级信息类
	 */
	void insert(ClassInfo classInfo);

	/**
	 * 更新班级信息
	 * 
	 * @param 班级信息类
	 */
	void update(ClassInfo classInfo);

	/**
	 * 删除班级信息
	 * 
	 * @param id
	 *            班级信息标识
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除班级信息
	 * 
	 * @param idList
	 *            需要删除的id的集合
	 * @return 成功删除的记录数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 根据班级信息标识查询班级信息
	 * 
	 * @param id
	 *            班级标识
	 * @return 班级信息类
	 */
	ClassInfo selectById(Integer id);

	/**
	 * 过滤查询
	 * 
	 * @param 班级信息类
	 * @return classInfo集合
	 */
	PageInfo<ClassInfo> listClassName(ClassInfo classInfo, Integer pageNum, Integer pageSize);
	
	/**
	 * 通过SpecialtyId查询班级id和班级名称集合
	 * @param classInfo 班级类
	 * @return 班级信息集合
	 */
	List<ClassInfo> listBySpecialtyId(ClassInfo classInfo);

	/**
	 * 通过YearPlanId查询班级id和班级名称集合
	 * @param classInfo 班级类
	 * @return 班级信息集合
	 */
	List<ClassInfo> listByYearPlanId(ClassInfo classInfo);
	
	/**
	 * 通过YearPlanId和SpecialtyId查询班级id和班级名称集合
	 * @param classInfo 班级类
	 * @return 班级信息集合
	 */
	List<ClassInfo> getClassNameByYearPlanIdSpecialtyId(ClassInfo classInfo);
	

}
