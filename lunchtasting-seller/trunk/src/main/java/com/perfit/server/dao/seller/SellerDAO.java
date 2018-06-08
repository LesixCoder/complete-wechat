package com.perfit.server.dao.seller;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Seller;

public interface SellerDAO extends  GenericDAO<Seller>{
	/**
	 * 登录
	 * @return
	 */
	Seller sellerUsersLogin(String account,String passwrod);
	
	/**
	 * 根据id查询seller
	 * @return
	 */
	Seller sellerById(Long id);
	
	/**
	 * 更新
	 * @param seller
	 * @return
	 */
	Long updateSeller(Seller seller);
}
