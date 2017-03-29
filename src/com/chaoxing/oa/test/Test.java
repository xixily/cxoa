package com.chaoxing.oa.test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.chaoxing.oa.entity.page.employee.PRenshiEmployee;
import com.chaoxing.oa.entity.page.employee.PWagesDate;
import com.chaoxing.oa.util.io.SXSSFWriter;
import com.chaoxing.oa.util.system.DateUtil;

public class Test {

	public static void main(String[] args) {
//		List<String> lis = new ArrayList<String>();
//		lis.add("");
//		int looper = 100;
//		for (int i = 0; i < 10; i++) {
//			lis.add((looper+i)+"");
//		}
//		Iterator<String> it = lis.iterator();
//		while(it.hasNext()){
//			System.out.println(it.next());
//			it.remove();
//			lis.size();
//			System.out.println("list size:"+lis.size());
//		}
//		System.out.println("list size:"+lis.size());
		
//		char[] str = new char [2147483646];
		String idcard = "362424199301252517";
		String right = idcard.substring(6).substring(0, 4);
		System.out.println(idcard.substring(6).substring(0, 4));
		System.out.println(Math.round(Math.random()*10));
//		System.out.println(Integer.MAX_VALUE);
//		int i = 708600000;
//		boolean flag = true;
//		char[] str;
//		while(flag){
//			try{
//				str = new char [i++];
//				System.out.println("正常：" + (i+1));
//			}catch(Exception e){
//				System.out.println(i);
//				flag = false;
//			}
//		}
//		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM");
//		Calendar cal = Calendar.getInstance();
//		String afterDate = df.format(cal.getTime());
//		cal.add(Calendar.MONTH, -1);
//		String date = df.format(cal.getTime());
//		cal.add(Calendar.MONTH, -1);
//		String preDate = df.format(cal.getTime());
//		System.out.println(afterDate);
//		System.out.println(date);
//		System.out.println(preDate);
		
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.MONTH, 0);
//		cal.set(cal.get(Calendar.YEAR), 0, 1, 0, 0, 0);
////		System.out.println(DateUtil.format(cal, null));
//		Date date = new Date();
//		System.out.println(DateUtil.format(date));
//		System.out.println(date.toLocaleString());
//		String s_remarks = "试用3200.8，转正4000";
//		
////		String str = "1000万元";
//		String str = "试用3200.232，转正4000.012,0.854,0.312";
////        String regex = "\\d*\\.\\d";
//        String regex = "[1-9]\\d*\\.\\d*|[1-9]\\d*|0\\.\\d*[1-9]\\d*";
//        Pattern p = Pattern.compile(regex);
//        Matcher m = p.matcher(str);
//        int i = 0;
//        while(m.find()) {
//        	System.out.println(++i);
//            if(!"".equals(m.group()))
//                System.out.println(m.group());
//        }
//		String regEx = "\\d";
//		String reg = "[^0-9]+";
//		String regEx = "\\d*\\.\\d*\\|\\d*|0\\.\\d*$/";
//		Pattern p = Pattern.compile(regEx);
//		Pattern p = Pattern.compile(reg);
//		Matcher m = p.matcher(s_remarks);
////		System.out.println(m.replaceAll("").trim());
//		while(m.find()){
//			if(!"".equals(m.group())){
//				System.out.println(m.group());
//			}
////			System.out.println(m.group()+",");
//		}
//		System.out.println(s_remarks.substring(s_remarks.indexOf("正")+1, s_remarks.length()));
//		StringBuffer stb = new StringBuffer();
//		stb.append("[sdafdasfasdfsa], ");
//		
//		System.out.println(stb.substring(0, stb.length()-2));
//		
//		String str = "201610部门调整：前场/教图事业部/浙江/（浙江）范凯|前场/教图事业部/浙江/（浙江）徐鸽";
////		str.split("|");
//		for (String st : str.substring(str.indexOf("：")+1).split("\\|")) {
//			System.out.println(st);
//		}
//		String str2 = "30145";
//		System.out.println(str2.contains("3"));
//		System.out.println(DateUtil.format(new Date(),"yyyyMM"));
//		Date date = new Date();
//		Calendar.getInstance().add(Calendar.MONTH, -1);
//		SimpleDateFormat.
//		System.out.println(new SimpleDateFormat("yyyyMM").format(Calendar.getInstance().getTime()));
//		System.out.println(DateUtil.format(Calendar.getInstance(),"yyyyMM"));
//		BigDecimal bg = new BigDecimal(22.00f);
//		System.out.println("201611".compareTo(null));
////		bg.divide(new BigDecimal(21),4);
////		System.out.println(bg.divide(new BigDecimal(21),3,BigDecimal.ROUND_HALF_UP).floatValue());
////		System.out.println(bg.floatValue()==21);
//		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
//		Calendar cal = Calendar.getInstance();
////		Integer year = cal.get(Calendar.YEAR);
//		String afterDate = df.format(cal.getTime());
//		System.out.println(afterDate);
//		String[] t = afterDate.split("\\.");
//		System.out.println(new BigDecimal("12").multiply(new BigDecimal(2)).subtract(new BigDecimal(4)));
//		System.out.println(t);
//		String afterDateC = afterDate.split(".")[0]+afterDate.split(".")[1];
//		System.out.println(afterDateC);
//		Calendar cal = Calendar.getInstance();
//		System.out.println(cal.get(Calendar.YEAR));
//		String sql = "TRUNCATE TABLE 考勤;"
//				+"INSERT INTO 考勤(eId,入职时间,入职报表,转正时间,转正报表,离职时间,离职报表,级别) "
//				+"SELECT id,入职时间,入职报表,转正时间,转正报表,离职时间,离职报表,级别 FROM username "
//				+"WHERE (离职时间 IS NULL AND (入职报表<'201611' OR 入职报表 IS NULL )AND (入职时间 < '2016.11' OR 入职时间 IS NULL))"
//				+"OR (离职时间=''  AND (入职报表<'201611' OR 入职报表 IS NULL )AND (入职时间 < '2016.11' OR 入职时间 IS NULL))"
//				+"OR (离职报表 >= '201610')";
//		System.out.println(sql);
//		System.out.println("".equals(null));
//		Math.random();
//		List<Integer> list = new ArrayList<Integer>();
//		for (int i = 0; i <20 ;i++) {
////			System.out.println((int)(Math.random()*10));
//			list.add((int)(Math.random()*10));
//		}
//		Iterator<Integer> it = list.iterator();
//		System.out.println("第一次循环");
//		while(it.hasNext()){
//			int i = it.next();
//			if(i%2 == 0){
//				System.out.println("被移除的："+i);
//				it.remove();
////				list.remove(i);
//			}
//		}
//		it = list.iterator();
//		System.out.println("第二次循环");
//		while(it.hasNext()){
//			System.out.println("剩下的：" + it.next());
//		}
//		Float i = 1.39f;
//		float b;
//		System.out.println((float)i);
//		b=i;
//		System.out.println(b*i);
//		POStructs pos = new POStructs();
//		Set<POStructs> cc = new HashSet<POStructs>();
//		POStructs pp = new POStructs();
//		pp.setId(33);
//		cc.add(pp);
//		pos.setChildren(cc);
////		pos.setChildren(new HashSet<POStructs>());
//		pos.setId(1);
//		pos.setCellCore("xiaoli");
//		pos.setCellCoreEmail("xiaoli@chaoxing.com");
//		pos.setDepartmentId(123);
//		pos.setFirstLevel("zhong");
//		pos.setSecondLevel("qian");
//		pos.setThirdLevel("xiaoshou");
//		pos.setFourthLevel("jiaowu");
//		pos.setGuidance("xiaoliu");
//		pos.setGuidanceEmail("xiaoliu@chaoxing.com");
//		pos.setLevel(4);
//		pos.setParentId(111);
//		pos.setPreId(111);
//		pos.setSortCode("ABC");
//		pos.setState("bjd");
//		pos.setTaxStructure("jiaowu");
//		Class<?> clazz = pos.getClass();
//		Field[] fs = clazz.getDeclaredFields();
//		Method method = null;
//		String name;
//		Object value;
//		String type;
//		for (Field field : fs) {
//			type = field.getType().getSimpleName();
//			name = field.getName();
//			try {
//				method = clazz.getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1));
//				value = method.invoke(pos);
//				System.out.println("====>name:" + name);
//				System.out.println("method:" + method.getName());
//				System.out.println("value:" + value);
////				if(value instanceof Object){
////					System.out.println("我是非基础类型" + value);
////				}
//				System.out.println("type:" + type);
//			} catch (NoSuchMethodException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (SecurityException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IllegalArgumentException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (InvocationTargetException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		String web_url = Test.class.getResource("").getFile().toString();
//		String web_url2 = Test.class.getResource("/").getFile().toString().split("WEB-INF/")[0] + "template/sfTemplate/img/";
//		
//		System.out.println(web_url.split("WEB-INF/")[0]);
//		System.out.println(web_url2.split("WEB-INF/")[0]);
//		System.out.println(Test.class.getResource("").getFile().toString());
//		System.out.println(Test.class.getClassLoader().getResource("").getPath());
//		System.out.println(System.getProperty("config"));
//		Test.class.getResource("/").getFile().toString();
//		Map<String,Object> params = new HashMap<String,Object>();
//		params.put("a", 1);
//		params.put("a", 2);
//		System.out.println(params.get("a"));
//		System.out.println("201610".substring(0, 6));;
//		try {
//			Integer i = Integer.valueOf("你是谁是啊");
//			System.out.println(i);
//		} catch (Exception e) {
//			System.out.println("不做事儿");
//			// TODO: handle exception
//		}
//		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
//		Calendar cal = Calendar.getInstance();
//		String afterDate = df.format(cal.getTime());
//		System.out.println(afterDate);
//		String hql = "select t.id,t.bb,t.cc from username ,wages where salfk";
//		System.out.println(hql.split("from")[1]);
////		String xml = Md5AndBase64.loadFile(TestHttpProvider.class.getClass().getResource("/").getFile().toString() + "SFTemplate.xml");
//		String dir = Test.class.getClass().getResource("/").getFile().toString();
//		System.out.println(dir);
//		Float a = 12f;
//		a = a < 18 ? 18 :(a>360?360:a);
//		System.out.println(a);
//		BigDecimal b = new BigDecimal("3");
//		System.out.println(b.multiply(new BigDecimal("4")).add(new BigDecimal("3").setScale(2, BigDecimal.ROUND_HALF_UP)));
//		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM");
//		Calendar cal = Calendar.getInstance();
//		String afterDate = df.format(cal.getTime());
//		System.out.println(afterDate);
//		System.out.println((new BigDecimal("25")).add(new BigDecimal("25")));
//		System.out.println(XMLEncoder.encode("<request><order>这是一个请求</order><request>"));
		//		System.out.println(Math.round(a*100));
//		System.out.println(Math.round(0.635*100));
//		BigDecimal bd = new BigDecimal(a.toString());
//		System.out.println(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
//		BigDecimal inte = new BigDecimal("100");
//		System.out.println(Math.round(bd.multiply(inte).floatValue()));
//		for (int i = 0; i < 1000; i++) {
//			System.out.println(Math.round(a*100)/100.0);
//		}
//		float tax= 1.0650f;
////		float tax= 1.01499f;
//		float result = Math.round(tax*100);
//		System.out.println(result/100);
//		System.out.println(1016%10);
//		float ee = 1.06f;
//		System.out.println((ee -(float)0.01));
//		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM");
//		Calendar cal = Calendar.getInstance();
//		String date = df.format(cal.getTime());
//		System.out.println(date);
//		cal.add(Calendar.MONTH, -1);
//		date = df.format(cal.getTime());
//		System.out.println(date);
//		cal.add(Calendar.MONTH, -1);
//		date = df.format(cal.getTime());
//		System.out.println(date);
		
