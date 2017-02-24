package com.chaoxing.oa.controller.pub;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaoxing.oa.entity.page.caiwu.PBaoxiao;
import com.chaoxing.oa.entity.page.caiwu.PBaoxiaoStatus;
import com.chaoxing.oa.entity.page.common.Json;
import com.chaoxing.oa.entity.page.common.Page;
import com.chaoxing.oa.entity.page.employee.PRenshiEmployee;
import com.chaoxing.oa.entity.page.pub.caiwu.PSelfBaoxiao;
import com.chaoxing.oa.entity.page.system.SessionInfo;
import com.chaoxing.oa.service.PubCaiwuService;
import com.chaoxing.oa.system.SysConfig;
import com.chaoxing.oa.system.cache.CacheManager;
import com.chaoxing.oa.util.ResourceUtil;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

@Controller
@RequestMapping("/public/caiwu")
public class PubCaiwuController {
	@Autowired
	private PubCaiwuService publicCaiwuService;
	
	@RequestMapping(value="/owners/{ownerId}",method=RequestMethod.GET)
	public String getOwner(@PathVariable String ownerId, Model model){
		System.out.println("路径值参："+ownerId);
		return null;
	}
	
	/**
	 * 查询报销信息
	 * @param pbaoxiao
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/queryBaoxiao")
	@ResponseBody
	public Map<String, Object> findBaoxiao(PBaoxiao pbaoxiao, Page page, HttpSession session){
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		Map<String, Object> results = publicCaiwuService.findBaoxiao(pbaoxiao,page,sessionInfo.getId());
		Double lastyear = publicCaiwuService.getLastYear(sessionInfo.getId());
		Double thisyear = publicCaiwuService.getThisYear(session.getId());
		results.put("lastYearTotal", lastyear);
		results.put("thisYearTotal", thisyear);
		results.put("success", true);
		return results;
	}
	
	/**
	 * 添加报销申请
	 * @param pbaoxiao
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/addBaoxiao")
	@ResponseBody
	public Json addBaoxiao(PBaoxiao pbaoxiao, HttpSession session){
		Json result = new Json();
		SessionInfo sessinfo = getSessInfo(session);
		pbaoxiao.setUid(sessinfo.getId());
//		pbaoxiao.setUsername(sessinfo.getUsername());
//		pbaoxiao.setEmail(sessinfo.getEmail());
//		pbaoxiao.setCellCoreEmail(sessinfo.getCellCoreEmail());
		pbaoxiao.setStatus(SysConfig.CW_BX_BEGIN);
		Serializable sid = publicCaiwuService.addBaoxiao(pbaoxiao);
		if(null!=sid){
			result.setSuccess(true);
			result.setMsg("添加成功，批次号为：" + sid.toString());
		}else{
			result.setMsg("抱歉，添加失败。");
		}
		return result;
	}
	
	/**
	 * 删除报销申请单
	 * @param pbaoxiao
	 * @param session
	 * @return
	 */
	public Json removeBaoxiao(PBaoxiao pbaoxiao, HttpSession session){
		Json result = new Json();
		SessionInfo sessionInfo = getSessInfo(session);
		if(null != pbaoxiao.getId() && pbaoxiao.getId() != 0){
			if(publicCaiwuService.deleteBaoxiao(pbaoxiao.getId(),sessionInfo.getId())>0){
				result.setSuccess(true);
				result.setMsg("删除成功！");
			}else{
				result.setMsg("删除失败，请确认您删除的批次号是否存在或者是否是属于您的。");
			}
		}else{
			result.setMsg("删除失败，批次号不存在，请确认!");
		}
		return result;
	}
	
