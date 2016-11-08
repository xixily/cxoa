package com.chaoxing.oa.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.chaoxing.oa.entity.page.common.POStructV;
import com.chaoxing.oa.entity.page.employee.PKaoQin;
import com.chaoxing.oa.entity.page.employee.PMonthWages;
import com.chaoxing.oa.entity.page.employee.PRenshiEmployee;
import com.chaoxing.oa.entity.page.employee.PSheBaoSummary;
import com.chaoxing.oa.entity.page.employee.PshebaoDetail;
import com.chaoxing.oa.service.ExportExcelService;
import com.chaoxing.oa.util.SXSSFWriter;

@Service("exportExcelService")
public class ExportExcelServiceImpl implements ExportExcelService {

	@Override
	public String getRenshiQueryExport(List<PRenshiEmployee> renshiEmployees) {
		SXSSFWriter sxffWriter = null;
		try {
			sxffWriter = new SXSSFWriter("employeeExcel");
			//文件绝对路径
			String filePath = SXSSFWriter.DEFAULT_FOLDER + sxffWriter.getFileName();
			sxffWriter.createNewSheet("查询结果excel表");
//			if(renshiEmployees==null || renshiEmployees.size()<=0){
//				return null;
//			}
			createHeader(sxffWriter);
			for (PRenshiEmployee pRenshiEmployee : renshiEmployees) {
				sxffWriter.createRow();
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getUsername());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getFirstLevel());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getSecondLevel());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getThirdLevel());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getFourthLevel());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getPosition());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getSex());
				//add
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getIdentityCard());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getBorthDay());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getNation());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getDegree());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getGraduatedSchool());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getMajor());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getHomeAddress());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getPhoneNumber());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getHomeNumber());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getHiredate());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getZhuanzhengTime());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getLeaveTime());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getEarlyEntryDate());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getHouseholdType());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getInsurance());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getInsuranceCompany());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getCompany());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getResume());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getPhoto());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getDegreeCertificate());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getIdentityCardCopy());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getFamilyRegister());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getLeavingCertificate());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getContract());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getManagementSystem());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getEntryForm());
				sxffWriter.createCell();
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getSignedTime());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getTerminationTime());
				sxffWriter.createCell();
				sxffWriter.createCell();
				sxffWriter.createCell();
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getHouseholdType());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getPostcode());
				
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getWorkPlace());
				//add
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getPastLeaveTime());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getCellCore());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getCellCoreEmail());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getGuidance());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getGuidanceEmail());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getSocialSecurityHospital());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getLevel());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getEmail());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getRuzhiReport());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getLizhiReport());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getZhuanzhengReport());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getBumentiaozhengReport());
				
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getMaritalStatus());
			}
			sxffWriter.flush();
			return filePath;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				sxffWriter.destroy();
			} catch (IOException e) {
				System.out.println("销毁失败！");
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	public String getKaoqinExportExcel(List<PKaoQin> pKaoqins) {
		SXSSFWriter sxffWriter = null;
		try {
			sxffWriter = new SXSSFWriter("kaoqinExcel");
			//文件绝对路径
			String filePath = SXSSFWriter.DEFAULT_FOLDER + sxffWriter.getFileName();
			sxffWriter.createNewSheet("查询结果excel表");
			createKaoqinHeader(sxffWriter);
			for (PKaoQin pKaoQin : pKaoqins) {
				sxffWriter.createRow();
				sxffWriter.createCell();
				sxffWriter.setStringData(pKaoQin.getUsername());
				sxffWriter.createCell();
				sxffWriter.setStringData(pKaoQin.getFirstLevel());
				sxffWriter.createCell();
				sxffWriter.setStringData(pKaoQin.getSecondLevel());
				sxffWriter.createCell();
				sxffWriter.setStringData(pKaoQin.getThirdLevel());
				sxffWriter.createCell();
				sxffWriter.setStringData(pKaoQin.getFourthLevel());
				sxffWriter.createCell();
				sxffWriter.setStringData(pKaoQin.getIdentityCard());
				sxffWriter.createCell();
				sxffWriter.setData(pKaoQin.getChuqinDay().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pKaoQin.getZhuanzhengChaeDay().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pKaoQin.getChidaoYingkouDay().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pKaoQin.getShiJiaHour().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pKaoQin.getBingJiaHour().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pKaoQin.getKuangGongHour().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pKaoQin.getHunJiaDay().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pKaoQin.getChanJiaDay().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pKaoQin.getSangJiaDay().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pKaoQin.getNianJiaDay().toString());
				sxffWriter.createCell();
				sxffWriter.setStringData(pKaoQin.getHiredate());
				sxffWriter.createCell();
				sxffWriter.setStringData(pKaoQin.getLeaveTime());
				sxffWriter.createCell();
				sxffWriter.setStringData(pKaoQin.getZhuanzhengTime());
				sxffWriter.createCell();
				sxffWriter.setStringData(pKaoQin.getRuzhiReport());
				sxffWriter.createCell();
				sxffWriter.setStringData(pKaoQin.getLizhiReport());
				sxffWriter.createCell();
				sxffWriter.setStringData(pKaoQin.getZhuanzhengReport());
				sxffWriter.createCell();
				sxffWriter.setStringData(pKaoQin.getKaoQinremarks());
			}
//			insertCell(PKaoQin.class,pKaoqins);
			sxffWriter.flush();
			return filePath;
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				sxffWriter.destroy();
			} catch (IOException e) {
				System.out.println("销毁失败！");
				e.printStackTrace();
			}
		}
		return null;
	}
	@Override
	public String getMonthWagesExcel(List<PMonthWages> pMonthWages) {
		SXSSFWriter sxffWriter = null;
		try {
			sxffWriter = new SXSSFWriter("kaoqinExcel");
			//文件绝对路径
			String filePath = SXSSFWriter.DEFAULT_FOLDER + sxffWriter.getFileName();
			sxffWriter.createNewSheet("查询结果excel表");
			createKaoqinHeader(sxffWriter, 1);
			BigDecimal tax = new BigDecimal(0);
			BigDecimal selfTax = new BigDecimal(0);
			BigDecimal bd = new BigDecimal(0);
			for (PMonthWages pMonthWage : pMonthWages) {
				sxffWriter.createRow();
				sxffWriter.createCell();
				if(pMonthWage.getUsername().contains("（重名）") || pMonthWage.getUsername().contains("(重名)")){
					sxffWriter.setData(pMonthWage.getUsername().replace("（重名）", ""));
				}else{
					sxffWriter.setData(pMonthWage.getUsername());
				}
				sxffWriter.createCell();
				sxffWriter.setData(pMonthWage.getFirstLevel());
				sxffWriter.createCell();
				sxffWriter.setData(pMonthWage.getSecondLevel());
				sxffWriter.createCell();
				sxffWriter.setData(pMonthWage.getThirdLevel());
				sxffWriter.createCell();
				sxffWriter.setData(pMonthWage.getFourthLevel());
				sxffWriter.createCell();
				sxffWriter.setData(pMonthWage.getCompany());
				sxffWriter.createCell();
				sxffWriter.setData(pMonthWage.getAccountBank());
				sxffWriter.createCell();
				sxffWriter.setData(pMonthWage.getAccount());
				sxffWriter.createCell();
				sxffWriter.setData(pMonthWage.getIdentityCard());
				sxffWriter.createCell();
				if(pMonthWage.getChanJiaDay()!=null){
					sxffWriter.setData(pMonthWage.getChuqinDay().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getZhuanzhengChaeDay()!=null){
					sxffWriter.setData(pMonthWage.getZhuanzhengChaeDay().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getSalary()!=null){
					sxffWriter.setData(pMonthWage.getSalary().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getSelfTax()!=null){
					selfTax = new BigDecimal(pMonthWage.getSelfTax().toString());
					tax = selfTax.setScale(2, BigDecimal.ROUND_HALF_UP);
					sxffWriter.setData(tax.toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getSubTotal()!=null){
					sxffWriter.setData(pMonthWage.getSubTotal().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getKaoqinTotal()!=null){
					sxffWriter.setData(pMonthWage.getKaoqinTotal().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getFakuan()!=null){
					sxffWriter.setData(pMonthWage.getFakuan().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getJiangjin()!=null){
					sxffWriter.setData(pMonthWage.getJiangjin().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getBufaSalary()!=null){
				sxffWriter.setData(pMonthWage.getBufaSalary().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getYingfaTotal()!=null){
					sxffWriter.setData(pMonthWage.getYingfaTotal().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getShifaTotal()!=null){
					bd = new BigDecimal(pMonthWage.getYingfaTotal().toString());
//					BigDecimal bdsub = new BigDecimal(tax.toString());
					sxffWriter.setData(bd.subtract(tax).toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getcTotal()!=null){
					sxffWriter.setData(pMonthWage.getcTotal().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getSecrecySubsidy()!=null){
					sxffWriter.setData(pMonthWage.getSecrecySubsidy().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getCommunicationSubsidy()!=null){
					sxffWriter.setData(pMonthWage.getCommunicationSubsidy().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getLunchSubsidy()!=null){
					sxffWriter.setData(pMonthWage.getLunchSubsidy().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getShiJiaHour()!=null){
					sxffWriter.setData(pMonthWage.getShiJiaHour().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getBingJiaHour()!=null){
					sxffWriter.setData(pMonthWage.getBingJiaHour().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getKuangGongHour()!=null){
					sxffWriter.setData(pMonthWage.getKuangGongHour().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getChidaoYingkouDay()!=null){
					sxffWriter.setData(pMonthWage.getChidaoYingkouDay().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getChanJiaDay()!=null){
					sxffWriter.setData(pMonthWage.getChanJiaDay().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getAnnualLleave()!=null){
					sxffWriter.setData(pMonthWage.getAnnualLleave().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getSickLleaveTotal()!=null){
					sxffWriter.setData(pMonthWage.getSickLleaveTotal().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getSubEndowmentIinsurance()!=null){
					sxffWriter.setData(pMonthWage.getSubEndowmentIinsurance().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getSubMedicare()!=null){
					sxffWriter.setData(pMonthWage.getSubMedicare().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getSubUnemployedInsurance()!=null){
					sxffWriter.setData(pMonthWage.getSubUnemployedInsurance().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getSubHouseIinsurance()!=null){
					sxffWriter.setData(pMonthWage.getSubHouseIinsurance().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getHiredate()!=null){
					sxffWriter.setData(pMonthWage.getHiredate().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getBasicWage()!=null){
					sxffWriter.setData(pMonthWage.getBasicWage().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getPostSalary()!=null){
					sxffWriter.setData(pMonthWage.getPostSalary().toString());
				}
				sxffWriter.createCell();
				if(pMonthWage.getPerformanceRelatedPay()!=null){
					sxffWriter.setData(pMonthWage.getPerformanceRelatedPay().toString());
				}
				sxffWriter.createCell();
				sxffWriter.setData(pMonthWage.getLeaveTime());
				sxffWriter.createCell();
				sxffWriter.setData(pMonthWage.getZhuanzhengTime());
				sxffWriter.createCell();
				sxffWriter.setData(pMonthWage.getRemarks());
				sxffWriter.createCell();
				sxffWriter.setData(pMonthWage.getKaoQinremarks());
				sxffWriter.createCell();
				if(pMonthWage.getLishiSalary()!=null){
					sxffWriter.setData(pMonthWage.getLishiSalary().toString());
				}
				sxffWriter.createCell();
				sxffWriter.setData(pMonthWage.getRuzhiReport());
				sxffWriter.createCell();
				sxffWriter.setData(pMonthWage.getLizhiReport());
				sxffWriter.createCell();
				sxffWriter.setData(pMonthWage.getZhuanzhengReport());
				
			}
//			insertCell(PKaoQin.class,pKaoqins);
			sxffWriter.flush();
			return filePath;
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				sxffWriter.destroy();
			} catch (IOException e) {
				System.out.println("销毁失败！");
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	public String getShebaoCompany(List<PshebaoDetail> pwages) {

		SXSSFWriter sxffWriter = null;
		try {
			sxffWriter = new SXSSFWriter("kaoqinExcel");
			//文件绝对路径
			String filePath = SXSSFWriter.DEFAULT_FOLDER + sxffWriter.getFileName();
			sxffWriter.createNewSheet("查询结果excel表");
			createShebaoHeader(sxffWriter);
			for (PshebaoDetail pwage : pwages) {
				sxffWriter.createRow();
				sxffWriter.createCell();
				sxffWriter.setData(pwage.getUsername());
				sxffWriter.createCell();
				sxffWriter.setData(pwage.getIdentityCard());
				sxffWriter.createCell();
				sxffWriter.setData(pwage.getRubaoTime());
				sxffWriter.createCell();
				sxffWriter.setData(pwage.getRadix().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pwage.getSubEndowmentIinsurance().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pwage.getSubMedicare().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pwage.getSubUnemployedInsurance().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pwage.getSubHouseIinsurance().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pwage.getcEndowmentIinsurance().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pwage.getcMedicare().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pwage.getcUnemployedInsurance().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pwage.getcHouseIinsurance().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pwage.getcInjuryInsurance().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pwage.getcBirthIinsurance().toString());
			}
//			insertCell(PKaoQin.class,pKaoqins);
			sxffWriter.flush();
			return filePath;
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				sxffWriter.destroy();
			} catch (IOException e) {
				System.out.println("销毁失败！");
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	public String getShebaoSummary(List<PSheBaoSummary> pShebaoSummarys) {
		SXSSFWriter sxffWriter = null;
		try {
			sxffWriter = new SXSSFWriter("kaoqinExcel");
			//文件绝对路径
			String filePath = SXSSFWriter.DEFAULT_FOLDER + sxffWriter.getFileName();
			sxffWriter.createNewSheet("查询结果excel表");
			createShebaoSummaryHeader(sxffWriter);
			for (PSheBaoSummary pSheBaoSummary : pShebaoSummarys) {
				sxffWriter.createRow();
				sxffWriter.createCell();
				sxffWriter.setData(pSheBaoSummary.getCompany());
				sxffWriter.createCell();
				sxffWriter.setNumbericData(new BigDecimal(pSheBaoSummary.getCount()));
				sxffWriter.createCell();
				sxffWriter.setData(pSheBaoSummary.getcEndowmentIinsurance().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pSheBaoSummary.getSubEndowmentIinsurance().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pSheBaoSummary.getcUnemployedInsurance().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pSheBaoSummary.getSubUnemployedInsurance().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pSheBaoSummary.getcInjuryInsurance().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pSheBaoSummary.getcBirthIinsurance().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pSheBaoSummary.getcMedicare().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pSheBaoSummary.getSubMedicare().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pSheBaoSummary.getcHouseIinsurance().toString());
				sxffWriter.createCell();
				sxffWriter.setData(pSheBaoSummary.getSubHouseIinsurance().toString());
			}
//			insertCell(PKaoQin.class,pKaoqins);
			sxffWriter.flush();
			return filePath;
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				sxffWriter.destroy();
			} catch (IOException e) {
				System.out.println("销毁失败！");
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	public String getPOStructExcel(List<POStructV> pos) {

		SXSSFWriter sxffWriter = null;
		try {
			sxffWriter = new SXSSFWriter("jiagouExcel");
			//文件绝对路径
			String filePath = SXSSFWriter.DEFAULT_FOLDER + sxffWriter.getFileName();
			sxffWriter.createNewSheet("查询结果excel表");
			createPOStruct(sxffWriter);
			Iterator<POStructV> it = pos.iterator();
			while(it.hasNext()){
				POStructV po = it.next();
				if(po.getLevel()!=4){
					continue;
				}
				sxffWriter.createRow();
				sxffWriter.createCell();
				sxffWriter.setData(String.valueOf(po.getId()));
				sxffWriter.createCell();
				sxffWriter.setData(po.getFirstLevel());
				sxffWriter.createCell();
				sxffWriter.setData(po.getSecondLevel());
				sxffWriter.createCell();
				sxffWriter.setData(po.getThirdLevel());
				sxffWriter.createCell();
				sxffWriter.setData(po.getFourthLevel());
				sxffWriter.createCell();
				sxffWriter.setData(po.getCellCore());
				sxffWriter.createCell();
				sxffWriter.setData(po.getCellCoreEmail());
				sxffWriter.createCell();
				sxffWriter.setData(po.getGuidance());
				sxffWriter.createCell();
				sxffWriter.setData(po.getGuidanceEmail());
				sxffWriter.createCell();
				sxffWriter.setData(po.getSortCode());
				sxffWriter.createCell();
				sxffWriter.setData(po.getTaxStructure());
			}
			sxffWriter.flush();
			return filePath;
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				sxffWriter.destroy();
			} catch (IOException e) {
				System.out.println("销毁失败！");
				e.printStackTrace();
			}
		}
		return null;
	}

	private void createPOStruct(SXSSFWriter sxffWriter) {
		sxffWriter.createRow();
		sxffWriter.createCell();
		sxffWriter.setStringData("id");
		sxffWriter.createCell();
		sxffWriter.setStringData("一级");
		sxffWriter.createCell();
		sxffWriter.setStringData("二级");
		sxffWriter.createCell();
		sxffWriter.setStringData("三级");
		sxffWriter.createCell();
		sxffWriter.setStringData("四级");
		sxffWriter.createCell();
		sxffWriter.setStringData("细胞核");
		sxffWriter.createCell();
		sxffWriter.setStringData("细胞核邮箱");
		sxffWriter.createCell();
		sxffWriter.setStringData("指导");
		sxffWriter.createCell();
		sxffWriter.setStringData("指导邮箱");
		sxffWriter.createCell();
		sxffWriter.setStringData("排序代码");
		sxffWriter.createCell();
		sxffWriter.setStringData("报税架构");
	}

	private void createShebaoSummaryHeader(SXSSFWriter sxffWriter) {
		sxffWriter.createRow();
		sxffWriter.createCell();
		sxffWriter.setStringData("公司名称");
		sxffWriter.createCell();
		sxffWriter.setStringData("总人数");
		sxffWriter.createCell();
		sxffWriter.setStringData("公司养老保险总额");
		sxffWriter.createCell();
		sxffWriter.setStringData("代扣养老金总额");
		sxffWriter.createCell();
		sxffWriter.setStringData("公司失业保险总额");
		sxffWriter.createCell();
		sxffWriter.setStringData("代扣失业保险总额");
		sxffWriter.createCell();
		sxffWriter.setStringData("公司工伤保险总额");
		sxffWriter.createCell();
		sxffWriter.setStringData("公司生育保险总额");
		sxffWriter.createCell();
		sxffWriter.setStringData("公司医疗保险总额");
		sxffWriter.createCell();
		sxffWriter.setStringData("代扣医疗保险总额");
		sxffWriter.createCell();
		sxffWriter.setStringData("公司住房保险总额");
		sxffWriter.createCell();
		sxffWriter.setStringData("代扣住房保险总额");
	}

	private void createShebaoHeader(SXSSFWriter sxffWriter) {
		sxffWriter.createRow();
		sxffWriter.createCell();
		sxffWriter.setStringData("职员姓名");
		sxffWriter.createCell();
		sxffWriter.setStringData("身份证号");
		sxffWriter.createCell();
		sxffWriter.setStringData("入保时间");
		sxffWriter.createCell();
		sxffWriter.setStringData("基数");
		sxffWriter.createCell();
		sxffWriter.setStringData("代扣养老保险");
		sxffWriter.createCell();
		sxffWriter.setStringData("代扣医疗保险");
		sxffWriter.createCell();
		sxffWriter.setStringData("代扣失业保险");
		sxffWriter.createCell();
		sxffWriter.setStringData("代扣住房保险");
		sxffWriter.createCell();
		sxffWriter.setStringData("公司养老保险");
		sxffWriter.createCell();
		sxffWriter.setStringData("公司医疗保险");
		sxffWriter.createCell();
		sxffWriter.setStringData("公司失业保险");
		sxffWriter.createCell();
		sxffWriter.setStringData("公司住房保险");
		sxffWriter.createCell();
		sxffWriter.setStringData("公司工伤保险");
		sxffWriter.createCell();
		sxffWriter.setStringData("公司生育保险");
	}

	@SuppressWarnings("unused")
	private void createMonthWagesHeader(SXSSFWriter sxffWriter, Class<?> clazz) {
		//TODO 自动生成头
//		sxffWriter.createRow();
//		sxffWriter.createCell();
//		sxffWriter.setStringData("姓名");
	}

//	private void insertCell(Class<PKaoQin> class1, List<PKaoQin> pKaoqins, String... args) {
//		Field[] fields = class1.getFields();
//	}
	private void createKaoqinHeader(SXSSFWriter sxffWriter) {
		createKaoqinHeader(sxffWriter, 0);
	}
	private void createKaoqinHeader(SXSSFWriter sxffWriter, int isMonthWages) {
		sxffWriter.createRow();
		sxffWriter.createCell();
		sxffWriter.setStringData("姓名");
		sxffWriter.createCell();
		sxffWriter.setStringData("公司");
		sxffWriter.createCell();
		sxffWriter.setStringData("部门");
		sxffWriter.createCell();
		sxffWriter.setStringData("岗位");
		sxffWriter.createCell();
		sxffWriter.setStringData("小组");
		if(isMonthWages == 1){
			sxffWriter.createCell();
			sxffWriter.setStringData("公司名称");
			sxffWriter.createCell();
			sxffWriter.setStringData("开户行");
			sxffWriter.createCell();
			sxffWriter.setStringData("职工帐号");
		}
		sxffWriter.createCell();
		sxffWriter.setStringData("身份证号");
		sxffWriter.createCell();
		sxffWriter.setStringData("出勤天数");
		sxffWriter.createCell();
		sxffWriter.setStringData("转正差额天数");
		if(isMonthWages == 1){
			sxffWriter.createCell();
			sxffWriter.setStringData("工资总额");
			sxffWriter.createCell();
			sxffWriter.setStringData("个人所得税");
			sxffWriter.createCell();
			sxffWriter.setStringData("代扣五险总额");
			sxffWriter.createCell();
			sxffWriter.setStringData("应扣总额");
			sxffWriter.createCell();
			sxffWriter.setStringData("罚款");
			sxffWriter.createCell();
			sxffWriter.setStringData("奖金");
			sxffWriter.createCell();
			sxffWriter.setStringData("补发工资");
			sxffWriter.createCell();
			sxffWriter.setStringData("应发总额");
			sxffWriter.createCell();
			sxffWriter.setStringData("实发总额");
			sxffWriter.createCell();
			sxffWriter.setStringData("单位五险总额");
			sxffWriter.createCell();
			sxffWriter.setStringData("保密补贴");
			sxffWriter.createCell();
			sxffWriter.setStringData("通讯补贴");
			sxffWriter.createCell();
			sxffWriter.setStringData("午餐补贴");
//			sxffWriter.createCell();
//			sxffWriter.setStringData("");
//			sxffWriter.createCell();
//			sxffWriter.setStringData("");
		}
		if(isMonthWages==0){
			sxffWriter.createCell();
			sxffWriter.setStringData("迟到应扣天数");
		}
		sxffWriter.createCell();
		sxffWriter.setStringData("事假时数");
		sxffWriter.createCell();
		sxffWriter.setStringData("病假时数");
		sxffWriter.createCell();
		sxffWriter.setStringData("旷工时数");
		if(isMonthWages==1){
			sxffWriter.createCell();
			sxffWriter.setStringData("迟到应扣天数");
			sxffWriter.createCell();
			sxffWriter.setStringData("产假天数");
			sxffWriter.createCell();
			sxffWriter.setStringData("年假累计");
			sxffWriter.createCell();
			sxffWriter.setStringData("病假累计");
			sxffWriter.createCell();
			sxffWriter.setStringData("代扣养老保险");
			sxffWriter.createCell();
			sxffWriter.setStringData("代扣医疗保险");
			sxffWriter.createCell();
			sxffWriter.setStringData("代扣失业保险");
			sxffWriter.createCell();
			sxffWriter.setStringData("代扣住房保险");
			sxffWriter.createCell();
			sxffWriter.setStringData("入职时间");
			sxffWriter.createCell();
			sxffWriter.setStringData("基本工资");
			sxffWriter.createCell();
			sxffWriter.setStringData("岗位工资");
			sxffWriter.createCell();
			sxffWriter.setStringData("绩效工资");
			
		}
		if(isMonthWages==0){
			sxffWriter.createCell();
			sxffWriter.setStringData("婚假天数");
			sxffWriter.createCell();
			sxffWriter.setStringData("产假天数");
			sxffWriter.createCell();
			sxffWriter.setStringData("丧假天数");
			sxffWriter.createCell();
			sxffWriter.setStringData("年假天数");
		sxffWriter.createCell();
		sxffWriter.setStringData("入职时间");
		}
		sxffWriter.createCell();
		sxffWriter.setStringData("离职时间");
		sxffWriter.createCell();
		sxffWriter.setStringData("转正时间");
		if(isMonthWages==1){
			sxffWriter.createCell();
			sxffWriter.setStringData("工资备注");
			sxffWriter.createCell();
			sxffWriter.setStringData("考勤备注");
			sxffWriter.createCell();
			sxffWriter.setStringData("历史工资");
		}
		sxffWriter.createCell();
		sxffWriter.setStringData("入职报表");
		sxffWriter.createCell();
		sxffWriter.setStringData("离职报表");
		sxffWriter.createCell();
		sxffWriter.setStringData("转正报表");
		sxffWriter.createCell();
		if(isMonthWages==0){
			sxffWriter.createCell();
			sxffWriter.setStringData("考勤备注");
		}
	}

	private void createHeader(SXSSFWriter sxffWriter) {
		sxffWriter.createRow();
		sxffWriter.createCell();
		sxffWriter.setStringData("职员姓名");
		sxffWriter.createCell();
		sxffWriter.setStringData("公司");
		sxffWriter.createCell();
		sxffWriter.setStringData("部门");
		sxffWriter.createCell();
		sxffWriter.setStringData("岗位");
		sxffWriter.createCell();
		sxffWriter.setStringData("小组");
		sxffWriter.createCell();
		sxffWriter.setStringData("职位");
		sxffWriter.createCell();
		sxffWriter.setStringData("性别");
		
		//add
		sxffWriter.createCell();
		sxffWriter.setStringData("身份证号");
		sxffWriter.createCell();
		sxffWriter.setStringData("出生年月");
		sxffWriter.createCell();
		sxffWriter.setStringData("民族");
		sxffWriter.createCell();
		sxffWriter.setStringData("学历");
		sxffWriter.createCell();
		sxffWriter.setStringData("毕业学校");
		sxffWriter.createCell();
		sxffWriter.setStringData("专业");
		sxffWriter.createCell();
		sxffWriter.setStringData("家庭住址");
		sxffWriter.createCell();
		sxffWriter.setStringData("联系电话");
		sxffWriter.createCell();
		sxffWriter.setStringData("家庭电话");
		sxffWriter.createCell();
		sxffWriter.setStringData("入职时间");
		sxffWriter.createCell();
		sxffWriter.setStringData("转正时间");
		sxffWriter.createCell();
		sxffWriter.setStringData("离职时间");
		sxffWriter.createCell();
		sxffWriter.setStringData("早期入职时间");
		sxffWriter.createCell();
		sxffWriter.setStringData("户口性质");
		sxffWriter.createCell();
		sxffWriter.setStringData("保险");
		sxffWriter.createCell();
		sxffWriter.setStringData("保险公司");
		sxffWriter.createCell();
		sxffWriter.setStringData("公司名称");
		sxffWriter.createCell();
		sxffWriter.setStringData("简历");
		sxffWriter.createCell();
		sxffWriter.setStringData("照片");
		sxffWriter.createCell();
		sxffWriter.setStringData("学历证书");
		sxffWriter.createCell();
		sxffWriter.setStringData("身份证复印件");
		sxffWriter.createCell();
		sxffWriter.setStringData("户口本");
		sxffWriter.createCell();
		sxffWriter.setStringData("离职证明");
		sxffWriter.createCell();
		sxffWriter.setStringData("合同");
		sxffWriter.createCell();
		sxffWriter.setStringData("关系");
		sxffWriter.createCell();
		sxffWriter.setStringData("入职表");
		sxffWriter.createCell();
		sxffWriter.setStringData("未签原因");
		sxffWriter.createCell();
		sxffWriter.setStringData("签定时间");
		sxffWriter.createCell();
		sxffWriter.setStringData("终止时间");
		sxffWriter.createCell();
		sxffWriter.setStringData("转正钱工资");
		sxffWriter.createCell();
		sxffWriter.setStringData("转正后工资");
		sxffWriter.createCell();
		sxffWriter.setStringData("银行账号");
		sxffWriter.createCell();
		sxffWriter.setStringData("户口地址");
		sxffWriter.createCell();
		sxffWriter.setStringData("邮编");
		
		sxffWriter.createCell();
		sxffWriter.setStringData("工作地点");
		
		//add
		sxffWriter.createCell();
		sxffWriter.setStringData("过去离职时间");
		sxffWriter.createCell();
		sxffWriter.setStringData("细胞核");
		sxffWriter.createCell();
		sxffWriter.setStringData("细胞核邮箱");
		sxffWriter.createCell();
		sxffWriter.setStringData("指导");
		sxffWriter.createCell();
		sxffWriter.setStringData("指导邮箱");
		sxffWriter.createCell();
		sxffWriter.setStringData("社保医院");
		sxffWriter.createCell();
		sxffWriter.setStringData("级别");
		sxffWriter.createCell();
		sxffWriter.setStringData("邮箱");
		sxffWriter.createCell();
		sxffWriter.setStringData("入职报表");
		sxffWriter.createCell();
		sxffWriter.setStringData("离职报表");
		sxffWriter.createCell();
		sxffWriter.setStringData("转正报表");
		sxffWriter.createCell();
		sxffWriter.setStringData("部门调整报表");
		
		sxffWriter.createCell();
		sxffWriter.setStringData("婚姻状况");
	}

	
	

}
