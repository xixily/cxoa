package com.chaoxing.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaoxing.oa.dao.BaseDaoI;
import com.chaoxing.oa.entity.page.PMenus;
import com.chaoxing.oa.entity.po.Menu;
import com.chaoxing.oa.service.SystemService;

@Service("systemService")
public class SystemServiceImpl implements SystemService {
	private BaseDaoI<Menu> menuDao;

	public BaseDaoI<Menu> getMenuDao() {
		return menuDao;
	}
	@Autowired
	public void setMenuDao(BaseDaoI<Menu> menuDao) {
		this.menuDao = menuDao;
	}

	@Override
	public List<PMenus> getMenus(PMenus pmenu) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<PMenus> pmenus = new ArrayList<PMenus>();
		StringBuffer hql = new StringBuffer("from Menu t where 1=1 and t.menuLevel=1 ");
		addconditions(params, pmenu, hql);
		List<Menu> menus = menuDao.find(hql.toString(), params);
		for (Menu menu : menus) {
			PMenus _menu = new PMenus();
			BeanUtils.copyProperties(menu, _menu);
			if(menu.getPreMenuId()!=null){
				_menu.set_preMenuId(menu.getPreMenuId().getMenuId());
			}
			copy(menu.getMenus(),_menu);
			pmenus.add(_menu);
		}
		return pmenus;
	}
	
	private void copy(Set<Menu> menus, PMenus _menu) {
		if(menus.size()>0){
			Set<PMenus> children = new TreeSet<PMenus>();
			for (Menu menu : menus) {
				PMenus p = new PMenus();
				p.setMenuId(menu.getMenuId());
				p.setMenuName(menu.getMenuName());
				p.setMenuLevel(menu.getMenuLevel());
				p.set_preMenuId(menu.getPreMenuId().getMenuId());
				p.setUrl(menu.getUrl());
				p.setIconCls(menu.getIconCls());
				if(menu.getMenus().size()>0){
					copy(menu.getMenus(),p);
				}
				children.add(p);
			}
			_menu.setChildren(children);
		}
	}
 	
	private void addconditions(Map<String, Object> params, PMenus pmenu,StringBuffer hql) {
		if(pmenu != null){
			if(pmenu.getMenuName()!= null && pmenu.getMenuName()!= ""){
				hql.append(" and t.menuName like :menuName ");
				params.put("menuName", "%" + pmenu.getMenuName() + "%");
			}
		}
	}

}
