package com.lunchtasting.server.util;



import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

public class FileUploadUtil {


	private static Logger logger = Logger.getLogger(FileUploadUtil.class);

	/**
	 * 图片文件根目录
	 */
	public static String filePath;
	

	/**
	 * 上传的单个文件大小
	 */
	public static  int fileSize;
	
	/**
	 * 文件暂存目录
	 */
	public static String tmpDir;
	
	/**
	 * 素材图片根目录
	 */
	public static String draftDir;
	
	/**
	 * 商品图片根目录
	 */
	public static String productDir;
	
	/**
	 * 主题根目录
	 */
	public static String topicDir;
	
	/**
	 * 品牌根目录
	 */
	public static String brandDir;
	
	/**
	 * logo根目录
	 */
	public static String logoDir;
	
	/**
	 * 其他图片根目录
	 */
	public static String otherDir;
	
	/**
	 * 图片服务器域名地址
	 */
	public static String imageUrl;
	
	public static String userDir;
	
	/**
	 * 设置编码
	 */
	public static String encoding;
	
	@SuppressWarnings("unchecked")
	/**
	 * 获得文件列表
	 * @return 表单域中得文件列表
	 * @request request请求
	 */
	public static List<FileItem> getFileList(HttpServletRequest request){
		DiskFileItemFactory fileFactory=new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(fileFactory);
		// 文件大小
		sfu.setSizeMax(1048576);
		sfu.setHeaderEncoding(encoding);
		List<FileItem> fileList=new ArrayList<FileItem>();
		// 得到所有表单字段对象的集合
		List<FileItem> formItems = null;
		try {
			formItems = sfu.parseRequest(request);
			Iterator<FileItem> iterator=formItems.iterator();
			FileItem item=null;
			while(iterator.hasNext()){
				item=iterator.next();
				if(!item.isFormField()){
					fileList.add(item);
				}
			}
		} catch (FileUploadException e) {
			logger.debug(e.getMessage(), e);
			return null;
		}
		return fileList;
	}
	
	/**
	 * 获取表单中普通文本域的参数
	 * @param request
	 * @return 参数Map
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> getParamMap(HttpServletRequest request){
		DiskFileItemFactory fileFactory=new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(fileFactory);
		sfu.setHeaderEncoding(encoding);
		Map<String,Object> param=new HashMap<String,Object>();
		// 得到所有表单字段对象的集合
		List<FileItem> formItems = null;
		try {
			formItems = sfu.parseRequest(request);
			Iterator<FileItem> iterator=formItems.iterator();
			FileItem item=null;
			while(iterator.hasNext()){
				item=iterator.next();
				if(item.isFormField()){
					param.put(item.getFieldName(), item.getString(encoding));
				}
			}
		} catch (FileUploadException e) {
			logger.debug(e.getMessage(), e);
			return null;
		} catch (UnsupportedEncodingException e) {
			logger.debug(e.getMessage(), e);
			return null;
		}
		return param;
	}
	
//	@SuppressWarnings("unchecked")
	/**
	 * @request 请求参数
	 * @return 返回表单中普通表单的数据 K-V map
	 * @saveDir 保存的路径，需要在调用前拼接完成
	 */
	/*public static Map<String,Object> uploadFile(String saveDir,HttpServletRequest request)
			throws UnsupportedEncodingException {
		Map<String,Object> param=new HashMap<String,Object>();
		File fUploadDir = new File(saveDir);
		if (!fUploadDir.exists()) {
			if (!fUploadDir.mkdir()) {
				logger.error("can not create directory");
				return null;
			}
		}
		if (!DiskFileUpload.isMultipartContent(request)) {
			logger.error("the file is wrong!");
			return null;
		}
		DiskFileUpload fu = new DiskFileUpload();
		// 文件大小
		fu.setSizeMax(fileSize);
		fu.setHeaderEncoding("utf8");
		// 得到所有表单字段对象的集合
		List<Object> formItems = null;
		try {
			formItems = fu.parseRequest(request);
		} catch (FileUploadException e) {
			logger.debug(e.getMessage(), e);
			return null;
		}
		// 处理每个表单字段
		Iterator i = formItems.iterator();
		while (i.hasNext()) {
			FileItem fi = (FileItem) i.next();
			if (fi.isFormField()) {//如果是普通表单，则返回表单参数
				String content = fi.getString(request.getCharacterEncoding());
				String fieldName = fi.getFieldName();
				param.put(fieldName, content);
			} else {
				try {
					String pathSrc = fi.getName();
					// 如果用户没有在form表单的文件字段中选择任何文件，那么忽略对该字段项的处理
					if (pathSrc.trim().equals("")) {
						continue;
					}
					int start = pathSrc.lastIndexOf("\\");
					String fileName = pathSrc.substring(start + 1);
					File pathDest = new File(saveDir, fileName);
					fi.write(pathDest);
					String fieldName = fi.getFieldName();
					request.setAttribute(fieldName, fileName);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage(), e);
					return null;
				} finally {// 总是立即删除保存表单字段内容的临时文件
					fi.delete();
				}
			}
		}
		return param;
	}*/


