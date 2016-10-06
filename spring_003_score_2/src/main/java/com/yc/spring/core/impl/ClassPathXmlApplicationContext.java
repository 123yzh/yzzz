package com.yc.spring.core.impl;
import java.lang.reflect.InvocationTargetException;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;

import com.yc.spring.core.ApplicationContext;
import com.yc.spring.mapping.BeanMapping;
import com.yc.spring.mapping.PropertyMapping;
import com.yc.spring.parser.SpringConfigXmlParse;
public class ClassPathXmlApplicationContext implements ApplicationContext{

	private Map<String,Object> beans;//容器，用来存储bean的实体对象的集合
	private SpringConfigXmlParse springConfigXmlParser;
	private Map<String,BeanMapping> tempBeanMappings;//临时容器，用来存储bean的实体对象的集合
	
	
	public ClassPathXmlApplicationContext(String config){
		beans = new HashMap<String,Object>();
		tempBeanMappings=new HashMap<String,BeanMapping>();
		createBans(config);
	}

	public void createBans(String config){
		springConfigXmlParser = new SpringConfigXmlParse(config);
		springConfigXmlParser.xmlParse();
		Map<String,BeanMapping> bms = springConfigXmlParser.getBeanMappings();
		for(Map.Entry<String,BeanMapping> bmEntry : bms.entrySet()){
			Object beanObject = beanMapping2Obj(bmEntry.getValue());
			LogManager.getLogger().debug("转换bean id = "+bmEntry.getKey()+"为Bean对象成功。。。");
			beans.put(bmEntry.getKey(), beanObject);
		}
		doRefInputValue();
	}

	private void doRefInputValue() {
		for(Map.Entry<String,BeanMapping> tbmEntry:tempBeanMappings.entrySet()){
			Object beanObject = beanMapping2Obj(tbmEntry.getValue());
			LogManager.getLogger().debug("转换bean id = "+tbmEntry.getKey()+"为Bean对象成功。。。");
			beans.put(tbmEntry.getKey(),beanObject);
		}
		tempBeanMappings = null;
	}

	public Object beanMapping2Obj(BeanMapping bm) {
		Object obj = null;
		LogManager.getLogger().debug("开始根据bean id = "+bm.getId()+"创建Bean对象...");
		try {
			//不明确是什么类型时--><?>泛型
			Class<?> clazz = Class.forName(bm.getClazz());
			obj = clazz.newInstance();
			LogManager.getLogger().debug("创建Bean对象成功...");
			boolean isInputTempBeans = true;
			
			for(PropertyMapping pm : bm.getProperMappings()){
				isInputTempBeans = isInputTempBeans && propertyMapping2ObjAttr(clazz,obj,pm);
				//propertyMapping2ObjAttr(clazz,obj,pm);//把pm转换为对象的属性
			}
			
			if(!isInputTempBeans){
				tempBeanMappings.put(bm.getId(), bm);
			}
			
			} catch (ClassNotFoundException|InstantiationException|IllegalAccessException e) {
			LogManager.getLogger().debug("根据bean id = "+bm.getId()+"创建Bean对象失败...",e.getMessage());
		}
		return obj;
	}

	private boolean propertyMapping2ObjAttr(Class<?> clazz, Object obj,PropertyMapping pm) {
		boolean isRefHaveValue = true;
		//取对应属性的get,set方法                toUpperCase-->转为大写的方法   
		String methodNameSuffix = Character.toUpperCase(pm.getName().charAt(0))+pm.getName().substring(1);
		//String sMethodName ="set"+methodNameSuffix;
		
		try {
			Class<?> typeClass=clazz.getDeclaredMethod("get"+methodNameSuffix).getReturnType();
			LogManager.getLogger().debug("根据property name =  "+pm.getName()+"取到属性的类型"+typeClass.getName());
			Method gMethod = clazz.getDeclaredMethod("set"+methodNameSuffix,typeClass);
			LogManager.getLogger().debug("根据property name =  "+pm.getName()+"取到属性的set方法的方法类的对象成功...");
			//如何判断一个数据的类型?-->反射处理 typeClass
			if(pm.getValue()!=null){
				if(typeClass.getName().endsWith("Integer")||typeClass.getName().endsWith("int")){
					gMethod.invoke(obj,Integer.parseInt(pm.getRef()));
				}else if(typeClass.getName().endsWith("Double")||typeClass.getName().endsWith("double")){
					gMethod.invoke(obj,Double.parseDouble(pm.getRef()));
				}
				gMethod.invoke(obj,pm.getValue());
			}else{
				if(beans.get(pm.getRef())!=null){
					gMethod.invoke(obj,beans.get(pm.getRef()));
				}else{
					isRefHaveValue= false;
				}
				
			}
			
			LogManager.getLogger().debug("通过方法类对象执行set方法成功...");
		} catch (Exception e) {
			LogManager.getLogger().debug("根据bean  = "+clazz.getName()+"没有属性"+pm.getName()+"的get/set方法",e.getMessage());
		}
		return isRefHaveValue;
	}

	@Override
	public Object getBean(String beanId) {
		return beans.get(beanId);
	}

	public Map<String, Object> getBeans() {
		return beans;
	}

}
