package com.perfit.server.controller;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.po.lt.Activity;
import com.lunchtasting.server.po.lt.Seller;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.ValidatorHelper;
import com.perfit.server.biz.activity.ActivityBIZ;
import com.perfit.server.helper.GetXY;
import com.perfit.server.helper.MapResult;
import com.perfit.server.helper.PageBean;
import com.perfit.server.helper.Utils;
import com.perfit.server.helper.VariableHelper;
@Controller
public class ActivityController extends BaseController{
	@Autowired
	private ActivityBIZ adminActivityBIZ;
	/**
	 * 查询活动列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryActivityList")
	@ResponseBody
	public Object queryActivity(HttpServletRequest request){
		String sEcho = "";
		int iDisplayStart =0;
		int iDisplayLength = 0;
		String activityDate = "";
		HashMap mapCondition = new HashMap<String, Object>();//条件
		try{
			Seller seller = (Seller)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
			String aoData = request.getParameter("aoData");
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
			if("activityDate".equals(JSON.parseObject(jo.get(35).toString()).get("name"))){
				activityDate = JSON.parseObject(jo.get(35).toString()).get("value").toString();
			}
			if(!"".equals(activityDate.trim())){
				mapCondition.put("beginTime", activityDate.split("[ - ]")[0]);
				mapCondition.put("endTime", activityDate.split("[ - ]")[2] +" 23:59:59");
			}else{
				mapCondition.put("beginTime", "");
				mapCondition.put("endTime", "");
			}
			mapCondition.put("name", !JSON.parseObject(jo.get(36).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(36).toString()).get("value").toString():"");
			mapCondition.put("flag", !JSON.parseObject(jo.get(37).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(37).toString()).get("value").toString():"");
			mapCondition.put("specificAddress", !JSON.parseObject(jo.get(38).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(38).toString()).get("value").toString():"");
			mapCondition.put("curPage", iDisplayStart);
			mapCondition.put("pageSize", VariableHelper.PAGE_SIZE);
			//商家id
			mapCondition.put("sellerId", seller.getId());

		}catch (Exception e) {
			return MapResult.build(1,"参数存在问题",null);//参数存在问题 查询失败
		}
		try{
			HashMap strmap = adminActivityBIZ.queryActivity(mapCondition);
			List list =(List)strmap.get("activityList");
			if(ValidatorHelper.isNotEmpty(list)){
				for (Object object : list) {
					HashMap map = (HashMap)object;
					map.put("id", map.get("id").toString()+"");
				}
			}
			return MapResult.getPaging(0,(List)strmap.get("activityList"), sEcho, (Integer)strmap.get("totalCount"));
		}catch (Exception e) {
			return  MapResult.build(1,"服务器错误",null);//服务器报错 查询失败
		}
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
		if(ValidatorHelper.isNumber(id)){
			Activity ac =  adminActivityBIZ.queryActivityById(id);
			ac.setImgUrl(VariableHelper.IMG_URL_HEAD+ac.getImgUrl());
			model.addAttribute("activity", ac);
			model.addAttribute("beginTime", df.format(ac.getBeginTime()));
			model.addAttribute("endTime", df.format(ac.getEndTime()));	
		}
		return "/activity_save";
	}
	
	/**
	 * 前往List页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goActivityList")
	public String goActivityList(){
		return "/activity_list";
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
		String id;
		Activity ac = new Activity();
		try{
			Seller seller = (Seller)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			id = request.getParameter("id");
			ac.setName(request.getParameter("name"));
			ac.setAddress(request.getParameter("address"));
			ac.setPrice(Double.parseDouble(request.getParameter("price")));
			ac.setImgText(request.getParameter("imgText"));
			ac.setBeginTime(df.parse(request.getParameter("activityDate").split(",")[0]));
			ac.setEndTime(df.parse(request.getParameter("activityDate").split(",")[1]));
			ac.setSellerId(seller.getId());
			ac.setContent(request.getParameter("content"));
			ac.setTag(request.getParameter("tag"));
			String imgUrl = request.getParameter("imgUrl");
			String enrollNum = request.getParameter("enrollNum");
			/**
			 * 报名人数
			 */
			if(enrollNum!=null && !enrollNum.equals("null") && !enrollNum.equals("undefined") && !enrollNum.equals("")){
				ac.setEnrollNum(Integer.parseInt(enrollNum));
			}else{
				ac.setEnrollNum(0);
			}
			/**
			 * 首图
			 */
			if(imgUrl!=null && !imgUrl.equals("null") && !imgUrl.equals("undefined") && !imgUrl.equals("")){
				ac.setImgUrl(imgUrl.replaceAll(VariableHelper.IMG_URL_HEAD,""));
			}
			/**
			 * 排序
			 */
			if(request.getParameter("sort") != null && request.getParameter("sort") !="null" && request.getParameter("sort") != ""){
				ac.setSort(Integer.parseInt(request.getParameter("sort")));
			}else{
				ac.setSort(99);
			}
			/**
			 * 高德坐标
			 */
			if(request.getParameter("address") != null && request.getParameter("specificAddress") != "" && request.getParameter("specificAddress") != "null"){
				try{
					String specificAddress = request.getParameter("specificAddress");
					ac.setSpecificAddress(specificAddress);
					ac.setLongitude(Double.parseDouble(GetXY.getGaoDeXY(specificAddress).split(",")[0]));
					ac.setLatitude(Double.parseDouble(GetXY.getGaoDeXY(specificAddress).split(",")[1]));
				}catch (Exception e) {
					// TODO: handle exception
					model.addAttribute("ditu", "no");
				}
			}
			ac.setMarketPrice(Double.parseDouble(request.getParameter("marketPrice")));
			ac.setSpecificAddress(request.getParameter("specificAddress"));
		}catch (Exception e){
			model.addAttribute("flag", "fail");
			return model;
		}
		try {
			if(!id .equals("null") && id != null && !id.equals("")){
				ac.setId(Long.parseLong(id));
				adminActivityBIZ.updateActivity(ac);
			}else{
				adminActivityBIZ.addActivity(ac);
			}
			model.addAttribute("flag", "success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
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
	public Object deleteActivity(HttpServletRequest request){
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		HashMap map = new HashMap();
		HashMap newmap=new HashMap();
		if(type.equals("del")){
			map.put("flag", 2);
		}else if(type.equals("pub")){
			map.put("flag", 0);
		}else if(type.equals("cel")){
			map.put("flag", 1);
		}
		map.put("id", id);
		try {
			if(adminActivityBIZ.deleteActivity(map)){
				newmap.put("flag", "success");
			}else{
				newmap.put("flag", "fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			newmap.put("flag", "fail");
		}
		return newmap;
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
			Activity sss = adminActivityBIZ.queryActivityById(id);
			model.addAttribute("imgText",sss.getImgText());
			model.addAttribute("imgUrl",VariableHelper.IMG_URL_HEAD+sss.getImgUrl());
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
		}
		return model;
	}
}
