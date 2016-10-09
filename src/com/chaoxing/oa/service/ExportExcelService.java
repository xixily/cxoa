package com.chaoxing.oa.service;

import java.util.List;

import com.chaoxing.oa.entity.page.PKaoQin;
import com.chaoxing.oa.entity.page.PMonthWages;
import com.chaoxing.oa.entity.page.PRenshiEmployee;
import com.chaoxing.oa.entity.page.PSheBaoSummary;
import com.chaoxing.oa.entity.page.PshebaoDetail;

public interface ExportExcelService {

	/** 人事查询结果excel报表导出 **/
	public String getRenshiQueryExport(List<PRenshiEmployee> renshiEmployees);

	String getKaoqinExportExcel(List<PKaoQin> pKaoqins);

	String getMonthWagesExcel(List<PMonthWages> pMonthWages);

	public String getShebaoCompany(List<PshebaoDetail> pShebaoDetails);

	public String getShebaoSummary(List<PSheBaoSummary> pShebaoSummarys);
}
