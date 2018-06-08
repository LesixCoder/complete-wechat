package com.lunchtasting.server.biz.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lunchtasting.server.biz.AdminOrdersBIZ;
import com.lunchtasting.server.dao.AdminOrdersDao;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.po.lt.Orders;

@Service
public class AdminOrdersBIZImpl implements AdminOrdersBIZ{
	@Autowired
	private AdminOrdersDao adminOrdersDao;
	@Override
	public HashMap queryOrders(HashMap map) {
		HashMap result = new HashMap();
	    List<Orders> ordersList;
	    int totalCount;
    	 try{
    		 ordersList = adminOrdersDao.queryOrdersList(map);
    		 for(int i = 0;i<ordersList.size();i++){
    			 ordersList.get(i).setNewId(ordersList.get(i).getId().toString());
    		 }
    		 totalCount = adminOrdersDao.getOrdersCount(map);
    	 }catch(Exception e){
			 e.printStackTrace();
			 result.put("result","1");
		     result.put("descript","查询失败");
		     result.put("totalCount",0);
		     result.put("page",new PageBean(null, Integer.parseInt(map.get("curPage").toString()), 0, Integer.parseInt(map.get("pageSize").toString())));
		     return result;
    	 }
    	 if(ordersList != null && ordersList.size() > 0){
    		 result.put("result","0");
		     result.put("descript","查询成功");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(ordersList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }else{
    		 result.put("result","0");
		     result.put("descript","没有更多");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(ordersList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }
		return result;
	}
	@Override
	public List queryOrdersListForSettl(HashMap map) {
		// TODO Auto-generated method stub
		return adminOrdersDao.queryOrdersListForSettl(map);
	}

}
