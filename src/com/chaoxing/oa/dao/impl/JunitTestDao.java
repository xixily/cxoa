package com.chaoxing.oa.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.chaoxing.oa.dao.BaseDaoI;
import com.chaoxing.oa.entity.po.UserName;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring2.xml","classpath:spring-hibernate2.xml"})
public class JunitTestDao {
	@Resource(name = "baseDao")
	private BaseDaoI<UserName> ud;
	@Before
	public void setUp() throws Exception{
		
	}
	@Test
	public void testMyDao(){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("mail", "guoyu@chaoxing.com");
			UserName t = ud.get("from TUserName t where t.email = :mail", params);
			if(null != t){
				System.out.println(t.toString());
			}else{
				System.out.println("找不着");
			}
		} catch (Exception e) {
			System.out.println("连接失败！" + e);
		}
	}
	@Ignore
	public void noyet(){
		
		System.out.println("fail");
	}
}
