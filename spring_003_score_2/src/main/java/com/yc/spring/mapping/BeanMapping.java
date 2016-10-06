package com.yc.spring.mapping;

import java.util.List;

public class BeanMapping {
	private String id;
	private String clazz;
	private List<PropertyMapping> propertyMappings;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public List<PropertyMapping> getProperMappings() {
		return propertyMappings;
	}
	public void setProperMappings(List<PropertyMapping> propertyMappings) {
		this.propertyMappings = propertyMappings;
	}
	public BeanMapping(String id, String clazz,
			List<PropertyMapping> propertyMappings) {
		this.id = id;
		this.clazz = clazz;
		this.propertyMappings = propertyMappings;
	}
	public BeanMapping() {
	}
	@Override
	public String toString() {//重写Object的toString()方法
		return "\nBeanMapping [id=" + id + ", clazz=" + clazz
				+ ", \n\tpropertyMappings=" + propertyMappings + "]";
	}
	
}
