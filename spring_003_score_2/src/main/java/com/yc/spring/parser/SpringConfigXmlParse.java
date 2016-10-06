package com.yc.spring.parser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.yc.spring.mapping.BeanMapping;
import com.yc.spring.mapping.PropertyMapping;

/**
 * 解析spring的配置文件
 * @author Administrator
 *才用了log4j框架进行日志记录
 */
public class SpringConfigXmlParse {
	private String config;
	private Map<String,BeanMapping> beanMappings;
	
	public SpringConfigXmlParse(String cinfig) {
		this.config = cinfig;
		beanMappings = new HashMap<String,BeanMapping>();
	}
	//注解
	@SuppressWarnings("unchecked")
	public void xmlParse(){
		//根据config来进行解析
		InputStream in=SpringConfigXmlParse.class.getClassLoader().getResourceAsStream(config);
		LogManager.getLogger().debug("加载配置"+config+"成功...");
		
		SAXReader reader = new SAXReader();
		LogManager.getLogger().debug("创建解析器SAXReader对象成功...");
		Document doc=null;
		try {
			doc = reader.read(in);
			LogManager.getLogger().debug("解析配置文件"+config+"的数据流生成Document对象成功...");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			LogManager.getLogger().error("解析配置文件"+config+"的数据流生成Document对象失败...",e);
		}
		
		//如何解析到spring.xml中的bean呢？如下
		List<Element> eles = doc.getRootElement().elements("bean");
		LogManager.getLogger().debug("取到所有的bean元素的集合成功...");
		for(Element ele : eles){
			//BeanMapping bm=new BeanMapping();
			String id=ele.attributeValue("id");//取到元素属性id属性的值
			String clazz=ele.attributeValue("class");//取到元素属性class属性的值
			LogManager.getLogger().debug(String.format("解析bean元素的属性id=%s,class=%s",id,clazz));
			//取到bean元素下的所有property子元素的集合
			List<Element> es =ele.elements("property");
			LogManager.getLogger().debug("取到bean元素下的所有property子元素的集合成功...");
			List<PropertyMapping> pms=new ArrayList<PropertyMapping>();//存放bean属性映射对象的集合
			for(Element e : es){
				String name=e.attributeValue("name");//取到元素属性name属性的值
				String value=e.attributeValue("value");//取到元素属性value属性的值
				String ref=null;
				if(value==null){
					ref=e.attributeValue("ref");//取到元素属性ref属性的值
					System.out.println("ref="+ref);
					LogManager.getLogger().debug(String.format("解析bean元素的属性name=%s,ref=%s",name,ref));
				}else{
					System.out.println("ref="+ref);
					LogManager.getLogger().debug(String.format("解析bean元素的属性name=%s,class=%s",name,value));
				}
				PropertyMapping pm = new PropertyMapping(name,value,ref);
				pms.add(pm);
			}
			BeanMapping bm=new BeanMapping(id,clazz,pms);
			beanMappings.put(id, bm);
		}
	}
	
	public Map<String, BeanMapping> getBeanMappings() {
		return beanMappings;
	}
}
