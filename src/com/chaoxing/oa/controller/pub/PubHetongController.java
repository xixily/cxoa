package com.chaoxing.oa.controller.pub;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaoxing.oa.entity.page.common.Json;
import com.chaoxing.oa.entity.page.employee.PRenshiEmployee;
import com.chaoxing.oa.entity.page.hetong.PCustomerDepart;
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
	
	@RequestMapping(value="/getAllSubordinate")
	@ResponseBody
	public Json getAllSubordinate(String email, Integer id, HttpSession session){
		SessionInfo sessionInfo = getSessionInfo(session);
//		String semail = sessionInfo.getEmail();
		String level = sessionInfo.getLevel();
		level = "指导";
		if(null!=level && "指导".equals(level)){
			if(null==email || "".equals(email)){
				return getAllCells(session);
			}else{
				return getCoreCells(email, session);
			}
		}else if(null!=level && "细胞核".equals(level)){
			return getCoreCells(email, session);
		}
		return null;
	}
	
	@RequestMapping(value = "/getAllCellCores")
	@ResponseBody
	public Json getAllCells(HttpSession session){
		SessionInfo sessionInfo = getSessionInfo(session);
		Json result = new Json();
		String level = sessionInfo.getLevel();
		level = "指导";
//		String email = sessionInfo.getEmail();
		String email = "chuanming@chaoxing.com";
		if(null!=level && "指导".equals(level)){
			result.setObj(pubHetongService.findCellCoresCount(email));
			result.setSuccess(true);
		}else{
			result.setMsg("只有指导可以查看他下面的细胞核情况~。");
		}
		return result;
	} 
	
	@RequestMapping(value="getCoreCells")
	@ResponseBody
	public Json getCoreCells(String email, HttpSession session){
		Json result = new Json();
		SessionInfo sessionInfo = getSessionInfo(session);
		String level = sessionInfo.getLevel();
		level = "指导";
		String semail = sessionInfo.getEmail();
		semail = "chuanming@chaoxing.com";
		
		if(null!=level && "细胞核".equals(level)){
			result.setObj(pubHetongService.findCoreCellsCount(semail));
		}else if(null!=level && "指导".equals(level)){
			if(null!=email && "".equals(email)){
				result.setObj(pubHetongService.findCellCoresCount(email));
				result.setSuccess(true);
			}else{
				result.setMsg("缺少email！~");
			}
		}else{
			result.setMsg("只有指导可以查看他下面的细胞核情况~。");
		}
		return result;
	}
	
	@RequestMapping(value="getUserList")
	@ResponseBody
	public Json getUserList(String email, HttpSession session){
		Json result = new Json();
		if(null!=email && !"".equals(email)){
			SessionInfo sessionInfo = getSessionInfo(session);
			String semail = sessionInfo.getEmail();
			semail = "chuanming@chaoxing.com";
			PRenshiEmployee pemployee = pubHetongService.getUserByEmai(email);
			if(semail.equals(pemployee.getEmail()) || semail.equals(pemployee.getCellCoreEmail()) || semail.equals(pemployee.getGuidanceEmail())){
				pubHetongService.findUserListCount(email, pemployee.getUsername());
			}
		}else{
			result.setMsg("email信息为空，无法查询！~");
		}
		return result;
	}
	
	@RequestMapping(value="getContracts")
	@ResponseBody
	public Json getContracts(Integer id, HttpSession session){
		Json result = new Json();
		if(null!=id && id!=0){
			SessionInfo sessionInfo = getSessionInfo(session);
			String level = sessionInfo.getLevel();
			level = "指导";
			String email = sessionInfo.getEmail();
			email = "chuanming@chaoxing.com";
			PRenshiEmployee pemployee = pubHetongService.getUserByEmai(email);
			pubHetongService.findUserList();
			
			PCustomerDepart pcd = pubHetongService.getUserList(id);
			if(email.equals(pcd.getEmail()) || email.equals(pcd.getCellCoreEmail()) || email.equals(pcd.getGemail())){
				pubHetongService.findUserContractsCount(id);
			}
			pubHetongService.findUserContractsCount(id);
			if(null!=level && "指导".equals(level)){
				result.setObj(pubHetongService.findCellCoresCount(email));
				result.setSuccess(true);
			}else if(null!=level && "细胞核".equals(level)){
				result.setObj(pubHetongService.findCoreCellsCount(email));
				result.setSuccess(true);
			}
			else{
				result.setMsg("只有指导可以查看他下面的细胞核情况~。");
			}
		}else{
			result.setMsg("Id 不存在。");
		}
		return result;
	}
	
	@RequestMapping(value="getContractDetails")
	@ResponseBody
	public Json getContractDetails(Integer did, HttpSession session){
		Json result = new Json();
		SessionInfo sessionInfo = getSessionInfo(session);
		String level = sessionInfo.getLevel();
		level = "指导";
		String email = sessionInfo.getEmail();
		email = "chuanming@chaoxing.com";
		if(null!=level && "指导".equals(level)){
			result.setObj(pubHetongService.findCellCoresCount(email));
			result.setSuccess(true);
		}else if(null!=level && "细胞核".equals(level)){
			result.setObj(pubHetongService.findCoreCellsCount(email));
			result.setSuccess(true);
		}
		else{
			result.setMsg("只有指导可以查看他下面的细胞核情况~。");
		}
		return result;
	}
	
	@RequestMapping(value = "/getUserlistCount")
	@ResponseBody
	public Json getUserlistCount(String semail, HttpSession session){
		SessionInfo sessionInfo = getSessionInfo(session);
		Json result = new Json();
		String email = sessionInfo.getEmail();
		if(null!=semail && !"".equals(semail)){
			int leader = ifCell(email,semail);
			if(leader > -1){
				result.setObj(pubHetongService.findUserListCount(semail, null));
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
	
	private SessionInfo getSessionInfo(HttpSession session){
		return (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
	}
}
