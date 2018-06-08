package com.lunchtasting.server.biz;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.po.lt.Seller;

public interface AdminSellerBIZ {
	HashMap querySeller(HashMap map);
	Long addSeller(Seller seller);
	Long updateSeller(Seller seller);
	Seller querySellerById(String id);
	Long deleteSeller(HashMap map);
	Long selectSellerExist(String account);
	List<Seller> querySellerNotLimit(HashMap map);
	Seller querySellerByName(String name);
	Seller getSellerNextSettDate(String id);
}
