package com.chaoxing.oa.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.web.servlet.ModelAndView;

import com.chaoxing.oa.config.SysConfig;
import com.chaoxing.oa.entity.page.Json;
import com.chaoxing.oa.entity.page.QueryForm;
import com.chaoxing.oa.entity.page.SessionInfo;
import com.chaoxing.oa.entity.page.PUserName;
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
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(QueryForm queryForm, HttpServletRequest request, HttpSession session, Model model ){
		ModelAndView modelView = new ModelAndView("login.jsp");
		if(queryForm.getEmail() == null){
//			if(queryForm.getUsername() == null || queryForm.getPassword() == null){
			modelView.setViewName("login");
			modelView.addObject("user_login_error", "请您输入用户名或者密码");
			return modelView;
		}
		String password = queryForm.getPassword();
		SessionInfo sessionInfo = userService.findUser(queryForm);
		if(null != sessionInfo){
			System.out.println(sessionInfo.getPassword());
			if(password == sessionInfo.getPassword()){
				sessionInfo.setIp(IpUtil.getIpAddr(request));
				session.setAttribute(ResourceUtil.getSessionInfoName(), sessionInfo);
				modelView.setViewName("redirect:/index.jsp");
			}else{
				modelView.setViewName("login");
				modelView.addObject("password_login_error", "您输入的密码不正确！");
			}
		}else{
			modelView.setViewName("login");
			modelView.addObject("user_login_error", "email不存在！");
		}
		return modelView;
	}
	
	@RequestMapping(value = "/getUserName")
	@ResponseBody
	public Json getUserName(QueryForm queryForm, HttpSession session) {
		Json result = new Json();
		PUserName pusername = userService.getUserName(queryForm.getId());
		if (pusername != null) {
			result.setSuccess(true);
			result.setMsg("获取员工信息成功");
			result.setObj(pusername);
		} else {
			result.setErrorCode(SysConfig.REQUEST_ERROR);
			result.setMsg("获取员工编号：" + queryForm.getId() + " 职员信息失败！");
		}
		return result;
	}

	@RequestMapping(value = "/addUserName")
	@ResponseBody
	public Json addUserName(PUserName username, HttpSession session) {
		Json result = new Json();
		long r = userService.addUserName(username);
		if (r != -1) {
			result.setSuccess(true);
			result.setMsg("添加成功！");
		} else {
			result.setErrorCode(SysConfig.REQUEST_ERROR);
			result.setMsg("插入失败！");
		}
		return result;
	}
	
	@RequestMapping(value = "deleteUserName")
	@ResponseBody
	public Json deleteUserName(QueryForm pu, HttpSession session) {
		Json result = new Json();
		if (pu.getId() == 0) {
			result.setErrorCode(SysConfig.REQUEST_ERROR);
			result.setMsg("您要查询的职员信息不存在！");
			return result;
		}
		int r = 0;
		r = userService.deleteUserName(pu);
		if (r != 0) {
			result.setSuccess(true);
			result.setMsg("删除成功！");
		} else {
			result.setSuccess(false);
			result.setErrorCode(SysConfig.REQUEST_ERROR);
			result.setMsg("删除失败");
		}
		return result;
	}
	
	@RequestMapping(value = "/updateUserName")
	@ResponseBody
	public Json updateUserName(PUserName username, HttpSession session) {
		Json result = new Json();
//		long r = 2;
		long r = userService.updateUserName(username);
		if (r != -1) {
			result.setSuccess(true);
			result.setMsg("更新成功！");
		} else {
			result.setErrorCode(SysConfig.REQUEST_ERROR);
			result.setMsg("更新失败！");
		}
		return result;
	}
	
	@RequestMapping(value = "/updateUserRole")
	@ResponseBody
	public Json updateUserRole(PUserName username, HttpSession session) {
		Json result = new Json();
		long r = userService.updateUserRole(username);
		if (r != 0) {
			result.setSuccess(true);
			result.setMsg("更新成功！");
		} else {
			result.setErrorCode(SysConfig.REQUEST_ERROR);
			result.setMsg("更新失败！");
		}
		return result;
	}
	/*@RequestMapping(value = "/login",method = RequestMethod.GET)
	@ResponseBody
	public Json login(UserInfo userInfo, HttpServletRequest request, HttpSession session){
		String password = userInfo.getPassword();
		userInfo = userService.findUser(userInfo);
		Json data = new Json();
		if(null != userInfo){
			if(password == userInfo.getPassword()){
				userInfo.setIp(IpUtil.getIpAddr(request));
				session.setAttribute(ResourceUtil.getSessionInfoName(), userInfo);
				session.setAttribute("companys", userService.getCompany());
//				System.out.println("companys" + session.getAttribute("companys"));
//				System.out.println("sessionInfo" + session.getAttribute("sessionInfo"));
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
	}*/
}
