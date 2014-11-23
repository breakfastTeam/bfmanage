package com.bean.breakfast.constants;

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
	
	/**************搜索*************/
	public static int DEFAULT_PAGE_SIZE = constants.getInteger("DEFAULT_PAGE_SIZE");
	
	/***********公共消息************/
	public static final String DESC = "desc";
	public static final String ASC = "asc";
	public static final String OPERATE_SUCCESS="success";
	public static final String OPERATE_ERROR = "error";
	public static final String SERVER_EXCEPTION = "exception";
	public static final String INFO = "info";
	public static final String VALID = "1";
	public static final String INVALID  = "0";
	public static final String FORBIDDEN  = "0";
	public static final String ACTIVE  = "1";
	public static final String ACCESS_CODE = "accessCode";
	public static final String ON = "on";
	public static final String YES = "1";
	public static final String NO = "0";
	public static final String NOT_PUBLISH="0";
	public static final String PUBLISH="1";
	
	/***********上传信息配置*************/
	public static final String MEDIA_URL = constants.getString("MEDIA_URL");
    public static final String COOK_BOOK_PIC_PATH = "upload\\\\cookbook";
    public static final int COOK_BOOK_BIG_PIC_WIDTH = 320;
    public static final int COOK_BOOK_BIG_PIC_HEIGHT = 200;
    public static final int COOK_BOOK_SMALL_PIC_WIDTH = 160;
    public static final int COOK_BOOK_SMALL_PIC_HEIGHT = 100;
    public static final double HALF = 0.5;
    
    
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
		return loadProperties("sysconfig.properties").getProperty(key); 
	}
	
	private Integer getInteger(String key){
		return Integer.parseInt(this.getString(key));
	}
}
