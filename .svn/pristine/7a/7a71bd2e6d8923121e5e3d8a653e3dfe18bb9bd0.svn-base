package org.jit.sose.controller.edu;

import java.util.List;


import org.jit.sose.entity.CourseProp;
import org.jit.sose.service.CoursePropService;
import org.jit.sose.util.DataExceptionUtil;
import org.jit.sose.util.StringUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

/**
 * 控制器类
 * @author 牛开专
 *
 */
@RestController
@RequestMapping("/edu/courseProp")
public class CoursePropController {
	
	@Autowired
	private CoursePropService coursePropService;
	
	/**
	 * 查询课程性质和id集合
	 * 
	 * @author 王越
	 * @return 课程性质和id集合
	 */
	@RequestMapping(value = "/selectCoursePropList", method = RequestMethod.GET)
	public List<CourseProp> selectCoursePropList() {
		return coursePropService.selectCoursePropList();
	}
	
	/**
	 * 过滤查询
	 * 
	 * @param start      当前页(Integer----长度5)
	 * @param pageSize   页面大小(Integer----长度3,最大值为100)
	 * @param propName 课程种类 (String----长度20)
	 * @return 课程种类信息集合
	 */
	@RequestMapping(value = "/selectByCourseProp",method = RequestMethod.POST)
	public PageInfo<CourseProp> selectByCourseProp(@RequestBody String str) {
		// 使用fastjson转为json对象
		JSONObject jsonObject = JSON.parseObject(str);
		CourseProp courseProp = new CourseProp();
		// 过滤查询条件
		String propName = jsonObject.getString("propName");
		courseProp.setPropName(StringUtil.isEmpty(propName)?null:propName);
		// 当前页面
		int pageNum = jsonObject.getIntValue("pageNum");
		// 页面大小
		int pageSize = jsonObject.getIntValue("pageSize");
		return coursePropService.listByCourseProp(courseProp, pageNum, pageSize);
	}
	
	/**
	 * 新增课程信息
	 * 
	 * @param id          类别ID (Integer----长度2)
	 * @param propName    类别名称(String----长度200)
	 * @param createdDate 创建时间(Date----长度0)
	 * @param state       审核状态(Char----长度1)
	 * @param stateDate   审核时间(Date----长度0)
	 * @return success/fail
	 */
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	public void addType(@RequestBody CourseProp courseProp) {
		// 验证数据是否为空
		DataExceptionUtil.stringEmpty(courseProp.getPropName(), "课程性质不能为空");
		//用户id
		courseProp.setUserId(1);
		// 调用添加数据接口
		coursePropService.insert(courseProp);
	}
	
	/**
	 * 更新课程信息
	 * 
	 * @param id         课程标识(Integer----长度5)
	 * @param propName    类别名称(String----长度200)
	 * @param createdDate 创建时间(Date----长度0)
	 * @return success/fail
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public void update(@RequestBody CourseProp courseProp) {
		// 验证数据是否合法

		// 更新操作
		coursePropService.update(courseProp);
	}
	
	/**
	 * 逻辑删除课程信息
	 * 
	 * @param id 课程标识(Integer----长度5)
	 * @return map
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		coursePropService.delete(id);
	}
	
	/**
	 * 批量逻辑删除课程信息
	 * 
	 * @param id 课程标识(Integer----长度5)
	 * @return map
	 */
	@RequestMapping(value = "/deleteSelection",method = RequestMethod.POST)
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return coursePropService.deleteSelection(idList);
	}
	
}
