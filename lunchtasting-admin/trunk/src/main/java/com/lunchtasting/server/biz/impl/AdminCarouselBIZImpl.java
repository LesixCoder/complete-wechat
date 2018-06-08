package com.lunchtasting.server.biz.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.AdminCarouselBIZ;
import com.lunchtasting.server.dao.AdminCarouselDao;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.po.lt.BannerIndex;
@Service
public class AdminCarouselBIZImpl implements AdminCarouselBIZ{
	@Autowired
	private AdminCarouselDao adminCarouselDao;
	@Override
	public Long addCarousel(BannerIndex photoCarousel) {
		// TODO Auto-generated method stub
		return adminCarouselDao.addCarousel(photoCarousel);
	}

	@Override
	public Long updateCarousel(BannerIndex photoCarousel) {
		// TODO Auto-generated method stub
		return adminCarouselDao.updateCarousel(photoCarousel);
	}

	@Override
	public HashMap queryCarouselList(HashMap map) {
		HashMap result = new HashMap();
	    List<BannerIndex> carouselList;
	    int totalCount;
    	 try{
    		 carouselList = adminCarouselDao.queryCarouselList(map);
    		 for(int i = 0;i<carouselList.size();i++){
    			 carouselList.get(i).setNewId(carouselList.get(i).getId().toString());
    		 }
    		 totalCount = adminCarouselDao.getCarouselCount(map);
    	 }catch(Exception e){
			 e.printStackTrace();
			 result.put("result","1");
		     result.put("descript","查询失败");
		     result.put("totalCount",0);
		     result.put("page",new PageBean(null, Integer.parseInt(map.get("curPage").toString()), 0, Integer.parseInt(map.get("pageSize").toString())));
		     return result;
    	 }
    	 if(carouselList != null && carouselList.size() > 0){
    		 result.put("result","0");
		     result.put("descript","查询成功");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(carouselList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }else{
    		 result.put("result","0");
		     result.put("descript","没有更多");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(carouselList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }
    	 return result;
	}

	@Override
	public BannerIndex queryCarouselById(String id) {
		// TODO Auto-generated method stub
		return adminCarouselDao.queryCarouselById(id);
	}

	@Override
	public int getCarouselCount(HashMap map) {
		// TODO Auto-generated method stub
		return adminCarouselDao.getCarouselCount(map);
	}

	@Override
	public int deleteCarousel(HashMap map) {
		// TODO Auto-generated method stub
		return adminCarouselDao.deleteCarousel(map);
	}

}
