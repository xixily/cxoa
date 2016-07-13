package com.chaoxing.oa.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chaoxing.oa.entity.page.QueryForm;
import com.chaoxing.oa.entity.page.SessionInfo;
import com.chaoxing.oa.service.UserServiceI;
import com.chaoxing.oa.util.IpUtil;
import com.chaoxing.oa.util.ResourceUtil;
public class UserController {
	private UserServiceI userService;
	
	
	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}


	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(QueryForm queryForm, Model model, HttpServletRequest request, HttpSession session){
		String password = queryForm.getPassword();
		SessionInfo sessionInfo = userService.findUser(queryForm);
//		queryForm = userService.findUser(queryForm);
		if(null != sessionInfo){
			if(sessionInfo.getPassword().equals(password)){
				sessionInfo.setIp(IpUtil.getIpAddr(request));
				session.setAttribute(ResourceUtil.getSessionInfoName(), sessionInfo);
				return "redirect:/index.jsp";
			}else{
				model.addAttribute("password_login_error", "您输入的密码不正确！");
			}
		}else{
			model.addAttribute("user_login_error", "email不存在!");
		}
		return "redirect:/login.jsp";
	}
	@RequestMapping("/userInfo")
	public String userInfo() {
		return "user/userInfo";
	}
}
