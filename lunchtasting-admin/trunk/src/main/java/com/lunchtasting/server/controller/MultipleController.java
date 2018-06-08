package com.lunchtasting.server.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.helper.ImageHelper;
import com.lunchtasting.server.helper.ImageMagick;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;

@Controller
public class MultipleController extends BaseController{
	@RequestMapping(value = "/image/multipleUpload", method = RequestMethod.POST)
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
		
		StringBuffer sb = new StringBuffer();
		
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
					}else if(type.equals("medal")){
						imgName = QiNiuStorageHelper
								.getQiNiuImgName(QiNiuStorageHelper.MEDAL_IMG_PREFIX);
					}else if(type.equals("user")){
						imgName = QiNiuStorageHelper
								.getQiNiuImgName(QiNiuStorageHelper.USER_IMG_PREFIX);
					}else if(type.equals("album")){
						imgName = QiNiuStorageHelper
								.getQiNiuImgName(QiNiuStorageHelper.ALBUM_IMG_PREFIX);
					}

//					if (!QiNiuStorageHelper.byteUpload(imgName, ImageHelper.scaleImage(fileItem.getInputStream(),1,"jpg"))) {
//						out.println(getError("上传文件失败。"));
//						return;
//					}
					
					if(type.equals("album")){
//						InputStream  stream = ImageHelper.markImageByIcon(savePath+"shuiyin.png", fileItem.getInputStream(), "right-bottom");
						byte[]  stream = ImageHelper.pressImage(savePath+"shuiyin.png", fileItem.getInputStream(), 4, 0.8f);
						if (!QiNiuStorageHelper.byteUpload(imgName, stream)) {
							out.println(getError("上传文件失败。"));
							return;
						}
					}else{
						if (!QiNiuStorageHelper.byteUpload(imgName, ImageHelper.scaleImage(fileItem.getInputStream(),1,"jpg"))) {
							out.println(getError("上传文件失败。"));
							return;
						}
					}
				} catch (Exception e) {
					out.println(getError("上传文件失败。"));
					return;
				}

				sb.append(SysConfig.IMG_VISIT_URL + imgName).append(",");
				
		}
	}
		JSONObject obj = new JSONObject();
		obj.put("error", 0);
		obj.put("url", sb.toString().substring(0, sb.toString().length()-1));
		out.println(obj.toJSONString());
}

	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
}
