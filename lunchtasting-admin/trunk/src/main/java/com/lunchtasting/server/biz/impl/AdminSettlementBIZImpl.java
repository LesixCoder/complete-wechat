package com.lunchtasting.server.biz.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lunchtasting.server.biz.AdminSettlementBIZ;
import com.lunchtasting.server.dao.AdminSettlementDao;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.po.lt.Orders;
import com.lunchtasting.server.po.lt.OrdersSettlement;
@Transactional(rollbackFor = Throwable.class)
@Service
public class AdminSettlementBIZImpl implements AdminSettlementBIZ{
	@Autowired
	private AdminSettlementDao adminSettlementDao;
	@Override
	public HashMap querySettlement(HashMap map) {
		HashMap result = new HashMap();
	    List<OrdersSettlement> settlementList;
	    int totalCount;
    	 try{
    		 settlementList = adminSettlementDao.querySettlementList(map);
    		 for(int i = 0;i<settlementList.size();i++){
    			 if(settlementList.get(i).getId() != null){
    			 settlementList.get(i).setNewId(settlementList.get(i).getId().toString());
    			 settlementList.get(i).setNewSellerId(settlementList.get(i).getSellerId().toString());
    			 }
    		 }
    		 totalCount = adminSettlementDao.getSettlementCount(map);
    	 }catch(Exception e){
			 e.printStackTrace();
			 result.put("result","1");
		     result.put("descript","查询失败");
		     result.put("totalCount",0);
		     result.put("page",new PageBean(null, Integer.parseInt(map.get("curPage").toString()), 0, Integer.parseInt(map.get("pageSize").toString())));
		     return result;
    	 }
    	 if(settlementList != null && settlementList.size() > 0){
    		 result.put("result","0");
		     result.put("descript","查询成功");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(settlementList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }else{
    		 result.put("result","0");
		     result.put("descript","没有更多");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(settlementList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }
		return result;
	}
	@Override
	public Long addSettlement(OrdersSettlement ordersSettlement) {
		// TODO Auto-generated method stub
		return adminSettlementDao.addSettlement(ordersSettlement);
	}
	@Override
	public int getById(HashMap map) {
		// TODO Auto-generated method stub
		return adminSettlementDao.getById(map);
	}
	@Override
	public Long saveRemark(HashMap map) {
		// TODO Auto-generated method stub
		return adminSettlementDao.saveRemark(map);
	}
	@Override
	public int getYearWeek(HashMap map) {
		// TODO Auto-generated method stub
		return adminSettlementDao.getYearWeek(map);
	}
	@Override
	public int getSellCrea(HashMap map) {
		// TODO Auto-generated method stub
		return adminSettlementDao.getSellCrea(map);
	}
	@Override
	public Long updateSellerSett(String id,String date) {
		// TODO Auto-generated method stub
		return adminSettlementDao.updateSellerSett(id,date);
	}
	@Override
	public List querySettlementNotLimit(HashMap map) {
		// TODO Auto-generated method stub
		return adminSettlementDao.querySettlementNotLimit(map);
	}
	@Override
	public int updateOrdersList(Long orderId, Long settlementId,
			String settlementTime) {
		HashMap map = new HashMap();
		map.put("orderId", orderId);
		map.put("settlementId", settlementId);
		map.put("settlementTime", settlementTime);
		return adminSettlementDao.updateOrdersList(map);
	}

}
