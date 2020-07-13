package org.jit.sose.controller.manage;

import java.util.List;


import org.jit.sose.entity.TermInfo;
import org.jit.sose.service.TermInfoService;
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
 * 学期分配控制器类
 * @author 牛开专
 *
 */
@RestController
@RequestMapping("/manage/termInfo")
public class TermInfoController {
	@Autowired
	private TermInfoService termInfoService;
	
	/**
	 * 查询学期信息和id集合
	 * 
	 * @author 王越
	 * @return 学期信息和id集合
	 */
	@RequestMapping(value = "/selectTermInfoList", method = RequestMethod.GET)
	public List<TermInfo> selectTermInfoList() {
		return termInfoService.selectTermInfoList();
	}
	
	
	
	/**
	 * 插入学期分配
	 * 
	 * @param id          ID (Integer----长度2)
	 * @param termName    學期名称(String----长度200)
	 * @param remark
	 * @param state       状态(Char----长度1)
	 * @param stateDate   时间(Date----长度0)
	 */
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	public void addTerm(@RequestBody TermInfo termInfo){
		// 验证数据是否为空
		DataExceptionUtil.stringEmpty(termInfo.getTermName(), "学期名称不能为空");
		
		// 调用添加数据接口
		termInfoService.insertTermInfo(termInfo);
	}
	
	/**
	 * 更新
	 * 
	 * @param id         标识(Integer----长度5)
	 * @param termName    名称(String----长度200)
	 * @param remark    备注（String）
	 * @return success/fail
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public void update(@RequestBody TermInfo termInfo) {
		// 验证数据是否合法

		// 更新操作
		termInfoService.updateTermInfo(termInfo);
	}
	
	/**
	 * 过滤查询
	 * 
	 * @param start      当前页(Integer----长度5)
	 * @param pageSize   页面大小(Integer----长度3,最大值为100)
	 * @param termName 
	 * @return 
	 */
	@RequestMapping("/selectTermName")
	public PageInfo<TermInfo> selectTermName(@RequestBody String str) {
		// 使用fastjson转为json对象
		JSONObject jsonObject = JSON.parseObject(str);
		TermInfo termInfo = new TermInfo();
		// 过滤查询条件
		String termName = jsonObject.getString("termName");
		termInfo.setTermName(StringUtil.isEmpty(termName) ? null : termName);
		// 当前页面
		int pageNum = jsonObject.getIntValue("pageNum");
		// 页面大小
		int pageSize = jsonObject.getIntValue("pageSize");
		return termInfoService.listTermName(termInfo, pageNum, pageSize);
		
	}
	
	
	/**
	 * 逻辑删除
	 * 
	 * @param id 课程标识(Integer----长度5)
	 * @return map
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		termInfoService.deleteTermInfo(id);
	}
	
	/**
	 * 批量逻辑删除学期分配信息
	 * 
	 * @param id 课程标识(Integer----长度5)
	 * @return map
	 */
	@RequestMapping(value = "/deleteSelection",method = RequestMethod.POST)
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return termInfoService.deleteSelection(idList);
	}
	
}
