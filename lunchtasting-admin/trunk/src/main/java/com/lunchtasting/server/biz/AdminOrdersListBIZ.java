package com.lunchtasting.server.biz;

import com.lunchtasting.server.po.lt.OrdersList;

public interface AdminOrdersListBIZ {
   OrdersList getOrdersListById(Long orderId);
}
