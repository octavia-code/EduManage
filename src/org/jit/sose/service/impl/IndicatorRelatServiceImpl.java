package org.jit.sose.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jit.sose.entity.GraduationRequireIndicator;
import org.jit.sose.entity.IndicatorFirst;
import org.jit.sose.entity.IndicatorRelat;
import org.jit.sose.entity.IndicatorSec;
import org.jit.sose.entity.query.IndicatorRelatQuery;
import org.jit.sose.entity.vo.RelatTreeVo;
import org.jit.sose.mapper.IndicatorRelatMapper;
import org.jit.sose.service.IndicatorRelatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class IndicatorRelatServiceImpl implements IndicatorRelatService {

	@Autowired
	private IndicatorRelatMapper indicatorRelatMapper;

	@Override
	public void insert(IndicatorRelat indicatorRelat) {
		indicatorRelatMapper.insert(indicatorRelat);
	}

	@Override
	public void update(IndicatorRelat indicatorRelat) {
		indicatorRelatMapper.update(indicatorRelat);
	}

	@Override
	public Integer delete(IndicatorRelat indicatorRelat) {
		return indicatorRelatMapper.delete(indicatorRelat);
	}

	@Override
	public PageInfo<IndicatorRelat> selectPageInfo(IndicatorRelat indicatorRelat, Integer pageNum, Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<IndicatorRelat> indicatorRelatList = indicatorRelatMapper.listByIndicatorRelat(indicatorRelat);
		PageInfo<IndicatorRelat> pageInfo = new PageInfo<IndicatorRelat>(indicatorRelatList);
		return pageInfo;
	}

	@Override
	public IndicatorRelat selectById(Integer id) {
		return indicatorRelatMapper.selectById(id);
	}

	@Override
	public List<RelatTreeVo> selectIndicatorListTree(IndicatorRelatQuery query) {
		// 循环查出的指标点list，转成ElementUi的结构
		List<IndicatorRelat> list = indicatorRelatMapper.selectIndicatorList(query);
		// 创建指标点集合
		List<RelatTreeVo> indicatorList = new ArrayList<RelatTreeVo>();
		// 第一层，指标点
		for (IndicatorRelat relat : list) {
			RelatTreeVo tree = new RelatTreeVo();
			// tree的指标点id:数据库id+指标点id
			tree.setId(relat.getId() + "-" + relat.getIndicatorId());
			tree.setIndicatorId(relat.getIndicatorId());
			tree.setLabel(relat.getIndicatorTitle());
			tree.setGraduationState(relat.getGraduationState());
			tree.setValue(relat.getId());// 给级联选择使用的格式，存放指标关联id
			tree.setTreeLevel(1);

			// 第二层，一级指标
			// 创建一级指标集合
			List<RelatTreeVo> indicatorFirstList = new ArrayList<RelatTreeVo>();
			for (IndicatorRelat firstRelat : relat.getIndicatorFirstList()) {
				RelatTreeVo firstTree = new RelatTreeVo();
				// tree的一级指标id：tree的指标点id+一级指标id
				firstTree.setId(
						firstRelat.getId() + "-" + relat.getIndicatorId() + "-" + firstRelat.getIndicatorFirstId());
				firstTree.setLabel("毕业要求:"+firstRelat.getFirstSeq()+"   "+firstRelat.getFirstTitle());
				firstTree.setContent(firstRelat.getFirstContent());
				firstTree.setGraduationState(firstRelat.getGraduationState());
				firstTree.setValue(firstRelat.getId());// 给级联选择使用的格式，存放一级指标id
				firstTree.setTreeLevel(2);
				
				
				

				// 第三层，二级指标
				// 创建二级指标集合
				List<RelatTreeVo> indicatorSecList = new ArrayList<RelatTreeVo>();
				for (IndicatorRelat secRelat : firstRelat.getIndicatorSecList()) {
					RelatTreeVo secTree = new RelatTreeVo();
					// tree的二级指标id:tree的一级指标id+二级指标id
					secTree.setId(secRelat.getId() + "-" + relat.getIndicatorId() + "-"
							+ firstRelat.getIndicatorFirstId() + "-" + secRelat.getIndicatorSecId());
//					secTree.setLabel(secRelat.getSecContent().subSequence(0, 10) + "...");
					secTree.setLabel(firstRelat.getFirstSeq()+"-"+secRelat.getSecSeq()+"   "+secRelat.getSecContent());
					secTree.setContent(secRelat.getSecContent());
					secTree.setTreeLevel(3);
					secTree.setGraduationState(secRelat.getGraduationState());
					secTree.setSupportCoefficientId(secRelat.getSupportCoefficientId()); // 支撑系数id
					secTree.setSupportCoefficientValue(secRelat.getValue());// 支撑系数
					secTree.setValue(secRelat.getId());// 给级联选择使用的格式，存放二级指标id
					indicatorSecList.add(secTree);
					
					
					
				}
				// 将二级指标的集合设置为一级指标的子列表
				firstTree.setChildren(indicatorSecList);
				// 将一级指标添加到一级指标树中
				indicatorFirstList.add(firstTree);
			}
			// 将一级指标的集合设置标题树子列表
			tree.setChildren(indicatorFirstList);
			// 添加到标题树集合中
			indicatorList.add(tree);
		}
		return indicatorList;
	}

	@Override
	public List<GraduationRequireIndicator> selectNotUseIndicatorList() {
		return indicatorRelatMapper.selectNotUseIndicatorList();
	}

	@Override
	public List<IndicatorFirst> selectNotUseIndicatorFirstList(Integer indicatorId) {
		return indicatorRelatMapper.selectNotUseIndicatorFirstList(indicatorId);
	}

	@Override
	public List<IndicatorSec> selectNotUseIndicatorSecList(Integer indicatorId, Integer indicatorFirstId) {
		return indicatorRelatMapper.selectNotUseIndicatorSecList(indicatorId, indicatorFirstId);
	}

}
