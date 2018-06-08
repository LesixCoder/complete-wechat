package com.lunchtasting.server.helper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.json.JSONException;

import com.lunchtasting.server.ocr.OcrImageHelper;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 图片批量处理方法 Created on 2017-7-12
 * @author xuqian
 * 
 */
	
public class ImageBatchHelper {
	
	/**
	 * 主文件名字
	 */
	private String GQ = "gaoqing";
	private String YS = "yasuo";
	
	/**
	 * 高清和压缩具体地址
	 */
	private String GQ_PATH = "D:\\matchImage\\test\\gaoqing";
	private String YS_PATH = "D:\\matchImage\\test\\yasuo";	
	
	/**
	 * ocr最长边长限制
	 */
	private int OCR_WIDTH = 4096;
	private int OCR_HEIGHT = 4096;

	/**
	 * 没有匹配上的图片，默认放到其他文件夹里
	 */
	private String QITA = "其他";
	private String CHICUN = "尺寸";
	
	public static void main(String[] args) throws IOException, JSONException {
		
		/**
		 * 图片批量处理流程(流程不能错误)
		 * 1. batchModifyName 批量修改高清图片名字
		 * 2. 批量压缩高清图 大小1M左右  最长边长4096以下
		 * 3. batchClassify 批量处理压缩图片分类 
		 * 4. batchCopy 批量高清图片根据压缩图片已分类的目录分类
		 */
		ImageBatchHelper i = new ImageBatchHelper();
		i.batchClassify();
	}
	
	/**
	 * 批量修改高清图图片名字
	 * 1.批量修改高清图名字
	 * 2.删除文件格式不对文件
	 */
	public void batchModifyName(){
		File file = new File(GQ_PATH);
		if (file.exists()) {
			File[] files = file.listFiles();
			for (File oldFile : files) {
				if(oldFile.isFile()){
					
					String oldName = oldFile.getName();
					/**
					 * 格式错误则删除图片
					 */
					if(checkFileFormat(oldName) == false){
						oldFile.delete();
					}
					
					System.out.println("修改前文件名称是：" + oldName);
					String newName = getFileName();
					System.out.println("修改后文件名称是：" + newName);
					File newFile = new File(oldFile.getParent() + File.separator + newName);
					if (oldFile.renameTo(newFile)) {
						System.out.println("修改成功!");
					}else{
						System.out.println("修改失败 " +oldFile.getAbsolutePath());
					}
				}
			}
		}
		
	}
	
	/**
	 * 批量图片分类 
	 * 1.压缩制定尺寸图按号码牌分类
	 * 2.原高清图按号码牌分类
	 * @throws IOException
	 * @throws JSONException 
	 */
	public void batchClassify() throws IOException, JSONException {
		createOtherDirectory();
		File file = new File(YS_PATH);
		if (file.exists()) {
			File[] files = file.listFiles();
			int number = 1;
			OcrImageHelper ocr = new OcrImageHelper();
			for (File fileImage : files) {
				
				System.out.println(fileImage.getName());
				
				if(fileImage.isFile()){
					
					if(checkFileFormat(fileImage.getName())){
						
						System.out.println(fileImage.getName() + "+"
								+ fileImage.getAbsolutePath());

						/**
						 * 获取图片高宽
						 */
						BufferedImage bufferedImage = ImageIO
								.read(fileImage);
						int width = bufferedImage.getWidth();
						int height = bufferedImage.getHeight();
						
						/**
						 * OCR识别要求图片不能超过最大尺寸
						 */
						if(width <= OCR_WIDTH && height <= OCR_HEIGHT){
							number++;
							
							/**
							 * 调用OCR识别图片中的号码牌列表
							 */
							List<String> list = ocr.getWordsList(fileImage.getAbsolutePath(), number);
							System.out.println("word list" +list);
							if(ValidatorHelper.isNotEmpty(list)){
								for(String word : list){
									System.out.println("word = " + word);
									File wordFile = new File(YS_PATH + File.separator + word);
									if(!wordFile.exists()){
										wordFile.mkdir();
									}
									
									/**
									 * 图片移动到对应号码牌下面
									 */
									String newPath = YS_PATH + File.separator + word + File.separator + fileImage.getName();
									copyFile(fileImage.getAbsolutePath(), newPath);
								}
								
								/**
								 * 删除原图片
								 */
								fileImage.delete();
							}else{
								
								/**
								 * 没有匹配上的图片，默认放到其他文件夹
								 */
								File noMatchFile = new File(YS_PATH + File.separator + QITA + File.separator + fileImage.getName());
								fileImage.renameTo(noMatchFile);
							}
						}else{
							
							/**
							 * 尺寸不对，默认放到尺寸文件夹
							 */
							File noMatchFile = new File(YS_PATH + File.separator + CHICUN+ File.separator + fileImage.getName());
							fileImage.renameTo(noMatchFile);
						}
						
					}
						
				}
				
			}
		}
	}
	
