package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.entity.Specialty;

public interface SpecialtyMapper {
	
	/**
	 * 逻辑删除专业信息
	 * 
	 * @param id 专业信息标识
	 */
    void delete(Integer id);

    /**
	 * 批量逻辑删除专业信息
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 受影响行数
	 */
    Integer deleteSelection(List<Integer> idList);

    /**
	 * 插入专业信息
	 * 
	 * @param specialty 课程信息
	 */
    void insert(Specialty specialty);

    /**
	 * 根据专业信息标识查询专业信息
	 * 
	 * @param id 专业标识
	 * @return 专业信息类
	 */
    Specialty selectById(Integer id);

    /**
	 * 更新专业信息
	 * 
	 * @param specialty 专业信息类
	 */
    Integer update(Specialty specialty);

    /**
	 * 过滤查询专业信息
	 * 
	 * @param specialty 专业信息类
	 * @return 专业信息集合
	 */
    List<Specialty> listBySpecialty(Specialty specialty);
    
    /**
	 * 查询任务id和任务名称集合
	 * 
	 * @return 任务id和任务名称集合
	 */
	List<Specialty> selectSpecialtyList();
	
	/**
	 * 根据学院信息标识查询专业信息
	 * @return id与专业名称集合
	 */
	List<Specialty> selectBySpecialty(Specialty specialty);
	
	/**
	 * 查询专业信息记录数
	 */
	Integer countBySpecialty(Specialty specialty);
}