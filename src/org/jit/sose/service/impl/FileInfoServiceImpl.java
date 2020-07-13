package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.FileInfo;
import org.jit.sose.mapper.FileInfoMapper;
import org.jit.sose.service.FileInfoSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class FileInfoServiceImpl implements FileInfoSerivce {

	@Autowired
	private FileInfoMapper fileInfoMapper;

	@Override
	public void insert(FileInfo record) {
		fileInfoMapper.insert(record);
	}

	@Override
	public Integer insertFileInfoList(List<FileInfo> list) {
		return fileInfoMapper.insertFileInfoList(list);
	}

	@Override
	public PageInfo<FileInfo> selectVideoInfoList(FileInfo fileInfo,Integer pageNum, Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<FileInfo> fileInfoList = fileInfoMapper.selectVideoInfoList(fileInfo);
		PageInfo<FileInfo> pageInfo = new PageInfo<FileInfo>(fileInfoList);
		return pageInfo;
	}

}