	/**
	 * 原高清图按压缩图分类批量复制
	 */
	public void batchCopy(){
		File gqFile = new File(GQ_PATH);
		File ysFile = new File(YS_PATH);
		
		if(gqFile.exists() && ysFile.exists()){
			
			File[] gqFiles = gqFile.listFiles();
			File[] ysFiles = ysFile.listFiles();
			
			for (File gqImage : gqFiles) {
				
				if(gqImage.isFile()){
					
					/**
					 * 格式错误则删除图片
					 */
					if(checkFileFormat(gqImage.getName()) == false){
						gqImage.delete();
					}
					
					/**
					 * 循环压缩图文件夹
					 */
					for (File ys : ysFiles) {
						if(ys.isDirectory()){
							
							/**
							 * 高清下面创建压缩对应文件夹
							 */
							String gqDirectoryPath = ys.getAbsolutePath();
							gqDirectoryPath = gqDirectoryPath.replaceAll(YS, GQ);
							File gqDirectory = new File(gqDirectoryPath);
							if(!gqDirectory.exists()){
								gqDirectory.mkdirs();
							}
							
							/**
							 * 循环压缩文件夹下的文件
							 */
							File[] ysFilesImage = ys.listFiles();		
							if(ysFilesImage != null){
								for (File ysImage : ysFilesImage) {
									
									/**
									 * 压缩图片名字和高清名字一样，则移动高清图片到相应文件夹
									 */
									if(ysImage.getName().equals(gqImage.getName())){
										String movePath = ysImage.getAbsolutePath();
										movePath = movePath.replaceAll(YS, GQ);
										copyFile(gqImage.getAbsolutePath(), movePath);
									}
								}
							}
						}
					}
					
					/**
					 * 循环处理完 删除移动前的图片
					 */
					gqImage.delete();
					
				}
				
				
			}
			
		}
		

	}
	
	/**
	 * 判断错误格式文件
	 * @param name
	 * @return
	 */
	private boolean checkFileFormat(String name){
		if(ValidatorHelper.isEmpty(name)){
			return false;
		}
		
		/**
		 * 剔除苹果格式文件
		 */
		if (name.indexOf("._") != -1
				|| name.indexOf(".DS") != -1) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 获得图片名字
	 * @return
	 */
	private String getFileName(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "" + Utils.getRandomNumber(3);
		return newFileName+".jpg";
	}
	
	/**
	 * 创建其他帮助文件夹
	 * 1.其他文件夹 （没有匹配上的图片）
	 * 2.尺寸文件夹 （尺寸不对的图片）
	 * @return
	 */
	private void createOtherDirectory(){
		
		File qtFile = new File(YS_PATH + File.separator + QITA);
		qtFile.mkdirs();
		
		File ccFile = new File(YS_PATH + File.separator + CHICUN);
		ccFile.mkdirs();
	}
	
	private void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { 
				InputStream inStream = new FileInputStream(oldPath); 
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1024];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; 
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();
		}
	}
}
