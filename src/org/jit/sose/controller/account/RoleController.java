package org.jit.sose.controller.account;

import java.util.List;

import org.jit.sose.entity.Role;
import org.jit.sose.service.RoleService;
import org.jit.sose.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/account/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@RequestBody Role role) {
		String result = roleService.insert(role);
		return ResponseUtil.success(result);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody Role role) {
		roleService.update(role);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		 roleService.delete(id);
	}

	@RequestMapping(value = "/deleteSelection", method = RequestMethod.POST)
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return  roleService.deleteSelection(idList);
	}

	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public PageInfo<Role> filter(@RequestBody String str) {
		JSONObject strj = JSON.parseObject(str);
		Role role = new Role();
		// 过滤查询条件
		role.setMykey("".equals(strj.getString("mykey")) ? null : strj.getString("mykey"));
		role.setName("".equals(strj.getString("name")) ? null : strj.getString("name"));
		// 当前页索引
		Integer pageNum = strj.getIntValue("pageNum");
		// 当前页页面大小
		Integer pageSize =strj.getIntValue("pageSize");
		return roleService.filter(role, pageNum, pageSize);

	}
	

}
