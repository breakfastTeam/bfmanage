package com.bean.breakfast.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bean.breakfast.constants.IConstants;
/**
 * 名称:MessageUtil
 * 描述:处理返回信息
 * 类型:工具类
 * 最近修改时间:
 * @since  2014-04-21
 * @author Felix
 */
public class MsgUtil {
	
	private MsgMap<String, String> headMap = new MsgMap<String, String>();//返回报文头部信息
	private boolean isValid = false;//报文是否合法，默认是不合法

	/**
	 * 生成报文头部信息
	 * @author Felix
	 * @param code  String 返回报文代码
	 * @param msg String 返回提示信息       
	 * @return MsgUtil 
	 * @since 2014-04-24 09:09
	 * 变更记录：
	 */
	public MsgUtil generateHeadMsg(String code, String msg){
		headMap.put(IConstants.RETURN_CODE, code);
		headMap.put(IConstants.RETURN_MESSAGE, msg);
		this.setHeadMap(headMap);
		return this;
	}
	
	/**
	 * 生成整个报文信息并以JSON格式返回
	 * @author Felix
	 * @param bodyList List 将要生成的对象集合
	 * @return String 
	 * @since 2014-04-24 09:09
	 * 变更记录：
	 */
	@SuppressWarnings("unchecked")
	public String generateRtnMsg(List bodyList){
		String headMapString = JSONObject.toJSONString(this.getHeadMap());
		String bodyListString = JSONObject.toJSONString(bodyList);
		JSONObject jsonHead=JSONObject.parseObject(headMapString);
		JSONArray jsonBody=JSONArray.parseArray(bodyListString);
		String headStr="{\"head\":"+jsonHead.toString()+",";
		String bodyStr="\"body\":{\"results\":"+jsonBody.toString()+"}}";
		return headStr+bodyStr;
	}

	/**
	 * 生成整个报文信息并以JSON格式返回
	 * @author Felix
	 * @param bodyMap Map 将要生成的对象
	 * @return String 
	 * @since 2014-04-24 09:09
	 * 变更记录：
	 */
	public String generateRtnMsg(Map<String, Object> bodyMap){
		String headMapString = JSONObject.toJSONString(this.getHeadMap());
		String bodyMapString = JSONObject.toJSONString(bodyMap);
		JSONObject jsonHead=JSONObject.parseObject(headMapString);
		JSONObject jsonBody=JSONObject.parseObject(bodyMapString);
		String headStr="{\"head\":"+jsonHead.toString()+",";
		String bodyStr="\"body\":"+jsonBody.toString()+"}";
		return headStr+bodyStr;
	}
	/**
	 * 生成整个报文信息并以JSON格式返回，消息体为空
	 * @author Felix
	 * @return String 
	 * @since 2014-04-24 09:09
	 * 变更记录：
	 */
	public String generateRtnMsg(){
		String headMapString = JSONObject.toJSONString(this.getHeadMap());
		JSONObject jsonHead=JSONObject.parseObject(headMapString);
		String headStr="{\"head\":"+jsonHead.toString()+",";
		String bodyStr="\"body\":{}}";
		return headStr+bodyStr;
	}
	
	private void setHeadMap(MsgMap<String, String> headMap){
		this.headMap = headMap;
	}
	
	private MsgMap<String, String> getHeadMap(){
		return this.headMap;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	
}

/**
 * 名称:MsgMap
 * 描述:自定义Map以防止调用generateResData时候传值混乱的问题
 * 类型:工具类
 * 最近修改时间:
 * @since  2014-04-24 08:40
 * @author Felix
 */
@SuppressWarnings("serial")
class MsgMap<T, TT> extends HashMap<T, TT>{
	public MsgMap(){
		super();
	}
}