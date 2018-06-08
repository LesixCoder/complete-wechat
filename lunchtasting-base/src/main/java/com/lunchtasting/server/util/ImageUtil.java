package com.lunchtasting.server.util;

import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;

public class ImageUtil {
	
	private static Logger logger = Logger.getLogger(ImageUtil.class);
	
	/**
	 * 按宽的比例更改图片的大小
	 * 
	 * @param filePath
	 *            图片路径
	 * @param width
	 *            需要改变图片的宽度
	 * @return
	 * @throws Exception
	 */
	public static Icon getRatioWidth(String filePath, int width) throws Exception{	
		File f = new File(filePath); 		
		BufferedImage bi = ImageIO.read(f);		
		double wRatio = (new Integer(width)).doubleValue() / bi.getWidth(); //宽度的比例	
		int height = (int)(wRatio * bi.getHeight());  //图片转换后的高度	
		Image image = bi.getScaledInstance(width,height,Image.SCALE_SMOOTH); //设置图像的缩放大小
		AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(wRatio,wRatio),null);   //设置图像的缩放比例	
		image = op.filter(bi,null);
		
		int lastLength = filePath.lastIndexOf(".");   
		String subFilePath = filePath.substring(0,lastLength);  //得到图片输出路径
	    String fileType = filePath.substring(lastLength);  //图片类型
	    File zoomFile = new File(subFilePath +"_"+ width +"_" + height + fileType);
	    
	    Icon ret = null;
		try{
			ImageIO.write((BufferedImage)image, ".jpg", zoomFile); 
			ret = new ImageIcon(zoomFile.getPath()); 
	    }
	    catch (Exception e){
	    	e.printStackTrace();
	    } 
	    
