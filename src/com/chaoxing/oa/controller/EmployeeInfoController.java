package com.chaoxing.oa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaoxing.oa.entity.page.UserInfo;
import com.chaoxing.oa.service.EmployeeInfoServiceI;

@Controller
@RequestMapping("/employee")
public class EmployeeInfoController {
	private EmployeeInfoServiceI employeeInfoService;
	
	public EmployeeInfoServiceI getEmployeeInfoService() {
		return employeeInfoService;
	}
	@Autowired
	public void setEmployeeInfoServiceI(EmployeeInfoServiceI employeeInfoServiceI) {
		this.employeeInfoService = employeeInfoServiceI;
	}

	@RequestMapping(value = "/renshiUser")
	@ResponseBody
	public Map<String, Object> getRenshiUserName(UserInfo page){
		Map<String, Object> userInfos = employeeInfoService.getRenshiUserName(page);
		return userInfos;
	}
}
