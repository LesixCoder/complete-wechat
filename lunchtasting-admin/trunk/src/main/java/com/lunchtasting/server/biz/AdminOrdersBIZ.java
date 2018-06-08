package com.lunchtasting.server.biz;

import java.util.HashMap;
import java.util.List;

public interface AdminOrdersBIZ {
	HashMap queryOrders(HashMap map);
	List queryOrdersListForSettl(HashMap map);
}
