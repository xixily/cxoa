//package com.chaoxing.oa.test;
//
//import java.security.cert.CertificateException;
//import java.security.cert.X509Certificate;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//
//import org.apache.http.Consts;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.conn.scheme.Scheme;
//import org.apache.http.conn.ssl.SSLSocketFactory;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.impl.conn.PoolingClientConnectionManager;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//
//import com.chaoxing.oa.util.Md5AndBase64;
//
//
//public class Demo {
//	private static HttpClient getHttpClient(int port){
//		PoolingClientConnectionManager pcm = new PoolingClientConnectionManager();
//		SSLContext ctx=null;
//		try{
//			ctx = SSLContext.getInstance("TLS");
//			X509TrustManager x509=new X509TrustManager(){
//				public void checkClientTrusted(X509Certificate[] xcs, String string)
//					throws CertificateException {
//				}
//				public void checkServerTrusted(X509Certificate[] xcs, String string)
//					throws CertificateException {
//				}
//				public X509Certificate[] getAcceptedIssuers(){
//					return null;
//				}
//			};
//			ctx.init(null, new TrustManager[]{x509}, null);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//		Scheme sch = new Scheme("https", port, ssf);
//		pcm.getSchemeRegistry().register(sch);
//		return new DefaultHttpClient(pcm);
//	}
//	public static void main(String args[]) throws Exception {
//		//参数 begin
//		//验证IP的URL
////		String url = "https://bsp-test.sf-express.com:9443/bsp-ois/sfexpressService";//外网地址
////		String url = "https://10.0.78.14/bsp-ois/sfexpressService";//顺丰内网地址
//		//不验证IP的URL
////		String url = "https://bsp-oisp.test.sf-express.com/bsp-oisp/sfexpressService";//外网地址
////		String url = "https://10.0.78.9/bsp-oisp/sfexpressService";//顺丰内网地址
//		String url = "http://bspoisp.sit.sf-express.com:11080/bsp-oisp/sfexpressService";//顺丰外网地址
////		int port = 9443;
////		int port = 443;
//		int port = 11080;
//		
//		String checkword = "j8DzkIFgmlomPt0aLuwU";//"PAYHZH    Hi9go87nCbFEuRWC";
////		String xmlFile="D:\\JavaDev\\workspace\\Demo-Java-Https-Post-BSP3.2\\src\\sf\\xml.txt";
//		//参数 end
//		String xml = Md5AndBase64.loadFile(Demo.class.getClass().getResource("/").getFile().toString() + "a.xml");
//		String verifyCode=Md5AndBase64.md5EncryptAndBase64(xml + checkword);
//		
//		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//		nvps.add(new BasicNameValuePair("xml", xml));
//		nvps.add(new BasicNameValuePair("verifyCode", verifyCode));
//		
//		HttpClient httpclient=getHttpClient(port);
//		HttpPost httpPost = new HttpPost(url);
//		httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
//		HttpResponse response = httpclient.execute(httpPost);
//
//		if (response.getStatusLine().getStatusCode() == 200){
//			System.out.println(EntityUtils.toString(response.getEntity()));
//		} else {
//			EntityUtils.consume(response.getEntity());
//			throw new RuntimeException("response status error: " + response.getStatusLine().getStatusCode());
//		}
//	}
//}
