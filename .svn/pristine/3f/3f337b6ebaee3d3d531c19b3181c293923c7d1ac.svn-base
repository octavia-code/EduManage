package org.jit.sose.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jit.sose.entity.TableTitle;
import org.jit.sose.mapper.TableTitleMapper;
import org.jit.sose.service.TableTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class TableTitleServiceImpl implements TableTitleService {

	@Autowired
	private TableTitleMapper tableTitleMapper;

	@Override
	public Map<String, Object> listByTableName(String tableName) {
		List<Object> key = new ArrayList<>();
		List<Object> title = new ArrayList<>();
		// 获取
		List<TableTitle> tableTitleList = tableTitleMapper.listByTableName(tableName);
		for (TableTitle tableTitle : tableTitleList) {
			key.add(tableTitle.getTableKey());
			title.add(tableTitle.getTitle());
		}
		Map<String, Object> tableHeadMap = new HashMap<String, Object>();
		tableHeadMap.put("key", key);
		tableHeadMap.put("title", title);
		return tableHeadMap;
	}

	@Override
	public List<TableTitle> selectTableTitleList() {
		// TODO Auto-generated method stub
		return tableTitleMapper.selectTableTitleList();
	}

	@Override
	public PageInfo<TableTitle> selectById(TableTitle tableTitle, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		List<TableTitle> nowData = tableTitleMapper.selectById(tableTitle);
		for (int i = 0; i < nowData.size(); i++) {
			TableTitle tabletitle = nowData.get(i);
			nowData.set(i, tabletitle);
		}
		PageInfo<TableTitle> pageInfo = new PageInfo<>(nowData);
		return pageInfo;
	}

	@Override
	public void insert(TableTitle tableTitle) {
		// TODO Auto-generated method stub
		tableTitleMapper.insert(tableTitle);
	}

	@Override
	public void update(TableTitle tableTitle) {
		// TODO Auto-generated method stub
		tableTitleMapper.update(tableTitle);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		tableTitleMapper.delete(id);
	}

	@Override
	public void deleteSelection(List<String> idList) {
		// TODO Auto-generated method stub
		tableTitleMapper.deleteSelection(idList);
	}

}
