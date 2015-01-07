package com.bean.breakfast.constants;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class IConstants {
	
	private static IConstants constants = getConstants();

	public static final String RETURN_CODE = "rtnCode";
	public static final String RETURN_MESSAGE = "rtnMsg";
	public static final String SUCCESS_CODE = "888888";//操作成功
	public static final String EXCEPTION_CODE = "666666";//服务器异常
	public static final String ERROR_CODE = "000000";//操作失败
	public static final String ERROR = "error";
	public static final String SUCCESS = "success";
	public static final String RANDOM_ENABLEATE_CODE_KEY = "RandomValidateCode";//验证码随机码的key
	
	/**************搜索*************/
	public static int DEFAULT_PAGE_SIZE = 18;
	
	/***********公共消息************/
	public static final String DESC = "desc";
	public static final String ASC = "asc";
	public static final String OPERATE_SUCCESS="success";
	public static final String OPERATE_ERROR = "error";
	public static final String SERVER_EXCEPTION = "exception";
	public static final String INFO = "info";



	public static final String USER_TYPE_ADMIN = "ADMIN";
	public static final String USER_TYPE_CUSTOMER = "CUSTOMER";
	public static final String USER_TYPE_COURIER = "COURIER";
	public static final String USER_TYPE_CUSTOMER_SERVICE = "CUSTOMER_SERVICE";


	public static final String ENABLE = "ENABLE";//可用
	public static final String DISCARD  = "DISCARD";//废弃
	public static final String PUTAWAY = "PUTAWAY";//上架
	public static final String SOLDOUT = "SOLDOUT";//下架

	public static final String DRAFT = "DRAFT";//草稿
	public static final String DISTRIBUTION = "DISTRIBUTION";//配送中
	public static final String CANCEL="CANCEL";//取消
	public static final String FINISH = "FINISH";//完成

	public static final String ACTIVE  = "1";
	public static final String ACCESS_CODE = "accessCode";
	public static final String ON = "on";
	public static final byte YES = 1;
	public static final byte NO = 0;
	public static final String NOT_PUBLISH="0";
	public static final String PUBLISH="1";

	public static final String WEIXIN_USER_NOT_EXIST = "User Not Exist";
	
	/***********上传信息配置*************/
    public static final String FOOD_PIC_PATH = "upload"+ File.separator+"food";
	public static final String SET_MEAL_PIC_PATH = "upload"+File.separator+"setmeal";
	public static final String INFO_PIC_PATH = "upload"+File.separator+"info";
	public static final String PHOTO_SHOW_PATH = "upload"+File.separator+"photoshow";



	public static final int FOOD_BIG_PIC_WIDTH = 320;
    public static final int FOOD_BIG_PIC_HEIGHT = 200;
    public static final int FOOD_SMALL_PIC_WIDTH = 160;
    public static final int FOOD_SMALL_PIC_HEIGHT = 100;
	public static final int INFO_SMALL_PIC_HEIGHT = 100;
	public static final int INFO_SMALL_PIC_WIDTH  = 160;
	public static final int PHOTO_SMALL_PIC_HEIGHT = 48;
	public static final int PHOTO_SMALL_PIC_WIDTH  = 85;

	public static final double HALF = 0.5;

	/************用户************/
	public static final String IS_LOGINED = "yes";//是否登录
	public static final String LOGINED = "logined";//已经登录
	public static final String USER_INFO = "userInfo";//用户信息
    
    /***********接口信息列表************/
	public static final String BEL001 = "BEL001";
	
	
	
	
	private static IConstants getConstants(){
	   if(constants==null){
		   constants=new IConstants();
	   }
	   return constants;
	  }
	
	/**
	 * 加载配置文件
	 * @param fileName
	 * @return
	 */
	private Properties loadProperties(String fileName){
		Properties props=null;
		try{
			props = new Properties();
			InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			props.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			new RuntimeException();
		} 
		return props; 
	}
	private String getString(String key){
		return loadProperties("config.properties").getProperty(key);
	}
	
	private Integer getInteger(String key){
		return Integer.parseInt(this.getString(key));
	}
}
