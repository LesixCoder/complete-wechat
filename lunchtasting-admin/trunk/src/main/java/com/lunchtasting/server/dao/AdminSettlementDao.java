package com.lunchtasting.server.dao;

import java.util.HashMap;
import java.util.List;
import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.OrdersSettlement;

public interface AdminSettlementDao extends GenericDAO<OrdersSettlement>{
	List querySettlementList(HashMap map);
    int getSettlementCount(HashMap map);
    Long addSettlement(OrdersSettlement ordersSettlement);
    int getById(HashMap map);
    int getYearWeek(HashMap map);
    Long saveRemark(HashMap map);
    int getSellCrea(HashMap map);
    Long updateSellerSett(String id,String date);
    List querySettlementNotLimit(HashMap map);
    int updateOrdersList(HashMap map);
}
