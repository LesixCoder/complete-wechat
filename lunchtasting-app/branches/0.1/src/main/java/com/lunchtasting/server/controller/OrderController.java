package com.lunchtasting.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lunchtasting.server.biz.order.OrdersBIZ;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.po.lt.ReceiverAddress;

/**
 * 订单模块
 * @author xq
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {
	@Autowired
	private OrdersBIZ ordersBIZ;
	/**
	 * 会员查询地址
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryOrdersList"/*,method=RequestMethod.POST*/)
	@ResponseBody
	public Object queryOrdersList(HttpServletRequest request,HttpServletResponse response){
		Map map = new HashedMap();
		HashMap requestMap =  new  HashMap();
		try {
			requestMap.put("userId",Integer.parseInt(request.getParameter("userId")));
		} catch (Exception e) {
			// TODO: handle exception
			return MapResult.build(MapResult.CODE_SYS_ERROR, "参数存在问题", null, request);
		}
		try{
			List orderlist =ordersBIZ.queryOrdersList(requestMap);
			map.put("orderlist", orderlist);
			return MapResult.build(MapResult.CODE_SUCCESS, "成功！",map, request);
		}catch (Exception e) {
			// TODO: handle exception
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, null, request);
		}
	}
	@RequestMapping(value = "/submitOrder"/*,method=RequestMethod.POST*/)
	@ResponseBody
	public Object submitOrder(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map map = new HashedMap();
		int userId;
		String deliveredTime;
		String couponId;
		String orderLineList;
		int addrId;
		ReceiverAddress receiverAddress =null;
		try{
			//用户ID
			userId=Integer.valueOf(request.getParameter("userId"));
			//deliveredTime收货时间
			deliveredTime=request.getParameter("deliveredTime");
			//优惠码
			couponId=request.getParameter("couponId");
			//数量,菜品id,备注-数量,菜品id,备注
			orderLineList = request.getParameter("orderLineList");
			
			receiverAddress=new ReceiverAddress();
			
			//地址ID新增为0
			addrId = Integer.valueOf(request.getParameter("addrId"));
			receiverAddress.setAddrId(addrId);
			if(addrId==0){
				//收货者姓名
				receiverAddress.setReceiverName(request.getParameter("receiverName"));
				//地域Id 无限制为95
				receiverAddress.setAreaId(Integer.valueOf(request.getParameter("areaId")));
				//地址
				receiverAddress.setAddressDetail(request.getParameter("addressDetail"));
				//手机号
				receiverAddress.setTelephone(request.getParameter("telephone"));
			}
		}catch(Exception e){
			return MapResult.build(MapResult.CODE_SYS_ERROR, "参数存在问题", null, request);
		}
		if(userId==0 || deliveredTime==null || deliveredTime.equals("") 
				|| couponId==null || couponId.equals("")
			    	|| orderLineList==null||orderLineList.equals("")
				      ||	addrId==0){
			return MapResult.build(MapResult.CODE_SYS_ERROR, "参数存在问题", null, request);
		}
		try {
			HashMap rmap =ordersBIZ.submitOrder(userId,orderLineList, receiverAddress,deliveredTime,couponId);
			map.put("rmap",rmap);
			return MapResult.build(MapResult.CODE_SUCCESS, "成功！",map, request);
		} catch (Exception e) {
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, null, request);
		}
	}
}
