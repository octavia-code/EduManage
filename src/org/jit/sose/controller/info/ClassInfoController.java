package org.jit.sose.controller.info;

import java.util.List;

import org.jit.sose.entity.ClassInfo;
import org.jit.sose.service.ClassInfoService;
import org.jit.sose.util.DataExceptionUtil;
import org.jit.sose.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

/**
 * 班级控制器类
 * @author 牛开专
 *
 */
@RestController
@RequestMapping("/info/classInfo")
public class ClassInfoController {
	@Autowired
	private ClassInfoService classInfoService;
	
	/**
	 * 查询班级信息id和班级名称集合
	 * 
	 * @return 班级信息id和班级名称集合
	 */
	@RequestMapping(value = "/selectClassInfoList", method = RequestMethod.GET)
	public List<ClassInfo> selectClassInfoList() {
		List<ClassInfo> classInfoList = classInfoService.selectClassInfoList();
		return classInfoList;
	}
	
	/**
	 * 过滤查询
	 * 
	 * @param pageNum      当前页(Integer----长度5)
	 * @param pageSize   页面大小(Integer----长度3,最大值为100)
	 * @param className  班级名称
	 * @param specialtyId 专业标识
	 * @param yearPlanId 年份标识
	 * @param remark 备注
	 * @return 
	 */
	@RequestMapping("/selectClassName")
	public PageInfo<ClassInfo> selectClassName(@RequestBody String str) {
		// 使用fastjson转为json对象
		JSONObject jsonObject = JSON.parseObject(str);
		ClassInfo classInfo = new ClassInfo();
		// 过滤查询条件
		String className = jsonObject.getString("className");
		classInfo.setClassName(StringUtil.isEmpty(className) ? null : className);
		// 当前页面
		int pageNum = jsonObject.getIntValue("pageNum");
		// 页面大小
		int pageSize = jsonObject.getIntValue("pageSize");
		return classInfoService.listClassName(classInfo, pageNum, pageSize);
	}
	/**
	 * 插入班级信息
	 * 
	 * @param id          ID (Integer----长度2)
	 * @param className  班级名称
	 * @param specialtyId 专业标识
	 * @param yearPlanId 年份标识
	 * @param state       状态(Char----长度1)
	 * @param stateDate   时间(Date----长度0)
	 * @param remark 备注
	 */
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	public void addTerm(@RequestBody ClassInfo classInfo){
		// 验证数据是否为空
		DataExceptionUtil.stringEmpty(classInfo.getClassName(), "班级名称不能为空");
		DataExceptionUtil.stringEmpty(classInfo.getRemark(), "备注不能为空");
		// 调用添加数据接口
		classInfoService.insert(classInfo);
	}
	/**
	 * 更新
	 * 
	 * @param id          ID (Integer----长度2)
	 * @param className  班级名称
	 * @param specialtyId 专业标识
	 * @param yearPlanId 年份标识
	 * @param state       状态(Char----长度1)
	 * @param stateDate   时间(Date----长度0)
	 * @param remark 备注
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public void update(@RequestBody ClassInfo classInfo) {
		// 验证数据是否合法

		// 更新操作
		classInfoService.update(classInfo);
	}
	/**
	 * 逻辑删除
	 * 
	 * @param id 班级标识(Integer----长度5)
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		classInfoService.delete(id);
	}
	
	/**
	 * 批量逻辑删除班级信息
	 * 
	 * @param id 班级标识(Integer----长度5)
	 */
	@RequestMapping(value = "/deleteSelection",method = RequestMethod.POST)
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return classInfoService.deleteSelection(idList);
	}
	
	@RequestMapping(value ="/listBySpecialtyId",method =RequestMethod.POST)
	public List<ClassInfo> listBySpecialtyId(@RequestBody ClassInfo classInfo){
		return classInfoService.listBySpecialtyId(classInfo);
	}

	@RequestMapping(value ="/listByYearPlanId",method =RequestMethod.POST)
	public List<ClassInfo> listByYearPlanId(@RequestBody ClassInfo classInfo){
		return classInfoService.listByYearPlanId(classInfo);
	}
	
	@RequestMapping(value ="/getClassNameByYearPlanIdSpecialtyId",method =RequestMethod.POST)
	public List<ClassInfo> getClassNameByYearPlanIdSpecialtyId(@RequestBody ClassInfo classInfo){
		return classInfoService.getClassNameByYearPlanIdSpecialtyId(classInfo);
	}
	
	
}
