package com.chaoxing.oa.service;


import java.util.List;

import com.chaoxing.oa.entity.page.PMenu;

public interface RoleMenuServiceI {
	public List<PMenu> findMenu(int roleId);
}
