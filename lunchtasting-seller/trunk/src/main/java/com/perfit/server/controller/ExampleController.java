package com.perfit.server.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.po.lt.OrdersList;
import com.lunchtasting.server.po.lt.Seller;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.perfit.server.biz.activity.ActivityBIZ;
import com.perfit.server.biz.activity.ActivityEnrollBIZ;
import com.perfit.server.biz.course.CourseBIZ;
import com.perfit.server.biz.orders.OrdersListBIZ;
import com.perfit.server.biz.orders.OrdersSettlementBIZ;
import com.perfit.server.biz.seller.impl.SellerBIZImpl;
import com.perfit.server.dao.orders.OrdersSettlementDAO;
import com.perfit.server.helper.MapResult;
import com.perfit.server.helper.VariableHelper;
  import java.util.*;
  import java.io.* ;
  import java.text.SimpleDateFormat ;
  import org.apache.commons.fileupload.* ;
  import org.apache.commons.fileupload.disk.* ;
  import org.apache.commons.fileupload.servlet.* ;
import org.json.simple.* ;


/**
 * 例子
 * @author xq
 *
 */
@Controller
public class ExampleController extends BaseController {
	
	@Autowired 
	private SellerBIZImpl sellerBIZImpl;
	@Autowired 
	private ActivityEnrollBIZ activityEnrollBIZ;
	@Autowired 
	private CourseBIZ courseBIZ;
	@Autowired 
	private ActivityBIZ activityBIZ;
	@Autowired 
	private OrdersSettlementBIZ ordersSettlementBIZ;
	@Autowired
	private OrdersListBIZ ordersListBIZ;
	/**
	 * 这里返回普通页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/test999")
	public String test1(Model model){
		
		model.addAttribute("msg", "123456");
		return "/index";

	}
	
	
/*	*//**
	 * 这里用于ajax异步请求 返回json数据   表明@ResponseBody标签 则返回json
	 * @param request
	 * @param response
	 * @return
	 *//*
	@RequestMapping(value = "/test2")
	@ResponseBody
	public Object test2(HttpServletRequest request,HttpServletResponse response){
		
		return userBIZ.getUserByPhone("18747363122");
	}
	
	
	*//**
	 * 返回user集合
	 * @param request
	 * @param response
	 * @return
	 *//*
	@RequestMapping(value = "/test3")
	@ResponseBody
	public Object test3(HttpServletRequest request,HttpServletResponse response){
		HashMap map = new HashMap();
		return activityBIZ.queryList(map);
	}
	
	*//**
	 * 返回user集合
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 *//*
	@RequestMapping(value = "/test4")
	@ResponseBody
	public Object test4(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HashMap map = new HashMap();
		//return activityBIZ.queryList(map);
		return quartzBIZ.getAllJob();
		//return quartzBIZ.getRunningJob();
	}
	
	@RequestMapping(value = "/test5")
	@ResponseBody
	public Object test5(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ScheduleJob job2 = new ScheduleJob();
        job2.setJobName("test0103");
        job2.setJobGroup("test");
        job2.setIsConcurrent("1");
        job2.setJobStatus("1");
        job2.setMethodName("hehehe3");
        job2.setCronExpression("0 15 17 29 8 ? 2016");
        job2.setBeanClass("com.lunchtasting.server.quartz.QuartzJobInit");
        if(scheduleJobBIZ.addScheduleJob(job2)){
        	 quartzBIZ.addJob(job2);
        }
		return job2;
	}
	*//**
	 * 测试URL
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/test7")
	@ResponseBody
	public Object test7(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String sj2 = request.getServletPath();
		String z = request.getQueryString();
		if(z==null||z.equals("")){
			return request.getContextPath()+request.getServletPath();
		}
		return request.getContextPath()+request.getServletPath()+"?"+z;
	}
/*	*//**
	 * 登录测试
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping(value = "/test8")
	@ResponseBody
	public Object test8(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String name  =request.getParameter("name");
		String password = request.getParameter("password");
		if(name==null || password==null ||name.equals("") || password.equals("")){
			return MapResult.build(1,"用户名或者密码输入不正规");
		}
		try{
			if(sellerBIZImpl.sellerUsersLogin(name, password)){
				return MapResult.build(0,"登录成功");
			}else{
				return MapResult.build(1,"用户名或者密码错误！");
			}
		}catch (Exception e) {
			return MapResult.build(1,"服务器错误");
		}
	}*/
	/**
	 * 	
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/test9")
	@ResponseBody
	public Object test9(HttpServletRequest request,HttpServletResponse response) throws Exception{
			return activityBIZ.queryActivityById("1");
	}
	/**
	 * 	测试session
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/test10")
	@ResponseBody
	public Object tes10(HttpServletRequest request,HttpServletResponse response) throws Exception{
			Seller seller = (Seller) request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
			seller = sellerBIZImpl.sellerById(seller.getId());
			return seller;
	}
	/**
	 * 	测试商家权限对活动
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/authenticationUser")
	@ResponseBody
	public Object tes11(HttpServletRequest request,HttpServletResponse response) throws Exception{
			
			return activityBIZ.authenticationUser(1, 1);
	}
	/**
	 * 	测试商家权限对活动
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tes12")
	@ResponseBody
	public Object tes12(HttpServletRequest request,HttpServletResponse response){
		 String[] value=new String[1];
		try{
			Map map=request.getParameterMap();
			Set keSet=map.entrySet();
			for(Iterator itr=keSet.iterator();itr.hasNext();){
			Map.Entry me=(Map.Entry)itr.next();
		   Object ok=me.getKey(); //获取参数名
		   Object ov=me.getValue(); //获取参数值
		  
		   if(ov instanceof String[]){
		    value=(String[])ov;
		   }else{
		    value[0]=ov.toString();
		   }
		   for(int k=0;k <value.length;k++){
		    System.out.println(ok+"="+value[k]);
		   }	
		  }
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
			return value;
	}
	
	/**
	 * 	测试活动报名列表 
	 * @param request
	 * @param response
	 * @return
	 * @throws FileUploadException 
	 * @throws Exception
	 */
	@RequestMapping(value = "/tes13")
	public void tes13(HttpServletRequest request,HttpServletResponse response) throws Exception{
		PrintWriter out = response.getWriter();
		
		String type = /*request.getParameter("type")*/"seller";

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
					}

