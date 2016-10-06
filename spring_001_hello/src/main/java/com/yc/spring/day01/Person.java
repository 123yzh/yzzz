package com.yc.spring.day01;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Person {
	private String name;
	private int age;
	private Phone phone;
	private Properties strProp;//集合
	private List<String> strList;
	private Set<String> strSet;
	private Map<String,String> strMap;
	private String[] strArr;
	
	public String[] getStrArr() {
		return strArr;
	}

	public void setStrArr(String[] strArr) {
		this.strArr = strArr;
	}

	public Properties getProperties() {
		return strProp;
	}

	public void setProperties(Properties properties) {
		this.strProp = properties;
	}

	public Set<String> getStrSet() {
		return strSet;
	}

	public void setStrSet(Set<String> strSet) {
		this.strSet = strSet;
	}

	public Map<String, String> getStrMap() {
		return strMap;
	}

	public void setStrMap(Map<String, String> strMap) {
		this.strMap = strMap;
	}

	
	public Person(String name, int age, Phone phone) {
		super();
		this.name = name;
		this.age = age;
		this.phone = phone;
	}

	public Person() {
		super();
		System.out.println("person实例化成功");
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

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public Properties getStrProp() {
		return strProp;
	}

	public void setStrProp(Properties strProp) {
		this.strProp = strProp;
	}

	public List<String> getStrList() {
		return strList;
	}

	public void setStrList(List<String> strList) {
		this.strList = strList;
	}

	@Override
	public String toString() {
		
		return "Person [name=" + name + ", \nage=" + age + ",\n phone=" + phone
				+ ", \nstrProp=" + strProp + ", \nstrList=" + strList + ", \nstrSet="
				+ strSet + ", \nstrMap=" + strMap +  ", \nstrArr=" + strArr +"]";
	}

	
	
}
