package com.lunchtasting.server.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.lunchtasting.server.biz.AdminOrdersBIZ;
import com.lunchtasting.server.biz.AdminSellerBIZ;
import com.lunchtasting.server.biz.AdminSettlementBIZ;
import com.lunchtasting.server.biz.AdminWeekDayBIZ;
import com.lunchtasting.server.helper.DateUtils;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.po.lt.Orders;
import com.lunchtasting.server.po.lt.OrdersSettlement;
import com.lunchtasting.server.po.lt.WeekDay;
import com.lunchtasting.server.util.IdWorker;
@Controller
public class AdminSettlementController extends BaseController{
	@Autowired
	private AdminSellerBIZ adminSellerBIZ;
	@Autowired
	private AdminSettlementBIZ adminSettlementBIZ;
	@Autowired
	private AdminOrdersBIZ adminOrdersBIZ;
	@Autowired
	private AdminWeekDayBIZ adminWeekDayBIZ;
	/**
	 * 查询订单结算列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/querySettlementList")
	@ResponseBody
	public Object querySettlementList(HttpServletRequest request){
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
//		mapCondition.put("status", !JSON.parseObject(jo.get(55).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(55).toString()).get("value").toString():"");
		mapCondition.put("type", !JSON.parseObject(jo.get(55).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(55).toString()).get("value").toString():"");
		mapCondition.put("curPage", iDisplayStart);
		mapCondition.put("pageSize", VariableHelper.PAGE_SIZE);
		
		HashMap strmap = adminSettlementBIZ.querySettlement(mapCondition);
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
	@RequestMapping(value = "/goSettlementList")
	public String goSettlementList(Model model){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		HashMap map = new HashMap();
		map.put("wYear", calendar.get(Calendar.YEAR));
		map.put("wMonth", calendar.get(Calendar.MONTH) + 1 < 10 ? "0" + calendar.get(Calendar.MONTH) + 1 : calendar.get(Calendar.MONTH) + 1);
		List<WeekDay> daysList = adminWeekDayBIZ.queryDays(map);
		for(int i = 0,L = daysList.size();i < L; i ++){
			daysList.get(i).setNewDate(sdf.format(daysList.get(i).getwDate()));
		}
		model.addAttribute("sellerList", adminSellerBIZ.querySellerNotLimit(new HashMap()));
		model.addAttribute("daysList", daysList);
		return "settlement/admin_settlement_list";
	}
	
	/**
	 * 结算
	 * @param model
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/settlement")
	@ResponseBody
	public Model settlement(Model model,HttpServletRequest request) throws ParseException{
		OrdersSettlement os = new OrdersSettlement();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat df = new DecimalFormat("#.00");
		HashMap map = new HashMap();
		double subsidyPrice = 50;//优惠总价
		double servicePrice = 100;//抽成
		double playPrice = 0;//实际打款 originalPrice-servicePrice-channelPrice
		double profitPrice = 0;//盈利 servicePrice-subsidyPrice
		    //需要结算的商家
			List<OrdersSettlement> s = adminSettlementBIZ.querySettlementNotLimit(new HashMap());
			for(int i = 0,sLeng = s.size();i<sLeng;i++){
				map.put("sellerId", s.get(i).getsId());
				map.put("startDate", sdf.format(s.get(i).getSettlementDate()));
				map.put("endDate", sdf.format(s.get(i).getSettlementEndDate()));
				//订单
				List<Orders> l = adminOrdersBIZ.queryOrdersListForSettl(map);
				if(l.size()>0 && l != null){
					
					double channelPrice = 0;//渠道  微信0.6%支付宝0.6%
					double originalPrice = 0;//原价总价
					double payPrice = 0;//实际收款
					for(int j = 0,jLeng = l.size();j<jLeng;j++){
						originalPrice += l.get(j).getOriginalPrice();
						payPrice += l.get(j).getPayPrice();
//						if(l.get(j).getPayType() == 1){
//							channelPrice += Double.parseDouble(df.format(l.get(j).getPayPrice()*0.006));
//						}else if(l.get(j).getPayType() == 2){
//							channelPrice += Double.parseDouble(df.format(l.get(j).getPayPrice()*0.006));
//						}
						channelPrice += Double.parseDouble(df.format(l.get(j).getPayPrice()*0.006));
					}
					
					os.setId(IdWorker.getId());
					os.setSellerId(s.get(i).getsId());
					os.setBeginTime(s.get(i).getNextDay());
					os.setEndTime(s.get(i).getSettlementEndDate());
					os.setOriginalPrice(originalPrice);
					os.setPayPrice(payPrice);
					os.setChannelPrice(channelPrice);
					os.setServicePrice(servicePrice);
					os.setSubsidyPrice(subsidyPrice);
					os.setProfitPrice(servicePrice-subsidyPrice);
					os.setPlayPrice(originalPrice-servicePrice-channelPrice);
					
					try {
						adminSettlementBIZ.addSettlement(os);
						adminSettlementBIZ.updateSellerSett(s.get(i).getsId().toString(),sdf.format(s.get(i).getSettlementEndDate()));
						model.addAttribute("flag", "success");
					} catch (Exception e) {
						e.printStackTrace();
						model.addAttribute("flag", "fail");
						return null;
					}
				}else{
					os.setId(IdWorker.getId());
					os.setSellerId(s.get(i).getsId());
					os.setBeginTime(s.get(i).getNextDay());
					os.setEndTime(s.get(i).getSettlementEndDate());
					os.setOriginalPrice(0.0);
					os.setPayPrice(0.0);
					os.setChannelPrice(0.0);
					os.setServicePrice(0.0);
					os.setSubsidyPrice(0.0);
					os.setProfitPrice(0.0);
					os.setPlayPrice(0.0);
					
					try {
						adminSettlementBIZ.addSettlement(os);
						adminSettlementBIZ.updateSellerSett(s.get(i).getsId().toString(),sdf.format(s.get(i).getSettlementEndDate()));
						model.addAttribute("flag", "success");
					} catch (Exception e) {
						e.printStackTrace();
						model.addAttribute("flag", "fail");
						return null;
					}
					
				}
		}

		return model;
	}
	
	/**
	 *是否结算 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/ordersAlreadySettlement")
	@ResponseBody
	public Model ordersAlreadySettlement(Model model,HttpServletRequest request){
		String sellerId = request.getParameter("sellerId");
		String settDate = request.getParameter("settDate");
		HashMap map = new HashMap();
		if(!"".equals(settDate.trim())){
			map.put("startDate", settDate.split("[ - ]")[0]);
			map.put("endDate", settDate.split("[ - ]")[2] +" 23:59:59");
		}
		    map.put("sellerId", sellerId);
		try {
			if(adminSettlementBIZ.getById(map) == 0){
				model.addAttribute("flag", "y");
			}else{
				model.addAttribute("flag", "n");
				model.addAttribute("sellerName", adminSellerBIZ.querySellerById(sellerId).getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
			return null;
		}
		return model;
	}
	
	/**
	 * 上周是否结算
	 * @param model
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/ordersWeekAlreadySettlement")
	@ResponseBody
	public Model ordersWeekAlreadySettlement(Model model,HttpServletRequest request) throws ParseException{
		String sellerId = request.getParameter("sellerId");
		String settDate = request.getParameter("settDate");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		HashMap map = new HashMap();
		if(!"".equals(settDate.trim())){
			map.put("startDate", settDate.split("[ - ]")[0]);
		}
		    map.put("sellerId", sellerId);
		try {
			if(settDate.split("[ - ]")[0].equals(DateUtils.convertWeekByDate(adminSellerBIZ.querySellerById(sellerId).getCreateTime()))){
				if(adminSettlementBIZ.getSellCrea(map) == 0 ){
					model.addAttribute("flag", "y");
				}else{
					model.addAttribute("flag", "n");
				}
			}else{
			if(adminSettlementBIZ.getYearWeek(map) != 0){
				model.addAttribute("flag", "y");
			}else{
				model.addAttribute("flag", "n");
				model.addAttribute("sellerName", adminSellerBIZ.querySellerById(sellerId).getName());
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
	 * 保存备注
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveRemark")
	@ResponseBody
	public Model saveRemark(Model model,HttpServletRequest request){
		String settlementId = request.getParameter("settlementId");
		String remark = request.getParameter("remark");
		HashMap map = new HashMap();
		map.put("settlementId", settlementId);
		map.put("remark", remark);
		try {
			adminSettlementBIZ.saveRemark(map);
		    model.addAttribute("flag", "success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
			return null;
		}
		return model;
	}
}
