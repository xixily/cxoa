package com.chaoxing.oa.config;

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
	public final static int SESSION_INVALIAD = -1;
	/**
	 * 请求成功
	 */
	public final static int REQUEST_SUCCESSED = 0;
	/**
	 * 权限不够
	 */
	public final static int NO_RIGHTS = 1;
	/**
	 * 请求错误
	 */
	public final static int REQUEST_ERROR = 2;
	/**
	 * 
	 */
	public final static String UPLOAD_DIR = "uploadDir";
	/**
	 * excel文件目录
	 */
	public final static String DOWNLOAD_EXCEL = ResourceUtil.getDownloadDirectory();
}
