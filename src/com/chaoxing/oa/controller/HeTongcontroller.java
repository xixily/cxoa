package com.chaoxing.oa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaoxing.oa.entity.page.Json;
import com.chaoxing.oa.entity.page.SF.Cargo;
import com.chaoxing.oa.entity.page.SF.Order;
import com.chaoxing.oa.entity.page.SF.OrderResponse;
import com.chaoxing.oa.entity.page.hetong.PFahuo;
import com.chaoxing.oa.entity.page.hetong.PFapiao;
import com.chaoxing.oa.service.HetongService;
import com.chaoxing.oa.util.BarCode128C;
import com.chaoxing.oa.util.ResourceUtil;
import com.chaoxing.oa.util.SFUtil;

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
			pfahuo = hetongService.getFahuo(pfahuo.getOrderid());
			if(pfahuo != null){
				if(pfahuo.getMailno()!=null){
					result.setMsg("请求失败，该条数据已经有订单号了！~");
					return result;
				}
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
				if(pfahuo.getD_company()!=null&&pfahuo.getD_address()!=null&&pfahuo.getD_contact()!=null&&pfahuo.getD_tel()!=null){
					if(true){//TODO 判断是否有 寄方 地址、联系人、公司、联系电话
						Order order = new Order();
						BeanUtils.copyProperties(pfahuo, order);
						order.setJ_tel("17744543034");
						order.setJ_company("世纪超星公司");
						order.setJ_contact("小邓");
						order.setJ_address("北京市海淀区 上地东里3区4号楼 601");
						Cargo cargo = new Cargo();
						cargo.setName("发票");
						List<Cargo> cargos = new ArrayList<Cargo>();
						cargos.add(cargo);
						Json re = SFUtil.sendOrder(order, cargos, null);
						if(re.isSuccess()){
//							if(re.getObj() instanceof OrderResponse)
						OrderResponse or = (OrderResponse) re.getObj();
						String mailno = or.getMailno();
						System.out.println("顺丰请求结果>>>>>>>>：");
						System.out.println(or);
						String web_url = session.getServletContext().getRealPath("/") + "template/sfTemplate/img/" + "code_" + pfahuo.getOrderid() + ".jpg";;
//						String web_url = this.getClass().getResource("/").getFile().toString().split("WEB-INF/")[0] + "template/sfTemplate/img/";
						BarCode128C.getCode128CPicture(mailno, 22, web_url );
						result.setSuccess(true);
						result.setMsg(re.getMsg());
					    }else{
					    	result.setMsg(re.getMsg());
					    }
//						hetongService.sendKuaidi(pfahuo);
					}
				}else{
					result.setMsg("数据库里面的寄件信息缺少，请核实。");
				}
			}else{
				result.setMsg("数据库里不存在该条记录，请刷新您的浏览器再试！~");
			}
		}else{
			result.setMsg("您输入的信息有误，请核对后重试！");
		}
//		Map<String, Object> hetongInfos = hetongService.findHetong(pfahuo, 0);
		return result;
	}
	@RequestMapping(value = "/createCode128C")
	@ResponseBody
	public Json createCode128c(PFahuo pfahuo, HttpSession session){
		Json result = new Json();
		if(null != pfahuo.getMailno()&&""!=pfahuo.getMailno()&&!"".equals(pfahuo.getOrderid())){
			String web_url = session.getServletContext().getRealPath("/") + "template/sfTemplate/img/" + "code_" + pfahuo.getOrderid() + ".jpg";;
			BarCode128C.getCode128CPicture(pfahuo.getMailno(), 22, web_url );
			result.setSuccess(true);
			result.setMsg("条形码已重新生成！~");
		}else{
			result.setMsg("邮寄凭证号为空！~");
		}
		return result;
		
	}
}
