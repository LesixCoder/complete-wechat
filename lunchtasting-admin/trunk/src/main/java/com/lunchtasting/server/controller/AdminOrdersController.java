package com.lunchtasting.server.controller;

import java.util.HashMap;
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
import com.lunchtasting.server.biz.AdminOrdersBIZ;
import com.lunchtasting.server.biz.AdminSellerBIZ;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.mvc.BaseController;

@Controller
public class AdminOrdersController extends BaseController{
	@Autowired
	private AdminOrdersBIZ adminOrdersBIZ;
	@Autowired
	private AdminSellerBIZ adminSellerBIZ;
	/**
	 * 查询订单列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryOrdersList")
	@ResponseBody
	public Object queryOrdersList(HttpServletRequest request){
		Map map = new HashMap();//返回结果
		String aoData = request.getParameter("aoData");
		String sellerId = request.getParameter("sellerId");
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
		if(sellerId != null && sellerId != "" && sellerId != "null"){
			mapCondition.put("sellerId", sellerId);
		}
//		else{
//			mapCondition.put("sellerId", !JSON.parseObject(jo.get(35).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(35).toString()).get("value").toString():"");
//		}
//		mapCondition.put("status", !JSON.parseObject(jo.get(36).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(36).toString()).get("value").toString():"");
		mapCondition.put("curPage", iDisplayStart);
		mapCondition.put("pageSize", VariableHelper.PAGE_SIZE);
		
		HashMap strmap = adminOrdersBIZ.queryOrders(mapCondition);
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
	@RequestMapping(value = "/goOrdersList")
	public String goOrdersList(Model model,HttpServletRequest request){
		String sellerId = request.getParameter("sellerId");
		model.addAttribute("sellerList", adminSellerBIZ.querySellerNotLimit(new HashMap()));
		model.addAttribute("sellerId", sellerId);
		return "orders/admin_orders_list";
	}
}
