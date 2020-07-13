package org.jit.sose.test.mapper;

import java.util.List;

import org.jit.sose.entity.GraduationRequireIndicator;
import org.jit.sose.entity.IndicatorFirst;
import org.jit.sose.entity.IndicatorRelat;
import org.jit.sose.entity.IndicatorSec;
import org.jit.sose.entity.query.IndicatorRelatQuery;
import org.jit.sose.mapper.IndicatorRelatMapper;
import org.jit.sose.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class IndicatorRelatMapperTest extends BaseTest {

	@Autowired
	IndicatorRelatMapper indicatorRelatMapper;

	// 查询指标点
	@Test
	public void selectIndicatorList() {
		IndicatorRelatQuery dao = new IndicatorRelatQuery();
		dao.setYearPlanId(17);
		dao.setCourseInfoId(1);
		List<IndicatorRelat> list = indicatorRelatMapper.selectIndicatorList(dao);
		for (IndicatorRelat indicatorRelat : list) {
			System.out.println("==========");
			System.out.println("标题:" + indicatorRelat);
			for (IndicatorRelat indicatorFirst : indicatorRelat.getIndicatorFirstList()) {
				System.out.println("	一级指标" + indicatorFirst);
				if (!indicatorFirst.getIndicatorSecList().isEmpty()) {
					for (IndicatorRelat indicatorSec : indicatorFirst.getIndicatorSecList()) {
						System.out.println("		二级指标" + indicatorSec);
					}
				}
			}
		}
	}

	// 查询一级指标
	@Test
	public void selectIndicatorFirstList() {
//		List<IndicatorRelat> list = indicatorRelatMapper.selectIndicatorFirstList(3);
//		for (IndicatorRelat indicatorFirst : list) {
//			System.out.println("	一级指标" + indicatorFirst);
//			if (!indicatorFirst.getIndicatorSecList().isEmpty()) {
//				for (IndicatorRelat indicatorSec : indicatorFirst.getIndicatorSecList()) {
//					System.out.println("		二级指标" + indicatorSec);
//				}
//			}
//		}
	}

	// 查询二级指标
	@Test
	public void selectIndicatorSecList() {
//		List<IndicatorRelat> list = indicatorRelatMapper.selectIndicatorSecList(3, 2, 2);
//		for (IndicatorRelat indicatorSec : list) {
//			System.out.println("		二级指标" + indicatorSec);
//		}
	}

	// 查询在指标关联表中未使用的指标点集合
	@Test
	public void selectNotUseIndicatorList() {
		List<GraduationRequireIndicator> list = indicatorRelatMapper.selectNotUseIndicatorList();
		for (GraduationRequireIndicator graduationRequireIndicator : list) {
			System.out.println(graduationRequireIndicator);
		}
	}

	// 查询在指标关联表中未使用的一级指标集合
	@Test
	public void selectNotUseIndicatorFirstList() {
		List<IndicatorFirst> list = indicatorRelatMapper.selectNotUseIndicatorFirstList(3);
		for (IndicatorFirst indicatorFirst : list) {
			System.out.println(indicatorFirst);
		}
	}

	// 查询在指标关联表中未使用的二级指标集合
	@Test
	public void selectNotUseIndicatorSecList() {
		List<IndicatorSec> list = indicatorRelatMapper.selectNotUseIndicatorSecList(3, 2);
		for (IndicatorSec indicatorSec : list) {
			System.err.println(indicatorSec);
		}
	}

}
