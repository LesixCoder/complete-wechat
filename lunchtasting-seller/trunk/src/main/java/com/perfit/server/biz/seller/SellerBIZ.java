package com.perfit.server.biz.seller;

import com.lunchtasting.server.po.lt.Seller;

public interface SellerBIZ {
	/**
	 * 登录
	 * @param account
	 * @param passwrod
	 * @return
	 */
	Seller sellerUsersLogin(String account,String passwrod);
	
	/**
	 * 根据id查询Seller
	 * @param id
	 * @return
	 */
	Seller sellerById(Long id);
	
	/**
	 * 更新seller
	 * @param seller
	 * @return
	 */
	boolean updateSeller(Seller seller);
}
