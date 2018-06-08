package com.lunchtasting.server.dao;

import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.SellerTemporary;

public interface AdminSellerTemporaryDao extends GenericDAO<SellerTemporary>{
	Long addSellerTemporary(SellerTemporary sellerTemporary);
	List querySellerTemporary();
}