		  //递归显示C盘下所有文件夹及其中文件
//		  File root = new File("d:/测试用");
//		  try {
//			showAllFiles(root);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		int[] array = new int[10];
//		for (int i = 0; i < array.length; i++) {
//			System.out.println(array[i]);
//		}
//		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM");
//		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
//		Date day = new Date();
//		Calendar cal = Calendar.getInstance();
//		System.out.println(cal.getTime());
//		System.out.println(cal.get(Calendar.DATE));
//		List<PWagesDate> ps = getWagesDate(2016,6);
//		for (PWagesDate pWagesDate : ps) {
//			System.out.println(pWagesDate);
//		}
//		System.out.println(cal.get(Calendar.MONTH)+1);
//		System.out.println(cal.get(Calendar.YEAR));
//		cal.add(Calendar.MONTH, -1);
//		System.out.println(df.format(new Date()));
//		System.out.println(df.format(cal.getTime()));
//		String dd = "2016.08.01";
//		System.out.println(dd.substring(0, 7));
//		Calendar cal = Calendar.getInstance();
//		System.out.println(cal.get(Calendar.DAY_OF_WEEK));
//		String qq = dd.replaceAll("\\.", "");
//		System.out.println("qq:"+qq);
//		long num = Long.valueOf(qq);
//		System.out.println(num);
//		Date d = new Date();
//		Calendar calendar = Calendar.getInstance();
//		System.out.println(calendar.get(Calendar.MONTH));
//		calendar.add(Calendar.MONTH, -1);
//		System.out.println(df.format(calendar.getTime()));
//		
//		String month = String.valueOf(1 + 100).substring(1);
//		System.out.println(month);
//		String year = String.valueOf(calendar.get(Calendar.YEAR));
//		System.out.println("year:" + year);
//		String month1 = new SimpleDateFormat("yyyy.MM").format(calendar.getTime());
//		System.out.println("month1:" + month1);
	/*	System.out.println(new SimpleDateFormat("yyyy.MM").format(new Date()));
		new SimpleDateFormat("yyyy.MM.dd").format(new Date());
		System.out.println("++++>>>>> later:");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM");//定义日期显示格式
        System.out.println(sdf.format(calendar.getTime()));//打印当前月份的下一个月份
        calendar.add(Calendar.MONTH, -1);//获取上个月月份
        System.out.println(sdf.format(calendar.getTime()));//输出结果*/
//		List<Date> dates = getDates(2016,8);    
//		List<PWagesDate> pwagesDates = getWagesDate(2016,8);
//        for(PWagesDate pwagesDate : pwagesDates){    
//            System.out.println("["+pwagesDate.getDate()+","+pwagesDate.getRuzhiDay()+","+pwagesDate.getLizhiDay()+"]");    
//        }    	
	}
	 private static List<PWagesDate> getWagesDate(int year,int month){
		 List<PWagesDate> pwagesDates = new ArrayList<PWagesDate>();
		 	int[] days = new int[32];
	        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
	        Calendar cal = Calendar.getInstance();    
	        cal.set(Calendar.YEAR, year);    
	        cal.set(Calendar.MONTH,  month - 1);    
	        cal.set(Calendar.DATE, 1);    
	        int i = 0;
	        while(cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) < month){
	        	int day = cal.get(Calendar.DAY_OF_WEEK);
	        	if(!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)){
	        		i++;
	        		}
	        	if(i==0){
	        		days[cal.get(Calendar.DATE)] = 21;
	        		}
	        	else{
	        		days[cal.get(Calendar.DATE)] = 22-i > 0?22-i:0;
	        		}
	        	cal.add(Calendar.DATE, 1);
	        	}
	        cal.add(Calendar.DATE, -1);
	        i = 0;
	        while(cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) == (month-1)){
	        	PWagesDate pwagesDate = new PWagesDate();
	        	int day = cal.get(Calendar.DAY_OF_WEEK);
	        	if(!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)){
	        		i++;
	        		}
	        	pwagesDate.setDate(df.format(cal.getTime()));
	        	if(i==0){
	        		pwagesDate.setLizhiDay(21);
	        		}else{
	        			pwagesDate.setLizhiDay(22-i > 0?22-i:0);
	        			}
	        	pwagesDate.setRuzhiDay(days[cal.get(Calendar.DATE)]);
	        	pwagesDates.add(pwagesDate);
	        	cal.add(Calendar.DATE, -1);
	        	}
	        return pwagesDates; 
	    }
	 final static void showAllFiles(File dir) throws Exception{
		  File[] fs = dir.listFiles();
		  for(int i=0; i<fs.length; i++){
		   System.out.println(fs[i].getAbsolutePath());
		   if(fs[i].isDirectory()){
		    try{
		     showAllFiles(fs[i]);
		    }catch(Exception e){}
		   }
		  }
		 }
	 public void tt(List<PRenshiEmployee> renshiEmployees, SXSSFWriter sxffWriter){
		 for (PRenshiEmployee pRenshiEmployee : renshiEmployees) {
				sxffWriter.createRow();
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getUsername());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getFirstLevel());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getSecondLevel());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getThirdLevel());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getFourthLevel());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getPosition());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getSex());
				//add
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getIdentityCard());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getBorthDay());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getNation());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getDegree());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getGraduatedSchool());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getMajor());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getHomeAddress());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getPhoneNumber());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getHomeNumber());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getHiredate());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getZhuanzhengTime());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getLeaveTime());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getEarlyEntryDate());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getHouseholdType());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getInsurance());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getInsuranceCompany());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getCompany());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getResume());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getPhoto());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getDegreeCertificate());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getIdentityCardCopy());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getFamilyRegister());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getLeavingCertificate());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getContract());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getManagementSystem());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getEntryForm());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getSignedTime());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getTerminationTime());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getHouseholdType());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getPostcode());
				
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getWorkPlace());
				//add
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getPastLeaveTime());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getCellCore());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getCellCoreEmail());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getGuidance());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getGuidanceEmail());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getSocialSecurityHospital());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getLevel());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getEmail());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getRuzhiReport());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getLizhiReport());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getZhuanzhengReport());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getBumentiaozhengReport());
				
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getMaritalStatus());
			}
	 }
}
