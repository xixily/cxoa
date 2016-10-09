package com.chaoxing.oa.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaoxing.oa.dao.BaseDaoI;
import com.chaoxing.oa.entity.page.PMenu;
import com.chaoxing.oa.entity.page.PUlList;
import com.chaoxing.oa.entity.po.Menu;
import com.chaoxing.oa.entity.po.RoleResources;
//import com.chaoxing.oa.entity.po.RoleRights;
import com.chaoxing.oa.service.RoleMenuService;

@Service("roleMenuService")
/*@Transactional*/
public class RoleMenuServiceImpl implements RoleMenuService {
	private static final Logger logger = Logger.getLogger(RoleMenuServiceImpl.class);
//	private BaseDaoI<RoleRights> rolerightsDao;
	private BaseDaoI<Menu> menuDao;
	private BaseDaoI<RoleResources> roleResourcesDao;
	
	public BaseDaoI<RoleResources> getRoleResourcesDao() {
		return roleResourcesDao;
	}
	@Autowired
	public void setRoleResourcesDao(BaseDaoI<RoleResources> roleResourcesDao) {
		this.roleResourcesDao = roleResourcesDao;
	}
	public BaseDaoI<Menu> getMenuDao() {
		return menuDao;
	}
	@Autowired
	public void setMenuDao(BaseDaoI<Menu> menuDao) {
		this.menuDao = menuDao;
	}

//	public BaseDaoI<RoleRights> getRolerightsDao() {
//		return rolerightsDao;
//	}

//	@Autowired
//	public void setRolerightsDao(BaseDaoI<RoleRights> rolerightsDao) {
//		this.rolerightsDao = rolerightsDao;
//	}

//	@Override
//	public List< PMenu> findMenu(int roleId,HttpSession session) {
//		List<PMenu> l_menuInfos = new ArrayList<PMenu>();
//		List<RoleRights> roleMenus;
//		List<RoleRights> ulMenus;
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("roleId", roleId);
//		StringBuffer hql = new StringBuffer("");
//		//TODO 如果是系统管理员的话，后面就没有条件，直接取所有菜单
//		roleMenus = rolerightsDao.find("from RoleRights r where r.roleId.roleId=:roleId and r.menuLevel = 1 order by r.menuId",params);
//		ulMenus = rolerightsDao.find("from RoleRights r where r.roleId.roleId=:roleId and r.menuLevel = 2",params);
//		for (RoleRights roleMenu : roleMenus) {
//				PMenu menuInfo = new PMenu(); 
//				menuInfo.setUserId(roleMenu.getRoleId().getRoleId());
//				menuInfo.setMenuId(roleMenu.getMenuId());
//				menuInfo.setMenuName(roleMenu.getMenuName());
//				for (RoleRights ulMenu : ulMenus) {
//					if(ulMenu.getPreMenuId().getMenuId()== menuInfo.getMenuId()){
//						PUlList ulList = new PUlList();
//						ulList.setDomId(ulMenu.getMenuId());
//						ulList.setText(ulMenu.getMenuName());
////						ulList.setState("closed");
////						ulList.setIconCls("");
//						ulList.setUrl(roleMenu.getUrl());
////						ulMenus.remove(ulMenu);
//						menuInfo.getUls().add(ulList);
//					}
//				}
//				l_menuInfos.add(menuInfo);
//		}
//		return l_menuInfos;
//	}
	
	@Override
	public List<PMenu> findMenuByRole(int roleId, HttpSession session) {
		List<PMenu> l_menuInfos = new ArrayList<PMenu>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		List<RoleResources> roleResources = roleResourcesDao.find("from RoleResources r where r.roleId.roleId=:roleId and menuId.menuLevel=1 ",params);
		List<RoleResources> roleResources2 = roleResourcesDao.find("from RoleResources r where r.roleId.roleId=:roleId and menuId.menuLevel=2 ",params);
		for (RoleResources roleRe : roleResources) {
			PMenu menuInfo = new PMenu(); 
//			UserRole urole = roleRe.getRoleId();
			menuInfo.setUserId(roleRe.getRoleId().getRoleId());
			menuInfo.setMenuId(roleRe.getMenuId().getMenuId());
			menuInfo.setMenuName(roleRe.getMenuId().getMenuName());
			for (RoleResources roleR : roleResources2) {
				if(roleR.getMenuId().getPreMenuId().getMenuId()== menuInfo.getMenuId()){
					PUlList ulList = new PUlList();
					ulList.setSortCode(roleR.getSortCode());
					ulList.setDomId(roleR.getMenuId().getMenuId());
					ulList.setText(roleR.getMenuId().getMenuName());
					ulList.setIconCls(roleR.getMenuId().getIconCls());
					ulList.setUrl(roleR.getUrl());
					menuInfo.getUls().add(ulList);
				}
			}
			l_menuInfos.add(menuInfo);
		}
		Collections.sort(l_menuInfos);
		return l_menuInfos;
	}
	
	@Override
	public List<PMenu> findAllMenu() {
		List<PMenu> l_menuInfos = new ArrayList<PMenu>();
		Map<String, Object> params = new HashMap<String, Object>();
		List<Menu> menus1 = menuDao.find("from Menu t where t.menuLevel=1");
		for (Menu menu : menus1) {
			PMenu menuInfo = new PMenu(); 
//			menuInfo.setUserId(menu.getRoleId().getRoleId());
			menuInfo.setMenuId(menu.getMenuId());
			menuInfo.setMenuName(menu.getMenuName());
			menuInfo.setSortCode(menu.getSortCode());
			for (Menu menu2 : menu.getMenus()) {
				PUlList ulList = new PUlList();
				ulList.setSortCode(menu2.getSortCode());
				ulList.setDomId(menu2.getMenuId());
				ulList.setText(menu2.getMenuName());
				ulList.setIconCls(menu2.getIconCls());
				if(menu2.getUrl()==null){
					ulList.setUrl("");
				}else{
					ulList.setUrl(menu2.getUrl());
				}
				menuInfo.getUls().add(ulList);
			}
			l_menuInfos.add(menuInfo);
		}
		Collections.sort(l_menuInfos);
		return l_menuInfos;
	}

	/*@Override
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
	}*/
}
