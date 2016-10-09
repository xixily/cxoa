package com.chaoxing.oa.config;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * TODO 做一个缓存工具，用来存放系统通用缓存，
 * 		包括系统配置和sessioin管理功能。
 * ① 利用spring去自动注入内容。
 * 		启动服务的时候，spring自动去实例化该对象，并从数据库把数据存入该结构体中。
 * @author Administrator
 *
 */
public class CacheUtil implements Serializable {
	private static final long serialVersionUID = -1071743560741219992L;
	private String cacheId;
	private String name;
	private Map<String, Object> contains;
	private List<CacheUtil> childen;
	public String getCacheId() {
		return cacheId;
	}
	public String getName() {
		return name;
	}
	public Map<String, Object> getContains() {
		return contains;
	}
	public List<CacheUtil> getChilden() {
		return childen;
	}
	public void setCacheId(String cacheId) {
		this.cacheId = cacheId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setContains(Map<String, Object> contains) {
		this.contains = contains;
	}
	public void setChilden(List<CacheUtil> childen) {
		this.childen = childen;
	}
	
}
