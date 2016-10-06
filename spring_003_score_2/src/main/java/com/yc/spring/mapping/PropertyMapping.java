package com.yc.spring.mapping;
//创建实体类
/**
 * bean的属性映射实体类
 * @author Administrator
 *
 */
public class PropertyMapping {
	private String name;
	private String value;
	private String ref;
	
	
	public PropertyMapping(String name, String value, String ref) {
		this.name = name;
		this.value = value;
		this.ref = ref;
	}
	
	public PropertyMapping() {
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	@Override
	public String toString() {
		return "\n\tPropertyMapping [name=" + name + ",value=" + value + ",ref="
				+ ref + "]";
	}
	
}