	/**
	 * 更新个人报销信息
	 * @param psbaoxiao
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/updateBaoxiao")
	@ResponseBody
	public Json updateBaoxiao(PSelfBaoxiao psbaoxiao, HttpSession session){
		Json result = new Json();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		if(null!=psbaoxiao.getId()){
			PBaoxiao pbaoxiao = publicCaiwuService.getBaoxiao(psbaoxiao.getId());
			if(pbaoxiao.getUid()==sessionInfo.getId()){
				BeanUtils.copyProperties(psbaoxiao, pbaoxiao);
				pbaoxiao.setStatus(SysConfig.CW_BX_BEGIN);
				if(publicCaiwuService.updateBaoxiao(pbaoxiao)>0){
					result.setSuccess(true);
					result.setMsg("更新成功。");
				}else{
					result.setMsg("更新失败。");
				}
			}else{
				result.setMsg("该批次号对应的信息与当前用户不匹配。");
			}
			
		}else{
			result.setMsg("没有批次号。");
		}
		return result;
	}
	
	/**
	 * 报销批准
	 * @param id
	 * @param aggree
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/approveBaoxiao")
	@ResponseBody
	public Json approveBaoxiao(Long id, String approRemark, boolean aggree, HttpSession session){
		Json result = new Json();
		if(null!=id && id!=0){
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
			String email = sessionInfo.getEmail();
			PBaoxiao pbaoxiaos = publicCaiwuService.getBaoxiao(id);
			pbaoxiaos.setApproRemark(approRemark);
//			Map<Integer, Integer> pxs = getBaoxiaoStatus();
			publicCaiwuService.getPreStep(SysConfig.CW_BX_APPROVE_AGREE);
			if(publicCaiwuService.getPreStep(SysConfig.CW_BX_APPROVE_AGREE) == pbaoxiaos.getStatus()){
				if(publicCaiwuService.updateApprove(pbaoxiaos,aggree,sessionInfo.getId(), sessionInfo.getEmail())>0){
					result.setSuccess(true);
					result.setMsg("批准成功");
				}else{
					result.setMsg("批准失败，可能原因是您不没有批准此条记录的权限，请您确认您是此条记录申请人组织架构里的细胞核或者是指导。");
				}
			}else{
				result.setMsg("请刷新后重试。");
			}
//			
//			if(null!=pxs.get(3) && pxs.get(3)==pbaoxiaos.getStatus()){
//				if(null == pbaoxiaos.getApproid() || pbaoxiaos.getStatus()==1){
//					PRenshiEmployee employee = publicCaiwuService.getEmployee(pbaoxiaos.getUid());
//					if(null!=employee && (email==employee.getCellCoreEmail() || email==employee.getGuidanceEmail())){
//						pbaoxiaos.setApproid(sessionInfo.getId());
//						pbaoxiaos.setApprover(sessionInfo.getUsername());
//						pbaoxiaos.setAproEmail(sessionInfo.getEmail());
//						publicCaiwuService.updateApprove(pbaoxiaos, aggree, sessionInfo.getId(), sessionInfo.getEmail());
//						if(publicCaiwuService.updateBaoxiao(pbaoxiaos)>0){
//							result.setSuccess(true);
//							result.setMsg("批准成功！");
//						}else{
//							result.setMsg("入库失败！");
//						}
//					}else{
//						result.setMsg("对不起，您没有批准该记录权限。");
//					}
//				}else{
//					result.setMsg("批次号为：["+pbaoxiaos.getId()+"]的申请单已被：[" + pbaoxiaos.getApprover() + "]批准。");
//				}
//			}else{
//				result.setMsg("改报销当前不是出于待批准状态。");
//			}
		}else{
			result.setMsg("报销信息批次号不存在。");
		}
		return result;
	}
	
	/**
	 * 接收报销邮寄单
	 * @param pbaoxiao
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/recivedBaoxiao")
	@ResponseBody
	public Json recivedBaoxiao(Integer id, Boolean agree, String spRemarks, HttpSession session){
		Json result = new Json();
		if(null!= id && id != 0 && null != agree){
			SessionInfo sessionInfo = getSessInfo(session);
			spRemarks = spRemarks != null ? spRemarks : "";
			if(publicCaiwuService.updateBaoxiaoReceive(id, agree, spRemarks, sessionInfo.getId()) > 0){
				result.setSuccess(true);
				result.setMsg("更新成功！");
			};
		}else{
			result.setMsg("批次号不存在");
		}
		return result;
	}
	
	/**
	 * 报销单审核
	 * @param pbaoxiao
	 * @param agree
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/checkBaoxiao")
	@ResponseBody
	public Json checkBaoxiao(PBaoxiao pbaoxiao, Boolean agree, Page page, HttpSession session){
		Json result = new Json();
		if(null!=pbaoxiao.getId() && pbaoxiao.getId()!=0){
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
			pbaoxiao.setCheckerId(sessionInfo.getId());
			if(null!=pbaoxiao.getId() && 0!= pbaoxiao.getId() && null!=agree){
				if(agree){
					pbaoxiao.setStatus(6);
				}else{
					pbaoxiao.setStatus(7);
				}
				publicCaiwuService.updateBaoxiaoCheck(pbaoxiao,agree);
			}
		}
		return result;
	}
	
	/**
	 * 报销单出票
	 * @param pbaoxiao
	 * @param agree
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/baoxiaoChupiao")
	@ResponseBody
	public Json baoxiaoChupiao(PBaoxiao pbaoxiao, Boolean agree, Page page, HttpSession session){
		Json result = new Json();
		if(null!=pbaoxiao.getId() && pbaoxiao.getId()!=0){
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
			pbaoxiao.setCheckerId(sessionInfo.getId());
			if(null!=pbaoxiao.getId() && 0!= pbaoxiao.getId() && null!=agree){
				if(agree){
					pbaoxiao.setStatus(6);
				}else{
					pbaoxiao.setStatus(7);
				}
				publicCaiwuService.updateBaoxiaoCheck(pbaoxiao,agree);
			}
		}
		return result;
	}
	
	/**
	 * 报销单汇款
	 * @param pbaoxiao
	 * @param agree
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/baoxiaoHuikuan")
	@ResponseBody
	public Json baoxiaoHuikuan(PBaoxiao pbaoxiao, Boolean agree, Page page, HttpSession session){
		Json result = new Json();
		if(null!=pbaoxiao.getId() && pbaoxiao.getId()!=0){
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
			pbaoxiao.setCheckerId(sessionInfo.getId());
			if(null!=pbaoxiao.getId() && 0!= pbaoxiao.getId() && null!=agree){
				if(agree){
					pbaoxiao.setStatus(6);
				}else{
					pbaoxiao.setStatus(7);
				}
				publicCaiwuService.updateBaoxiaoCheck(pbaoxiao,agree);
			}
		}
		return result;
	}
	
	@RequestMapping(value="/getAllCells")
	@ResponseBody
	public Json getAllCells(HttpSession session){
		Json result = new Json();
		SessionInfo sessinfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		String level = null != sessinfo.getLevel() ? sessinfo.getLevel() : "" ;
		if("细胞核".equals(level) || "预备细胞核".equals(level) || "指导".equals(level)){
			publicCaiwuService.findAllCells(sessinfo.getEmail());
		}
		return result;
	}
	
//	private Map<Integer, Integer> getBaoxiaoStatus(){
//		if(null == CacheManager.getInstance().get(SysConfig.CACHE_COMMON+SysConfig.COMMON_BAOXIAO_STATUS)){
//			List<PBaoxiaoStatus> pbxStatus = publicCaiwuService.findAllBaoxiaoStatus();
//			CacheManager.getInstance().put(SysConfig.CACHE_COMMON + SysConfig.COMMON_BAOXIAO_STATUS, pbxStatus);
//			Map<Integer, Integer> pbs = new HashMap<Integer, Integer>();
//			Iterator<PBaoxiaoStatus> it = pbxStatus.iterator();
//			while(it.hasNext()){
//				PBaoxiaoStatus pb = it.next();
//				pbs.put(pb.getStatus(), pb.getPrestatus());
//			}
//			return pbs;
//		}else{
//			return (Map<Integer, Integer>) CacheManager.getInstance().get(SysConfig.CACHE_COMMON + SysConfig.COMMON_BAOXIAO_STATUS);
//		}
//	}
	
	private SessionInfo getSessInfo(HttpSession session){
		return (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
	}
}
