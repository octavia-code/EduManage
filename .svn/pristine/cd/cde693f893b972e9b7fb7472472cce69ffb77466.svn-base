package org.jit.sose.controller.outline;

import org.jit.sose.entity.AssessItem;
import org.jit.sose.service.AssessItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课程考核项
 * 
 * @author: 王越
 * @date: 2019-07-30 17:53:03
 */
@RestController
@RequestMapping("/outline/assessItem")
public class AssessItemController {

	@Autowired
	private AssessItemService assessItemService;

	/**
	 * 添加考核项
	 * 
	 * @param record
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insert(@RequestBody AssessItem record) {
		assessItemService.insert(record);
	}

	/**
	 * 更新考核项
	 * 
	 * @param record
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody AssessItem record) {
		assessItemService.update(record);
	}

	/**
	 * 根据id逻辑删除课程考核项
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		assessItemService.delete(id);
	}
}
