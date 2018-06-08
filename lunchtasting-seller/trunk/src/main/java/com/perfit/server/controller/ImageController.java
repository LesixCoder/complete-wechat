package com.perfit.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.json.simple.*;

import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.qiniu.ImageHelper;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.config.SysConfig;

@Controller
public class ImageController extends BaseController{
	
	@RequestMapping(value = "/image/upload")
	public void upload(HttpServletRequest request,HttpServletResponse response) throws IOException, FileUploadException{
		PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		// 最大文件大小
		long maxSize = 6000000;
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
					}else{
						imgName = QiNiuStorageHelper
								.getQiNiuImgName(QiNiuStorageHelper.SELLER_IMG_PREFIX);						
					}
					
					if (!QiNiuStorageHelper.byteUpload(imgName, ImageHelper.scaleImage(fileItem.getInputStream(),1,"jpg"))) {
						out.println(getError("上传文件失败。"));
						return;
					}
				} catch (Exception e) {
					out.println(getError("上传文件失败。"));
					return;
				}
				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("name", imgName);
				obj.put("url", SysConfig.IMG_VISIT_URL + imgName);
				out.println(obj.toJSONString());
		}
	}
	
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
}
