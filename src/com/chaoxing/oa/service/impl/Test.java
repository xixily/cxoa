package com.chaoxing.oa.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.util.SystemOutLogger;

import com.chaoxing.oa.entity.page.PWagesDate;

public class Test {

	public static void main(String[] args) {
//		float tax= 1.0650f;
////		float tax= 1.01499f;
//		float result = Math.round(tax*100);
//		System.out.println(result/100);
//		System.out.println(1016%10);
		float ee = 1.06f;
		System.out.println((ee -(float)0.01));
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
}
