package com.chaoxing.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaoxing.oa.dao.BaseDaoI;
import com.chaoxing.oa.entity.page.PMenu;
import com.chaoxing.oa.entity.page.PUlList;
import com.chaoxing.oa.entity.po.RoleRights;
import com.chaoxing.oa.service.RoleMenuServiceI;

@Service("roleMenuService")
/*@Transactional*/
public class RoleMenuServiceImpl implements RoleMenuServiceI {
	private static final Logger logger = Logger.getLogger(RoleMenuServiceImpl.class);
	private BaseDaoI<RoleRights> rolerightsDao;



	public BaseDaoI<RoleRights> getRolerightsDao() {
		return rolerightsDao;
	}


	@Autowired
	public void setRolerightsDao(BaseDaoI<RoleRights> rolerightsDao) {
		this.rolerightsDao = rolerightsDao;
	}



	@Override
	public List< PMenu> findMenu(int roleId) {
		List<PMenu> l_menuInfos = new ArrayList<PMenu>();
		List<RoleRights> roleMenus;
		List<RoleRights> ulMenus;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		//TODO 如果是系统管理员的话，后面就没有条件，直接取所有菜单
		roleMenus = rolerightsDao.find("from RoleRights r where r.roleId.roleId=:roleId and r.menuLevel = 1 order by r.menuId",params);
		ulMenus = rolerightsDao.find("from RoleRights r where r.roleId.roleId=:roleId and r.menuLevel = 2",params);
		for (RoleRights roleMenu : roleMenus) {
				PMenu menuInfo = new PMenu(); 
				menuInfo.setUserId(roleMenu.getRoleId().getRoleId());
				menuInfo.setMenuId(roleMenu.getMenuId());
				menuInfo.setMenuName(roleMenu.getMenuName());
				l_menuInfos.add(menuInfo);
				for (RoleRights ulMenu : ulMenus) {
					if(ulMenu.getPreMenuId().getMenuId()== menuInfo.getMenuId()){
						PUlList ulList = new PUlList();
						ulList.setDomId(ulMenu.getMenuId());
						ulList.setText(ulMenu.getMenuName());
//						ulList.setState("closed");
//						ulList.setIconCls("");
						ulList.setUrl(roleMenu.getUrl());
//						ulMenus.remove(ulMenu);
						menuInfo.getUls().add(ulList);
					}
				}
		}
		return l_menuInfos;
	}


	@Override
	public List<PMenu> findAllMenu() {
		List<PMenu> l_menuInfos = new ArrayList<PMenu>();
		List<RoleRights> roleMenus;
		List<RoleRights> ulMenus;
		roleMenus = rolerightsDao.find("from RoleRights r where r.menuLevel = 1 order by r.menuId");
		ulMenus = rolerightsDao.find("from RoleRights r where r.menuLevel = 2 ");
		for (RoleRights roleMenu : roleMenus) {
				PMenu menuInfo = new PMenu(); 
				menuInfo.setUserId(roleMenu.getRoleId().getRoleId());
				menuInfo.setMenuId(roleMenu.getMenuId());
				menuInfo.setMenuName(roleMenu.getMenuName());
				l_menuInfos.add(menuInfo);
				for (RoleRights ulMenu : ulMenus) {
					if(ulMenu.getPreMenuId().getMenuId()== menuInfo.getMenuId()){
						PUlList ulList = new PUlList();
						ulList.setDomId(ulMenu.getMenuId());
						ulList.setText(ulMenu.getMenuName());
//						ulList.setState("closed");
//						ulList.setIconCls("");
						ulList.setUrl(ulMenu.getUrl());
//						ulMenus.remove(ulMenu);
						menuInfo.getUls().add(ulList);
					}
				}
		}
		return l_menuInfos;
	}
}
