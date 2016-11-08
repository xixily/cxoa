package com.chaoxing.oa.test;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.chaoxing.oa.dao.BaseDaoI;
import com.chaoxing.oa.entity.po.employee.UserName;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring2.xml","classpath:spring-hibernate2.xml"})
public class JunitTestDao {
	@Resource(name = "baseDao")
	private BaseDaoI<UserName> ud;
	@Resource(name = "baseDao")
	private BaseDaoI<Object> od;
//	@Resource(name = "baseDao")
//	private BaseDaoI<Struct> structDao;
	@Before
	public void setUp() throws Exception{
		
	}
	@Test
	public void testMyDao(){
//		try {
//			List<Struct> structs = structDao.find("from Structure where id = 1");
//			for (Struct structure : structs) {
//				System.out.println("一级架构" + structure.getName() +"下面的二级架构有:");
//				for (Struct st : structure.getChildren()) {
//					System.out.println("##" +st.getName());
//					System.out.println("二级" + st.getName() +"下面的三级架构有:");
//					for (Struct st2 : st.getChildren()) {
//						System.out.println("###" + st2.getName());
//						System.out.println("三级" + st2.getName() +"下面的四级架构有:");
//						for (Struct st3 : st2.getChildren()) {
//							System.out.println("####" + st3.getName());
//						}
//					}
//				}
//			}
//			Map<String, Object> params = new HashMap<String, Object>();
//			params.put("date1", "2016.06");
//			params.put("date2", "2016.05");
////			params.put("out", "@p_out");
//			
////			params.put("mail", "guoyu@chaoxing.com");
////			UserName t = ud.get("from TUserName t where t.email = :mail", params);
//			String sql = "{CALL update_kaoqin_pr( :date1, :date2)}";
////			String sql2 = "select @p_out";
////			String sql = "{CALL update_monthWages_pr()}";
//			od.prepareCall(sql, params);
////			Object rs = od.find(sql2);
////			System.out.println(rs);
////			if(null != t){
////				System.out.println(t.toString());
////			}else{
////				System.out.println("找不着");
////			}
//		} catch (Exception e) {
//			System.out.println("连接失败！" + e);
//		}
	}
	@Ignore
	public void noyet(){
		
		System.out.println("fail");
	}
}
