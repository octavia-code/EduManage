package org.jit.sose.controller.info;

import org.jit.sose.entity.AchieveDegree;
import org.jit.sose.service.AchieveDegreeService;
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
@RequestMapping("/info/achieveDegree")
public class AchieveDegreeController {


	@Autowired
	private AchieveDegreeService achieveDegreeService;
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		achieveDegreeService.delete(id);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody AchieveDegree achieveDegree) {
		if(achieveDegree.getId()==null){
			achieveDegreeService.updateScore(achieveDegree);
		}
		else{
			// 更新操作
			achieveDegreeService.update(achieveDegree);
		}
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insert(@RequestBody AchieveDegree achieveDegree) {
		// 验证数据是否为空
		DataExceptionUtil.stringEmpty(achieveDegree.getUserCode(), "学号不能为空");
		// 调用添加数据接口
		achieveDegreeService.insert(achieveDegree);
	}
	
	@RequestMapping(value = "/selectPageInfo", method = RequestMethod.POST)
	public PageInfo<AchieveDegree> selectPageInfo(@RequestBody String str) {
		// 使用fastjson转为json对象
		JSONObject strj = JSON.parseObject(str);
		AchieveDegree achieveDegree = new AchieveDegree();
		// 过滤查询条件
		String userCode = strj.getString("userCode");
		achieveDegree.setUserCode(StringUtil.isEmpty(userCode) ? null : userCode);
		// 当前页码
		int pageNum = strj.getIntValue("pageNum");
		// 页面大小
		int pageSize = strj.getIntValue("pageSize");
		return achieveDegreeService.selectPageInfo(achieveDegree, pageNum, pageSize);
	}
	
}
