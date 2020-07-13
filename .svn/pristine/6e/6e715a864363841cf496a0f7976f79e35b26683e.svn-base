package org.jit.sose.controller.info;

import java.util.List;

import org.jit.sose.entity.StaffInfo;
import org.jit.sose.service.StaffInfoService;
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
@RequestMapping("/info/staffInfo")
public class StaffInfoController {

	@Autowired
	private StaffInfoService staffInfoService;
	
	/**
	 * 查询教师工号和教师名称集合
	 * 
	 * @return 教师工号和教师名称集合
	 */
	@RequestMapping(value = "/selectStaffInfoList", method = RequestMethod.GET)
	public List<StaffInfo> selectStaffInfoList() {
		List<StaffInfo> staffInfoList = staffInfoService.selectStaffInfoList();
		return staffInfoList;
	}
	
	/**
	 * 插入教师信息
	 * 
	 * @param staffName 教师名称 (String----长度50)
	 * @param staffCode 教师编码 (String----长度50)
	 * @param userId 	用户标识 (Integer----长度2)
	 * @param birthday 	出生日期 (Date)
	 * @param sex 		性别 (String----长度1)
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insert(@RequestBody StaffInfo staffInfo) {
		// 验证数据格式是否正确==========未写好

		// 验证数据是否为空
		DataExceptionUtil.stringEmpty(staffInfo.getStaffName(), "教师姓名不能为空");
		DataExceptionUtil.stringEmpty(staffInfo.getStaffCode(), "教师编码不能为空");
		// 验证数据长度
		DataExceptionUtil.stringLength(staffInfo.getStaffName(), 50, "教师姓名长度最多为50");
		DataExceptionUtil.stringLength(staffInfo.getStaffCode(), 50, "教师编码长度最多为50");
		
		// 暂设置用户id为1
		staffInfo.setUserId(1);
		
		// 调用添加数据接口
		staffInfoService.insert(staffInfo);
	}

	/**
	 * 更新教师信息
	 * 
	 * @param staffName 教师名称 (String----长度50)
	 * @param staffCode 教师编码 (String----长度50)
	 * @param userId 	用户标识 (Integer----长度2)
	 * @param birthday 	出生日期 (Date)
	 * @param sex 		性别 (String----长度1)
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody StaffInfo staffInfo) {
		// @RequestBody 自动根据参数类型转换值
		// 验证数据是否合法
		// 暂设置用户id为1
		staffInfo.setUserId(1);
		// 更新操作
		staffInfoService.update(staffInfo);
	}
	
	/**
	 * 逻辑删除教师信息
	 * 
	 * @param id 教师标识(Integer----长度2)
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		staffInfoService.delete(id);
	}

	/**
	 * 批量逻辑删除教师信息
	 * 
	 * @param id 教师标识(Integer----长度2)
	 * @return 成功删除的记录数
	 */
	@RequestMapping(value = "/deleteSelection", method = RequestMethod.POST)
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return staffInfoService.deleteSelection(idList);
	}
	
	/**
	 * 过滤查询
	 * 
	 * @param start      	当前页(Integer----长度5)
	 * @param pageSize   	页面大小(Integer----长度3,最大值为100)
	 * @param staffName 	教师姓名 (String----长度50)
	 * @param staffCode 	教师编码 (String----长度50)
	 * @return 教师信息集合
	 */
	@RequestMapping(value = "/selectPageInfo", method = RequestMethod.POST)
	public PageInfo<StaffInfo> selectPageInfo(@RequestBody String str) {
		// 使用fastjson转为json对象
		JSONObject strj = JSON.parseObject(str);
		StaffInfo staffInfo = new StaffInfo();

		// 过滤查询条件
		String staffName = strj.getString("staffName");
		String staffCode = strj.getString("staffCode");
		staffInfo.setStaffName(StringUtil.isEmpty(staffName) ? null : staffName);
		staffInfo.setStaffCode(StringUtil.isEmpty(staffCode) ? null : staffCode);

		// 当前页码
		int pageNum = strj.getIntValue("pageNum");
		// 页面大小
		int pageSize = strj.getIntValue("pageSize");
		return staffInfoService.selectPageInfo(staffInfo, pageNum, pageSize);
	}

}

