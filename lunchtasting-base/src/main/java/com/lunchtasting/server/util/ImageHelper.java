//package com.lunchtasting.server.util;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.Rectangle;
//import java.awt.geom.AffineTransform;
//import java.awt.image.AffineTransformOp;
//import java.awt.image.BufferedImage;
//import java.awt.image.ConvolveOp;
//import java.awt.image.Kernel;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Properties;
//
//import javax.imageio.ImageIO;
//import javax.swing.Icon;
//import javax.swing.ImageIcon;
//
//import org.apache.log4j.Logger;
//
//import com.sun.image.codec.jpeg.*;
//
//public class ImageHelper {
//
//
//	private String uploadName;
//
//	public String getUploadName() {
//		return uploadName;
//	}
//
//	public void setUploadName(String uploadName) {
//		this.uploadName = uploadName;
//	}
//
//
//
//
//	/**
//	 * 绘制缩放图
//	 * 
//	 * @param img
//	 *            原图
//	 * @param width
//	 *            目标图宽
//	 * @param height
//	 *            目标图高
//	 * @return
//	 */
//	private static BufferedImage makeThumbnail(Image img, int width, int height) {
//		BufferedImage tag = new BufferedImage(width, height,
//				BufferedImage.TYPE_INT_RGB);
//		Graphics g = tag.getGraphics();
//		g.drawImage(img.getScaledInstance(width, height, Image.SCALE_SMOOTH),
//				0, 0, null);
//		g.dispose();
//		return tag;
//	}
//
//	/**
//	 * 裁剪图片
//	 * 
//	 * @param image
//	 *            原图
//	 * @param subImageBounds
//	 *            裁剪矩形框
//	 * @param subImageFile
//	 *            保存路径
//	 * @throws IOException
//	 */
//	private static void saveSubImage(BufferedImage image,
//			Rectangle subImageBounds, File subImageFile) throws IOException {
//		String fileName = subImageFile.getName();
//		String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);
//		BufferedImage subImage = new BufferedImage(subImageBounds.width,
//				subImageBounds.height, BufferedImage.TYPE_INT_RGB);
//		Graphics g = subImage.getGraphics();
//		if (subImageBounds.width > image.getWidth()
//				|| subImageBounds.height > image.getHeight()) {
//			int left = subImageBounds.x;
//			int top = subImageBounds.y;
//			if (image.getWidth() < subImageBounds.width)
//				left = (int) ((subImageBounds.width - image.getWidth()) / 2);
//			if (image.getHeight() < subImageBounds.height)
//				top = (int) ((subImageBounds.height - image.getHeight()) / 2);
//			g.setColor(Color.white);
//			g.fillRect(0, 0, subImageBounds.width, subImageBounds.height);
//			g.drawImage(image, left, top, null);
//			ImageIO.write(image, formatName, subImageFile);
//			//System.out.println("if is running left:" + left + " top: " + top);
//		} else {
//			// BufferedImage subImage =
//			// image.getSubimage(subImageBounds.x,subImageBounds.y,
//			// subImageBounds.width, subImageBounds.height);
//			g.drawImage(image.getSubimage(subImageBounds.x, subImageBounds.y,
//					subImageBounds.width, subImageBounds.height), 0, 0, null);
//			//System.out.println("else is running");
//		}
//		g.dispose();
//		ImageIO.write(subImage, formatName, subImageFile);
//	}
//
//	/**
//	 * 图片缩放裁剪
//	 * 
//	 * @param srcImageFile
//	 *            原图保存路径
//	 * @param descDir
//	 *            目标图保存路径
//	 * @param width
//	 *            缩放后图片宽度
//	 * @param height
//	 *            缩放后图片高度
//	 * @param rect
//	 *            裁剪矩形框
//	 * @throws IOException
//	 */
//	public static void cut(String srcImageFile, String descDir, int width,
//			int height, Rectangle rect) throws IOException {
//		Image image = javax.imageio.ImageIO.read(new File(srcImageFile));
//		BufferedImage bImage = makeThumbnail(image, width, height);
//
//		saveSubImage(bImage, rect, new File(descDir));
//	}
//
//	public static void cut(File srcImageFile, File descDir, int width,
//			int height, Rectangle rect) throws IOException {
//		Image image = javax.imageio.ImageIO.read(srcImageFile);
//		BufferedImage bImage = makeThumbnail(image, width, height);
//
//		saveSubImage(bImage, rect, descDir);
//	}
//
//	/**
//	 * 根据参数截取图片
//	 * 
//	 * @param srcImageFile
//	 * @param descDir
//	 * @param width
//	 * @param height
//	 * @param rect
//	 * @throws IOException
//	 */
//	public boolean saveSubImage(File srcImageFile, File descDir, int width,
//			int height, Rectangle rect) throws IOException {
//		try {
//			ImageHelper.cut(srcImageFile, descDir, width, height, rect);
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}
//	
//	/**
//	 *  压缩最优比例图片
//	 * @param inputDir
//	 *            原图地址
//	 * @param outputDir
//	 *            输出地址
//	 * @param width
//	 * @param height
//	 * @return
//	 */
//	public static Boolean imageScale(String inputDir,String outputDir,int width, int height,float quality) {
//		try {
//			// 获得源文件
//			File file = new File(inputDir);
//			if (!file.exists()) {
//				return Boolean.FALSE;
//			}
//			Image image = ImageIO.read(file);
//			int imageWidth = image.getWidth(null);
//			int imageHeight = image.getHeight(null);
//			float scale = getRatio(imageWidth, imageHeight, width, height);
//			imageWidth = (int) (scale * imageWidth);
//			imageHeight = (int) (scale * imageHeight);
//
//			image = image.getScaledInstance(imageWidth, imageHeight,
//					Image.SCALE_AREA_AVERAGING);
//			// Make a BufferedImage from the Image.
//			BufferedImage mBufferedImage = new BufferedImage(imageWidth,
//					imageHeight, BufferedImage.TYPE_INT_RGB);
//			Graphics2D g2 = mBufferedImage.createGraphics();
//
//			g2.drawImage(image, 0, 0, imageWidth, imageHeight, Color.white,
//					null);
//			g2.dispose();
//
//			float[] kernelData2 = { -0.125f, -0.125f, -0.125f, -0.125f, 2,
//					-0.125f, -0.125f, -0.125f, -0.125f };
//			Kernel kernel = new Kernel(3, 3, kernelData2);
//			ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
//			mBufferedImage = cOp.filter(mBufferedImage, null);
//
//			FileOutputStream out = new FileOutputStream(outputDir);
//			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(mBufferedImage);
//			param.setQuality(quality, true);
//			encoder.setJPEGEncodeParam(param);
//			encoder.encode(mBufferedImage);
//			out.close();
//		} catch (IOException ex) {
//			ex.printStackTrace();
//			return Boolean.FALSE;
//		}
//		return Boolean.TRUE;
//	}
//	
//	/**
//	 *  压缩最优比例图片
//	 * @param inputDir
//	 *            原二进制
//	 * @param outputDir
//	 *            输出地址
//	 * @param width
//	 * @param height
//	 * @return
//	 */
//	public static Boolean imageScaleByInputStream(InputStream inputStream ,String outputDir,int width, int height,float quality) {
//		try {
//			Image image = ImageIO.read(inputStream);
//			int imageWidth = image.getWidth(null);
//			int imageHeight = image.getHeight(null);
//			float scale = getRatio(imageWidth, imageHeight, width, height);
//			imageWidth = (int) (scale * imageWidth);
//			imageHeight = (int) (scale * imageHeight);
//			
//			image = image.getScaledInstance(imageWidth, imageHeight,
//					Image.SCALE_SMOOTH);
//			// Make a BufferedImage from the Image.
//			BufferedImage mBufferedImage = new BufferedImage(imageWidth,
//					imageHeight, BufferedImage.TYPE_INT_RGB);
//			Graphics2D g2 = mBufferedImage.createGraphics();
//
//			g2.drawImage(image, 0, 0, imageWidth, imageHeight, Color.white,
//					null);
//			g2.dispose();
//
//			float[] kernelData2 = { -0.125f, -0.125f, -0.125f, -0.125f, 2,
//					-0.125f, -0.125f, -0.125f, -0.125f };
//			Kernel kernel = new Kernel(3, 3, kernelData2);
//			ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
//			mBufferedImage = cOp.filter(mBufferedImage, null);
//
//			FileOutputStream out = new FileOutputStream(outputDir);
//			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(mBufferedImage);
//			param.setQuality(quality, true);
//			encoder.setJPEGEncodeParam(param);
//			encoder.encode(mBufferedImage);
//			out.close();
//		} catch (IOException ex) {
//			ex.printStackTrace();
//			return Boolean.FALSE;
//		}
//		return Boolean.TRUE;
//	}
//
//	public static float getRatio(int width, int height, int maxWidth, int maxHeight) {
//		float Ratio = 1.0f;
//		float widthRatio;
//		float heightRatio;
//		widthRatio = (float) maxWidth / width;
//		heightRatio = (float) maxHeight / height;
//		if (widthRatio < 1.0 || heightRatio < 1.0) {
//			Ratio = widthRatio <= heightRatio ? widthRatio : heightRatio;
//		}
//		return Ratio;
//	}
//	
//	/**
//	 * 压缩最优比例图片 (不过会出现不规则边框  暂不使用)
//	 * 
//	 * @param inputDir
//	 *            原图地址
//	 * @param outputDir
//	 *            输出地址
//	 * @param width
//	 * @param height
//	 * @param proportion
//	 * @return
//	 */
//	public static Boolean compressPic(String inputDir, String outputDir,
//			int width, int height, boolean proportion) {
//		try {
//			// 获得源文件
//			File file = new File(inputDir);
//			if (!file.exists()) {
//				return Boolean.FALSE;
//			}
//			Image img = ImageIO.read(file);
//			// 判断图片格式是否正确
//			if (img.getWidth(null) == -1) {
//				//System.out.println(" can't read,retry!" + "<BR>");
//				return Boolean.FALSE;
//			} else {
//				int newWidth;
//				int newHeight;
//				// 判断是否是等比缩放
//				if (proportion == true) {
//					// 为等比缩放计算输出的图片宽度及高度
//					double rate1 = ((double) img.getWidth(null))
//							/ (double) width + 0.1;
//					double rate2 = ((double) img.getHeight(null))
//							/ (double) height + 0.1;
//					// 根据缩放比率大的进行缩放控制
//					double rate = rate1 > rate2 ? rate1 : rate2;
//					newWidth = (int) (((double) img.getWidth(null)) / rate);
//					newHeight = (int) (((double) img.getHeight(null)) / rate);
//				} else {
//					newWidth = width; // 输出的图片宽度
//					newHeight = height; // 输出的图片高度
//				}
//				if (newWidth == 0) {
//					newWidth = 1;
//				}
//				if (newHeight == 0) {
//					newHeight = 1;
//				}
//				BufferedImage tag = new BufferedImage((int) newWidth,
//						(int) newHeight, BufferedImage.TYPE_INT_RGB);
//
//				/*
//				 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
//				 */
//				tag.getGraphics().drawImage(
//						img.getScaledInstance(newWidth, newHeight,
//								Image.SCALE_SMOOTH), 0, 0, null);
//				FileOutputStream out = new FileOutputStream(outputDir);
//				// JPEGImageEncoder可适用于其他图片类型的转换
//				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//				encoder.encode(tag);
//				out.close();
//			}
//		} catch (IOException ex) {
//			ex.printStackTrace();
//			return Boolean.FALSE;
//		}
//		return Boolean.TRUE;
//	}
//
//	/**
//	 * 图片按宽度等比例压缩
//	 * 
//	 * @param input
//	 * @param savePath
//	 * @param width
//	 * @param height
//	 * @throws IOException
//	 */
//	public void saveImg(String inputDir, String savePath, int compressWidth,
//			int baseHeight) throws IOException {
//		File file = new File(inputDir);
//		BufferedImage bi = ImageIO.read(file);
//		double wRatio = (new Integer(compressWidth)).doubleValue()
//				/ bi.getWidth(); // 宽度的比例
//		Image image = bi.getScaledInstance(compressWidth, baseHeight,
//				Image.SCALE_SMOOTH); // 设置图像的缩放大小
//		AffineTransformOp op = new AffineTransformOp(AffineTransform
//				.getScaleInstance(wRatio, wRatio), null); // 设置图像的缩放比例
//		image = op.filter(bi, null);
//
//		File zoomFile = new File(savePath);
//
//		Icon ret = null;
//		try {
//			ImageIO.write((BufferedImage) image, "jpg", zoomFile);
//			ret = new ImageIcon(zoomFile.getPath());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 图片按宽度等比例压缩
//	 * 
//	 * @param input
//	 * @param savePath
//	 * @param width
//	 * @param height
//	 * @throws IOException
//	 */
//	public void saveImg(InputStream input, String savePath, int compressWidth,
//			int baseHeight) throws IOException {
//		BufferedImage bi = ImageIO.read(input);
//		double wRatio = (new Integer(compressWidth)).doubleValue()
//				/ bi.getWidth(); // 宽度的比例
//		Image image = bi.getScaledInstance(compressWidth, baseHeight,
//				Image.SCALE_SMOOTH); // 设置图像的缩放大小
//		AffineTransformOp op = new AffineTransformOp(AffineTransform
//				.getScaleInstance(wRatio, wRatio), null); // 设置图像的缩放比例
//		image = op.filter(bi, null);
//
//		File zoomFile = new File(savePath);
//
//		Icon ret = null;
//		try {
//			ImageIO.write((BufferedImage) image, "jpg", zoomFile);
//			ret = new ImageIcon(zoomFile.getPath());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	/***
// 	 * 调整图片大小
// 	 * @param srcImgPath 原图片路径
// 	 * @param distImgPath  转换大小后图片路径
// 	 * @param width   转换后图片宽度
// 	 * @param height  转换后图片高度
// 	 */
// 	public static void resizeImage(String srcImgPath, String distImgPath,int width, int height) throws IOException {
//
// 		File srcFile = new File(srcImgPath);
// 		Image srcImg = ImageIO.read(srcFile);
// 		BufferedImage buffImg = null;
// 		buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
// 		buffImg.getGraphics().drawImage(
// 				srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0,
// 				0, null);
//
// 		ImageIO.write(buffImg, "JPEG", new File(distImgPath));
//
// 	}
//
//}
