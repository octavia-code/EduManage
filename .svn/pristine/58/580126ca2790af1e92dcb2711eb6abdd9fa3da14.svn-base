package org.jit.sose.controller.manage;

import java.util.List;
import org.jit.sose.entity.Specialty;
import org.jit.sose.service.SpecialtyService;
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

@RestController
@RequestMapping("/manage/specialty")
public class SpecialtyController {

	@Autowired
	private SpecialtyService specialtyService;

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@RequestBody Specialty specialty) {
		String result = specialtyService.insert(specialty);
		return ResponseUtil.success(result);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody Specialty specialty) {
		// @RequestBody 自动根据参数类型转换值
		// 验证数据是否合法

		// 更新操作
		specialtyService.update(specialty);
	}

	/**
	 * 逻辑删除专业信息
	 * 
	 * @param id 标识(Integer----长度5)
	 * @return map
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		specialtyService.delete(id);
	}


	/**
	 * 批量逻辑删除专业信息
	 * 
	 * @param id 标识(Integer----长度5)
	 * @return map
	 */
	@RequestMapping(value = "/deleteSelection", method = RequestMethod.POST)
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return specialtyService.deleteSelection(idList);
	}

	/**
	 * 过滤查询
	 * 
	 * @param start      当前页(Integer----长度5)
	 * @param pageSize   页面大小(Integer----长度3,最大值为100)
	 * @param subjectName 专业名 (String----长度200)
	 * @return 专业信息集合
	 */
	@RequestMapping(value = "/selectPageInfo", method = RequestMethod.POST)
	public PageInfo<Specialty> selectPageInfo(@RequestBody String str) {
		// 使用fastjson转为json对象
		JSONObject strj = JSON.parseObject(str);
		Specialty specialty = new Specialty();

		// 过滤查询条件
		String subjectName = strj.getString("subjectName");
		String remark = strj.getString("remark");
		specialty.setSubjectName(StringUtil.isEmpty(subjectName) ? null : subjectName);
		specialty.setRemark(StringUtil.isEmpty(remark) ? null : remark);

		// 当前页码
		int pageNum = strj.getIntValue("pageNum");
		// 页面大小
		int pageSize = strj.getIntValue("pageSize");
		return specialtyService.selectPageInfo(specialty, pageNum, pageSize);
	}
	
	/**
	 * 查询专业id和专业名称集合
	 * 
	 * @return 专业id和专业名称集合
	 * @author 王锐
	 */
	@RequestMapping(value = "/selectSpecialtyList", method = RequestMethod.GET)
	public List<Specialty> selectSpecialtyList() {
		List<Specialty> specialtyList = specialtyService.selectSpecialtyList();
		return specialtyList;
	}
	/**
	 * 导入excel文件
	 * @throws IOException 
	 * @throws Exception 
	 * 
	 
	 
	@RequestMapping(value = "/excelExport", method = RequestMethod.GET)
	public String ExcelExport() throws Exception{
		Workbook wb = new HSSFWorkbook();
		String headers[]={"专业名称","备注信息"};
		ResultSet rs = (ResultSet)specialtyService.selectSpecialtyList();
		try {
			ExcelUtil.ecxelExport(rs, wb, headers);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileOutputStream file = new FileOutputStream("d:\\specialty.xls");
		wb.write(file);
		file.close();
		return null;
	}
	
	@RequestMapping("/excelExport")
	public void export(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    Specialty specialty = new Specialty();//创建实体类对象
	    List<Specialty> specialtyList = specialtyService.selectSpecialtyList();
	    ExcelUtil<Specialty> sp= new ExcelUtil<Specialty>();
	    String[] headers = {"Id","专业信息","备注信息"};
	    String fileName = "导出specialty";
	    sp.exportExcel(headers,specialtyList,fileName,response);
	}
	*/
	/**
	 * 根据学院标识查询id与专业名称
	 * 
	 * @return 专业id和专业名称集合
	 * @author 王锐
	 */
	@RequestMapping(value = "/selectBySpecialty", method = RequestMethod.POST)
	public List<Specialty> selcetBySpecialty(@RequestBody Specialty specialty) {
		List<Specialty> specialtyList = specialtyService.selectBySpecialty(specialty);
		return specialtyList;
	}
}