	 /**
	 * 依据传递参数上传文件
	 * 
	 * @param file
	 *            上传的文件
	 * @param savePath
	 *            文件保存路径
	 * @param fileName
	 * 			  文件保存名称
	 * @throws IOException
	 */
	public static String saveFile(File file, String savePath, String fileName)
			throws IOException {

		File dir = new File(savePath);
		if (!dir.exists())
			dir.mkdirs();

		// 获取上传文件的输入流
		InputStream in = new BufferedInputStream(new FileInputStream(file));

		// 实例化文件空壳（根据 filePath 路径名字符串和 fileName 路径名字符串创建一个新 File 实例）
		File fileSaved = new File(savePath, fileName);

		// 创建二进制文件输出流
		OutputStream os = new FileOutputStream(fileSaved);

		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		// 关闭文件流
		os.close();
		in.close();
		return fileName;
	}
	
	/**
	 * 保存文件
	 * @param itemList FileItem列表
	 * @param saveDir 保存路径
	 */
	public static void savaFile(List<FileItem> itemList,String saveDir){
		for (FileItem fileItem : itemList) {
			File pathDest = new File(saveDir);
			try {
				fileItem.write(pathDest);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	

	/**
	 * 根据服务器端文件相对路径删除文件
	 * 
	 * @param fileUri
	 * @return
	 * @throws IOException
	 */
	public static boolean deleteFile(String realPath) {
		File file = new File(realPath);
		return file.delete();
	}
	
	public  Logger getLogger() {
		return logger;
	}

	public  void setLogger(Logger logger) {
		FileUploadUtil.logger = logger;
	}

	public  String getFilePath() {
		return filePath;
	}

	public  void setFilePath(String filePath) {
		FileUploadUtil.filePath = filePath;
	}

	public  int getFileSize() {
		return fileSize;
	}

	public  void setFileSize(int fileSize) {
		FileUploadUtil.fileSize = fileSize;
	}

	public  String getTmpDir() {
		return tmpDir;
	}

	public  void setTmpDir(String tmpDir) {
		FileUploadUtil.tmpDir = tmpDir;
	}

	public  String getDraftDir() {
		return draftDir;
	}

	public  void setDraftDir(String draftDir) {
		FileUploadUtil.draftDir = draftDir;
	}

	public  String getProductDir() {
		return productDir;
	}

	public  void setProductDir(String productDir) {
		FileUploadUtil.productDir = productDir;
	}

	public  String getTopicDir() {
		return topicDir;
	}

	public  void setTopicDir(String topicDir) {
		FileUploadUtil.topicDir = topicDir;
	}

	public  String getBrandDir() {
		return brandDir;
	}

	public  void setBrandDir(String brandDir) {
		FileUploadUtil.brandDir = brandDir;
	}

	public  String getLogoDir() {
		return logoDir;
	}

	public  void setLogoDir(String logoDir) {
		FileUploadUtil.logoDir = logoDir;
	}

	public  String getOtherDir() {
		return otherDir;
	}

	public  void setOtherDir(String otherDir) {
		FileUploadUtil.otherDir = otherDir;
	}

	public  String getImageUrl() {
		return imageUrl;
	}

	public  void setImageUrl(String imageUrl) {
		FileUploadUtil.imageUrl = imageUrl;
	}
	
	

	public String getUserDir() {
		return userDir;
	}

	public void setUserDir(String userDir) {
		FileUploadUtil.userDir = userDir;
	}

	public  String getEncoding() {
		return encoding;
	}

	public  void setEncoding(String encoding) {
		FileUploadUtil.encoding = encoding;
	}

}
