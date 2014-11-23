package com.bean.core.utils;

import javax.persistence.Table;

/**
 * 功能描述：根据实体类得到对应的表名、主键名、字段名工具类
 * 
 */
@SuppressWarnings("unchecked")
public class HibernateConfigurationHelper {	
	public static String getTableName(Class clazz) {
		Table annotation = (Table)clazz.getAnnotation(Table.class);
		if(annotation != null){
			return annotation.name();
		}
		return null;
	}

}
