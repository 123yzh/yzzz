package com.yc.spring.day03;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//工厂模式
public class PersonFactory {
	private static Map<Integer,Person> persons;
	static{
		//persons = new HashMap<Integer,Person>();
		//persons.put(1001,new Person(1001,"yc",null));
	}
}
