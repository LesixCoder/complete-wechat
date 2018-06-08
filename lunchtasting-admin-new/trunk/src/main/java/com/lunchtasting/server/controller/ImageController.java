package com.lunchtasting.server.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.ValidatorHelper;

@Controller
@RequestMapping("/image")
public class ImageController {

	/**
	 * 图片上传
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/upload")
	@ResponseBody
	public Object upload(HttpServletRequest request,HttpServletResponse response,
				@RequestParam("fileName") MultipartFile fileName) throws IOException{
		String type = request.getParameter("type");
		if(!fileName.isEmpty()){
			System.out.println(fileName.getOriginalFilename() + "//" + fileName.getSize());
		}
		
		if(fileName.isEmpty() || !ValidatorHelper.isNumber(type)){
			callback("", "", 0,MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER, response);
			return null;
		}
		int intType = Integer.parseInt(type);
		
		/**
		 * type 1场馆封面图  2场馆多图 3教练封面图 4教练相册图 5课程封面图 6课程多图
		 */
		String imgName = "";
		if(intType == 1 || intType == 2){
			imgName = QiNiuStorageHelper.getQiNiuImgName(QiNiuStorageHelper.GYM_IMG_PREFIX);
		}else if (intType == 3){
			imgName = QiNiuStorageHelper.getQiNiuImgName(QiNiuStorageHelper.COACH_IMG_PREFIX);
		}else if (intType == 4){
			imgName = QiNiuStorageHelper.getQiNiuImgName(QiNiuStorageHelper.COACH_ALBUM_IMG_PREFIX);
		}else if (intType == 5 || intType == 6){
			imgName = QiNiuStorageHelper.getQiNiuImgName(QiNiuStorageHelper.COURSE_IMG_PREFIX);
		}else{
			callback("", "", intType,MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER, response);
			return null;
		}
		
		try {
			Boolean result = QiNiuStorageHelper.byteUpload(imgName, fileName.getBytes());
			if(result){
				callback(imgName, SysConfig.IMG_VISIT_URL+imgName, intType, MapResult.CODE_SUCCESS
						,MapResult.MESSAGE_SUCCESS, response);
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			callback("", "", intType,MapResult.CODE_SYS_ERROR,MapResult.MESSAGE_ERROR, response);
			return null;
			
		}
		callback("", "", intType,MapResult.CODE_FAILURE,MapResult.MESSAGE_FAILURE, response);
		return null;
	}
	
	private void callback(String imageName,String imageUrl,int type
			,Integer code,String msg,HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		out.println("<script>parent.setImage('"+imageName+"','"+imageUrl+"','"+type+"','"+code+"','"+msg+"')</script>");
	} 
}
