package com.lanou.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Test3 {

	public static void main(String[] args) {
		//无序不重复--->聚合对象
		Set<String> s = new HashSet<String>();
		s.add("zhong1");
		s.add("zhong2");
		s.add("zhong3");
	
		//常规for循环出来的数据   便利删除前的数据
		for (String string : s) {
			System.out.println(string);
		}
		
		//迭代器————迭代器模式
		Iterator<String> sIterator=s.iterator();
		//迭代器 出来的数据
		while(sIterator.hasNext()){
			if(sIterator.next().equals("zhong2")){
				sIterator.remove();
			}
//			System.out.println("迭代器"+sIterator.next());//游标
		}
		System.out.println(s);
			
		//有序可重复
		List<String> l = new ArrayList<String>();
		l.add("zhang4");
		l.add("zhang5");
		l.add("zhang6");
		for (int i = 0; i<l.size(); i++) {
			System.out.println(l.get(i));
		}
	
		Iterator<String> lIterator=l.iterator();
		while(lIterator.hasNext()){
			String a=lIterator.next();
			if("zhang5".equals(a)){
				lIterator.remove();
			}
		}
		while(lIterator.hasNext()){//空的
			System.out.println("迭代器"+lIterator.next());
		}
		System.out.println(l);
		for (String string : l) {
			System.out.println(string);
		}
	}
}
