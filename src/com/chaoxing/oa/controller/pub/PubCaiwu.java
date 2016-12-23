package com.chaoxing.oa.controller.pub;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaoxing.oa.entity.page.caiwu.PBaoxiao;
import com.chaoxing.oa.entity.page.common.Page;
import com.chaoxing.oa.entity.page.system.SessionInfo;
import com.chaoxing.oa.service.PubCaiwuService;
import com.chaoxing.oa.util.ResourceUtil;

@Controller
@RequestMapping("/public/caiwu")
public class PubCaiwu {
	@Autowired
	private PubCaiwuService publicService;
	
	@RequestMapping(value="/owners/{ownerId}",method=RequestMethod.GET)
	public String getOwner(@PathVariable String ownerId, Model model){
		
		return null;
	}
	
	@RequestMapping(value="/queryBaoxiao")
	@ResponseBody
	public Map<String, Object> findBaoxiao(PBaoxiao pbaoxiao, Page page, HttpSession session){
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		Map<String, Object> results = publicService.findBaoxiao(pbaoxiao,page,sessionInfo.getId());
		Double lastyear = publicService.getLastYear(sessionInfo.getId());
		Double thisyear = publicService.getThisYear(session.getId());
		results.put("lastYearTotal", lastyear);
		results.put("thisYearTotal", thisyear);
		results.put("success", true);
		return results;
	}
	
}
