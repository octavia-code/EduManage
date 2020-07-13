package org.jit.sose.controller.info;

import java.util.List;

import org.jit.sose.entity.ChoiceCourseNo;
import org.jit.sose.service.ChoiceCourseNoService;
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
 * 选课课号控制器类
 * @author 王锐
 *
 */
@RestController
@RequestMapping("/info/choiceCourseNo")
public class ChoiceCourseNoController {
	@Autowired
	private ChoiceCourseNoService choiceCourseNoService;
	
	/**
	 * 查询选课课号信息id和选课课号名称集合
	 * 
	 * @return 选课课号信息id和选课课号名称集合
	 */
	@RequestMapping(value = "/selectChoiceCourseNoList", method = RequestMethod.GET)
	public List<ChoiceCourseNo> selectChoiceCourseNoList() {
		List<ChoiceCourseNo> choiceCourseNoList = choiceCourseNoService.selectChoiceCourseNoList();
		return choiceCourseNoList;
	}
	
	/**
	 * 通过学年id，教师id，课程大纲id查询:选课课号信息id,选课课号名称
	 * 
	 * @return 选课课号信息id和选课课号名称
	 */
	@RequestMapping(value = "/selectByChoiceCourseNo", method = RequestMethod.POST)
	public ChoiceCourseNo selectByChoiceCourseNo(@RequestBody ChoiceCourseNo record){
		ChoiceCourseNo choiceCourseNo = choiceCourseNoService.selectByChoiceCourseNo(record);
		return choiceCourseNo;
	}

	/**
	 * 过滤查询
	 * 
	 * @param pageNum      当前页(Integer----长度5)
	 * @param pageSize   页面大小(Integer----长度3,最大值为100)
	 * @param courseNo  选课课号
	 * @param yearTermId 学年标识
	 * @param staffInfoId 课程教师表标识
	 * @param courseOutlineId 课程大纲标识
	 * @param remark 备注
	 * @return 
	 */
	@RequestMapping("/selectPageInfo")
	public PageInfo<ChoiceCourseNo> selectChoiceCourseNo(@RequestBody String str) {
		// 使用fastjson转为json对象
		JSONObject jsonObject = JSON.parseObject(str);
		ChoiceCourseNo choiceCourseNo = new ChoiceCourseNo();
		// 过滤查询条件
		String termName = jsonObject.getString("termName");
		String outlineName = jsonObject.getString("outlineName");
		String staffName = jsonObject.getString("staffName");
		choiceCourseNo.setTermName(StringUtil.isEmpty(termName) ? null : termName);
		choiceCourseNo.setOutlineName(StringUtil.isEmpty(outlineName) ? null : outlineName);
		choiceCourseNo.setStaffName(StringUtil.isEmpty(staffName) ? null : staffName);
		// 当前页面
		int pageNum = jsonObject.getIntValue("pageNum");
		// 页面大小
		int pageSize = jsonObject.getIntValue("pageSize");
		return choiceCourseNoService.listChoiceCourseNo(choiceCourseNo, pageNum, pageSize);
	}
	/**
	 * 插入选课课号信息
	 * 
	 * @param id          ID (Integer----长度2)
	 * @param courseNo  选课课号
	 * @param yearTermId 学年标识
	 * @param staffInfoId 课程教师表标识
	 * @param courseOutlineId 课程大纲标识
	 * @param state       状态(Char----长度1)
	 * @param stateDate   时间(Date----长度0)
	 * @param remark 备注
	 */
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	public void addTerm(@RequestBody ChoiceCourseNo choiceCourseNo){
		// 验证数据是否为空
		DataExceptionUtil.stringEmpty(choiceCourseNo.getCourseNo(), "选课课号不能为空");
		// 调用添加数据接口
		choiceCourseNoService.insert(choiceCourseNo);
	}
	/**
	 * 更新
	 * 
	 * @param id          ID (Integer----长度2)
	 * @param courseNo  选课课号
	 * @param yearTermId 学年标识
	 * @param staffInfoId 课程教师表标识
	 * @param courseOutlineId 课程大纲标识
	 * @param state       状态(Char----长度1)
	 * @param stateDate   时间(Date----长度0)
	 * @param remark 备注
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public void update(@RequestBody ChoiceCourseNo choiceCourseNo) {
		// 验证数据是否合法

		// 更新操作
		choiceCourseNoService.update(choiceCourseNo);
	}
	/**
	 * 逻辑删除
	 * 
	 * @param id 选课课号标识(Integer----长度5)
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		choiceCourseNoService.delete(id);
	}
	
	/**
	 * 批量逻辑删除选课课号信息
	 * 
	 * @param id 选课课号标识(Integer----长度5)
	 */
	@RequestMapping(value = "/deleteSelection",method = RequestMethod.POST)
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return choiceCourseNoService.deleteSelection(idList);
	}
}