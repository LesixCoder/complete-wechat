package com.lunchtasting.server.biz.order.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.lunchtasting.server.biz.order.OrdersBIZ;
import com.lunchtasting.server.dao.meals.MealsDAO;
import com.lunchtasting.server.dao.order.OrdersDAO;
import com.lunchtasting.server.dao.receiverAddress.ReceiverAddressDAO;
import com.lunchtasting.server.po.lt.Meals;
import com.lunchtasting.server.po.lt.OrderLines;
import com.lunchtasting.server.po.lt.Orders;
import com.lunchtasting.server.po.lt.ReceiverAddress;

@Service 
public class OrdersBIZImpl implements OrdersBIZ{
	@Autowired
	private OrdersDAO ordersDAO;
	
	@Autowired
	private MealsDAO mealsDAO;
	
	@Autowired
	private ReceiverAddressDAO receiverAddressDAO;

	@Override
	public List queryOrdersList(HashMap map) {
		// TODO Auto-generated method stub
		return ordersDAO.queryOrdersList(map);
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public synchronized HashMap submitOrder(int userId, String orderLineList,
			ReceiverAddress receiverAddress, String deliveredTime,
			String couponId) {
		//数量、菜品ID、商家ID
		//返回值
		HashMap rmap = new HashMap();
		int quantity,mealId;
		//单价、总价、小计、配送费
		double price=0.0,total=0.0,subtotal=0.0,packageFee=0.0;//餐盒费逻辑有问题
	    try{
	    	String[] dataArray = orderLineList.split("-");
	    	Orders orders = new Orders();
	    	List<OrderLines> orderLinesList = new LinkedList<OrderLines>();
	    	for(int i = 0;i < dataArray.length;i++){
	    		//条件
	    		HashMap<String,Object> map = new HashMap<String,Object>();
	    		//前段数据
	    		String[] orderLinesArray = dataArray[i].split(",");
	    		//菜品编号
	    		mealId = Integer.parseInt(orderLinesArray[1]);
	    		//菜品类
	    		map.put("mealId", mealId);
	    		map.put("deliveryDate", deliveredTime.substring(0, 10));
	    		Meals meals = mealsDAO.queryMealByIdTo(map); //===============得到菜品
    			//单价
	    		price = meals.getPrice();
	    		//数量
	    		quantity = Integer.parseInt(orderLinesArray[0]);
	    		//餐盒费
	    		//packageFee = CommonConstants.PACKAGE_FEE * quantity;
	    		//小计
	    		subtotal = price * quantity + packageFee;
	    		//总价格
	    		total = total + subtotal;
	    		//总数量
	    		int stock = meals.getStock();
	    		//已定数量
	    		int buyNum = meals.getBuyNum();
	    		
	    		if(buyNum >= stock){
	    			rmap.put("result","2");
	    		    rmap.put("descript","您定的【"+meals.getMealName()+"】已经卖光了哦，请点别的菜吧！");
	    		    return rmap;
	    		} else if((buyNum + quantity) > stock){
	    			rmap.put("result","2");
	    			rmap.put("descript","您定的【"+meals.getMealName()+"】只剩"+(stock-buyNum)+"个了哦！");
	    		    return rmap;
	    		}
	    		
	    		OrderLines orderLines = new OrderLines();
	    		if(!"null".equals(orderLinesArray[2])){
	    			orderLines.setComment(orderLinesArray[2]);
	    		}
	    		orderLines.setMealId(mealId);
	    		orderLines.setPrice(price);
	    		orderLines.setSubtotal(subtotal);
	    		orderLines.setPackageFee(packageFee);
	    		orderLines.setQuantity(quantity);
	    		orderLines.setDeliveryDate(deliveredTime.substring(0, 10));
	    		orderLinesList.add(i, orderLines);
	    	}
	    	//添加新地址
	    	if(receiverAddress.getAddrId() == 0){
	    		receiverAddress.setUserId(userId);
	    		receiverAddress.setAddressDefault(1);
	    		int flag = receiverAddressDAO.addReceiverAddress(receiverAddress);
	    		//修改不是默认地址
	    		if(flag == 1){
	    			ReceiverAddress addr = new ReceiverAddress();
	    			addr.setAddressDefault(0);
	    			addr.setUserId(userId);
	    			addr.setAddrId(receiverAddress.getAddrId());
	    			receiverAddressDAO.updateDefaultAddress(addr);
	    		}
    		}
	    	//增加配送费  低于100加10元
	    	if(total < 100){
	    		total = total + 10;
	    	}
	    	orders.setTotal(total);
	    	orders.setUserId(userId);
	    	orders.setAddrId(receiverAddress.getAddrId());
	    	if(!"null".equals(deliveredTime)){
	    		orders.setExpectedDelivery(deliveredTime);
	    		orders.setDeliveryDate(deliveredTime.substring(0, 10));
	    	}
	    	boolean t = couponId.equals("null");
	    	if(couponId!=null && !"".equals(couponId) && !couponId.equals("null")){
	    		orders.setCouponId(Integer.parseInt(couponId));
	    	}
	    	
	    	//保存订单主表数据
	    	ordersDAO.submitOrder(orders);
	    	for(int j = 0;j < orderLinesList.size();j++){
	    		//添加订单号
	    		orderLinesList.get(j).setOrderId(Integer.valueOf(orders.getOrderId().toString()));
	    		orderLinesList.get(j).setCreatedUser(userId);
	    		//增加订单明细
	    		//ordersMapper.insertOrderLines(orderLinesList.get(j));
	    	}
	    	//订单唯一ID
	    	String onlyId=get13var();
	    	rmap.put("result","0");
	    	rmap.put("onlyId",onlyId);
	    	rmap.put("descript","提交订单成功，订单号为:"+orders.getOrderId()+"!");
	    } catch(Exception e) {
	    	e.printStackTrace();
			rmap.put("result","1");
			rmap.put("descript","提交订单失败");
		    throw new RuntimeException("提交订单失败");
	    }
		return null;
	}
	
	/**
	 * 随机生成13位的订单号
	 * @return
	 */
	public static String get13var(){
		boolean t = true;
		String rMath = null;
		while(t){
			double kk=Math.pow(10,13);
			double kkkk =Math.round(kk*Math.random());
			BigDecimal bigDecimal = new BigDecimal(kkkk);
			rMath = bigDecimal.toString();
			if(rMath.length()==13){
				t=false;
			}
		}
		return rMath;
	}
}
