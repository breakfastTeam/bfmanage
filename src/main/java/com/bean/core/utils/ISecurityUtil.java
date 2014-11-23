package com.bean.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
/**
 * 名称:ISecurityUtil
 * 描述:定义系统中开发涉及到的安全方面常用的工具，涉及到加密、解密等安全类操作均在此工具类中定义
 *     如果工具无法满足业务需要请自行拓展此工具类并标明注释！
 * 类型:工具类
 * 修改时间： 
 * @since  2014-04-19
 * @author 李庆飞
 * @version 1.0
 * 修改记录
 * 1、添加3DES加密算法       2014-06-10 8:44
 */ 
public class ISecurityUtil {
	
	/**
	 * 获取字符串加密后的结果
	 * @return str String 返回加密后的结果
	 * @author Felix
	 * @since  2014-04-19 9:59
	 * 变更记录:
	 *
	 */
	public static String getMD5String(String info){  
        MessageDigest md=null;  
        try {  
            md=MessageDigest.getInstance("MD5");  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
        md.update(info.getBytes()); 
        byte[] bs=md.digest();     
        StringBuffer sb=new StringBuffer();  
        for(int i=0;i<bs.length;i++){  
            int v=bs[i]&0xff;  
            if(v<16){  
                sb.append(0);  
            }  
            sb.append(Integer.toHexString(v));  
        } 
        return sb.toString();
    } 
	
	private static final String Algorithm = "DESede"; //定义 加密算法,可用 DES,DESede,Blowfish
	
	/**
	 * 返回加密过后的字符
	 * @param byte keybyte 为加密密钥，长度为24字节 
	 * @param byte src 为被加密的数据缓冲区（源）
	 * @return res byte 返回加密后的结果
	 * @author Felix
	 * @since  2014-06-10 8:59
	 * 变更记录:
	 *
	 */
    public static byte[] encryptMode(byte[] keybyte, byte[] src) {
       try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

            //加密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    /**
	 * 返回解密过后的结果
	 * @param byte keybyte 为加密密钥，长度为24字节 
	 * @param byte src 为加密后的缓冲区
	 * @return res byte 返回解密后的结果
	 * @author Felix
	 * @since  2014-06-10 8:59
	 * 变更记录:
	 *
	 */
    public static byte[] decryptMode(byte[] keybyte, byte[] src) {      
    try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

            //解密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    /**
	 * 将byte数组转换成十六进制字符串
	 * @param byte keybyte 将要转换的byte数组 
	 * @return res string 转换成字符串结果
	 * @author Felix
	 * @since  2014-06-10 8:59
	 * 变更记录:
	 *
	 */
    public static String byte2hex(byte[] b) {
        String hs="";
        String stmp="";

        for (int n=0;n<b.length;n++) {
            stmp=(java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length()==1) hs=hs+"0"+stmp;
            else hs=hs+stmp;
            if (n<b.length-1)  hs=hs+":";
        }
        return hs.toUpperCase();
    }
    
    /**
	 * 生成32位的UUID字符串
	 * @return res string UUID字符串结果
	 * @author Felix
	 * @since  2014-06-10 8:59
	 * 变更记录:
	 *
	 */
    public static String getUUID() {
    	 UUID uuid = UUID.randomUUID();
    	 String s = uuid.toString();
    	 String res = s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    	 return res;
    }
//    public static void main(String[] args){
//        //添加新安全算法,如果用JCE就要把它添加进去
//        Security.addProvider(new com.sun.crypto.provider.SunJCE());
//
//        final byte[] keyBytes = {0x32, 0x21, 0x4F, 0x58, (byte)0x88, 0x10, 0x40, 0x38
//                               , 0x28, 0x25, 0x79, 0x51, (byte)0xCB, (byte)0xDD, 0x55, 0x66
//                               , 0x77, 0x29, 0x74, (byte)0x98, 0x30, 0x40, 0x36, (byte)0xE2};    //24字节的密钥
//        String szSrc = "我的名字叫李庆飞";
//        
//        System.out.println("加密前的字符串:" + szSrc);
//        
//        byte[] encoded = encryptMode(keyBytes, szSrc.getBytes());        
//        System.out.println("加密后的字符串:" + new String(encoded));
//        
//        byte[] srcBytes = decryptMode(keyBytes, encoded);
//        System.out.println("解密后的字符串:" + (new String(srcBytes)));
//    }
}
