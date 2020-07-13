package org.jit.sose.controller.info;

import java.util.List;

import org.jit.sose.entity.QuestionBank;
import org.jit.sose.service.QuestionBankService;
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


@RestController
@RequestMapping("/info/questionBank")
public class QuestionBankController {

	@Autowired
	private QuestionBankService questionBankService;
	
	/**
	 * 查询课程信息和id集合
	 * 
	 * @author 王越
	 * @return 课程信息和id集合
	 */
	@RequestMapping(value = "/selectQuestionBankList", method = RequestMethod.GET)
	public List<QuestionBank> selectQuestionBankList() {
		return questionBankService.selectQuestionBankList();
	}

	/**
	 * 插入学院信息
	 * 
	 * @param schoolName
	 *            学院名称 (varchar----长度50)
	 * @param remark
	 *            备注(int----长度4000)
	 * @return success/fail
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insert(@RequestBody QuestionBank questionBank) {
		// 验证数据是否为空
		DataExceptionUtil.stringEmpty(questionBank.getStem(), "题干不能为空");
		// 验证数据长度
		DataExceptionUtil.stringLength(questionBank.getSubject(), 200, "学科长度最多为50");
		// 调用添加数据接口
		questionBankService.insert(questionBank);

	}

	/**
	 * 更新学院信息
	 * 
	 * @param schoolName
	 *            学院名称 (varchar----长度50)
	 * @param remark
	 *            备注(int----长度4000)
	 * @return success/fail
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody QuestionBank questionBank) {
		// 更新操作
		 questionBankService.update(questionBank);
	}

	/**
	 * 逻辑删除学院信息
	 * 
	 * @param id
	 *            学院信息标识标识(Integer----长度2)
	 * @return map
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		questionBankService.delete(id);
	}

	/**
	 * 批量逻辑删除学院信息
	 * 
	 * @param id
	 *            学院信息标识标识(Integer----长度2)
	 * @return 成功删除的记录数
	 */
	@RequestMapping(value = "/deleteSelection", method = RequestMethod.POST)
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return questionBankService.deleteSelection(idList);
	}

	/**
	 * 过滤查询
	 * 
	 * @param start
	 *            当前页(Integer----长度5)
	 * @param pageSize
	 *            页面大小(Integer----长度3,最大值为100)
	 * @param schoolName
	 *            学院名称 (varchar----长度50)
	 * @return 学院信息集合
	 */
	@RequestMapping(value = "/selectPageInfo", method = RequestMethod.POST)
	public PageInfo<QuestionBank> selectPageInfo(@RequestBody String str) {
		// 使用fastjson转为json对象
		JSONObject strj = JSON.parseObject(str);
		QuestionBank questionBank = new QuestionBank();
		// 过滤查询条件
		String subject = strj.getString("subject");
		questionBank.setSubject(StringUtil.isEmpty(subject) ? null : subject);
		// 当前页码
		int pageNum = strj.getIntValue("pageNum");
		// 页面大小
		int pageSize = strj.getIntValue("pageSize");
		return questionBankService.selectPageInfo(questionBank, pageNum, pageSize);
	}
	
	@RequestMapping(value = "/selectFiveQuestion", method = RequestMethod.GET)
	public List<QuestionBank> selectFiveQuestion(){
	    return questionBankService.selectFiveQuestion();
	}
	
	/**
	 * 
	 */
}
