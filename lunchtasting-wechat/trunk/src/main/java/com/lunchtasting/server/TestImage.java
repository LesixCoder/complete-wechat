package com.lunchtasting.server;

import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TestImage {

	public static void main(String[] args) {
		// 加载原图
		BufferedImage back_img = null;
		File fpic = new File("D:\\ewm\\1.jpg");
		try {
			back_img = (BufferedImage) ImageIO.read(fpic);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int width = back_img.getWidth();
		int height = back_img.getHeight();
		Graphics2D backg2d = (Graphics2D) back_img.getGraphics();

		// 加载新图
		BufferedImage old_img = null;
		File fpic2 = new File("D:\\ewm\\2.png");
		try {
			old_img = (BufferedImage) ImageIO.read(fpic2);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 背景透明
		BufferedImage new_img = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d2 = new_img.createGraphics();
		
//		new_img = g2d2.getDeviceConfiguration().createCompatibleImage(height,
//				width, Transparency.TRANSLUCENT);
//		g2d2.dispose();
//		g2d2 = new_img.createGraphics();

		// // 确定旋转圆心
//		AffineTransform origXform = g2d2.getTransform();
//		AffineTransform newXform = (AffineTransform) (origXform.clone());
//		newXform.rotate(Math.toRadians(8.0), width, height);
//		g2d2.setTransform(newXform);
		//

//		g2d2.setTransform(origXform);
		g2d2.drawImage(back_img, 0, 0, null);
		g2d2.drawImage(old_img, 123, 578, 85, 85, null);

		g2d2.dispose();

		try {
			ImageIO.write(new_img, "png", new File(
					"D:\\ewm\\3.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
