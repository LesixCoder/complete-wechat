package com.lunchtasting.server.biz;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.po.lt.OrdersSettlement;

public interface AdminSettlementBIZ {
	HashMap querySettlement(HashMap map);
	Long addSettlement(OrdersSettlement ordersSettlement);
	int getById(HashMap map);
	int getYearWeek(HashMap map);
	Long saveRemark(HashMap map);
	int getSellCrea(HashMap map);
	Long updateSellerSett(String id,String date);
	List querySettlementNotLimit(HashMap map);
	int updateOrdersList(Long orderId,Long settlementId,String settlementTime);
}
