package com.chaoxing.oa.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Field implements Comparable<Field> {

    private String name;
    private int age;

    public Field() {
    }
    public Field(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Field o) {
        // 先按age排序
        if (this.age > o.getAge()) {
            return (this.age - o.getAge());
        }
        if (this.age < o.getAge()) {
            return (this.age - o.getAge());
        }
        // 按name排序
        if (this.name.compareTo(o.getName()) > 0) {
            return 1;
        }
        if (this.name.compareTo(o.getName()) < 0) {
            return -1;
        }
        return 0;
    }
    public static void main(String[] args) {
    	 Field f1 = new Field("tony", 11);
         Field f2 = new Field("jack", 11);
         Field f3 = new Field("tom", 11);
         Field f4 = new Field("jason", 44);

         List<Field> list = new ArrayList<Field>();
         list.add(f1);
         list.add(f3);
         list.add(f4);
         list.add(f2);
         Collections.sort(list);

         for (Field o : list) {
             System.out.println(o.getAge() + "-->" + o.getName());
         }
     }
}