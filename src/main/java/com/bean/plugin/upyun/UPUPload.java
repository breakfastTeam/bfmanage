package com.bean.plugin.upyun;

import com.bean.plugin.upyun.UPYun.PARAMS;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UPUPload {

    // 运行前先设置好以下三个参数
    private static final String BUCKET_NAME = "bfmanage";
    private static final String OPERATOR_NAME = "shuijingqiu";
    private static final String OPERATOR_PWD = "abc123456";
    private static final String URL = "http://" + BUCKET_NAME  + ".b0.upaiyun.com";
    private static final String DIR_ROOT = "/";
    private UPYun upyun = null;
    public UPUPload(){
        upyun = new UPYun(BUCKET_NAME, OPERATOR_NAME, OPERATOR_PWD);
    }

    public UPYun getUpyun() {
        return upyun;
    }

    public void setUpyun(UPYun upyun) {
        this.upyun = upyun;
    }

    public boolean deleteImage(String picName){
        String filePath = DIR_ROOT + picName;
        boolean result = upyun.deleteFile(filePath);
        return result;
    }
    /**

     * 上传文件
     *
     * @throws java.io.IOException
     */
    public String uploadImage(byte[] bytes, String picName) throws IOException {
        String filePath = DIR_ROOT + picName;
        upyun.setFileSecret("bac");
        boolean result = upyun.writeFile(filePath, bytes);
        if (result) {
            return URL + filePath + "!bac";
        }else {
            return null;
        }
    }
    /**
     * 上传文件
     *
     * @throws java.io.IOException
     */
    public String uploadImage(File file, String picName) throws IOException {
        String filePath = DIR_ROOT + picName;
        FileInputStream fs = new FileInputStream(file);
        upyun.setContentMD5(UPYun.md5(file));
        upyun.setFileSecret("bac");
        boolean result = upyun.writeFile(filePath, fs, true);
        if (result) {
            return URL + filePath + "!bac";
        }else {
            return null;
        }
    }
    /**
     * 上传文件
     *
     * @throws java.io.IOException
     */
    public String uploadImage(String localFilePath, String picName) throws IOException {
        String filePath = DIR_ROOT + picName;
        File file = new File(localFilePath);
        FileInputStream fs = new FileInputStream(file);
        upyun.setContentMD5(UPYun.md5(file));
        upyun.setFileSecret("bac");
        boolean result = upyun.writeFile(filePath, file, true);
        if (result) {
            return URL + filePath + "!bac";
        }else {
            return null;
        }
    }
    /**
     * 图片做缩略图
     * <p/>
     * 注意：若使用了缩略图功能，则会丢弃原图
     *
     * @throws java.io.IOException
     */
    public String scaleImage(byte[] bytes, String picName, String size) throws IOException {
        String filePath = DIR_ROOT + picName;
//        File file = new File(localFile);
        Map<String, String> params = new HashMap<String, String>();
        params.put(PARAMS.KEY_X_GMKERL_TYPE.getValue(),
                PARAMS.VALUE_FIX_BOTH.getValue());
        params.put(PARAMS.KEY_X_GMKERL_VALUE.getValue(), size);
        params.put(PARAMS.KEY_X_GMKERL_QUALITY.getValue(), "95");
        params.put(PARAMS.KEY_X_GMKERL_UNSHARP.getValue(), "true");
        params.put(PARAMS.KEY_X_GMKERL_THUMBNAIL.getValue(), "small");
        boolean result = upyun.writeFile(filePath, bytes, true, params);
        if (result) {
            return URL + filePath;
        }else{
            return null;
        }
    }
    /**
     * 图片做缩略图
     * <p/>
     * 注意：若使用了缩略图功能，则会丢弃原图
     *
     * @throws java.io.IOException
     */
    public String scaleImage(File file, String picName, String size) throws IOException {
        String filePath = DIR_ROOT + picName;
//        File file = new File(localFile);
        Map<String, String> params = new HashMap<String, String>();
        params.put(PARAMS.KEY_X_GMKERL_TYPE.getValue(),
                PARAMS.VALUE_FIX_BOTH.getValue());
        params.put(PARAMS.KEY_X_GMKERL_VALUE.getValue(), size);
        params.put(PARAMS.KEY_X_GMKERL_QUALITY.getValue(), "95");
        params.put(PARAMS.KEY_X_GMKERL_UNSHARP.getValue(), "true");
        params.put(PARAMS.KEY_X_GMKERL_THUMBNAIL.getValue(), "small");
        boolean result = upyun.writeFile(filePath, file, true, params);
        if (result) {
            return URL + filePath;
        }else{
            return null;
        }
    }
}
