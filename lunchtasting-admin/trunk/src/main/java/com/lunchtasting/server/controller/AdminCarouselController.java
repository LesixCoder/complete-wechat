package com.lunchtasting.server.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lunchtasting.server.biz.AdminActivityBIZ;
import com.lunchtasting.server.biz.AdminArticleBIZ;
import com.lunchtasting.server.biz.AdminCarouselBIZ;
import com.lunchtasting.server.biz.AdminCourseBIZ;
import com.lunchtasting.server.biz.AdminSellerBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.po.lt.BannerIndex;
import com.lunchtasting.server.util.IdWorker;
@Controller
public class AdminCarouselController extends BaseController{
	@Autowired
	private AdminCarouselBIZ adminCarouselBIZ;
	@Autowired
	private AdminActivityBIZ adminActivityBIZ;
	@Autowired
	private AdminArticleBIZ adminArticleBIZ;
	@Autowired
	private AdminCourseBIZ adminCourseBIZ;
	@Autowired
	private AdminSellerBIZ adminSellerBIZ;
	
	/**
	 * 查询图片轮播列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryCarouselList")
	@ResponseBody
	public Object queryCarouselList(HttpServletRequest request){
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
		HashMap mapCondition = new HashMap<String, Object>();//条件
		mapCondition.put("curPage", iDisplayStart);
		mapCondition.put("pageSize", VariableHelper.PAGE_SIZE);
		
		HashMap strmap = adminCarouselBIZ.queryCarouselList(mapCondition);
		if(Integer.parseInt(strmap.get("result").toString()) == 0){
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
	 * 前往List页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goCarouselList")
	public String goCarouselList(){
		return "carousel/admin_carousel_list";
	}
	
	/**
	 * 前往保存页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goCarouselSave")
	public String goCarouselSave(Model model,HttpServletRequest request){
		String id = request.getParameter("id");
		if(id != null && id != "null" && id != ""){
			BannerIndex ac =  adminCarouselBIZ.queryCarouselById(id);
			model.addAttribute("carousel", ac);
			if(!"".equals(ac.getImgUrl())){
				model.addAttribute("imgUrl", SysConfig.IMG_VISIT_URL+ac.getImgUrl());
			}
		}
		model.addAttribute("activityList", adminActivityBIZ.queryActivityNotLimit());
		model.addAttribute("articleList", adminArticleBIZ.queryArticleNotLimit());
		model.addAttribute("courseList", adminCourseBIZ.queryCourseNotLimit());
		model.addAttribute("sellerList", adminSellerBIZ.querySellerNotLimit(new HashMap()));
		return "carousel/admin_carousel_save";
	}
	
	/**
	 * 删除或启用或禁用
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteCarousel",method=RequestMethod.POST)
	@ResponseBody
	public Model deleteCarousel(Model model,HttpServletRequest request){
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
			adminCarouselBIZ.deleteCarousel(map);
			model.addAttribute("flag", "success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
		}
		return model;
	}
	
	/**
	 * 保存图片轮播
	 * @param model
	 * @param request
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 * @throws IOException 
	 */
	@RequestMapping(value = "/saveCarousel")
	@ResponseBody
	public Model saveCarousel(Model model,HttpServletRequest request) throws SQLException, ParseException, IOException{
		String id = request.getParameter("id");
		BannerIndex ac = new BannerIndex();
		ac.setDepict(request.getParameter("depict"));
		ac.setUrl(request.getParameter("url"));
		ac.setId(IdWorker.getId());
		ac.setBizId(Long.parseLong(request.getParameter("bizId")));
		ac.setBizType(Integer.parseInt(request.getParameter("bizType")));
		ac.setIsClick(Integer.parseInt(request.getParameter("isClick")));
		String imgUrl = request.getParameter("imgUrl");
		if(imgUrl != null && !"".equals(imgUrl) && imgUrl != "null"){
			if("http://ocjp9x6x9.bkt.clouddn.com/".equals(SysConfig.IMG_VISIT_URL)){
				ac.setImgUrl(imgUrl.substring(33,63));
			}else if("http://image.mperfit.com/".equals(SysConfig.IMG_VISIT_URL)){
				ac.setImgUrl(imgUrl.substring(25,55));
			}
		}
		if(request.getParameter("sort") != null && request.getParameter("sort") !="null" && request.getParameter("sort") != ""){
			ac.setSort(Integer.parseInt(request.getParameter("sort")));
		}else{
			ac.setSort(99);
		}
		
		try {
			if(id != "null" && id != null && id != ""){
				ac.setId(Long.parseLong(id));
				adminCarouselBIZ.updateCarousel(ac);
			}else{
				adminCarouselBIZ.addCarousel(ac);
			}
			model.addAttribute("flag", "success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
			return null;
		}
		return model;
	}
	/*public static void main(String[] args) {
		String s = "http://ocjp9x6x9.bkt.clouddn.com/activity/20161207152949325.jpg";
		System.out.println(s.substring(33, 63));
	}*/
}
