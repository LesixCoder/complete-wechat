package com.lunchtasting.server.controller;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lunchtasting.server.biz.AdminActivityBIZ;
import com.lunchtasting.server.biz.AdminActivityEnrollBIZ;
import com.lunchtasting.server.biz.AdminSellerBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.helper.GetXY;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.helper.Utils;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.po.lt.Activity;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.IdWorker;
@Controller
public class AdminController extends BaseController{
	@Autowired
	private AdminActivityBIZ adminActivityBIZ;
	@Autowired
	private AdminSellerBIZ adminSellerBIZ;
	@Autowired
	private AdminActivityEnrollBIZ adminActivityEnrollBIZ;
	
	/**
	 * 查询活动列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryActivityList")
	@ResponseBody
	public Object queryActivity(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		Map map = new HashMap();//返回结果
		String aoData = request.getParameter("aoData");
		String sEcho = "";
		int iDisplayStart =0;
		int iDisplayLength = 0;
		String activityDate = "";
		JSONArray jo = JSON.parseArray(aoData);
		if("sEcho".equals(JSON.parseObject(jo.get(0).toString()).get("name"))){
			sEcho = JSON.parseObject(jo.get(0).toString()).get("value").toString();
		}
		if("iDisplayStart".equals(JSON.parseObject(jo.get(3).toString()).get("name"))){
			iDisplayStart = Integer.parseInt(JSON.parseObject(jo.get(3).toString()).get("value").toString());
		}
		if("iDisplayLength".equals(JSON.parseObject(jo.get(4).toString()).get("name"))){
			iDisplayLength = Integer.parseInt(JSON.parseObject(jo.get(4).toString()).get("value").toString());
		}
		if("activityDate".equals(JSON.parseObject(jo.get(27).toString()).get("name"))){
			activityDate = JSON.parseObject(jo.get(27).toString()).get("value").toString();
		}
		HashMap mapCondition = new HashMap<String, Object>();//条件
		if(!"".equals(activityDate.trim())){
			mapCondition.put("beginTime", activityDate.split("[ - ]")[0]);
			mapCondition.put("endTime", activityDate.split("[ - ]")[2] +" 23:59:59");
		}else{
			mapCondition.put("beginTime", "");
			mapCondition.put("endTime", "");
		}
		mapCondition.put("name", !JSON.parseObject(jo.get(28).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(28).toString()).get("value").toString():"");
		mapCondition.put("flag", !JSON.parseObject(jo.get(29).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(29).toString()).get("value").toString():"");
		mapCondition.put("specificAddress", !JSON.parseObject(jo.get(30).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(30).toString()).get("value").toString():"");
		mapCondition.put("curPage", iDisplayStart);
		mapCondition.put("pageSize", VariableHelper.PAGE_SIZE);
		
		//HashMap strmap = JSON.parseObject((adminActivityBIZ.queryActivity(mapCondition)));
		HashMap strmap = adminActivityBIZ.queryActivity(mapCondition);
		if(Integer.parseInt(strmap.get("result").toString()) == 0){
			//JSONObject strPage = JSONObject.parseObject(strmap.get("page").toString());
			PageBean pageBean = (PageBean)strmap.get("page");
			map.put("result", 0);
			map.put("aaData", pageBean.getList());
			map.put("sEcho", sEcho);
			map.put("iTotalRecords", strmap.get("totalCount"));
			map.put("iTotalDisplayRecords", strmap.get("totalCount"));
		}
		mv.setViewName("/admin_activity_list");
		return map;
}
	
	/**
	 * 前往保存页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goActivitySave")
	public String goActivitySave(Model model,HttpServletRequest request){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String id = request.getParameter("id");
		if(id != null && id != "null" && id != ""){
			Activity ac =  adminActivityBIZ.queryActivityById(id);
			model.addAttribute("activity", ac);
			model.addAttribute("beginTime", df.format(ac.getBeginTime()));
			model.addAttribute("endTime", df.format(ac.getEndTime()));
			model.addAttribute("imgUrl", SysConfig.IMG_VISIT_URL+ac.getImgUrl());
		}
		model.addAttribute("sellerList", adminSellerBIZ.querySellerNotLimit(new HashMap()));
		return "/admin_activity_save";
	}
	
	/**
	 * 前往List页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goActivityList")
	public String goActivityList(){
		return "/admin_activity_list";
	}
	
	/**
	 * 保存活动
	 * @param model
	 * @param request
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 * @throws IOException 
	 */
	@RequestMapping(value = "/saveActivity")
	@ResponseBody
	public Model saveActivity(Model model,HttpServletRequest request) throws SQLException, ParseException, IOException{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String id = request.getParameter("id");
		Activity ac = new Activity();
		ac.setName(request.getParameter("name"));
		ac.setAddress(request.getParameter("address"));
		ac.setPrice(Double.parseDouble(request.getParameter("price")));
		ac.setImgText(request.getParameter("imgText"));
		ac.setBeginTime(df.parse(request.getParameter("activityDate").split(",")[0]));
		ac.setEndTime(df.parse(request.getParameter("activityDate").split(",")[1]));
		if(request.getParameter("sellerId") != null && request.getParameter("sellerId") != "" && request.getParameter("sellerId") != "null" && !request.getParameter("type").equals("2")){
			ac.setSellerId(Long.parseLong(request.getParameter("sellerId")));
		}
		ac.setContent(request.getParameter("content"));
		ac.setTag(request.getParameter("tag"));
		ac.setId(IdWorker.getId());
		ac.setType(Integer.parseInt(request.getParameter("type")));
		if(request.getParameter("enrollNum") != null && request.getParameter("enrollNum") != "" && request.getParameter("enrollNum") != "null"){
			ac.setEnrollNum(Integer.parseInt(request.getParameter("enrollNum")));
		}else{
			ac.setEnrollNum(0);
		}
		String imgText = request.getParameter("imgText");
		String url = request.getParameter("url");
		if(url != null && !"".equals(url) && url != "null"){
			if("http://ocjp9x6x9.bkt.clouddn.com/".equals(SysConfig.IMG_VISIT_URL)){
				ac.setImgUrl(url.substring(33, 63));
			}else if("http://image.mperfit.com/".equals(SysConfig.IMG_VISIT_URL)){
				ac.setImgUrl(url.substring(25, 55));
			}
		}
		
		/*if (url != null && url != "" && url != "null") {
			String imgName = QiNiuStorageHelper
					.getQiNiuImgName(QiNiuStorageHelper.ACTIVITY_IMG_PREFIX);
//			if (QiNiuStorageHelper.upload(imgName, request.getRealPath("")+url)){
//				ac.setImgUrl(imgName);
//			}
			
			if("/".equals(File.separator)){
				if (QiNiuStorageHelper.upload(imgName, request.getRealPath("")+url)){
					ac.setImgUrl(imgName);
				}
			}else if("\\".equals(File.separator)){
				if (QiNiuStorageHelper.upload(imgName, request.getRealPath("")+url.replaceAll("/", "\\\\"))){
					ac.setImgUrl(imgName);
				}
			}
			
		}*/
		ac.setImgText(imgText);
		/*if (imgText != null && imgText != "" && imgText != "null") {
			List<String> list = Utils.getImgSrc(imgText);
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					String s = "";
					String imgName = QiNiuStorageHelper
							.getQiNiuImgName(QiNiuStorageHelper.ACTIVITY_IMG_PREFIX);
//					if (i == 0) {
						if (QiNiuStorageHelper.urlUpload(imgName, list.get(i)) == true) {
//							ac.setImgUrl(imgName);
							s = imgText.replaceAll(list.get(i), SysConfig.IMG_VISIT_URL+imgName);
							imgText = s;
						}
//					} else if (QiNiuStorageHelper.urlUpload(imgName,
//							list.get(i)) == true) {
//						    s = imgText.replaceAll(list.get(i), SysConfig.IMG_VISIT_URL+imgName);
//						    imgText=s;
//					}
				}
				ac.setImgText(imgText);
			}else{
				ac.setImgText(imgText);
			}
		}*/
		
		if(request.getParameter("sort") != null && request.getParameter("sort") !="null" && request.getParameter("sort") != ""){
			ac.setSort(Integer.parseInt(request.getParameter("sort")));
		}else{
			ac.setSort(99);
		}
		ac.setMarketPrice(Double.parseDouble(request.getParameter("marketPrice")));
		
		if(request.getParameter("specificAddress") != null && request.getParameter("specificAddress") != "" && request.getParameter("specificAddress") != "null"){
			String specificAddress = request.getParameter("specificAddress");
			ac.setSpecificAddress(specificAddress);
			ac.setLongitude(Double.parseDouble(GetXY.getGaoDeXY(specificAddress.replaceAll("\\s*", "").trim()).split(",")[0]));
			ac.setLatitude(Double.parseDouble(GetXY.getGaoDeXY(specificAddress.replaceAll("\\s*", "").trim()).split(",")[1]));
		}
		
		try {
			if(id != "null" && id != null && id != ""){
				ac.setId(Long.parseLong(id));
				adminActivityBIZ.updateActivity(ac);
			}else{
				adminActivityBIZ.addActivity(ac);
			}
			model.addAttribute("flag", "success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
			return null;
		}
		return model;
	}
	
	/**
	 * 删除或发布或撤销
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteActivity",method=RequestMethod.POST)
	@ResponseBody
	public Model deleteActivity(Model model,HttpServletRequest request){
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		HashMap map = new HashMap();
		if(type.equals("del")){
			map.put("flag", 2);
		}else if(type.equals("pub")){
			map.put("flag", 0);
		}else if(type.equals("cel")){
			map.put("flag", 1);
		}
		map.put("id", id);
		try {
			adminActivityBIZ.deleteActivity(map);
			model.addAttribute("flag", "success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
		}
		return model;
	}
	
	/**
	 * 图文
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findActivityText")
	@ResponseBody
	public Model findText(Model model,HttpServletRequest request){
		String id = request.getParameter("id");
		try {
			model.addAttribute("imgText", adminActivityBIZ.queryActivityById(id).getImgText());
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
		}
		return model;
	}
	
	/**
	 * 报名详细
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryEnroll")
	@ResponseBody
	public Object queryEnroll(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		Map map = new HashMap();//返回结果
		String aoData = request.getParameter("aoData");
		String activityId = request.getParameter("activityId");
		String sEcho = "";
		int iDisplayStart =0;
		int iDisplayLength = 0;
		JSONArray jo = JSON.parseArray(aoData);
		if("sEcho".equals(JSON.parseObject(jo.get(0).toString()).get("name"))){
			sEcho = JSON.parseObject(jo.get(0).toString()).get("value").toString();
		}
		if("iDisplayStart".equals(JSON.parseObject(jo.get(3).toString()).get("name"))){
			iDisplayStart = Integer.parseInt(JSON.parseObject(jo.get(3).toString()).get("value").toString());
		}
		if("iDisplayLength".equals(JSON.parseObject(jo.get(4).toString()).get("name"))){
			iDisplayLength = Integer.parseInt(JSON.parseObject(jo.get(4).toString()).get("value").toString());
		}
		HashMap mapCondition = new HashMap<String, Object>();//条件
		mapCondition.put("activityId", activityId);
		mapCondition.put("curPage", iDisplayStart);
		mapCondition.put("pageSize", VariableHelper.PAGE_SIZE);
		
		//HashMap strmap = JSON.parseObject((adminActivityBIZ.queryActivity(mapCondition)));
		HashMap strmap = adminActivityEnrollBIZ.queryEnroll(mapCondition);
		if(Integer.parseInt(strmap.get("result").toString()) == 0){
			//JSONObject strPage = JSONObject.parseObject(strmap.get("page").toString());
			PageBean pageBean = (PageBean)strmap.get("page");
			map.put("result", 0);
			map.put("aaData", pageBean.getList());
			map.put("sEcho", sEcho);
			map.put("iTotalRecords", strmap.get("totalCount"));
			map.put("iTotalDisplayRecords", strmap.get("totalCount"));
		}
		return map;
}
	
	/**
	 * 前往报名详细页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goEnrollList")
	public String goEnrollList(Model model,HttpServletRequest request){
		String activityId = request.getParameter("activityId");
		model.addAttribute("activityId",activityId);
		return "/admin_enroll_list";
	}
	
	/**
	 * 置顶
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/topActivity",method=RequestMethod.POST)
	@ResponseBody
	public Model topActivity(Model model,HttpServletRequest request){
		String id = request.getParameter("id");
		HashMap map = new HashMap();
		map.put("id", id);
		try {
			adminActivityBIZ.topActivity(map);
			model.addAttribute("flag", "success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
		}
		return model;
	}
}
