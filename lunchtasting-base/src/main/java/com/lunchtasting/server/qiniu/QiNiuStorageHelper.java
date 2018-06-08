package com.lunchtasting.server.qiniu;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.util.ImageUtil;
import com.lunchtasting.server.util.Utils;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class QiNiuStorageHelper {

	// 密钥配置
	static Auth auth = Auth.create(SysConfig.QINIU_ACCESSKKEY,
			SysConfig.QINIU_SECRETKEY);
	// 创建上传对象
	static UploadManager uploadManager = new UploadManager();
	
	//图片存储前缀路径
	public final static String USER_IMG_PREFIX = "user/";
	public final static String ACTIVITY_IMG_PREFIX = "activity/";
	public final static String SELLER_IMG_PREFIX = "seller/";
	public final static String ARTICLE_IMG_PREFIX = "article/";
	public final static String COURSE_IMG_PREFIX = "course/";
	public final static String NOTE_IMG_PREFIX = "note/";
	public final static String MEDAL_IMG_PREFIX = "medal/";
	public final static String MATCH_IMG_PREFIX = "match/";
	public final static String MATCH_ALBUM_IMAGE_PREFIX = "match_album_image/";

	
	//模式七牛参考文档：http://developer.qiniu.com/code/v6/api/kodo-api/image/imageview2.html
	//等比缩放 
	public final static String MODEL0 = "?imageView2/0/";
	//居中裁剪
	public final static String MODEL1 = "?imageView2/1/";
	//等比缩放
	public final static String MODEL2 = "?imageView2/2/";

	
	//默认用户图片
	public final static String DEFAULT_USER_IMG = "user/default.jpg";
	
	/**
	 * 获得上传凭证
	 * @return
	 */
	public static String getUpToken(){
		return auth.uploadToken(SysConfig.QINIU_IMAGE_BUCKETNAME);
	}
	
	/**
	 * 本地地址上传
	 * @param key
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static Boolean upload(String key, String path) throws IOException {
		try {
			// 调用put方法上传
			Response res = uploadManager.put(path, key,
					auth.uploadToken(SysConfig.QINIU_IMAGE_BUCKETNAME));
			// 打印返回的信息
			System.out.println(res.bodyString());
			if (res.statusCode == 200) {
				return true;
			}
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			System.out.println(r.toString());
			try {
				// 响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}
		return false;
	}
	
	/**
	 * 网络地址上传
	 * @param key
	 * @param url
	 * @return
	 */
	public static Boolean urlUpload(String key, String url) {
		try {
			// 调用put方法上传
			byte[] data = ImageUtil.ReadUrlToByte(url);
			Response res = uploadManager.put(data, key,
					auth.uploadToken(SysConfig.QINIU_IMAGE_BUCKETNAME));
			// 打印返回的信息
			System.out.println(res.bodyString());
			if (res.statusCode == 200) {
				return true;
			}
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			System.out.println(r.toString());
			try {
				// 响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}
		return false;
	}
	
	/**
	 * 二进制上传
	 * @param key
	 * @param data
	 * @return
	 */
	public static Boolean byteUpload(String key,byte[] data) {
		try {
			// 调用put方法上传
			Response res = uploadManager.put(data, key,
					auth.uploadToken(SysConfig.QINIU_IMAGE_BUCKETNAME));
			// 打印返回的信息
			System.out.println(res.bodyString());
			if (res.statusCode == 200) {
				return true;
			}
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			System.out.println(r.toString());
			try {
				// 响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}
		return false;
	}
	
	/**
	 * 覆盖上传
	 * @param key
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static Boolean overlayUpload(String key, String path)
			throws IOException {
		// <bucket>:<key>，表示只允许用户上传指定key的文件。在这种格式下文件默认允许“修改”，已存在同名资源则会被本次覆盖。
		String upToken = auth.uploadToken(SysConfig.QINIU_IMAGE_BUCKETNAME, key);

		try {
			// 调用put方法上传，这里指定的key和上传策略中的key要一致
			Response res = uploadManager.put(path, key, upToken);
			// 打印返回的信息
			System.out.println(res.bodyString());
			if (res.statusCode == 200) {
				return true;
			}
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常信息
			System.out.println(r.toString());
			try {
				// 响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}
		return false;
	}
	
	/**
	 * 获得七牛图片存储名字
	 * @param name
	 * @return
	 */
	public static String getQiNiuImgName(String name){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "" + Utils.getRandomNumber(3);
		return name+newFileName+".jpg";
	}

	public static void main(String[] args) throws IOException {
		
//		String token = auth.uploadToken(SysConfig.QINIU_IMAGE_BUCKETNAME);
//		System.out.println(token);
		
		//System.out.println(upload("/user/default.jpg","D:\\sz\\default_user.jpg"));
		
		QiNiuStorageHelper.upload("match/426.jpg",
		 "D:\\222.jpg");
//		String url = "http://pic3.nipic.com/20090701/2847972_130628068_2.jpg";
//		System.out.println(QiNiuStorageHelper.urlUpload(QiNiuStorageHelper.getQiNiuImgName(QiNiuStorageHelper.ACTIVITY_IMG_PREFIX),
//				url));
//		System.out.println(QiNiuStorageHelper.getQiNiuImgName(QiNiuStorageHelper.ACTIVITY_IMG_PREFIX));
		
//		System.out.println(QiNiuStorageHelper.upload(
//							QiNiuStorageHelper.getQiNiuImgName(QiNiuStorageHelper.USER_IMG_PREFIX), 
//							"D:\\1.jpg"));
	}

}
