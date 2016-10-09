package com.chaoxing.oa.util;

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

import com.chaoxing.oa.entity.page.Json;
import com.chaoxing.oa.entity.page.hetong.PFahuo;
import com.chaoxing.oa.test.TestHttpProvider;
import com.chaoxing.oa.util.http.HttpProvider;

public class SFSender {
	private static SFSender sfSender;
	
	public static SFSender getInstance(){
		if(sfSender == null){
			synchronized (SFSender.class) {
				sfSender = new SFSender();
			}
		}
		return sfSender;
	}
	
	/**
	 * 发送一个快递请求
	 * @param pfahuo 发货信息
	 * @param xml 发货xml配置信息
	 * @return
	 */
	public Json sendKuaidi(PFahuo pfahuo, String xml){
		Json result = new Json();
		String url = ResourceUtil.getUrl1();
		String checkword = ResourceUtil.getCheckWord();
		String verifyCode=Md5AndBase64.md5EncryptAndBase64(xml + checkword);
		StringBuffer params = new StringBuffer("xml=");
		try {
			params.append(URLEncoder.encode(xml, "UTF-8"));
			params.append("&verifyCode=");
			params.append(verifyCode);
			try {
				String result1 = HttpProvider.sendPost(url, params.toString().getBytes());
				Map<String, Object> obj =new HashMap<String, Object>();
				System.out.println(result1);
				Document document = DocumentHelper.parseText(result1);
				Element response = document.getRootElement();
				Element head = response.element("Head");
				if(head.getText().equalsIgnoreCase("OK")){
					result.setSuccess(true);
					Element body = response.element("Body");
					for (Iterator it = body.elementIterator();it.hasNext();) {
						Element element = (Element) it.next();
						System.out.println("遍历节点：" + element.getName() + "  >>>>");
						List<Attribute> attrs = element.attributes();
						for (Attribute attr : attrs) {
							//TODO 返回一个 map集合收集属性，或者直接插入数据库。
							String name=attr.getName();//属性名称  
							String value=attr.getValue();//属性的值  
							obj.put(name, value);
							System.out.println("属性名称："+name+",属性值："+value);  
						}
					}
					result.setObj(obj);
				}else{
					Element error = response.element("ERROR");
					String errCode = error.attributeValue("code");
					String errText = error.getText();
					result.setMsg("获取订单出错，错误代码：[" + errCode + "],错误原因：" + errText);
					System.out.println("获取订单出错，错误代码：[" + errCode + "],错误原因：" + errText);
				}
			} catch (Exception e) {
				result.setMsg( "请求失败~！");
				System.out.println("请求失败~！");
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			result.setMsg("URLEncoder.encode(xml, 'UTF-8'),解析参数xml失败");
			System.out.println("URLEncoder.encode(xml, 'UTF-8'),解析参数xml失败");
			e.printStackTrace();
		}
		return result;
	}
}
