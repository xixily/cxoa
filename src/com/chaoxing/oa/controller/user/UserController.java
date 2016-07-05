package com.chaoxing.oa.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chaoxing.oa.entity.page.UserInfo;
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
	public String login(UserInfo userPageInfo, Model model, HttpServletRequest request, HttpSession session){
		String password = userPageInfo.getPassword();
		userPageInfo = userService.findUser(userPageInfo);
		if(null != userPageInfo){
			if(userPageInfo.getPassword().equals(password)){
				userPageInfo.setIp(IpUtil.getIpAddr(request));
				session.setAttribute(ResourceUtil.getSessionInfoName(), userPageInfo);
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
