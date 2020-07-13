package org.jit.sose.test.mapper;

import java.util.ArrayList;
import java.util.List;

import org.jit.sose.entity.FileInfo;
import org.jit.sose.mapper.FileInfoMapper;
import org.jit.sose.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 文件mapper接口测试类
 * 
 * @author: dylan
 * @date: 2019-08-02 00:12:55
 */
public class FileInfoMapperTest extends BaseTest {

	@Autowired
	private FileInfoMapper fileInfoMapper;

	@Test
	public void insertFileInfoList() {
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		FileInfo f1 = new FileInfo("f1", "/test", "P");
		FileInfo f2 = new FileInfo("f2", "/test2", "P");
		fileInfoList.add(f1);
		fileInfoList.add(f2);
		Integer result = fileInfoMapper.insertFileInfoList(fileInfoList);
		System.out.println(result);
		for (FileInfo fileInfo : fileInfoList) {
			System.out.println(fileInfo);
		}
	}

	@Test
	public void updateStateById() {
		fileInfoMapper.updateStateById(75, "A");
	}

	@Test
	public void updateStateByIdList() {
		List<Integer> idList = new ArrayList<Integer>();
		idList.add(76);
		idList.add(77);
		fileInfoMapper.updateStateByIdList(idList, "A");
	}
	
	@Test
	public void selectPageInfo(){
		List<FileInfo> lidt = new ArrayList<FileInfo>();
		FileInfo info = new FileInfo();
		info.setFileName("video");
		lidt = fileInfoMapper.selectVideoInfoList(info);
		System.out.println("集合为-------"+lidt);
	}
}
