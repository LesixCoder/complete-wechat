package com.lunchtasting.server.biz;

import java.util.List;

import com.lunchtasting.server.po.lt.SellerTemporary;

public interface AdminSellerTemporaryBIZ {
	Long addSellerTemporary(SellerTemporary sellerTemporary) throws Exception;
	List querySellerTemporary();
}