					if (!QiNiuStorageHelper.byteUpload(imgName, fileItem.get())) {
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
	/**
	 * 	测试活动报名列表 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/test15")
	@ResponseBody
	public Object tes14(HttpServletRequest request,HttpServletResponse response){
		Seller ss = sellerBIZImpl.sellerById(Long.parseLong(request.getParameter("id")));
		return ss;
	}
	
	/**
	 * 测试课程列表
	 */
	@RequestMapping(value = "/test16")
	@ResponseBody
	public Object tes15(HttpServletRequest request,HttpServletResponse response){
		HashMap map =new HashMap();
		List ss = courseBIZ.queryCourseList(map);
		int con = courseBIZ.queryCourseCount(map);
		return con;
	}
	
	/**
	 * 测试jies 列表
	 */
	@RequestMapping(value = "/test17")
	@ResponseBody
	public Object ordersSettlementDAO(HttpServletRequest request,HttpServletResponse response){
		HashMap map =new HashMap();
		map.put("sellerId", "778479287597531136");
		List<OrdersList> oil = ordersListBIZ.queryOrdersListList(map);
		//List ss = ordersSettlementBIZ.getOrdersSettlementList(map);
	//	int con = ordersSettlementBIZ.getOrdersSettlementCount(map);
		//System.out.println(con);
		return oil;
	}
	/**
	 * 测试
	 */
	@RequestMapping(value = "/test18")
	@ResponseBody
	public Object test18(HttpServletRequest request,HttpServletResponse response){
		System.out.println("进入控制层");
		return ordersListBIZ.employCode("120", Long.parseLong("110110110"));
	}
	
	/**
	 * 测试
	 */
	@RequestMapping(value = "/test19")
	@ResponseBody
	public Object test19(HttpServletRequest request,HttpServletResponse response){
		System.out.println("进入控制层");
		HashMap map = new HashMap();
		map.put("sellerId", "778479287597531136");
		map.put("code", "110");
		return ordersListBIZ.queryOrdersListOne(map);
	}
}
