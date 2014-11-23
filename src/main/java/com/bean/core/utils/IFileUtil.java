package com.bean.core.utils;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class IFileUtil {
	public static boolean createUploadFile(String path, String fileName, MultipartFile file){
		boolean flag = true;
		File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {
        	flag = false;
            e.printStackTrace();  
        }
        return flag;
	}
	public static boolean deleteFile(String path){
		boolean flag = false;  
		File file = new File(path);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag; 
	}
}
