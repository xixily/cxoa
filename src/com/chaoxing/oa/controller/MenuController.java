package com.chaoxing.oa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chaoxing.oa.entity.page.MenuInfo;
import com.chaoxing.oa.entity.page.SessionInfo;
import com.chaoxing.oa.entity.page.UserInfo;
import com.chaoxing.oa.service.RoleMenuServiceI;
import com.chaoxing.oa.util.ResourceUtil;

@Controller
@RequestMapping("/menu")
public class MenuController {
	private RoleMenuServiceI menuService;
	
	public RoleMenuServiceI getMenuService() {
		return menuService;
	}
	@Autowired
	public void setMenuService(RoleMenuServiceI menuService) {
		this.menuService = menuService;
	}

	@RequestMapping(value = "allMenu")
	@ResponseBody
	public List<MenuInfo> getRleMenu(HttpSession session, Model model){
		UserInfo sessionInfo = (UserInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		if(sessionInfo == null){
			model.addAttribute("msg", "您未登陆，或登陆失效，请您先登陆!");
			return null;
		}
		return menuService.findMenu(sessionInfo.getRights());
	}
}
