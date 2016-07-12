package com.chaoxing.oa.service.impl;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.chaoxing.oa.entity.page.PMenu;
import com.chaoxing.oa.entity.page.PUlList;
import com.chaoxing.oa.entity.page.PUser;
import com.chaoxing.oa.entity.po.UserName;
import com.chaoxing.oa.service.RoleMenuServiceI;
import com.chaoxing.oa.service.UserServiceI;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring2.xml","classpath:spring-hibernate2.xml"})
public class TestService {
	@Resource(name = "userService")
	private UserServiceI userService;
	@Resource(name = "roleMenuService")
	private RoleMenuServiceI roleMenuService;
//	@Resource(name = "roleMenuService")
//	private RoleMenuServiceI roleMenuService;
	@Test
	public void test() {
//		UserName userName = new UserName();
//		UserInfo userInfo = new UserInfo();
//		userInfo.setEmail("guoyu@chaoxing.com");
//		userInfo.setPassword("");
//		String password = userInfo.getPassword();
//		userInfo = userService.findUser(userInfo);
//		if(userInfo == null){
//			System.out.println("用户不存在！");
//		}else{
//			
//			if(password == userInfo.getPassword()){
//				System.out.println("登陆成功！");
//			}else{
//				System.out.println("密码错误！");
//			}
//		}
		try {
			List<PMenu> menuInfos = roleMenuService.findMenu(0);
			for (PMenu menuInfo : menuInfos) {
				if(menuInfo.getUls() == null){
					System.out.println(menuInfo.toString());
				}else{
					System.out.println(menuInfo.toString());
					Set<PUlList> uls = menuInfo.getUls();
					for (PUlList ulList : uls) {
						System.out.println(ulList.getText());
					}
				}
			}
		} catch (Exception e) {
		}
		fail("Not yet implemented");
	}
}
