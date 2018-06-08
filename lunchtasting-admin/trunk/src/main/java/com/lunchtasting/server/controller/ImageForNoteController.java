package com.lunchtasting.server.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.common.io.ByteStreams;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.helper.ImageHelper;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;

@Controller
public class ImageForNoteController extends BaseController{
	@RequestMapping(value = "/image/uploadForNote", method = RequestMethod.POST)
	@ResponseBody
	public void upload(HttpServletRequest request, HttpServletResponse response)
			throws IOException, FileUploadException {

		ServletContext application = request.getSession().getServletContext();
		String savePath = application.getRealPath("/") + "attached/";  
		// 文件保存目录URL  
        String saveUrl = request.getContextPath() + "/attached/"; 
        
		PrintWriter out = response.getWriter();
		
		String type = request.getParameter("type");

		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,mp4");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		// 最大文件大小
		long maxSize = 60000000;

		response.setContentType("text/html; charset=UTF-8");

		if (!ServletFileUpload.isMultipartContent(request)) {
			out.println(getError("请选择文件。"));
			return;
		}

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if (!extMap.containsKey(dirName)) {
			out.println(getError("目录名不正确。"));
			return;
		}
		
		MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
		Map map = mrequest.getFileMap();
		Collection<MultipartFile> c = map.values();
		Iterator item = c.iterator();
		
		while (item.hasNext()) {
			CommonsMultipartFile file=(CommonsMultipartFile) item.next();
			FileItem fileItem=file.getFileItem();
			long fileSize = file.getSize();
			if (!fileItem.isFormField()) {
				// 检查文件大小
				if (fileSize > maxSize) {
					out.println(getError("上传文件大小超过限制。"));
					return;
				}
				// 检查扩展名
				String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();  
				if (!Arrays.<String> asList(extMap.get(dirName).split(","))
						.contains(fileExt)) {
					out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许"
							+ extMap.get(dirName) + "格式。"));
					return;
				}
				String imgName = "";
				String newFileName = "";
				int width = 0;
				int height = 0;
				if(!dirName.equals("media")){
					try {
					if (type.equals("seller")) {
						imgName = QiNiuStorageHelper
								.getQiNiuImgName(QiNiuStorageHelper.SELLER_IMG_PREFIX);
					} else if (type.equals("activity")) {
						imgName = QiNiuStorageHelper
								.getQiNiuImgName(QiNiuStorageHelper.ACTIVITY_IMG_PREFIX);
					}else if(type.equals("course")){
						imgName = QiNiuStorageHelper
								.getQiNiuImgName(QiNiuStorageHelper.COURSE_IMG_PREFIX);
					}else if(type.equals("note")){
						imgName = QiNiuStorageHelper
								.getQiNiuImgName(QiNiuStorageHelper.NOTE_IMG_PREFIX);
					}else if(type.equals("match")){
						imgName = QiNiuStorageHelper
								.getQiNiuImgName(QiNiuStorageHelper.MATCH_IMG_PREFIX);
					}

					if (!QiNiuStorageHelper.byteUpload(imgName, ImageHelper.scaleImage(fileItem.getInputStream(),1,"jpg"))) {
						out.println(getError("上传文件失败。"));
						return;
					}
					BufferedImage bufferedImage = ImageIO.read(fileItem.getInputStream());
					width = ImageHelper.parseDoubleToInt(bufferedImage.getWidth());
					height = ImageHelper.parseDoubleToInt(bufferedImage.getHeight());
				} catch (Exception e) {
					out.println(getError("上传文件失败。"));
					return;
				}
				}else{
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
					newFileName = df.format(new Date()) + "_"
							+ new Random().nextInt(1000) + "." + fileExt;
					try {

						File uploadedFile = new File(savePath, newFileName);
						ByteStreams.copy(file.getInputStream(),
								new FileOutputStream(uploadedFile));
					} catch (Exception e) {
						response.getWriter().write(getError("上传文件失败。"));
						return;
					}
				}

				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				if(!dirName.equals("media")){
					obj.put("url", SysConfig.IMG_VISIT_URL + imgName);
					obj.put("width", width);
					obj.put("height", height);
				}else{
					obj.put("url", saveUrl + newFileName);
				}
				out.println(obj.toJSONString());
		}
	}
}

	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
}
