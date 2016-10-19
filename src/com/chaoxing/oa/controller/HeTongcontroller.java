package com.chaoxing.oa.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaoxing.oa.entity.page.Json;
import com.chaoxing.oa.entity.page.hetong.PFahuo;
import com.chaoxing.oa.entity.page.hetong.PFapiao;
import com.chaoxing.oa.service.HetongService;

@Controller
@RequestMapping("/hetong")
public class HeTongcontroller {
	private HetongService hetongService;

	public HetongService getHetongService() {
		return hetongService;
	}

	@Autowired
	public void setHetongService(HetongService hetongService) {
		this.hetongService = hetongService;
	}
	
	@RequestMapping(value = "/queryHetong")
	@ResponseBody
	public Map<String, Object> getHetong(PFahuo pfahuo, HttpSession session){
		Map<String, Object> hetongInfos = hetongService.findHetong(pfahuo, 0);
		return hetongInfos;
	}
	
	@RequestMapping(value = "/queryFapiao")
	@ResponseBody
	public Map<String, Object> getFapiao(PFapiao pfapiao, HttpSession session){
		Map<String, Object> hetongInfos = hetongService.findFapiao(pfapiao, 0);
		return hetongInfos;
	}
	
	@RequestMapping(value = "/updateFapiao")
	@ResponseBody
	public Json updateFapiao(PFapiao pfapiao, HttpSession session){
		Json result = new Json();
//		SessionInfo seesesion = (SessionInfo) session.getAttribute(Resource);
		if(pfapiao.getId()!=0&&pfapiao.getQueryStatus()!=null){
			if(hetongService.updateFapiao(pfapiao)>0){
				result.setSuccess(true);
				result.setMsg("更新成功!~");
			}else{
				result.setMsg("更新失败!~");
			}
		}else{
			result.setMsg("id为空或者发票数量为空");
		}
		return result;
	}
	
	@RequestMapping(value = "/sendKuaidi")
	@ResponseBody
	public Json sendKuaidi(PFahuo pfahuo, HttpSession session){
		Json result = new Json();
		System.out.println(pfahuo);
		if(pfahuo != null && pfahuo.getOrderid()!=0){
			if(pfahuo.getD_company()!=null&&pfahuo.getD_address()!=null&&pfahuo.getD_contact()!=null&&pfahuo.getD_contact()!=null){
				/**
				 * TODO
				 * 必要项目
				 * orderid ==> id
				 * d_company ==> jDepart 到件方公司名称
				 * d_contact ==> receiver 到件方联系人
				 * d_tel ==> dPhone 到件方联系电话
				 * d_address ==> jAddress 邮寄地址
				 * name ==> remark 货物名称，如果需要生成电子运单，则为必填(可选)
				 */
				 
				hetongService.sendKuaidi(pfahuo);
			}
		}else{
			result.setMsg("您输入的信息有误，请核对后重试！");
		}
//		Map<String, Object> hetongInfos = hetongService.findHetong(pfahuo, 0);
		return result;
	}
	
}
