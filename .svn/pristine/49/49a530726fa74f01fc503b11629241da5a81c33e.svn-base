package org.jit.sose.mapper;

import java.util.List;
import org.jit.sose.entity.ClassInfo;
public interface ClassInfoMapper {
	/**
	 * 查询班级信息id和班级名称集合
	 * 
	 * @return 班级信息id和班级名称集合
	 */
	List<ClassInfo> selectClassInfoList();

	/**
	 * 通过id逻辑删除班级信息
	 * 
	 * @param id
	 * @return
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除班级信息
	 * 
	 * @param idList
	 *            需要删除的id的集合
	 * @return 受影响行数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 插入班级信息
	 * 
	 * @param classInfo
	 * @return
	 */
	void insert(ClassInfo classInfo);

	/**
	 * 根据班级信息标识查询学期信息
	 * 
	 * @param id
	 *            标识
	 * @return
	 */
	ClassInfo selectById(Integer id);

	/**
	 * 更新班级信息
	 * 
	 * @param classInfo
	 * @return
	 */
	void update(ClassInfo classInfo);

	/**
	 * 过滤查询班级信息
	 * 
	 * @param classInfo
	 *            班级类
	 * @return 班级信息集合
	 */
	List<ClassInfo> listByClassInfo(ClassInfo classInfo);
	
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