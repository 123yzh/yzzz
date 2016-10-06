package com.yc.spring.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.yc.spring.day01.Person;
import com.yc.spring.day01.Phone;


public class SpringTest {
	public void testNomal(){
		/*获得person对象
		Phone phone = new Phone();
		phone.setBrand("金立");
		phone.setColor("金色");
		phone.setPrice(688);
		Person person = new Person();
		phone.setBrand("小红");
		phone.setColor("23");
		phone.getPrice(6800);
		*/
		//System.out.println(person);
	}
	
	@Test
	public void testIOC_DI(){
		//加载spring的配置文件(spring.xml)，根据配置文件中的bean创建实例对象,实体对象被spring容器（AppliacationContext）管理
		ApplicationContext cxt = new ClassPathXmlApplicationContext("spring.xml");//创建spring容器
		
		//Person person = (Person) cxt.getBean("person");//getBean()  对应<bean> 的id值-->控制反转(其原理是反射机制)
		Person person02 = (Person) cxt.getBean("person02");
		//Person person03 = (Person) cxt.getBean("person03");
		
		//System.out.println(person);
		System.out.println(person02);
		//System.out.println(person03);
	}
}
