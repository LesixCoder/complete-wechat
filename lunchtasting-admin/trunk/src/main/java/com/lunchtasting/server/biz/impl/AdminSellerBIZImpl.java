package com.lunchtasting.server.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lunchtasting.server.biz.AdminSellerBIZ;
import com.lunchtasting.server.dao.AdminSellerDao;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.po.lt.Seller;
@Service
public class AdminSellerBIZImpl implements AdminSellerBIZ{
	@Autowired
	private AdminSellerDao adminSellerDao;
	@Override
	public HashMap querySeller(HashMap map) {
		HashMap result = new HashMap();
	    List<Seller> sellerList;
	    int totalCount;
    	 try{
    		 sellerList = adminSellerDao.querySellerList(map);
    		 for(int i = 0;i<sellerList.size();i++){
    			 sellerList.get(i).setNewId(sellerList.get(i).getId().toString());
    		 }
    		 totalCount = adminSellerDao.querySellerListCount(map);
    	 }catch(Exception e){
			 e.printStackTrace();
			 result.put("result","1");
		     result.put("descript","查询失败");
		     result.put("totalCount",0);
		     result.put("page",new PageBean(null, Integer.parseInt(map.get("curPage").toString()), 0, Integer.parseInt(map.get("pageSize").toString())));
		     return result;
    	 }
    	 if(sellerList != null && sellerList.size() > 0){
    		 result.put("result","0");
		     result.put("descript","查询成功");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(sellerList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }else{
    		 result.put("result","0");
		     result.put("descript","没有更多");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(sellerList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }
		return result;
	}
	
	@Override
	public Long addSeller(Seller seller) {
		// TODO Auto-generated method stub
		return adminSellerDao.addSeller(seller);
	}

	@Override
	public Long updateSeller(Seller seller) {
		// TODO Auto-generated method stub
		return adminSellerDao.updateSeller(seller);
	}

	@Override
	public Seller querySellerById(String id) {
		// TODO Auto-generated method stub
		return adminSellerDao.querySellerById(id);
	}

	@Override
	public Long deleteSeller(HashMap map) {
		// TODO Auto-generated method stub
		return adminSellerDao.deleteSeller(map);
	}

	@Override
	public Long selectSellerExist(String account) {
		// TODO Auto-generated method stub
		return adminSellerDao.selectSellerExist(account);
	}

	@Override
	public List<Seller> querySellerNotLimit(HashMap map) {
		// TODO Auto-generated method stub
		return adminSellerDao.querySellerNotLimit(map);
	}

	@Override
	public Seller querySellerByName(String name) {
		// TODO Auto-generated method stub
		return adminSellerDao.querySellerByName(name);
	}

	@Override
	public Seller getSellerNextSettDate(String id) {
		// TODO Auto-generated method stub
		return adminSellerDao.getSellerNextSettDate(id);
	}


}
