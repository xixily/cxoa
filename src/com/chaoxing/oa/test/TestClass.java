package com.chaoxing.oa.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.chaoxing.oa.entity.page.Json;

public class TestClass {
	public void getFileds(Object source, Object target, Class<?> clazz){
		System.out.println(source.getClass());
		System.out.println(target.getClass());
		System.out.println(clazz);
	}
	
	class Student{
		private String name;
		private int age;
		private String sex;
		public String getName() {
			return name;
		}
		public int getAge() {
			return age;
		}
		public String getSex() {
			return sex;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		
	}
	
	public static void main(String[] args) {
		TestClass tc = new TestClass();
		TestClass.Student tcs = tc.new Student();
//		Class clazz = tcs.getClass();
		Class clazz = Json.class;
		Json res = new Json();
		Field[] fs = clazz.getDeclaredFields();
//		clazz.
		Field fd = null;
		try {
			Object obj = clazz.newInstance();
//			fd = res.getClass().getField("msg");
			fd = clazz.getDeclaredField("msg");
			fd.setAccessible(true);
			System.out.println(fd.getType().getSimpleName().toString());
//			fd.set(obj,)
//			fd.set
			Method method = clazz.getMethod("setMsg",String.class);
			Class[] params = method.getParameterTypes();
			for (Class class1 : params) {
				System.out.println(class1.getName());
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
////		TestClass tt = new TestClass();
////		String st = "12345";
//		Json re = new Json();
////		int it = 111;
////		tt.getFileds(re, it, re.getClass());
//		Class<?> clazz = re.getClass();
////		System.out.println(clazz.getName());
//		try {
//			Class<?> ot = Class.forName(clazz.getName());
//			clazz.newInstance();
//			
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
 catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IllegalAccessException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
}
