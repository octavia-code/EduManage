package org.jit.sose.controller.indicator;

import java.util.List;

import org.jit.sose.entity.GraduationRequireIndicator;
import org.jit.sose.entity.IndicatorFirst;
import org.jit.sose.entity.IndicatorRelat;
import org.jit.sose.entity.IndicatorSec;
import org.jit.sose.entity.query.IndicatorRelatQuery;
import org.jit.sose.entity.vo.RelatTreeVo;
import org.jit.sose.exception.DataFormatException;
import org.jit.sose.service.IndicatorRelatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 指标关联控制层
 * 
 * @author: 王越
 * @date: 2019-07-02 14:23:02
 */
@RestController
@RequestMapping("/indicator/indicatorRelat")
public class IndicatorRelatController {

	@Autowired
	private IndicatorRelatService indicatorRelatService;

	/**
	 * 查询毕业要求指标点标题
	 */
	@RequestMapping(value = "/selectIndicatorList", method = RequestMethod.POST)
	public List<RelatTreeVo> selectIndicatorList(@RequestBody IndicatorRelatQuery query) {
		// 课程信息id为空，设置一个0值让其在sql中传递
		if (query.getCourseInfoId() == null) {
			query.setCourseInfoId(0);
		}
		return indicatorRelatService.selectIndicatorListTree(query);
	}

	/**
	 * 查询在指标关联表中未使用的指标点集合
	 * 
	 */
	@RequestMapping(value = "/selectNotUseIndicatorList", method = RequestMethod.GET)
	public List<GraduationRequireIndicator> selectNotUseIndicatorList() {
		return indicatorRelatService.selectNotUseIndicatorList();
	}

	/**
	 * 查询在指标关联表中未使用的一级指标集合
	 * 
	 * @param indicatorId 指标点id
	 * @return
	 */
	@RequestMapping(value = "/selectNotUseIndicatorFirstList", method = RequestMethod.POST)
	public List<IndicatorFirst> selectNotUseIndicatorFirstList(@RequestBody Integer indicatorId) {
		return indicatorRelatService.selectNotUseIndicatorFirstList(indicatorId);
	}

	/**
	 * 查询在指标关联表中未使用的二级指标集合
	 * 
	 */
	@RequestMapping(value = "/selectNotUseIndicatorSecList", method = RequestMethod.POST)
	public List<IndicatorSec> selectNotUseIndicatorSecList(@RequestBody IndicatorRelat indicatorRelat) {
		return indicatorRelatService.selectNotUseIndicatorSecList(indicatorRelat.getIndicatorId(),
				indicatorRelat.getIndicatorFirstId());
	}

	/**
	 * 插入指标关联
	 * @param indicatorRelat
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insert(@RequestBody IndicatorRelat indicatorRelat) {
		if (indicatorRelat.getIndicatorId() == null) {
			throw new DataFormatException("indicatorId Cannot be null");
		}
		indicatorRelatService.insert(indicatorRelat);
	}

	/**
	 * 更新指标关联
	 * 
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
	public void update(@RequestBody IndicatorRelat indicatorRelat) {
		// 更新操作
		indicatorRelatService.update(indicatorRelat);
	}

	/**
	 * 逻辑删除指标关联，根据标题id，一级指标id，二级指标id
	 * 
	 * @param indicatorRelat
	 * @return 成功删除的记录
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Integer delete(@RequestBody IndicatorRelat indicatorRelat) {
		if (indicatorRelat.getIndicatorId() == null) {
			throw new DataFormatException("indicatorId Cannot be null");
		}
		return indicatorRelatService.delete(indicatorRelat);
	}

}
