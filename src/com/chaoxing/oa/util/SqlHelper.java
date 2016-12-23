package com.chaoxing.oa.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 
 * @author dengxf
 *
 */
public class SqlHelper {
	/**
	 * 预设hql and语句
	 * @param obj 查询数据对象
	 * @param params 
	 * @param isLike "like" 还是 "="
	 * @param expect 数组，被排除的字段
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static String prepareAndSql(Object obj, Map<String, Object> params, boolean isLike, String... expect) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return prepareAndSql(obj,params,"t",isLike,true,expect);
	}
	public static String prepareOrSql(Object obj, Map<String, Object> params, boolean isLike, String... expect) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return prepareAndSql(obj,params,"t",isLike,false,expect);
	}
	
	public static String prepareAndSql(Object obj, Map<String, Object> params, String tableName, boolean isLike, boolean isAnd,String... expect){
		Class<?> clss = obj.getClass();
		Field[] fs = clss.getDeclaredFields();
		StringBuffer sql = new StringBuffer(" 1=1 ");
		String concat = isAnd?"and " : "or ";
		Method method = null;
		Object value = null;
		String type = null;
		String name = null;
		String upperName = null;
		int flag = 0;
		for (Field field : fs) {
			type = field.getType().getSimpleName();
			name = field.getName();
			upperName = name.substring(0, 1).toUpperCase() + name.substring(1);
			try {
				method = clss.getMethod("get" + upperName);
				value = method.invoke(obj);
			} catch (NoSuchMethodException e1) {
				System.out.println("没有[get" + upperName + "]方法,属性[" + name + "]不拼接");
				continue;
			} catch (IllegalAccessException e) {
				System.out.println(e.toString());
				continue;
//				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				System.out.println(e.toString());
				continue;
			} catch (InvocationTargetException e) {
				System.out.println(e.toString());
				continue;
			}
			if(value == null){
				continue;
			}
			if(expect.length>0){
				for (String e : expect) {
					if(e.equals(name)){
						flag += 1;
						break;
					}
				}
				if(flag>0){
					flag = 0;
					continue;
				}
			}
			if("String".equals(type)){
				if(!isLike){
					sql.append(concat + tableName + "." + name + "=:" + name +" ");
					params.put(name, String.valueOf(value));
				}else{
					sql.append(concat + tableName + "." + name + " like :" + name + " ");
					params.put(name, "%" + value + "%");
				}
			}else{
				setParams(type,name,sql,value,params,tableName);
			}
//			if(("float").equalsIgnoreCase(type)||("int").equals(type)||("Integer").equals(type)||("double").equalsIgnoreCase(type)||("long").equalsIgnoreCase(type)
//					||("boolean").equals(type)||("byte").equals(type)||("char").equals(type)||("String").equals(type)){
//				if(!isLike){
//					sql.append("and " + tableName + "." + name + "=:" + name +" ");
//					params.put(name, String.valueOf(value));
//				}else{
//					sql.append("and " + tableName + "." + name + " like :" + name + " ");
//					params.put(name, "%" + value + "%");
//				}
//			}else{
//				continue;
//			}
		}
		System.out.println("return hql:" + sql.toString());
		return sql.toString();
	}
	
	
//	public static String prepareOrSql(Object obj, Map<String, Object> params, boolean isLike, String... expect) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
//		return prepareOrSql(obj,params,"t",isLike,expect);
//	}
	
//	public static String prepareOrSql(Object obj, Map<String, Object> params, String tableName, boolean isLike, String... expect){
//		Class<?> clss = obj.getClass();
//		Field[] fs = clss.getDeclaredFields();
//		StringBuffer sql = new StringBuffer(" 1=1 ");
//		Method method = null;
//		Object value = null;
//		String type = null;
//		String name = null;
//		String upperName = null;
//		int flag = 0;
//		for (Field field : fs) {
//			type = field.getType().getSimpleName();
//			name = field.getName();
//			upperName = name.substring(0, 1).toUpperCase() + name.substring(1);
//			try {
//				method = clss.getMethod("get" + upperName);
//				value = method.invoke(obj);
//			} catch (NoSuchMethodException e1) {
//				System.out.println("没有[get" + upperName + "]方法,属性[" + name + "]不拼接");
//				continue;
//			} catch (IllegalAccessException e) {
//				System.out.println(e.toString());
//				continue;
////				e.printStackTrace();
//			} catch (IllegalArgumentException e) {
//				System.out.println(e.toString());
//				continue;
//			} catch (InvocationTargetException e) {
//				System.out.println(e.toString());
//				continue;
//			}
//			if(value == null){
//				continue;
//			}
//			if(expect.length>0){
//				for (String e : expect) {
//					if(e.equals(name)){
//						flag += 1;
//						break;
//					}
//				}
//				if(flag>0){
//					flag = 0;
//					continue;
//				}
//			}
//			if(("float").equalsIgnoreCase(type)||("int").equals(type)||("Integer").equals(type)||("double").equalsIgnoreCase(type)||("long").equalsIgnoreCase(type)
//					||("boolean").equals(type)||("byte").equals(type)||("char").equals(type)||("String").equals(type)){
//				if(!isLike){
//					sql.append("or " + tableName + "." + name + "=:" + name +" ");
//					params.put(name, "" + String.valueOf(value));
//				}else{
//					sql.append("or " + tableName + "." + name + " like :" + name + " ");
//					params.put(name, "%" + value + "%");
//				}
//			}else{
//				continue;
//			}
//		}
//		return sql.toString();
//	}
	
	public static void setParams(String type, String name,StringBuffer sql, Object value, Map<String,Object> params,String tableName){
		if("float".equalsIgnoreCase(type)){
			sql.append("and " + tableName + "." + name + "=:" + name +" ");
			params.put(name,(Float) value);
		}else if("int".equals(type) || "Integer".equals(type)){
			sql.append("and " + tableName + "." + name + "=:" + name +" ");
			params.put(name,(Integer) value);
		}else if("double".equalsIgnoreCase(type)){
			sql.append("and " + tableName + "." + name + "=:" + name +" ");
			params.put(name,(Double) value);
		}else if("long".equalsIgnoreCase(type)){
			sql.append("and " + tableName + "." + name + "=:" + name +" ");
			params.put(name,(Long) value);
		}else if("byte".equalsIgnoreCase(type)){
			sql.append("and " + tableName + "." + name + "=:" + name +" ");
			params.put(name,(Byte) value);
		}else if("short".equalsIgnoreCase(type)){
			sql.append("and " + tableName + "." + name + "=:" + name +" ");
			params.put(name,(Short) value);
		}else if("boolean".equalsIgnoreCase(type)){
			sql.append("and " + tableName + "." + name + "=:" + name +" ");
			params.put(name,(Boolean) value);
		}else{
			sql.append("and " + tableName + "." + name + "=:" + name +" ");
			params.put(name,(Object) value);
		}
	}
	
}
