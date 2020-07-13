package org.jit.sose.service.impl;

import org.jit.sose.entity.AssessItem;
import org.jit.sose.mapper.AssessItemMapper;
import org.jit.sose.service.AssessItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssessItemServiceImpl implements AssessItemService {

	@Autowired
	private AssessItemMapper assessItemMapper;

	@Override
	public void insert(AssessItem record) {
		assessItemMapper.insert(record);
	}

	@Override
	public void update(AssessItem record) {
		assessItemMapper.update(record);
	}

	@Override
	public void delete(Integer id) {
		assessItemMapper.delete(id);
	}
}
