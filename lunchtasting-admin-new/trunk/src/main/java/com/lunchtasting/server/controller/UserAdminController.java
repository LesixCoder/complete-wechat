package com.lunchtasting.server.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.user.CookieHandlingBIZ;
import com.lunchtasting.server.biz.user.UserAdminBIZ;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.po.lt.UserAdmin;
import com.lunchtasting.server.util.ValidatorHelper;

@Controller
public class UserAdminController {
	
	@Autowired
	private UserAdminBIZ userAdminBIZ;
	@Autowired
	private CookieHandlingBIZ cookieHandlingBIZ;

	@RequestMapping(value = "/doLogin")
	@ResponseBody
	public Object login(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String account = request.getParameter("account");
		String pwd = request.getParameter("password");
		if(ValidatorHelper.isEmpty(account) || ValidatorHelper.isEmpty(pwd)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		
		UserAdmin userAdmin = userAdminBIZ.getByAccountAndPwd(account, pwd);
		if(userAdmin != null){
			
			/**
			 * 保存session
			 */
			session.setAttribute(VariableHelper.LOGIN_SESSION_USER,userAdmin);
			cookieHandlingBIZ.saveCookie(request, response, VariableHelper.LOGIN_COOKIE_USER, userAdmin.getId()+"");
			
			return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS);
		}
				
		return MapResult.build(MapResult.CODE_FAILURE,MapResult.MESSAGE_FAILURE);
	}
	
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/logout")
	public void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.getSession().removeAttribute(VariableHelper.LOGIN_SESSION_USER);
		cookieHandlingBIZ.logout(request, response, VariableHelper.LOGIN_COOKIE_USER);
		response.sendRedirect(CommonHelper.gotoWebRoot(request)+"login");
		return;
	}
}
