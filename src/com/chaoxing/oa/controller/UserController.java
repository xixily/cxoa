package com.chaoxing.oa.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaoxing.oa.entity.page.Json;
import com.chaoxing.oa.entity.page.UserInfo;
import com.chaoxing.oa.service.UserServiceI;
import com.chaoxing.oa.util.IpUtil;
import com.chaoxing.oa.util.ResourceUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	private UserServiceI userService;

	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/login.jsp";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public Json logout(HttpSession session, Model model){
		if (session != null) {
			session.invalidate();
			System.out.println("*********session失效**********");
		}
		Json json = new Json();
		json.setMsg("登出成功！");
		json.setSuccess(true);
		return json;
	}
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(UserInfo userInfo, HttpServletRequest request, HttpSession session, Model model ){
		String password = userInfo.getPassword();
		userInfo = userService.findUser(userInfo);
		if(null != userInfo){
			if(password == userInfo.getPassword()){
				userInfo.setIp(IpUtil.getIpAddr(request));
				session.setAttribute(ResourceUtil.getSessionInfoName(), userInfo);
				return "redirect:/index.jsp";
			}else{
				Map<String, String> info = new HashMap<String, String>();
				info.put("msg", "您输入的密码不正确！");
				model.addAttribute("password_login_error", "您输入的密码不正确！");
			}
		}else{
			model.addAttribute("user_login_error", "email不存在!");
		}
		return "redirect:/login.jsp";
//		return "login.jsp";
	}
	
	@RequestMapping(value = "login",method = RequestMethod.POST)
	@ResponseBody
	public Json login(UserInfo userInfo, HttpServletRequest request, HttpSession session){
		String password = userInfo.getPassword();
		userInfo = userService.findUser(userInfo);
		Json data = new Json();
		if(null != userInfo){
			if(password == userInfo.getPassword()){
				userInfo.setIp(IpUtil.getIpAddr(request));
				session.setAttribute(ResourceUtil.getSessionInfoName(), userInfo);
				data.setSuccess(true);
				data.setMsg("登陆成功");
				data.setObj(userInfo);
			}else{
				data.setSuccess(false);
				data.setMsg("您输入的密码有误！");
			}
		}else{
			data.setSuccess(false);
			data.setMsg("您输入的账号不存在！");
		}
		return data;
	}
	
}
