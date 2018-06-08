package com.perfit.server.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lunchtasting.server.qiniu.ImageHelper;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.perfit.server.helper.MapResult;
import com.perfit.server.helper.VariableHelper;

@Controller
public class IndexController {

	@RequestMapping(value = "/")
	public String index(){
		return "login";
	}
	@RequestMapping(value = "login")
	public String index2(){
		return "login";
	}
	/**
	 * 图片上传
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upImgSrc")
	@ResponseBody
	public Object upImgSrc(@RequestParam("myfiles")MultipartFile fileField,HttpServletRequest request,HttpServletResponse response){
		try {
			HashMap map = new HashMap(); 
			String name = QiNiuStorageHelper.getQiNiuImgName(QiNiuStorageHelper.SELLER_IMG_PREFIX);
			if(QiNiuStorageHelper.byteUpload(name,ImageHelper.scaleImage(fileField.getInputStream(),1,"jpg"))){
				map.put("name",name);
				map.put("hand", VariableHelper.IMG_URL_HEAD);
				return  MapResult.build(0,"上传图片成功",map,request);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  MapResult.buildin(1,"上传图片失败",null);
		}
		return  MapResult.buildin(1,"上传图片失败",null);
	}
}
