package com.chaoxing.oa.service;

import java.util.List;

import com.chaoxing.oa.entity.page.PRenshiEmployee;

public interface ExportExcelService {

	/** 人事查询结果excel报表导出 **/
	public String getRenshiQueryExport(List<PRenshiEmployee> renshiEmployees);
}
