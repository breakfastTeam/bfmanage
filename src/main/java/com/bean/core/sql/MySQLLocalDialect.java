package com.bel.core.sql;

import org.hibernate.dialect.MySQL5Dialect;

/**
 * 
 * 注册自己的方言。目的是为了解决中文排序时能按字母顺序来排序
 * 
 */
public class MySQLLocalDialect extends MySQL5Dialect {
	public MySQLLocalDialect()
	{
		super();  
//        registerFunction("convert", new SQLFunctionTemplate(StringType.INSTANCE, "convert(?1 using ?2)"));  
	}
	
}
