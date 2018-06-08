package com.perfit.server.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lunchtasting.server.po.lt.Seller;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.perfit.server.biz.seller.CookieHandlingBIZ;
import com.perfit.server.biz.seller.SellerBIZ;
import com.perfit.server.biz.seller.impl.SellerBIZImpl;
import com.perfit.server.helper.MapResult;
import com.perfit.server.helper.VariableHelper;

@Controller
public class ShellUserController {
	@Autowired 
	private SellerBIZ sellerBIZ;
	@Autowired
	private CookieHandlingBIZ cookieHandlingBIZ;
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sellerLogin")
	@ResponseBody
	public Object sellerLogin(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String name  =request.getParameter("name");
		String password = request.getParameter("password");
		if(name==null || password==null ||name.equals("") || password.equals("")){
			return MapResult.buildin(1,"用户名或者密码输入不正规",null);
		}
		try{
			Seller seller=sellerBIZ.sellerUsersLogin(name, password);
			if(seller!=null){
				Map map =new HashMap();
				request.getSession().setAttribute(VariableHelper.LOGIN_SESSION_USER, seller);
				if(request.getSession().getAttribute("url")!=null){
					String url = (String) request.getSession().getAttribute("url");
					map.put("url",url);
				}else{
					map.put("url", "goSeller");
				}
				request.getSession().setMaxInactiveInterval(3600);
				cookieHandlingBIZ.saveCookie(request, response, VariableHelper.LOGIN_COOKIE_USER, seller.getId()+"");
				return MapResult.buildin(0,"登录成功",map);
			}else{
				return MapResult.buildin(1,"用户名或者密码错误！",null);
			}
		}catch (Exception e) {
			return MapResult.build(1,"服务器错误",null);
		}
	}
	/**
	 * 查询，个人信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/goSeller")
	public String goSeller(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Seller seller = (Seller)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
		try{
			seller = sellerBIZ.sellerById(seller.getId());
			seller.setImgUrl(VariableHelper.IMG_URL_HEAD+seller.getImgUrl());
			String imgArr =seller.getImgArray();
			if(imgArr!=null && !imgArr.equals("")){
				seller.setImgArray(VariableHelper.IMG_URL_HEAD+imgArr.replaceAll(",",","+VariableHelper.IMG_URL_HEAD));
			}
			model.addAttribute("seller",seller);
			return "/seller_save";
		}catch (Exception e) {
			return "/seller_save";//404或者错误页面
		}
	}
	@RequestMapping(value = "/saveSeller")
	@ResponseBody
	public Object saveSeller(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Seller ac = new Seller();
		try{
			Seller seller = (Seller)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
			ac.setId(seller.getId());
			ac.setSpecificAddress(request.getParameter("specificAddress"));
			ac.setName(request.getParameter("name"));
			ac.setIntroduction(request.getParameter("introduction"));
			ac.setTag(request.getParameter("tag"));
			ac.setPhone(request.getParameter("phone"));
			ac.setBusinessHours(request.getParameter("businessHours"));
			ac.setAddress(request.getParameter("address"));
			String imgUrlss= request.getParameter("imgimg");
			String imgArr = request.getParameter("imgArr");
			/**
			 * 多图
			 */
			if(imgArr!=null){
				imgArr = imgArr.replaceAll(VariableHelper.IMG_URL_HEAD,"");
				ac.setImgArray(imgArr);
			}
			/**
			 * 首图
			 */
			if(!imgUrlss.equals("") && imgUrlss!=null){
				ac.setImgUrl(imgUrlss);
			}
			String imgArray = request.getParameter("imgArray");
		}catch (Exception e) {
			return MapResult.build(1,"参数存在问题",null);
		}
		try{ 
			if(!sellerBIZ.updateSeller(ac)){
				return MapResult.build(1,"提交失败",null);
			}
			return MapResult.build(0,"提交成功",null);
		}catch (Exception e) {
			System.out.println(e.toString());
			return MapResult.build(1,"服务器错误",null);
		}
	}
	
	@RequestMapping(value = "/uppwdSeller")
	@ResponseBody
	public Object uppwdSeller(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try{
			Seller seller = (Seller)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
			String oldPassword = request.getParameter("oldpassword");
			String newPassword = request.getParameter("newpassword");
			if(oldPassword==null||oldPassword==null||oldPassword.equals("")||newPassword.equals("")){
				return MapResult.build(1,"参数存在问题",null);
			}
			Seller seller2 = sellerBIZ.sellerById(seller.getId());
			if(!oldPassword.equals(seller2.getPassword())){
				return MapResult.build(1,"原密码输入错误",null);
			}
			Seller seller3 = new Seller();
			seller3.setPassword(newPassword);
			seller3.setId(seller.getId());
			if(sellerBIZ.updateSeller(seller3)){
				request.getSession().invalidate();
				cookieHandlingBIZ.logout(request, response, VariableHelper.LOGIN_COOKIE_USER);
				return MapResult.build(0,"修改密码成功",null);
			}
		}catch (Exception e) {
			return MapResult.build(1,"服务错误！",null);
		}
		return MapResult.build(1,"修改密码失败。",null);
	}
}
