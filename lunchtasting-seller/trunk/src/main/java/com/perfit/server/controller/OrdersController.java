package com.perfit.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.po.lt.OrdersList;
import com.lunchtasting.server.po.lt.Seller;
import com.lunchtasting.server.util.ValidatorHelper;
import com.lunchtasting.server.vo.OrdersListExhibition;
import com.perfit.server.biz.orders.OrdersBIZ;
import com.perfit.server.biz.orders.OrdersListBIZ;
import com.perfit.server.biz.orders.OrdersSettlementBIZ;
import com.perfit.server.dao.orders.OrdersDAO;
import com.perfit.server.helper.MapResult;
import com.perfit.server.helper.VariableHelper;

@Controller
public class OrdersController extends BaseController {
	@Autowired
	private OrdersBIZ ordersBIZ;
	@Autowired
	private OrdersSettlementBIZ ordersSettlementBIZ;
	@Autowired
	private OrdersListBIZ ordersListBIZ;
	@RequestMapping(value = "/goOrdersList")
	public String goOrdersList(Model model,HttpServletRequest request){
		String courseId = request.getParameter("courseId");
		if(ValidatorHelper.isNumber(courseId)){
			model.addAttribute("courseId", courseId);
		}
		return "orders/orders_list";
	}
	@RequestMapping(value = "/goOrdersSettlementList")
	public String goOrdersSettlementList(Model model){
		return "orders/orders_settlement_list";
	}
	@RequestMapping(value = "/goOpenCode")
	public String goOnpeCode(Model model){
		return "orders/seller";
	}
	
	@RequestMapping(value = "/getOrdersList")
	@ResponseBody
	public Object getOrdersList(HttpServletRequest request){
		HashMap mapCondition = new HashMap<String, Object>();//条件
		String sEcho = "";
		try{
			Seller seller = (Seller)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
			String aoData = request.getParameter("aoData");
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
			mapCondition.put("sellerId",seller.getId());
			mapCondition.put("status", !JSON.parseObject(jo.get(43).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(43).toString()).get("value").toString():"");
			mapCondition.put("courseId", !JSON.parseObject(jo.get(44).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(44).toString()).get("value").toString():"");
			mapCondition.put("curPage", iDisplayStart);
			mapCondition.put("pageSize", VariableHelper.PAGE_SIZE);

		}catch(Exception e){
			return MapResult.getPaging(1,null,null,0);
		} 
		List list  = ordersBIZ.queryOrdersList(mapCondition);
		int con = ordersBIZ.getOrdersCount(mapCondition);
		return MapResult.getPaging(0, list, sEcho,con);
	}
	
	@RequestMapping(value = "/getOrdersSettlementList")
	@ResponseBody
	public Object getOrdersSettlementList(HttpServletRequest request){
		HashMap mapCondition = new HashMap<String, Object>();//条件
		String sEcho = "";
		try{
			Seller seller = (Seller)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
			String aoData = request.getParameter("aoData");
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
			mapCondition.put("sellerId",seller.getId());
			mapCondition.put("curPage", iDisplayStart);
			mapCondition.put("pageSize", VariableHelper.PAGE_SIZE);
		}catch(Exception e){
			return MapResult.getPaging(1,null,null,0);
		} 
		List list  = ordersSettlementBIZ.getOrdersSettlementList(mapCondition);
		int con = ordersSettlementBIZ.getOrdersSettlementCount(mapCondition);
		return MapResult.getPaging(0, list, sEcho,con);
	}
	
/*	@RequestMapping(value = "/getOrdersListCode")
	@ResponseBody
	public Object getOrdersCodeList(HttpServletRequest request){
		HashMap remap = new HashMap();
		try{
			String code =request.getParameter("code");
			if(code==null || code.equals("") || code.equals("undefined")|| code.equals("null")){
				return MapResult.buildin(1,"输入code不合法", null);
			}
			Seller seller = (Seller)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
			OrdersListExhibition orderlist = ordersListBIZ.queryOrdersListByCode(code,seller.getId());
			if(orderlist==null){
				return MapResult.buildin(2,"消费码不正确。", null);
			}
			remap.put("orderlist",orderlist);	
		}catch (Exception e) {
			return MapResult.buildin(1,"服务器错误", null);
		}
		return MapResult.buildin(0,"有消费验证码信息",remap);
	}
	*/
	@RequestMapping(value = "/getOrdersListCode")
	@ResponseBody
	public Object getOrdersCodeList(HttpServletRequest request){
		HashMap remap = new HashMap();
		try{
			String code =request.getParameter("code");
			if(code==null || code.equals("") || code.equals("undefined")|| code.equals("null")){
				return MapResult.buildin(1,"输入消费码不合法。", null);
			}
			Seller seller = (Seller)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
			if(seller==null || seller.getId()==0){
				return MapResult.buildin(2,"商家登录失效，请重新登录！", null);
			}
			if(!ordersListBIZ.verifyOrdersList(code,seller.getId(),null)){
				return MapResult.buildin(1,"没有查找到消费码。", null);
			}
			List orders = ordersBIZ.queryOrdersListToSon(code,null);
			remap.put("ordersList",orders);	
		}catch (Exception e) {
			return MapResult.buildin(1,"服务器错误", null);
		}
		return MapResult.buildin(0,"查询成功",remap);
	}
	
