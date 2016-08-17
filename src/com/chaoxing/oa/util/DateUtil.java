package com.chaoxing.oa.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.chaoxing.oa.entity.page.PWagesDate;

/**
 * 日期工具类
 * 
 * @author dengxf
 * 
 */
public class DateUtil {

	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @return 日期字符串
	 */
	public static String format(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @param pattern
	 *            字符串格式
	 * @return 日期字符串
	 */
	public static String format(Date date, String pattern) {
		if (date == null) {
			return "null";
		}
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		return new java.text.SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 将字符串转换为Date类型
	 * 
	 * @param date
	 *            字符串类型
	 * @return 日期类型
	 */
	public static Date format(String date) {
		return format(date, null);
	}

	/**
	 * 将字符串转换为Date类型
	 * 
	 * @param date
	 *            字符串类型
	 * @param pattern
	 *            格式
	 * @return 日期类型
	 */
	public static Date format(String date, String pattern) {
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		if (date == null || date.equals("") || date.equals("null")) {
			return new Date();
		}
		Date d = null;
		try {
			d = new java.text.SimpleDateFormat(pattern).parse(date);
		} catch (ParseException pe) {
		}
		return d;
	}
	/**
	 * 获取指定月份工作日
	 * @param year 年份
	 * @param month 月份
	 * @return list<Date> 工作日数组
	 */
	public static List<Date> getDates(int year,int month){    
	        List<Date> dates = new ArrayList<Date>();    
	            
	        Calendar cal = Calendar.getInstance();    
	        cal.set(Calendar.YEAR, year);    
	        cal.set(Calendar.MONTH,  month - 1);    
	        cal.set(Calendar.DATE, 1);    
	        while(cal.get(Calendar.YEAR) == year &&     
	                cal.get(Calendar.MONTH) < month){    
	            int day = cal.get(Calendar.DAY_OF_WEEK);    
	                
	            if(!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)){    
	                dates.add((Date)cal.getTime().clone());    
	            }    
	            cal.add(Calendar.DATE, 1);    
	        }    
	        return dates;    
	    
	    } 
	/**
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static List<PWagesDate> getWagesDate(int year,int month){
	 	List<PWagesDate> pwagesDates = new ArrayList<PWagesDate>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        Calendar cal = Calendar.getInstance();    
        cal.set(Calendar.YEAR, year);    
        cal.set(Calendar.MONTH,  month - 1);    
        cal.set(Calendar.DATE, 1);    
        int i = 0;
        while(cal.get(Calendar.YEAR) == year &&     
                cal.get(Calendar.MONTH) < month){    
            int day = cal.get(Calendar.DAY_OF_WEEK);    
            PWagesDate pwagesDate = new PWagesDate();    
            if(!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)){
            	i++;
            }
            pwagesDate.setDate(df.format(cal.getTime()));
            if(i==0){
            	pwagesDate.setRuzhiDay(21);
            	pwagesDate.setLizhiDay(0);
            }else{
            	pwagesDate.setRuzhiDay(22-i > 0?22-i:1);
            	pwagesDate.setLizhiDay(i-1>21?21:i-1);
            }
        	pwagesDates.add(pwagesDate);
            cal.add(Calendar.DATE, 1);    
        }    
        return pwagesDates;    
    } 

}
