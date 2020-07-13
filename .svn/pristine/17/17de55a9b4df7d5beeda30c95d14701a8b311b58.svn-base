package org.jit.sose.controller.config;

import java.util.List;

import org.jit.sose.entity.CourseProp;
import org.jit.sose.entity.Eecstate;
import org.jit.sose.service.EecstateService;
import org.jit.sose.util.ResponseUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 域表信息控制器类
 * 
 * @author lxz
 *
 */
@Slf4j
@RestController
@RequestMapping("/config/eecstate")
public class EecstateController {
	@Autowired
	private EecstateService eecstateService;
	/**
	 * 插入域表信息
	 * 
	 * @param table_name
	 *            表名 (String----长度20)
	 * @param colm_name
	 *            列名(String----长度20)
	 * @param code
	 *            编码(char----长度1)
	 * @param code_name
	 *            编码名(Stringr----长度20)
	 * @param seq
	 *            序号(Integer----长度2)
	 * @param remark
	 *            备注(String----长度200)
	 * @return success/fail
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@RequestBody Eecstate eecstate) {
		String result = eecstateService.insert(eecstate);
		return ResponseUtil.success(result);
	}
	/**
	 * 插入域表信息
	 * 
	 * @param table_name
	 *            表名 (String----长度20)
	 * @param colm_name
	 *            列名(String----长度20)
	 * @param code
	 *            编码(char----长度1)
	 * @param code_name
	 *            编码名(Stringr----长度20)
	 * @param seq
	 *            序号(Integer----长度2)
	 * @param remark
	 *            备注(Stringr----长度200)
	 * @return success/fail
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody Eecstate eecstate) {
		eecstateService.update(eecstate);
	}	
	/**
	 * 逻辑删除课程信息
	 * 
	 * @param id
	 *            课程标识(Integer----长度5)
	 * @return map
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		eecstateService.delete(id);
	}
	/**
	 * 批量逻辑删除课程信息
	 * 
	 * @param id
	 *            课程标识(Integer----长度5)
	 * @return map
	 */
	@RequestMapping(value = "/deleteSelection", method = RequestMethod.POST)
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return eecstateService.deleteSelection(idList);
	}

	/**
	 * 过滤查询
	 * 
	 * @param start
	 *            当前页(Integer----长度5)
	 * @param pageSize
	 *            页面大小(Integer----长度3,最大值为100)
	 * @param courseCode
	 *            课程编码 (String----长度20)
	 * @param courseName
	 *            课程名称 (String----长度20)
	 * @return 课程信息集合
	 */
	@RequestMapping(value = "/selectPageInfo", method = RequestMethod.POST)
	public PageInfo<Eecstate> selectPageInfo(@RequestBody String str) {
		JSONObject strj = new JSONObject(str);
		Eecstate eecstate = new Eecstate();
		// 过滤查询条件
		eecstate.setTableName("".equals(strj.getString("tableName")) ? null : strj.getString("tableName"));
		eecstate.setColmName("".equals(strj.getString("colmName")) ? null : strj.getString("colmName"));
		// 当前页索引
		Integer pageNum = strj.getInt("pageNum");
		// 当前页页面大小
		Integer pageSize = strj.getInt("pageSize");
		return eecstateService.selectPageInfo(eecstate, pageNum, pageSize); 
	}
	
	

}