	@RequestMapping(value = "/employCodeById")
	@ResponseBody
	public Object employCodeById(HttpServletRequest request){
		Map map = new HashMap();
		OrdersListExhibition ordersList = null;
		List orders =null;
		try {
			Seller seller = (Seller)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
			String id = request.getParameter("id");
			if(!ValidatorHelper.isNumber(id)){
				return MapResult.buildin(1,"使用失败，参数不合法", null);
			}
			if(seller==null || seller.getId()==0){
				return MapResult.buildin(1,"商家登录失效，请重新登录！", null);
			}
			ordersList =ordersListBIZ.queryOrdersListById(Long.parseLong(id), seller.getId());
			if(ordersList==null){
				return MapResult.buildin(1,"没有查找到消费码", null);
			}
			
			orders = ordersBIZ.queryOrdersListToSon(null,Long.parseLong(id));
			if(ordersList.getStatus()!=1){
				map.put("ordersList", ordersList);
				return MapResult.buildin(2,"消费码已失效", map);
			}
			if(ordersListBIZ.employCode(null, Long.parseLong(id))){
				ordersList.setStatus(2);
				map.put("ordersList", ordersList);
				return MapResult.buildin(0,"消费成功", map);
			}
		} catch (Exception e) {
			return MapResult.buildin(1,"使用失败", null);
		}
		return MapResult.buildin(1,"使用失败", null);
	}
	/**
	 * 根据code消费
	 * @param request
	 * @return chenchen
	 */
	@RequestMapping(value = "/employCodeByCode")
	@ResponseBody
	public Object employCodeByCode(HttpServletRequest request){
		Map map = new HashMap();
		List orders =null;
		OrdersListExhibition ordersList = null;
		try {
			Seller seller = (Seller)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
			String code = request.getParameter("code");
			if(code==null || code.equals("") || code.equals("undefined")|| code.equals("null")){
				return MapResult.buildin(1,"使用失败，参数不合法", map);
			}
			if(seller==null || seller.getId()==0){
				return MapResult.buildin(1,"商家登录失效，请重新登录！", null);
			}
			/**
			 * 根据验证码和商家ID查找orderList 判断
			 */
			ordersList =ordersListBIZ.queryOrdersListByCode(code, seller.getId());
			if(ordersList==null){
				return MapResult.buildin(1,"没有查找到消费码", map);
			}
			/**
			 * 存在，查询对应父级订单的所有信息
			 */
			orders = ordersBIZ.queryOrdersListToSon(code,null);
			/**
			 * 判断状态  
			 */
			if(ordersList.getStatus()!=1){
				map.put("ordersList", orders);
				return MapResult.buildin(2,"消费码已失效", map);
			}
	
			/**
			 * 直接消费 刷新
			 */
			if(ordersListBIZ.employCode(code, ordersList.getId())){
				/**
				 * 存在，查询对应父级订单的所有信息
				 */
				orders = ordersBIZ.queryOrdersListToSon(code,null);
				map.put("ordersList", orders);
				return MapResult.buildin(0,"消费成功", map);
			}
		} catch (Exception e) {
			map.put("ordersList", orders);
			return MapResult.buildin(1,"使用失败", map);
		}
		map.put("ordersList", orders);
		return MapResult.buildin(1,"使用失败", map);
	}
	
}
