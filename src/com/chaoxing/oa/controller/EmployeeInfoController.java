package com.chaoxing.oa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaoxing.oa.entity.page.Pager;
import com.chaoxing.oa.entity.page.RenshiEmployeeInfo;
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
	public Map<String, Object> getRenshiUserName(RenshiEmployeeInfo page, Model model){
		System.out.println(page);
		if(null!=page){
			System.out.println("========>page=" + page.getPage());
			System.out.println("========>rows=" + page.getRows());
		}else{
			System.out.println("失败！");
		}
		List<RenshiEmployeeInfo> renshiUserNames = employeeInfoService.getRenshiUserName(page);
		long total = employeeInfoService.getRenshiUserNameCount();
		Map<String, Object> userInfos = new HashMap<String, Object>();
		model.addAttribute("page", page);
		userInfos.put("total", total);
		userInfos.put("rows", renshiUserNames);
		return userInfos;
	}
}
