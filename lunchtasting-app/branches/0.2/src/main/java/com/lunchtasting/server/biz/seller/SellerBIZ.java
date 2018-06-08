package com.lunchtasting.server.biz.seller;

import com.lunchtasting.server.po.lt.Seller;


public interface SellerBIZ {

	/**
	 * 通过账号或邮箱和密码登录
	 * @param account
	 * @param pwd
	 * @return
	 */
	Seller getByAccountAndPwd(String account,String pwd);
}
