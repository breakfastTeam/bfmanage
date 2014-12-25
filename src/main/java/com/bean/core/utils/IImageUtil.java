package com.bean.core.utils;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class IImageUtil {
	/**
	 * w：原图片宽度
	 * h：原图片高度
	 * w1:目标图片宽度
	 * h1：目标图片高度
	 * 如果scale为零则只需要等比例缩小即可，如果scale
	 * */
	public static double getScale(int w, int h, int w1, int h1){
		double scale = 0;
		BigDecimal bw = new BigDecimal(Double.toString(w));
		BigDecimal bh = new BigDecimal(Double.toString(h));
		BigDecimal bw1 = new BigDecimal(Double.toString(w1));
		BigDecimal bh1 = new BigDecimal(Double.toString(h1));
		int rs = bw.divide(bh,10, BigDecimal.ROUND_HALF_EVEN).compareTo(bw1.divide(bh1,10, BigDecimal.ROUND_HALF_EVEN));
		if(rs==0){
			scale = bw1.divide(bw,10, BigDecimal.ROUND_HALF_EVEN).doubleValue();
		}else if(rs<0){
			scale = bw1.divide(bw,10, BigDecimal.ROUND_HALF_EVEN).doubleValue();
		}else if(rs>0){
			scale = bh1.divide(bh,10, BigDecimal.ROUND_HALF_EVEN).doubleValue();
		}
		return scale;
	}
	/**
	 * size数组0是宽、1是高
	 * **/
	public static int[] getSize(String filePath){
		int size[] = new int[2];
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File(filePath));
			int width = bufferedImage.getWidth();   
			int height = bufferedImage.getHeight();
			size[0] = width;
			size[1] = height;
		} catch (IOException e) {
			e.printStackTrace();
		}   
		return size;
	}

	public static void scaleImage(String imgSrc, String imgDest, int distImgWidth, int distImgHeight){
		try {
			File srcImgFile = new File(imgSrc);
			File destImgFile = new File(imgDest);

			BufferedImage srcImage = ImageIO.read(new FileInputStream(imgSrc));

			BufferedImage target=new BufferedImage(distImgWidth,distImgHeight,srcImage.getType());
			Graphics2D g=target.createGraphics();  
			g.fillRect(0,0,distImgWidth,distImgHeight);
			g.drawImage(srcImage, 0, 0, distImgWidth, distImgHeight, null);
			g.dispose();
			String srcImgFileName = srcImgFile.getName();
			String suffix = srcImgFileName.substring(srcImgFileName.lastIndexOf(".") + 1);

//			String imgDests[] = imgDest.split(File.separator);
//			String imgDestTemp = "";
//			for(int i = 0; i<imgDests.length-1; i++){
//				imgDestTemp = imgDestTemp +imgDests[i]+File.separator;
//			}
			File distImgFilePath = new File(destImgFile.getAbsolutePath());
			if(!distImgFilePath.exists()){
				distImgFilePath.mkdirs();
			}
			File distImgFile = new File(distImgFilePath, srcImgFileName);
			if(!distImgFile.exists()){
				distImgFile.createNewFile();
			}
			ImageIO.write(target, suffix, distImgFile); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	/* 
	 * 图片裁剪通用接口 
	 */  

	public static void cutImage(String imgSrc,String imgDest,int x,int y,int w,int h){
		try{
			File srcImgFile = new File(imgSrc);
			Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");   
			ImageReader reader = (ImageReader)iterator.next();   
			InputStream in=new FileInputStream(imgSrc);  
			ImageInputStream iis = ImageIO.createImageInputStream(in);   
			reader.setInput(iis, true);   
			ImageReadParam param = reader.getDefaultReadParam();   
			Rectangle rect = new Rectangle(x, y, w, h);    
			param.setSourceRegion(rect);  
			
			String srcImgFileName = srcImgFile.getName();
			String suffix = srcImgFileName.substring(srcImgFileName.lastIndexOf(".")+1);
			File distImgFilePath = new File(imgDest);
			if(!distImgFilePath.exists()){
				distImgFilePath.mkdirs();
			}
			File distImgFile = new File(distImgFilePath, srcImgFileName);
			if(!distImgFile.exists()){
				distImgFile.createNewFile();
			}
			BufferedImage bi = reader.read(0,param);
			ImageIO.write(bi, suffix, distImgFile); 
		}catch(Exception e){

		}
	}
}
