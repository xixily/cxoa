package com.chaoxing.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chaoxing.oa.dao.BaseDaoI;
import com.chaoxing.oa.entity.page.MenuInfo;
import com.chaoxing.oa.entity.page.Right;
import com.chaoxing.oa.entity.page.UlList;
import com.chaoxing.oa.entity.po.RoleMenu;
import com.chaoxing.oa.service.RoleMenuServiceI;

@Service("roleMenuService")
/*@Transactional*/
public class RoleMenuServiceImpl implements RoleMenuServiceI {
	private static final Logger logger = Logger.getLogger(RoleMenuServiceImpl.class);
	private BaseDaoI<RoleMenu> roleMenuDao;

	public BaseDaoI<RoleMenu> getUsernameDao() {
		return roleMenuDao;
	}

	@Autowired
	public void setUsernameDao(BaseDaoI<RoleMenu> roleMenuDao) {
		this.roleMenuDao = roleMenuDao;
	}


	@Override
	public List< MenuInfo> findMenu(int roleId) {
		List<MenuInfo> l_menuInfos = new ArrayList<MenuInfo>();
		List<RoleMenu> roleMenus;
		List<RoleMenu> ulMenus;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		roleMenus = roleMenuDao.find("from RoleMenu r where r.roleId=:roleId and r.menuLevel = 1",params);
		ulMenus = roleMenuDao.find("from RoleMenu r where r.roleId=:roleId and r.menuLevel = 2",params);
		for (RoleMenu roleMenu : roleMenus) {
				MenuInfo menuInfo = new MenuInfo(); 
				menuInfo.setUserId(roleMenu.getRoleId());
				menuInfo.setMenuId(roleMenu.getMenuId());
				menuInfo.setMenuName(roleMenu.getMenuName());
				l_menuInfos.add(menuInfo);
				for (RoleMenu ulMenu : ulMenus) {
					if(ulMenu.getPreMenuId()== menuInfo.getMenuId()){
						UlList ulList = new UlList();
						ulList.setDomId(ulMenu.getMenuId());
						ulList.setText(ulMenu.getMenuName());
//						ulList.setState("closed");
//						ulList.setIconCls("");
						ulList.setUrl("");
//						ulMenus.remove(ulMenu);
						menuInfo.getUls().add(ulList);
					}
				}
		}
		return l_menuInfos;
	}
	/**
	 * 获取所有权限
	 * @return
	 */
	public Right getRight(){
		
		return null;
	}
}
