package com.yc.spring.day03;


public class Person{
	private String name;
	private int age;
	private Phone phone;
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

	public Person() {
		super();
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", phone=" + phone
				+ "]";
	}
	
	
	public Person(String name, int age, Phone phone) {
		this.name = name;
		this.age = age;
		this.phone = phone;
	}
	public void init(){
		System.out.println("我是一个Person的初始化方法...");
	}
	/**
	 * 该销毁方法在原型模式下,由于容器(对象)被销毁,将不会被调用;而单例模式下，则会调用该方法，因为它
	 */
	public void destoty(){
		System.out.println("我是一个Person的销毁方法...");
	}
	
	//生命周期
	//容器启动时实例化bean,-->postProcessBeforeInitialization()出始化前处理
	//-->初始化处理-->postProcessAfterInitialization()初始化处理-->关闭
	
	
	
}
