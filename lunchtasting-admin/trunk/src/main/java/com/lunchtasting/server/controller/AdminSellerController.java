package com.lunchtasting.server.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lunchtasting.server.biz.AdminAreaBIZ;
import com.lunchtasting.server.biz.AdminSellerBIZ;
import com.lunchtasting.server.biz.AdminWeekDayBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.helper.GetXY;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.helper.Utils;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.po.lt.Seller;
import com.lunchtasting.server.po.lt.WeekDay;
import com.lunchtasting.server.util.IdWorker;

@Controller
public class AdminSellerController extends BaseController{
	
	@Autowired
	private AdminSellerBIZ adminSellerBIZ;
	@Autowired
	private AdminAreaBIZ adminAreaBIZ;
	@Autowired
	private AdminWeekDayBIZ adminWeekDayBIZ;
	
	/**
	 * 查询商家列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/querySellerList")
	@ResponseBody
	public Object querySeller(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		Map map = new HashMap();//返回结果
		String aoData = request.getParameter("aoData");
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
		mapCondition.put("name", !JSON.parseObject(jo.get(31).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(31).toString()).get("value").toString():"");
		mapCondition.put("phone", !JSON.parseObject(jo.get(32).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(32).toString()).get("value").toString():"");
		mapCondition.put("specificAddress", !JSON.parseObject(jo.get(33).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(33).toString()).get("value").toString():"");
		mapCondition.put("flag", !JSON.parseObject(jo.get(34).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(34).toString()).get("value").toString():"");
		mapCondition.put("curPage", iDisplayStart);
		mapCondition.put("pageSize", VariableHelper.PAGE_SIZE);
		
		HashMap strmap = adminSellerBIZ.querySeller(mapCondition);
		if(Integer.parseInt(strmap.get("result").toString()) == 0){
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
	 * 前往List页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goSellerList")
	public String goSellerList(){
		return "seller/admin_seller_list";
	}
	
	/**
	 * 前往保存页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goSellerSave")
	public String goSellerSave(Model model,HttpServletRequest request){
		String id = request.getParameter("id");
		if(id != null && id != "null" && !"".equals(id)){
			Seller ac =  adminSellerBIZ.querySellerById(id);
			model.addAttribute("seller", ac);
			String s = "<img src=";
			String t = " alt='' />";
			StringBuffer sbf = new StringBuffer();
			if(ac.getImgArray() != null && !"".equals(ac.getImgArray()) && ac.getImgArray() != "null"){
				StringBuffer sb = null;
				String[] img = ac.getImgArray().split(",");
				for(int i = 0; i < img.length; i ++){
					if("http://ocjp9x6x9.bkt.clouddn.com/".equals(SysConfig.IMG_VISIT_URL)){
						sb = new StringBuffer(img[i]).insert(0, s+SysConfig.IMG_VISIT_URL).insert(70, t);
					}else if("http://image.mperfit.com/".equals(SysConfig.IMG_VISIT_URL)){
						sb = new StringBuffer(img[i]).insert(0, s+SysConfig.IMG_VISIT_URL).insert(62, t);
					}
//					sb = new StringBuffer(img[i]).insert(0, s+SysConfig.IMG_VISIT_URL).insert(62, t);
//					sb = new StringBuffer(img[i]).insert(0, s+SysConfig.IMG_VISIT_URL).insert(70, t);
					sbf.append(sb);
				}
				model.addAttribute("imgArray",sbf.toString());
			}
		}
		model.addAttribute("areaList", adminAreaBIZ.queryArea());
		return "seller/admin_seller_save";
	}
	
	/**
	 * 保存商家
	 * @param model
	 * @param request
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/saveSeller")
	@ResponseBody
	public Model saveSeller(Model model,HttpServletRequest request) throws SQLException, ParseException, IOException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String id = request.getParameter("id");
		Seller ac = new Seller();
		ac.setName(request.getParameter("name"));
		ac.setIntroduction(request.getParameter("introduction"));
		ac.setAreaId(Long.parseLong(request.getParameter("areaId")));
		ac.setTag(request.getParameter("tag"));
		ac.setAccount(request.getParameter("account"));
		ac.setPassword(request.getParameter("password"));
		ac.setPhone(request.getParameter("phone"));
		ac.setBusinessHours(request.getParameter("businessHours"));
		ac.setAddress(request.getParameter("address"));
		String imgArray = request.getParameter("imgArray");
		String account = request.getParameter("account");
		
		if(request.getParameter("specificAddress") != null && request.getParameter("specificAddress") != "" && request.getParameter("specificAddress") != "null"){
			String specificAddress = request.getParameter("specificAddress");
			ac.setSpecificAddress(specificAddress);
			ac.setLongitude(Double.parseDouble(GetXY.getGaoDeXY(specificAddress.replaceAll("\\s*", "").trim()).split(",")[0]));
			ac.setLatitude(Double.parseDouble(GetXY.getGaoDeXY(specificAddress.replaceAll("\\s*", "").trim()).split(",")[1]));
		}
		
		String s = "";
		if (imgArray != null && imgArray != "" && imgArray != "null") {
			List<String> list = Utils.getImgSrc(imgArray);
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					if("http://ocjp9x6x9.bkt.clouddn.com/".equals(SysConfig.IMG_VISIT_URL)){
					if (i == 0) {
							ac.setImgUrl(list.get(0).substring(33,61));
					}
					s += list.get(i).substring(33,61)+",";
					}else if("http://image.mperfit.com/".equals(SysConfig.IMG_VISIT_URL)){
						if (i == 0) {
								ac.setImgUrl(list.get(0).substring(25,53));
						}
						s += list.get(i).substring(25,53)+",";
					}
				}
				ac.setImgArray(s.substring(0, s.length()-1));
			}
		}
		
		List<WeekDay> weekDayList = adminWeekDayBIZ.getWNextDay(sdf.format(new Date()));
		
		try {
			if (id != "null" && id != null && !"".equals(id)) {
				ac.setId(Long.parseLong(id));
				if(!"".equals(account) && account != null){
					if (account.equals(adminSellerBIZ.querySellerById(id)
							.getAccount())) {
						adminSellerBIZ.updateSeller(ac);
						model.addAttribute("flag", "success");
					} else {
						if (adminSellerBIZ.selectSellerExist(account) == 0) {
							adminSellerBIZ.updateSeller(ac);
							model.addAttribute("flag", "success");
						} else {
							model.addAttribute("flag", "n");
						}
					}
				}else{
					adminSellerBIZ.updateSeller(ac);
					model.addAttribute("flag", "success");
				}
			} else {
				ac.setId(IdWorker.getId());
				if(!"".equals(account) && account != null){
					if (adminSellerBIZ.selectSellerExist(account) == 0) {
						if(weekDayList.get(0).getwFlag() == 0){
							ac.setSettlementDate(new Date());
						}else{
							ac.setSettlementDate(weekDayList.get(0).getwLastDay());
						}
						adminSellerBIZ.addSeller(ac);
						model.addAttribute("flag", "success");
					} else {
						model.addAttribute("flag", "n");
					}
				}else{
					adminSellerBIZ.addSeller(ac);
					model.addAttribute("flag", "success");
				}
			}
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
	@RequestMapping(value = "/deleteSeller")
	@ResponseBody
	public Model deleteSeller(Model model,HttpServletRequest request){
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
			adminSellerBIZ.deleteSeller(map);
			model.addAttribute("flag", "success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
		}
		return model;
	}
	
	/**
	 * 查询账户是否存在
	 * @param model
	 * @param request
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/selectSellerExist")
	@ResponseBody
	public Model selectSellerExist(Model model, HttpServletRequest request)
			throws SQLException {
		String account = request.getParameter("account");
		String id = request.getParameter("id");
		try {
			if (id != "null" && id != null && id != "") {
				if (account.equals(adminSellerBIZ.querySellerById(id)
						.getAccount())) {
					model.addAttribute("flag", "y");
				} else {
					if (adminSellerBIZ.selectSellerExist(account) == 0) {
						model.addAttribute("flag", "y");
					} else {
						model.addAttribute("flag", "n");
					}
				}
			} else {
				if (adminSellerBIZ.selectSellerExist(account) == 0) {
					model.addAttribute("flag", "y");
				} else {
					model.addAttribute("flag", "n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
			return null;
		}
		return model;
	}
	
	/**
	 * 下一个结算日期
	 * @param model
	 * @param request
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/getSellerNextSettDate")
	@ResponseBody
	public Model getSellerNextSettDate(Model model, HttpServletRequest request)
			throws SQLException {
		String id = request.getParameter("id");
		try {
			if (id != "null" && id != null && id != "") {
				Seller s = adminSellerBIZ.getSellerNextSettDate(id);
				model.addAttribute("settlementEndDate", s.getSettlementEndDate());
				model.addAttribute("flag", "success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
			return model;
		}
		return model;
	}
	
	/*public static void main(String[] args) {
		String s = "<img src=";
		String t = " alt='' />";
		StringBuffer sbf = new StringBuffer();
		StringBuffer sb = null;
		sb = new StringBuffer("seller/20161021122222716.jpg").insert(0, s+SysConfig.IMG_VISIT_URL).insert(62, t);
		sbf.append(sb);
//		System.out.println(sbf.toString());
		System.out.println("http://ocjp9x6x9.bkt.clouddn.com/".equals(SysConfig.IMG_VISIT_URL));
	}*/
}
