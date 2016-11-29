package com.chaoxing.oa.util.log;

import java.util.List;
import java.util.Vector;

public class LoggerUtil implements Runnable{
	private static List<Object> firstQueue = new Vector<Object>();//70-100
	private static List<Object> secondQueue = new Vector<Object>();//90-250
	private static List<Object> thirdQueue = new Vector<Object>();//240-1000
	private static int flag;

	@Override
	public void run() {
		
	}
	
	public static void add(Object obj){
		
	}
	
	public static void remove(Object obj){
		
	}
	
	public static void removeAll(){
		
	}
	
	public static void addLogger(List<Object> lis){
		
	}
}
