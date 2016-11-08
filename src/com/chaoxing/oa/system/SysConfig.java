package com.chaoxing.oa.system;

import com.chaoxing.oa.util.ResourceUtil;

public class SysConfig {
	/**
	 * 降序排序
	 */
	public final static String DESC = "desc"; 
	/**
	 * 升序排序
	 */
	public final static String ASC = "asc"; 
	/**
	 * session 失效
	 */
	public final static String SESSION_INVALIAD = "-1";
	/**
	 * 请求成功
	 */
	public final static String REQUEST_SUCCESSED = "0";
	/**
	 * 权限不够
	 */
	public final static String NO_RIGHTS = "1";
	/**
	 * 请求错误
	 */
	public final static String REQUEST_ERROR = "2";
	/**
	 * 
	 */
	public final static String UPLOAD_DIR = "uploadDir";
	/**
	 * excel文件目录
	 */
	public final static String DOWNLOAD_EXCEL = ResourceUtil.getDownloadDirectory();
	/**
	 * 系统配置类型 -- 社保汇总
	 */
	public final static String SHEBAO_SUMMARY = "shebaoSummary";
	/**
	 * 系统配置类型 -- 考勤开关
	 */
	public final static String KAOQIN_BUTTON = "kaoqin_button";
	/**
	 * 系统配置类型 -- 工资发放
	 */
	public final static String MONTHWAGE_FAFANG = "month_wages_fafang";
	/**
	 * 缓存 -- 公用
	 */
	public final static String CACHE_COMMON = "common_";
	public final static String COMMON_JIAGOU = "jiagou";
	public final static String COMMON_COMPANY = "company";
	public final static String COMMON_HOUSEHOLD_TYPE = "householdType";
	public final static String COMMON_LEVEL = "level";
	/**
	 * 缓存 -- employee
	 */
	public final static String CACHE_EMPLOYEE = "employee_";
	/**
	 * 缓存 -- user
	 */
	public final static String CACHE_USER = "user_";
	/**
	 * 缓存 -- system
	 */
	public final static String CACHE_SYSTEM = "system_";
	public final static String SYSTEM_MENUS = "menus";
}
