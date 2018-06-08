package com.lunchtasting.server.dao.seller;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Seller;

@Repository
public interface SellerDAO extends GenericDAO<Seller>  {


	/**
	 * 通过账号或邮箱和密码登录
	 * @param account
	 * @param pwd
	 * @return
	 */
	Seller getByAccountAndPwd(String account,String pwd);
}
