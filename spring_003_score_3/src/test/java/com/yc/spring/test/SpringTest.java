package com.yc.spring.test;

import org.junit.Test;

import com.yc.spring.core.ApplicationContext;
import com.yc.spring.core.impl.ClassPathXmlApplicationContext;
import com.yc.spring.day03.Person;

public class SpringTest {

	@Test
	public void testScope(){
		//prototype(原型) :容器只任务创建bean对象,不管理
		//在单例模式下,不是由容器管理，而是由垃圾回收器管理
		//加载spring的配置文件(spring.xml),根据配置文件中的bean创建实例对象,实例对象被spring容器管理
		ApplicationContext cxt1 =new ClassPathXmlApplicationContext("spring.xml");
		Person person = (Person) cxt1.getBean("person");
		System.out.println(person);
	}
}
