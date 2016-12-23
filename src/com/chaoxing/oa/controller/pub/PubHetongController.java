package com.chaoxing.oa.controller.pub;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaoxing.oa.entity.page.common.Json;
import com.chaoxing.oa.entity.page.employee.PRenshiEmployee;
import com.chaoxing.oa.entity.page.system.SessionInfo;
import com.chaoxing.oa.service.PubHetongService;
import com.chaoxing.oa.util.ResourceUtil;

@Controller
@RequestMapping("/public/hetong")
public class PubHetongController {
	@Autowired
	private PubHetongService pubHetongService;
	
	@RequestMapping(value = "/helloPublic")
	@ResponseBody
	public Json helloc(HttpSession session){
		Json result = new Json();
		result.setSuccess(true);
		result.setMsg("hello public ~!");
		return result;
	}
	
	@RequestMapping(value = "/getAllCells")
	@ResponseBody
	public Json getAllCells(HttpSession session){
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		Json result = new Json();
//		String level = sessionInfo.getLevel();
		String level = "指导";
//		String email = sessionInfo.getEmail();
		String email = "chuanming@chaoxing.com";
		if(null!=level && "指导".equals(level) && null!= email){
			result.setObj(pubHetongService.findCellCores(email));
			result.setSuccess(true);
		}else if(null!=level && "细胞核".equals(level)){
			result.setObj(null);
//			pubHetongService.findCells();
		}
		else{
			result.setMsg("只有指导可以查看他下面的细胞核情况~。");
		}
		return result;
	} 
	
	@RequestMapping(value = "/getSalerUsers")
	@ResponseBody
	public Json getSalerUsers(String semail, HttpSession session){
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		Json result = new Json();
		String email = sessionInfo.getEmail();
		if(null!=semail && !"".equals(semail)){
			int leader = ifCell(email,semail);
			if(leader > -1){
				result.setObj(pubHetongService.findUserList(semail, null));
				result.setSuccess(true);
			}
		}
		return result;
	}
	
	private int ifCell(String email, String semail){
		PRenshiEmployee puser = pubHetongService.getUserByEmai(semail);
		if(null != puser){
			if(email.equals(puser.getGuidanceEmail())){
				return 2;
			}else if(email.equals(puser.getCellCoreEmail())){
				return 1;
			}else if(email.equals(puser.getEmail())){
				return 0;
			}
		}
		return -1;
	}
}
