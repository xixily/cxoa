package com.chaoxing.oa.test;

import com.chaoxing.oa.entity.page.hetong.PFahuo;

public class TestXml {

	public static void main(String[] args) {
		PFahuo pfahuo = new PFahuo();
		pfahuo.setOrderid(123);
		pfahuo.setHetongCode(55238);
		pfahuo.setD_contact("lily");
		pfahuo.setD_company("company");
		pfahuo.setD_address("China Jiang Xi");
		pfahuo.setD_tel("18146612837");
		pfahuo.setPostMethod("顺丰");
		pfahuo.setjDate("2016.09.15");
		pfahuo.setMailno("9623108");
		pfahuo.setRemark("这是一条测试数据。");
		pfahuo.setD_post_code("330022");
		pfahuo.setContent("这是一条测试数据的内容");
		pfahuo.setAreaCode("0791");
		pfahuo.setSender("test cc");
		pfahuo.setD_city("BeiJing");
		pfahuo.setArea("haidian");
		pfahuo.setRecorder("jj");
	}

}
