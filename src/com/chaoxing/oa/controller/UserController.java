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
import com.chaoxing.oa.entity.page.PUser;
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
	public ModelAndView login(PUser userInfo, HttpServletRequest request, HttpSession session, Model model ){
		ModelAndView modelView = new ModelAndView("redirect:/index.jsp");
		String password = userInfo.getPassword();
		userInfo = userService.findUser(userInfo);
		if(null != userInfo){
			if(password == userInfo.getPassword()){
				userInfo.setIp(IpUtil.getIpAddr(request));
				session.setAttribute(ResourceUtil.getSessionInfoName(), userInfo);
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
	public Json getUserName(int id, HttpSession session){
		Json result = new Json();
		PUser userinfo = null;
		if(session!=null){
			userinfo = (PUser) session.getAttribute(ResourceUtil.getSessionInfoName());
		}else{
			result.setErrorCode(SysConfig.SESSION_INVALIAD);
			result.setMsg("您的登陆已失效！");
			return result;
		}
		if(id == 0){
			result.setErrorCode(SysConfig.REQUEST_ERROR);
			result.setMsg("您要查询的职员信息不存在！");
			return result;
		}
		if(userinfo != null){
			if(0 == userinfo.getRights()|| 1 == userinfo.getRights()){
				PUserName pusername = userService.getUserName(id);
				if(pusername!=null){
					result.setSuccess(true);
					result.setMsg("获取员工信息成功");
					result.setObj(pusername);
				}else{
					result.setErrorCode(SysConfig.REQUEST_ERROR);
					result.setMsg("获取员工编号：" + id + " 职员信息失败！");
				}
				return result;
			}else{
				result.setErrorCode(SysConfig.NO_RIGHTS);
				result.setMsg("您还没有权限！");
			}
		}else{
			result.setErrorCode(SysConfig.SESSION_INVALIAD);
			result.setMsg("您未登陆或者登陆失效！");
		}
		return result;
	}
	
	@RequestMapping(value = "/addUserName")
	@ResponseBody
	public Json addUserName(PUserName username, HttpSession session){
		Json result = new Json();
		PUser userinfo = null;
		
		if(session!=null){
			userinfo = (PUser) session.getAttribute(ResourceUtil.getSessionInfoName());
		}else{
			result.setErrorCode(SysConfig.SESSION_INVALIAD);
			result.setMsg("您的登陆已失效！");
			return result;
		}
//		userinfo = null;
		if(userinfo != null){
			if(0 == userinfo.getRights()|| 1 == userinfo.getRights()){
				long r = 2;
//				long r = userService.addUserName(username);
				if(r != -1){
					result.setSuccess(true);
					result.setMsg("添加成功！");
				}else{
					result.setErrorCode(SysConfig.REQUEST_ERROR);
					result.setMsg("插入失败！");
				}
				return result;
			}else{
				result.setErrorCode(SysConfig.NO_RIGHTS);
				result.setMsg("您还没有权限！");
			}
		}
		return result;
	}
	@RequestMapping(value = "deleteUserName")
	@ResponseBody
	public Json deleteUserName(int id, HttpSession session){
		Json result = new Json();
		PUser userinfo = null;
		if(session!=null){
			userinfo = (PUser) session.getAttribute(ResourceUtil.getSessionInfoName());
		}else{
			result.setErrorCode(SysConfig.SESSION_INVALIAD);
			result.setMsg("您的登陆已失效！");
			return result;
		}
		if(id == 0){
			result.setErrorCode(SysConfig.REQUEST_ERROR);
			result.setMsg("您要查询的职员信息不存在！");
			return result;
		}
		if(userinfo != null){
			if(0 == userinfo.getRights()|| 1 == userinfo.getRights()){
				int r = 1;
//				int r = userService.deleteUserName(id);
				if(r != 0){
					result.setSuccess(true);
					result.setMsg("删除成功！");
				}else{
					result.setSuccess(false);
					result.setErrorCode(SysConfig.REQUEST_ERROR);
					result.setMsg("删除失败");
				}
				return result;
			}else{
				result.setMsg("您还没有权限！");
			}
		}else{
			result.setMsg("您未登陆或者登陆失效！");
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