	    return ret;
	}

	/**
	 * 按高的比例更改图片大小
	 * 
	 * @param filePath
	 *            图片路径
	 * @param height
	 *            需要改变图片的高度
	 * @return
	 * @throws Exception
	 */
	public static Icon getRatioHeight(String filePath, int height) throws Exception{
		File f = new File(filePath); 		
		BufferedImage bi = ImageIO.read(f);		
		double hRatio = (new Integer(height)).doubleValue() / bi.getHeight(); //高度的比例		
		int width = (int)(hRatio * bi.getWidth());  //图片转换后的高度		
		Image image = bi.getScaledInstance(width,height,Image.SCALE_SMOOTH); //设置图像的缩放大小		
		AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(hRatio,hRatio),null);   //设置图像的缩放比例		
		image = op.filter(bi,null);
		
		int lastLength = filePath.lastIndexOf(".");   
		String subFilePath = filePath.substring(0,lastLength);  //得到图片输出路径
	    String fileType = filePath.substring(lastLength);  //图片类型
	    File zoomFile = new File(subFilePath +"_" + width +"_" + height + fileType);
	    
	    Icon ret = null;
		try{
			ImageIO.write((BufferedImage)image, "jpg", zoomFile); 
			ret = new ImageIcon(zoomFile.getPath()); 
	    }
	    catch (Exception e){
	    	e.printStackTrace();
	    } 
	    
	    return ret;
	}

	
	/**
	 * 从网络上获取图片
	 * @param savePath 保存路径
	 * @param fileUrl  图片来源url
	 * @param width 图片保存时的宽度
	 * @param height 图片保存时的高度
	 * @param id 一般为userId .. questionId
	 * @throws Exception
	 */
	public static Icon saveImageFromUrl(String savePath,String fileUrl,int width,int height,String id) throws Exception {
		savePath = savePath.replace("\\", "/");
		fileUrl = fileUrl.replace("\\", "/");
		URL url = null;
		HttpURLConnection connection = null;
		DataInputStream in = null;
		try {

			url = new URL(fileUrl);
			connection = (HttpURLConnection) url.openConnection();
			in = new DataInputStream(connection.getInputStream());
			saveImage(in,savePath,width,height,id);
			/*
			out = new DataOutputStream(new FileOutputStream(file));
			byte[] buffer = new byte[4096];
			int count = 0;
			while ((count = in.read(buffer)) > 0) {
				out.write(buffer, 0, count);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("src pic url: " + fileUrl);
			logger.error(e.getMessage(), e);
		} finally {
			/*
			if(out != null)
				out.close();*/
			if(in != null)
				in.close();
			if(connection != null)
				connection.disconnect();
		}
		return null;
	}
	
	/**
	 * input上传
	 * 按输入的任意宽高改变图片的大小
	 * @param filePath
	 * @param width
	 * @param height
	 * @return
	 * @throws Exception
	 */
	
	public static Icon saveImage(InputStream input,String savePath, int width, int height,String id) throws Exception{		
		BufferedImage bi = ImageIO.read(input);	
		double wRatio = (new Integer(width)).doubleValue() / bi.getWidth(); //宽度的比例		
		double hRatio = (new Integer(height)).doubleValue() / bi.getHeight(); //高度的比例		
		Image image = bi.getScaledInstance(width,height,Image.SCALE_SMOOTH); //设置图像的缩放大小		
		AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(wRatio,hRatio),null);   //设置图像的缩放比例		
		image = op.filter(bi,null);
		
		//创建保存目录
		File file = new File(savePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		
		savePath += id+"_"+width+"_"+height+".jpg";
				
	    File zoomFile = new File(savePath);
		
	    Icon ret = null;
		try{
			ImageIO.write((BufferedImage)image, "jpg", zoomFile); 
			ret = new ImageIcon(zoomFile.getPath()); 
	    }
	    catch (Exception e){
	    	e.printStackTrace();
	    } 
	    
	    return ret;	
	}
	
	
	/**
	 * 路径上传
	 * 按输入的任意宽高改变图片的大小
	 * @param filePath
	 * @param width
	 * @param height
	 * @return
	 * @throws Exception
	 */
	public static Icon saveImage(String filePath, String savePath,int width, int height,String id) throws Exception{
		File f = new File(filePath); 		
		BufferedImage bi = ImageIO.read(f);	
		double wRatio = (new Integer(width)).doubleValue() / bi.getWidth(); //宽度的比例		
		double hRatio = (new Integer(height)).doubleValue() / bi.getHeight(); //高度的比例	
		Image image = bi.getScaledInstance(width,height,Image.SCALE_SMOOTH); //设置图像的缩放大小	
		AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(wRatio,hRatio),null);   //设置图像的缩放比例	
		image = op.filter(bi,null);
		//创建保存目录
		File file = new File(savePath);
		if (!file.exists()) {
			file.mkdirs();
		}
			
		savePath += id+"_"+width+"_"+height+".jpg";
		
				
	    File zoomFile = new File(savePath);
	    
	    Icon ret = null;
		try{
			ImageIO.write((BufferedImage)image, "jpg", zoomFile); 
			ret = new ImageIcon(zoomFile.getPath()); 
	    }
	    catch (Exception e){
	    	e.printStackTrace();
	    } 
	    
	    return ret;
	}
	
	/**
	 * 根据指定的id去切割路径 从尾部往头部获取（2位 2位 3位）
	 * 
	 * @param id
	 *            （1000000开始）
	 * @return 假如ID=1000000 返回：00\00\100\
	 */
	public static String getImgPath(String id) {
		StringBuffer path = new StringBuffer();
		if (id.length() < 7) {
			return id + "/";
		}
		path.append(id.substring(id.length() - 2, id.length()));
		path.append("/");
		path.append(id.substring(id.length() - 4, id.length() - 2));
		path.append("/");
		path.append(id.substring(0, 3));
		path.append("/");
		return path.toString();
	}

	public static byte[] ReadUrlToByte(String photoUrl) {
		byte[] photoByte = new byte[0];

		try {
			URL url = new URL(photoUrl);
			URLConnection uc = url.openConnection();
			InputStream in = uc.getInputStream();

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int len = 0;
			byte[] b = new byte[1024];

			while ((len = in.read(b, 0, b.length)) != -1) {
				baos.write(b, 0, len);
			}

			in.close();
			photoByte = baos.toByteArray();
			baos.close();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		return photoByte;
	}
	
	
	
//	/**
//	 * 从网络上获取图片
//	 * 
//	 * @param savePath
//	 *            保存路径
//	 * @param fileName
//	 *            保存名称
//	 * @param fileUrl
//	 *            图片来源url
//	 * @throws Exception
//	 */
//	public static void getImgFromUrl(String savePath, String fileName,
//			String fileUrl) throws Exception {
//		savePath = savePath.replace("\\", "/");
//		fileUrl = fileUrl.replace("\\", "/");
//		URL url = null;
//		HttpURLConnection connection = null;
//		DataInputStream in = null;
//		DataOutputStream out = null;
//		try {
//			File file = new File(savePath);
//			if (!file.exists()) {
//				file.mkdirs();
//			}
//			file = new File(savePath, fileName);
//
//			url = new URL(fileUrl);
//			connection = (HttpURLConnection) url.openConnection();
//			in = new DataInputStream(connection.getInputStream());
//			out = new DataOutputStream(new FileOutputStream(file));
//			byte[] buffer = new byte[4096];
//			int count = 0;
//			while ((count = in.read(buffer)) > 0) {
//				out.write(buffer, 0, count);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.info("src pic url: " + fileUrl);
//			logger.error(e.getMessage(), e);
//		} finally {
//			if(out != null)
//				out.close();
//			if(in != null)
//				in.close();
//			if(connection != null)
//				connection.disconnect();
//		}
//	}
	
	/**
	 * 对图片进行裁切
	 * @param inFilePath
	 * @param outFilePath
	 * @param width
	 * @param height
	 */
	public static int[] cutImage(String inFilePath, String outFilePath,int x, int y,int nx,int ny){
		int newWidth=0;
		int newHeight=0;
		if(outFilePath==null){
			logger.error("outFilePath is null!please check the outFilePath");
			return null;
		}
		if(inFilePath==null){
			logger.error("inFilePath is null!please check the inFilePath");
			return null;
		}
		if(x<0){
			logger.error("width is 0!please check the width");
			return null;
		}
		if(y<0){
			logger.error("height is 0!please check the height");
			return null;
		}
		if(nx<x){
			logger.error("srcx is smaller than x!please check the srcx");
		}
		if(ny<y){
			logger.error("srcy is smaller than y!please check the srcy");
		}
		IMOperation im=new IMOperation();
		im.addImage(inFilePath);
		im.crop(nx-x,ny-y,x,y);
		im.addImage(outFilePath);
		ConvertCmd convert=new ConvertCmd();
		// linux下不要设置此值，不然会报错
//		convert.setSearchPath("C:/Program Files/ImageMagick-6.7.3-Q16");
		try {
			convert.run(im);
			BufferedImage bi=ImageIO.read(new File(outFilePath));
			newWidth=bi.getWidth();
			newHeight=bi.getHeight();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (IM4JavaException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return new int[]{newWidth,newHeight};
	}
	
		
	public static void main(String[] args) {
		String filePath = "e:\\1.jpg"; // 图片的位置  
		int height=16;  
		int width=16;  
		Icon icon = null;  
		try {
			//icon = getFixedIcon(filePath,width,height);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		System.out.println(" ### " +icon); //生成新图片的位置；  
		 
	}
	
	
}
