package com.chaoxing.oa.service;

import java.util.List;

import com.chaoxing.oa.entity.page.common.POStructV;
import com.chaoxing.oa.entity.page.employee.PKaoQin;
import com.chaoxing.oa.entity.page.employee.PMonthWages;
import com.chaoxing.oa.entity.page.employee.PRenshiEmployee;
import com.chaoxing.oa.entity.page.employee.PSheBaoSummary;
import com.chaoxing.oa.entity.page.employee.PshebaoDetail;

public interface ExportExcelService {

	/** 人事查询结果excel报表导出 **/
	public String getRenshiQueryExport(List<PRenshiEmployee> renshiEmployees);

	String getKaoqinExportExcel(List<PKaoQin> pKaoqins);

	String getMonthWagesExcel(List<PMonthWages> pMonthWages);

	public String getShebaoCompany(List<PshebaoDetail> pShebaoDetails);

	public String getShebaoSummary(List<PSheBaoSummary> pShebaoSummarys);

	public String getPOStructExcel(List<POStructV> pos);
}
