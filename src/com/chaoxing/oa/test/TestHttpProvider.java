package com.chaoxing.oa.test;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.chaoxing.oa.util.Md5AndBase64;
import com.chaoxing.oa.util.ResourceUtil;
import com.chaoxing.oa.util.http.HttpProvider;

public class TestHttpProvider {
	public static void main(String[] args) {
		String url = ResourceUtil.getUrl1();
		String port = ResourceUtil.getPort1();
		String checkword = ResourceUtil.getCheckWord();
		String xml = Md5AndBase64.loadFile(TestHttpProvider.class.getClass().getResource("/").getFile().toString() + "a.xml");
		String verifyCode=Md5AndBase64.md5EncryptAndBase64(xml + checkword);
		StringBuffer params = new StringBuffer("xml=");
		Map<String,Object> rs = new HashMap<String, Object>();
		try {
			params.append(URLEncoder.encode(xml, "UTF-8"));
//			params.append(URLEncoder.encode(xml, "UTF-8"));
			params.append("&verifyCode=");
			params.append(verifyCode);
			try {
				String result = HttpProvider.sendPost(url, params.toString().getBytes());
				System.out.println(result);
				Document document = DocumentHelper.parseText(result);
				Element response = document.getRootElement();
//				List<Element> respE = rootElment.elements("Response");
//				Element res = rootElment.element("Response");
				Element head = response.element("Head");
				if(head.getText().equalsIgnoreCase("OK")){
					Element body = response.element("Body");
//					Element orderResponse = body.element("OrderResponse");
					for (Iterator it = body.elementIterator();it.hasNext();) {
						Element element = (Element) it.next();
						System.out.println("遍历节点：" + element.getName() + "  >>>>");
						List<Attribute> attrs = element.attributes();
						for (Attribute attr : attrs) {
							//TODO 返回一个 map集合收集属性，或者直接插入数据库。
							String name=attr.getName();//属性名称  
							String value=attr.getValue();//属性的值  
							System.out.println("属性名称："+name+",属性值："+value);  
						}
					}
//					List<Attribute> attrs = orderResponse.attributes();
//					for (Attribute attr : attrs) {
//						//TODO 返回一个 map集合收集属性，或者直接插入数据库。
//						String name=attr.getName();//属性名称  
//						String value=attr.getValue();//属性的值  
//						System.out.println("属性名称："+name+"属性值："+value);  
//					}
				}else{
					Element error = response.element("ERROR");
					String errCode = error.attributeValue("code");
					String errText = error.getText();
					System.out.println("获取订单出错，错误代码：[" + errCode + "],错误原因：" + errText);
				}
//				System.out.println(repe.asXML());
//				System.out.println(head.getText());
//				for (Element e : respE) {
//				}
//				StringReader sr = new StringReader(result);
//				InputSource is = new InputSource(sr);
//				DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
//				DocumentBuilder builder = df.newDocumentBuilder();
//				Document doc = builder.parse(is);
//				Element em = doc.getDocumentElement();
//				NodeList nodes = em.getChildNodes();
//				System.out.println(nodes.getLength());
//				System.out.println(nodes.item(1));
//				System.out.println(em.getChildNodes());
			} catch (Exception e) {
				System.out.println("请求失败~！");
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println("URLEncoder.encode(xml, 'UTF-8'),解析参数xml失败");
			e.printStackTrace();
		}
	}
}
