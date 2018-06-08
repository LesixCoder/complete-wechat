//package com.lunchtasting.server.controller;
//
//import java.util.HashMap;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.lunchtasting.server.biz.activity.ActivityBIZ;
//import com.lunchtasting.server.biz.activity.impl.ActivityBIZImpl;
//import com.lunchtasting.server.biz.article.ArticleBIZ;
//import com.lunchtasting.server.biz.user.UserBIZ;
//import com.lunchtasting.server.mvc.BaseController;
//import com.lunchtasting.server.po.lt.Activity;
//import com.lunchtasting.server.po.lt.Article;
//import com.lunchtasting.server.util.DateUtils;
//
//
//
///**
// * 首页list
// * @author chenchen
// *
// */
//@Controller
//public class ListController extends BaseController {
//	
//	@Autowired
//	private UserBIZ userBIZ;
//	@Autowired
//	private ActivityBIZ activityBIZ;
//	@Autowired
//	private ArticleBIZ articleBIZ;
//	
//	
//	/**
//	 * 首页（活动文章列表）
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value = "/index")
//	public String getList(Model model,HttpServletRequest request,HttpServletResponse response){
//		HashMap map = new HashMap();
//		try {
//			map.put("TABLEtype",Integer.parseInt(request.getParameter("type")));
//		} catch (Exception e) {
//			map.put("TABLEtype",0);
//		}
//		List list = activityBIZ.queryList(map);
//		model.addAttribute("list",list);
//		return "/index";
//	}
//	
//	/**
//	 * 活动详情
//	 * @param id
//	 * @param model
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "{id}")
//	public String activityDetail(@PathVariable("id") String id,Model model,HttpServletRequest request){
//		return "";
//	}
//	
//	/**
//	 * 文章详情
//	 * @param id
//	 * @param model
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "{id}")
//	public String articleDetail(@PathVariable("id") String id,Model model,HttpServletRequest request){
//		return "";
//	}
//	
//	
////	/**
////	 * 查询详细 前往详细页面
////	 * @param request
////	 * @param response
////	 * @return
////	 */
////	@RequestMapping(value = "/getOne")
////	public String getOne(Model model,HttpServletRequest request,HttpServletResponse response){
////		int k;
////		
////		Long id = null;
////		//取值
////		try {
////			id = Long.parseLong(request.getParameter("id"));
////			k=Integer.parseInt(request.getParameter("type"));
////		} catch (Exception e) {
////			k=0;
////		}
////		//判断类型
////		//文章
////		if(k==2){
////			Article article = articleBIZ.queryArticleById(id);
////			//查询失败
////			if(article==null){
////				return null;
////			}
////			//查询成功
////			model.addAttribute("bean",article);
////			return "/Artdetails";
////		//活动
////		}else if(k==1){  
////			int i =activityBIZ.queryApplyNum(id);
////			Activity activity = activityBIZ.queryActivityById(id);
////			if(activity==null){
////				return null;
////			}
////			model.addAttribute("begin_time",DateUtils.datetotai(activity.getBeginTime()));
////			model.addAttribute("end_time",DateUtils.datetotai(activity.getEndTime()));
////			model.addAttribute("bean",activity);
////			model.addAttribute("applyNum",i);
////		}else{
////			return null;
////		}
////		return "/Actdetails";
////	}
////	/**
////	 * 活动报名
////	 * @param request
////	 * @param response
////	 * @return
////	 */
////	@RequestMapping(value = "/test5")
////	@ResponseBody
////	public Object activityRegister(HttpServletRequest request,HttpServletResponse response){
////		HashMap map = new HashMap();
////		map.put("userId",new Long(123));
////		map.put("activityId",new Long(321));
////		map.put("name","zhaoyi");
////		map.put("phone","17744595416");
////		return activityBIZ.addActivityenroll(map);
////	}
//}
