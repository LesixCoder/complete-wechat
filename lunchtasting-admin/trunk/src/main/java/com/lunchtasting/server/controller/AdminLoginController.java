package com.lunchtasting.server.controller;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lunchtasting.server.biz.AdminRoleBIZ;
import com.lunchtasting.server.biz.AdminUserBIZ;
import com.lunchtasting.server.biz.CookieHandlingBIZ;
import com.lunchtasting.server.helper.Utils;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.po.lt.UserAdmin;
import com.lunchtasting.server.util.HttpUtil;
import com.lunchtasting.server.util.ValidatorHelper;
@Controller
public class AdminLoginController extends BaseController{
	
	//头条
	public static final String TOUTIAO_URL = "http://v.juhe.cn/toutiao/index?";
	public static final String KEY = "05689d14a641cf2326f54302ef613996";
	//星座
	public static final String XINGZUO_URL = "http://web.juhe.cn:8080/constellation/getAll?";
	public static final String XKEY = "685a831093e60609183a3aafbd3f49d7";
	//笑话
	public static final String XIAOHUA_URL = "http://japi.juhe.cn/joke/content/list.from?";
	public static final String HKEY = "52c5057ca755f98e4168d199c95fbbe8";
	
	@Autowired
	private AdminUserBIZ adminUserBIZ;
	@Autowired
	private CookieHandlingBIZ cookieHandlingBIZ;
	@Autowired
	private AdminRoleBIZ adminRoleBIZ;
	
	
	/**
	 * 登录
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/adminUserLogin")
	@ResponseBody
	public Model adminUserLogin(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model){
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		if(ValidatorHelper.isEmpty(account) || ValidatorHelper.isEmpty(password)){
		 	model.addAttribute("descript", "账号或密码错误!");
			model.addAttribute("result", 1);
			return model;
		}
		
		
		UserAdmin admin = adminUserBIZ.systemUsersLogin(account, password);
		if(null != admin){
			session.setAttribute(VariableHelper.LOGIN_SESSION_USER,admin);
//			session.setMaxInactiveInterval(3600);
			cookieHandlingBIZ.saveCookie(request, response, VariableHelper.LOGIN_COOKIE_USER, admin.getId()+"");
			
			model.addAttribute("descript", "登录成功!");
			model.addAttribute("result",0);
		}else{
			model.addAttribute("descript", "登录失败!");
			model.addAttribute("result", 1);
		}
		
		return model;
	}
	
	/**
	 * 前往登录页面
	 * @return
	 */
	@RequestMapping(value = "/")
	public String login(){
		return "/login";
	}
	
	/**
	 * 前往index页面
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/goToIndex")
	public String goIndex(Model model) throws UnsupportedEncodingException, ParseException{
		/*String name = "天秤座";
		String consName = URLEncoder.encode(name,"UTF-8");
		String url = TOUTIAO_URL+"type="+"&key="+KEY+"";
		String xurl = XINGZUO_URL+"consName="+consName+""+"&type=today"+"&key="+XKEY+"";
		String hurl = XIAOHUA_URL+"key="+HKEY+""+"&page=1"+"&pagesize=1"+"&time="+String.valueOf(System.currentTimeMillis() / 1000)+""+"&sort=desc";
		String result = HttpUtil.queryStringForGet(url);
		String xresult = HttpUtil.queryStringForGet(xurl);
		String hresult = HttpUtil.queryStringForGet(hurl);
		JSONObject jsonObject = JSON.parseObject(result);
		JSONObject jsonObject1 = JSON.parseObject(jsonObject.getString("result"));
		JSONArray jsonArray = JSON.parseArray(jsonObject1.getString("data"));
		model.addAttribute("jsonArray", jsonArray.subList(0, 15));
		
		JSONObject xjsonObject = JSON.parseObject(xresult);
		model.addAttribute("summary", xjsonObject.getString("summary"));
		model.addAttribute("all", xjsonObject.getString("all"));
		model.addAttribute("work", xjsonObject.getString("work"));
		model.addAttribute("health", xjsonObject.getString("health"));
		model.addAttribute("love", xjsonObject.getString("love"));
		model.addAttribute("money", xjsonObject.getString("money"));
		model.addAttribute("color", xjsonObject.getString("color"));
		model.addAttribute("number", xjsonObject.getString("number"));
		model.addAttribute("QFriend", xjsonObject.getString("QFriend"));
		
		JSONObject hjsonObject = JSON.parseObject(hresult);
		
		JSONObject jsonObject2 = JSON.parseObject(hjsonObject.getString("result"));
		JSONArray jsonArray1 = JSON.parseArray(jsonObject2.getString("data"));
		model.addAttribute("content", jsonArray1.getJSONObject(0).getString("content"));*/
		
//		model.addAttribute("thumbnail_pic_s", jsonArray.getJSONObject(0).getString("thumbnail_pic_s"));
//		model.addAttribute("title", jsonArray.getJSONObject(0).getString("title"));
		return "/index";
	}
	
	

	//用户退出
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response,Model model){
		HttpSession session = request.getSession();
		try{
			
			cookieHandlingBIZ.logout(request, response, VariableHelper.LOGIN_COOKIE_USER);			
			session.removeAttribute(VariableHelper.LOGIN_SESSION_USER);
			session.invalidate();
		}catch(Exception e){
			//logger.error("user logout is error："+e.getMessage(),e);
		}
		return "redirect:"+Utils.gotoWebRoot(request);
	}
	
	/**
	 * 根据权限显示菜单
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryMenuByRole")
	@ResponseBody
	public Model queryMenuByRole(HttpServletRequest request,Model model){
		String role = request.getParameter("role");
		String userId = request.getParameter("userId");
		HashMap map = new HashMap();
		map.put("roleId", role);
		map.put("userId", userId);
		List l = adminRoleBIZ.queryRoleList(map);
		if(null != l && l.size()>0){
			model.addAttribute("menu", adminRoleBIZ.queryRoleList(map));
			model.addAttribute("result",0);
		}else{
			model.addAttribute("descript", "权限信息有误!");
			model.addAttribute("result", 1);
		}
		return model;
	}
}
