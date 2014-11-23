package com.bean.core.utils;

import org.apache.commons.lang.StringUtils;
/**
 * 名称:IStringUtils
 * 描述:定义开发过程中操作字符串的工具，因为工具类好多工具包中都包含字符串工具类，为了方便后面介入的伙伴能够统一使用遂在此定义。
 *     凡是参与开发的伙伴均采用此字符串工具类，如果工具无法满足业务需要请自行拓展此工具类并标明注释！
 * 类型:工具类 
 * @since  2014-04-19
 * @author 李庆飞
 */ 
public class IStringUtil extends StringUtils{
	public static boolean contains(String searStr, String[] strs){
		for(String str : strs){
			if(StringUtils.equals(searStr, str)){
				return true;
			}else{
				continue;
			}
		}
		return false;
	}
}
