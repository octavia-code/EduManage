package org.jit.sose.controller.info;

import java.util.List;

import org.jit.sose.entity.SchoolInfo;
import org.jit.sose.service.SchoolInfoService;
import org.jit.sose.util.DataExceptionUtil;
import org.jit.sose.util.ResponseUtil;
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
 * 学院信息控制器类
 * 
 * @author 单爽
 *
 */

@RestController
@RequestMapping("/info/schoolInfo")
public class SchoolInfoController {
	@Autowired
	private SchoolInfoService schoolInfoService;

	/**
	 * 查询课程信息和id集合
	 * 
	 * @author 王越
	 * @return 课程信息和id集合
	 */
	@RequestMapping(value = "/selectSchoolInfoList", method = RequestMethod.GET)
	public List<SchoolInfo> selectSchoolInfoList() {
		return schoolInfoService.selectSchoolInfoList();
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
	public String insert(@RequestBody SchoolInfo schoolInfo) {
		// 验证数据是否为空
		DataExceptionUtil.stringEmpty(schoolInfo.getSchoolName(), "学院名称不能为空");
		// 验证数据长度
		DataExceptionUtil.stringLength(schoolInfo.getSchoolName(), 200, "学院名称长度最多为50");
		// 调用添加数据接口
		String result = schoolInfoService.insert(schoolInfo);

		return ResponseUtil.success(result);
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
	public String update(@RequestBody SchoolInfo schoolInfo) {
		// 更新操作
		String result = schoolInfoService.update(schoolInfo);
		return ResponseUtil.success(result);
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
		schoolInfoService.delete(id);
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
		return schoolInfoService.deleteSelection(idList);
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
	public PageInfo<SchoolInfo> selectPageInfo(@RequestBody String str) {
		// 使用fastjson转为json对象
		JSONObject strj = JSON.parseObject(str);
		SchoolInfo schoolInfo = new SchoolInfo();
		// 过滤查询条件
		String schoolName = strj.getString("schoolName");
		schoolInfo.setSchoolName(StringUtil.isEmpty(schoolName) ? null : schoolName);
		// 当前页码
		int pageNum = strj.getIntValue("pageNum");
		// 页面大小
		int pageSize = strj.getIntValue("pageSize");
		return schoolInfoService.selectPageInfo(schoolInfo, pageNum, pageSize);
	}

}
