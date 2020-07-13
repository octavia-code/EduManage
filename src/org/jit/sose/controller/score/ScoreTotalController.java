package org.jit.sose.controller.score;

import java.util.List;

import org.jit.sose.entity.ScoreTotal;
import org.jit.sose.service.ScoreTotalService;
import org.jit.sose.util.FastjsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
/**
 * 总成绩控制器类
 * 
 * @author nkz
 *
 */
@RestController
@RequestMapping("/score/scoreTotal")
public class ScoreTotalController {
	
	@Autowired
	private ScoreTotalService scoreTotalService;

	/**
	 * 查询总成绩和id集合
	 * @return
	 */
	@RequestMapping(value = "/selectScoreTotalList", method = RequestMethod.GET)
	public List<ScoreTotal> selectScoreTotalList() {
		return scoreTotalService.selectScoreTotalList();
	}
	
	/**
	 * 过滤查询
	 * 
	 * @param start      当前页(Integer----长度5)
	 * @param pageSize   页面大小(Integer----长度3,最大值为100)
	 *
	 * @return 
	 */
	@RequestMapping(value = "/selectScoreTotal",method = RequestMethod.GET)
	public PageInfo<ScoreTotal> selectScoreTotal(String str) {
		// 使用fastjson转为json对象
		JSONObject jsonObject = JSON.parseObject(str);
		ScoreTotal scoreTotal = new ScoreTotal();
		// 过滤查询条件
		
//		String termName = jsonObject.getString("termName");
//		termInfo.setTermName(StringUtil.isEmpty(termName) ? null : termName);
		// 当前页面
//		int pageNum = jsonObject.getIntValue("pageNum");
//		// 页面大小
//		int pageSize = jsonObject.getIntValue("pageSize");
		return scoreTotalService.listScoreTotal(scoreTotal, 1, 10);
	}
	
	/**
	 * 插入
	 * 
	 * @param id                 	标识列(Integer----长度7)
	 * @param couId    				课程班级学生信息标识(Integer----长度5)
	 * @param totalScore			总分(Integer----长度3)
	 * @param studentState			学生状态(Char----长度1)
	 * @param state       			状态(Char----长度1)
	 * @param createdDate 			创建日期(Date----长度0)
	 * @param stateDate   			状态日期(Date----长度0)
	 */
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	public void insert(@RequestBody String str){
		//总成绩表主键id集合
		List<Integer> idList = FastjsonUtil.toIntegerList(str, "idList");
		System.out.println(idList);
		//课程班级学生信息id
		List<Integer> couIdList = FastjsonUtil.toIntegerList(str, "couIdList");
		System.out.println(couIdList);
		//总成绩集合
		List<Double> totalScoreList = FastjsonUtil.toDoubleList(str, "totalScoreList");
		System.out.println(totalScoreList);
		// 调用添加数据接口
		scoreTotalService.insert(idList,couIdList,totalScoreList);
	}
	
	/**
	 * 更新
	 * 
	 * @param id                 	标识列(Integer----长度7)
	 * @param couId    				课程班级学生信息标识(Integer----长度5)
	 * @param totalScore			总分(Integer----长度3)
	 * @param studentState			学生状态(Char----长度1)
	 * @param state       			状态(Char----长度1)
	 * @param createdDate 			创建日期(Date----长度0)
	 * @param stateDate   			状态日期(Date----长度0)
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public void update(@RequestBody ScoreTotal scoreTotal) {
		// 验证数据是否合法

		// 更新操作
		scoreTotalService.update(scoreTotal);
	}

	/**
	 * 逻辑删除
	 * 
	 * @param id 总成绩标识(Integer----长度7)
	 * 
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		scoreTotalService.delete(id);
	}
	
	/**
	 * 批量逻辑删除
	 * 
	 * @param id 总成绩标识(Integer----长度7)
	 * @return
	 */
	@RequestMapping(value = "/deleteSelection",method = RequestMethod.POST)
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return scoreTotalService.deleteSelection(idList);
	}
}
