package org.jit.sose.test.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jit.sose.entity.ExcelMerge;
import org.jit.sose.test.BaseTest;
import org.jit.sose.util.CompareListUtil;
import org.jit.sose.util.DateFormatUtil;
import org.junit.Test;

public class DateFormatUtilTest extends BaseTest {

	@Test
	public void addMonths() {
		Date date = new Date();
		date = DateFormatUtil.addMonths(date, 6);
		System.out.println(DateFormatUtil.formatCode(date));
	}

	/**
	 * 将属性使用“-”连接
	 * 
	 * @param merge
	 * @return String
	 */
	public String transformString(ExcelMerge merge) {
		String string = merge.getRow() + "-" + merge.getCol() + "-" + merge.getRowspan() + "-" + merge.getColspan();
		return string;
	}

	/**
	 * 将使用“-”连接的String转为ExcelMerge对象
	 * 
	 * @return ExcelMerge
	 */
	public ExcelMerge transformExcelMerge(String string) {
		String[] list = string.split("-");
		ExcelMerge merge = new ExcelMerge(Integer.valueOf(list[0]), Integer.valueOf(list[1]), Integer.valueOf(list[2]),
				Integer.valueOf(list[3]));
		return merge;
	}

	@Test
	public void test() {
		// 原数据
		ExcelMerge oldMerge1 = new ExcelMerge(0, 0, 1, 2);
		ExcelMerge oldMerge2 = new ExcelMerge(0, 1, 1, 2);
		ExcelMerge oldMerge3 = new ExcelMerge(1, 2, 2, 1);
		List<ExcelMerge> oldMergeList = new ArrayList<ExcelMerge>();
		oldMergeList.add(oldMerge1);
		oldMergeList.add(oldMerge2);
		oldMergeList.add(oldMerge3);

		List<String> oldStringList = new ArrayList<String>();
		// 比较的原数据
		for (ExcelMerge excelMerge : oldMergeList) {
			oldStringList.add(this.transformString(excelMerge));
		}
		for (String string : oldStringList) {
			System.out.println(string);
		}
		System.out.println("=========");

		// 新数据
		ExcelMerge newMerge1 = new ExcelMerge(0, 1, 1, 2);
		ExcelMerge newMerge2 = new ExcelMerge(1, 2, 2, 2);
		ExcelMerge newMerge3 = new ExcelMerge(2, 2, 3, 4);
		List<ExcelMerge> newMergeList = new ArrayList<ExcelMerge>();
		newMergeList.add(newMerge1);
		newMergeList.add(newMerge2);
		newMergeList.add(newMerge3);

		//// -----------

		// 比较的新数据
		List<String> newStringList = new ArrayList<String>();
		for (ExcelMerge excelMerge : newMergeList) {
			newStringList.add(this.transformString(excelMerge));
		}
		for (String string : newStringList) {
			System.out.println(string);
		}

		// 比较获取需要添加的数据
		List<String> addMergeStringList = CompareListUtil.getAddaListThanbList(newStringList, oldStringList);
		List<ExcelMerge> addMergeList = new ArrayList<ExcelMerge>();
		System.out.println("需要添加的数据");
		for (String string : addMergeStringList) {
			System.out.println(string);
			addMergeList.add(this.transformExcelMerge(string));
		}
		for (ExcelMerge excelMerge : addMergeList) {
			System.out.println(excelMerge);
		}

		// 比较获取需要删除的数据
		List<String> removeMergeStringList = CompareListUtil.getReduceaListThanbList(newStringList, oldStringList);
		List<ExcelMerge> removeMergeList = new ArrayList<ExcelMerge>();
		System.out.println("需要删除的数据");
		for (String string : removeMergeStringList) {
			System.out.println(string);
			removeMergeList.add(this.transformExcelMerge(string));
		}
		for (ExcelMerge excelMerge : removeMergeList) {
			System.out.println(excelMerge);
		}

	}

}
