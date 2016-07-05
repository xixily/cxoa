package com.chaoxing.oa.service;


import java.util.List;

import com.chaoxing.oa.entity.page.MenuInfo;

public interface RoleMenuServiceI {
	public List<MenuInfo> findMenu(int roleId);
}
